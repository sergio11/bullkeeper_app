package sanchez.sanchez.sergio.bullkeeper.ui.activity.devicephotodetail;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.NonNull;
import com.crashlytics.android.answers.ContentViewEvent;
import sanchez.sanchez.sergio.bullkeeper.R;
import sanchez.sanchez.sergio.bullkeeper.core.ui.SupportMvpActivity;
import sanchez.sanchez.sergio.bullkeeper.di.HasComponent;
import sanchez.sanchez.sergio.bullkeeper.di.components.DaggerDevicePhotoComponent;
import sanchez.sanchez.sergio.bullkeeper.di.components.DevicePhotoComponent;
import sanchez.sanchez.sergio.bullkeeper.ui.fragment.kiddetail.devicephotodetail.DevicePhotoDetailActivityMvpFragment;

/**
 * Device Photo Detail Mvp Activity
 */
public class DevicePhotoDetailMvpActivity extends SupportMvpActivity<DevicePhotoDetailPresenter, IDevicePhotoDetailView>
        implements HasComponent<DevicePhotoComponent>
        , IDevicePhotoDetailView, IDevicePhotoActivityHandler {

    private final String CONTENT_FULL_NAME = "DEVICE_PHOTO_DETAIL";
    private final String CONTENT_TYPE_NAME = "DEVICE_PHOTO";

    /**
     * Args
     */
    public static String TERMINAL_ID_ARG = "TERMINAL_ID_ARG";
    public static String KID_ID_ARG = "KID_ID_ARG";
    public static String DEVICE_PHOTO_ARG = "DEVICE_PHOTO_ARG";

    /**
     * Device Photo Component
     */
    private DevicePhotoComponent devicePhotoComponent;

    /**
     * Get Calling Intent
     * @param context
     * @param kid
     * @param terminal
     * @param photo
     * @return
     */
    public static Intent getCallingIntent(final Context context, final String kid,
                                          final String terminal, final String photo) {
        final Intent intent = new Intent(context, DevicePhotoDetailMvpActivity.class);
        intent.putExtra(KID_ID_ARG, kid);
        intent.putExtra(TERMINAL_ID_ARG, terminal);
        intent.putExtra(DEVICE_PHOTO_ARG, photo);
        return intent;
    }


    /**
     * Initialize Injector
     */
    @Override
    protected void initializeInjector() {
        devicePhotoComponent = DaggerDevicePhotoComponent.builder()
                .applicationComponent(getApplicationComponent())
                .activityModule(getActivityModule())
                .build();
        devicePhotoComponent.inject(this);

    }

    /**
     * Get Layout Resource
     * @return
     */
    @Override
    protected int getLayoutRes() {
        return R.layout.activity_fragment_container;
    }


    @Override
    protected void onViewReady(final Bundle savedInstanceState) {
        if(savedInstanceState == null) {


            if(!getIntent().hasExtra(TERMINAL_ID_ARG))
                throw new IllegalArgumentException("It is necessary to specify an terminal identifier");

            if (!getIntent().hasExtra(KID_ID_ARG))
                throw new IllegalArgumentException("It is necessary to specify an kid identifier");

            if (!getIntent().hasExtra(DEVICE_PHOTO_ARG))
                throw new IllegalArgumentException("It is necessary to specify an day scheduled identifier");

            final String terminal = getIntent().getStringExtra(TERMINAL_ID_ARG);
            final String kid = getIntent().getStringExtra(KID_ID_ARG);
            final String photo = getIntent().getStringExtra(DEVICE_PHOTO_ARG);

            addFragment(R.id.mainContainer,
                    DevicePhotoDetailActivityMvpFragment
                            .newInstance(terminal, kid, photo), false);
        }
    }

    /**
     * On Create Content View Event
     * @return
     */
    @Override
    protected ContentViewEvent onCreateContentViewEvent() {
        return new ContentViewEvent()
                .putContentName(CONTENT_FULL_NAME)
                .putContentType(CONTENT_TYPE_NAME);

    }

    /**
     * Provide Presenter
     * @return
     */
    @NonNull
    @Override
    public DevicePhotoDetailPresenter providePresenter() {
        return devicePhotoComponent.devicePhotoDetailPresenter();
    }

    /**
     * Get component
     * @return
     */
    @Override
    public DevicePhotoComponent getComponent() {
        return devicePhotoComponent;
    }

    /**
     * Get Background
     * @return
     */
    @Override
    protected int getBackgroundResource() {
        return R.drawable.background_cyan_5;
    }

}
