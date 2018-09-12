package sanchez.sanchez.sergio.data.net.services;

import java.util.List;
import io.reactivex.Observable;
import retrofit2.http.Body;
import retrofit2.http.DELETE;
import retrofit2.http.GET;
import retrofit2.http.POST;
import retrofit2.http.Path;
import sanchez.sanchez.sergio.data.net.models.request.SaveSocialMediaDTO;
import sanchez.sanchez.sergio.data.net.models.response.APIResponse;
import sanchez.sanchez.sergio.data.net.models.response.SocialMediaDTO;

/**
 * Social Media Service
 */
public interface ISocialMediaService {

    /**
     * Get All Social Media By Son Id
     * @param id
     * @return
     */
    @GET("children/{id}/social")
    Observable<APIResponse<List<SocialMediaDTO>>> getAllSocialMediaBySonId(@Path("id") final String id);


    /**
     * Save All Social Media
     * @param idSon
     * @param socialMedias
     * @return
     */
    @POST("children/{id}/social/save/all")
    Observable<APIResponse<List<SocialMediaDTO>>> saveAllSocialMedia(
            @Path("id") final String idSon, @Body final List<SaveSocialMediaDTO> socialMedias);

    /**
     * Delete Social Media
     * @param son
     * @param idSocial
     * @return
     */
    @DELETE("{son}/social/delete/{social}")
    Observable<APIResponse<SocialMediaDTO>> deleteSocialMedia(@Path("son") final String son,
                                                              @Path("social") final String idSocial);

}
