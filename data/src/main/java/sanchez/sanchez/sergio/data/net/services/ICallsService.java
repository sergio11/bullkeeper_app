package sanchez.sanchez.sergio.data.net.services;

import java.util.List;
import io.reactivex.Observable;
import retrofit2.http.GET;
import retrofit2.http.Path;
import sanchez.sanchez.sergio.data.net.models.response.APIResponse;
import sanchez.sanchez.sergio.data.net.models.response.CallDetailDTO;

/**
 * Calls Service
 */
public interface ICallsService {

    /**
     * Get Call List
     * @param kid
     * @return
     */
    @GET("children/{kid}/terminal/{terminal}/calls")
    Observable<APIResponse<List<CallDetailDTO>>> getCallsList(
            final @Path("kid") String kid,
            final @Path("terminal") String terminal);


    /**
     * Get Call Detail
     * @param kid
     * @param terminal
     * @param call
     * @return
     */
    @GET("children/{kid}/terminal/{terminal}/calls/{call}")
    Observable<APIResponse<CallDetailDTO>> getCallDetail(
            final @Path("kid") String kid,
            final @Path("terminal") String terminal,
            final @Path("call") String call
    );
}
