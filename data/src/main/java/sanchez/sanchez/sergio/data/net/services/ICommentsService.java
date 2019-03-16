package sanchez.sanchez.sergio.data.net.services;

import java.util.List;

import io.reactivex.Observable;
import retrofit2.http.GET;
import retrofit2.http.Path;
import retrofit2.http.Query;
import sanchez.sanchez.sergio.data.net.models.response.APIResponse;
import sanchez.sanchez.sergio.data.net.models.response.CommentDTO;

/**
 * Comments Service
 */
public interface ICommentsService {


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
    @GET("comments/search")
    Observable<APIResponse<List<CommentDTO>>> getComments(
            @Query("children") final String[] ids,
            @Query("author") final String author,
            @Query("social_media") final String[] socialMedias,
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
    @GET("comments/search")
    Observable<APIResponse<List<CommentDTO>>> getComments(
            @Query("children") final String[] ids,
            @Query("social_media") final String[] socialMedias,
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
    @GET("comments/search")
    Observable<APIResponse<List<CommentDTO>>> getComments(
            @Query("children") final String[] ids,
            @Query("social_media") final String[] socialMedias,
            @Query("days_ago") final int daysAgo
    );

    /**
     * Get Comments
     * @param ids
     * @param daysAgo
     * @return
     */
    @GET("comments/search")
    Observable<APIResponse<List<CommentDTO>>> getComments(
            @Query("children") final String[] ids,
            @Query("days_ago") final int daysAgo
    );


    /**
     * Get Comment By Id
     * @param id
     * @return
     */
    @GET("comments/{id}")
    Observable<APIResponse<CommentDTO>> getCommentById(@Path("id") final String id);

}
