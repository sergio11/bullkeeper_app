package sanchez.sanchez.sergio.bullkeeper.ui.activity.settings;

import sanchez.sanchez.sergio.bullkeeper.core.ui.IBasicActivityHandler;

/**
 * User Settings Activity Handler
 */
public interface IUserSettingsActivityHandler extends IBasicActivityHandler {


    /**
     * Save Preferences
     * @param pushNotificationsEnabled
     * @param removeAlertsEvery
     */
    void savePreferences(final Boolean pushNotificationsEnabled, final String removeAlertsEvery);


}
