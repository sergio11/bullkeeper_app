package sanchez.sanchez.sergio.bullkeeper.ui.activity.settings;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.NonNull;
import sanchez.sanchez.sergio.bullkeeper.R;
import sanchez.sanchez.sergio.bullkeeper.di.HasComponent;
import sanchez.sanchez.sergio.bullkeeper.di.components.DaggerSettingsComponent;
import sanchez.sanchez.sergio.bullkeeper.di.components.SettingsComponent;
import sanchez.sanchez.sergio.bullkeeper.ui.fragment.settings.UserSettingsActivityFragment;
import sanchez.sanchez.sergio.bullkeeper.ui.support.SupportMvpActivity;
import sanchez.sanchez.sergio.bullkeeper.ui.support.SupportToolbarApp;

/**
 * User Settings Activity
 */
public class UserSettingsMvpActivity extends SupportMvpActivity<UserSettingsActivityPresenter, IUserSettingsView>
        implements HasComponent<SettingsComponent>, IUserSettingsActivityHandler
        , IUserSettingsView {


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
        return R.layout.activity_user_settings;
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
}
