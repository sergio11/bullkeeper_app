package sanchez.sanchez.sergio.domain.repository;


import java.util.List;

import io.reactivex.Observable;
import sanchez.sanchez.sergio.domain.models.DevicePhotoEntity;

/**
 * Device Photos Repository
 **/
public interface IDevicePhotosRepository {

    /**
     *
     * @param kid
     * @param terminal
     * @return
     */
    Observable<List<DevicePhotoEntity>> getDevicePhotos(final String kid, final String terminal);


    /**
     * @param kid
     * @param terminal
     * @return
     */
    Observable<List<DevicePhotoEntity>> getListOfDisabledDevicePhotos(final String kid, final String terminal);

    /**
     *
     * @param kid
     * @param terminal
     * @param photo
     * @return
     */
    Observable<DevicePhotoEntity> getDevicePhotoDetail(final String kid, final String terminal, final String photo);

    /**
     *
     * @param kid
     * @param terminal
     * @param photo
     * @return
     */
    Observable<String> disableDevicePhoto(final String kid, final String terminal, final String photo);

}
