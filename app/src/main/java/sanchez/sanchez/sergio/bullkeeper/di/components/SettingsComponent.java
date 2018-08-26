package sanchez.sanchez.sergio.bullkeeper.di.components;

import dagger.Component;
import sanchez.sanchez.sergio.bullkeeper.di.modules.ActivityModule;
import sanchez.sanchez.sergio.bullkeeper.di.scopes.PerActivity;
import sanchez.sanchez.sergio.bullkeeper.ui.activity.settings.UserSettingsMvpActivity;
import sanchez.sanchez.sergio.bullkeeper.ui.activity.settings.UserSettingsActivityPresenter;
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


    UserSettingsActivityPresenter userSettingsActivityPresenter();

}
