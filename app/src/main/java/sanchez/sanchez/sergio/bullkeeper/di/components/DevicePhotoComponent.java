package sanchez.sanchez.sergio.bullkeeper.di.components;

import dagger.Component;
import sanchez.sanchez.sergio.bullkeeper.di.modules.ActivityModule;
import sanchez.sanchez.sergio.bullkeeper.di.modules.DataMapperModule;
import sanchez.sanchez.sergio.bullkeeper.di.modules.DevicePhotosModule;
import sanchez.sanchez.sergio.bullkeeper.di.scopes.PerActivity;
import sanchez.sanchez.sergio.bullkeeper.ui.activity.devicephotodetail.DevicePhotoDetailMvpActivity;
import sanchez.sanchez.sergio.bullkeeper.ui.activity.devicephotodetail.DevicePhotoDetailPresenter;
import sanchez.sanchez.sergio.bullkeeper.ui.fragment.kiddetail.devicephotodetail.DevicePhotoDetailActivityMvpFragment;
import sanchez.sanchez.sergio.bullkeeper.ui.fragment.kiddetail.devicephotodetail.DevicePhotoDetailFragmentPresenter;


/**
 * Device Photo Component
 */
@PerActivity
@Component(dependencies = ApplicationComponent.class,
        modules = {ActivityModule.class, DataMapperModule.class, DevicePhotosModule.class})
public interface DevicePhotoComponent extends ActivityComponent {

    /**
     * Inject into Device Photo Detail Mvp Activity
     * @param devicePhotoDetailMvpActivity
     */
    void inject(final DevicePhotoDetailMvpActivity devicePhotoDetailMvpActivity);

    /**
     * Inject into Device Photo Detail Activity Mvp Fragment
     * @param devicePhotoDetailActivityMvpFragment
     */
    void inject(final DevicePhotoDetailActivityMvpFragment devicePhotoDetailActivityMvpFragment);

    /**
     * Presenters
     * @return
     */
    DevicePhotoDetailPresenter devicePhotoDetailPresenter();
    DevicePhotoDetailFragmentPresenter devicePhotoDetailFragmentPresenter();
}
