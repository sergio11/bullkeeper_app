package sanchez.sanchez.sergio.data.net.models.request;

import com.fasterxml.jackson.annotation.JsonProperty;

import java.io.Serializable;

/**
 * Save Day Scheduled DTO
 */
public final class SaveDayScheduledDTO implements Serializable {

    /**
     * Day
     */
    @JsonProperty("day")
    private String day;

    /**
     * Enabled
     */
    @JsonProperty("enabled")
    private Boolean enabled;

    /**
     * Total Hours
     */
    @JsonProperty("total_hours")
    private Integer totalHours;

    public SaveDayScheduledDTO(){}

    /**
     *
     * @param day
     * @param enabled
     * @param totalHours
     */
    public SaveDayScheduledDTO(final String day, final Boolean enabled, final Integer totalHours) {
        this.day = day;
        this.enabled = enabled;
        this.totalHours = totalHours;
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

    @Override
    public String toString() {
        return "SaveDayScheduledDTO{" +
                "day='" + day + '\'' +
                ", enabled=" + enabled +
                ", totalHours=" + totalHours +
                '}';
    }
}
