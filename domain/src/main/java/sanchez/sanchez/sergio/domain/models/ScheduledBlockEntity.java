package sanchez.sanchez.sergio.domain.models;

import java.io.Serializable;
import org.joda.time.LocalTime;
import java.util.Arrays;

/**
 * Scheduled Block Entity
 */
public final class ScheduledBlockEntity implements Serializable {

    private String identity;
    private String name;
    private boolean enable;
    private boolean repeatable;
    private LocalTime startAt;
    private LocalTime endAt;
    private int[] weeklyFrequency;
    private String image;

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
     */
    public ScheduledBlockEntity(final String identity, final String name, final boolean enable, final boolean repeatable,
                                final LocalTime startAt, final LocalTime endAt, final int[] weeklyFrequency,
                                final String image) {
        this.identity = identity;
        this.name = name;
        this.enable = enable;
        this.repeatable = repeatable;
        this.startAt = startAt;
        this.endAt = endAt;
        this.weeklyFrequency = weeklyFrequency;
        this.image = image;
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
