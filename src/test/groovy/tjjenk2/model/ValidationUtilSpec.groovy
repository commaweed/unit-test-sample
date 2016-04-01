package tjjenk2.model

import spock.lang.Specification
import spock.lang.Unroll

class ValidationUtilSpec extends Specification {

	def "Invoking validateRequiredField with too few arguments produces error"() {
		when:
			ValidationUtil.validateRequiredField()
		then:
			thrown(IllegalArgumentException.class)
	}

	def "Invoking validateRequiredField with too many arguments produces error"() {
		when:
			ValidationUtil.validateRequiredField("1", "2", "3")
		then:
			thrown(IllegalArgumentException.class)
	}

	@Unroll
	def "Invoking validateRequiredField with bad arguments produces error: (#value1, #value2)"() {
		when:
			ValidationUtil.validateRequiredField(value1, value2)
		then:
			thrown(IllegalArgumentException.class)
		where:
			value1 				| value2
			null				| null
			null				| "field"
		    ""					| "field"
	}

	@Unroll
	def "Invoking validateRequiredField with good arguments is a success: (#value1, #value2, #expected_result)"() {
		when:
			def actualResult = ValidationUtil.validateRequiredField(value1, value2)
		then:
			actualResult != null
			actualResult == expected_result
		where:
			value1 				| value2			|| expected_result
			" travis "			| null				|| "travis"
			"jenkins"			| "field"			|| "jenkins"
	}
}
