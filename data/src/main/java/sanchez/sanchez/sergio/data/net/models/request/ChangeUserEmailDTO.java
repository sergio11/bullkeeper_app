package sanchez.sanchez.sergio.data.net.models.request;

import com.fasterxml.jackson.annotation.JsonProperty;

import java.io.Serializable;

/**
 * Change User Email DTO
 **/
public final class ChangeUserEmailDTO implements Serializable {

    /**
     * Current Email
     */
    @JsonProperty("current_email")
    private String currentEmail;

    /**
     * New Email
     */
    @JsonProperty("new_email")
    private String newEmail;

    public ChangeUserEmailDTO(){}

    public ChangeUserEmailDTO(final String currentEmail, final String newEmail) {
        this.currentEmail = currentEmail;
        this.newEmail = newEmail;
    }

    public String getCurrentEmail() {
        return currentEmail;
    }

    public void setCurrentEmail(String currentEmail) {
        this.currentEmail = currentEmail;
    }

    public String getNewEmail() {
        return newEmail;
    }

    public void setNewEmail(String newEmail) {
        this.newEmail = newEmail;
    }

    @Override
    public String toString() {
        return "ChangeUserEmailDTO{" +
                "currentEmail='" + currentEmail + '\'' +
                ", newEmail='" + newEmail + '\'' +
                '}';
    }
}
