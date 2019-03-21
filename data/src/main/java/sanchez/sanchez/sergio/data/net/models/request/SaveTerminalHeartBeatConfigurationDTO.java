package sanchez.sanchez.sergio.data.net.models.request;

import com.fasterxml.jackson.annotation.JsonProperty;
import java.io.Serializable;

/**
 * Save Terminal Heart Beat Configuration DTO
 */
public final class SaveTerminalHeartBeatConfigurationDTO implements Serializable {

    @JsonProperty("kid")
    private String kid;
    @JsonProperty("terminal")
    private String terminal;
    @JsonProperty("alert_threshold_in_minutes")
    private int alertThresholdInMinutes;
    @JsonProperty("is_alert_mode_enabled")
    private boolean isAlertModeEnabled;


    /**
     *
     * @param kid
     * @param terminal
     * @param alertThresholdInMinutes
     * @param isAlertModeEnabled
     */
    public SaveTerminalHeartBeatConfigurationDTO(String kid, String terminal, int alertThresholdInMinutes, boolean isAlertModeEnabled) {
        this.kid = kid;
        this.terminal = terminal;
        this.alertThresholdInMinutes = alertThresholdInMinutes;
        this.isAlertModeEnabled = isAlertModeEnabled;
    }

    public String getKid() {
        return kid;
    }

    public void setKid(String kid) {
        this.kid = kid;
    }

    public String getTerminal() {
        return terminal;
    }

    public void setTerminal(String terminal) {
        this.terminal = terminal;
    }

    public int getAlertThresholdInMinutes() {
        return alertThresholdInMinutes;
    }

    public void setAlertThresholdInMinutes(int alertThresholdInMinutes) {
        this.alertThresholdInMinutes = alertThresholdInMinutes;
    }

    public boolean isAlertModeEnabled() {
        return isAlertModeEnabled;
    }

    public void setAlertModeEnabled(boolean alertModeEnabled) {
        isAlertModeEnabled = alertModeEnabled;
    }

    @Override
    public String toString() {
        return "SaveTerminalHeartBeatConfigurationDTO{" +
                "kid='" + kid + '\'' +
                ", terminal='" + terminal + '\'' +
                ", alertThresholdInMinutes=" + alertThresholdInMinutes +
                ", isAlertModeEnabled=" + isAlertModeEnabled +
                '}';
    }
}
