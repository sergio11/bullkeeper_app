package sanchez.sanchez.sergio.bullkeeper.ui.fragment.kiddetail.devicephotodetail;

import android.os.Bundle;
import com.fernandocejas.arrow.checks.Preconditions;
import javax.inject.Inject;
import sanchez.sanchez.sergio.bullkeeper.R;
import sanchez.sanchez.sergio.bullkeeper.core.ui.SupportPresenter;
import sanchez.sanchez.sergio.domain.interactor.photos.DisableDevicePhotoInteract;
import sanchez.sanchez.sergio.domain.interactor.photos.GetDevicePhotoDetailInteract;
import sanchez.sanchez.sergio.domain.models.DevicePhotoEntity;

/**
 * Device Photo Detail Presenter
 */
public final class DevicePhotoDetailFragmentPresenter
        extends SupportPresenter<IDevicePhotoDetailView> {

    /**
     * Get Device Photo Detail Interact
     */
    private final GetDevicePhotoDetailInteract getDevicePhotoDetailInteract;

    /**
     * Disable Device Photo Interact
     */
    private final DisableDevicePhotoInteract disableDevicePhotoInteract;

    /**
     *
     * @param getDevicePhotoDetailInteract
     * @param disableDevicePhotoInteract
     */
    @Inject
    public DevicePhotoDetailFragmentPresenter(
            final GetDevicePhotoDetailInteract getDevicePhotoDetailInteract,
            final DisableDevicePhotoInteract disableDevicePhotoInteract) {
        this.getDevicePhotoDetailInteract = getDevicePhotoDetailInteract;
        this.disableDevicePhotoInteract = disableDevicePhotoInteract;
    }

    /**
     * On Init
     * @param args
     */
    @Override
    protected void onInit(Bundle args) {
        super.onInit(args);

        if(isViewAttached() && getView() != null)
            getView().showProgressDialog(R.string.generic_loading_text);

        final String kid = args.getString(DevicePhotoDetailActivityMvpFragment.KID_ID_ARG);
        final String terminal = args.getString(DevicePhotoDetailActivityMvpFragment.TERMINAL_ID_ARG);
        final String photo = args.getString(DevicePhotoDetailActivityMvpFragment.DEVICE_PHOTO_ARG);

        getDevicePhotoDetailInteract.execute(new GetDevicePhotoDetailObserver(),
                GetDevicePhotoDetailInteract.Params.create(kid, terminal, photo));
    }

    /**
     * Delete Device Photo
     * @param kid
     * @param terminal
     * @param photo
     */
    public void deleteDevicePhoto(final String kid, final String terminal, final String photo){
        Preconditions.checkNotNull(kid, "Kid can not be null");
        Preconditions.checkState(!kid.isEmpty(), "Kid can not be empty");
        Preconditions.checkNotNull(terminal, "Terminal can not be null");
        Preconditions.checkState(!terminal.isEmpty(), "Terminal can not be empty");
        Preconditions.checkNotNull(photo, "Photo can not be null");
        Preconditions.checkState(!photo.isEmpty(), "Photo can not be empty");

        disableDevicePhotoInteract.execute(new DisableDevicePhotoDetailObserver(),
                DisableDevicePhotoInteract.Params.create(kid, terminal, photo));

    }

    /**
     * Get Device Photo Detail Observer
     */
    public class GetDevicePhotoDetailObserver extends BasicCommandCallBackWrapper<DevicePhotoEntity> {

        /**
         * On Success
         * @param response
         */
        @Override
        protected void onSuccess(final DevicePhotoEntity response) {
            Preconditions.checkNotNull(response, "Response can not be null");

            if(isViewAttached() && getView() != null) {
                getView().hideProgressDialog();
                getView().onDevicePhotoLoaded(response);
            }

        }
    }


    /**
     * Disable Device Photo Detail Observer
     */
    public class DisableDevicePhotoDetailObserver extends BasicCommandCallBackWrapper<String> {
        /**
         * On Success
         * @param response
         */
        @Override
        protected void onSuccess(final String response) {
            Preconditions.checkNotNull(response, "Response can not be null");

            if(isViewAttached() && getView() != null) {
                getView().hideProgressDialog();
                getView().onDevicePhotoDisabledSuccessfully();
            }

        }
    }

}
