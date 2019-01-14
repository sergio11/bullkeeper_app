package sanchez.sanchez.sergio.domain.models;

import java.io.Serializable;
import java.util.Date;

/**
 * Day Scheduled Entity
 */
public final class DayScheduledEntity implements Serializable {

    /**
     * Day
     */
    private String day;

    /**
     * Enabled
     */
    private Boolean enabled = false;

    /**
     * Total Hours
     */
    private Integer totalHours = 0;

    /**
     * Paused
     */
    private Boolean paused = false;

    /**
     * Paused At
     */
    private Date pausedAt;

    /**
     * Editable
     */
    private boolean editable = false;

    /**
     *
     */
    public DayScheduledEntity(){}

    /**
     *
     * @param day
     * @param enabled
     * @param totalHours
     * @param paused
     * @param pausedAt
     * @param editable
     */
    public DayScheduledEntity(final String day, final Boolean enabled, final Integer totalHours,
                              final Boolean paused, final Date pausedAt, boolean editable) {
        this.day = day;
        this.enabled = enabled;
        this.totalHours = totalHours;
        this.paused = paused;
        this.pausedAt = pausedAt;
        this.editable = editable;
    }

    public String getDay() {
        return day;
    }

    public void setDay(String day) {
        this.day = day;
    }

    public Boolean getEnabled() {
        return enabled;
    }

    public void setEnabled(Boolean enabled) {
        this.enabled = enabled;
    }

    public Integer getTotalHours() {
        return totalHours;
    }

    public void setTotalHours(Integer totalHours) {
        this.totalHours = totalHours;
    }

    public Boolean getPaused() {
        return paused;
    }

    public void setPaused(Boolean paused) {
        this.paused = paused;
    }

    public Date getPausedAt() {
        return pausedAt;
    }

    public void setPausedAt(Date pausedAt) {
        this.pausedAt = pausedAt;
    }

    public boolean isEditable() {
        return editable;
    }

    public void setEditable(boolean editable) {
        this.editable = editable;
    }

    @Override
    public String toString() {
        return "DayScheduledEntity{" +
                "day='" + day + '\'' +
                ", enabled=" + enabled +
                ", totalHours=" + totalHours +
                ", paused=" + paused +
                ", pausedAt=" + pausedAt +
                ", editable=" + editable +
                '}';
    }
}
