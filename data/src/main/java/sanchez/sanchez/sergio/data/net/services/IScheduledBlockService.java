package sanchez.sanchez.sergio.data.net.services;

import java.util.List;
import io.reactivex.Observable;
import retrofit2.http.Body;
import retrofit2.http.DELETE;
import retrofit2.http.GET;
import retrofit2.http.POST;
import retrofit2.http.Path;
import sanchez.sanchez.sergio.data.net.models.request.SaveScheduledBlockDTO;
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
    @GET("children/{id}/scheduled-blocks")
    Observable<APIResponse<List<ScheduledBlockDTO>>> getScheduledBlockByChildId(@Path("id") final String id);

    /**
     * Delete All Scheduled Block for child
     * @param id
     * @return
     */
    @DELETE("children/{id}/scheduled-blocks")
    Observable<APIResponse<String>> deleteScheduledBlockByChildId(@Path("id") final String id);

    /**
     * Delete Scheduled Block
     * @param childId
     * @param blockId
     * @return
     */
    @DELETE("children/{childId}/scheduled-blocks/{blockId}")
    Observable<APIResponse<String>> deleteScheduledBlock(@Path("childId") final String childId,  @Path("blockId") final String blockId);

    /**
     * Save Scheduled Block
     * @param saveScheduledBlockDTO
     * @return
     */
    @POST("children/{id}/scheduled-blocks")
    Observable<APIResponse<ScheduledBlockDTO>> saveScheduledBlock(@Path("id") final String childId, final @Body SaveScheduledBlockDTO saveScheduledBlockDTO);

}
