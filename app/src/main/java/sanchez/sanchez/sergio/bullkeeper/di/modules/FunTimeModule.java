package sanchez.sanchez.sergio.bullkeeper.di.modules;

import dagger.Module;
import dagger.Provides;
import retrofit2.Retrofit;
import sanchez.sanchez.sergio.bullkeeper.di.scopes.PerActivity;
import sanchez.sanchez.sergio.data.mapper.AbstractDataMapper;
import sanchez.sanchez.sergio.data.net.models.response.FunTimeScheduledDTO;
import sanchez.sanchez.sergio.data.net.services.IFunTimeService;
import sanchez.sanchez.sergio.data.repository.FunTimeRepositoryImpl;
import sanchez.sanchez.sergio.domain.executor.IPostExecutionThread;
import sanchez.sanchez.sergio.domain.executor.IThreadExecutor;
import sanchez.sanchez.sergio.domain.interactor.funtime.GetFunTimeByChildIdInteract;
import sanchez.sanchez.sergio.domain.models.FunTimeScheduledEntity;
import sanchez.sanchez.sergio.domain.repository.IFunTimeRepository;

/**
 * Fun Time Module
 */
@Module
public class FunTimeModule {

    /**
     * Provide Fun Time Service
     * @param retrofit
     * @return
     */
    @Provides
    @PerActivity
    public IFunTimeService provideFunTimeService(final Retrofit retrofit) {
        return retrofit.create(IFunTimeService.class);
    }

    /**
     * Provide Fun Time Repository
     * @param screenTimeAllowanceService
     * @param funTimeScheduledEntityAbstractDataMapper
     * @return
     */
    @Provides
    @PerActivity
    public IFunTimeRepository provideFunTimeRepository(
            final IFunTimeService screenTimeAllowanceService,
            final AbstractDataMapper<FunTimeScheduledDTO, FunTimeScheduledEntity> funTimeScheduledEntityAbstractDataMapper) {
        return new FunTimeRepositoryImpl(screenTimeAllowanceService, funTimeScheduledEntityAbstractDataMapper);
    }

    /**
     * Provide Fun Time By Child Interact
     * @param threadExecutor
     * @param postExecutionThread
     * @param funTimeRepository
     * @return
     */
    @Provides
    @PerActivity
    public GetFunTimeByChildIdInteract provideFunTimeByChildInteract(final IThreadExecutor threadExecutor, final IPostExecutionThread postExecutionThread,
                                                                                      final IFunTimeRepository funTimeRepository) {
        return new GetFunTimeByChildIdInteract(threadExecutor, postExecutionThread, funTimeRepository);
    }
}
