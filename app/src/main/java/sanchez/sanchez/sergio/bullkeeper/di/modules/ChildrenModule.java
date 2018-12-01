package sanchez.sanchez.sergio.bullkeeper.di.modules;

import javax.inject.Named;
import dagger.Module;
import dagger.Provides;
import retrofit2.Retrofit;
import sanchez.sanchez.sergio.bullkeeper.di.scopes.PerActivity;
import sanchez.sanchez.sergio.data.mapper.AbstractDataMapper;
import sanchez.sanchez.sergio.data.net.models.response.AlertsStatisticsDTO;
import sanchez.sanchez.sergio.data.net.models.response.DimensionsStatisticsDTO;
import sanchez.sanchez.sergio.data.net.models.response.ImageDTO;
import sanchez.sanchez.sergio.data.net.models.response.KidDTO;
import sanchez.sanchez.sergio.data.net.models.response.KidGuardianDTO;
import sanchez.sanchez.sergio.data.net.models.response.SentimentAnalysisStatisticsDTO;
import sanchez.sanchez.sergio.data.net.models.response.SocialMediaActivityStatisticsDTO;
import sanchez.sanchez.sergio.data.net.services.IChildrenService;
import sanchez.sanchez.sergio.data.repository.ChildrenRepositoryImpl;
import sanchez.sanchez.sergio.domain.executor.IPostExecutionThread;
import sanchez.sanchez.sergio.domain.executor.IThreadExecutor;
import sanchez.sanchez.sergio.domain.interactor.children.GetAlertsStatisticsInteract;
import sanchez.sanchez.sergio.domain.interactor.children.GetFourDimensionsStatisticsByChildInteract;
import sanchez.sanchez.sergio.domain.interactor.children.GetKidGuardiansInteract;
import sanchez.sanchez.sergio.domain.interactor.children.GetSentimentAnalysisStatisticsInteract;
import sanchez.sanchez.sergio.domain.interactor.children.GetSocialMediaActivityStatisticsInteract;
import sanchez.sanchez.sergio.domain.interactor.children.GetSonByIdInteract;
import sanchez.sanchez.sergio.domain.models.AlertsStatisticsEntity;
import sanchez.sanchez.sergio.domain.models.DimensionEntity;
import sanchez.sanchez.sergio.domain.models.ImageEntity;
import sanchez.sanchez.sergio.domain.models.KidEntity;
import sanchez.sanchez.sergio.domain.models.KidGuardianEntity;
import sanchez.sanchez.sergio.domain.models.SentimentAnalysisStatisticsEntity;
import sanchez.sanchez.sergio.domain.models.SocialMediaActivityStatisticsEntity;
import sanchez.sanchez.sergio.domain.repository.IChildrenRepository;

/**
 * Children Module
 */
@Module
public class ChildrenModule {

    /**
     * Provide Children Service
     * @return
     */
    @Provides @PerActivity
    IChildrenService provideChildrenService(final Retrofit retrofit) {
        return retrofit.create(IChildrenService.class);
    }

    /**
     * Provide Children Repository
     * @param childrenService
     * @param sonDataMapper
     * @param imageDataMapper
     * @param dimensionDataMapper
     * @param socialMediaDataMapper
     * @param sentimentAnalysisDataMapper
     * @param alertsStatisticsDataMapper
     * @param kidGuardianEntityAbstractDataMapper
     * @return
     */
    @Provides @PerActivity
    IChildrenRepository provideChildrenRepository(final IChildrenService childrenService,
                                                  final AbstractDataMapper<KidDTO, KidEntity> sonDataMapper,
                                                  @Named("SonImageEntity") final AbstractDataMapper<ImageDTO, ImageEntity> imageDataMapper,
                                                  final AbstractDataMapper<DimensionsStatisticsDTO.DimensionDTO, DimensionEntity> dimensionDataMapper,
                                                  final AbstractDataMapper<SocialMediaActivityStatisticsDTO, SocialMediaActivityStatisticsEntity>
                                                    socialMediaDataMapper,
                                                  final AbstractDataMapper<SentimentAnalysisStatisticsDTO, SentimentAnalysisStatisticsEntity>
                                                          sentimentAnalysisDataMapper,
                                                  final AbstractDataMapper<AlertsStatisticsDTO, AlertsStatisticsEntity>
                                                          alertsStatisticsDataMapper,
                                                  final AbstractDataMapper<KidGuardianDTO, KidGuardianEntity>
                                                        kidGuardianEntityAbstractDataMapper) {
        return new ChildrenRepositoryImpl(childrenService, sonDataMapper, imageDataMapper, dimensionDataMapper, socialMediaDataMapper,
                sentimentAnalysisDataMapper, alertsStatisticsDataMapper, kidGuardianEntityAbstractDataMapper);
    }

    /**
     * Provide Get Kid By Id Interact
     * @param threadExecutor
     * @param postExecutionThread
     * @param childrenRepository
     * @return
     */
    @Provides @PerActivity
    GetSonByIdInteract provideGetKidByIdInteract(final IThreadExecutor threadExecutor, final IPostExecutionThread postExecutionThread,
                                                 final IChildrenRepository childrenRepository){
        return new GetSonByIdInteract(threadExecutor, postExecutionThread, childrenRepository);
    }

    /**
     * Provide Get Four Dimensions Statistics By Child Interact
     * @return
     */
    @Provides @PerActivity
    GetFourDimensionsStatisticsByChildInteract provideGetFourDimensionsStatisticsByChildInteract(final IThreadExecutor threadExecutor,
                                                                                                 final IPostExecutionThread postExecutionThread,
                                                                                                 final IChildrenRepository childrenRepository){
        return new GetFourDimensionsStatisticsByChildInteract(threadExecutor, postExecutionThread, childrenRepository);
    }

    /**
     * Provide Get Social Media Activity Statistics Interact
     * @return
     */
    @Provides @PerActivity
    GetSocialMediaActivityStatisticsInteract provideGetSocialMediaActivityStatisticsInteract(final IThreadExecutor threadExecutor, final IPostExecutionThread postExecutionThread,
                                                                                             final IChildrenRepository childrenRepository){
        return new GetSocialMediaActivityStatisticsInteract(threadExecutor, postExecutionThread, childrenRepository);
    }

    /**
     * Get Sentiment Analysis Statistics Interact
     * @param threadExecutor
     * @param postExecutionThread
     * @param childrenRepository
     * @return
     */
    @Provides @PerActivity
    GetSentimentAnalysisStatisticsInteract provideGetSentimentAnalysisStatisticsInteract(final IThreadExecutor threadExecutor, final IPostExecutionThread postExecutionThread,
                                                                                         final IChildrenRepository childrenRepository){
        return new GetSentimentAnalysisStatisticsInteract(threadExecutor, postExecutionThread, childrenRepository);
    }

    /**
     * Provide Get Alerts Statistics Interact
     * @param threadExecutor
     * @param postExecutionThread
     * @param childrenRepository
     * @return
     */
    @Provides @PerActivity
    GetAlertsStatisticsInteract provideGetAlertsStatisticsInteract(final IThreadExecutor threadExecutor, final IPostExecutionThread postExecutionThread,
                                                                   final IChildrenRepository childrenRepository) {
        return new GetAlertsStatisticsInteract(threadExecutor, postExecutionThread, childrenRepository);
    }

    /**
     *
     * @param threadExecutor
     * @param postExecutionThread
     * @param childrenRepository
     * @return
     */
    @Provides @PerActivity
    GetKidGuardiansInteract provideGetKidGuardiansInteract(final IThreadExecutor threadExecutor, final IPostExecutionThread postExecutionThread,
                                                           final IChildrenRepository childrenRepository){
        return new GetKidGuardiansInteract(threadExecutor, postExecutionThread, childrenRepository);
    }

}
