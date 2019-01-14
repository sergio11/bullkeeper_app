package sanchez.sanchez.sergio.data.net.models.response;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonProperty;

import java.io.Serializable;

/**
 * Fun Time Scheduled DTO
 */
@JsonIgnoreProperties(ignoreUnknown = true)
public final class FunTimeScheduledDTO implements Serializable {

    /**
     * Enabled
     */
    @JsonProperty("enabled")
    private Boolean enabled = false;

    /**
     * Monday
     */
    @JsonProperty("monday")
    private DayScheduledDTO monday;

    /**
     * Tuesday
     */
    @JsonProperty("tuesday")
    private DayScheduledDTO tuesday;

    /**
     * Wednesday
     */
    @JsonProperty("wednesday")
    private DayScheduledDTO wednesday;

    /**
     * Thursday
     */
    @JsonProperty("thursday")
    private DayScheduledDTO thursday;

    /**
     * Friday
     */
    @JsonProperty("friday")
    private DayScheduledDTO friday;

    /**
     * Saturday
     */
    @JsonProperty("saturday")
    private DayScheduledDTO saturday;

    /**
     * Sunday
     */
    @JsonProperty("sunday")
    private DayScheduledDTO sunday;

    /**
     *
     */
    public FunTimeScheduledDTO(){}

    /**
     *
     * @param enabled
     * @param monday
     * @param tuesday
     * @param wednesday
     * @param thursday
     * @param friday
     * @param saturday
     * @param sunday
     */
    public FunTimeScheduledDTO(final Boolean enabled, final DayScheduledDTO monday, final DayScheduledDTO tuesday,
                               final DayScheduledDTO wednesday, final DayScheduledDTO thursday, final DayScheduledDTO friday,
                               final DayScheduledDTO saturday, final DayScheduledDTO sunday) {
        this.enabled = enabled;
        this.monday = monday;
        this.tuesday = tuesday;
        this.wednesday = wednesday;
        this.thursday = thursday;
        this.friday = friday;
        this.saturday = saturday;
        this.sunday = sunday;
    }

    public Boolean getEnabled() {
        return enabled;
    }

    public void setEnabled(Boolean enabled) {
        this.enabled = enabled;
    }

    public DayScheduledDTO getMonday() {
        return monday;
    }

    public void setMonday(DayScheduledDTO monday) {
        this.monday = monday;
    }

    public DayScheduledDTO getTuesday() {
        return tuesday;
    }

    public void setTuesday(DayScheduledDTO tuesday) {
        this.tuesday = tuesday;
    }

    public DayScheduledDTO getWednesday() {
        return wednesday;
    }

    public void setWednesday(DayScheduledDTO wednesday) {
        this.wednesday = wednesday;
    }

    public DayScheduledDTO getThursday() {
        return thursday;
    }

    public void setThursday(DayScheduledDTO thursday) {
        this.thursday = thursday;
    }

    public DayScheduledDTO getFriday() {
        return friday;
    }

    public void setFriday(DayScheduledDTO friday) {
        this.friday = friday;
    }

    public DayScheduledDTO getSaturday() {
        return saturday;
    }

    public void setSaturday(DayScheduledDTO saturday) {
        this.saturday = saturday;
    }

    public DayScheduledDTO getSunday() {
        return sunday;
    }

    public void setSunday(DayScheduledDTO sunday) {
        this.sunday = sunday;
    }
}
