package sanchez.sanchez.sergio.data.net.models.response;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonProperty;
import java.io.Serializable;
import java.util.Date;

/**
 * Comment DTO
 */
@JsonIgnoreProperties(ignoreUnknown = true)
public final class CommentDTO implements Serializable {

    @JsonProperty("identity")
    private String identity;

    @JsonProperty("message")
    private String message;

    @JsonProperty("likes")
    private int likes;

    @JsonProperty("social_media")
    private String socialMedia;

    @JsonProperty("created_time")
    private Date createdTime;

    @JsonProperty("extracted_at")
    private Date extractedAt;

    @JsonProperty("extracted_at_since")
    private String extractedAtSince;

    @JsonProperty("author_name")
    private String authorName;

    @JsonProperty("author_photo")
    private String authorPhoto;

    @JsonProperty("adult")
    private String adult;

    @JsonProperty("bullying")
    private String bullying;

    @JsonProperty("drugs")
    private String drugs;

    @JsonProperty("sentiment")
    private String sentiment;

    @JsonProperty("violence")
    private String violence;

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

    public int getLikes() {
        return likes;
    }

    public void setLikes(int likes) {
        this.likes = likes;
    }

    public String getSocialMedia() {
        return socialMedia;
    }

    public void setSocialMedia(String socialMedia) {
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

    public String getAdult() {
        return adult;
    }

    public void setAdult(String adult) {
        this.adult = adult;
    }

    public String getBullying() {
        return bullying;
    }

    public void setBullying(String bullying) {
        this.bullying = bullying;
    }

    public String getDrugs() {
        return drugs;
    }

    public void setDrugs(String drugs) {
        this.drugs = drugs;
    }

    public String getSentiment() {
        return sentiment;
    }

    public void setSentiment(String sentiment) {
        this.sentiment = sentiment;
    }

    public String getViolence() {
        return violence;
    }

    public void setViolence(String violence) {
        this.violence = violence;
    }

    @Override
    public String toString() {
        return "CommentDTO{" +
                "identity='" + identity + '\'' +
                ", message='" + message + '\'' +
                ", likes='" + likes + '\'' +
                ", socialMedia='" + socialMedia + '\'' +
                ", createdTime=" + createdTime +
                ", extractedAt=" + extractedAt +
                ", extractedAtSince='" + extractedAtSince + '\'' +
                ", authorName='" + authorName + '\'' +
                ", authorPhoto='" + authorPhoto + '\'' +
                ", adult='" + adult + '\'' +
                ", bullying='" + bullying + '\'' +
                ", drugs='" + drugs + '\'' +
                ", sentiment='" + sentiment + '\'' +
                ", violence='" + violence + '\'' +
                '}';
    }
}
