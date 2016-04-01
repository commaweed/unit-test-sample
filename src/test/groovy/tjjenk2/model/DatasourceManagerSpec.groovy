package tjjenk2.model

import org.springframework.test.util.ReflectionTestUtils
import spock.lang.Shared
import spock.lang.Specification
/**
 * Test suite for the DatasourceManager cache.
 */
class DatasourceManagerSpec extends Specification {
	@Shared
	def mockProperties = [
		DatasourceType.someMongoSource.toString() + ":" + EnvironmentType.prod.toString(),
		DatasourceType.someRestSource.toString() + ":" + EnvironmentType.development.toString()
	]

	def "The constructor successfully creates the cache with valid arguments"() {
		when:
			def manager = new DatasourceManager(mockProperties as Set<String>)

			// use spring-test to get a reference to private field
			Map<DatasourceType, EnvironmentType> cacheRef =
				ReflectionTestUtils.getField(manager, 'datasourceCache') as Map<DatasourceType, EnvironmentType>

		then: "the cache was created successfully"

			cacheRef != null
			cacheRef.size() == DatasourceType.values().size()

		and: "the mock properties were added to the cache correctly"

			cacheRef.each { entry ->
				DatasourceType datasource = entry.key
				EnvironmentType environment = entry.value

				def actualValue = cacheRef[datasource]

				cacheRef[datasource] != null
				cacheRef[datasource] == environment

				switch(datasource) {
					case DatasourceType.someMongoSource:
						cacheRef[datasource] == EnvironmentType.prod
						break
					case DatasourceType.someRestSource:
						cacheRef[datasource] == EnvironmentType.development
						break
					default:
						cacheRef[datasource] == EnvironmentType.none
						break
				}
			}
	}
}
