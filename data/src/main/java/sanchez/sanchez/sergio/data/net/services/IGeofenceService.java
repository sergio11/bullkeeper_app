package sanchez.sanchez.sergio.data.net.services;

import java.util.List;
import io.reactivex.Observable;
import retrofit2.http.Body;
import retrofit2.http.DELETE;
import retrofit2.http.GET;
import retrofit2.http.POST;
import retrofit2.http.Path;
import sanchez.sanchez.sergio.data.net.models.request.SaveGeofenceDTO;
import sanchez.sanchez.sergio.data.net.models.response.APIResponse;
import sanchez.sanchez.sergio.data.net.models.response.GeofenceAlertDTO;
import sanchez.sanchez.sergio.data.net.models.response.GeofenceDTO;

/**
 * Geofence Service
 */
public interface IGeofenceService {

    /**
     * Get Geofences By Kid
     * @param kid
     * @return
     */
    @GET("children/{kid}/geofences")
    Observable<APIResponse<List<GeofenceDTO>>> getGeofencesByKid(
            @Path("kid") final String kid
    );

    /**
     * Get Geofences By Id
     * @param kid
     * @param id
     * @return
     */
    @GET("children/{kid}/geofences/{id}")
    Observable<APIResponse<GeofenceDTO>> getGeofenceById(
            @Path("kid") final String kid,
            @Path("id") final String id
    );


    /**
     * Get alerts from a Geofence
     * @param kid
     * @param geofence
     * @return
     */
    @GET("children/{kid}/geofences/{geofence}/alerts")
    Observable<APIResponse<List<GeofenceAlertDTO>>> getGeofenceAlerts(
            @Path("kid") final String kid,
            @Path("geofence") final String geofence
    );

    /**
     * Delete Geofence Alerts
     * @param kid
     * @param geofence
     * @return
     */
    @DELETE("children/{kid}/geofences/{geofence}/alerts")
    Observable<APIResponse<String>> deleteGeofenceAlerts(
            @Path("kid") final String kid,
            @Path("geofence") final String geofence
    );

    /**
     * Delete All Geofences By Kid
     * @param kid
     * @return
     */
    @DELETE("children/{kid}/geofences")
    Observable<APIResponse<String>> deleteAllGeofencesByKidId(
            @Path("kid") final String kid
    );

    /**
     * Delete Geofence
     * @param kid
     * @param id
     * @return
     */
    @DELETE("children/{kid}/geofences/{id}")
    Observable<APIResponse<String>> deleteGeofence(
            @Path("kid") final String kid,
            @Path("id") final String id
    );

    /**
     * Save Geofence
     * @param kid
     * @param saveGeofenceDTO
     * @return
     */
    @POST("children/{kid}/geofences")
    Observable<APIResponse<GeofenceDTO>> saveGeofence(
            @Path("kid") final String kid,
            @Body final SaveGeofenceDTO saveGeofenceDTO
    );

}
