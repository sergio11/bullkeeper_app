package sanchez.sanchez.sergio.data.net.services;

import io.reactivex.Observable;
import retrofit2.http.Body;
import retrofit2.http.GET;
import retrofit2.http.POST;
import retrofit2.http.Path;
import sanchez.sanchez.sergio.data.net.models.request.SaveDayScheduledDTO;
import sanchez.sanchez.sergio.data.net.models.request.SaveFunTimeScheduledDTO;
import sanchez.sanchez.sergio.data.net.models.response.APIResponse;
import sanchez.sanchez.sergio.data.net.models.response.DayScheduledDTO;
import sanchez.sanchez.sergio.data.net.models.response.FunTimeScheduledDTO;

/**
 * Fun Time Service
 */
public interface IFunTimeService {

    /**
     * Get Fun Time Scheduled
     * @param kid
     * @param terminal
     * @return
     */
    @GET("children/{kid}/terminal/{terminal}/funtime-scheduled")
    Observable<APIResponse<FunTimeScheduledDTO>> getFunTimeScheduled(
            @Path("kid") final String kid,
            @Path("terminal") final String terminal
    );

    /**
     * Get Fun Time Scheduled
     * @param kid
     * @param terminal
     * @param day
     * @return
     */
    @GET("children/{kid}/terminal/{terminal}/funtime-scheduled/{day}")
    Observable<APIResponse<DayScheduledDTO>> getDayScheduled(
            @Path("kid") final String kid,
            @Path("terminal") final String terminal,
            @Path("day") final String day
    );


    /**
     * Save Day Fun Time Scheduled
     * @param kid
     * @param terminal
     * @param day
     * @param dayScheduledDTO
     * @return
     */
    @POST("children/{kid}/terminal/{terminal}/funtime-scheduled/{day}")
    Observable<APIResponse<DayScheduledDTO>> saveDayScheduled(
            @Path("kid") final String kid,
            @Path("terminal") final String terminal,
            @Path("day") final String day,
            @Body final SaveDayScheduledDTO dayScheduledDTO);

    /**
     * Save Fun Time Scheduled
     * @param kid
     * @param terminal
     * @param saveFunTimeScheduledDTO
     * @return
     */
    @POST("children/{kid}/terminal/{terminal}/funtime-scheduled")
    Observable<APIResponse<FunTimeScheduledDTO>> saveFunTimeScheduled(
            @Path("kid") final String kid,
            @Path("terminal") final String terminal,
            @Body SaveFunTimeScheduledDTO saveFunTimeScheduledDTO
    );
}
