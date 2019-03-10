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
     * Kid
     */
    @JsonProperty("kid")
    private KidDTO kid;

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
     * @param kid
     * @param totalDevices
     * @param location
     * @param socialMedias
     * @param totalCommentsAnalyzed
     * @param totalViolentComments
     * @param totalCommentsAdultContent
     * @param totalCommentsDrugs
     * @param totalCommentsBullying
     * @param totalCommentsNegativeSentiment
     * @param totalCommentsPositiveSentiment
     * @param totalCommentsNeutralSentiment
     */
    public SummaryMyKidResultDTO(KidDTO kid, int totalDevices, LocationDTO location, List<SocialMediaDTO> socialMedias,
                                 int totalCommentsAnalyzed, int totalViolentComments, int totalCommentsAdultContent,
                                 int totalCommentsDrugs, int totalCommentsBullying, int totalCommentsNegativeSentiment,
                                 int totalCommentsPositiveSentiment, int totalCommentsNeutralSentiment) {
        this.kid = kid;
        this.totalDevices = totalDevices;
        this.location = location;
        this.socialMedias = socialMedias;
        this.totalCommentsAnalyzed = totalCommentsAnalyzed;
        this.totalViolentComments = totalViolentComments;
        this.totalCommentsAdultContent = totalCommentsAdultContent;
        this.totalCommentsDrugs = totalCommentsDrugs;
        this.totalCommentsBullying = totalCommentsBullying;
        this.totalCommentsNegativeSentiment = totalCommentsNegativeSentiment;
        this.totalCommentsPositiveSentiment = totalCommentsPositiveSentiment;
        this.totalCommentsNeutralSentiment = totalCommentsNeutralSentiment;
    }

    public KidDTO getKid() {
        return kid;
    }

    public void setKid(KidDTO kid) {
        this.kid = kid;
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
        return "SummaryMyKidResultDTO{" +
                "kid=" + kid +
                ", totalDevices=" + totalDevices +
                ", location=" + location +
                ", socialMedias=" + socialMedias +
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
