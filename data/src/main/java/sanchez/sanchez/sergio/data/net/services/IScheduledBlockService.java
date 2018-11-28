package sanchez.sanchez.sergio.data.net.services;

import java.util.List;
import io.reactivex.Observable;
import okhttp3.MultipartBody;
import retrofit2.http.Body;
import retrofit2.http.DELETE;
import retrofit2.http.GET;
import retrofit2.http.Multipart;
import retrofit2.http.POST;
import retrofit2.http.Part;
import retrofit2.http.Path;
import sanchez.sanchez.sergio.data.net.models.request.SaveScheduledBlockDTO;
import sanchez.sanchez.sergio.data.net.models.request.SaveScheduledBlockStatusDTO;
import sanchez.sanchez.sergio.data.net.models.response.APIResponse;
import sanchez.sanchez.sergio.data.net.models.response.ImageDTO;
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
     * @param kid
     * @param blockId
     * @return
     */
    @DELETE("children/{kid}/scheduled-blocks/{blockId}")
    Observable<APIResponse<String>> deleteScheduledBlock(@Path("kid") final String kid,
                                                         @Path("blockId") final String blockId);

    /**
     * Save Scheduled Block
     * @param saveScheduledBlockDTO
     * @return
     */
    @POST("children/{id}/scheduled-blocks")
    Observable<APIResponse<ScheduledBlockDTO>> saveScheduledBlock(@Path("id") final String childId, final @Body SaveScheduledBlockDTO saveScheduledBlockDTO);

    /**
     * Save Scheduled Block Status
     * @param kid
     * @param saveScheduledStatus
     * @return
     */
    @POST("children/{kid}/scheduled-blocks/status")
    Observable<APIResponse<String>> saveScheduledBlockStatus(@Path("kid") final String kid,
                                                                        final @Body List<SaveScheduledBlockStatusDTO> saveScheduledStatus);


    /**
     * Upload Scheduled Block
     * @param kid
     * @param blockId
     * @param image
     * @return
     */
    @Multipart
    @POST("children/{kid}/scheduled-blocks/{block}/image")
    Observable<APIResponse<ImageDTO>> uploadScheduledBlockImage(
            @Path("kid") final String kid,
            @Path("block") final String blockId,
            @Part final MultipartBody.Part image);

}
