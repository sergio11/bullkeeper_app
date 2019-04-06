package sanchez.sanchez.sergio.data.repository;

import com.fernandocejas.arrow.checks.Preconditions;
import java.util.List;
import io.reactivex.Observable;
import sanchez.sanchez.sergio.data.mapper.AbstractDataMapper;
import sanchez.sanchez.sergio.data.net.models.response.DevicePhotoDTO;
import sanchez.sanchez.sergio.data.net.services.IDevicePhotosService;
import sanchez.sanchez.sergio.domain.models.DevicePhotoEntity;
import sanchez.sanchez.sergio.domain.repository.IDevicePhotosRepository;

/**
 * Device Photos Repository
 **/
public final class DevicePhotosRepositoryImpl implements IDevicePhotosRepository {

    private final AbstractDataMapper<DevicePhotoDTO, DevicePhotoEntity> devicePhotoEntityAbstractDataMapper;
    private final IDevicePhotosService devicePhotosService;

    /**
     *
     * @param devicePhotoEntityAbstractDataMapper
     * @param devicePhotosService
     */
    public DevicePhotosRepositoryImpl(final AbstractDataMapper<DevicePhotoDTO, DevicePhotoEntity> devicePhotoEntityAbstractDataMapper,
                                      final IDevicePhotosService devicePhotosService) {
        this.devicePhotoEntityAbstractDataMapper = devicePhotoEntityAbstractDataMapper;
        this.devicePhotosService = devicePhotosService;
    }

    /**
     * Get Device Photos
     * @param kid
     * @param terminal
     * @return
     */
    @Override
    public Observable<List<DevicePhotoEntity>> getDevicePhotos(final String kid, final String terminal) {
        Preconditions.checkNotNull(kid, "Kid can not be null");
        Preconditions.checkNotNull(terminal, "Terminal can not be null");

        return devicePhotosService.getDevicePhotos(kid, terminal)
                .map(response -> response != null && response.getData() != null ?
                        response.getData(): null)
                .map(devicePhotoEntityAbstractDataMapper::transform);
    }

    /**
     * Get List Of Disabled Device Photos
     * @param kid
     * @param terminal
     * @return
     */
    @Override
    public Observable<List<DevicePhotoEntity>> getListOfDisabledDevicePhotos(final String kid, final String terminal) {
        Preconditions.checkNotNull(kid, "Kid can not be null");
        Preconditions.checkNotNull(terminal, "Terminal can not be null");

        return devicePhotosService.getListOfDisabledDevicePhotos(kid, terminal)
                .map(response -> response != null && response.getData() != null ?
                        response.getData(): null)
                .map(devicePhotoEntityAbstractDataMapper::transform);

    }

    /**
     * Get Device Photo Detail
     * @param kid
     * @param terminal
     * @param photo
     * @return
     */
    @Override
    public Observable<DevicePhotoEntity> getDevicePhotoDetail(final String kid, final String terminal, final String photo) {
        Preconditions.checkNotNull(kid, "Kid can not be null");
        Preconditions.checkNotNull(terminal, "Terminal can not be null");
        Preconditions.checkNotNull(photo, "Photo can not be null");

        return devicePhotosService.getDevicePhotoDetail(kid, terminal, photo)
                .map(response -> response != null && response.getData() != null ?
                        response.getData(): null)
                .map(devicePhotoEntityAbstractDataMapper::transform);
    }

    /**
     * Disable Device Photo
     * @param kid
     * @param terminal
     * @param photo
     * @return
     */
    @Override
    public Observable<String> disableDevicePhoto(final String kid, final String terminal, final String photo) {
        Preconditions.checkNotNull(kid, "Kid can not be null");
        Preconditions.checkNotNull(terminal, "Terminal can not be null");
        Preconditions.checkNotNull(photo, "Photo can not be null");

        return devicePhotosService.disableDevicePhoto(kid, terminal, photo)
                .map(response -> response != null && response.getData() != null ?
                        response.getData(): null);

    }
}
