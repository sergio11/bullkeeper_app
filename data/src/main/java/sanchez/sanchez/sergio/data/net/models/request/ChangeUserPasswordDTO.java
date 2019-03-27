package sanchez.sanchez.sergio.data.net.models.request;

import com.fasterxml.jackson.annotation.JsonProperty;
import java.io.Serializable;

/**
 * Change User Password DTO
 **/
public final class ChangeUserPasswordDTO implements Serializable {

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

    public ChangeUserPasswordDTO(){}

    /**
     *
     * @param passwordClear
     * @param confirmPassword
     */
    public ChangeUserPasswordDTO(final String passwordClear, final String confirmPassword) {
        this.passwordClear = passwordClear;
        this.confirmPassword = confirmPassword;
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

    @Override
    public String toString() {
        return "ChangeUserPasswordDTO{" +
                "passwordClear='" + passwordClear + '\'' +
                ", confirmPassword='" + confirmPassword + '\'' +
                '}';
    }
}
