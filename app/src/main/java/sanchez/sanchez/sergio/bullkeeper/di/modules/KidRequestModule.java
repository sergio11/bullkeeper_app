package sanchez.sanchez.sergio.bullkeeper.di.modules;

import dagger.Module;
import dagger.Provides;
import retrofit2.Retrofit;
import sanchez.sanchez.sergio.bullkeeper.di.scopes.PerActivity;
import sanchez.sanchez.sergio.data.mapper.AbstractDataMapper;
import sanchez.sanchez.sergio.data.net.models.response.KidRequestDTO;
import sanchez.sanchez.sergio.data.net.services.IKidRequestService;
import sanchez.sanchez.sergio.data.repository.KidRequestRepositoryImpl;
import sanchez.sanchez.sergio.domain.executor.IPostExecutionThread;
import sanchez.sanchez.sergio.domain.executor.IThreadExecutor;
import sanchez.sanchez.sergio.domain.interactor.kidrequest.DeleteAllKidRequestInteract;
import sanchez.sanchez.sergio.domain.interactor.kidrequest.DeleteKidRequestInteract;
import sanchez.sanchez.sergio.domain.interactor.kidrequest.GetKidRequestDetailInteract;
import sanchez.sanchez.sergio.domain.interactor.kidrequest.GetKidRequestInteract;
import sanchez.sanchez.sergio.domain.models.KidRequestEntity;
import sanchez.sanchez.sergio.domain.repository.IKidRequestRepository;

/**
 * Kid Request Module
 */
@Module
public class KidRequestModule {

    /**
     * Provide Kid Request Service
     * @return
     */
    @Provides
    @PerActivity
    public IKidRequestService provideKidRequestService(final Retrofit retrofit){
        return retrofit.create(IKidRequestService.class);
    }

    /**
     * Provide Kid Request Repository
     * @param kidRequestService
     * @param kidRequestEntityAbstractDataMapper
     * @return
     */
    @Provides
    @PerActivity
    public IKidRequestRepository provideKidRequestRepository(
            final IKidRequestService kidRequestService,
            final AbstractDataMapper<KidRequestDTO, KidRequestEntity> kidRequestEntityAbstractDataMapper
            ){
        return new KidRequestRepositoryImpl(kidRequestService,
                kidRequestEntityAbstractDataMapper);
    }


    /**
     * Provide Delete All Kid Request Interact
     * @param threadExecutor
     * @param postExecutionThread
     * @param kidRequestRepository
     * @return
     */
    @Provides
    @PerActivity
    public DeleteAllKidRequestInteract provideDeleteAllKidRequestInteract(
            final IThreadExecutor threadExecutor,
            final IPostExecutionThread postExecutionThread,
            final IKidRequestRepository kidRequestRepository
    ) {
        return new DeleteAllKidRequestInteract(threadExecutor, postExecutionThread,
                kidRequestRepository);
    }


    /**
     * Provide Get Kid Request Interact
     * @param threadExecutor
     * @param postExecutionThread
     * @param kidRequestRepository
     * @return
     */
    @Provides
    @PerActivity
    public GetKidRequestInteract provideGetKidRequestInteract(
            final IThreadExecutor threadExecutor,
            final IPostExecutionThread postExecutionThread,
            final IKidRequestRepository kidRequestRepository
    ) {
        return new GetKidRequestInteract(
                threadExecutor, postExecutionThread, kidRequestRepository);
    }


    /**
     * Provide Get Kid Request Detail Interact
     * @param threadExecutor
     * @param postExecutionThread
     * @param kidRequestRepository
     * @return
     */
    @Provides
    @PerActivity
    public GetKidRequestDetailInteract provideGetKidRequestDetailInteract(
            final IThreadExecutor threadExecutor,
            final IPostExecutionThread postExecutionThread,
            final IKidRequestRepository kidRequestRepository
    ){
        return new GetKidRequestDetailInteract(
                threadExecutor, postExecutionThread, kidRequestRepository);
    }


    /**
     * Provide Delete Kid Request Interact
     * @param threadExecutor
     * @param postExecutionThread
     * @param kidRequestRepository
     * @return
     */
    @Provides
    @PerActivity
    public DeleteKidRequestInteract provideDeleteKidRequestInteract(
            final IThreadExecutor threadExecutor,
            final IPostExecutionThread postExecutionThread,
            final IKidRequestRepository kidRequestRepository
    ){
        return new DeleteKidRequestInteract(threadExecutor, postExecutionThread, kidRequestRepository);
    }
}
