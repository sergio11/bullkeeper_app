package sanchez.sanchez.sergio.data.repository;

import com.fernandocejas.arrow.checks.Preconditions;
import java.util.Collections;
import java.util.List;
import io.reactivex.Observable;
import sanchez.sanchez.sergio.data.mapper.AbstractDataMapper;
import sanchez.sanchez.sergio.data.net.models.response.CommentsStatisticsBySocialMediaDTO;
import sanchez.sanchez.sergio.data.net.models.response.DimensionsStatisticsDTO;
import sanchez.sanchez.sergio.data.net.models.response.SentimentAnalysisStatisticsDTO;
import sanchez.sanchez.sergio.data.net.models.response.SocialMediaActivityStatisticsDTO;
import sanchez.sanchez.sergio.data.net.models.response.SocialMediaLikesStatisticsDTO;
import sanchez.sanchez.sergio.data.net.models.response.SummaryMyKidResultDTO;
import sanchez.sanchez.sergio.data.net.services.IAnalysisStatisticsService;
import sanchez.sanchez.sergio.domain.models.CommentsStatisticsBySocialMediaEntity;
import sanchez.sanchez.sergio.domain.models.DimensionsStatisticsEntity;
import sanchez.sanchez.sergio.domain.models.SentimentAnalysisStatisticsEntity;
import sanchez.sanchez.sergio.domain.models.SocialMediaActivityStatisticsEntity;
import sanchez.sanchez.sergio.domain.models.SocialMediaLikesStatisticsEntity;
import sanchez.sanchez.sergio.domain.models.SummaryMyKidResultEntity;
import sanchez.sanchez.sergio.domain.repository.IAnalysisStatisticsRepository;

/**
 * Analysis Statistics Repository
 */
public final class AnalysisStatisticsRepositoryImpl implements IAnalysisStatisticsRepository {

    private final IAnalysisStatisticsService analysisStatisticsService;
    private final AbstractDataMapper<DimensionsStatisticsDTO, DimensionsStatisticsEntity> dimensionsStatisticsEntityAbstractDataMapper;
    private final AbstractDataMapper<SocialMediaActivityStatisticsDTO, SocialMediaActivityStatisticsEntity> socialMediaStatisticsDataMapper;
    private final AbstractDataMapper<SentimentAnalysisStatisticsDTO, SentimentAnalysisStatisticsEntity> sentimentAnalysisStatisticsDataMapper;
    private final AbstractDataMapper<CommentsStatisticsBySocialMediaDTO, CommentsStatisticsBySocialMediaEntity>  commentsStatisticsDataMapper;
    private final AbstractDataMapper<SocialMediaLikesStatisticsDTO, SocialMediaLikesStatisticsEntity> socialMediaLikesStatisticsDataMapper;
    private final AbstractDataMapper<SummaryMyKidResultDTO, SummaryMyKidResultEntity> summaryMyKidResultEntityAbstractDataMapper;

    /**
     *
     * @param analysisStatisticsService
     * @param dimensionsStatisticsEntityAbstractDataMapper
     * @param socialMediaStatisticsDataMapper
     * @param sentimentAnalysisStatisticsDataMapper
     * @param commentsStatisticsDataMapper
     * @param socialMediaLikesStatisticsDataMapper
     * @param summaryMyKidResultEntityAbstractDataMapper
     */
    public AnalysisStatisticsRepositoryImpl(
            final IAnalysisStatisticsService analysisStatisticsService,
            final AbstractDataMapper<DimensionsStatisticsDTO, DimensionsStatisticsEntity> dimensionsStatisticsEntityAbstractDataMapper,
            final AbstractDataMapper<SocialMediaActivityStatisticsDTO, SocialMediaActivityStatisticsEntity> socialMediaStatisticsDataMapper,
            final AbstractDataMapper<SentimentAnalysisStatisticsDTO, SentimentAnalysisStatisticsEntity> sentimentAnalysisStatisticsDataMapper,
            final AbstractDataMapper<CommentsStatisticsBySocialMediaDTO, CommentsStatisticsBySocialMediaEntity>  commentsStatisticsDataMapper,
            final AbstractDataMapper<SocialMediaLikesStatisticsDTO, SocialMediaLikesStatisticsEntity> socialMediaLikesStatisticsDataMapper,
            final AbstractDataMapper<SummaryMyKidResultDTO, SummaryMyKidResultEntity> summaryMyKidResultEntityAbstractDataMapper) {
        this.analysisStatisticsService = analysisStatisticsService;
        this.dimensionsStatisticsEntityAbstractDataMapper = dimensionsStatisticsEntityAbstractDataMapper;
        this.socialMediaStatisticsDataMapper = socialMediaStatisticsDataMapper;
        this.sentimentAnalysisStatisticsDataMapper = sentimentAnalysisStatisticsDataMapper;
        this.commentsStatisticsDataMapper = commentsStatisticsDataMapper;
        this.socialMediaLikesStatisticsDataMapper = socialMediaLikesStatisticsDataMapper;
        this.summaryMyKidResultEntityAbstractDataMapper = summaryMyKidResultEntityAbstractDataMapper;
    }

    /**
     * Get Dimensions Statistics
     * @param identities
     * @param daysAgo
     * @return
     */
    @Override
    public Observable<DimensionsStatisticsEntity> getDimensionsStatistics(final List<String> identities, int daysAgo) {
        Preconditions.checkNotNull(identities, "Identities can not be null");
        Preconditions.checkState(!identities.isEmpty(), "Identities can not be empty");

        return analysisStatisticsService.getDimensionsStatistics(identities, daysAgo)
                .map(response -> response != null && response.getData() != null ?
                        response.getData() : null)
                .map(dimensionsStatisticsEntityAbstractDataMapper::transform);
    }

    /**
     * Get Dimensions Statistics
     * @param id
     * @param daysAgo
     * @return
     */
    @Override
    public Observable<DimensionsStatisticsEntity> getDimensionsStatistics(final String id, int daysAgo) {
        Preconditions.checkNotNull(id, "Id can not be null");
        Preconditions.checkState(!id.isEmpty(), "Id can not be empty");
        return getDimensionsStatistics(Collections.singletonList(id), daysAgo);
    }

    /**
     * Get Social Media Activity Statistics
     * @param identities
     * @param daysAgo
     * @return
     */
    @Override
    public Observable<SocialMediaActivityStatisticsEntity> getSocialMediaActivityStatistics(
            final List<String> identities, int daysAgo) {
        Preconditions.checkNotNull(identities, "Identities can not be null");
        return analysisStatisticsService.getSocialMediaActivityStatistics(identities, daysAgo)
                .map(response -> response != null && response.getData() != null ?
                        response.getData() : null)
                .map(socialMediaStatisticsDataMapper::transform);
    }

    /**
     * Get Social Media Activity Statistics
     * @param id
     * @param daysAgo
     * @return
     */
    @Override
    public Observable<SocialMediaActivityStatisticsEntity> getSocialMediaActivityStatistics(
            final String id, int daysAgo) {
        Preconditions.checkNotNull(id, "Id can not be null");
        Preconditions.checkState(!id.isEmpty(), "Id can not be empty");
        return getSocialMediaActivityStatistics(Collections.singletonList(id), daysAgo);
    }

    /**
     * Get Sentiment Analysis Statistics
     * @param identities
     * @param daysAgo
     * @return
     */
    @Override
    public Observable<SentimentAnalysisStatisticsEntity> getSentimentAnalysisStatistics(final List<String> identities, int daysAgo) {
        Preconditions.checkNotNull(identities,
                "Identities can not be null");
        return analysisStatisticsService.getSentimentAnalysisStatistics(identities, daysAgo)
                .map(response -> response != null &&
                        response.getData() != null ? response.getData(): null)
                .map(sentimentAnalysisStatisticsDataMapper::transform);
    }

    /**
     * Get Sentiment Analysis Statistics
     * @param id
     * @param daysAgo
     * @return
     */
    @Override
    public Observable<SentimentAnalysisStatisticsEntity> getSentimentAnalysisStatistics(
            final String id, int daysAgo) {
        Preconditions.checkNotNull(id, "Id can not be null");
        Preconditions.checkState(!id.isEmpty(), "Id can not be empty");
        return getSentimentAnalysisStatistics(Collections.singletonList(id), daysAgo);
    }

    /**
     * Get Comments Statistics By Social Media
     * @param identities
     * @param daysAgo
     * @return
     */
    @Override
    public Observable<CommentsStatisticsBySocialMediaEntity> getCommentsStatisticsBySocialMedia(List<String> identities, int daysAgo) {
        Preconditions.checkNotNull(identities, "Identities can not be null");
        Preconditions.checkState(!identities.isEmpty(), "Identities can not be empty");

        return analysisStatisticsService.getCommentsStatisticsBySocialMedia(identities, daysAgo)
                .map(response -> response != null && response.getData() != null ?
                        response.getData() : null)
                .map(commentsStatisticsDataMapper::transform);
    }

    /**
     * Get Comments Statistics By Social Media
     * @param id
     * @param daysAgo
     * @return
     */
    @Override
    public Observable<CommentsStatisticsBySocialMediaEntity> getCommentsStatisticsBySocialMedia(String id, int daysAgo) {
        Preconditions.checkNotNull(id, "Id can not be null");
        Preconditions.checkState(!id.isEmpty(), "Id can not be empty");
        return getCommentsStatisticsBySocialMedia(Collections.singletonList(id), daysAgo);
    }

    /**
     * Get Social Media Likes Statistics
     * @param identities
     * @param daysAgo
     * @return
     */
    @Override
    public Observable<SocialMediaLikesStatisticsEntity> getSocialMediaLikesStatistics(List<String> identities, int daysAgo) {
        Preconditions.checkNotNull(identities, "Identities can not be null");
        Preconditions.checkState(!identities.isEmpty(), "Identities can not be empty");

        return analysisStatisticsService.getSocialMediaLikesStatistics(identities, daysAgo)
                .map(response -> response != null && response.getData() != null ?
                        response.getData() : null)
                .map(socialMediaLikesStatisticsDataMapper::transform);
    }

    /**
     * Get Social Media Likes Statistics
     * @param id
     * @param daysAgo
     * @return
     */
    @Override
    public Observable<SocialMediaLikesStatisticsEntity> getSocialMediaLikesStatistics(String id, int daysAgo) {
        Preconditions.checkNotNull(id, "Id can not be null");
        Preconditions.checkState(!id.isEmpty(), "Id can not be empty");
        return getSocialMediaLikesStatistics(Collections.singletonList(id), daysAgo);
    }

    /**
     * Get Statistics Summary
     * @return
     */
    @Override
    public Observable<List<SummaryMyKidResultEntity>> getStatisticsSummary() {
        return analysisStatisticsService.getStatisticsSummary()
                .map(response -> response != null && response.getData() != null ?
                        response.getData(): null)
                .map(summaryMyKidResultEntityAbstractDataMapper::transform);
    }
}
