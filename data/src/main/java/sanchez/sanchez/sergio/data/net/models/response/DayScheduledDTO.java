package sanchez.sanchez.sergio.data.net.models.response;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonProperty;
import java.io.Serializable;
import java.util.Date;

/**
 * Day Scheduled DTO
 */
@JsonIgnoreProperties(ignoreUnknown = true)
public final class DayScheduledDTO implements Serializable {

    /**
     * Day
     */
    @JsonProperty("day")
    private String day;

    /**
     * Enabled
     */
    @JsonProperty("enabled")
    private Boolean enabled = false;

    /**
     * Total Hours
     */
    @JsonProperty("total_hours")
    private Integer totalHours = 0;

    /**
     * Paused
     */
    @JsonProperty("paused")
    private Boolean paused = false;

    /**
     * Paused At
     */
    @JsonProperty("paused_at")
    private Date pausedAt;


    public DayScheduledDTO(){}

    /**
     *
     * @param day
     * @param enabled
     * @param totalHours
     * @param paused
     * @param pausedAt
     */
    public DayScheduledDTO(final String day, final Boolean enabled, final Integer totalHours,
                           final Boolean paused, final Date pausedAt) {
        this.day = day;
        this.enabled = enabled;
        this.totalHours = totalHours;
        this.paused = paused;
        this.pausedAt = pausedAt;
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

    @Override
    public String toString() {
        return "DayScheduledDTO{" +
                "day='" + day + '\'' +
                ", enabled=" + enabled +
                ", totalHours=" + totalHours +
                ", paused=" + paused +
                ", pausedAt=" + pausedAt +
                '}';
    }
}
