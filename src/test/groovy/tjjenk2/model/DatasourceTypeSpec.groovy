package tjjenk2.model

import spock.lang.Specification
import spock.lang.Unroll
/**
 * Test DatasourceType using Spock - compare to JUnit test of EnvironmentType.
 */
class DatasourceTypeSpec extends Specification {

	@Unroll
	def "supplying invalid arguments to determineValue() produces an error when using #invalidValue"() {
		when:
			DatasourceType.determineValue(invalidValue as String)
		then:
			thrown(IllegalArgumentException.class)
		where:
			invalidValue 	| _
			null			| _
			"bad-value"		| _
	}

	@Unroll
	def "supplying valid arguments to determineValue() works when using #validValue"() {

		// somewhat brittle when/then/where example
		when:
			def result = DatasourceType.determineValue(validValue)
			def actualLabel = result.getLabel()
		then:
			result instanceof DatasourceType
			actualLabel != null
			actualLabel == expectedLabel
			result.isSuperPowered() == expectedIsSuperPowered
		where:
			validValue				| expectedLabel				| expectedIsSuperPowered
			"somePostGresSource"	| "PostGresSQL Source"		| false
			"someMongoSource"		| "MongoDB Source"			| true
			"someRestSource"		| "RESTFul Service Source"	| false
	}

	// this is the exact same test as the one above (demonstrating pipes)
	@Unroll
	def "supplying valid arguments to determineValue() works (ex 2) when using #validValue"() {
		when:
			def result = DatasourceType.determineValue(validValue)
			def actualLabel = result.getLabel()
		then:
			result instanceof DatasourceType
			actualLabel != null
			actualLabel == expectedLabel
			result.isSuperPowered() == expectedIsSuperPow
		where:
			validValue << DatasourceType.values().collect { it.toString() }
			expectedLabel << DatasourceType.values().collect { it.getLabel() }
			expectedIsSuperPow << DatasourceType.values().collect { it.isSuperPowered() }
	}
}
