package sanchez.sanchez.sergio.domain.models;

import java.io.Serializable;
import java.util.Date;
import java.util.Map;

/**
 * Son Entity
 */
public final class SonEntity implements Serializable {

    private String identity;
    private String firstName;
    private String lastName;
    private Date birthdate;
    private String age;
    private SchoolEntity school;
    private String profileImage;
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

    public String getAge() {
        return age;
    }

    public void setAge(String age) {
        this.age = age;
    }

    public SchoolEntity getSchool() {
        return school;
    }

    public void setSchool(SchoolEntity school) {
        this.school = school;
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

    public String getFullName() {
        return String.format("%s %s", firstName, lastName);

    }

    @Override
    public String toString() {
        return "SonEntity{" +
                "identity='" + identity + '\'' +
                ", firstName='" + firstName + '\'' +
                ", lastName='" + lastName + '\'' +
                ", birthdate=" + birthdate +
                ", age='" + age + '\'' +
                ", school=" + school +
                ", profileImage='" + profileImage + '\'' +
                ", alertStatistics=" + alertStatistics +
                '}';
    }
}
