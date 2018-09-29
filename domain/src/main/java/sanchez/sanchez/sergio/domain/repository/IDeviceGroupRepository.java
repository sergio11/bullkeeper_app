package sanchez.sanchez.sergio.domain.repository;

import io.reactivex.Observable;
import sanchez.sanchez.sergio.domain.models.DeviceEntity;

/**
 * Device Group Repository
 */
public interface IDeviceGroupRepository {

    /**
     * Save
     * @param deviceId
     * @param registrationToken
     * @return
     */
    Observable<DeviceEntity> save(final String deviceId, final String registrationToken);

    /**
     * Delete Device
     * @param deviceId
     * @return
     */
    Observable<String> delete(final String deviceId);

}
