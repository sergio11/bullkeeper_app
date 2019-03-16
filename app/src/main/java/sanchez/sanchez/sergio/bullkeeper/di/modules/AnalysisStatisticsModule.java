package sanchez.sanchez.sergio.bullkeeper.di.modules;

import dagger.Module;
import dagger.Provides;
import retrofit2.Retrofit;
import sanchez.sanchez.sergio.bullkeeper.di.scopes.PerActivity;
import sanchez.sanchez.sergio.data.mapper.AbstractDataMapper;
import sanchez.sanchez.sergio.data.net.models.response.CommentsStatisticsBySocialMediaDTO;
import sanchez.sanchez.sergio.data.net.models.response.DimensionsStatisticsDTO;
import sanchez.sanchez.sergio.data.net.models.response.SentimentAnalysisStatisticsDTO;
import sanchez.sanchez.sergio.data.net.models.response.SocialMediaActivityStatisticsDTO;
import sanchez.sanchez.sergio.data.net.models.response.SocialMediaLikesStatisticsDTO;
import sanchez.sanchez.sergio.data.net.services.IAnalysisStatisticsService;
import sanchez.sanchez.sergio.data.repository.AnalysisStatisticsRepositoryImpl;
import sanchez.sanchez.sergio.domain.executor.IPostExecutionThread;
import sanchez.sanchez.sergio.domain.executor.IThreadExecutor;
import sanchez.sanchez.sergio.domain.interactor.statistics.GetCommentsStatisticsBySocialMediaInteract;
import sanchez.sanchez.sergio.domain.interactor.statistics.GetSocialMediaLikesStatisticsInteract;
import sanchez.sanchez.sergio.domain.interactor.statistics.GetFourDimensionsStatisticsByChildInteract;
import sanchez.sanchez.sergio.domain.interactor.statistics.GetSentimentAnalysisStatisticsInteract;
import sanchez.sanchez.sergio.domain.interactor.statistics.GetSocialMediaActivityStatisticsInteract;
import sanchez.sanchez.sergio.domain.models.CommentsStatisticsBySocialMediaEntity;
import sanchez.sanchez.sergio.domain.models.DimensionEntity;
import sanchez.sanchez.sergio.domain.models.SentimentAnalysisStatisticsEntity;
import sanchez.sanchez.sergio.domain.models.SocialMediaActivityStatisticsEntity;
import sanchez.sanchez.sergio.domain.models.SocialMediaLikesStatisticsEntity;
import sanchez.sanchez.sergio.domain.repository.IAnalysisStatisticsRepository;
/**
 * Analysis Statistics Module
 */
@Module
public class AnalysisStatisticsModule {

    /**
     * Provide Analysis Statistics Service
     * @param retrofit
     * @return
     */
    @Provides @PerActivity
    IAnalysisStatisticsService provideAnalysisStatisticsService(final Retrofit retrofit){
        return retrofit.create(IAnalysisStatisticsService.class);
    }

    /**
     * Provide Analysis Statistics Repository
     * @param analysisStatisticsService
     * @param dimensionDataMapper
     * @param socialMediaDataMapper
     * @param sentimentAnalysisDataMapper
     * @param commentsStatisticsDataMapper
     * @param socialMediaLikesStatisticsDataMapper
     * @return
     */
    @Provides @PerActivity
    IAnalysisStatisticsRepository provideAnalysisStatisticsRepository(
            final IAnalysisStatisticsService analysisStatisticsService,
            final AbstractDataMapper<DimensionsStatisticsDTO.DimensionDTO, DimensionEntity> dimensionDataMapper,
            final AbstractDataMapper<SocialMediaActivityStatisticsDTO, SocialMediaActivityStatisticsEntity>
                    socialMediaDataMapper,
            final AbstractDataMapper<SentimentAnalysisStatisticsDTO, SentimentAnalysisStatisticsEntity>
                    sentimentAnalysisDataMapper,
            final AbstractDataMapper<CommentsStatisticsBySocialMediaDTO, CommentsStatisticsBySocialMediaEntity>  commentsStatisticsDataMapper,
            final AbstractDataMapper<SocialMediaLikesStatisticsDTO, SocialMediaLikesStatisticsEntity> socialMediaLikesStatisticsDataMapper
    ){
        return new AnalysisStatisticsRepositoryImpl(analysisStatisticsService, dimensionDataMapper,
                socialMediaDataMapper, sentimentAnalysisDataMapper, commentsStatisticsDataMapper,
                socialMediaLikesStatisticsDataMapper);
    }

    /**
     * Provide Get Four Dimensions Statistics Interact
     * @param threadExecutor
     * @param postExecutionThread
     * @param analysisStatisticsRepository
     * @return
     */
    @Provides @PerActivity
    GetFourDimensionsStatisticsByChildInteract provideGetFourDimensionsStatisticsInteract(
            final IThreadExecutor threadExecutor,
            final IPostExecutionThread postExecutionThread,
            final IAnalysisStatisticsRepository analysisStatisticsRepository){
        return new GetFourDimensionsStatisticsByChildInteract(threadExecutor, postExecutionThread, analysisStatisticsRepository);
    }

    /**
     * Provide Get Social Media Activity Statistics Interact
     * @param threadExecutor
     * @param postExecutionThread
     * @param analysisStatisticsRepository
     * @return
     */
    @Provides @PerActivity
    GetSocialMediaActivityStatisticsInteract provideGetSocialMediaActivityStatisticsInteract(
            final IThreadExecutor threadExecutor,
            final IPostExecutionThread postExecutionThread,
            final IAnalysisStatisticsRepository analysisStatisticsRepository){
        return new GetSocialMediaActivityStatisticsInteract(threadExecutor, postExecutionThread, analysisStatisticsRepository);
    }

    /**
     * Get Sentiment Analysis Statistics Interact
     * @param threadExecutor
     * @param postExecutionThread
     * @param analysisStatisticsRepository
     * @return
     */
    @Provides @PerActivity
    GetSentimentAnalysisStatisticsInteract provideGetSentimentAnalysisStatisticsInteract(
            final IThreadExecutor threadExecutor,
            final IPostExecutionThread postExecutionThread,
            final IAnalysisStatisticsRepository analysisStatisticsRepository){
        return new GetSentimentAnalysisStatisticsInteract(threadExecutor, postExecutionThread, analysisStatisticsRepository);
    }

    /**
     * Provide Get Comments Statistics By Social Media Interact
     * @param threadExecutor
     * @param postExecutionThread
     * @param analysisStatisticsRepository
     * @return
     */
    @Provides @PerActivity
    public GetCommentsStatisticsBySocialMediaInteract provideGetCommentsStatisticsBySocialMediaInteract(
            final IThreadExecutor threadExecutor,
            final IPostExecutionThread postExecutionThread,
            final IAnalysisStatisticsRepository analysisStatisticsRepository
    ){
        return new GetCommentsStatisticsBySocialMediaInteract(threadExecutor, postExecutionThread, analysisStatisticsRepository);
    }

    /**
     * Provide Get Social Media Likes Statistics Interact
     * @param threadExecutor
     * @param postExecutionThread
     * @param analysisStatisticsRepository
     * @return
     */
    @Provides @PerActivity
    public GetSocialMediaLikesStatisticsInteract provideGetSocialMediaLikesStatisticsInteract(
            final IThreadExecutor threadExecutor,
            final IPostExecutionThread postExecutionThread,
            final IAnalysisStatisticsRepository analysisStatisticsRepository
    ){
        return new GetSocialMediaLikesStatisticsInteract(threadExecutor, postExecutionThread, analysisStatisticsRepository);
    }
}
