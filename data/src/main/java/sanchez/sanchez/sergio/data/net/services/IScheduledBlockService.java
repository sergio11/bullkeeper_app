package sanchez.sanchez.sergio.data.net.services;

import java.util.List;
import io.reactivex.Observable;
import retrofit2.http.Body;
import retrofit2.http.DELETE;
import retrofit2.http.GET;
import retrofit2.http.POST;
import retrofit2.http.Path;
import sanchez.sanchez.sergio.data.net.models.request.SaveScheduledBlockDTO;
import sanchez.sanchez.sergio.data.net.models.request.SaveScheduledBlockStatusDTO;
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
     * Get Scheduled Block Detail
     * @param id
     * @param block
     * @return
     */
    @GET("children/{id}/scheduled-blocks/{block}")
    Observable<APIResponse<ScheduledBlockDTO>> getScheduledBlockDetail(@Path("id") final String id,
                                                                             @Path("block") final String block);

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

    /**
     * Save Scheduled Block Status
     * @param childId
     * @param saveScheduledStatus
     * @return
     */
    @POST("children/{son}/scheduled-blocks/status")
    Observable<APIResponse<String>> saveScheduledBlockStatus(@Path("son") final String childId,
                                                                        final @Body List<SaveScheduledBlockStatusDTO> saveScheduledStatus);


}
