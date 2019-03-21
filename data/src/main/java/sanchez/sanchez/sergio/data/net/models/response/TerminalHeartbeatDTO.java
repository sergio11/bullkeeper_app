package sanchez.sanchez.sergio.data.net.models.response;

import com.fasterxml.jackson.annotation.JsonProperty;
import java.io.Serializable;
import java.util.Date;

/**
 * Terminal Heartbeat DTO
 */
public final class TerminalHeartbeatDTO implements Serializable {

    /**
     * Alert Threshold
     */
    @JsonProperty("alert_threshold")
    private int alertThresholdInMinutes;

    /**
     * Alert Mode Enabled
     */
    @JsonProperty("alert_mode_enabled")
    private boolean alertModeEnabled;

    /**
     * Last Time Notified Since
     */
    @JsonProperty("last_time_notified_since")
    private String lastTimeNotifiedSince;

    /**
     * Last Time Notified
     */
    @JsonProperty("last_time_notified")
    private Date lastTimeNotified;

    public TerminalHeartbeatDTO(){}

    /**
     *
     * @param alertThresholdInMinutes
     * @param alertModeEnabled
     * @param lastTimeNotifiedSince
     * @param lastTimeNotified
     */
    public TerminalHeartbeatDTO(int alertThresholdInMinutes, boolean alertModeEnabled,
                                String lastTimeNotifiedSince, Date lastTimeNotified) {
        this.alertThresholdInMinutes = alertThresholdInMinutes;
        this.alertModeEnabled = alertModeEnabled;
        this.lastTimeNotifiedSince = lastTimeNotifiedSince;
        this.lastTimeNotified = lastTimeNotified;
    }

    public int getAlertThresholdInMinutes() {
        return alertThresholdInMinutes;
    }

    public void setAlertThresholdInMinutes(int alertThresholdInMinutes) {
        this.alertThresholdInMinutes = alertThresholdInMinutes;
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

    @Override
    public String toString() {
        return "TerminalHeartbeatDTO{" +
                "alertThresholdInMinutes=" + alertThresholdInMinutes +
                ", alertModeEnabled=" + alertModeEnabled +
                ", lastTimeNotifiedSince='" + lastTimeNotifiedSince + '\'' +
                ", lastTimeNotified=" + lastTimeNotified +
                '}';
    }
}
