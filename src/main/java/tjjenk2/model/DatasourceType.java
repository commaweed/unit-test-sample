package tjjenk2.model;

public enum DatasourceType {
	somePostGresSource("PostGresSQL Source"),
	someMongoSource("MongoDB Source", true),
	someRestSource("RESTFul Service Source");

	private String label;
	private boolean superPowered;

	DatasourceType(
		String label
	) {
		this(label, false);
	}

	DatasourceType(
		String label,
		boolean superPowered
	) {
		this.label = label;
		this.superPowered = superPowered;
	}

	/**
	 * Returns a matching Data source for the given value; meant to replace valueOf() to overload error handling.
	 * @param value The value to convernt to an DatasourceType.
	 * @return A Data source type.
	 * @throws IllegalArgumentException if value is not a valid DatasourceType.
	 */
	public static DatasourceType determineValue(String value) {
		DatasourceType datasource;

		try {
			datasource = DatasourceType.valueOf(value);
		} catch (IllegalArgumentException | NullPointerException e) {
			throw new IllegalArgumentException(
				String.format(
					"Unable to convert data source for value %s; it does not match existing values.",
					value == null ? "[it is null]" : value
				)
			);
		}

		return datasource;
	}

	public String getLabel() {
		return label;
	}


	public boolean isSuperPowered() {
		return superPowered;
	}

}
