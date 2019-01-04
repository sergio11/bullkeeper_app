package sanchez.sanchez.sergio.bullkeeper.di.components;


import dagger.Component;
import sanchez.sanchez.sergio.bullkeeper.di.modules.ActivityModule;
import sanchez.sanchez.sergio.bullkeeper.di.modules.DataMapperModule;
import sanchez.sanchez.sergio.bullkeeper.di.modules.KidRequestModule;
import sanchez.sanchez.sergio.bullkeeper.di.scopes.PerActivity;
import sanchez.sanchez.sergio.bullkeeper.ui.activity.kidrequest.KidRequestListMvpActivity;
import sanchez.sanchez.sergio.bullkeeper.ui.activity.kidrequest.KidRequestListPresenter;
import sanchez.sanchez.sergio.bullkeeper.ui.activity.kidrequestdetail.KidRequestDetailMvpActivity;
import sanchez.sanchez.sergio.bullkeeper.ui.activity.kidrequestdetail.KidRequestDetailPresenter;
import sanchez.sanchez.sergio.bullkeeper.ui.fragment.kidrequestdetail.KidRequestDetailActivityMvpFragment;
import sanchez.sanchez.sergio.bullkeeper.ui.fragment.kidrequestdetail.KidRequestDetailFragmentPresenter;

/**
 * Kid Request Component
 */
@PerActivity
@Component(dependencies = ApplicationComponent.class,
        modules = { ActivityModule.class, DataMapperModule.class, KidRequestModule.class})
public interface KidRequestComponent extends ActivityComponent {

    /**
     * Inject into Kid Request List Mvp Activity
     * @param kidRequestListMvpActivity
     */
    void inject(final KidRequestListMvpActivity kidRequestListMvpActivity);

    /**
     * Inject into Kid Request Detail Mvp Activity
     * @param kidRequestDetailMvpActivity
     */
    void inject(final KidRequestDetailMvpActivity kidRequestDetailMvpActivity);

    /**
     * Inject into Kid Request Detail Activity Mvp Fragment
     * @param kidRequestDetailActivityMvpFragment
     */
    void inject(final KidRequestDetailActivityMvpFragment kidRequestDetailActivityMvpFragment);

    /**
     * Kid Request List Presenter
     * @return
     */
    KidRequestListPresenter kidRequestListPresenter();

    /**
     * Kid Request Detail Presenter
     * @return
     */
    KidRequestDetailPresenter kidRequestDetailPresenter();

    /**
     * Kid Request Detail Fragment Presenter
     * @return
     */
    KidRequestDetailFragmentPresenter kidRequestDetailFragmentPresenter();
}
