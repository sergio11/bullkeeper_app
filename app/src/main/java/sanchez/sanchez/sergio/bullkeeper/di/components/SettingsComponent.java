package sanchez.sanchez.sergio.bullkeeper.di.components;

import dagger.Component;
import sanchez.sanchez.sergio.bullkeeper.di.modules.ActivityModule;
import sanchez.sanchez.sergio.bullkeeper.di.scopes.PerActivity;
import sanchez.sanchez.sergio.bullkeeper.ui.activity.alertlist.AlertsSettingsActivityPresenter;
import sanchez.sanchez.sergio.bullkeeper.ui.activity.alertlist.AlertsSettingsMvpActivity;
import sanchez.sanchez.sergio.bullkeeper.ui.activity.kidresultssettings.KidResultsSettingsActivityPresenter;
import sanchez.sanchez.sergio.bullkeeper.ui.activity.kidresultssettings.KidResultsSettingsMvpActivity;
import sanchez.sanchez.sergio.bullkeeper.ui.activity.settings.UserSettingsMvpActivity;
import sanchez.sanchez.sergio.bullkeeper.ui.activity.settings.UserSettingsActivityPresenter;
import sanchez.sanchez.sergio.bullkeeper.ui.fragment.alertslist.AlertsSettingsActivityFragment;
import sanchez.sanchez.sergio.bullkeeper.ui.fragment.kidresultssettings.KidResultsSettingsActivityFragment;
import sanchez.sanchez.sergio.bullkeeper.ui.fragment.settings.UserSettingsActivityFragment;

/**
 * Settings Component
 */
@PerActivity
@Component(dependencies = ApplicationComponent.class, modules = { ActivityModule.class })
public interface SettingsComponent extends ActivityComponent {

    /**
     * Inject into User Settings Activity
     * @param userSettingsActivity
     */
    void inject(final UserSettingsMvpActivity userSettingsActivity);

    /**
     * Inject into User Settings Activity Fragment
     * @param userSettingsActivityFragment
     */
    void inject(final UserSettingsActivityFragment userSettingsActivityFragment);

    /**
     * Inject into Alerts Settings Mvp Activity
     * @param alertsSettingsMvpActivity
     */
    void inject(final AlertsSettingsMvpActivity alertsSettingsMvpActivity);

    /**
     * Alerts Settings Fragment
     * @param alertsSettingsFragment
     */
    void inject(final AlertsSettingsActivityFragment alertsSettingsFragment);

    /**
     * Kid Results Settings Mvp Activity
     * @param kidResultsSettingsMvpActivity
     */
    void inject(final KidResultsSettingsMvpActivity kidResultsSettingsMvpActivity);

    /**
     * Inject into Kid Results Settings Activity Fragment
     * @param kidResultsSettingsActivityFragment
     */
    void inject(final KidResultsSettingsActivityFragment kidResultsSettingsActivityFragment);


    UserSettingsActivityPresenter userSettingsActivityPresenter();
    AlertsSettingsActivityPresenter alertsSettingsActivityPresenter();
    KidResultsSettingsActivityPresenter kidResultsSettingsActivityPresenter();

}
