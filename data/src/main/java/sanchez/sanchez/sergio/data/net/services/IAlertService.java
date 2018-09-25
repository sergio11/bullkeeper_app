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
     * Clear Self Warning Alerts
     * @return
     */
    @DELETE("parents/self/alerts/warning")
    Observable<APIResponse<String>> clearSelfWarningAlerts();

    /**
     * Clear Self Information Alerts
     * @return
     */
    @DELETE("parents/self/alerts/info")
    Observable<APIResponse<String>> clearSelfInformationAlerts();


    /**
     * Clear Self Danger Alerts
     * @return
     */
    @DELETE("parents/self/alerts/danger")
    Observable<APIResponse<String>> clearSelfDangerAlerts();

    /**
     * Clear Self Success Alerts
     * @return
     */
    @DELETE("parents/self/alerts/success")
    Observable<APIResponse<String>> clearSelfSuccessAlerts();

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


    /**
     * Get Danger Alerts for self parent
     * @return
     */
    @GET("parents/self/alerts/danger")
    Observable<APIResponse<List<AlertDTO>>> getDangerAlertsForSelfParent(
            final @Query("count") String count, final @Query("last_minutes") String lastMinutes);

    /**
     * Get Information Alerts for self parent
     * @return
     */
    @GET("parents/self/alerts/info")
    Observable<APIResponse<List<AlertDTO>>> getInformationAlertsForSelfParent(
            final @Query("count") String count, final @Query("last_minutes") String lastMinutes);


    /**
     * Get success alerts for self parent
     * @return
     */
    @GET("parents/self/alerts/success")
    Observable<APIResponse<List<AlertDTO>>> getSuccessAlertsForSelfParent(
            final @Query("count") String count, final @Query("last_minutes") String lastMinutes);


    /**
     * Get warning alerts for self parent
     * @return
     */
    @GET("parents/self/alerts/warning")
    Observable<APIResponse<List<AlertDTO>>> getWarningAlertsForSelfParent(
            final @Query("count") String count, final @Query("last_minutes") String lastMinutes);

    /**
     * Get warning alerts for self parent
     * @return
     */
    @GET("children/{id}/alerts/warning")
    Observable<APIResponse<List<AlertDTO>>> getWarningAlertsOfSonForSelfParent(
            final @Path("id") String id, final @Query("count") String count, final @Query("last_minutes") String lastMinutes);

    /**
     * Get info alerts for self parent
     * @return
     */
    @GET("children/{id}/alerts/info")
    Observable<APIResponse<List<AlertDTO>>> getInfoAlertsOfSonForSelfParent(
            final @Query("count") String count, final @Query("last_minutes") String lastMinutes,
            final @Path("id") String id);


    /**
     * Get danger alerts of son for self parent
     * @return
     */
    @GET("children/{id}/alerts/danger")
    Observable<APIResponse<List<AlertDTO>>> getDangerAlertsOfSonForSelfParent(
            final @Path("id") String id, final @Query("count") String count, final @Query("last_minutes") String lastMinutes);


    /**
     * Get success alerts of son for self parent
     * @return
     */
    @GET("children/{id}/alerts/success")
    Observable<APIResponse<List<AlertDTO>>> getSuccessAlertsOfSonForSelfParent(
            final @Path("id") String id, final @Query("count") String count, final @Query("last_minutes") String lastMinutes);


    /**
     * Clear warning alerts of son for self parent
     * @return
     */
    @DELETE("children/{id}/alerts/warning")
    Observable<APIResponse<String>> clearWarningAlertsOfSonForSelfParent(final @Path("id") String id);

    /**
     * Delete info alerts for self parent
     * @return
     */
    @DELETE("children/{id}/alerts/info")
    Observable<APIResponse<String>> clearInfoAlertsOfSonForSelfParent(final @Path("id") String id);


    /**
     * Delete danger alerts for self parent
     * @return
     */
    @GET("children/{id}/alerts/danger")
    Observable<APIResponse<String>> clearDangerAlertsOfSonForSelfParent(final @Path("id") String id);


    /**
     * Delete success alerts for self parent
     * @return
     */
    @GET("children/{id}/alerts/success")
    Observable<APIResponse<String>> clearSuccessAlertsOfSonForSelfParent(final @Path("id") String id);
}
