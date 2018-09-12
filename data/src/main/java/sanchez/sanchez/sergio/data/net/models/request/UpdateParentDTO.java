package sanchez.sanchez.sergio.data.net.models.request;

import com.fasterxml.jackson.annotation.JsonProperty;
import java.io.Serializable;

/**
 * Update Parent DTO
 */
public final class UpdateParentDTO implements Serializable {

    @JsonProperty("first_name")
    private String firstName;

    @JsonProperty("last_name")
    private String lastName;

    @JsonProperty("birthdate")
    private String birthdate;

    @JsonProperty("email")
    private String email;

    @JsonProperty("telephone")
    private String telephone;

    public UpdateParentDTO(){}

    public UpdateParentDTO(String firstName, String lastName, String birthdate, String email, String telephone) {
        this.firstName = firstName;
        this.lastName = lastName;
        this.birthdate = birthdate;
        this.email = email;
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

    public String getTelephone() {
        return telephone;
    }

    public void setTelephone(String telephone) {
        this.telephone = telephone;
    }

    @Override
    public String toString() {
        return "UpdateParentDTO{" +
                "firstName='" + firstName + '\'' +
                ", lastName='" + lastName + '\'' +
                ", birthdate='" + birthdate + '\'' +
                ", email='" + email + '\'' +
                ", telephone='" + telephone + '\'' +
                '}';
    }
}
