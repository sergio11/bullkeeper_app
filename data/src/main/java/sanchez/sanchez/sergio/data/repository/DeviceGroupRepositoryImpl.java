package sanchez.sanchez.sergio.data.repository;

import com.fernandocejas.arrow.checks.Preconditions;
import io.reactivex.Observable;
import sanchez.sanchez.sergio.data.mapper.AbstractDataMapper;
import sanchez.sanchez.sergio.data.net.models.request.SaveDeviceDTO;
import sanchez.sanchez.sergio.data.net.models.response.DeviceDTO;
import sanchez.sanchez.sergio.data.net.services.IDeviceGroupsService;
import sanchez.sanchez.sergio.domain.models.DeviceEntity;
import sanchez.sanchez.sergio.domain.repository.IDeviceGroupRepository;

/**
 * Device Group Repository
 */
public final class DeviceGroupRepositoryImpl implements IDeviceGroupRepository {

    /**
     * Device Data Mapper
     */
    private final AbstractDataMapper<DeviceDTO, DeviceEntity> deviceDataMapper;

    /**
     * Device Group Service
     */
    private final IDeviceGroupsService deviceGroupsService;

    /**
     *
     * @param deviceDataMapper
     * @param deviceGroupsService
     */
    public DeviceGroupRepositoryImpl(final AbstractDataMapper<DeviceDTO, DeviceEntity> deviceDataMapper,
                                     final IDeviceGroupsService deviceGroupsService) {
        this.deviceDataMapper = deviceDataMapper;
        this.deviceGroupsService = deviceGroupsService;
    }

    /**
     * Save
     * @param deviceId
     * @param registrationToken
     * @return
     */
    @Override
    public Observable<DeviceEntity> save(final String deviceId, final String registrationToken) {
        Preconditions.checkNotNull(deviceId, "Device Id can not be null");
        Preconditions.checkNotNull(registrationToken, "Registration can not be null");

        return deviceGroupsService.save(new SaveDeviceDTO(deviceId, registrationToken))
            .map(response -> response != null && response.getData() != null ? response.getData() : null)
            .map(deviceDataMapper::transform);
    }

    /**
     * Delete
     * @param deviceId
     * @return
     */
    @Override
    public Observable<String> delete(final String deviceId) {
        Preconditions.checkNotNull(deviceId, "Device Id can not be null");
        Preconditions.checkState(!deviceId.isEmpty(), "Device id ca not be null");

        return deviceGroupsService.delete(deviceId)
                .map(response -> response != null && response.getData() != null ? response.getData() : null);
    }
}
