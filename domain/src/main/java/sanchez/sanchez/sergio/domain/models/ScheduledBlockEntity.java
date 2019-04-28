package sanchez.sanchez.sergio.domain.models;

import java.io.Serializable;
import org.joda.time.LocalTime;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

/**
 * Scheduled Block Entity
 */
public final class ScheduledBlockEntity implements Serializable {

    /**
     * Identity
     */
    private String identity;

    /**
     * Name
     */
    private String name;

    /**
     * Description
     */
    private String description;

    /**
     * Enable
     */
    private boolean enable;

    /**
     * Repeatable
     */
    private boolean repeatable;

    /**
     * Allow Calls
     */
    private boolean allowCalls;

    /**
     * Start At
     */
    private LocalTime startAt;

    /**
     * End At
     */
    private LocalTime endAt;

    /**
     * Weekly Frequency
     */
    private int[] weeklyFrequency;

    /**
     * Image
     */
    private String image;

    /**
     * Child Id
     */
    private String childId;

    /**
     * Apps Allowed
     */

    private List<AppAllowedByScheduledEntity> appsAllowed = new ArrayList<>();

    /**
     * Geofence
     */
    private GeofenceEntity geofence;

    public ScheduledBlockEntity(){}

    /**
     * Scheduled Block Entity
     * @param identity
     * @param name
     * @param description
     * @param enable
     * @param repeatable
     * @param allowCalls
     * @param startAt
     * @param endAt
     * @param weeklyFrequency
     * @param  image
     * @param childId
     * @param appsAllowed
     * @param geofence
     */
    public ScheduledBlockEntity(final String identity, final String name,
                                final String description, final boolean enable, final boolean repeatable,
                                final boolean allowCalls, final LocalTime startAt, final LocalTime endAt, final int[] weeklyFrequency,
                                final String image, final String childId, final List<AppAllowedByScheduledEntity> appsAllowed,
                                final GeofenceEntity geofence) {
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
        this.childId = childId;
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

    public String getChildId() {
        return childId;
    }

    public void setChildId(String childId) {
        this.childId = childId;
    }

    public List<AppAllowedByScheduledEntity> getAppsAllowed() {
        return appsAllowed;
    }

    public void setAppsAllowed(List<AppAllowedByScheduledEntity> appsAllowed) {
        this.appsAllowed = appsAllowed;
    }

    public GeofenceEntity getGeofence() {
        return geofence;
    }

    public void setGeofence(GeofenceEntity geofence) {
        this.geofence = geofence;
    }

    @Override
    public String toString() {
        return "ScheduledBlockEntity{" +
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
                ", childId='" + childId + '\'' +
                ", appsAllowed=" + appsAllowed +
                ", geofence=" + geofence +
                '}';
    }
}
