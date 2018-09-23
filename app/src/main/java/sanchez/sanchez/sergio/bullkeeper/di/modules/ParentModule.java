package sanchez.sanchez.sergio.bullkeeper.di.modules;

import android.content.Context;

import com.fernandocejas.arrow.checks.Preconditions;
import dagger.Module;
import dagger.Provides;
import retrofit2.Retrofit;
import sanchez.sanchez.sergio.bullkeeper.di.scopes.PerActivity;
import sanchez.sanchez.sergio.bullkeeper.core.utils.SupportImagePicker;
import sanchez.sanchez.sergio.data.mapper.AbstractDataMapper;
import sanchez.sanchez.sergio.data.net.models.response.ImageDTO;
import sanchez.sanchez.sergio.data.net.models.response.ParentDTO;
import sanchez.sanchez.sergio.data.net.models.response.SonDTO;
import sanchez.sanchez.sergio.data.net.services.IParentsService;
import sanchez.sanchez.sergio.data.repository.ParentRepositoryImpl;
import sanchez.sanchez.sergio.domain.executor.IPostExecutionThread;
import sanchez.sanchez.sergio.domain.executor.IThreadExecutor;
import sanchez.sanchez.sergio.domain.interactor.parents.DeleteAccountInteract;
import sanchez.sanchez.sergio.domain.interactor.parents.GetParentInformationInteract;
import sanchez.sanchez.sergio.domain.interactor.parents.GetSelfChildrenInteract;
import sanchez.sanchez.sergio.domain.interactor.parents.UpdateSelfInformationInteract;
import sanchez.sanchez.sergio.domain.models.ImageEntity;
import sanchez.sanchez.sergio.domain.models.ParentEntity;
import sanchez.sanchez.sergio.domain.models.SonEntity;
import sanchez.sanchez.sergio.domain.repository.IParentRepository;
import sanchez.sanchez.sergio.domain.utils.IAppUtils;

/**
 * Parent Module
 */
@Module
public class ParentModule {

    /**
     * Provide Parents Service
     * @return
     */
    @Provides @PerActivity
    public IParentsService provideParentsService(final Retrofit retrofit){
        return retrofit.create(IParentsService.class);
    }


    @Provides @PerActivity
    public SupportImagePicker provideSupportImagePicker(final Context appContext) {
        return new SupportImagePicker(appContext);
    }

    /**
     * Provide Parent Repository
     * @return
     */
    @Provides @PerActivity
    public IParentRepository provideParentRepository(final IParentsService parentsService,
                                                     final AbstractDataMapper<SonDTO, SonEntity> sonDataMapper,
                                                     final AbstractDataMapper<ParentDTO, ParentEntity> parentDataMapper,
                                                     final AbstractDataMapper<ImageDTO, ImageEntity> imageDataMapper){
        return new ParentRepositoryImpl(parentsService, sonDataMapper, parentDataMapper, imageDataMapper);
    }

    /**
     * Provide Get Self Children Interact
     * @return
     */
    @Provides @PerActivity
    public GetSelfChildrenInteract provideGetSelfChildrenInteract(final IThreadExecutor threadExecutor, final IPostExecutionThread postExecutionThread,
                                                                  final IParentRepository parentRepository){
        Preconditions.checkNotNull(threadExecutor, "Thread Executor can not be null");
        Preconditions.checkNotNull(postExecutionThread, "Post Execution can not be null");
        Preconditions.checkNotNull(parentRepository, "Parents Repository can not be null");
        return new GetSelfChildrenInteract(threadExecutor, postExecutionThread, parentRepository);
    }

    /**
     * Provide Get Parent Information Interact
     * @param threadExecutor
     * @param postExecutionThread
     * @param parentRepository
     * @return
     */
    @Provides @PerActivity
    public GetParentInformationInteract provideGetParentInformationInteract(final IThreadExecutor threadExecutor, final IPostExecutionThread postExecutionThread,
                                                                            final IParentRepository parentRepository) {
        Preconditions.checkNotNull(threadExecutor, "Thread Executor can not be null");
        Preconditions.checkNotNull(postExecutionThread, "Post Execution can not be null");
        Preconditions.checkNotNull(parentRepository, "Parents Repository can not be null");
        return new GetParentInformationInteract(threadExecutor, postExecutionThread, parentRepository);
    }

    /**
     * Provide Update Self Information Interact
     * @param threadExecutor
     * @param postExecutionThread
     * @param parentRepository
     * @return
     */
    @Provides @PerActivity
    public UpdateSelfInformationInteract provideUpdateSelfInformationInteract(final IThreadExecutor threadExecutor, final IPostExecutionThread postExecutionThread,
                                                                              final IParentRepository parentRepository, final IAppUtils appUtils){
        Preconditions.checkNotNull(threadExecutor, "Thread Executor can not be null");
        Preconditions.checkNotNull(postExecutionThread, "Post Execution can not be null");
        Preconditions.checkNotNull(parentRepository, "Parents Repository can not be null");
        return new UpdateSelfInformationInteract(threadExecutor, postExecutionThread, parentRepository, appUtils);
    }

    /**
     * Provide Delete Account Interact
     * @param threadExecutor
     * @param postExecutionThread
     * @param parentRepository
     * @return
     */
    @Provides @PerActivity
    public DeleteAccountInteract provideDeleteAccountInteract(final IThreadExecutor threadExecutor, final IPostExecutionThread postExecutionThread,
                                                              final IParentRepository parentRepository){
        Preconditions.checkNotNull(threadExecutor, "Thread Executor can not be null");
        Preconditions.checkNotNull(postExecutionThread, "Post Execution can not be null");
        Preconditions.checkNotNull(parentRepository, "Parents Repository can not be null");
        return new DeleteAccountInteract(threadExecutor, postExecutionThread, parentRepository);
    }

}
