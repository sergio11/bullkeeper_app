package sanchez.sanchez.sergio.data.net.models.response;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonProperty;
import java.io.Serializable;

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


    public DayScheduledDTO(){}

    /**
     * @param day
     * @param enabled
     * @param totalHours
     */
    public DayScheduledDTO(final String day, final Boolean enabled, final Integer totalHours) {
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
        return "DayScheduledDTO{" +
                "day='" + day + '\'' +
                ", enabled=" + enabled +
                ", totalHours=" + totalHours +
                '}';
    }
}
