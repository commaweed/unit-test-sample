package tjjenk2.model;

import org.apache.commons.lang3.ArrayUtils;
import org.apache.commons.lang3.StringUtils;
import org.springframework.util.CollectionUtils;

import java.util.*;


/**
 * A fake datasource manager that maintains a cache of all the fake data sources and their associated
 * environments.
 */
public class DatasourceManager {
	private Map<DatasourceType, EnvironmentType> datasourceCache;

	public DatasourceManager(Set<String> datasourceProperties) {
		if (CollectionUtils.isEmpty(datasourceProperties)) {
			throw new IllegalArgumentException("Invalid datasourceProperties; it cannot be null!");
		}

		prepopulateCache();

		for (String prop : datasourceProperties) {
			String[] values = StringUtils.split(prop, ":");

			if (!ArrayUtils.isEmpty(values) && values.length == 2) {
				datasourceCache.put(
					DatasourceType.determineValue(values[0]),
					EnvironmentType.determineValue(values[1])
				);
			}
		}
	}

	public void prepopulateCache() {
		datasourceCache = new HashMap<>();
		for (DatasourceType datasource : DatasourceType.values()) {
			datasourceCache.put(datasource, EnvironmentType.none);
		}
	}

	public EnvironmentType getEnvironment(DatasourceType datasource) {
		if (datasource == null) {
			throw new IllegalArgumentException("Invalid datasource; it cannot be null!");
		}

		return datasourceCache.get(datasource);
	}

	public Set<DatasourceType> getDatasources() {
		return datasourceCache.keySet();
	}

	public Collection<EnvironmentType> getEnvironmentTypes() {
		return datasourceCache.values();
	}
}
