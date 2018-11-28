package sanchez.sanchez.sergio.data.net.models.response;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonProperty;
import java.io.Serializable;
import java.util.Date;

/**
 * Alert Dto
 */
@JsonIgnoreProperties(ignoreUnknown = true)
public final class AlertDTO implements Serializable {

    /**
     * Identity
     */
    @JsonProperty("identity")
    private String identity;

    /**
     * Level
     */
    @JsonProperty("level")
    private String level;

    /**
     * Title
     */
    @JsonProperty("title")
    private String title;

    /**
     * Payload
     */
    @JsonProperty("payload")
    private String payload;

    /**
     * Create At
     */
    @JsonProperty("create_at")
    private Date createAt;

    /**
     * Since
     */
    @JsonProperty("since")
    private String since;

    /**
     * Kid
     */
    @JsonProperty("kid")
    private KidDTO kid;

    /**
     * Category
     */
    @JsonProperty("category")
    private String category;


    public String getIdentity() {
        return identity;
    }

    public void setIdentity(String identity) {
        this.identity = identity;
    }

    public String getLevel() {
        return level;
    }

    public void setLevel(String level) {
        this.level = level;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getPayload() {
        return payload;
    }

    public void setPayload(String payload) {
        this.payload = payload;
    }

    public Date getCreateAt() {
        return createAt;
    }

    public void setCreateAt(Date createAt) {
        this.createAt = createAt;
    }

    public String getSince() {
        return since;
    }

    public void setSince(String since) {
        this.since = since;
    }

    public KidDTO getKid() {
        return kid;
    }

    public void setKid(KidDTO kid) {
        this.kid = kid;
    }

    public String getCategory() {
        return category;
    }

    public void setCategory(String category) {
        this.category = category;
    }

    @Override
    public String toString() {
        return "AlertDTO{" +
                "identity='" + identity + '\'' +
                ", level='" + level + '\'' +
                ", title='" + title + '\'' +
                ", payload='" + payload + '\'' +
                ", createAt=" + createAt +
                ", since='" + since + '\'' +
                ", kid=" + kid +
                ", category='" + category + '\'' +
                '}';
    }
}
