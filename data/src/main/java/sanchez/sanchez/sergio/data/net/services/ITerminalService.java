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
     * @param childId
     * @return
     */
    @GET("children/{son}/terminal")
    Observable<APIResponse<List<TerminalDTO>>> getMonitoredTerminals(final @Path("son") String childId);

    /**
     * Get Terminal Detail
     * @param childId
     * @param terminal
     * @return
     */
    @GET("children/{son}/terminal/{terminal}")
    Observable<APIResponse<TerminalDetailDTO>> getTerminalDetail(
            final @Path("son") String childId, final @Path("terminal") String terminal);


    /**
     * Delete Terminal
     * @param childId
     * @param terminal
     * @return
     */
    @DELETE("children/{son}/terminal/{terminal}")
    Observable<APIResponse<String>> deleteTerminal(final @Path("son") String childId,
                                                   final @Path("terminal") String terminal);

}
