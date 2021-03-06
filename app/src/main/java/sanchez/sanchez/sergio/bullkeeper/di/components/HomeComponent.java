package sanchez.sanchez.sergio.bullkeeper.di.components;

import dagger.Component;
import sanchez.sanchez.sergio.bullkeeper.di.modules.ActivityModule;
import sanchez.sanchez.sergio.bullkeeper.di.modules.AlertsModule;
import sanchez.sanchez.sergio.bullkeeper.di.modules.DataMapperModule;
import sanchez.sanchez.sergio.bullkeeper.di.modules.GuardianModule;
import sanchez.sanchez.sergio.bullkeeper.di.scopes.PerActivity;
import sanchez.sanchez.sergio.bullkeeper.ui.activity.home.HomeMvpActivity;
import sanchez.sanchez.sergio.bullkeeper.ui.activity.home.HomePresenter;
import sanchez.sanchez.sergio.bullkeeper.ui.fragment.profile.ProfileMvpFragment;
import sanchez.sanchez.sergio.bullkeeper.ui.fragment.profile.ProfileFragmentPresenter;
import sanchez.sanchez.sergio.bullkeeper.ui.fragment.lastalerts.LastAlertsActivityMvpFragment;
import sanchez.sanchez.sergio.bullkeeper.ui.fragment.lastalerts.LastAlertsFragmentPresenter;

@PerActivity
@Component(
        dependencies = ApplicationComponent.class,
        modules = { ActivityModule.class, DataMapperModule.class,
                 GuardianModule.class, AlertsModule.class })
public interface HomeComponent extends ActivityComponent {

    /**
     * Inject into Home Activity
     * @param homeActivity
     */
    void inject(final HomeMvpActivity homeActivity);

    /**
     * Inject into Home Fragment
     * @param homeFragment
     */
    void inject(final ProfileMvpFragment homeFragment);

    /**
     * Inject into Last Alerts Activity Mvp Fragment
     * @param lastAlertsActivityMvpFragment
     */
    void inject(final LastAlertsActivityMvpFragment lastAlertsActivityMvpFragment);


    HomePresenter homePresenter();
    ProfileFragmentPresenter homeFragmentPresenter();
    LastAlertsFragmentPresenter lastAlertsFragmentPresenter();

}
