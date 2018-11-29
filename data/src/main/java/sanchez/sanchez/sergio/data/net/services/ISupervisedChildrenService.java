package sanchez.sanchez.sergio.data.net.services;

import java.util.List;
import io.reactivex.Observable;
import retrofit2.http.DELETE;
import retrofit2.http.GET;
import retrofit2.http.Path;
import sanchez.sanchez.sergio.data.net.models.response.APIResponse;
import sanchez.sanchez.sergio.data.net.models.response.SupervisedChildrenDTO;

/**
 * Supervised Children Service
 */
public interface ISupervisedChildrenService {

    /**
     * Get Supervised Children Confirmed
     * @return
     */
    @GET("guardians/self/children/confirmed")
    Observable<APIResponse<List<SupervisedChildrenDTO>>> getSupervisedChildrenConfirmed();

    /**
     * Delete Supervised Children Confirmed
     * @param kid
     * @return
     */
    @DELETE("guardians/self/children/confirmed/{kid}")
    Observable<APIResponse<String>> deleteSupervisedChildrenConfirmed(
            @Path("kid") final String kid);

    /**
     * Get Supervised Children Confirmed Detail
     * @param kid
     * @return
     */
    @GET("guardians/self/children/confirmed/{kid}")
    Observable<APIResponse<SupervisedChildrenDTO>>
        getSupervisedChildrenConfirmedDetail(@Path("kid") final String kid);


    /**
     * Delete Supervised Children No Confirmed
     * @return
     */
    @DELETE("guardians/self/children/no-confirmed")
    Observable<APIResponse<String>> deleteSupervisedChildrenNoConfirmed();


    /**
     * Get Supervised Children No Confirmed
     * @return
     */
    @GET("guardians/self/children/no-confirmed")
    Observable<APIResponse<List<SupervisedChildrenDTO>>> getSupervisedChildrenNoConfirmed();

}
