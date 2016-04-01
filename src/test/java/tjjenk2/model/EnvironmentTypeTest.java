package tjjenk2.model;

import org.junit.Test;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotNull;



/**
 * Demonstrates a JUnit test.  Unit test for EnvironmentType.
 */
public class EnvironmentTypeTest {

	@Test(expected=IllegalArgumentException.class)
	public void testDetermineValueNullValue() {
		EnvironmentType.determineValue(null);
	}

	@Test(expected=IllegalArgumentException.class)
	public void testDetermineValueBogusValue() {
		EnvironmentType.determineValue("Bogus Value");
	}

	@Test
	public void testDetermineValueSuccess() {
		for (EnvironmentType env : EnvironmentType.values()) {
			EnvironmentType result = EnvironmentType.determineValue(env.toString());
			assertNotNull(result);
			assertEquals(env, result);
		}
	}
}
