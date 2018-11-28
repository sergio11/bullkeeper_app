package sanchez.sanchez.sergio.data.net.services;

import java.util.List;

import io.reactivex.Observable;
import retrofit2.http.DELETE;
import retrofit2.http.GET;
import retrofit2.http.Path;
import sanchez.sanchez.sergio.data.net.models.response.APIResponse;
import sanchez.sanchez.sergio.data.net.models.response.TerminalDTO;
import sanchez.sanchez.sergio.data.net.models.response.TerminalDetailDTO;

/**
 * Terminal Service
 */
public interface ITerminalService {


    /**
     * Get Monitored Terminals
     * @param kid
     * @return
     */
    @GET("children/{kid}/terminal")
    Observable<APIResponse<List<TerminalDTO>>> getMonitoredTerminals(final @Path("kid") String kid);

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

}
