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

    @JsonProperty("identity")
    private String identity;

    @JsonProperty("level")
    private String level;

    @JsonProperty("title")
    private String title;

    @JsonProperty("payload")
    private String payload;

    @JsonProperty("create_at")
    private Date createAt;

    @JsonProperty("since")
    private String since;

    @JsonProperty("son")
    private SonDTO son;

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

    public SonDTO getSon() {
        return son;
    }

    public void setSon(SonDTO son) {
        this.son = son;
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
                ", son=" + son +
                ", category='" + category + '\'' +
                '}';
    }
}
