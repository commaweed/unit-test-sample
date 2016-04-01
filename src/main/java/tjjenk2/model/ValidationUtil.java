package tjjenk2.model;

import org.apache.commons.lang3.StringUtils;

/**
 * A validation utility. This could be an interface with a default method in java 8;
 * want to demonstrate static method testing with PowerMock.
 */
public class ValidationUtil {

    // prevent instantiation
    private ValidationUtil() {}

    /**
     * Validates the given value for the given field.
     * @param fieldValue The value to validate or the value and field name.
     * @return The value will be trimmed.
     * @throws IllegalArgumentException If the value is invalid or if this validation method was invoked with improper
     *         arguments.
     */
    public static String validateRequiredField(String... fieldValue) {
        if (fieldValue != null && (fieldValue.length == 1 || fieldValue.length == 2)) {
            String formattedValue = fieldValue[0].trim();

            if (StringUtils.isEmpty(formattedValue)) {
                throw new IllegalArgumentException(
                    String.format(
                        "Invalid required field [%s]; it cannot be null or empty!",
                        fieldValue.length == 2 ? fieldValue[1] : "with value " + fieldValue[0]
                    )
                );
            }

            return formattedValue;
        } else {
            throw new IllegalArgumentException(
                "Invalid validation fieldValue; it cannot be null and must contain a value or a value and a fieldName!"
            );
        }
    }

}
