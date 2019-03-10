package sanchez.sanchez.sergio.domain.models;

import java.io.Serializable;
import java.util.List;

/**
 * Summary My Kid Result Entity
 */
public final class SummaryMyKidResultEntity implements Serializable {

    /**
     * Kid Entity
     */
    private KidEntity kidEntity;

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
     * @param kidEntity
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
    public SummaryMyKidResultEntity(KidEntity kidEntity, int totalDevices, LocationEntity location, List<SocialMediaEntity> socialMediaEntityList,
                                    int totalCommentsAnalyzed, int totalViolentComments, int totalCommentsAdultContent, int totalCommentsDrugs, int totalCommentsBullying, int totalCommentsNegativeSentiment, int totalCommentsPositiveSentiment, int totalCommentsNeutralSentiment) {
        this.kidEntity = kidEntity;
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

    public KidEntity getKidEntity() {
        return kidEntity;
    }

    public void setKidEntity(KidEntity kidEntity) {
        this.kidEntity = kidEntity;
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
}
