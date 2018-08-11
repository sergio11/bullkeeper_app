package sanchez.sanchez.sergio.data.models.response;

import com.fasterxml.jackson.annotation.JsonProperty;

import java.io.Serializable;
import java.util.Date;

/**
 * Parent DTO
 */
public final class ParentDTO implements Serializable {

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

    @JsonProperty("email")
    private String email;

    @JsonProperty("phone_prefix")
    private String phonePrefix;

    @JsonProperty("phone_number")
    private String phoneNumber;

    @JsonProperty("fb_id")
    private String fbId;

    @JsonProperty("children")
    private Long children;

    @JsonProperty("locale")
    private String locale;

    @JsonProperty("profile_image")
    private String profileImage;

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

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getPhonePrefix() {
        return phonePrefix;
    }

    public void setPhonePrefix(String phonePrefix) {
        this.phonePrefix = phonePrefix;
    }

    public String getPhoneNumber() {
        return phoneNumber;
    }

    public void setPhoneNumber(String phoneNumber) {
        this.phoneNumber = phoneNumber;
    }

    public String getFbId() {
        return fbId;
    }

    public void setFbId(String fbId) {
        this.fbId = fbId;
    }

    public Long getChildren() {
        return children;
    }

    public void setChildren(Long children) {
        this.children = children;
    }

    public String getLocale() {
        return locale;
    }

    public void setLocale(String locale) {
        this.locale = locale;
    }

    public String getProfileImage() {
        return profileImage;
    }

    public void setProfileImage(String profileImage) {
        this.profileImage = profileImage;
    }

    @Override
    public String toString() {
        return "ParentDTO{" +
                "identity='" + identity + '\'' +
                ", firstName='" + firstName + '\'' +
                ", lastName='" + lastName + '\'' +
                ", birthdate=" + birthdate +
                ", age=" + age +
                ", email='" + email + '\'' +
                ", phonePrefix='" + phonePrefix + '\'' +
                ", phoneNumber='" + phoneNumber + '\'' +
                ", fbId='" + fbId + '\'' +
                ", children=" + children +
                ", locale='" + locale + '\'' +
                ", profileImage='" + profileImage + '\'' +
                '}';
    }
}
