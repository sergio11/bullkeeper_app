package sanchez.sanchez.sergio.domain.models;

import java.io.Serializable;

/**
 * Person Entity
 */
public final class PersonEntity implements Serializable {

    private String identity;
    private String firstName;
    private String lastName;
    private String profileImage;

    public PersonEntity(){}

    public PersonEntity(String identity, String firstName, String lastName, String profileImage) {
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

    public String getFullName(){
        return firstName + " " + lastName;
    }

    @Override
    public String toString() {
        return "PersonEntity{" +
                "identity='" + identity + '\'' +
                ", firstName='" + firstName + '\'' +
                ", lastName='" + lastName + '\'' +
                ", profileImage='" + profileImage + '\'' +
                '}';
    }
}
