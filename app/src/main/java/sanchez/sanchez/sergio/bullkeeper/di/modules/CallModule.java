package sanchez.sanchez.sergio.bullkeeper.di.modules;

import dagger.Module;
import dagger.Provides;
import retrofit2.Retrofit;
import sanchez.sanchez.sergio.bullkeeper.di.scopes.PerActivity;
import sanchez.sanchez.sergio.data.mapper.AbstractDataMapper;
import sanchez.sanchez.sergio.data.net.models.response.CallDetailDTO;
import sanchez.sanchez.sergio.data.net.services.ICallsService;
import sanchez.sanchez.sergio.data.repository.CallsRepositoryImpl;
import sanchez.sanchez.sergio.domain.executor.IPostExecutionThread;
import sanchez.sanchez.sergio.domain.executor.IThreadExecutor;
import sanchez.sanchez.sergio.domain.interactor.calls.GetCallDetailInteract;
import sanchez.sanchez.sergio.domain.interactor.calls.GetCallDetailsInteract;
import sanchez.sanchez.sergio.domain.models.CallDetailEntity;
import sanchez.sanchez.sergio.domain.repository.ICallsRepository;

/**
 * Call Module
 */
@Module
public class CallModule {

    /**
     * Provide Calls Service
     * @param retrofit
     * @return
     */
    @Provides @PerActivity
    public ICallsService provideCallsService(final Retrofit retrofit) {
        return retrofit.create(ICallsService.class);
    }

    /**
     * Provide Calls Repository
     * @param callsService
     * @param callsDetailDataMapper
     * @return
     */
    @Provides @PerActivity
    public ICallsRepository provideCallsRepository(
            final ICallsService callsService,
            final AbstractDataMapper<CallDetailDTO, CallDetailEntity>
                callsDetailDataMapper) {
        return new CallsRepositoryImpl(callsService, callsDetailDataMapper);
    }

    /**
     * Provide Get Call Details Interact
     * @param threadExecutor
     * @param postExecutionThread
     * @param callsRepository
     * @return
     */
    @Provides @PerActivity
    public GetCallDetailsInteract provideGetCallDetailsInteract(
            final IThreadExecutor threadExecutor,
            final IPostExecutionThread postExecutionThread,
            final ICallsRepository callsRepository
    ){
        return new GetCallDetailsInteract(threadExecutor, postExecutionThread, callsRepository);
    }

    /**
     * Provide Get Call Detail Interact
     * @param threadExecutor
     * @param postExecutionThread
     * @param callsRepository
     * @return
     */
    @Provides @PerActivity
    public GetCallDetailInteract provideGetCallDetailInteract(
            final IThreadExecutor threadExecutor,
            final IPostExecutionThread postExecutionThread,
            final ICallsRepository callsRepository
    ){
        return new GetCallDetailInteract(threadExecutor, postExecutionThread, callsRepository);
    }

}
