package sanchez.sanchez.sergio.domain.models;

import java.io.Serializable;
import java.util.Date;

/**
 * Comment Entity
 */
public class CommentEntity implements Serializable {

    /**
     * Identity
     */
    private String identity;

    /**
     * Message
     */
    private String message;

    /**
     * Likes
     */
    private Integer likes;

    /**
     * Social Media
     */
    private SocialMediaEnum socialMedia;

    /**
     * Created Time
     */
    private Date createdTime;

    /**
     * Extracted At
     */
    private Date extractedAt;

    /**
     * Extracted At Since
     */
    private String extractedAtSince;

    /**
     * Author Name
     */
    private String authorName;

    /**
     * Author Photo
     */
    private String authorPhoto;

    /**
     * Sentiment Level Enum
     */
    private SentimentLevelEnum sentimentLevelEnum;

    /**
     * Violence Level Enum
     */
    private ViolenceLevelEnum violenceLevelEnum;

    /**
     * Drugs Level Enum
     */
    private DrugsLevelEnum drugsLevelEnum;

    /**
     * Bullying Level Enum
     */
    private BullyingLevelEnum bullyingLevelEnum;

    /**
     * Adult Level Enum
     */
    private AdultLevelEnum adultLevelEnum;


    public CommentEntity(){}

    /**
     *
     * @param socialMedia
     */
    public CommentEntity(final SocialMediaEnum socialMedia) {
        this.socialMedia = socialMedia;
    }


    public String getIdentity() {
        return identity;
    }

    public void setIdentity(String identity) {
        this.identity = identity;
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }

    public Integer getLikes() {
        return likes;
    }

    public void setLikes(Integer likes) {
        this.likes = likes;
    }


    public SocialMediaEnum getSocialMedia() {
        return socialMedia;
    }

    public void setSocialMedia(SocialMediaEnum socialMedia) {
        this.socialMedia = socialMedia;
    }

    public Date getCreatedTime() {
        return createdTime;
    }

    public void setCreatedTime(Date createdTime) {
        this.createdTime = createdTime;
    }

    public Date getExtractedAt() {
        return extractedAt;
    }

    public void setExtractedAt(Date extractedAt) {
        this.extractedAt = extractedAt;
    }

    public String getExtractedAtSince() {
        return extractedAtSince;
    }

    public void setExtractedAtSince(String extractedAtSince) {
        this.extractedAtSince = extractedAtSince;
    }

    public String getAuthorName() {
        return authorName;
    }

    public void setAuthorName(String authorName) {
        this.authorName = authorName;
    }

    public String getAuthorPhoto() {
        return authorPhoto;
    }

    public void setAuthorPhoto(String authorPhoto) {
        this.authorPhoto = authorPhoto;
    }

    public SentimentLevelEnum getSentimentLevelEnum() {
        return sentimentLevelEnum;
    }

    public void setSentimentLevelEnum(SentimentLevelEnum sentimentLevelEnum) {
        this.sentimentLevelEnum = sentimentLevelEnum;
    }

    public ViolenceLevelEnum getViolenceLevelEnum() {
        return violenceLevelEnum;
    }

    public void setViolenceLevelEnum(ViolenceLevelEnum violenceLevelEnum) {
        this.violenceLevelEnum = violenceLevelEnum;
    }

    public DrugsLevelEnum getDrugsLevelEnum() {
        return drugsLevelEnum;
    }

    public void setDrugsLevelEnum(DrugsLevelEnum drugsLevelEnum) {
        this.drugsLevelEnum = drugsLevelEnum;
    }

    public BullyingLevelEnum getBullyingLevelEnum() {
        return bullyingLevelEnum;
    }

    public void setBullyingLevelEnum(BullyingLevelEnum bullyingLevelEnum) {
        this.bullyingLevelEnum = bullyingLevelEnum;
    }

    public AdultLevelEnum getAdultLevelEnum() {
        return adultLevelEnum;
    }

    public void setAdultLevelEnum(AdultLevelEnum adultLevelEnum) {
        this.adultLevelEnum = adultLevelEnum;
    }
}
