package sanchez.sanchez.sergio.bullkeeper.ui.activity.settings;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.v4.app.DialogFragment;

import com.crashlytics.android.answers.ContentViewEvent;
import com.fernandocejas.arrow.checks.Preconditions;
import sanchez.sanchez.sergio.bullkeeper.R;
import sanchez.sanchez.sergio.bullkeeper.di.HasComponent;
import sanchez.sanchez.sergio.bullkeeper.di.components.DaggerSettingsComponent;
import sanchez.sanchez.sergio.bullkeeper.di.components.SettingsComponent;
import sanchez.sanchez.sergio.bullkeeper.ui.dialog.NoticeDialogFragment;
import sanchez.sanchez.sergio.bullkeeper.ui.fragment.settings.UserSettingsActivityFragment;
import sanchez.sanchez.sergio.bullkeeper.core.ui.SupportMvpActivity;
import sanchez.sanchez.sergio.bullkeeper.core.ui.SupportToolbarApp;
import sanchez.sanchez.sergio.domain.models.UserPreferenceEntity;

/**
 * User Settings Activity
 */
public class UserSettingsMvpActivity extends SupportMvpActivity<UserSettingsActivityPresenter, IUserSettingsView>
        implements HasComponent<SettingsComponent>, IUserSettingsActivityHandler
        , IUserSettingsView {

    private final String CONTENT_FULL_NAME = "USER_SETTINGS";
    private final String CONTENT_TYPE_NAME = "USER";
    /**
     * Settings Component
     */
    private SettingsComponent settingsComponent;

    private UserSettingsActivityFragment userSettingsActivityFragment;

    /**
     * Get Calling Intent
     * @param context
     * @return
     */
    public static Intent getCallingIntent(final Context context) {
        return new Intent(context, UserSettingsMvpActivity.class);
    }


    /**
     * Initialize Injector
     */
    @Override
    protected void initializeInjector() {
        this.settingsComponent = DaggerSettingsComponent.builder()
                .applicationComponent(getApplicationComponent())
                .activityModule(getActivityModule())
                .build();
        this.settingsComponent.inject(this);
    }

    /**
     * Provide Presenter
     * @return
     */
    @NonNull
    @Override
    public UserSettingsActivityPresenter providePresenter() {
        return settingsComponent.userSettingsActivityPresenter();
    }

    /**
     * Get Component
     * @return
     */
    @Override
    public SettingsComponent getComponent() {
        return settingsComponent;
    }

    /**
     * Get Toolbar Type
     * @return
     */
    @Override
    protected int getToolbarType() {
        return SupportToolbarApp.RETURN_TOOLBAR;
    }

    /**
     * Get Layout Resource
     * @return
     */
    @Override
    protected int getLayoutRes() {
        return R.layout.activity_settings;
    }

    /**
     * On View Ready
     * @param savedInstanceState
     */
    @Override
    protected void onViewReady(Bundle savedInstanceState) {
        userSettingsActivityFragment = new UserSettingsActivityFragment();
        if (savedInstanceState == null)
            addFragment(R.id.mainContainer, userSettingsActivityFragment, false);
    }

    /**
     * On Create Content View Event
     * @return
     */
    @Override
    protected ContentViewEvent onCreateContentViewEvent() {
        return new ContentViewEvent().putContentName(CONTENT_FULL_NAME)
                .putContentType(CONTENT_TYPE_NAME);
    }

    /**
     * Has Pending Changes
     * @return
     */
    @Override
    public Boolean hasPendingChanges() {
        super.hasPendingChanges();
        return userSettingsActivityFragment.hasPendingChanges();
    }

    /**
     * On Save Pending Changes
     */
    @Override
    public void onSavedPendingChanges() {
        super.onSavedPendingChanges();
        userSettingsActivityFragment.onSavedPendingChanges();
    }

    /**
     * On Discard Pending Changes
     */
    @Override
    public void onDiscardPendingChanges() {
        super.onDiscardPendingChanges();
        userSettingsActivityFragment.onDiscardPendingChanges();
    }

    /**
     * Get Background
     * @return
     */
    @Override
    protected int getBackgroundResource() {
        return R.drawable.background_cyan_6;
    }

    /**
     * on User Preferences Loaded
     * @param userPreferenceEntity
     */
    @Override
    public void onUserPreferencesLoaded(final UserPreferenceEntity userPreferenceEntity) {
        Preconditions.checkNotNull(userPreferenceEntity, "User Preferences Entity");

        if(userSettingsActivityFragment != null)
            userSettingsActivityFragment.onUserPreferencesLoaded(userPreferenceEntity.getPushNotificationsEnabled(),
                    userPreferenceEntity.getRemoveAlertsEvery());
    }

    /**
     * on User Preferences Saved
     * @param userPreferenceEntity
     */
    @Override
    public void onUserPreferencesSaved(final UserPreferenceEntity userPreferenceEntity) {
        Preconditions.checkNotNull(userPreferenceEntity, "User Preferences Entity can not be null");

        if(userSettingsActivityFragment != null)
            userSettingsActivityFragment.onUserPreferencesLoaded(userPreferenceEntity.getPushNotificationsEnabled(),
                    userPreferenceEntity.getRemoveAlertsEvery());

        showNoticeDialog(R.string.preferences_saved_successfully_message, new NoticeDialogFragment.NoticeDialogListener() {
            @Override
            public void onAccepted(DialogFragment dialog) {
                closeActivity();
            }
        });

    }

    /**
     * on Save Preferences Error
     */
    @Override
    public void onSavePreferencesError() {

        showNoticeDialog(R.string.preferences_saved_error_message);

    }

    /**
     * Save Preferences
     * @param pushNotificationsEnabled
     * @param removeAlertsEvery
     */
    @Override
    public void savePreferences(final Boolean pushNotificationsEnabled, final String removeAlertsEvery) {
        Preconditions.checkNotNull(pushNotificationsEnabled, "Push Notification Enabled");
        Preconditions.checkNotNull(removeAlertsEvery, "Remove Alerts Every");

        getPresenter().savePreferences(pushNotificationsEnabled, removeAlertsEvery);

    }
}
