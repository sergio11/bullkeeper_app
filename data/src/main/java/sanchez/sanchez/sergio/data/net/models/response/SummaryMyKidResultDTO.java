package sanchez.sanchez.sergio.data.net.models.response;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonProperty;
import java.io.Serializable;
import java.util.List;

/**
 * Summary My Kid Result DTO
 */
@JsonIgnoreProperties(ignoreUnknown = true)
public final class SummaryMyKidResultDTO implements Serializable {

    /**
     * Identity
     */
    @JsonProperty("identity")
    private String identity;

    /**
     * Fist Name
     */
    @JsonProperty("first_name")
    private String firstName;

    /**
     * Last Name
     */
    @JsonProperty("last_name")
    private String lastName;

    /**
     * Birthdate
     */
    @JsonProperty("birthdate")
    private String birthdate;

    /**
     * Age
     */
    @JsonProperty("age")
    private Integer age;

    /**
     * School
     */
    @JsonProperty("school")
    private SchoolDTO school;

    /**
     * Profile Image
     */
    @JsonProperty("profile_image")
    private String profileImage;

    /**
     * Total Devices
     */
    @JsonProperty("total_devices")
    private int totalDevices;

    /**
     * Location
     */
    @JsonProperty("location")
    private LocationDTO location;

    /**
     * Social Meddias
     */
    @JsonProperty("social_medias")
    private List<SocialMediaDTO> socialMedias;

    /**
     * Total Comments
     */
    @JsonProperty("total_comments")
    private int totalComments;

    /**
     * Total Comments Analyzed
     */
    @JsonProperty("total_comments_analyzed")
    private int totalCommentsAnalyzed;

    /**
     * Total Violent Comments
     */
    @JsonProperty("total_violent_comments")
    private int totalViolentComments;

    /**
     * Total Comments Adult Content
     */
    @JsonProperty("total_comments_adult_content")
    private int totalCommentsAdultContent;

    /**
     * Total Comments Drugs
     */
    @JsonProperty("total_comments_drugs")
    private int totalCommentsDrugs;

    /**
     * Total Comments Bullying
     */
    @JsonProperty("total_comments_bullying")
    private int totalCommentsBullying;

    /**
     * Total Comments Negative Sentiment
     */
    @JsonProperty("total_comments_negative_sentiment")
    private int totalCommentsNegativeSentiment;

    /**
     * Total Comments Positive Sentiment
     */
    @JsonProperty("total_comments_positive_sentiment")
    private int totalCommentsPositiveSentiment;

    /**
     * Total Comments Neutral Sentiment
     */
    @JsonProperty("total_comments_neutral_sentiment")
    private int totalCommentsNeutralSentiment;

    public SummaryMyKidResultDTO(){}

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
     * @param socialMedias
     * @param totalComments
     * @param totalCommentsAnalyzed
     * @param totalViolentComments
     * @param totalCommentsAdultContent
     * @param totalCommentsDrugs
     * @param totalCommentsBullying
     * @param totalCommentsNegativeSentiment
     * @param totalCommentsPositiveSentiment
     * @param totalCommentsNeutralSentiment
     */
    public SummaryMyKidResultDTO(String identity, String firstName, String lastName, String birthdate, Integer age, SchoolDTO school,
                                 String profileImage, int totalDevices, LocationDTO location, List<SocialMediaDTO> socialMedias, int totalComments,
                                 int totalCommentsAnalyzed, int totalViolentComments, int totalCommentsAdultContent, int totalCommentsDrugs,
                                 int totalCommentsBullying, int totalCommentsNegativeSentiment, int totalCommentsPositiveSentiment,
                                 int totalCommentsNeutralSentiment) {
        this.identity = identity;
        this.firstName = firstName;
        this.lastName = lastName;
        this.birthdate = birthdate;
        this.age = age;
        this.school = school;
        this.profileImage = profileImage;
        this.totalDevices = totalDevices;
        this.location = location;
        this.socialMedias = socialMedias;
        this.totalComments = totalComments;
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

    public SchoolDTO getSchool() {
        return school;
    }

    public void setSchool(SchoolDTO school) {
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

    public LocationDTO getLocation() {
        return location;
    }

    public void setLocation(LocationDTO location) {
        this.location = location;
    }

    public List<SocialMediaDTO> getSocialMedias() {
        return socialMedias;
    }

    public void setSocialMedias(List<SocialMediaDTO> socialMedias) {
        this.socialMedias = socialMedias;
    }

    public int getTotalComments() {
        return totalComments;
    }

    public void setTotalComments(int totalComments) {
        this.totalComments = totalComments;
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
}
