package sanchez.sanchez.sergio.data.mapper.impl;

import com.fernandocejas.arrow.checks.Preconditions;
import sanchez.sanchez.sergio.data.mapper.AbstractDataMapper;
import sanchez.sanchez.sergio.data.net.models.response.DevicePhotoDTO;
import sanchez.sanchez.sergio.data.net.utils.ApiEndPointsHelper;
import sanchez.sanchez.sergio.domain.models.DevicePhotoEntity;
import sanchez.sanchez.sergio.domain.utils.IAppUtils;

/**
 * Device Photo Entity Data Mapper
 **/
public final class DevicePhotoEntityDataMapper extends AbstractDataMapper<DevicePhotoDTO, DevicePhotoEntity> {


    private final ApiEndPointsHelper apiEndPointsHelper;
    private final IAppUtils appUtils;

    /**
     *
     * @param apiEndPointsHelper
     */
    public DevicePhotoEntityDataMapper(final ApiEndPointsHelper apiEndPointsHelper,
                                       final IAppUtils appUtils) {
        this.apiEndPointsHelper = apiEndPointsHelper;
        this.appUtils = appUtils;
    }

    /**
     *
     * @param originModel
     * @return
     */
    @Override
    public DevicePhotoEntity transform(final DevicePhotoDTO originModel) {
        Preconditions.checkNotNull(originModel, "Origin Model can not be null ");

        final DevicePhotoEntity devicePhotoEntity = new DevicePhotoEntity();
        devicePhotoEntity.setDateAdded(originModel.getDateAdded());
        devicePhotoEntity.setDateModified(originModel.getDateModified());
        devicePhotoEntity.setDateTaken(originModel.getDateTaken());
        devicePhotoEntity.setDisplayName(originModel.getDisplayName());
        devicePhotoEntity.setHeight(originModel.getHeight());
        devicePhotoEntity.setIdentity(originModel.getIdentity());
        devicePhotoEntity.setKid(originModel.getKid());
        devicePhotoEntity.setLocalId(originModel.getLocalId());
        devicePhotoEntity.setOrientation(originModel.getOrientation());
        devicePhotoEntity.setPath(originModel.getPath());
        devicePhotoEntity.setSize(originModel.getSize());
        devicePhotoEntity.setTerminal(originModel.getTerminal());
        devicePhotoEntity.setWidth(originModel.getWidth());
        if(appUtils.isValidString(originModel.getImageId()))
            devicePhotoEntity.setImageUrl(apiEndPointsHelper.getDevicePhotoUrl(
                    originModel.getKid(), originModel.getTerminal(),
                    originModel.getIdentity()
            ));

        return devicePhotoEntity;
    }

    /**
     *
     * @param originModel
     * @return
     */
    @Override
    public DevicePhotoDTO transformInverse(DevicePhotoEntity originModel) {
        Preconditions.checkNotNull(originModel, "Origin Model can not be null");

        final DevicePhotoDTO devicePhotoDTO = new DevicePhotoDTO();
        devicePhotoDTO.setDateAdded(originModel.getDateAdded());
        devicePhotoDTO.setDateModified(originModel.getDateModified());
        devicePhotoDTO.setDateTaken(originModel.getDateTaken());
        devicePhotoDTO.setDisplayName(originModel.getDisplayName());
        devicePhotoDTO.setHeight(originModel.getHeight());
        devicePhotoDTO.setIdentity(originModel.getIdentity());
        devicePhotoDTO.setKid(originModel.getKid());
        devicePhotoDTO.setLocalId(originModel.getLocalId());
        devicePhotoDTO.setOrientation(originModel.getOrientation());
        devicePhotoDTO.setPath(originModel.getPath());
        devicePhotoDTO.setSize(originModel.getSize());
        devicePhotoDTO.setTerminal(originModel.getTerminal());
        devicePhotoDTO.setWidth(originModel.getWidth());

        return devicePhotoDTO;
    }
}
