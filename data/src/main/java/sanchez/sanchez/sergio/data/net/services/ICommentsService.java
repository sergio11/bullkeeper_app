package sanchez.sanchez.sergio.data.net.services;

import java.util.List;

import io.reactivex.Observable;
import retrofit2.http.GET;
import retrofit2.http.Query;
import sanchez.sanchez.sergio.data.net.models.response.APIResponse;
import sanchez.sanchez.sergio.data.net.models.response.CommentDTO;
import sanchez.sanchez.sergio.data.net.models.response.CommentsStatisticsBySocialMediaDTO;
import sanchez.sanchez.sergio.data.net.models.response.CommentsStatisticsDTO;
import sanchez.sanchez.sergio.data.net.models.response.MostActiveFriendsDTO;
import sanchez.sanchez.sergio.data.net.models.response.NewFriendsDTO;
import sanchez.sanchez.sergio.data.net.models.response.SocialMediaLikesStatisticsDTO;

/**
 * Comments Service
 */
public interface ICommentsService {


    /**
     * Get Comments Statistics
     * @param ids
     * @param daysAgo
     * @return
     */
    @GET("comments/comments-extracted")
    Observable<APIResponse<CommentsStatisticsDTO>> getCommentsStatistics(@Query("identities") final String[] ids,
                                                                         @Query("days_ago") final Integer daysAgo);

    /**
     * Get Social Media Likes Statistics
     * @param ids
     * @param daysAgo
     * @return
     */
    @GET("comments/social-media-Likes")
    Observable<APIResponse<SocialMediaLikesStatisticsDTO>> getSocialMediaLikesStatistics(@Query("identities") final String[] ids,
                                                                                         @Query("days_ago") final Integer daysAgo);


    /**
     * Get Most Active Friends
     * @param ids
     * @param daysAgo
     * @return
     */
    @GET("comments/most-active-friends")
    Observable<APIResponse<MostActiveFriendsDTO>> getMostActiveFriends(@Query("identities") final String[] ids,
                                                                       @Query("days_ago") final Integer daysAgo);

    /**
     * Get New Friends
     * @param ids
     * @param daysAgo
     * @return
     */
    @GET("comments/new-friends")
    Observable<APIResponse<NewFriendsDTO>> getNewFriends(@Query("identities") final String[] ids,
                                                         @Query("days_ago") final Integer daysAgo);


    /**
     * Get Comments Statistics By Social Media
     * @param ids
     * @param daysAgo
     * @return
     */
    @GET("comments/comments-extracted-by-social-media")
    Observable<APIResponse<CommentsStatisticsBySocialMediaDTO>> getCommentsStatisticsBySocialMedia(
            @Query("identities") final String[] ids,
            @Query("days_ago") final Integer daysAgo
    );

    /**
     * Get Comments
     * @param ids
     * @param author
     * @param socialMedias
     * @param daysAgo
     * @param violence
     * @param drugs
     * @param bullying
     * @param adult
     * @return
     */
    @GET("comments/comments")
    Observable<APIResponse<List<CommentDTO>>> getComments(
            @Query("children") final String[] ids,
            @Query("author") final String author,
            @Query("social_medias") final String[] socialMedias,
            @Query("days_ago") final int daysAgo,
            @Query("violence") final String violence,
            @Query("drugs") final String drugs,
            @Query("bullying") final String bullying,
            @Query("adult") final String adult
    );

    /**
     * Get Comments
     * @param ids
     * @param socialMedias
     * @param daysAgo
     * @param violence
     * @param drugs
     * @param bullying
     * @param adult
     * @return
     */
    @GET("comments/comments")
    Observable<APIResponse<List<CommentDTO>>> getComments(
            @Query("children") final String[] ids,
            @Query("social_medias") final String[] socialMedias,
            @Query("days_ago") final int daysAgo,
            @Query("violence") final String violence,
            @Query("drugs") final String drugs,
            @Query("bullying") final String bullying,
            @Query("adult") final String adult
    );

    /**
     * Get Comments
     * @param ids
     * @param socialMedias
     * @param daysAgo
     * @return
     */
    @GET("comments/comments")
    Observable<APIResponse<List<CommentDTO>>> getComments(
            @Query("children") final String[] ids,
            @Query("social_medias") final String[] socialMedias,
            @Query("days_ago") final int daysAgo
    );

    /**
     * Get Comments
     * @param ids
     * @param daysAgo
     * @return
     */
    @GET("comments/comments")
    Observable<APIResponse<List<CommentDTO>>> getComments(
            @Query("children") final String[] ids,
            @Query("days_ago") final int daysAgo
    );

}
