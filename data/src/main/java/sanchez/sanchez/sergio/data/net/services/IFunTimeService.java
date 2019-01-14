package sanchez.sanchez.sergio.data.net.services;

import io.reactivex.Observable;
import retrofit2.http.GET;
import retrofit2.http.POST;
import retrofit2.http.Path;
import sanchez.sanchez.sergio.data.net.models.response.APIResponse;
import sanchez.sanchez.sergio.data.net.models.response.FunTimeScheduledDTO;

/**
 * Fun Time Service
 */
public interface IFunTimeService {

    /**
     * Get Fun Time Scheduled
     * @param kid
     * @return
     */
    @GET("children/{kid}/funtime-scheduled")
    Observable<APIResponse<FunTimeScheduledDTO>> getFunTimeScheduled(
            @Path("kid") final String kid
    );

    /**
     * Save Fun Time Scheduled
     * @param kid
     * @return
     */
    @POST("children/{kid}/funtime-scheduled")
    Observable<APIResponse<FunTimeScheduledDTO>> saveFunTimeScheduled(
            @Path("kid") final String kid
    );
}
