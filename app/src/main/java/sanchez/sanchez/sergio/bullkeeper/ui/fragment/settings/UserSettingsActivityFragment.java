package sanchez.sanchez.sergio.bullkeeper.ui.fragment.settings;

import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v4.app.DialogFragment;
import android.support.v7.preference.ListPreference;
import android.support.v7.preference.Preference;
import android.support.v7.preference.SwitchPreferenceCompat;
import android.view.View;

import butterknife.OnClick;
import sanchez.sanchez.sergio.bullkeeper.R;
import sanchez.sanchez.sergio.bullkeeper.di.HasComponent;
import sanchez.sanchez.sergio.bullkeeper.di.components.SettingsComponent;
import sanchez.sanchez.sergio.bullkeeper.ui.activity.settings.IUserSettingsActivityHandler;
import sanchez.sanchez.sergio.bullkeeper.ui.dialog.NoticeDialogFragment;
import sanchez.sanchez.sergio.bullkeeper.ui.support.SupportPreferenceFragment;
import sanchez.sanchez.sergio.domain.repository.IPreferenceRepository;

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
        final ListPreference numberOfAlertsListPreference = (ListPreference) findPreference(IPreferenceRepository.PREF_NUMBER_OF_ALERTS);
        numberOfAlertsListPreference.setOnPreferenceChangeListener(this);
        // Age Of Alerts
        final ListPreference ageOfAlertsPreference = (ListPreference) findPreference(IPreferenceRepository.PREF_AGE_OF_ALERTS);
        ageOfAlertsPreference.setOnPreferenceChangeListener(this);
        // Remove Alerts Every
        final ListPreference removeAlertsEvery = (ListPreference) findPreference(IPreferenceRepository.PREF_REMOVE_ALERTS_EVERY);
        removeAlertsEvery.setOnPreferenceChangeListener(this);
        // Enable Push Notification
        final SwitchPreferenceCompat enablePushNotification = (SwitchPreferenceCompat) findPreference(IPreferenceRepository.PREF_ENABLE_PUSH_NOTIFICATIONS);
        enablePushNotification.setOnPreferenceChangeListener(this);
        // Enable All Alerts Categories
        final SwitchPreferenceCompat enableAllAlertCategories = (SwitchPreferenceCompat) findPreference(IPreferenceRepository.PREF_ENABLE_ALL_ALERT_CATEGORIES);
        enableAllAlertCategories.setOnPreferenceChangeListener(this);
        // Enable Success Alerts
        final SwitchPreferenceCompat enableSuccessAlerts = (SwitchPreferenceCompat) findPreference(IPreferenceRepository.PREF_ENABLE_SUCCESS_ALERTS);
        enableSuccessAlerts.setOnPreferenceChangeListener(this);
        enableSuccessAlerts.setEnabled(!enableAllAlertCategories.isEnabled());
        // Enable Information Alerts
        final SwitchPreferenceCompat enableInformationAlerts = (SwitchPreferenceCompat) findPreference(IPreferenceRepository.PREF_ENABLE_INFORMATION_ALERTS);
        enableInformationAlerts.setOnPreferenceChangeListener(this);
        enableInformationAlerts.setEnabled(!enableAllAlertCategories.isEnabled());
        // Enable Warning Alerts
        final SwitchPreferenceCompat enableWarningAlerts = (SwitchPreferenceCompat) findPreference(IPreferenceRepository.PREF_ENABLE_WARNING_ALERTS);
        enableWarningAlerts.setOnPreferenceChangeListener(this);
        enableWarningAlerts.setEnabled(!enableAllAlertCategories.isEnabled());
        // Enable Danger Alerts
        final SwitchPreferenceCompat enableDangerAlerts = (SwitchPreferenceCompat) findPreference(IPreferenceRepository.PREF_ENABLE_DANGER_ALERTS);
        enableDangerAlerts.setOnPreferenceChangeListener(this);
        enableDangerAlerts.setEnabled(!enableAllAlertCategories.isEnabled());

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

            case IPreferenceRepository.PREF_ENABLE_ALL_ALERT_CATEGORIES:

                final boolean enableAllAlertsCategories = (boolean)newValue;

                final SwitchPreferenceCompat enableSuccessAlerts, enableInformationAlerts,
                        enableWarningAlerts, enableDangerAlerts;

                enableSuccessAlerts  = (SwitchPreferenceCompat) findPreference(IPreferenceRepository.PREF_ENABLE_SUCCESS_ALERTS);
                enableInformationAlerts = (SwitchPreferenceCompat) findPreference(IPreferenceRepository.PREF_ENABLE_INFORMATION_ALERTS);
                enableWarningAlerts = (SwitchPreferenceCompat) findPreference(IPreferenceRepository.PREF_ENABLE_WARNING_ALERTS);
                enableDangerAlerts = (SwitchPreferenceCompat) findPreference(IPreferenceRepository.PREF_ENABLE_DANGER_ALERTS);

                if(enableAllAlertsCategories) {

                    enableSuccessAlerts.setChecked(true);
                    enableSuccessAlerts.setEnabled(false);

                    enableInformationAlerts.setChecked(true);
                    enableInformationAlerts.setEnabled(false);

                    enableWarningAlerts.setChecked(true);
                    enableWarningAlerts.setEnabled(false);

                    enableDangerAlerts.setChecked(true);
                    enableDangerAlerts.setEnabled(false);

                } else {
                    // Enable all
                    enableSuccessAlerts.setEnabled(true);
                    enableInformationAlerts.setEnabled(true);
                    enableWarningAlerts.setEnabled(true);
                    enableDangerAlerts.setEnabled(true);
                }

                break;

        }

        return true;
    }

    @OnClick(R.id.savePreferences)
    protected void onSavePreferences(){

        final SwitchPreferenceCompat enableAllAlertCategories, enableSuccessAlerts, enableInformationAlerts,
                enableWarningAlerts, enableDangerAlerts;

        enableAllAlertCategories = (SwitchPreferenceCompat) findPreference(IPreferenceRepository.PREF_ENABLE_ALL_ALERT_CATEGORIES);
        enableSuccessAlerts  = (SwitchPreferenceCompat) findPreference(IPreferenceRepository.PREF_ENABLE_SUCCESS_ALERTS);
        enableInformationAlerts = (SwitchPreferenceCompat) findPreference(IPreferenceRepository.PREF_ENABLE_INFORMATION_ALERTS);
        enableWarningAlerts = (SwitchPreferenceCompat) findPreference(IPreferenceRepository.PREF_ENABLE_WARNING_ALERTS);
        enableDangerAlerts = (SwitchPreferenceCompat) findPreference(IPreferenceRepository.PREF_ENABLE_DANGER_ALERTS);

        preferencesRepositoryImpl.setEnableAllAlertCategories(enableAllAlertCategories.isChecked());
        preferencesRepositoryImpl.setSuccessAlertsEnabled(enableSuccessAlerts.isChecked());
        preferencesRepositoryImpl.setInformationAlertsEnabled(enableInformationAlerts.isChecked());
        preferencesRepositoryImpl.setWarningAlertsEnabled(enableWarningAlerts.isChecked());
        preferencesRepositoryImpl.setDangerAlertsEnabled(enableDangerAlerts.isChecked());

        // Save NUmber Of Alerts Preference
        final ListPreference numberOfAlertsListPreference = (ListPreference) findPreference(IPreferenceRepository.PREF_NUMBER_OF_ALERTS);
        preferencesRepositoryImpl.setNumberOfAlerts(numberOfAlertsListPreference.getValue());
        // Save Age of Alerts Preference
        final ListPreference ageOfAlertsPreference = (ListPreference) findPreference(IPreferenceRepository.PREF_AGE_OF_ALERTS);
        preferencesRepositoryImpl.setAgeOfAlerts(ageOfAlertsPreference.getValue());
        // Save Remove Alerts Every preference
        final ListPreference removeAlertsEvery = (ListPreference) findPreference(IPreferenceRepository.PREF_REMOVE_ALERTS_EVERY);
        preferencesRepositoryImpl.setRemoveAlertsEvery(removeAlertsEvery.getValue());

        // Save Enable Push Notification
        final SwitchPreferenceCompat enablePushNotification = (SwitchPreferenceCompat) findPreference(IPreferenceRepository.PREF_ENABLE_PUSH_NOTIFICATIONS);
        preferencesRepositoryImpl.setEnablePushNotifications(enablePushNotification.isChecked());

        activityHandler.showNoticeDialog(R.string.preferences_saved_successfully, new NoticeDialogFragment.NoticeDialogListener() {
            @Override
            public void onAccepted(DialogFragment dialog) {
                activityHandler.closeActivity();
            }
        });
    }

    @Override
    public Boolean hasPendingChanges() {
        return false;
    }

    @Override
    public void onSavedPendingChanges() {

    }

    @Override
    public void onDiscardPendingChanges() {

    }
}
