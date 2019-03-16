package sanchez.sanchez.sergio.data.net.services;

import java.util.List;
import io.reactivex.Observable;
import okhttp3.MultipartBody;
import retrofit2.http.Body;
import retrofit2.http.GET;
import retrofit2.http.Multipart;
import retrofit2.http.POST;
import retrofit2.http.Part;
import retrofit2.http.Path;
import retrofit2.http.Query;
import sanchez.sanchez.sergio.data.net.models.request.RegisterKidDTO;
import sanchez.sanchez.sergio.data.net.models.request.SaveKidGuardianDTO;
import sanchez.sanchez.sergio.data.net.models.request.UpdateKidDTO;
import sanchez.sanchez.sergio.data.net.models.response.APIResponse;
import sanchez.sanchez.sergio.data.net.models.response.AlertsStatisticsDTO;
import sanchez.sanchez.sergio.data.net.models.response.ImageDTO;
import sanchez.sanchez.sergio.data.net.models.response.KidDTO;
import sanchez.sanchez.sergio.data.net.models.response.KidGuardianDTO;
import sanchez.sanchez.sergio.data.net.models.response.LocationDTO;

/**
 * Children Service Interface
 */
public interface IChildrenService {

    /**
     * Get Son By Id
     * @param id
     * @return
     */
    @GET("children/{id}")
    Observable<APIResponse<KidDTO>> getKidById(@Path("id") final String id);

    /**
     * Get Alerts Statistics
     * @param ids
     * @param daysAgo
     * @return
     */
    @GET("alerts/statistics/alerts")
    Observable<APIResponse<AlertsStatisticsDTO>> getAlertsStatistics(@Query("identities") final String[] ids,
                                                                     @Query("days_ago") final Integer daysAgo);

    /**
     * Add Kid To Self Guardian
     * @param registerKidDTO
     * @return
     */
    @POST("guardians/self/children/add")
    Observable<APIResponse<KidDTO>> addSonToSelfGuardian(final @Body RegisterKidDTO registerKidDTO);

    /**
     * Save Kid Information
     * @param updateKidDTO
     * @return
     */
    @POST("guardians/self/children/update")
    Observable<APIResponse<KidDTO>> saveKidInformation(final @Body UpdateKidDTO updateKidDTO);


    /**
     * Upload Profile Image
     * @return
     */
    @Multipart
    @POST("children/{id}/image")
    Observable<APIResponse<ImageDTO>> uploadProfileImage(@Path("id") final String id, @Part final MultipartBody.Part image);

    /**
     * Save Guardians
     * @param kid
     * @param saveKidGuardianDTOS
     * @return
     */
    @POST("children/{id}/guardians")
    Observable<APIResponse<List<KidGuardianDTO>>> saveGuardians(
            @Path("id") final String kid,
            @Body final List<SaveKidGuardianDTO> saveKidGuardianDTOS);


    /**
     * Get Guardians
     * @param kid
     * @return
     */
    @GET("children/{id}/guardians")
    Observable<APIResponse<List<KidGuardianDTO>>> getGuardians(
            @Path("id") final String kid);


    /**
     * Get Kid Location
     * @param kid
     * @return
     */
    @GET("children/{kid}/location")
    Observable<APIResponse<LocationDTO>> getKidLocation(
            @Path("kid") final String kid
    );

}
