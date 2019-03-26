package sanchez.sanchez.sergio.data.net.models.request;

import com.fasterxml.jackson.annotation.JsonProperty;
import java.io.Serializable;

/**
 * Update Guardian DTO
 */
public final class UpdateGuardianDTO implements Serializable {

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
     * Birth Date
     */
    @JsonProperty("birthdate")
    private String birthdate;


    /**
     * Telephone
     */
    @JsonProperty("telephone")
    private String telephone;

    /**
     * Profile Image
     */
    private String profileImage;


    /**
     * Visible
     */
    @JsonProperty("visible")
    protected boolean visible = false;

    public UpdateGuardianDTO(){}

    public UpdateGuardianDTO(String firstName, String lastName,
                             String birthdate, String telephone, boolean visible) {
        this.firstName = firstName;
        this.lastName = lastName;
        this.birthdate = birthdate;
        this.telephone = telephone;
        this.visible = visible;
    }

    public UpdateGuardianDTO(String firstName, String lastName, String birthdate, String telephone, String profileImage) {
        this.firstName = firstName;
        this.lastName = lastName;
        this.birthdate = birthdate;
        this.telephone = telephone;
        this.profileImage = profileImage;
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

    public String getTelephone() {
        return telephone;
    }

    public void setTelephone(String telephone) {
        this.telephone = telephone;
    }

    public boolean isVisible() {
        return visible;
    }

    public void setVisible(boolean visible) {
        this.visible = visible;
    }

    public String getProfileImage() {
        return profileImage;
    }

    public void setProfileImage(String profileImage) {
        this.profileImage = profileImage;
    }

    @Override
    public String toString() {
        return "UpdateGuardianDTO{" +
                "firstName='" + firstName + '\'' +
                ", lastName='" + lastName + '\'' +
                ", birthdate='" + birthdate + '\'' +
                ", telephone='" + telephone + '\'' +
                ", profileImage='" + profileImage + '\'' +
                ", visible=" + visible +
                '}';
    }
}
