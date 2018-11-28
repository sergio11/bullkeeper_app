package sanchez.sanchez.sergio.data.net.models.request;

import com.fasterxml.jackson.annotation.JsonProperty;
import java.io.Serializable;
import java.util.Date;

/**
 * Update Kid DTO
 */
public final class UpdateKidDTO implements Serializable{

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
     * Birth Date
     */
    @JsonProperty("birthdate")
    private String birthdate;

    /**
     * School
     */
    @JsonProperty("school")
    private String school;

    public UpdateKidDTO(){}

    public UpdateKidDTO(String identity, String firstName, String lastName, String birthdate, String school) {
        this.identity = identity;
        this.firstName = firstName;
        this.lastName = lastName;
        this.birthdate = birthdate;
        this.school = school;
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

    public String getBirthdate() {
        return birthdate;
    }

    public void setBirthdate(String birthdate) {
        this.birthdate = birthdate;
    }

    public String getSchool() {
        return school;
    }

    public void setSchool(String school) {
        this.school = school;
    }

    @Override
    public String toString() {
        return "UpdateKidDTO{" +
                "identity='" + identity + '\'' +
                ", firstName='" + firstName + '\'' +
                ", lastName='" + lastName + '\'' +
                ", birthdate='" + birthdate + '\'' +
                ", school='" + school + '\'' +
                '}';
    }
}
