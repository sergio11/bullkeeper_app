package sanchez.sanchez.sergio.data.net.services;

import java.util.List;

import io.reactivex.Observable;
import retrofit2.http.DELETE;
import retrofit2.http.GET;
import retrofit2.http.Path;
import sanchez.sanchez.sergio.data.net.models.response.APIResponse;
import sanchez.sanchez.sergio.data.net.models.response.AlertDTO;

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
     * Delete Alerts Of Son
     * @param son
     * @param alert
     * @return
     */
    @DELETE("children/{son}/alerts/{alert}")
    Observable<APIResponse<String>> deleteAlertOfSon(@Path("son") String son,
                                                     @Path("alert") String alert);

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
    Observable<APIResponse<List<AlertDTO>>> getSelfAlerts();

}
