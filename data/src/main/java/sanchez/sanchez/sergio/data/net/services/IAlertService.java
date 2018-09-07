package sanchez.sanchez.sergio.data.net.services;

import java.util.List;

import io.reactivex.Observable;
import retrofit2.http.DELETE;
import retrofit2.http.GET;
import retrofit2.http.Path;
import retrofit2.http.Query;
import sanchez.sanchez.sergio.data.net.models.response.APIResponse;
import sanchez.sanchez.sergio.data.net.models.response.AlertDTO;
import sanchez.sanchez.sergio.data.net.models.response.AlertsPageDTO;

/**
 * Alert Service
 */
public interface IAlertService {


    /**
     * Clear Alerts of Son
     * @param son
     * @return
     */
    @DELETE("children/{son}/alerts")
    Observable<APIResponse<String>> clearAlertsOfSon(@Path("son") String son);


    /**
     * Get Alerts By Son
     * @return
     */
    @GET("children/{son}/alerts")
    Observable<APIResponse<List<AlertDTO>>> getAlertsBySon(@Path("son") final String sonIdentity);

    /**
     * Delete Alerts Of Son
     * @param son
     * @param alert
     * @return
     */
    @DELETE("children/{son}/alerts/{alert}")
    Observable<APIResponse<String>> deleteAlertOfSon(@Path("son") String son,
                                                     @Path("alert") String alert);

    /**
     * Get Alert By Id
     * @param son
     * @param alert
     * @return
     */
    @GET("children/{son}/alerts/{alert}")
    Observable<APIResponse<AlertDTO>> getAlertById(@Path("son") final String son,
                                                   @Path("alert") final String alert);

    /**
     * Clear Self Alerts
     * @return
     */
    @DELETE("parents/self/alerts")
    Observable<APIResponse<String>> clearSelfAlerts();

    /**
     * Get Self Alerts
     * @return
     */
    @GET("parents/self/alerts")
    Observable<APIResponse<List<AlertDTO>>> getSelfAlerts(
            @Query("count") String count,
            @Query("days_ago") String daysAgo,
            @Query("levels") String levelsCsv);

    /**
     * Get Self Alerts
     * @return
     */
    @GET("parents/self/alerts")
    Observable<APIResponse<List<AlertDTO>>> getSelfAlerts();

    /**
     * Get Self Alerts Last
     * @return
     */
    @GET("parents/self/alerts/last")
    Observable<APIResponse<AlertsPageDTO>> getSelfAlertsLast();

    /**
     * Get Self Alerts Last
     * @return
     */
    @GET("parents/self/alerts/last")
    Observable<APIResponse<AlertsPageDTO>> getSelfAlertsLast(final @Query("count") String count,
                                                             final @Query("last_minutes") String lastMinutes,
                                                             final @Query("levels") String levels);

}
