package sanchez.sanchez.sergio.data.net.models.response;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonProperty;
import org.joda.time.LocalTime;
import java.io.Serializable;
import java.util.Arrays;

/**
 * Scheduled Block DTO
 */
@JsonIgnoreProperties(ignoreUnknown = true)
public final class ScheduledBlockDTO implements Serializable {

    /**
     * Identity
     */
    @JsonProperty("identity")
    private String identity;

    /**
     * Name
     */
    @JsonProperty("name")
    private String name;

    /**
     * Enable
     */
    @JsonProperty("enable")
    private boolean enable;

    /**
     * Repeatable
     */
    @JsonProperty("repeatable")
    private boolean repeatable;

    /**
     * Start At
     */
    @JsonProperty("start_at")
    private LocalTime startAt;

    /**
     * End At
     */
    @JsonProperty("end_at")
    private LocalTime endAt;

    /**
     * Weekly Frequency
     */
    @JsonProperty("weekly_frequency")
    private int[] weeklyFrequency;


    /**
     * Image
     */
    @JsonProperty("image")
    private String image;

    /**
     * Child
     */
    @JsonProperty("son")
    private String child;

    public ScheduledBlockDTO(){}

    /**
     *
     * @param identity
     * @param name
     * @param enable
     * @param repeatable
     * @param startAt
     * @param endAt
     * @param weeklyFrequency
     * @param image
     * @param child
     */
    public ScheduledBlockDTO(String identity, String name, boolean enable, boolean repeatable,
                             LocalTime startAt, LocalTime endAt, int[] weeklyFrequency, String image,
                             String child) {
        this.identity = identity;
        this.name = name;
        this.enable = enable;
        this.repeatable = repeatable;
        this.startAt = startAt;
        this.endAt = endAt;
        this.weeklyFrequency = weeklyFrequency;
        this.image = image;
        this.child = child;
    }

    public String getIdentity() {
        return identity;
    }

    public void setIdentity(String identity) {
        this.identity = identity;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public boolean isEnable() {
        return enable;
    }

    public void setEnable(boolean enable) {
        this.enable = enable;
    }

    public boolean isRepeatable() {
        return repeatable;
    }

    public void setRepeatable(boolean repeatable) {
        this.repeatable = repeatable;
    }

    public LocalTime getStartAt() {
        return startAt;
    }

    public void setStartAt(LocalTime startAt) {
        this.startAt = startAt;
    }

    public LocalTime getEndAt() {
        return endAt;
    }

    public void setEndAt(LocalTime endAt) {
        this.endAt = endAt;
    }

    public int[] getWeeklyFrequency() {
        return weeklyFrequency;
    }

    public void setWeeklyFrequency(int[] weeklyFrequency) {
        this.weeklyFrequency = weeklyFrequency;
    }

    public String getImage() {
        return image;
    }

    public void setImage(String image) {
        this.image = image;
    }

    public String getChild() {
        return child;
    }

    public void setChild(String child) {
        this.child = child;
    }

    @Override
    public String toString() {
        return "ScheduledBlockDTO{" +
                "identity='" + identity + '\'' +
                ", name='" + name + '\'' +
                ", enable=" + enable +
                ", repeatable=" + repeatable +
                ", startAt=" + startAt +
                ", endAt=" + endAt +
                ", weeklyFrequency=" + Arrays.toString(weeklyFrequency) +
                '}';
    }
}
