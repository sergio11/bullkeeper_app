package sanchez.sanchez.sergio.data.net.services;

import java.util.List;
import io.reactivex.Observable;
import retrofit2.http.GET;
import retrofit2.http.Query;
import sanchez.sanchez.sergio.data.net.models.response.APIResponse;
import sanchez.sanchez.sergio.data.net.models.response.CommentsStatisticsBySocialMediaDTO;
import sanchez.sanchez.sergio.data.net.models.response.DimensionsStatisticsDTO;
import sanchez.sanchez.sergio.data.net.models.response.SentimentAnalysisStatisticsDTO;
import sanchez.sanchez.sergio.data.net.models.response.SocialMediaActivityStatisticsDTO;
import sanchez.sanchez.sergio.data.net.models.response.SocialMediaLikesStatisticsDTO;

/**
 * Analysis Statistics Service
 * GET /api/v1/analysis-statistics/comments-extracted GET_COMMENTS_EXTRACTED
 * GET /api/v1/analysis-statistics/comments-extracted-by-social-media GET_COMMENTS_EXTRACTED_BY_SOCIAL_MEDIA
 * GET /api/v1/analysis-statistics/communities COMMUNITIES_STATISTICS
 * GET /api/v1/analysis-statistics/dimensions FOUR_DIMENSIONS_STATISTICS
 * GET /api/v1/analysis-statistics/sentiment-analysis SENTIMENT_ANALYSIS_STATISTICS
 * GET /api/v1/analysis-statistics/social-activity SOCIAL_MEDIA_ACTIVITY_STATISTICS
 * GET /api/v1/analysis-statistics/social-media-Likes SOCIAL_MEDIA_LIKES
 */
public interface IAnalysisStatisticsService {

    /**
     *
     * Get Social Media Activity Statistics
     * @param identities
     * @param daysAgo
     * @return
     */
    @GET("analysis-statistics/social-activity")
    Observable<APIResponse<SocialMediaActivityStatisticsDTO>> getSocialMediaActivityStatistics(
            @Query("identities") final List<String> identities,
            @Query("days_ago") final Integer daysAgo);

    /**
     * Get Sentiment Analysis Statistics
     * @param identities
     * @param daysAgo
     * @return
     */
    @GET("analysis-statistics/sentiment-analysis")
    Observable<APIResponse<SentimentAnalysisStatisticsDTO>> getSentimentAnalysisStatistics(
            @Query("identities") final List<String> identities,
            @Query("days_ago") final Integer daysAgo);


    /**
     * Get Dimensions Statistics
     * @param identities
     * @param daysAgo
     * @return
     */
    @GET("analysis-statistics/dimensions")
    Observable<APIResponse<DimensionsStatisticsDTO>> getDimensionsStatistics(
            @Query("identities") final List<String> identities,
            @Query("days_ago") final Integer daysAgo);


    /**
     * Get Social Media Likes Statistics
     * @param identities
     * @param daysAgo
     * @return
     */
    @GET("analysis-statistics/social-media-Likes")
    Observable<APIResponse<SocialMediaLikesStatisticsDTO>> getSocialMediaLikesStatistics(
            @Query("identities") final List<String> identities, @Query("days_ago") final Integer daysAgo);

    /**
     * Get Comments Statistics By Social Media
     * @param identities
     * @param daysAgo
     * @return
     */
    @GET("analysis-statistics/comments-extracted-by-social-media")
    Observable<APIResponse<CommentsStatisticsBySocialMediaDTO>> getCommentsStatisticsBySocialMedia(
            @Query("identities") final List<String> identities,
            @Query("days_ago") final Integer daysAgo
    );
}
