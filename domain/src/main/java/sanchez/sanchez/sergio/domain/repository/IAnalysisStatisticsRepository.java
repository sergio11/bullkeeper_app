package sanchez.sanchez.sergio.domain.repository;

import java.util.List;
import io.reactivex.Observable;
import sanchez.sanchez.sergio.domain.models.CommentsStatisticsBySocialMediaEntity;
import sanchez.sanchez.sergio.domain.models.DimensionsStatisticsEntity;
import sanchez.sanchez.sergio.domain.models.SentimentAnalysisStatisticsEntity;
import sanchez.sanchez.sergio.domain.models.SocialMediaActivityStatisticsEntity;
import sanchez.sanchez.sergio.domain.models.SocialMediaLikesStatisticsEntity;
import sanchez.sanchez.sergio.domain.models.SummaryMyKidResultEntity;

/**
 * Analysis Statistics Repository
 */
public interface IAnalysisStatisticsRepository {

    /**
     * Get Dimensions Statistics
     * @param identities
     * @param daysAgo
     * @return
     */
    Observable<DimensionsStatisticsEntity> getDimensionsStatistics(final List<String> identities, final int daysAgo);

    /**
     * Get Dimensions Statistics
     * @param id
     * @param daysAgo
     * @return
     */
    Observable<DimensionsStatisticsEntity> getDimensionsStatistics(final String id, final int daysAgo);

    /**
     * Get Social Media Activity Statistics
     * @param identities
     * @param daysAgo
     * @return
     */
    Observable<SocialMediaActivityStatisticsEntity> getSocialMediaActivityStatistics(
            final List<String> identities,
            final int daysAgo);

    /**
     * Get Social Media Activity Statistics
     * @param id
     * @param daysAgo
     * @return
     */
    Observable<SocialMediaActivityStatisticsEntity> getSocialMediaActivityStatistics(
            final String id,
            final int daysAgo);


    /**
     * Get Sentiment Analysis Statistics
     * @param identities
     * @param daysAgo
     * @return
     */
    Observable<SentimentAnalysisStatisticsEntity> getSentimentAnalysisStatistics(
            final List<String> identities,
            final int daysAgo);

    /**
     * Get Sentiment Analysis Statistics
     * @param id
     * @param daysAgo
     * @return
     */
    Observable<SentimentAnalysisStatisticsEntity> getSentimentAnalysisStatistics(
            final String id,
            final int daysAgo);


    /**
     * Get Comments Statistics By Social Media
     * @param identities
     * @param daysAgo
     * @return
     */
    Observable<CommentsStatisticsBySocialMediaEntity>
            getCommentsStatisticsBySocialMedia(final List<String> identities, final int daysAgo);

    /**
     * Get Comments Statistics By Social Media
     * @param id
     * @param daysAgo
     * @return
     */
    Observable<CommentsStatisticsBySocialMediaEntity>
        getCommentsStatisticsBySocialMedia(final String id, final int daysAgo);


    /**
     * Get Social Media Likes Statistics
     * @param identities
     * @param daysAgo
     * @return
     */
    Observable<SocialMediaLikesStatisticsEntity> getSocialMediaLikesStatistics(
            final List<String> identities,
            final int daysAgo);

    /**
     * Get Social Media Likes Statistics
     * @param id
     * @param daysAgo
     * @return
     */
    Observable<SocialMediaLikesStatisticsEntity> getSocialMediaLikesStatistics(
            final String id,
            final int daysAgo);



    /**
     * Get Statistics Summary
     * @return
     */
    Observable<List<SummaryMyKidResultEntity>> getStatisticsSummary();

}
