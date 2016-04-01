package tjjenk2.model;

import org.apache.commons.lang3.StringUtils;
import org.apache.commons.lang3.builder.EqualsBuilder;
import org.apache.commons.lang3.builder.HashCodeBuilder;
import org.apache.commons.lang3.builder.ToStringBuilder;
import org.apache.commons.lang3.builder.ToStringStyle;


public class User {

    private String firstName;
    private String lastName;
    private String middleName;
    private String fullName;

	/**
     * Initialize.
     * @param firstName The user's first name.
     * @param lastName The user's last name.
     * @param middleName The optional middle name.
     */
    public User(
        String firstName,
        String lastName,
        String middleName
    ) {
        this.firstName = ValidationUtil.validateRequiredField(firstName, "firstName");
        this.lastName = ValidationUtil.validateRequiredField(lastName);

        this.middleName = StringUtils.trim(middleName);
        this.fullName = buildFullName();
    }

	/**
     * Builds the full name.
     * @return A string representing the full name.
     */
    private String buildFullName() {
        return String.format(
            "%s %s %s",
            firstName,
            StringUtils.isEmpty(middleName) ? "" : middleName,
            lastName
        );
    }

	/**
     * {@inheritDoc
     */
    @Override
    public String toString() {
        return ToStringBuilder.reflectionToString(this, ToStringStyle.MULTI_LINE_STYLE);
    }

    /**
     * {@inheritDoc
     */
    @Override
    public boolean equals(Object that) {
        return EqualsBuilder.reflectionEquals(this, that);
    }

    /**
     * {@inheritDoc
     */
    @Override
    public int hashCode() {
        return HashCodeBuilder.reflectionHashCode(this);
    }


    public String getFirstName() {
        return firstName;
    }

    public void setFirstName(String firstName) {
        this.firstName = firstName;
    }

    public String getLastName() {
        return lastName;
    }

    public void setLastName(String lastName) {
        this.lastName = lastName;
    }

    public String getMiddleName() {
        return middleName;
    }

    public void setMiddleName(String middleName) {
        this.middleName = middleName;
    }

    public String getFullName() {
        return fullName;
    }

    public void setFullName(String fullName) {
        this.fullName = fullName;
    }
}
