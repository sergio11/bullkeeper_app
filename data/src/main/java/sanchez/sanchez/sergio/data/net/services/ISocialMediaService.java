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
     * Get All Social Media By Kid Id
     * @param id
     * @return
     */
    @GET("children/{id}/social")
    Observable<APIResponse<List<SocialMediaDTO>>> getAllSocialMediaBySonId(@Path("id") final String id);


    /**
     * Save All Social Media
     * @param kid
     * @param socialMedias
     * @return
     */
    @POST("children/{kid}/social/save/all")
    Observable<APIResponse<List<SocialMediaDTO>>> saveAllSocialMedia(
            @Path("kid") final String kid, @Body final List<SaveSocialMediaDTO> socialMedias);

    /**
     * Delete Social Media
     * @param kid
     * @param idSocial
     * @return
     */
    @DELETE("{kid}/social/delete/{social}")
    Observable<APIResponse<SocialMediaDTO>> deleteSocialMedia(@Path("kid") final String kid,
                                                              @Path("social") final String idSocial);

}
