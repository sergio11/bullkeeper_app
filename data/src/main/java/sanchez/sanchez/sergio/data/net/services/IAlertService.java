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
    @DELETE("guardians/self/alerts")
    Observable<APIResponse<String>> clearSelfAlerts();

    /**
     * Clear Self Warning Alerts
     * @return
     */
    @DELETE("guardians/self/alerts/warning")
    Observable<APIResponse<String>> clearSelfWarningAlerts();

    /**
     * Clear Self Information Alerts
     * @return
     */
    @DELETE("guardians/self/alerts/info")
    Observable<APIResponse<String>> clearSelfInformationAlerts();


    /**
     * Clear Self Danger Alerts
     * @return
     */
    @DELETE("guardians/self/alerts/danger")
    Observable<APIResponse<String>> clearSelfDangerAlerts();

    /**
     * Clear Self Success Alerts
     * @return
     */
    @DELETE("guardians/self/alerts/success")
    Observable<APIResponse<String>> clearSelfSuccessAlerts();

    /**
     * Get Self Alerts
     * @return
     */
    @GET("guardians/self/alerts")
    Observable<APIResponse<List<AlertDTO>>> getSelfAlerts(
            @Query("count") String count,
            @Query("days_ago") String daysAgo,
            @Query("levels") String levelsCsv);

    /**
     * Get Self Alerts
     * @return
     */
    @GET("guardians/self/alerts")
    Observable<APIResponse<List<AlertDTO>>> getSelfAlerts();

    /**
     * Get Self Alerts Last
     * @return
     */
    @GET("guardians/self/alerts/last")
    Observable<APIResponse<AlertsPageDTO>> getSelfAlertsLast();

    /**
     * Get Self Alerts Last
     * @return
     */
    @GET("guardians/self/alerts/last")
    Observable<APIResponse<AlertsPageDTO>> getSelfAlertsLast(final @Query("count") String count,
                                                             final @Query("last_minutes") String lastMinutes,
                                                             final @Query("levels") String levels);


    /**
     * Get Danger Alerts for self guardians
     * @return
     */
    @GET("guardians/self/alerts/danger")
    Observable<APIResponse<List<AlertDTO>>> getDangerAlertsForSelfGuardian(
            final @Query("count") String count, final @Query("last_minutes") String lastMinutes);

    /**
     * Get Information Alerts for self guardians
     * @return
     */
    @GET("guardians/self/alerts/info")
    Observable<APIResponse<List<AlertDTO>>> getInformationAlertsForSelfGuardian(
            final @Query("count") String count, final @Query("last_minutes") String lastMinutes);


    /**
     * Get success alerts for self guardian
     * @return
     */
    @GET("guardians/self/alerts/success")
    Observable<APIResponse<List<AlertDTO>>> getSuccessAlertsForSelfGuardian(
            final @Query("count") String count, final @Query("last_minutes") String lastMinutes);


    /**
     * Get warning alerts for self guardian
     * @return
     */
    @GET("guardians/self/alerts/warning")
    Observable<APIResponse<List<AlertDTO>>> getWarningAlertsForSelfGuardian(
            final @Query("count") String count, final @Query("last_minutes") String lastMinutes);

    /**
     * Get warning alerts for self guardian
     * @return
     */
    @GET("children/{id}/alerts/warning")
    Observable<APIResponse<List<AlertDTO>>> getWarningAlertsOfSonForSelfGuardian(
            final @Path("id") String id, final @Query("count") String count, final @Query("last_minutes") String lastMinutes);

    /**
     * Get info alerts for self guardian
     * @return
     */
    @GET("children/{id}/alerts/info")
    Observable<APIResponse<List<AlertDTO>>> getInfoAlertsOfSonForSelfGuardian(
            final @Query("count") String count, final @Query("last_minutes") String lastMinutes,
            final @Path("id") String id);


    /**
     * Get danger alerts of son for self guardian
     * @return
     */
    @GET("children/{id}/alerts/danger")
    Observable<APIResponse<List<AlertDTO>>> getDangerAlertsOfSonForSelfGuardian(
            final @Path("id") String id, final @Query("count") String count, final @Query("last_minutes") String lastMinutes);


    /**
     * Get success alerts of son for self guardian
     * @return
     */
    @GET("children/{id}/alerts/success")
    Observable<APIResponse<List<AlertDTO>>> getSuccessAlertsOfSonForSelfGuardian(
            final @Path("id") String id, final @Query("count") String count, final @Query("last_minutes") String lastMinutes);


    /**
     * Clear warning alerts of son for self guardian
     * @return
     */
    @DELETE("children/{id}/alerts/warning")
    Observable<APIResponse<String>> clearWarningAlertsOfSonForSelfGuardian(final @Path("id") String id);

    /**
     * Delete info alerts for self guardian
     * @return
     */
    @DELETE("children/{id}/alerts/info")
    Observable<APIResponse<String>> clearInfoAlertsOfSonForSelfGuardian(final @Path("id") String id);


    /**
     * Delete danger alerts for self parent
     * @return
     */
    @GET("children/{id}/alerts/danger")
    Observable<APIResponse<String>> clearDangerAlertsOfSonForSelfGuardian(final @Path("id") String id);


    /**
     * Delete success alerts for self parent
     * @return
     */
    @GET("children/{id}/alerts/success")
    Observable<APIResponse<String>> clearSuccessAlertsOfSonForSelfGuardian(final @Path("id") String id);
}
