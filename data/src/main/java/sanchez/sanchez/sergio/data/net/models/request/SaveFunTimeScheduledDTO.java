package sanchez.sanchez.sergio.data.net.models.request;

import com.fasterxml.jackson.annotation.JsonProperty;
import java.io.Serializable;

/**
 * Save Fun Time Scheduled DTO
 */
public final class SaveFunTimeScheduledDTO implements Serializable {

    /**
     * Enabled
     */
    @JsonProperty("enabled")
    private Boolean enabled = false;

    /**
     * Monday
     */
    @JsonProperty("monday")
    private SaveDayScheduledDTO monday;

    /**
     * Tuesday
     */
    @JsonProperty("tuesday")
    private SaveDayScheduledDTO tuesday;

    /**
     * Wednesday
     */
    @JsonProperty("wednesday")
    private SaveDayScheduledDTO wednesday;

    /**
     * Thursday
     */
    @JsonProperty("thursday")
    private SaveDayScheduledDTO thursday;

    /**
     * Friday
     */
    @JsonProperty("friday")
    private SaveDayScheduledDTO friday;

    /**
     * Saturday
     */
    @JsonProperty("saturday")
    private SaveDayScheduledDTO saturday;

    /**
     * Sunday
     */
    @JsonProperty("sunday")
    private SaveDayScheduledDTO sunday;


    public SaveFunTimeScheduledDTO(){}

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
    public SaveFunTimeScheduledDTO(final Boolean enabled, final SaveDayScheduledDTO monday,
                                   final SaveDayScheduledDTO tuesday, final SaveDayScheduledDTO wednesday,
                                   final SaveDayScheduledDTO thursday, final SaveDayScheduledDTO friday,
                                   final SaveDayScheduledDTO saturday, final SaveDayScheduledDTO sunday) {
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

    public SaveDayScheduledDTO getMonday() {
        return monday;
    }

    public void setMonday(SaveDayScheduledDTO monday) {
        this.monday = monday;
    }

    public SaveDayScheduledDTO getTuesday() {
        return tuesday;
    }

    public void setTuesday(SaveDayScheduledDTO tuesday) {
        this.tuesday = tuesday;
    }

    public SaveDayScheduledDTO getWednesday() {
        return wednesday;
    }

    public void setWednesday(SaveDayScheduledDTO wednesday) {
        this.wednesday = wednesday;
    }

    public SaveDayScheduledDTO getThursday() {
        return thursday;
    }

    public void setThursday(SaveDayScheduledDTO thursday) {
        this.thursday = thursday;
    }

    public SaveDayScheduledDTO getFriday() {
        return friday;
    }

    public void setFriday(SaveDayScheduledDTO friday) {
        this.friday = friday;
    }

    public SaveDayScheduledDTO getSaturday() {
        return saturday;
    }

    public void setSaturday(SaveDayScheduledDTO saturday) {
        this.saturday = saturday;
    }

    public SaveDayScheduledDTO getSunday() {
        return sunday;
    }

    public void setSunday(SaveDayScheduledDTO sunday) {
        this.sunday = sunday;
    }

    @Override
    public String toString() {
        return "SaveFunTimeScheduledDTO{" +
                "enabled=" + enabled +
                ", monday=" + monday +
                ", tuesday=" + tuesday +
                ", wednesday=" + wednesday +
                ", thursday=" + thursday +
                ", friday=" + friday +
                ", saturday=" + saturday +
                ", sunday=" + sunday +
                '}';
    }
}
