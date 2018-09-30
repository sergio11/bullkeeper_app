package sanchez.sanchez.sergio.data.mapper.impl;

import sanchez.sanchez.sergio.data.mapper.AbstractDataMapper;
import sanchez.sanchez.sergio.data.net.models.response.DeviceDTO;
import sanchez.sanchez.sergio.domain.models.DeviceEntity;

/**
 * Device Entity Data Mapper
 */
public class DeviceEntityDataMapper  extends AbstractDataMapper<DeviceDTO, DeviceEntity> {

    /**
     *
     * @param originModel
     * @return
     */
    @Override
    public DeviceEntity transform(final DeviceDTO originModel) {
        final DeviceEntity deviceEntity = new DeviceEntity();
        deviceEntity.setDeviceId(originModel.getDeviceId());
        deviceEntity.setCreateAt(originModel.getCreateAt());
        deviceEntity.setNotificationKey(originModel.getNotificationKey());
        deviceEntity.setNotificationKeyName(originModel.getNotificationKeyName());
        deviceEntity.setRegistrationToken(originModel.getRegistrationToken());
        deviceEntity.setType(originModel.getType());
        return deviceEntity;
    }

    /**
     *
     * @param originModel
     * @return
     */
    @Override
    public DeviceDTO transformInverse(DeviceEntity originModel) {
        final DeviceDTO deviceDTO = new DeviceDTO();
        deviceDTO.setDeviceId(originModel.getDeviceId());
        deviceDTO.setCreateAt(originModel.getCreateAt());
        deviceDTO.setNotificationKey(originModel.getNotificationKey());
        deviceDTO.setNotificationKeyName(originModel.getNotificationKeyName());
        deviceDTO.setRegistrationToken(originModel.getRegistrationToken());
        deviceDTO.setType(originModel.getType());
        return deviceDTO;
    }
}
