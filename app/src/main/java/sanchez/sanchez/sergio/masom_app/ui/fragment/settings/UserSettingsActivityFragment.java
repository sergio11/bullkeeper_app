package sanchez.sanchez.sergio.masom_app.ui.fragment.settings;

import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v14.preference.SwitchPreference;
import android.support.v7.preference.ListPreference;
import android.support.v7.preference.Preference;
import android.support.v7.preference.SwitchPreferenceCompat;
import android.view.View;
import sanchez.sanchez.sergio.masom_app.R;
import sanchez.sanchez.sergio.masom_app.di.HasComponent;
import sanchez.sanchez.sergio.masom_app.di.components.SettingsComponent;
import sanchez.sanchez.sergio.masom_app.ui.activity.settings.IUserSettingsActivityHandler;
import sanchez.sanchez.sergio.masom_app.ui.support.SupportPreferenceFragment;
import sanchez.sanchez.sergio.masom_app.utils.PreferencesManager;
import timber.log.Timber;

/**
 * User Settings Activity Fragment
 */
public class UserSettingsActivityFragment extends
        SupportPreferenceFragment<IUserSettingsActivityHandler> {

    /**
     * Settings Component
     */
    protected SettingsComponent settingsComponent;

    /**
     * Get Preferences Layout
     * @return
     */
    @Override
    protected int getPreferencesLayout() {
        return R.xml.app_preferences;
    }

    /**
     * Initialize Injector
     */
    @Override
    protected void initializeInjector() {
        settingsComponent = SettingsComponent.class
                .cast(((HasComponent<SettingsComponent>) getActivity())
                        .getComponent());
        settingsComponent.inject(this);
    }

    /**
     * On View Created
     * @param view
     * @param savedInstanceState
     */
    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);

        // Number of alerts
        final ListPreference numberOfAlertsListPreference = (ListPreference) findPreference(PreferencesManager.PREF_NUMBER_OF_ALERTS);
        numberOfAlertsListPreference.setOnPreferenceChangeListener(this);
        // Age Of Alerts
        final ListPreference ageOfAlertsPreference = (ListPreference) findPreference(PreferencesManager.PREF_AGE_OF_ALERTS);
        ageOfAlertsPreference.setOnPreferenceChangeListener(this);
        // Remove Alerts Every
        final ListPreference removeAlertsEvery = (ListPreference) findPreference(PreferencesManager.PREF_REMOVE_ALERTS_EVERY);
        removeAlertsEvery.setOnPreferenceChangeListener(this);
        // Enable Push Notification
        final SwitchPreferenceCompat enablePushNotification = (SwitchPreferenceCompat) findPreference(PreferencesManager.PREF_ENABLE_PUSH_NOTIFICATIONS);
        enablePushNotification.setOnPreferenceChangeListener(this);

    }


    /**
     * On Preference Change
     * @param preference
     * @param newValue
     * @return
     */
    @Override
    public boolean onPreferenceChange(Preference preference, Object newValue) {

        switch (preference.getKey()) {

            case PreferencesManager.PREF_NUMBER_OF_ALERTS:
                final String numberOfAlerts = String.valueOf(newValue);
                Timber.d("Number Of Alerts -> %s", numberOfAlerts);
                preferencesManager.setNumberOfAlerts(numberOfAlerts);
                break;

            case PreferencesManager.PREF_AGE_OF_ALERTS:
                final String ageOfAlerts = String.valueOf(newValue);
                Timber.d("Age of Alerts -> %s", ageOfAlerts);
                preferencesManager.setAgeOfAlerts(ageOfAlerts);
                break;

            case PreferencesManager.PREF_REMOVE_ALERTS_EVERY:
                final String removeAlertsEvery = String.valueOf(newValue);
                Timber.d("Remove Alerts Every -> %s", removeAlertsEvery);
                preferencesManager.setRemoveAlertsEvery(removeAlertsEvery);
                break;

            case PreferencesManager.PREF_ENABLE_PUSH_NOTIFICATIONS:
                final boolean enablePushNotification = (boolean)newValue;
                preferencesManager.setEnablePushNotifications(enablePushNotification);
                break;

        }

        return true;
    }
}
