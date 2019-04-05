package sanchez.sanchez.sergio.domain.models;

import java.io.Serializable;

/**
 * User Preference
 **/
public final class UserPreferenceEntity implements Serializable {

    private Boolean pushNotificationsEnabled;
    private RemoveAlertsEveryEnum removeAlertsEvery;

    public UserPreferenceEntity(){}

    /**
     *
     * @param pushNotificationsEnabled
     * @param removeAlertsEvery
     */
    public UserPreferenceEntity(final Boolean pushNotificationsEnabled, final RemoveAlertsEveryEnum removeAlertsEvery) {
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
        return "UserPreferenceEntity{" +
                "pushNotificationsEnabled=" + pushNotificationsEnabled +
                ", removeAlertsEvery='" + removeAlertsEvery + '\'' +
                '}';
    }
}
