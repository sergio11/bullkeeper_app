package sanchez.sanchez.sergio.bullkeeper.di.components;

import dagger.Component;
import sanchez.sanchez.sergio.bullkeeper.di.modules.ActivityModule;
import sanchez.sanchez.sergio.bullkeeper.di.modules.AlertsModule;
import sanchez.sanchez.sergio.bullkeeper.di.modules.DataMapperModule;
import sanchez.sanchez.sergio.bullkeeper.di.modules.ParentModule;
import sanchez.sanchez.sergio.bullkeeper.di.scopes.PerActivity;
import sanchez.sanchez.sergio.bullkeeper.ui.activity.home.HomeMvpActivity;
import sanchez.sanchez.sergio.bullkeeper.ui.activity.home.HomePresenter;
import sanchez.sanchez.sergio.bullkeeper.ui.fragment.home.HomeMvpFragment;
import sanchez.sanchez.sergio.bullkeeper.ui.fragment.home.HomeFragmentPresenter;
import sanchez.sanchez.sergio.bullkeeper.ui.fragment.lastalerts.LastAlertsActivityMvpFragment;
import sanchez.sanchez.sergio.bullkeeper.ui.fragment.lastalerts.LastAlertsFragmentPresenter;

@PerActivity
@Component(
        dependencies = ApplicationComponent.class,
        modules = { ActivityModule.class, DataMapperModule.class,
                ParentModule.class, AlertsModule.class })
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
    void inject(final HomeMvpFragment homeFragment);

    /**
     * Inject into Last Alerts Activity Mvp Fragment
     * @param lastAlertsActivityMvpFragment
     */
    void inject(final LastAlertsActivityMvpFragment lastAlertsActivityMvpFragment);


    HomePresenter homePresenter();
    HomeFragmentPresenter homeFragmentPresenter();
    LastAlertsFragmentPresenter lastAlertsFragmentPresenter();

}
