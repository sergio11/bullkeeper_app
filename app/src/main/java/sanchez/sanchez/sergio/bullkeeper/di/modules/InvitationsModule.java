package sanchez.sanchez.sergio.bullkeeper.di.modules;

import com.fernandocejas.arrow.checks.Preconditions;

import dagger.Module;
import dagger.Provides;
import retrofit2.Retrofit;
import sanchez.sanchez.sergio.bullkeeper.di.scopes.PerActivity;
import sanchez.sanchez.sergio.data.mapper.AbstractDataMapper;
import sanchez.sanchez.sergio.data.net.models.response.SupervisedChildrenDTO;
import sanchez.sanchez.sergio.data.net.services.ISupervisedChildrenService;
import sanchez.sanchez.sergio.data.repository.SupervisedChildrenRepositoryImpl;
import sanchez.sanchez.sergio.domain.executor.IPostExecutionThread;
import sanchez.sanchez.sergio.domain.executor.IThreadExecutor;
import sanchez.sanchez.sergio.domain.interactor.children.AcceptSupervisedChildrenNoConfirmedInteract;
import sanchez.sanchez.sergio.domain.interactor.children.DeleteSupervisedChildrenConfirmedInteract;
import sanchez.sanchez.sergio.domain.interactor.children.DeleteAllSupervisedChildrenNoConfirmedInteract;
import sanchez.sanchez.sergio.domain.interactor.children.DeleteSupervisedChildrenNoConfirmedInteract;
import sanchez.sanchez.sergio.domain.interactor.children.GetSupervisedChildrenConfirmedDetailInteract;
import sanchez.sanchez.sergio.domain.interactor.children.GetSupervisedChildrenConfirmedInteract;
import sanchez.sanchez.sergio.domain.interactor.children.GetSupervisedChildrenNoConfirmedDetailInteract;
import sanchez.sanchez.sergio.domain.interactor.children.GetSupervisedChildrenNoConfirmedInteract;
import sanchez.sanchez.sergio.domain.models.SupervisedChildrenEntity;
import sanchez.sanchez.sergio.domain.repository.ISupervisedChildrenRepository;

/**
 * Invitations Module
 */
@Module
public class InvitationsModule {

    /**
     * Provide Supervised Children Service
     * @param retrofit
     * @return
     */
    @Provides
    @PerActivity
    public ISupervisedChildrenService provideSupervisedChildrenService(final Retrofit retrofit) {
        Preconditions.checkNotNull(retrofit, "Retrofit can not be null");
        return retrofit.create(ISupervisedChildrenService.class);
    }

    /**
     * Provide Supervised Children Repository
     * @param supervisedChildrenService
     * @return
     */
    @Provides
    @PerActivity
    public ISupervisedChildrenRepository provideSupervisedChildrenRepository(
            final ISupervisedChildrenService supervisedChildrenService,
            final AbstractDataMapper<SupervisedChildrenDTO, SupervisedChildrenEntity> supervisedChildrenEntityAbstractDataMapper) {
        Preconditions.checkNotNull(supervisedChildrenService, "Supervised Children Service");
        return new SupervisedChildrenRepositoryImpl(supervisedChildrenService,
                supervisedChildrenEntityAbstractDataMapper);
    }


    /**
     * Provide Delete All Supervised Children No Confirmed Interact
     * @return
     */
    @Provides
    @PerActivity
    public DeleteAllSupervisedChildrenNoConfirmedInteract provideDeleteAllSupervisedChildrenNoConfirmedInteract(
            final IThreadExecutor threadExecutor, final IPostExecutionThread postExecutionThread,
            final ISupervisedChildrenRepository supervisedChildrenRepository
    ){
        return new DeleteAllSupervisedChildrenNoConfirmedInteract(threadExecutor,
                postExecutionThread, supervisedChildrenRepository);
    }

    /**
     * Provide Delete Supervised Children Confirmed Interact
     * @return
     */
    @Provides
    @PerActivity
    public DeleteSupervisedChildrenConfirmedInteract provideDeleteSupervisedChildrenConfirmedInteract(
            final IThreadExecutor threadExecutor, final IPostExecutionThread postExecutionThread,
            final ISupervisedChildrenRepository supervisedChildrenRepository
    ){
        return new DeleteSupervisedChildrenConfirmedInteract(threadExecutor,
                postExecutionThread, supervisedChildrenRepository);
    }

    /**
     * Get Supervised Children Confirmed Detail Interact
     * @return
     */
    @Provides
    @PerActivity
    public GetSupervisedChildrenConfirmedDetailInteract provideGetSupervisedChildrenConfirmedDetailInteract(
            final IThreadExecutor threadExecutor, final IPostExecutionThread postExecutionThread,
            final ISupervisedChildrenRepository supervisedChildrenRepository
    ){
        return new GetSupervisedChildrenConfirmedDetailInteract(threadExecutor, postExecutionThread, supervisedChildrenRepository);
    }

    /**
     * Get Supervised Children Confirmed Interact
     * @return
     */
    @Provides
    @PerActivity
    public GetSupervisedChildrenConfirmedInteract provideGetSupervisedChildrenConfirmedInteract(
            final IThreadExecutor threadExecutor, final IPostExecutionThread postExecutionThread,
            final ISupervisedChildrenRepository supervisedChildrenRepository
    ){
        return new GetSupervisedChildrenConfirmedInteract(threadExecutor, postExecutionThread ,supervisedChildrenRepository);
    }

    /**
     * Get Supervised Children No Confirmed Interact
     * @return
     */
    @Provides
    @PerActivity
    public GetSupervisedChildrenNoConfirmedInteract provideGetSupervisedChildrenNoConfirmedInteract(
            final IThreadExecutor threadExecutor, final IPostExecutionThread postExecutionThread,
            final ISupervisedChildrenRepository supervisedChildrenRepository
    ){
        return new GetSupervisedChildrenNoConfirmedInteract(threadExecutor, postExecutionThread,
                supervisedChildrenRepository);
    }


    /**
     * Provide Delete Supervised Children No Confirm
     * @param threadExecutor
     * @param postExecutionThread
     * @param supervisedChildrenRepository
     * @return
     */
    @Provides
    @PerActivity
    public DeleteSupervisedChildrenNoConfirmedInteract provideDeleteSupervisedChildrenNoConfirmedInteract(
            final IThreadExecutor threadExecutor, final IPostExecutionThread postExecutionThread,
            final ISupervisedChildrenRepository supervisedChildrenRepository
    )
    {
        return new DeleteSupervisedChildrenNoConfirmedInteract(threadExecutor, postExecutionThread,
                supervisedChildrenRepository);
    }


    /**
     * Get Supervised Children No Confirmed Detail Interact
     * @return
     */
    @Provides
    @PerActivity
    public GetSupervisedChildrenNoConfirmedDetailInteract provideGetSupervisedChildrenNoConfirmedDetailInteract(
            final IThreadExecutor threadExecutor, final IPostExecutionThread postExecutionThread,
            final ISupervisedChildrenRepository supervisedChildrenRepository
    ){
        return new GetSupervisedChildrenNoConfirmedDetailInteract(threadExecutor, postExecutionThread, supervisedChildrenRepository);
    }

    /**
     * Provide Accept Supervised Children No Confirmed Interact
     * @param threadExecutor
     * @param postExecutionThread
     * @param supervisedChildrenRepository
     * @return
     */
    @Provides
    @PerActivity
    public AcceptSupervisedChildrenNoConfirmedInteract provideAcceptSupervisedChildrenNoConfirmedInteract(
            final IThreadExecutor threadExecutor, final IPostExecutionThread postExecutionThread,
            final ISupervisedChildrenRepository supervisedChildrenRepository
    ){
        return new AcceptSupervisedChildrenNoConfirmedInteract(threadExecutor, postExecutionThread, supervisedChildrenRepository);
    }

}
