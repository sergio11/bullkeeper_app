package sanchez.sanchez.sergio.data.net.models.request;

import com.fasterxml.jackson.annotation.JsonProperty;
import java.io.Serializable;

/**
 * Save User System Preference DTO
 */
public final class SaveUserSystemPreferencesDTO implements Serializable {

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
        return "SaveUserSystemPreferencesDTO{" +
                "pushNotificationsEnabled=" + pushNotificationsEnabled +
                ", removeAlertsEvery='" + removeAlertsEvery + '\'' +
                '}';
    }
}
