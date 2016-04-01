package tjjenk2.model;

import org.junit.*;
import org.junit.runner.RunWith;
import org.mockito.Mockito;
import org.powermock.core.classloader.annotations.PrepareForTest;
import org.powermock.modules.junit4.PowerMockRunner;

import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.allOf;
import static org.hamcrest.Matchers.containsString;
import static org.hamcrest.Matchers.equalTo;
import static org.mockito.Matchers.anyString;
import static org.powermock.api.mockito.PowerMockito.mockStatic;
import static org.powermock.api.mockito.PowerMockito.verifyStatic;

/**
 * Unit test for ValidationUtil; demonstrating the use of power mock to test a static method.
 */
@RunWith(PowerMockRunner.class)
@PrepareForTest(ValidationUtil.class) // contains static method
public class UserTest {

	@BeforeClass
	public static void setupBeforeAll() {

	}

	@AfterClass
	public static void cleanupAfterAll() {}

	@Before
	public void setupBeforeEach() {
		mockStatic(ValidationUtil.class);
	}

	@After
	public void cleanupAfterEach() {}


	@Test(expected=IllegalArgumentException.class)
	public void testWhereValidationFails() {

		// Mock static validation call
		// we could avoid mocking here and let the User test ValidationUtil.validateRequiredField
		// but this is an example of an isolated test
		Mockito.when(
			ValidationUtil.validateRequiredField(anyString())
		).thenThrow(
			new IllegalArgumentException("Fake test exception; failed simulated argument validation")
		);


		new User("who", "cares", "sdjkf");
	}

	/**
	 * Utility method used to mock the static validation with fake data.
	 * @param expectedValue The fake value to return from the mock call.
	 * @param hasFieldName Indicates whether or not to include the field name.
	 */
	private void mockValidation(String expectedValue, boolean hasFieldName) {
		Mockito.when(
			hasFieldName ?
			ValidationUtil.validateRequiredField(anyString()) :
			ValidationUtil.validateRequiredField(anyString(), anyString())
		).thenReturn(
			expectedValue
		);
	}

	@Test
	public void testSuccessfulUserCreation() {
		// we could avoid mocking here and let the User test ValidationUtil.validateRequiredField
		// but this is an example of an isolated test
		String expectedFirstName = "fake_first_name";
		String expectedLastName = "fake_first_name";
		String expectedMiddleName = "fake_middle_name";

		mockValidation(expectedFirstName, true);
		mockValidation(expectedLastName, false);


		User user = new User("fake_first_name", "fake_last_name", "fake_middle_name");

		// some hamcrest asserts
		assertThat(user.getFirstName(), equalTo(expectedFirstName));
		assertThat(user.getMiddleName(), equalTo(expectedMiddleName));
		assertThat(user.getLastName(), equalTo(expectedLastName));

		// this is not a very good test for full name; just demonstrating hamcrest
		assertThat(
			user.getFullName(),
			allOf(
				containsString(expectedFirstName),
				containsString(expectedMiddleName),
				containsString(expectedLastName)
			)
		);

		// ensure the static method was called twice, once for first name and once for last name
		verifyStatic(Mockito.times(2));
	}
}
