package sanchez.sanchez.sergio.data.net.models.request;

import com.fasterxml.jackson.annotation.JsonProperty;

import java.io.Serializable;

import sanchez.sanchez.sergio.domain.models.RemoveAlertsEveryEnum;

/**
 * Save User Preference DTO
 **/
public final class SaveUserPreferenceDTO implements Serializable {


    @JsonProperty("push_notifications_enabled")
    private Boolean pushNotificationsEnabled;

    @JsonProperty("remove_alerts_every")
    private RemoveAlertsEveryEnum removeAlertsEvery;

    public SaveUserPreferenceDTO(){}

    public SaveUserPreferenceDTO(Boolean pushNotificationsEnabled, RemoveAlertsEveryEnum removeAlertsEvery) {
        this.pushNotificationsEnabled = pushNotificationsEnabled;
        this.removeAlertsEvery = removeAlertsEvery;
    }

    public Boolean getPushNotificationsEnabled() {
        return pushNotificationsEnabled;
    }

    public void setPushNotificationsEnabled(Boolean pushNotificationsEnabled) {
        this.pushNotificationsEnabled = pushNotificationsEnabled;
    }

    public RemoveAlertsEveryEnum getRemoveAlertsEvery() {
        return removeAlertsEvery;
    }

    public void setRemoveAlertsEvery(RemoveAlertsEveryEnum removeAlertsEvery) {
        this.removeAlertsEvery = removeAlertsEvery;
    }

    @Override
    public String toString() {
        return "SaveUserPreferenceDTO{" +
                "pushNotificationsEnabled=" + pushNotificationsEnabled +
                ", removeAlertsEvery='" + removeAlertsEvery + '\'' +
                '}';
    }
}
