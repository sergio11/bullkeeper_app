package sanchez.sanchez.sergio.bullkeeper.di.components;

import dagger.Component;
import sanchez.sanchez.sergio.bullkeeper.di.modules.ActivityModule;
import sanchez.sanchez.sergio.bullkeeper.di.modules.DataMapperModule;
import sanchez.sanchez.sergio.bullkeeper.di.modules.GuardianModule;
import sanchez.sanchez.sergio.bullkeeper.di.scopes.PerActivity;
import sanchez.sanchez.sergio.bullkeeper.ui.activity.searchguardian.SearchGuardiansActivityPresenter;
import sanchez.sanchez.sergio.bullkeeper.ui.activity.searchguardian.SearchGuardiansMvpActivity;

/**
 * Search Guardian Component
 */
@PerActivity
@Component(dependencies = ApplicationComponent.class,
        modules = { ActivityModule.class, DataMapperModule.class,
                 GuardianModule.class })
public interface SearchGuardianComponent extends ActivityComponent {

    /**
     * Inject into Search Guardian Mvp Activity
     * @param searchGuardiansMvpActivity
     */
    void inject(final SearchGuardiansMvpActivity searchGuardiansMvpActivity);

    /**
     * Search Guardian Activity Presenter
     * @return
     */
    SearchGuardiansActivityPresenter searchGuardianActivityPresenter();
}
