package sanchez.sanchez.sergio.data.net.models.request;

import com.fasterxml.jackson.annotation.JsonProperty;

import java.io.Serializable;

/**
 * Save Scheduled Block DTO
 */
public final class SaveScheduledBlockDTO implements Serializable {

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
    private String startAt;

    /**
     * End at
     */
    @JsonProperty("end_at")
    private String endAt;

    /**
     * Weekly Frequency
     */
    @JsonProperty("weekly_frequency")
    private int[] weeklyFrequency;

    /**
     * Kid
     */
    @JsonProperty("kid")
    private String kid;

    /**
     * Description
     */
    @JsonProperty("description")
    private String description;

    /**
     * Allow Calls
     */
    @JsonProperty("allow_calls")
    private boolean allowCalls;

    public SaveScheduledBlockDTO(){}

    /**
     *
     * @param identity
     * @param name
     * @param enable
     * @param repeatable
     * @param startAt
     * @param endAt
     * @param weeklyFrequency
     * @param kid
     */
    public SaveScheduledBlockDTO(String identity, String name, boolean enable,
                                 boolean repeatable, String startAt, String endAt,
                                 int[] weeklyFrequency, String kid, String description,
                                boolean allowCalls) {
        this.identity = identity;
        this.name = name;
        this.enable = enable;
        this.repeatable = repeatable;
        this.startAt = startAt;
        this.endAt = endAt;
        this.weeklyFrequency = weeklyFrequency;
        this.kid = kid;
        this.description = description;
        this.allowCalls = allowCalls;
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

    public String getStartAt() {
        return startAt;
    }

    public void setStartAt(String startAt) {
        this.startAt = startAt;
    }

    public String getEndAt() {
        return endAt;
    }

    public void setEndAt(String endAt) {
        this.endAt = endAt;
    }

    public int[] getWeeklyFrequency() {
        return weeklyFrequency;
    }

    public void setWeeklyFrequency(int[] weeklyFrequency) {
        this.weeklyFrequency = weeklyFrequency;
    }

    public String getKid() {
        return kid;
    }

    public void setKid(String kid) {
        this.kid = kid;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public boolean isAllowCalls() {
        return allowCalls;
    }

    public void setAllowCalls(boolean allowCalls) {
        this.allowCalls = allowCalls;
    }
}
