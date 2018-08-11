package sanchez.sanchez.sergio.data.services;


import java.util.List;
import java.util.Map;
import io.reactivex.Observable;
import retrofit2.http.Body;
import retrofit2.http.DELETE;
import retrofit2.http.FormUrlEncoded;
import retrofit2.http.GET;
import retrofit2.http.POST;
import retrofit2.http.Path;
import retrofit2.http.Query;
import sanchez.sanchez.sergio.data.models.request.SaveSocialMediaDTO;
import sanchez.sanchez.sergio.data.models.response.APIResponse;
import sanchez.sanchez.sergio.data.models.response.AlertsStatisticsDTO;
import sanchez.sanchez.sergio.data.models.response.CommentDTO;
import sanchez.sanchez.sergio.data.models.response.CommentsStatisticsDTO;
import sanchez.sanchez.sergio.data.models.response.CommunitiesStatisticsDTO;
import sanchez.sanchez.sergio.data.models.response.DimensionsStatisticsDTO;
import sanchez.sanchez.sergio.data.models.response.MostActiveFriendsDTO;
import sanchez.sanchez.sergio.data.models.response.NewFriendsDTO;
import sanchez.sanchez.sergio.data.models.response.SentimentAnalysisStatisticsDTO;
import sanchez.sanchez.sergio.data.models.response.SocialMediaActivityStatisticsDTO;
import sanchez.sanchez.sergio.data.models.response.SocialMediaDTO;
import sanchez.sanchez.sergio.data.models.response.SocialMediaLikesStatisticsDTO;
import sanchez.sanchez.sergio.data.models.response.SonDTO;
import sanchez.sanchez.sergio.domain.models.DimensionCategoryEnum;
import sanchez.sanchez.sergio.domain.models.SocialMediaTypeEnum;

public interface IChildrenService {

    /**
     * Get Son By Id
     * @param id
     * @return
     */
    @GET("/children/:id")
    Observable<APIResponse<SonDTO>> getSonById(@Path("id") final String id);


    /**
     * Get All Social Media By Son Id
     * @param id
     * @return
     */
    @GET("/children/:id/social")
    Observable<APIResponse<List<SocialMediaDTO>>> getAllSocialMediaBySonId(@Path("id") final String id);

    /**
     * Get Invalid Social Media By Son Id
     * @param id
     * @return
     */
    @GET("/children/:id/social/invalid")
    Observable<APIResponse<List<SocialMediaDTO>>> getInvalidSocialMediaBySonId(@Path("id") final String id);

    /**
     * Save Social Media
     * @param socialMedia
     * @return
     */
    @FormUrlEncoded
    @POST("/children/social/save")
    Observable<APIResponse<SocialMediaDTO>> saveSocialMedia(
            @Body final SaveSocialMediaDTO socialMedia);


    /**
     * Save All Social Media
     * @param idSon
     * @param socialMedias
     * @return
     */
    @FormUrlEncoded
    @POST("/children/:id/social/save/all")
    Observable<APIResponse<List<SocialMediaDTO>>> saveAllSocialMedia(
            @Path("id") final String idSon, @Body final List<SaveSocialMediaDTO> socialMedias);


    /**
     * Delete Social Media
     * @param son
     * @param idSocial
     * @return
     */
    @DELETE("/:son/social/delete/:social")
    Observable<APIResponse<SocialMediaDTO>> deleteSocialMedia(@Path("son") final String son,
                                                              @Path("social") final String idSocial);

    //Observable<APIResponse<ImageDTO>> UploadProfileImage(String id, Stream stream);

    /**
     * Delete Son By Id
     * @param id
     * @return
     */
    @DELETE("/children/:id")
    Observable<APIResponse<String>> deleteSonById(@Path("id") final String id);

    /**
     * Get Social Media Activity Statistics
     * @param id
     * @param daysAgo
     * @return
     */
    @GET("/children/:id/statistics/social-activity")
    Observable<APIResponse<SocialMediaActivityStatisticsDTO>> getSocialMediaActivityStatistics(
            @Path("id") final String id, @Query("days_ago") final Integer daysAgo);

    /**
     * Get Sentiment Analysis Statistics
     * @param id
     * @param daysAgo
     * @return
     */
    @GET("/children/:id/statistics/sentiment-analysis")
    Observable<APIResponse<SentimentAnalysisStatisticsDTO>> getSentimentAnalysisStatistics(
            @Path("id") final String id, @Query("days_ago") final Integer daysAgo);


    /**
     * Get Communities Statistics
     * @param id
     * @param daysAgo
     * @return
     */
    @GET("/children/:id/statistics/communities")
    Observable<APIResponse<CommunitiesStatisticsDTO>> getCommunitiesStatistics(
            @Path("id") final String id, @Query("days_ago") final Integer daysAgo);


    /**
     * Get Dimensions Statistics
     * @param id
     * @param daysAgo
     * @return
     */
    @GET("/children/:id/statistics/dimensions")
    Observable<APIResponse<DimensionsStatisticsDTO>> getDimensionsStatistics(@Path("id") final String id,
                                                                             @Query("days_ago") final Integer daysAgo);

    /**
     * Get Comments Statistics
     * @param ids
     * @param daysAgo
     * @return
     */
    @GET("/comments/comments-extracted")
    Observable<APIResponse<CommentsStatisticsDTO>> getCommentsStatistics(@Query("identities") final String[] ids,
                                                                         @Query("days_ago") final Integer daysAgo);

    /**
     * Get Social Media Likes Statistics
     * @param ids
     * @param daysAgo
     * @return
     */
    @GET("/comments/social-media-Likes")
    Observable<APIResponse<SocialMediaLikesStatisticsDTO>> getSocialMediaLikesStatistics(@Query("identities") final String[] ids,
                                                                                         @Query("days_ago") final Integer daysAgo);

    /**
     * Get Alerts Statistics
     * @param ids
     * @param daysAgo
     * @return
     */
    @GET("/alerts/statistics/alerts")
    Observable<APIResponse<AlertsStatisticsDTO>> getAlertsStatistics(@Query("identities") final String[] ids,
                                                                     @Query("days_ago") final Integer daysAgo);

    /**
     * Get Most Active Friends
     * @param ids
     * @param daysAgo
     * @return
     */
    @GET("/comments/most-active-friends")
    Observable<APIResponse<MostActiveFriendsDTO>> getMostActiveFriends(@Query("identities") final String[] ids,
                                                                       @Query("days_ago") final Integer daysAgo);

    /**
     * Get New Friends
     * @param ids
     * @param daysAgo
     * @return
     */
    @GET("/comments/new-friends")
    Observable<APIResponse<NewFriendsDTO>> getNewFriends(@Query("identities") final String[] ids,
                                                         @Query("days_ago") final Integer daysAgo);


    /**
     * Get Comments By Son
     * @param sonId
     * @param authorId
     * @param daysAgo
     * @param SocialMedia
     * @param dimensions
     * @return
     */
    @GET("/children/:id/comments")
    Observable<APIResponse<List<CommentDTO>>> getCommentsBySon(@Path("id") final String sonId, @Query("author") final String authorId,
                                                                @Query("days_ago") final Integer daysAgo, @Query("social_media") final List<SocialMediaTypeEnum> SocialMedia,
                                                                final Map<DimensionCategoryEnum, String> dimensions);
}
