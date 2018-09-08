package sanchez.sanchez.sergio.data.net.models.request;

import com.fasterxml.jackson.annotation.JsonProperty;
import java.io.Serializable;
import java.util.Date;

/**
 * Register Son DTO
 */
public final class RegisterSonDTO implements Serializable {

    @JsonProperty("first_name")
    private String firstName;

    @JsonProperty("last_name")
    private String lastName;

    @JsonProperty("birthdate")
    private Date birthdate;

    @JsonProperty("school")
    private String school;

    public RegisterSonDTO(){}

    public RegisterSonDTO(String firstName, String lastName, Date birthdate, String school) {
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

    public Date getBirthdate() {
        return birthdate;
    }

    public void setBirthdate(Date birthdate) {
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
        return "RegisterSonDTO{" +
                "firstName='" + firstName + '\'' +
                ", lastName='" + lastName + '\'' +
                ", birthdate='" + birthdate + '\'' +
                ", school='" + school + '\'' +
                '}';
    }
}
