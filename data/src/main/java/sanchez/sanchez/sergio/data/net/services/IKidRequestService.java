package sanchez.sanchez.sergio.data.net.services;

import java.util.List;
import io.reactivex.Observable;
import retrofit2.http.Body;
import retrofit2.http.DELETE;
import retrofit2.http.GET;
import retrofit2.http.POST;
import retrofit2.http.Path;
import sanchez.sanchez.sergio.data.net.models.response.APIResponse;
import sanchez.sanchez.sergio.data.net.models.response.KidRequestDTO;

/**
 * Request Service
 * DELETE /api/v1/children/{kid}/request DELETE_ALL_REQUEST_FOR_KID
 * POST /api/v1/children/{kid}/request/delete DELETE_REQUEST_FOR_KID
 */
public interface IKidRequestService {


    /**
     * Get All Request For Kid
     * @return
     */
    @GET("children/{kid}/request")
    Observable<APIResponse<List<KidRequestDTO>>>
        getAllRequestForKid(final @Path("kid") String kid);

    /**
     * Get Kid Request Detail
     * @param kid
     * @parma id
     * @return
     */
    @GET("children/{kid}/request/{id}")
    Observable<APIResponse<KidRequestDTO>>
        getKidRequestDetail(
            final @Path("kid") String kid,
            final @Path("id") String id);

    /**
     * Delete All Request For Kid
     * @return
     */
    @DELETE("children/{kid}/request")
    Observable<APIResponse<String>> deleteAllRequestForKid(final @Path("kid") String kid);

    /**
     * Delete Kid Request
     * @return
     */
    @DELETE("children/{kid}/request/{id}")
    Observable<APIResponse<String>> deleteKidRequest(
            final @Path("kid") String kid,
            final @Path("id") String id);

    /**
     * Delete Self Account
     * @return
     */
    @POST("children/{kid}/request/delete")
    Observable<APIResponse<String>> deleteRequestForKid(
            final @Path("kid") String kid,
            @Body final List<String> ids
    );
}
