package sanchez.sanchez.sergio.domain.models;

import java.io.Serializable;
import java.util.Date;

/**
 * Terminal Heartbeat Entity
 */
public final class TerminalHeartbeatEntity implements Serializable {

    /**
     * Alert Threshold
     */
    private int alertThreshold;

    /**
     * Alert Mode Enabled
     */
    private boolean alertModeEnabled;

    /**
     * last Time Notified Since
     */
    private String lastTimeNotifiedSince;

    /**
     * Last Time Notified
     */
    private Date lastTimeNotified;

    public TerminalHeartbeatEntity(){}

    /**
     *
     * @param alertThreshold
     * @param alertModeEnabled
     * @param lastTimeNotifiedSince
     * @param lastTimeNotified
     */
    public TerminalHeartbeatEntity(int alertThreshold, boolean alertModeEnabled,
                                   final String lastTimeNotifiedSince, final Date lastTimeNotified) {
        this.alertThreshold = alertThreshold;
        this.alertModeEnabled = alertModeEnabled;
        this.lastTimeNotifiedSince = lastTimeNotifiedSince;
        this.lastTimeNotified = lastTimeNotified;
    }

    public int getAlertThreshold() {
        return alertThreshold;
    }

    public void setAlertThreshold(int alertThreshold) {
        this.alertThreshold = alertThreshold;
    }

    public boolean isAlertModeEnabled() {
        return alertModeEnabled;
    }

    public void setAlertModeEnabled(boolean alertModeEnabled) {
        this.alertModeEnabled = alertModeEnabled;
    }

    public String getLastTimeNotifiedSince() {
        return lastTimeNotifiedSince;
    }

    public void setLastTimeNotifiedSince(String lastTimeNotifiedSince) {
        this.lastTimeNotifiedSince = lastTimeNotifiedSince;
    }

    public Date getLastTimeNotified() {
        return lastTimeNotified;
    }

    public void setLastTimeNotified(Date lastTimeNotified) {
        this.lastTimeNotified = lastTimeNotified;
    }

    /**
     * Has Exceeded The Threshold
     * @return
     */
    public boolean hasExceededThreshold(){
        long diffInMillies = Math.abs(new Date().getTime() - lastTimeNotified.getTime());
        return diffInMillies > 0 && diffInMillies/1000/60 > alertThreshold;
    }

    @Override
    public String toString() {
        return "TerminalHeartbeatEntity{" +
                "alertThreshold=" + alertThreshold +
                ", alertModeEnabled=" + alertModeEnabled +
                ", lastTimeNotifiedSince='" + lastTimeNotifiedSince + '\'' +
                ", lastTimeNotified='" + lastTimeNotified + '\'' +
                '}';
    }
}
