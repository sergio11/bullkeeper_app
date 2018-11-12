package sanchez.sanchez.sergio.domain.models;

import java.io.Serializable;
import java.util.Date;
import java.util.List;
import java.util.Map;

/**
 * Son Entity
 */
public final class SonEntity implements Serializable {

    /**
     * Identity
     */
    private String identity;

    /**
     * First Name
     */
    private String firstName;

    /**
     * Last Name
     */
    private String lastName;

    /**
     * Birth date
     */
    private Date birthdate;

    /**
     * Age
     */
    private int age;

    /**
     * School
     */
    private SchoolEntity school;

    /**
     * Profile Image
     */
    private String profileImage;

    /**
     * Alert Statistics
     */
    private Map<AlertLevelEnum, Integer> alertStatistics;

    /**
     * Terminal Entities
     */
    private List<TerminalEntity> terminalEntities;


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

    public int getAge() {
        return age;
    }

    public void setAge(int age) {
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

    public List<TerminalEntity> getTerminalEntities() {
        return terminalEntities;
    }

    public void setTerminalEntities(List<TerminalEntity> terminalEntities) {
        this.terminalEntities = terminalEntities;
    }

    @Override
    public String toString() {
        return "SonEntity{" +
                "identity='" + identity + '\'' +
                ", firstName='" + firstName + '\'' +
                ", lastName='" + lastName + '\'' +
                ", birthdate=" + birthdate +
                ", age=" + age +
                ", school=" + school +
                ", profileImage='" + profileImage + '\'' +
                ", alertStatistics=" + alertStatistics +
                ", terminalEntities=" + terminalEntities +
                '}';
    }
}
