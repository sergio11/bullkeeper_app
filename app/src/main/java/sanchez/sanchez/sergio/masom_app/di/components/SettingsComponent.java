package sanchez.sanchez.sergio.masom_app.di.components;

import dagger.Component;
import sanchez.sanchez.sergio.masom_app.di.modules.ActivityModule;
import sanchez.sanchez.sergio.masom_app.di.scopes.PerActivity;
import sanchez.sanchez.sergio.masom_app.ui.activity.settings.UserSettingsMvpActivity;
import sanchez.sanchez.sergio.masom_app.ui.activity.settings.UserSettingsActivityPresenter;
import sanchez.sanchez.sergio.masom_app.ui.fragment.settings.UserSettingsActivityFragment;

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


    UserSettingsActivityPresenter userSettingsActivityPresenter();

}
