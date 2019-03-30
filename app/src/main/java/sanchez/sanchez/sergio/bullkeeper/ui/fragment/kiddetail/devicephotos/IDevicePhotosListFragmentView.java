package sanchez.sanchez.sergio.bullkeeper.ui.fragment.kiddetail.devicephotos;

import sanchez.sanchez.sergio.bullkeeper.core.ui.ISupportLCEView;
import sanchez.sanchez.sergio.domain.models.DevicePhotoEntity;

/**
 * Device Photos List Fragment View
 */
interface IDevicePhotosListFragmentView extends ISupportLCEView<DevicePhotoEntity> {

    /**
     * On Lock Camera Status Changed Successfully
     */
    void onLockCameraStatusChangedSuccessfully();

    /**
     * On Lock Screen Status Changed Failed
     */
    void onLockCameraStatusChangedFailed();

}
