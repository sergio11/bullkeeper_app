package sanchez.sanchez.sergio.bullkeeper.ui.fragment.kiddetail.devicephotodetail;

import android.os.Bundle;
import com.fernandocejas.arrow.checks.Preconditions;
import javax.inject.Inject;
import sanchez.sanchez.sergio.bullkeeper.R;
import sanchez.sanchez.sergio.bullkeeper.core.ui.SupportPresenter;
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
     *
     * @param getDevicePhotoDetailInteract
     */
    @Inject
    public DevicePhotoDetailFragmentPresenter(final GetDevicePhotoDetailInteract getDevicePhotoDetailInteract) {
        this.getDevicePhotoDetailInteract = getDevicePhotoDetailInteract;
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

}
