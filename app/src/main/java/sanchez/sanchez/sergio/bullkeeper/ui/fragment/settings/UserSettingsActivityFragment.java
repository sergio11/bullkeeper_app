package sanchez.sanchez.sergio.bullkeeper.ui.fragment.settings;

import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v4.app.DialogFragment;
import android.support.v7.preference.ListPreference;
import android.support.v7.preference.Preference;
import android.support.v7.preference.SwitchPreferenceCompat;
import android.view.View;
import java.util.Date;
import butterknife.OnClick;
import sanchez.sanchez.sergio.bullkeeper.R;
import sanchez.sanchez.sergio.bullkeeper.di.HasComponent;
import sanchez.sanchez.sergio.bullkeeper.di.components.SettingsComponent;
import sanchez.sanchez.sergio.bullkeeper.ui.activity.settings.IUserSettingsActivityHandler;
import sanchez.sanchez.sergio.bullkeeper.ui.dialog.NoticeDialogFragment;
import sanchez.sanchez.sergio.bullkeeper.ui.support.SupportPreferenceFragment;
import sanchez.sanchez.sergio.domain.repository.IPreferenceRepository;
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

    private String numberOfAlerts;
    private String ageOfAlerts;
    private String removeAlertsEvery;
    private boolean enableAllAlertCategories;
    private boolean enableSuccessAlerts;
    private boolean enableInformationAlerts;
    private boolean enableWarningAlerts;
    private boolean enableDangerAlerts;

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
        // Get Current Value
        numberOfAlerts = numberOfAlertsListPreference.getValue();

        // Age Of Alerts
        final ListPreference ageOfAlertsPreference = (ListPreference) findPreference(IPreferenceRepository.PREF_AGE_OF_ALERTS);
        ageOfAlertsPreference.setOnPreferenceChangeListener(this);
        // Get current value
        ageOfAlerts = ageOfAlertsPreference.getValue();

        // Remove Alerts Every
        final ListPreference removeAlertsEveryPreference = (ListPreference) findPreference(IPreferenceRepository.PREF_REMOVE_ALERTS_EVERY);
        removeAlertsEveryPreference.setOnPreferenceChangeListener(this);

        removeAlertsEvery = removeAlertsEveryPreference.getValue();

        // Enable Push Notification
        final SwitchPreferenceCompat enablePushNotification = (SwitchPreferenceCompat) findPreference(IPreferenceRepository.PREF_ENABLE_PUSH_NOTIFICATIONS);
        enablePushNotification.setOnPreferenceChangeListener(this);

        // Enable All Alerts Categories
        final SwitchPreferenceCompat enableAllAlertCategoriesPreferences = (SwitchPreferenceCompat) findPreference(IPreferenceRepository.PREF_ENABLE_ALL_ALERT_CATEGORIES);
        enableAllAlertCategoriesPreferences.setOnPreferenceChangeListener(this);
        // Get Current Value
        enableAllAlertCategories = enableAllAlertCategoriesPreferences.isChecked();

        // Enable Success Alerts
        final SwitchPreferenceCompat enableSuccessAlertsPreferences = (SwitchPreferenceCompat) findPreference(IPreferenceRepository.PREF_ENABLE_SUCCESS_ALERTS);
        enableSuccessAlertsPreferences.setOnPreferenceChangeListener(this);
        enableSuccessAlertsPreferences.setEnabled(!enableAllAlertCategoriesPreferences.isChecked());
        // Get current value
        enableSuccessAlerts = enableSuccessAlertsPreferences.isChecked();

        // Enable Information Alerts
        final SwitchPreferenceCompat enableInformationAlertsPreferences = (SwitchPreferenceCompat) findPreference(IPreferenceRepository.PREF_ENABLE_INFORMATION_ALERTS);
        enableInformationAlertsPreferences.setOnPreferenceChangeListener(this);
        enableInformationAlertsPreferences.setEnabled(!enableAllAlertCategoriesPreferences.isChecked());
        // Get Current Value
        enableInformationAlerts = enableInformationAlertsPreferences.isChecked();
        // Enable Warning Alerts
        final SwitchPreferenceCompat enableWarningAlertsPreferences = (SwitchPreferenceCompat) findPreference(IPreferenceRepository.PREF_ENABLE_WARNING_ALERTS);
        enableWarningAlertsPreferences.setOnPreferenceChangeListener(this);
        enableWarningAlertsPreferences.setEnabled(!enableAllAlertCategoriesPreferences.isChecked());
        // Get Current Value
        enableWarningAlerts = enableWarningAlertsPreferences.isChecked();
        // Enable Danger Alerts
        final SwitchPreferenceCompat enableDangerAlertsPreferences = (SwitchPreferenceCompat) findPreference(IPreferenceRepository.PREF_ENABLE_DANGER_ALERTS);
        enableDangerAlertsPreferences.setOnPreferenceChangeListener(this);
        enableDangerAlertsPreferences.setEnabled(!enableAllAlertCategoriesPreferences.isChecked());

        enableDangerAlerts = enableDangerAlertsPreferences.isChecked();

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

        preferencesRepositoryImpl.setPreferencesUpdateAt(new Date().getTime());

        activityHandler.showNoticeDialog(R.string.preferences_saved_successfully, new NoticeDialogFragment.NoticeDialogListener() {
            @Override
            public void onAccepted(DialogFragment dialog) {
                activityHandler.closeActivity();
            }
        });
    }

    /**
     * Has Pending Changes
     * @return
     */
    @Override
    public Boolean hasPendingChanges() {

        // Check Number Of Alerts
        final ListPreference numberOfAlertsListPreference = (ListPreference) findPreference(IPreferenceRepository.PREF_NUMBER_OF_ALERTS);
        if(!numberOfAlertsListPreference.getValue().equals(numberOfAlerts)) return true;

        // Pref Age of alerts
        final ListPreference ageOfAlertsPreference = (ListPreference) findPreference(IPreferenceRepository.PREF_AGE_OF_ALERTS);
        if(!ageOfAlertsPreference.getValue().equals(ageOfAlerts)) return true;

        // Pref Remove Alerts Every
        final ListPreference removeAlertsEveryPreference = (ListPreference) findPreference(IPreferenceRepository.PREF_REMOVE_ALERTS_EVERY);
        if(!removeAlertsEveryPreference.getValue().equals(removeAlertsEvery)) return true;

        final SwitchPreferenceCompat enableAllAlertCategoriesPreferences =
                (SwitchPreferenceCompat) findPreference(IPreferenceRepository.PREF_ENABLE_ALL_ALERT_CATEGORIES);

        if(enableAllAlertCategories != enableAllAlertCategoriesPreferences.isChecked()) return true;

        final SwitchPreferenceCompat enableSuccessAlertsPreferences =
                (SwitchPreferenceCompat) findPreference(IPreferenceRepository.PREF_ENABLE_SUCCESS_ALERTS);

        if(enableSuccessAlerts != enableSuccessAlertsPreferences.isChecked()) return true;

        final SwitchPreferenceCompat enableInformationAlertsPreferences =
                (SwitchPreferenceCompat) findPreference(IPreferenceRepository.PREF_ENABLE_INFORMATION_ALERTS);

        if(enableInformationAlerts != enableInformationAlertsPreferences.isChecked()) return true;

        final SwitchPreferenceCompat enableWarningAlertsPreferences =
                (SwitchPreferenceCompat) findPreference(IPreferenceRepository.PREF_ENABLE_WARNING_ALERTS);

        if(enableWarningAlerts != enableWarningAlertsPreferences.isChecked()) return true;

        final SwitchPreferenceCompat enableDangerAlertsPreferences =
                (SwitchPreferenceCompat) findPreference(IPreferenceRepository.PREF_ENABLE_DANGER_ALERTS);

        if(enableDangerAlerts != enableDangerAlertsPreferences.isChecked()) return true;

        return false;
    }

    /**
     * On Saved Pending Changes
     */
    @Override
    public void onSavedPendingChanges() {
        Timber.d("On Saved Pending Changes");
        preferencesRepositoryImpl.setPreferencesUpdateAt(new Date().getTime());
    }

    /**
     * On Discard Pending Changes
     */
    @Override
    public void onDiscardPendingChanges() {
        Timber.d("On Discard Pending Changes");

        preferencesRepositoryImpl.setNumberOfAlerts(numberOfAlerts);
        preferencesRepositoryImpl.setAgeOfAlerts(ageOfAlerts);
        preferencesRepositoryImpl.setRemoveAlertsEvery(removeAlertsEvery);
        preferencesRepositoryImpl.setEnableAllAlertCategories(enableAllAlertCategories);
        preferencesRepositoryImpl.setSuccessAlertsEnabled(enableSuccessAlerts);
        preferencesRepositoryImpl.setInformationAlertsEnabled(enableInformationAlerts);
        preferencesRepositoryImpl.setDangerAlertsEnabled(enableDangerAlerts);
        preferencesRepositoryImpl.setWarningAlertsEnabled(enableWarningAlerts);
    }
}
