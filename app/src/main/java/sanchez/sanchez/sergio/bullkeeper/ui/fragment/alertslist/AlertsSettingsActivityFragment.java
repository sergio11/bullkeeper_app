package sanchez.sanchez.sergio.bullkeeper.ui.fragment.alertslist;

import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v4.app.DialogFragment;
import android.support.v7.preference.ListPreference;
import android.support.v7.preference.Preference;
import android.support.v7.preference.PreferenceCategory;
import android.support.v7.preference.PreferenceScreen;
import android.support.v7.preference.SwitchPreferenceCompat;
import android.view.View;

import java.util.Date;

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

    public static final String ENABLE_ALERTS_CATEGORY_ARG = "ENABLE_ALERTS_CATEGORY_ARG";

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
     * New Instance
     * @return
     */
    public static AlertsSettingsActivityFragment newInstance() {
        final AlertsSettingsActivityFragment alertDetailActivityFragment =
                new AlertsSettingsActivityFragment();
        return alertDetailActivityFragment;
    }

    /**
     * New Instance
     * @param enableAlertsCategory
     * @return
     */
    public static AlertsSettingsActivityFragment newInstance(final Boolean enableAlertsCategory) {
        final AlertsSettingsActivityFragment alertSettingsActivityFragment =
                new AlertsSettingsActivityFragment();
        final Bundle args = new Bundle();
        args.putBoolean(ENABLE_ALERTS_CATEGORY_ARG, enableAlertsCategory);
        alertSettingsActivityFragment.setArguments(args);
        return alertSettingsActivityFragment;
    }


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


        if(getArguments() != null && getArguments().getBoolean(ENABLE_ALERTS_CATEGORY_ARG, false)) {

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

        } else {

            // Get Preference Screen
            final PreferenceScreen preferenceScreen = (PreferenceScreen)
                    findPreference(IPreferenceRepository.ALERTS_SETTINGS_PREFERENCE_SCREEN);

            // Alerts Category
            final PreferenceCategory alertsCategoryGroup = (PreferenceCategory)
                    findPreference(IPreferenceRepository.ALERTS_CATEGORY_GROUP_KEY);

            preferenceScreen.removePreference(alertsCategoryGroup);
        }
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
        preferencesRepositoryImpl.setPreferencesUpdateAt(new Date().getTime());
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

        final ListPreference numberOfAlertsListPreference = (ListPreference) findPreference(IPreferenceRepository.PREF_FILTER_ALERTS_COUNT);

        if(!numberOfAlertsListPreference.getValue().equals(numberOfAlerts)) return true;

        final ListPreference ageOfAlertsPreference = (ListPreference) findPreference(IPreferenceRepository.PREF_FILTER_AGE_OF_ALERTS);

        if(!ageOfAlertsPreference.getValue().equals(ageOfAlerts)) return true;


        if(getArguments() != null && getArguments().getBoolean(ENABLE_ALERTS_CATEGORY_ARG, false)) {

            final SwitchPreferenceCompat enableAllAlertCategories =
                    (SwitchPreferenceCompat) findPreference(IPreferenceRepository.PREF_FILTER_ALERTS_ENABLE_ALL_CATEGORIES);

            if(!enableAllAlert.equals(enableAllAlertCategories.isChecked())) return true;

            final SwitchPreferenceCompat enableSuccessAlerts =
                    (SwitchPreferenceCompat) findPreference(IPreferenceRepository.PREF_FILTER_ALERTS_ENABLE_SUCCESS_CATEGORY);

            if(!enableSuccess.equals(enableSuccessAlerts.isChecked())) return true;

            final SwitchPreferenceCompat enableInformationAlerts =
                    (SwitchPreferenceCompat) findPreference(IPreferenceRepository.PREF_FILTER_ALERTS_ENABLE_INFORMATION_CATEGORY);

            if(!enableInformation.equals(enableInformationAlerts.isChecked())) return true;

            final SwitchPreferenceCompat enableWarningAlerts =
                    (SwitchPreferenceCompat) findPreference(IPreferenceRepository.PREF_FILTER_ALERTS_ENABLE_WARNING_CATEGORY);

            if(!enableWarning.equals(enableWarningAlerts.isChecked())) return true;

            final SwitchPreferenceCompat enableDangerAlerts = (SwitchPreferenceCompat) findPreference(IPreferenceRepository.PREF_FILTER_ALERTS_ENABLE_DANGER_CATEGORY);

            if(!enableDanger.equals(enableDangerAlerts.isChecked())) return true;

        }

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

        preferencesRepositoryImpl.setFilterAlertsCount(numberOfAlerts);
        preferencesRepositoryImpl.setFilterAgeOfAlerts(ageOfAlerts);

        if(getArguments() != null && getArguments().getBoolean(ENABLE_ALERTS_CATEGORY_ARG, false)) {
            preferencesRepositoryImpl.setFilterEnableAllAlertCategories(enableAllAlert);
            preferencesRepositoryImpl.setPrefFilterAlertsEnableSuccessCategory(enableSuccess);
            preferencesRepositoryImpl.setPrefFilterAlertsEnableInformationCategory(enableInformation);
            preferencesRepositoryImpl.setPrefFilterAlertsEnableDangerCategory(enableDanger);
            preferencesRepositoryImpl.setPrefFilterAlertsEnableWarningCategory(enableWarning);
        }
    }
}
