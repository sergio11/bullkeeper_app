package sanchez.sanchez.sergio.data.net.models.request;

import com.fasterxml.jackson.annotation.JsonProperty;
import java.io.Serializable;
import java.util.Date;

/**
 * Register Kid DTO
 */
public final class RegisterKidDTO implements Serializable {

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

    public RegisterKidDTO(){}

    public RegisterKidDTO(String firstName, String lastName, String birthdate, String school) {
        this.firstName = firstName;
        this.lastName = lastName;
        this.birthdate = birthdate;
        this.school = school;
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
        return "RegisterKidDTO{" +
                "firstName='" + firstName + '\'' +
                ", lastName='" + lastName + '\'' +
                ", birthdate='" + birthdate + '\'' +
                ", school='" + school + '\'' +
                '}';
    }
}
