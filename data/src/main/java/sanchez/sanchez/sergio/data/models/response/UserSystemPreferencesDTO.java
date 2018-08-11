package sanchez.sanchez.sergio.data.models.response;

import com.fasterxml.jackson.annotation.JsonProperty;

import java.io.Serializable;

/**
 * User System Preference DTO
 */
public final class UserSystemPreferencesDTO implements Serializable {

    @JsonProperty("push_notifications_enabled")
    private Boolean pushNotificationsEnabled;

    @JsonProperty("remove_alerts_every")
    private String removeAlertsEvery;

    public Boolean getPushNotificationsEnabled() {
        return pushNotificationsEnabled;
    }

    public void setPushNotificationsEnabled(Boolean pushNotificationsEnabled) {
        this.pushNotificationsEnabled = pushNotificationsEnabled;
    }

    public String getRemoveAlertsEvery() {
        return removeAlertsEvery;
    }

    public void setRemoveAlertsEvery(String removeAlertsEvery) {
        this.removeAlertsEvery = removeAlertsEvery;
    }

    @Override
    public String toString() {
        return "UserSystemPreferencesDTO{" +
                "pushNotificationsEnabled=" + pushNotificationsEnabled +
                ", removeAlertsEvery='" + removeAlertsEvery + '\'' +
                '}';
    }
}
