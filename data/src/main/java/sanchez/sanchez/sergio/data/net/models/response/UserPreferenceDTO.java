package sanchez.sanchez.sergio.data.net.models.response;

import com.fasterxml.jackson.annotation.JsonProperty;

import java.io.Serializable;

/**
 * User Preference DTO
 **/
public final class UserPreferenceDTO implements Serializable {

    @JsonProperty("push_notifications_enabled")
    private Boolean pushNotificationsEnabled;

    @JsonProperty("remove_alerts_every")
    private String removeAlertsEvery;

    public UserPreferenceDTO(){}

    public UserPreferenceDTO(Boolean pushNotificationsEnabled, String removeAlertsEvery) {
        this.pushNotificationsEnabled = pushNotificationsEnabled;
        this.removeAlertsEvery = removeAlertsEvery;
    }

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
        return "UserPreferenceDTO{" +
                "pushNotificationsEnabled=" + pushNotificationsEnabled +
                ", removeAlertsEvery='" + removeAlertsEvery + '\'' +
                '}';
    }
}
