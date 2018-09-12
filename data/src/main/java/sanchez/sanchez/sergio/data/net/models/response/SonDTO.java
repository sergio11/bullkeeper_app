package sanchez.sanchez.sergio.data.net.models.response;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonProperty;
import java.io.Serializable;
import java.util.Date;
import java.util.Map;
import sanchez.sanchez.sergio.domain.models.AlertLevelEnum;

/**
 * Son DTO
 */
@JsonIgnoreProperties(ignoreUnknown = true)
public final class SonDTO implements Serializable {

    @JsonProperty("identity")
    private String identity;

    @JsonProperty("first_name")
    private String firstName;

    @JsonProperty("last_name")
    private String lastName;

    @JsonProperty("birthdate")
    private Date birthdate;

    @JsonProperty("age")
    private Integer age;

    @JsonProperty("school")
    private SchoolDTO schoolDTO;

    @JsonProperty("profile_image")
    private String profileImage;

    @JsonProperty("alert_statistics")
    private Map<AlertLevelEnum, Integer> alertStatistics;


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

    public Date getBirthdate() {
        return birthdate;
    }

    public void setBirthdate(Date birthdate) {
        this.birthdate = birthdate;
    }

    public Integer getAge() {
        return age;
    }

    public void setAge(Integer age) {
        this.age = age;
    }

    public SchoolDTO getSchoolDTO() {
        return schoolDTO;
    }

    public void setSchoolDTO(SchoolDTO schoolDTO) {
        this.schoolDTO = schoolDTO;
    }

    public String getProfileImage() {
        return profileImage;
    }

    public void setProfileImage(String profileImage) {
        this.profileImage = profileImage;
    }

    public Map<AlertLevelEnum, Integer> getAlertStatistics() {
        return alertStatistics;
    }

    public void setAlertStatistics(Map<AlertLevelEnum, Integer> alertStatistics) {
        this.alertStatistics = alertStatistics;
    }

    @Override
    public String toString() {
        return "SonDTO{" +
                "identity='" + identity + '\'' +
                ", firstName='" + firstName + '\'' +
                ", lastName='" + lastName + '\'' +
                ", birthdate='" + birthdate + '\'' +
                ", age=" + age +
                ", schoolDTO=" + schoolDTO +
                ", profileImage='" + profileImage + '\'' +
                ", alertStatistics=" + alertStatistics +
                '}';
    }
}
