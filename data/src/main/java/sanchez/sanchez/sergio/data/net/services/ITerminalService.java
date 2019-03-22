package sanchez.sanchez.sergio.data.net.services;

import java.util.List;

import io.reactivex.Observable;
import retrofit2.http.Body;
import retrofit2.http.DELETE;
import retrofit2.http.GET;
import retrofit2.http.POST;
import retrofit2.http.Path;
import sanchez.sanchez.sergio.data.net.models.request.AddMessageDTO;
import sanchez.sanchez.sergio.data.net.models.request.SaveTerminalHeartBeatConfigurationDTO;
import sanchez.sanchez.sergio.data.net.models.response.APIResponse;
import sanchez.sanchez.sergio.data.net.models.response.TerminalDTO;
import sanchez.sanchez.sergio.data.net.models.response.TerminalDetailDTO;
import sanchez.sanchez.sergio.data.net.models.response.TerminalHeartbeatDTO;

/**
 * Terminal Service
 * DELETE /api/v1/children/{kid}/terminal DELETE_ALL_TERMINAL_FOR_KID
 * POST /api/v1/children/{kid}/terminal/screen/lock LOCK_SCREEN_FOR_ALL_KID_TERMINAL
 * POST /api/v1/children/{kid}/terminal/screen/unlock UNLOCK_SCREEN_FOR_ALL_KID_TERMINAL
 */
public interface ITerminalService {


    /**
     * Get Monitored Terminals
     * @param kid
     * @return
     */
    @GET("children/{kid}/terminal")
    Observable<APIResponse<List<TerminalDTO>>> getMonitoredTerminals(
            final @Path("kid") String kid);

    /**
     * Get Terminal Detail
     * @param kid
     * @param terminal
     * @return
     */
    @GET("children/{kid}/terminal/{terminal}")
    Observable<APIResponse<TerminalDetailDTO>> getTerminalDetail(
            final @Path("kid") String kid, final @Path("terminal") String terminal);


    /**
     * Delete Terminal
     * @param kid
     * @param terminal
     * @return
     */
    @DELETE("children/{kid}/terminal/{terminal}")
    Observable<APIResponse<String>> deleteTerminal(final @Path("kid") String kid,
                                                   final @Path("terminal") String terminal);

    /**
     * Delete All Terminal for kid
     * @param kid
     * @return
     */
    @DELETE("children/{kid}/terminal")
    Observable<APIResponse<String>> deleteAllTerminalForKid(
            final @Path("kid") String kid);


    /**
     * Disable Bed Time in the terminal
     * @param kid
     * @param terminal
     * @return
     */
    @POST("children/{kid}/terminal/{terminal}/bedtime/disable")
    Observable<APIResponse<String>>  disableBedTimeInTheTerminal(
            final @Path("kid") String kid,
            final @Path("terminal") String terminal);


    /**
     * Enable Bed Time in the terminal
     * @param kid
     * @param terminal
     * @return
     */
    @POST("children/{kid}/terminal/{terminal}/bedtime/enable")
    Observable<APIResponse<String>>  enableBedTimeInTheTerminal(
            final @Path("kid") String kid,
            final @Path("terminal") String terminal);

    /**
     * Lock Camera in the terminal
     * @param kid
     * @param terminal
     * @return
     */
    @POST("children/{kid}/terminal/{terminal}/camera/lock")
    Observable<APIResponse<String>>  lockCameraInTheTerminal(
            final @Path("kid") String kid,
            final @Path("terminal") String terminal);


    /**
     * Unlock Camera in the terminal
     * @param kid
     * @param terminal
     * @return
     */
    @POST("children/{kid}/terminal/{terminal}/camera/unlock")
    Observable<APIResponse<String>> unlockCameraInTheTerminal(
            final @Path("kid") String kid,
            final @Path("terminal") String terminal);


    /**
     * Lock Screen In The Terminal
     * @param kid
     * @param terminal
     * @return
     */
    @POST("children/{kid}/terminal/{terminal}/screen/lock")
    Observable<APIResponse<String>> lockScreenInTheTerminal(
            final @Path("kid") String kid,
            final @Path("terminal") String terminal);

    /**
     * Lock Screen For All Kid Terminal
     * @param kid
     * @return
     */
    @POST("children/{kid}/terminal/screen/lock")
    Observable<APIResponse<String>> lockScreenForAllKidTerminal(
            final @Path("kid") String kid);


    /**
     * Lock Screen In The Terminal
     * @param kid
     * @param terminal
     * @return
     */
    @POST("children/{kid}/terminal/{terminal}/screen/unlock")
    Observable<APIResponse<String>> unLockScreenInTheTerminal(
            final @Path("kid") String kid,
            final @Path("terminal") String terminal);

    /**
     * Lock Screen For All Kid Terminal
     * @param kid
     * @return
     */
    @POST("children/{kid}/terminal/screen/unlock")
    Observable<APIResponse<String>> unLockScreenForAllKidTerminal(
            final @Path("kid") String kid);


    /**
     * Enable Settings Screen In The Terminal
     * @param kid
     * @param terminal
     * @return
     */
    @POST("children/{kid}/terminal/{terminal}/settings/enable")
    Observable<APIResponse<String>> enableSettingsScreenInTheTerminal(
            final @Path("kid") String kid,
            final @Path("terminal") String terminal);


    /**
     * Disable Settings Screen In The Terminal
     * @param kid
     * @param terminal
     * @return
     */
    @POST("children/{kid}/terminal/{terminal}/settings/disable")
    Observable<APIResponse<String>> disableSettingsScreenInTheTerminal(
            final @Path("kid") String kid,
            final @Path("terminal") String terminal);

    /**
     * Save Terminal HeartBeat Configuration
     * @param kid
     * @param terminal
     * @return
     */
    @POST("children/{kid}/terminal/{terminal}/heartbeat/configuration")
    Observable<APIResponse<TerminalHeartbeatDTO>> saveTerminalHeartbeatConfiguration(
            final @Path("kid") String kid,
            final @Path("terminal") String terminal,
            @Body final SaveTerminalHeartBeatConfigurationDTO terminalHeartBeatConfiguration);

}
