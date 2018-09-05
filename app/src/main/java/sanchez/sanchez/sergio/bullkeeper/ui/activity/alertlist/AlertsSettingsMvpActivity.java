package sanchez.sanchez.sergio.bullkeeper.ui.activity.alertlist;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.NonNull;

import sanchez.sanchez.sergio.bullkeeper.R;
import sanchez.sanchez.sergio.bullkeeper.di.HasComponent;
import sanchez.sanchez.sergio.bullkeeper.di.components.DaggerSettingsComponent;
import sanchez.sanchez.sergio.bullkeeper.di.components.SettingsComponent;
import sanchez.sanchez.sergio.bullkeeper.ui.fragment.alertslist.AlertsSettingsActivityFragment;
import sanchez.sanchez.sergio.bullkeeper.ui.support.IDataManagement;
import sanchez.sanchez.sergio.bullkeeper.ui.support.SupportMvpActivity;
import sanchez.sanchez.sergio.bullkeeper.ui.support.SupportToolbarApp;

/**
 * Alerts Settings Activity
 */
public class AlertsSettingsMvpActivity extends SupportMvpActivity<AlertsSettingsActivityPresenter, IAlertsSettingsView>
        implements HasComponent<SettingsComponent>, IAlertsSettingsActivityHandler
        , IAlertsSettingsView {


    /**
     * Settings Component
     */
    private SettingsComponent settingsComponent;

    private AlertsSettingsActivityFragment alertsSettingsActivityFragment;

    /**
     * Get Calling Intent
     * @param context
     * @return
     */
    public static Intent getCallingIntent(final Context context) {
        return new Intent(context, AlertsSettingsMvpActivity.class);
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
    public AlertsSettingsActivityPresenter providePresenter() {
        return settingsComponent.alertsSettingsActivityPresenter();
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
        return R.layout.activity_alert_settings;
    }

    /**
     * On View Ready
     * @param savedInstanceState
     */
    @Override
    protected void onViewReady(Bundle savedInstanceState) {
        alertsSettingsActivityFragment = new AlertsSettingsActivityFragment();
        if (savedInstanceState == null)
            addFragment(R.id.mainContainer, alertsSettingsActivityFragment,
                    false, AlertsSettingsActivityFragment.TAG);
    }

    /**
     * Has Pending Changes
     * @return
     */
    @Override
    public Boolean hasPendingChanges() {
        return alertsSettingsActivityFragment.hasPendingChanges();
    }

    /**
     * On Saved Pending Changes
     */
    @Override
    public void onSavedPendingChanges() {
        super.onSavedPendingChanges();
        alertsSettingsActivityFragment.onSavedPendingChanges();
    }

    /**
     * On Discard Pending Changes
     */
    @Override
    public void onDiscardPendingChanges() {
        super.onDiscardPendingChanges();
        alertsSettingsActivityFragment.onDiscardPendingChanges();
    }
}
