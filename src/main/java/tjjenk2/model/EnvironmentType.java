package tjjenk2.model;

public enum EnvironmentType {
	prod,
	staging,
	development,
	none;

	/**
	 * Returns a matching Environment for the given value; meant to replace valueOf() to overload error handling.
	 * @param value The value to convernt to an Environment.
	 * @return An Environment
	 * @throws IllegalArgumentException if value is not a valid Environment.
	 */
	public static EnvironmentType determineValue(String value) {
		EnvironmentType env;

		try {
			env = EnvironmentType.valueOf(value);
		} catch (IllegalArgumentException | NullPointerException e) {
			throw new IllegalArgumentException(
				String.format(
					"Unable to convert environment for value %s; it does not match existing values.",
					value == null ? "[it is null]" : value
				)
			);
		}

		return env;
	}
}
