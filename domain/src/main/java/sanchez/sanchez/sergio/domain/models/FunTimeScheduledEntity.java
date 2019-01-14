package sanchez.sanchez.sergio.domain.models;

import java.io.Serializable;

/**
 * Fun Time Scheduled Entity
 */
public final class FunTimeScheduledEntity implements Serializable {

    /**
     * Enabled
     */
    private Boolean enabled = false;

    /**
     * Monday
     */
    private DayScheduledEntity monday;

    /**
     * Tuesday
     */
    private DayScheduledEntity tuesday;

    /**
     * Wednesday
     */
    private DayScheduledEntity wednesday;

    /**
     * Thursday
     */
    private DayScheduledEntity thursday;

    /**
     * Friday
     */
    private DayScheduledEntity friday;

    /**
     * Saturday
     */
    private DayScheduledEntity saturday;

    /**
     * Sunday
     */
    private DayScheduledEntity sunday;

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
    public FunTimeScheduledEntity(
            final Boolean enabled, final DayScheduledEntity monday,
            final DayScheduledEntity tuesday, final DayScheduledEntity wednesday,
            final DayScheduledEntity thursday, final DayScheduledEntity friday,
            final DayScheduledEntity saturday, final DayScheduledEntity sunday) {
        this.enabled = enabled;
        this.monday = monday;
        this.tuesday = tuesday;
        this.wednesday = wednesday;
        this.thursday = thursday;
        this.friday = friday;
        this.saturday = saturday;
        this.sunday = sunday;
    }

    public FunTimeScheduledEntity(){}

    public Boolean getEnabled() {
        return enabled;
    }

    public void setEnabled(Boolean enabled) {
        this.enabled = enabled;
    }

    public DayScheduledEntity getMonday() {
        return monday;
    }

    public void setMonday(DayScheduledEntity monday) {
        this.monday = monday;
    }

    public DayScheduledEntity getTuesday() {
        return tuesday;
    }

    public void setTuesday(DayScheduledEntity tuesday) {
        this.tuesday = tuesday;
    }

    public DayScheduledEntity getWednesday() {
        return wednesday;
    }

    public void setWednesday(DayScheduledEntity wednesday) {
        this.wednesday = wednesday;
    }

    public DayScheduledEntity getThursday() {
        return thursday;
    }

    public void setThursday(DayScheduledEntity thursday) {
        this.thursday = thursday;
    }

    public DayScheduledEntity getFriday() {
        return friday;
    }

    public void setFriday(DayScheduledEntity friday) {
        this.friday = friday;
    }

    public DayScheduledEntity getSaturday() {
        return saturday;
    }

    public void setSaturday(DayScheduledEntity saturday) {
        this.saturday = saturday;
    }

    public DayScheduledEntity getSunday() {
        return sunday;
    }

    public void setSunday(DayScheduledEntity sunday) {
        this.sunday = sunday;
    }

    @Override
    public String toString() {
        return "FunTimeScheduledEntity{" +
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
