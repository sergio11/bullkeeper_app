package sanchez.sanchez.sergio.bullkeeper.ui.fragment.kiddetail.devicephotodetail;

import android.content.Context;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v4.app.DialogFragment;
import android.support.v7.widget.AppCompatImageView;
import android.support.v7.widget.AppCompatTextView;
import android.view.View;
import android.view.ViewGroup;
import com.fernandocejas.arrow.checks.Preconditions;
import com.squareup.picasso.Picasso;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Locale;
import javax.inject.Inject;
import butterknife.BindView;
import butterknife.OnClick;
import icepick.State;
import sanchez.sanchez.sergio.bullkeeper.R;
import sanchez.sanchez.sergio.bullkeeper.core.ui.SupportMvpFragment;
import sanchez.sanchez.sergio.bullkeeper.di.components.DevicePhotoComponent;
import sanchez.sanchez.sergio.bullkeeper.ui.activity.devicephotodetail.IDevicePhotoActivityHandler;
import sanchez.sanchez.sergio.bullkeeper.ui.dialog.ConfirmationDialogFragment;
import sanchez.sanchez.sergio.bullkeeper.ui.dialog.NoticeDialogFragment;
import sanchez.sanchez.sergio.domain.models.DevicePhotoEntity;
import static sanchez.sanchez.sergio.bullkeeper.core.ui.SupportToolbarApp.RETURN_TOOLBAR;

/**
 * Day Scheduled Activity Fragment
 */
public class DevicePhotoDetailActivityMvpFragment extends SupportMvpFragment<DevicePhotoDetailFragmentPresenter,
        IDevicePhotoDetailView, IDevicePhotoActivityHandler, DevicePhotoComponent>
        implements IDevicePhotoDetailView {

    /**
     * Args
     */
    public static String TERMINAL_ID_ARG = "TERMINAL_ID_ARG";
    public static String KID_ID_ARG = "KID_ID_ARG";
    public static String DEVICE_PHOTO_ARG = "DEVICE_PHOTO_ARG";

    /**
     * Views
     * =============
     */

    @BindView(R.id.image)
    protected AppCompatImageView imageView;

    /**
     * Delete Device Photo View
     */
    @BindView(R.id.deleteDevicePhoto)
    protected View deleteDevicePhotoView;

    /**
     * Display Name Text View
     */
    @BindView(R.id.displayNameTextView)
    protected AppCompatTextView displayNameTextView;

    /**
     * Date Taken
     */
    @BindView(R.id.dateTakenTextView)
    protected AppCompatTextView dateTakenTextView;

    /**
     * Dimension And Size
     */
    @BindView(R.id.dimensionAndSizeTextView)
    protected AppCompatTextView dimensionAndSizeTextView;


    /**
     * Dependencies
     * ===============
     */


    /**
     * App Context
     */
    @Inject
    protected Context appContext;

    /**
     * Picasso
     */
    @Inject
    protected Picasso picasso;

    /**
     * State
     * =============
     */


    /**
     * Kid
     */
    @State
    protected String kid;

    /**
     * Terminal Id
     */
    @State
    protected String terminal;

    /**
     * Photo Id
     */
    @State
    protected String photo;


    public DevicePhotoDetailActivityMvpFragment() { }

    /**
     * New Instance
     * @param terminal
     * @param kid
     * @param photo
     * @return
     */
    public static DevicePhotoDetailActivityMvpFragment newInstance(
            final String terminal, final String kid, final String photo) {
        final DevicePhotoDetailActivityMvpFragment fragment =
                new DevicePhotoDetailActivityMvpFragment();
        final Bundle args = new Bundle();
        args.putString(TERMINAL_ID_ARG, terminal);
        args.putString(KID_ID_ARG, kid);
        args.putString(DEVICE_PHOTO_ARG, photo);
        fragment.setArguments(args);
        return fragment;
    }

    /**
     * Get Args
     * @return
     */
    @Override
    public Bundle getArgs() {
        return getArguments();
    }

    /**
     * On View Created
     * @param view
     * @param savedInstanceState
     */
    @Override
    public void onViewCreated(View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);

        // Get Child Id
        if(!getArgs().containsKey(KID_ID_ARG) ||
                !appUtils.isValidString(getArgs().getString(KID_ID_ARG)))
            throw new IllegalArgumentException("You must provide a child id");

        kid = getArgs().getString(KID_ID_ARG);

        // Get Terminal Id
        if(!getArgs().containsKey(TERMINAL_ID_ARG) ||
                !appUtils.isValidString(getArgs().getString(TERMINAL_ID_ARG)))
            throw new IllegalStateException("You must provide a terminal id");

        terminal = getArgs().getString(TERMINAL_ID_ARG);

        // Get Photo
        if(!getArgs().containsKey(DEVICE_PHOTO_ARG) ||
                !appUtils.isValidString(getArgs().getString(DEVICE_PHOTO_ARG)))
            throw new IllegalStateException("You must provide a photo");

        photo = getArgs().getString(DEVICE_PHOTO_ARG);

    }

    /**
     * Get Layout Resource
     * @return
     */
    @Override
    protected int getLayoutRes() {
        return R.layout.fragment_device_photo_detail;
    }

    /**
     * Initialize Injector
     */
    @Override
    protected void initializeInjector(final DevicePhotoComponent component) {
        component.inject(this);
    }


    /**
     * Provide Presenter
     * @return
     */
    @NonNull
    @Override
    public DevicePhotoDetailFragmentPresenter providePresenter() {
        return component.devicePhotoDetailFragmentPresenter();
    }


    /**
     * Get Toolbar Type
     * @return
     */
    @Override
    protected int getToolbarType() {
        return RETURN_TOOLBAR;
    }

    /**
     * On Network Error
     */
    @Override
    public void onNetworkError() {
        activityHandler.showNoticeDialog(R.string.network_error_ocurred, new NoticeDialogFragment.NoticeDialogListener() {
            @Override
            public void onAccepted(DialogFragment dialog) {
                activityHandler.closeActivity();
            }
        });
    }

    /**
     * On Other Exception
     */
    @Override
    public void onOtherException() {
        activityHandler.showNoticeDialog(R.string.unexpected_error_ocurred, new NoticeDialogFragment.NoticeDialogListener() {
            @Override
            public void onAccepted(DialogFragment dialog) {
                activityHandler.closeActivity();
            }
        });
    }

    /**
     * on Device Photo Loaded
     * @param devicePhotoEntity
     */
    @Override
    public void onDevicePhotoLoaded(DevicePhotoEntity devicePhotoEntity) {
        Preconditions.checkNotNull(devicePhotoEntity, "Device Photo Entity can not be null");

        final ViewGroup.LayoutParams params = imageView.getLayoutParams();
        params.width = devicePhotoEntity.getWidth();
        params.height = devicePhotoEntity.getHeight();
        imageView.setLayoutParams(params);

        picasso.load(devicePhotoEntity.getImageUrl())
                .rotate(devicePhotoEntity.getOrientation())
                .into(imageView);

        displayNameTextView.setText(devicePhotoEntity.getDisplayName());

        final SimpleDateFormat sdf = new SimpleDateFormat(getString(R.string.date_time_format_2),
                Locale.getDefault());

        try {
            dateTakenTextView.setText(String.format(Locale.getDefault(),
                    getString(R.string.device_photo_taken_at), sdf.format(new Date(devicePhotoEntity.getDateTaken()))));

        }  catch (final Exception ex) {
            dateTakenTextView.setText(getString(R.string.device_photo_unknown_data));
        }

        dimensionAndSizeTextView.setText(String.format(Locale.getDefault(),
                getString(R.string.device_photo_dimension_and_size),
                devicePhotoEntity.getWidth(), devicePhotoEntity.getHeight(),
                devicePhotoEntity.getSize()/1024));

    }

    /**
     *
     */
    @Override
    public void onDevicePhotoDisabledSuccessfully() {
        showNoticeDialog(R.string.device_photo_disabled_successfully, new NoticeDialogFragment.NoticeDialogListener() {
            @Override
            public void onAccepted(DialogFragment dialog) {
                activityHandler.closeActivity();
            }
        });
    }


    /**
     * on Delete Device Photo Clicked
     */
    @OnClick(R.id.deleteDevicePhoto)
    protected void onDeleteDevicePhotoClicked(){
        uiUtils.startBounceAnimationForView(deleteDevicePhotoView);
        activityHandler.showConfirmationDialog(R.string.delete_device_photo_confirm, new ConfirmationDialogFragment.ConfirmationDialogListener() {
            @Override
            public void onAccepted(DialogFragment dialog) {
                getPresenter().deleteDevicePhoto(kid, terminal, photo);
            }

            @Override
            public void onRejected(DialogFragment dialog) {}
        });
    }
}
