package sanchez.sanchez.sergio.data.net.models.request;

import com.fasterxml.jackson.annotation.JsonProperty;
import java.io.Serializable;

/**
 * Register Guardian
 */
public final class RegisterGuardianDTO implements Serializable {

    /**
     * First Name
     */
    @JsonProperty("first_name")
    private String firstName;

    /**
     * Last Name
     */
    @JsonProperty("last_name")
    private String lastName;

    /**
     * Bith Date
     */
    @JsonProperty("birthdate")
    private String birthdate;

    /**
     * Email
     */
    @JsonProperty("email")
    private String email;

    /**
     * Password Clear
     */
    @JsonProperty("password_clear")
    private String passwordClear;

    /**
     * Confirm Password
     */
    @JsonProperty("confirm_password")
    private String confirmPassword;

    /**
     * Locale
     */
    @JsonProperty("locale")
    private String locale;

    /**
     * Telephone
     */
    @JsonProperty("telephone")
    private String telephone;

    public RegisterGuardianDTO(){}

    public RegisterGuardianDTO(final String firstName, final String lastName, final String birthdate, final String email,
                               final String passwordClear, final String confirmPassword, final String locale,
                               final String telephone) {
        this.firstName = firstName;
        this.lastName = lastName;
        this.birthdate = birthdate;
        this.email = email;
        this.passwordClear = passwordClear;
        this.confirmPassword = confirmPassword;
        this.locale = locale;
        this.telephone = telephone;
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

    public String getBirthdate() {
        return birthdate;
    }

    public void setBirthdate(String birthdate) {
        this.birthdate = birthdate;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getPasswordClear() {
        return passwordClear;
    }

    public void setPasswordClear(String passwordClear) {
        this.passwordClear = passwordClear;
    }

    public String getConfirmPassword() {
        return confirmPassword;
    }

    public void setConfirmPassword(String confirmPassword) {
        this.confirmPassword = confirmPassword;
    }

    public String getLocale() {
        return locale;
    }

    public void setLocale(String locale) {
        this.locale = locale;
    }

    public String getTelephone() {
        return telephone;
    }

    public void setTelephone(String telephone) {
        this.telephone = telephone;
    }

    @Override
    public String toString() {
        return "RegisterGuardianDTO{" +
                "firstName='" + firstName + '\'' +
                ", lastName='" + lastName + '\'' +
                ", birthdate='" + birthdate + '\'' +
                ", email='" + email + '\'' +
                ", passwordClear='" + passwordClear + '\'' +
                ", confirmPassword='" + confirmPassword + '\'' +
                ", telephone='" + telephone + '\'' +
                '}';
    }
}
