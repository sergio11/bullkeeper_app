package sanchez.sanchez.sergio.data.net.models.response;

import com.fasterxml.jackson.annotation.JsonProperty;

import java.io.Serializable;

/**
 * Person DTO
 */
public final class PersonDTO implements Serializable {

    /**
     * Identity
     */
    @JsonProperty("identity")
    private String identity;

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
     * Profile Image
     */
    @JsonProperty("profile_image")
    private String profileImage;


    public PersonDTO(){}

    /**
     *
     * @param identity
     * @param firstName
     * @param lastName
     * @param profileImage
     */
    public PersonDTO(String identity, String firstName, String lastName, String profileImage) {
        this.identity = identity;
        this.firstName = firstName;
        this.lastName = lastName;
        this.profileImage = profileImage;
    }

    public String getIdentity() {
        return identity;
    }

    public void setIdentity(String identity) {
        this.identity = identity;
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

    public String getProfileImage() {
        return profileImage;
    }

    public void setProfileImage(String profileImage) {
        this.profileImage = profileImage;
    }

    @Override
    public String toString() {
        return "PersonDTO{" +
                "identity='" + identity + '\'' +
                ", firstName='" + firstName + '\'' +
                ", lastName='" + lastName + '\'' +
                ", profileImage='" + profileImage + '\'' +
                '}';
    }
}
