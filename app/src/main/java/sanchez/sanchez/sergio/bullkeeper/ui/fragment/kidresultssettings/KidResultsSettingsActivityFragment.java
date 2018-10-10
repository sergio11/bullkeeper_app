package sanchez.sanchez.sergio.bullkeeper.ui.fragment.kidresultssettings;

import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v4.app.DialogFragment;
import android.support.v7.preference.ListPreference;
import android.support.v7.preference.Preference;
import android.view.View;
import java.util.Date;
import butterknife.OnClick;
import icepick.State;
import sanchez.sanchez.sergio.bullkeeper.R;
import sanchez.sanchez.sergio.bullkeeper.core.ui.SupportPreferenceFragment;
import sanchez.sanchez.sergio.bullkeeper.di.HasComponent;
import sanchez.sanchez.sergio.bullkeeper.di.components.SettingsComponent;
import sanchez.sanchez.sergio.bullkeeper.ui.activity.kidresultssettings.IKidResultsSettingsActivityHandler;
import sanchez.sanchez.sergio.bullkeeper.ui.dialog.NoticeDialogFragment;
import sanchez.sanchez.sergio.domain.repository.IPreferenceRepository;
import timber.log.Timber;

/**
 * Kid Results Settings Activity Fragment
 */
public class KidResultsSettingsActivityFragment extends
        SupportPreferenceFragment<IKidResultsSettingsActivityHandler> {

    /**
     * Settings Component
     */
    protected SettingsComponent settingsComponent;

    /**
     * State
     * =================
     */

    @State
    protected String ageOfResults;

    /**
     * Get Preferences Layout
     * @return
     */
    @Override
    protected int getPreferencesLayout() {
        return R.xml.kid_results_preferences;
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

        /**
         * Age Of Results List Preference
         */
        final ListPreference ageOfResultsListPreference = (ListPreference) findPreference(IPreferenceRepository.PREF_AGE_OF_RESULTS);
        ageOfResultsListPreference.setOnPreferenceChangeListener(this);
        ageOfResults = ageOfResultsListPreference.getValue();


    }


    /**
     * On Preference Change
     * @param preference
     * @param newValue
     * @return
     */
    @Override
    public boolean onPreferenceChange(Preference preference, Object newValue) {
        return true;
    }

    /**
     * On Save Preferences
     */
    @OnClick(R.id.savePreferences)
    protected void onSavePreferences(){

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

        // Check Age of Results
        final ListPreference ageOfResultsListPreference =
                (ListPreference) findPreference(IPreferenceRepository.PREF_AGE_OF_RESULTS);
        if(!ageOfResultsListPreference.getValue().equals(ageOfResults)) return true;

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
        preferencesRepositoryImpl.setPrefAgeOfResults(ageOfResults);
    }
}
