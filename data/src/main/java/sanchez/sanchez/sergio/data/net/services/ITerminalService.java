package sanchez.sanchez.sergio.data.net.services;

import java.util.List;

import io.reactivex.Observable;
import retrofit2.http.GET;
import retrofit2.http.Path;
import sanchez.sanchez.sergio.data.net.models.response.APIResponse;
import sanchez.sanchez.sergio.data.net.models.response.TerminalDTO;

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
}
