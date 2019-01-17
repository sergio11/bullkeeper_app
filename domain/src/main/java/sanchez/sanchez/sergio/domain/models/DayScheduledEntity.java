package sanchez.sanchez.sergio.domain.models;

import java.io.Serializable;

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
     * @param editable
     */
    public DayScheduledEntity(final String day, final Boolean enabled, final Integer totalHours,
                              boolean editable) {
        this.day = day;
        this.enabled = enabled;
        this.totalHours = totalHours;
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
                ", editable=" + editable +
                '}';
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        DayScheduledEntity that = (DayScheduledEntity) o;

        return getDay().equals(that.getDay());
    }

    @Override
    public int hashCode() {
        return getDay().hashCode();
    }
}
