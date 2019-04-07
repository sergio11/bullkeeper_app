package sanchez.sanchez.sergio.bullkeeper.ui.fragment.kiddetail.devicephotodetail;

import sanchez.sanchez.sergio.bullkeeper.core.ui.ISupportView;
import sanchez.sanchez.sergio.domain.models.DayScheduledEntity;
import sanchez.sanchez.sergio.domain.models.DevicePhotoEntity;

/**
 * Device Photo Detail
 */
public interface IDevicePhotoDetailView extends ISupportView {

    /**
     * On Device Photo Loaded
     * @param devicePhotoEntity
     */
    void onDevicePhotoLoaded(final DevicePhotoEntity devicePhotoEntity);

    /**
     * On Device Photo Disabled Successfully
     */
    void onDevicePhotoDisabledSuccessfully();


}
