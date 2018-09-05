package sanchez.sanchez.sergio.bullkeeper.ui.fragment.alertslist;

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
import sanchez.sanchez.sergio.bullkeeper.ui.activity.alertlist.IAlertsSettingsActivityHandler;
import sanchez.sanchez.sergio.bullkeeper.ui.dialog.NoticeDialogFragment;
import sanchez.sanchez.sergio.bullkeeper.ui.support.SupportPreferenceFragment;
import sanchez.sanchez.sergio.bullkeeper.utils.PreferencesRepositoryImpl;
import sanchez.sanchez.sergio.domain.repository.IPreferenceRepository;
import timber.log.Timber;

/**
 * Alerts Settings Activity Fragment
 */
public class AlertsSettingsActivityFragment extends
        SupportPreferenceFragment<IAlertsSettingsActivityHandler> {

    public static final String TAG = "ALERTS_SETTINGS_ACTIVITY_FRAGMENT";

    /**
     * Settings Component
     */
    protected SettingsComponent settingsComponent;

    private String numberOfAlerts;
    private String ageOfAlerts;
    private Boolean enableAllAlert;
    private Boolean enableSuccess;
    private Boolean enableInformation;
    private Boolean enableWarning;
    private Boolean enableDanger;


    /**
     * Get Preferences Layout
     * @return
     */
    @Override
    protected int getPreferencesLayout() {
        return R.xml.filter_alerts_preferences;
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
        final ListPreference numberOfAlertsListPreference = (ListPreference) findPreference(IPreferenceRepository.PREF_FILTER_ALERTS_COUNT);
        numberOfAlertsListPreference.setOnPreferenceChangeListener(this);

        numberOfAlerts = preferencesRepositoryImpl.getFilterAlertsCount();

        // Age Of Alerts
        final ListPreference ageOfAlertsPreference = (ListPreference) findPreference(IPreferenceRepository.PREF_FILTER_AGE_OF_ALERTS);
        ageOfAlertsPreference.setOnPreferenceChangeListener(this);

        ageOfAlerts = preferencesRepositoryImpl.getFilterAgeOfAlerts();

        // Enable All Alerts Categories
        final SwitchPreferenceCompat enableAllAlertCategories = (SwitchPreferenceCompat) findPreference(IPreferenceRepository.PREF_FILTER_ALERTS_ENABLE_ALL_CATEGORIES);
        enableAllAlertCategories.setOnPreferenceChangeListener(this);

        enableAllAlert = preferencesRepositoryImpl.isFilterAlertsEnableAllCategories();

        // Enable Success Alerts
        final SwitchPreferenceCompat enableSuccessAlerts = (SwitchPreferenceCompat) findPreference(IPreferenceRepository.PREF_FILTER_ALERTS_ENABLE_SUCCESS_CATEGORY);
        enableSuccessAlerts.setOnPreferenceChangeListener(this);
        enableSuccessAlerts.setEnabled(!enableAllAlertCategories.isEnabled());

        enableSuccess = preferencesRepositoryImpl.isFilterAlertsEnableSuccessCategory();

        // Enable Information Alerts
        final SwitchPreferenceCompat enableInformationAlerts = (SwitchPreferenceCompat) findPreference(IPreferenceRepository.PREF_FILTER_ALERTS_ENABLE_INFORMATION_CATEGORY);
        enableInformationAlerts.setOnPreferenceChangeListener(this);
        enableInformationAlerts.setEnabled(!enableAllAlertCategories.isEnabled());

        enableInformation = preferencesRepositoryImpl.isFilterAlertsEnableInformationCategory();

        // Enable Warning Alerts
        final SwitchPreferenceCompat enableWarningAlerts = (SwitchPreferenceCompat) findPreference(IPreferenceRepository.PREF_FILTER_ALERTS_ENABLE_WARNING_CATEGORY);
        enableWarningAlerts.setOnPreferenceChangeListener(this);
        enableWarningAlerts.setEnabled(!enableAllAlertCategories.isEnabled());

        enableWarning = preferencesRepositoryImpl.isFilterAlertsEnableWarningCategory();

        // Enable Danger Alerts
        final SwitchPreferenceCompat enableDangerAlerts = (SwitchPreferenceCompat) findPreference(IPreferenceRepository.PREF_FILTER_ALERTS_ENABLE_DANGER_CATEGORY);
        enableDangerAlerts.setOnPreferenceChangeListener(this);
        enableDangerAlerts.setEnabled(!enableAllAlertCategories.isEnabled());

        enableDanger = preferencesRepositoryImpl.isFilterAlertsEnableDangerCategory();

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

            case PreferencesRepositoryImpl.PREF_FILTER_ALERTS_ENABLE_ALL_CATEGORIES:

                final boolean enableAllAlertsCategories = (boolean)newValue;

                final SwitchPreferenceCompat enableSuccessAlerts, enableInformationAlerts,
                        enableWarningAlerts, enableDangerAlerts;

                enableSuccessAlerts  = (SwitchPreferenceCompat) findPreference(PreferencesRepositoryImpl.PREF_FILTER_ALERTS_ENABLE_SUCCESS_CATEGORY);
                enableInformationAlerts = (SwitchPreferenceCompat) findPreference(PreferencesRepositoryImpl.PREF_FILTER_ALERTS_ENABLE_INFORMATION_CATEGORY);
                enableWarningAlerts = (SwitchPreferenceCompat) findPreference(PreferencesRepositoryImpl.PREF_FILTER_ALERTS_ENABLE_WARNING_CATEGORY);
                enableDangerAlerts = (SwitchPreferenceCompat) findPreference(PreferencesRepositoryImpl.PREF_FILTER_ALERTS_ENABLE_DANGER_CATEGORY);

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

    /**
     * On Save Preferences
     */
    @OnClick(R.id.savePreferences)
    protected void onSavePreferences(){
        activityHandler.showNoticeDialog(R.string.preferences_saved_successfully_message, new NoticeDialogFragment.NoticeDialogListener() {
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

        Boolean hasPendingChanges = Boolean.FALSE;

        final ListPreference numberOfAlertsListPreference = (ListPreference) findPreference(IPreferenceRepository.PREF_FILTER_ALERTS_COUNT);

        if(!numberOfAlertsListPreference.getValue().equals(numberOfAlerts))
            hasPendingChanges = Boolean.TRUE;

        final ListPreference ageOfAlertsPreference = (ListPreference) findPreference(IPreferenceRepository.PREF_FILTER_AGE_OF_ALERTS);

        if(!ageOfAlertsPreference.getValue().equals(ageOfAlerts))
            hasPendingChanges = Boolean.TRUE;

        Timber.d("Has Pending Changes");
        return hasPendingChanges;
    }

    /**
     * On Saved Pending Changes
     */
    @Override
    public void onSavedPendingChanges() {
        Timber.d("On Saved Pending Changes");
    }

    /**
     * On Discard Pending Changes
     */
    @Override
    public void onDiscardPendingChanges() {
        Timber.d("On Discard Pending Changes");

        preferencesRepositoryImpl.setFilterAlertsCount(numberOfAlerts);
        preferencesRepositoryImpl.setFilterAgeOfAlerts(ageOfAlerts);
        preferencesRepositoryImpl.setFilterEnableAllAlertCategories(enableAllAlert);
        preferencesRepositoryImpl.setPrefFilterAlertsEnableSuccessCategory(enableSuccess);
        preferencesRepositoryImpl.setPrefFilterAlertsEnableInformationCategory(enableInformation);
        preferencesRepositoryImpl.setPrefFilterAlertsEnableDangerCategory(enableDanger);
        preferencesRepositoryImpl.setPrefFilterAlertsEnableWarningCategory(enableWarning);
    }
}
