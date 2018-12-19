package sanchez.sanchez.sergio.bullkeeper.di.components;

import dagger.Component;
import sanchez.sanchez.sergio.bullkeeper.di.modules.ActivityModule;
import sanchez.sanchez.sergio.bullkeeper.di.modules.CallModule;
import sanchez.sanchez.sergio.bullkeeper.di.modules.DataMapperModule;
import sanchez.sanchez.sergio.bullkeeper.di.scopes.PerActivity;
import sanchez.sanchez.sergio.bullkeeper.ui.activity.calldetail.CallDetailMvpActivity;
import sanchez.sanchez.sergio.bullkeeper.ui.activity.calldetail.CallDetailPresenter;
import sanchez.sanchez.sergio.bullkeeper.ui.fragment.kiddetail.calldetail.CallDetailActivityMvpFragment;
import sanchez.sanchez.sergio.bullkeeper.ui.fragment.kiddetail.calldetail.CallDetailFragmentPresenter;

/**
 * Call Detail Component
 */
@PerActivity
@Component(dependencies = ApplicationComponent.class,
        modules = { ActivityModule.class, DataMapperModule.class, CallModule.class})
public interface CallDetailComponent extends ActivityComponent {

    /**
     * Inject into Call Detail Activity
     * @param callDetailMvpActivity
     */
    void inject(final CallDetailMvpActivity callDetailMvpActivity);

    /**
     * Inject into Call Detail Activity Mvp Fragment
     * @param callDetailActivityMvpFragment
     */
    void inject(final CallDetailActivityMvpFragment callDetailActivityMvpFragment);


    /**
     * Presenters
     * @return
     */
    CallDetailPresenter callDetailPresenter();
    CallDetailFragmentPresenter callDetailFragmentPresenter();
}
