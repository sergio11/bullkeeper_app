package sanchez.sanchez.sergio.domain.models;

import java.io.Serializable;
import java.util.Date;

/**
 * Guardian Entity
 */
public final class GuardianEntity implements Serializable {

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
     * Birth Date
     */
    private Date birthdate;

    /**
     * Age
     */
    private Integer age;

    /**
     * Email
     */
    private String email;

    /**
     * Phone Prefix
     */
    private String phonePrefix;

    /**
     * Phone Number
     */
    private String phoneNumber;

    /**
     * FB id
     */
    private String fbId;

    /**
     * Children
     */
    private Long children;
    private String locale;
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

    public String getPhone() {
        return phonePrefix != null && !phonePrefix.isEmpty() &&
                phoneNumber != null && !phoneNumber.isEmpty() ?
                         phonePrefix + "" + phoneNumber : null;
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

    public String getFullName() {
        return firstName + " " + lastName;
    }


}
