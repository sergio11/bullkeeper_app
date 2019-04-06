package sanchez.sanchez.sergio.data.net.services;

import java.util.List;

import io.reactivex.Observable;
import retrofit2.http.GET;
import retrofit2.http.POST;
import retrofit2.http.Path;
import sanchez.sanchez.sergio.data.net.models.response.APIResponse;
import sanchez.sanchez.sergio.data.net.models.response.DevicePhotoDTO;

/**
 * Device Photos Service
 * GET /api/v1/children/{kid}/terminal/{terminal}/photos GET_DEVICE_PHOTOS
 * GET /api/v1/children/{kid}/terminal/{terminal}/photos/disable GET_LIST_OF_DISABLED_DEVICE_PHOTOS
 * GET /api/v1/children/{kid}/terminal/{terminal}/photos/{photo} GET_DEVICE_PHOTO_DETAIL
 * POST /api/v1/children/{kid}/terminal/{terminal}/photos/{photo}/disable DISABLE_DEVICE_PHOTO
 **/
public interface IDevicePhotosService {

    /**
     * Get Device Photos
     * @param kid
     * @param terminal
     * @return
     */
    @GET("children/{kid}/terminal/{terminal}/photos")
    Observable<APIResponse<List<DevicePhotoDTO>>> getDevicePhotos(
            @Path("kid") final String kid,
            @Path("terminal") final String terminal
    );


    /**
     * Get List Of Disabled Device Photos
     * @param kid
     * @param terminal
     * @return
     */
    @GET("children/{kid}/terminal/{terminal}/photos")
    Observable<APIResponse<List<DevicePhotoDTO>>> getListOfDisabledDevicePhotos(
            @Path("kid") final String kid,
            @Path("terminal") final String terminal
    );

    /**
     * Get Device Photo Detail
     * @param kid
     * @param terminal
     * @return
     */
    @GET("children/{kid}/terminal/{terminal}/photos/{photo}")
    Observable<APIResponse<DevicePhotoDTO>> getDevicePhotoDetail(
            @Path("kid") final String kid,
            @Path("terminal") final String terminal,
            @Path("photo") final String photo
    );

    /**
     * Disable Device Photo
     * @param kid
     * @param terminal
     * @param photo
     * @return
     */
    @POST("children/{kid}/terminal/{terminal}/photos/{photo}/disable")
    Observable<APIResponse<String>> disableDevicePhoto(
            @Path("kid") final String kid,
            @Path("terminal") final String terminal,
            @Path("photo") final String photo);

}
