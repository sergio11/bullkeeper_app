package sanchez.sanchez.sergio.domain.models;

import java.io.Serializable;
import java.util.Date;

/**
 * Alert Entity
 */
public final class AlertEntity implements Serializable {

    private String identity;
    private AlertLevelEnum level = AlertLevelEnum.INFO;
    private String title;
    private String payload;
    private Date createAt;
    private String since;
    private KidEntity son;
    private AlertCategoryEnum category;

    public AlertEntity(){}

    public AlertEntity(AlertLevelEnum level) {
        this.level = level;
    }

    public String getIdentity() {
        return identity;
    }

    public void setIdentity(String identity) {
        this.identity = identity;
    }

    public AlertLevelEnum getLevel() {
        return level;
    }

    public void setLevel(AlertLevelEnum level) {
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

    public KidEntity getSon() {
        return son;
    }

    public void setSon(KidEntity son) {
        this.son = son;
    }

    public AlertCategoryEnum getCategory() {
        return category;
    }

    public void setCategory(AlertCategoryEnum category) {
        this.category = category;
    }

    @Override
    public String toString() {
        return "AlertEntity{" +
                "identity='" + identity + '\'' +
                ", level=" + level +
                ", title='" + title + '\'' +
                ", payload='" + payload + '\'' +
                ", createAt=" + createAt +
                ", since='" + since + '\'' +
                ", son=" + son +
                ", category=" + category +
                '}';
    }
}
