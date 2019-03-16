package sanchez.sanchez.sergio.domain.models;

import java.io.Serializable;
import java.util.List;

/**
 * Summary My Kid Result Entity
 */
public final class SummaryMyKidResultEntity implements Serializable {

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
     * Birthdate
     */
    private String birthdate;

    /**
     * Age
     */
    private Integer age;

    /**
     * School
     */
    private SchoolEntity school;

    /**
     * Profile Image
     */
    private String profileImage;

    /**
     * Total Devices
     */
    private int totalDevices;

    /**
     * Location
     */
    private LocationEntity location;

    /**
     * Social Media Entity List
     */
    private List<SocialMediaEntity> socialMediaEntityList;

    /**
     * Total Comments Analyzed
     */
    private int totalCommentsAnalyzed;

    /**
     * Total Violent Comments
     */
    private int totalViolentComments;

    /**
     * Total Comments Adult Content
     */
    private int totalCommentsAdultContent;

    /**
     * Total Comments Drugs
     */
    private int totalCommentsDrugs;

    /**
     * Total Comments Bullying
     */
    private int totalCommentsBullying;

    /**
     * Total Comments Negative Sentiment
     */
    private int totalCommentsNegativeSentiment;

    /**
     * Total Comments Positive Sentiment
     */
    private int totalCommentsPositiveSentiment;

    /**
     * Total Comments Neutral Sentiment
     */
    private int totalCommentsNeutralSentiment;


    public SummaryMyKidResultEntity(){}

    /**
     *
     * @param identity
     * @param firstName
     * @param lastName
     * @param birthdate
     * @param age
     * @param school
     * @param profileImage
     * @param totalDevices
     * @param location
     * @param socialMediaEntityList
     * @param totalCommentsAnalyzed
     * @param totalViolentComments
     * @param totalCommentsAdultContent
     * @param totalCommentsDrugs
     * @param totalCommentsBullying
     * @param totalCommentsNegativeSentiment
     * @param totalCommentsPositiveSentiment
     * @param totalCommentsNeutralSentiment
     */
    public SummaryMyKidResultEntity(String identity, String firstName, String lastName, String birthdate, Integer age, SchoolEntity school, String profileImage, int totalDevices, LocationEntity location, List<SocialMediaEntity> socialMediaEntityList, int totalCommentsAnalyzed, int totalViolentComments, int totalCommentsAdultContent, int totalCommentsDrugs, int totalCommentsBullying, int totalCommentsNegativeSentiment, int totalCommentsPositiveSentiment, int totalCommentsNeutralSentiment) {
        this.identity = identity;
        this.firstName = firstName;
        this.lastName = lastName;
        this.birthdate = birthdate;
        this.age = age;
        this.school = school;
        this.profileImage = profileImage;
        this.totalDevices = totalDevices;
        this.location = location;
        this.socialMediaEntityList = socialMediaEntityList;
        this.totalCommentsAnalyzed = totalCommentsAnalyzed;
        this.totalViolentComments = totalViolentComments;
        this.totalCommentsAdultContent = totalCommentsAdultContent;
        this.totalCommentsDrugs = totalCommentsDrugs;
        this.totalCommentsBullying = totalCommentsBullying;
        this.totalCommentsNegativeSentiment = totalCommentsNegativeSentiment;
        this.totalCommentsPositiveSentiment = totalCommentsPositiveSentiment;
        this.totalCommentsNeutralSentiment = totalCommentsNeutralSentiment;
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

    public Integer getAge() {
        return age;
    }

    public void setAge(Integer age) {
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

    public int getTotalDevices() {
        return totalDevices;
    }

    public void setTotalDevices(int totalDevices) {
        this.totalDevices = totalDevices;
    }

    public LocationEntity getLocation() {
        return location;
    }

    public void setLocation(LocationEntity location) {
        this.location = location;
    }

    public List<SocialMediaEntity> getSocialMediaEntityList() {
        return socialMediaEntityList;
    }

    public void setSocialMediaEntityList(List<SocialMediaEntity> socialMediaEntityList) {
        this.socialMediaEntityList = socialMediaEntityList;
    }

    public int getTotalCommentsAnalyzed() {
        return totalCommentsAnalyzed;
    }

    public void setTotalCommentsAnalyzed(int totalCommentsAnalyzed) {
        this.totalCommentsAnalyzed = totalCommentsAnalyzed;
    }

    public int getTotalViolentComments() {
        return totalViolentComments;
    }

    public void setTotalViolentComments(int totalViolentComments) {
        this.totalViolentComments = totalViolentComments;
    }

    public int getTotalCommentsAdultContent() {
        return totalCommentsAdultContent;
    }

    public void setTotalCommentsAdultContent(int totalCommentsAdultContent) {
        this.totalCommentsAdultContent = totalCommentsAdultContent;
    }

    public int getTotalCommentsDrugs() {
        return totalCommentsDrugs;
    }

    public void setTotalCommentsDrugs(int totalCommentsDrugs) {
        this.totalCommentsDrugs = totalCommentsDrugs;
    }

    public int getTotalCommentsBullying() {
        return totalCommentsBullying;
    }

    public void setTotalCommentsBullying(int totalCommentsBullying) {
        this.totalCommentsBullying = totalCommentsBullying;
    }

    public int getTotalCommentsNegativeSentiment() {
        return totalCommentsNegativeSentiment;
    }

    public void setTotalCommentsNegativeSentiment(int totalCommentsNegativeSentiment) {
        this.totalCommentsNegativeSentiment = totalCommentsNegativeSentiment;
    }

    public int getTotalCommentsPositiveSentiment() {
        return totalCommentsPositiveSentiment;
    }

    public void setTotalCommentsPositiveSentiment(int totalCommentsPositiveSentiment) {
        this.totalCommentsPositiveSentiment = totalCommentsPositiveSentiment;
    }

    public int getTotalCommentsNeutralSentiment() {
        return totalCommentsNeutralSentiment;
    }

    public void setTotalCommentsNeutralSentiment(int totalCommentsNeutralSentiment) {
        this.totalCommentsNeutralSentiment = totalCommentsNeutralSentiment;
    }

    @Override
    public String toString() {
        return "SummaryMyKidResultEntity{" +
                "identity='" + identity + '\'' +
                ", firstName='" + firstName + '\'' +
                ", lastName='" + lastName + '\'' +
                ", birthdate='" + birthdate + '\'' +
                ", age=" + age +
                ", school=" + school +
                ", profileImage='" + profileImage + '\'' +
                ", totalDevices=" + totalDevices +
                ", location=" + location +
                ", socialMediaEntityList=" + socialMediaEntityList +
                ", totalCommentsAnalyzed=" + totalCommentsAnalyzed +
                ", totalViolentComments=" + totalViolentComments +
                ", totalCommentsAdultContent=" + totalCommentsAdultContent +
                ", totalCommentsDrugs=" + totalCommentsDrugs +
                ", totalCommentsBullying=" + totalCommentsBullying +
                ", totalCommentsNegativeSentiment=" + totalCommentsNegativeSentiment +
                ", totalCommentsPositiveSentiment=" + totalCommentsPositiveSentiment +
                ", totalCommentsNeutralSentiment=" + totalCommentsNeutralSentiment +
                '}';
    }
}
