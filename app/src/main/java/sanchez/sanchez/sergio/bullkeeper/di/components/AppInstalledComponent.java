package sanchez.sanchez.sergio.bullkeeper.di.components;

import dagger.Component;
import sanchez.sanchez.sergio.bullkeeper.di.modules.ActivityModule;
import sanchez.sanchez.sergio.bullkeeper.di.modules.AppRulesModule;
import sanchez.sanchez.sergio.bullkeeper.di.modules.DataMapperModule;
import sanchez.sanchez.sergio.bullkeeper.di.scopes.PerActivity;
import sanchez.sanchez.sergio.bullkeeper.ui.activity.appdetail.AppDetailMvpActivity;
import sanchez.sanchez.sergio.bullkeeper.ui.activity.appdetail.AppDetailPresenter;
import sanchez.sanchez.sergio.bullkeeper.ui.fragment.appdetail.AppInstalledDetailActivityMvpFragment;
import sanchez.sanchez.sergio.bullkeeper.ui.fragment.appdetail.AppInstalledDetailFragmentPresenter;

/**
 * App Installed Component
 */
@PerActivity
@Component(dependencies = ApplicationComponent.class,
        modules = { ActivityModule.class, DataMapperModule.class, AppRulesModule.class})
public interface AppInstalledComponent extends ActivityComponent {

    /**
     * Inject to App Detail Mvp Activity
     * @param appDetailMvpActivity
     */
    void inject(final AppDetailMvpActivity appDetailMvpActivity);

    /**
     * Inject into App Installed Detail Activity Mvp Fragment
     * @param appInstalledDetailActivityMvpFragment
     */
    void inject(final AppInstalledDetailActivityMvpFragment appInstalledDetailActivityMvpFragment);

    AppDetailPresenter appDetailPresenter();
    AppInstalledDetailFragmentPresenter appInstalledDetailFragmentPresenter();

}
