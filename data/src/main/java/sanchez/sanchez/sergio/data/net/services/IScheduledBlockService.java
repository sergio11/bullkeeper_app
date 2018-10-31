package sanchez.sanchez.sergio.data.net.services;

import java.util.List;

import io.reactivex.Observable;
import retrofit2.http.DELETE;
import retrofit2.http.GET;
import retrofit2.http.Path;
import sanchez.sanchez.sergio.data.net.models.response.APIResponse;
import sanchez.sanchez.sergio.data.net.models.response.ScheduledBlockDTO;

/**
 * Scheduled Block Service
 */
public interface IScheduledBlockService {

    /**
     * Get All Scheduled Block for child
     * @param id
     * @return
     */
    @GET("children/{id}/scheduled-block")
    Observable<APIResponse<List<ScheduledBlockDTO>>> getScheduledBlockByChildId(@Path("id") final String id);

    /**
     * Delete Scheduled Block
     * @param childId
     * @param sblockId
     * @return
     */
    @DELETE("children/{childId}/scheduled-block/{sblockId}")
    Observable<APIResponse<String>> deleteScheduledBlock(@Path("childId") final String childId,
                                                               @Path("sblockId") final String sblockId);

}
