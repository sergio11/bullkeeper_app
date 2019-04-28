package sanchez.sanchez.sergio.data.net.models.response;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonProperty;
import org.joda.time.LocalTime;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

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
     * Description
     */
    @JsonProperty("description")
    private String description;

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
     * Allow Calls
     */
    @JsonProperty("allow_calls")
    private boolean allowCalls;

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
     * Kid
     */
    @JsonProperty("kid")
    private String kid;

    /**
     * Apps Allowed
     */
    @JsonProperty("apps_allowed")
    private List<AppAllowedByScheduledDTO> appsAllowed = new ArrayList<>();

    /**
     * Geofence
     */
    @JsonProperty("geofence")
    private GeofenceDTO geofence;

    public ScheduledBlockDTO(){}

    /**
     *
     * @param identity
     * @param name
     * @param description
     * @param enable
     * @param repeatable
     * @param allowCalls
     * @param startAt
     * @param endAt
     * @param weeklyFrequency
     * @param image
     * @param kid
     * @param appsAllowed
     * @param geofence
     */
    public ScheduledBlockDTO(String identity, String name, final String description,
                             boolean enable, boolean repeatable, boolean allowCalls, LocalTime startAt,
                             LocalTime endAt, int[] weeklyFrequency, String image,
                             String kid, List<AppAllowedByScheduledDTO> appsAllowed, final GeofenceDTO geofence) {
        this.identity = identity;
        this.name = name;
        this.description = description;
        this.enable = enable;
        this.repeatable = repeatable;
        this.allowCalls = allowCalls;
        this.startAt = startAt;
        this.endAt = endAt;
        this.weeklyFrequency = weeklyFrequency;
        this.image = image;
        this.kid = kid;
        this.appsAllowed = appsAllowed;
        this.geofence = geofence;
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

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
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

    public boolean isAllowCalls() {
        return allowCalls;
    }

    public void setAllowCalls(boolean allowCalls) {
        this.allowCalls = allowCalls;
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

    public String getKid() {
        return kid;
    }

    public void setKid(String kid) {
        this.kid = kid;
    }

    public List<AppAllowedByScheduledDTO> getAppsAllowed() {
        return appsAllowed;
    }

    public void setAppsAllowed(List<AppAllowedByScheduledDTO> appsAllowed) {
        this.appsAllowed = appsAllowed;
    }

    public GeofenceDTO getGeofence() {
        return geofence;
    }

    public void setGeofence(GeofenceDTO geofence) {
        this.geofence = geofence;
    }

    @Override
    public String toString() {
        return "ScheduledBlockDTO{" +
                "identity='" + identity + '\'' +
                ", name='" + name + '\'' +
                ", description='" + description + '\'' +
                ", enable=" + enable +
                ", repeatable=" + repeatable +
                ", allowCalls=" + allowCalls +
                ", startAt=" + startAt +
                ", endAt=" + endAt +
                ", weeklyFrequency=" + Arrays.toString(weeklyFrequency) +
                ", image='" + image + '\'' +
                ", kid='" + kid + '\'' +
                ", appsAllowed=" + appsAllowed +
                ", geofence=" + geofence +
                '}';
    }
}
