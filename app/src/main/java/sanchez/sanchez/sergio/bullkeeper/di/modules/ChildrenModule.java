package sanchez.sanchez.sergio.bullkeeper.di.modules;

import javax.inject.Named;
import dagger.Module;
import dagger.Provides;
import retrofit2.Retrofit;
import sanchez.sanchez.sergio.bullkeeper.di.scopes.PerActivity;
import sanchez.sanchez.sergio.data.mapper.AbstractDataMapper;
import sanchez.sanchez.sergio.data.net.models.response.AlertsStatisticsDTO;
import sanchez.sanchez.sergio.data.net.models.response.ImageDTO;
import sanchez.sanchez.sergio.data.net.models.response.KidDTO;
import sanchez.sanchez.sergio.data.net.models.response.KidGuardianDTO;
import sanchez.sanchez.sergio.data.net.models.response.LocationDTO;
import sanchez.sanchez.sergio.data.net.services.IChildrenService;
import sanchez.sanchez.sergio.data.repository.ChildrenRepositoryImpl;
import sanchez.sanchez.sergio.domain.executor.IPostExecutionThread;
import sanchez.sanchez.sergio.domain.executor.IThreadExecutor;
import sanchez.sanchez.sergio.domain.interactor.children.GetAlertsStatisticsInteract;
import sanchez.sanchez.sergio.domain.interactor.children.GetKidGuardiansInteract;
import sanchez.sanchez.sergio.domain.interactor.children.GetKidLocationInteract;
import sanchez.sanchez.sergio.domain.interactor.children.GetKidByIdInteract;
import sanchez.sanchez.sergio.domain.models.AlertsStatisticsEntity;
import sanchez.sanchez.sergio.domain.models.ImageEntity;
import sanchez.sanchez.sergio.domain.models.KidEntity;
import sanchez.sanchez.sergio.domain.models.KidGuardianEntity;
import sanchez.sanchez.sergio.domain.models.LocationEntity;
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
     * @param alertsStatisticsDataMapper
     * @param kidGuardianEntityAbstractDataMapper
     * @param locationEntityAbstractDataMapper
     * @return
     */
    @Provides @PerActivity
    IChildrenRepository provideChildrenRepository(final IChildrenService childrenService,
                                                  final AbstractDataMapper<KidDTO, KidEntity> sonDataMapper,
                                                  @Named("SonImageEntity") final AbstractDataMapper<ImageDTO, ImageEntity> imageDataMapper,
                                                  final AbstractDataMapper<AlertsStatisticsDTO, AlertsStatisticsEntity>
                                                          alertsStatisticsDataMapper,
                                                  final AbstractDataMapper<KidGuardianDTO, KidGuardianEntity>
                                                        kidGuardianEntityAbstractDataMapper,
                                                  final AbstractDataMapper<LocationDTO, LocationEntity>
                                                    locationEntityAbstractDataMapper) {
        return new ChildrenRepositoryImpl(childrenService, sonDataMapper, imageDataMapper, alertsStatisticsDataMapper, kidGuardianEntityAbstractDataMapper, locationEntityAbstractDataMapper);
    }

    /**
     * Provide Get Kid By Id Interact
     * @param threadExecutor
     * @param postExecutionThread
     * @param childrenRepository
     * @return
     */
    @Provides @PerActivity
    GetKidByIdInteract provideGetKidByIdInteract(final IThreadExecutor threadExecutor, final IPostExecutionThread postExecutionThread,
                                                 final IChildrenRepository childrenRepository){
        return new GetKidByIdInteract(threadExecutor, postExecutionThread, childrenRepository);
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
    GetKidGuardiansInteract provideGetKidGuardiansInteract(
            final IThreadExecutor threadExecutor,
            final IPostExecutionThread postExecutionThread,
            final IChildrenRepository childrenRepository){
        return new GetKidGuardiansInteract(threadExecutor, postExecutionThread, childrenRepository);
    }

    /**
     * Provide Get Kid Location Interact
     * @param threadExecutor
     * @param postExecutionThread
     * @param childrenRepository
     * @return
     */
    @Provides @PerActivity
    GetKidLocationInteract provideGetKidLocationInteract(
            final IThreadExecutor threadExecutor,
            final IPostExecutionThread postExecutionThread,
            final IChildrenRepository childrenRepository
    ){
        return new GetKidLocationInteract(threadExecutor, postExecutionThread, childrenRepository);
    }


}
