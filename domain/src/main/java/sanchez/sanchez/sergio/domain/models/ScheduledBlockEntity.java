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
     * Enable
     */
    private boolean enable;

    /**
     * Repeatable
     */
    private boolean repeatable;

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
     * @param enable
     * @param repeatable
     * @param startAt
     * @param endAt
     * @param weeklyFrequency
     * @param  image
     * @param childId
     * @param appsAllowed
     * @param geofence
     */
    public ScheduledBlockEntity(final String identity, final String name, final boolean enable, final boolean repeatable,
                                final LocalTime startAt, final LocalTime endAt, final int[] weeklyFrequency,
                                final String image, final String childId, final List<AppAllowedByScheduledEntity> appsAllowed,
                                final GeofenceEntity geofence) {
        this.identity = identity;
        this.name = name;
        this.enable = enable;
        this.repeatable = repeatable;
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
                ", enable=" + enable +
                ", repeatable=" + repeatable +
                ", startAt=" + startAt +
                ", endAt=" + endAt +
                ", weeklyFrequency=" + Arrays.toString(weeklyFrequency) +
                ", image='" + image + '\'' +
                '}';
    }
}
