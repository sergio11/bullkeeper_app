package sanchez.sanchez.sergio.bullkeeper.di.components;

import dagger.Component;
import sanchez.sanchez.sergio.bullkeeper.di.modules.ActivityModule;
import sanchez.sanchez.sergio.bullkeeper.di.modules.AppRulesModule;
import sanchez.sanchez.sergio.bullkeeper.di.modules.DataMapperModule;
import sanchez.sanchez.sergio.bullkeeper.di.scopes.PerActivity;
import sanchez.sanchez.sergio.bullkeeper.ui.activity.appdetail.AppDetailMvpActivity;
import sanchez.sanchez.sergio.bullkeeper.ui.activity.appdetail.AppDetailPresenter;
import sanchez.sanchez.sergio.bullkeeper.ui.activity.appsearch.AppSearchListMvpActivity;
import sanchez.sanchez.sergio.bullkeeper.ui.activity.appsearch.AppSearchListPresenter;
import sanchez.sanchez.sergio.bullkeeper.ui.fragment.kiddetail.appdetail.AppInstalledDetailActivityMvpFragment;
import sanchez.sanchez.sergio.bullkeeper.ui.fragment.kiddetail.appdetail.AppInstalledDetailFragmentPresenter;

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

    /**
     * Inject into App Search List Mvp Activity
     * @param appSearchListMvpActivity
     */
    void inject(final AppSearchListMvpActivity appSearchListMvpActivity);


    /**
     * Presenter
     * @return
     */
    AppDetailPresenter appDetailPresenter();
    AppInstalledDetailFragmentPresenter appInstalledDetailFragmentPresenter();
    AppSearchListPresenter appSearchListPresenter();

}
