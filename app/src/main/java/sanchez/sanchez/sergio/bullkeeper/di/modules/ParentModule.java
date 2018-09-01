package sanchez.sanchez.sergio.bullkeeper.di.modules;

import com.fernandocejas.arrow.checks.Preconditions;
import dagger.Module;
import dagger.Provides;
import retrofit2.Retrofit;
import sanchez.sanchez.sergio.bullkeeper.di.scopes.PerActivity;
import sanchez.sanchez.sergio.data.mapper.AbstractDataMapper;
import sanchez.sanchez.sergio.data.mapper.impl.ImageEntityDataMapper;
import sanchez.sanchez.sergio.data.mapper.impl.ParentEntityDataMapper;
import sanchez.sanchez.sergio.data.mapper.impl.SchoolEntityDataMapper;
import sanchez.sanchez.sergio.data.mapper.impl.SonEntityDataMapper;
import sanchez.sanchez.sergio.data.net.models.response.ImageDTO;
import sanchez.sanchez.sergio.data.net.models.response.ParentDTO;
import sanchez.sanchez.sergio.data.net.models.response.SchoolDTO;
import sanchez.sanchez.sergio.data.net.models.response.SonDTO;
import sanchez.sanchez.sergio.data.net.services.IParentsService;
import sanchez.sanchez.sergio.data.net.utils.ApiEndPointsHelper;
import sanchez.sanchez.sergio.data.repository.ParentRepositoryImpl;
import sanchez.sanchez.sergio.domain.executor.IPostExecutionThread;
import sanchez.sanchez.sergio.domain.executor.IThreadExecutor;
import sanchez.sanchez.sergio.domain.interactor.parents.GetParentInformationInteract;
import sanchez.sanchez.sergio.domain.interactor.parents.GetSelfChildrenInteract;
import sanchez.sanchez.sergio.domain.interactor.parents.UpdateSelfInformationInteract;
import sanchez.sanchez.sergio.domain.models.ImageEntity;
import sanchez.sanchez.sergio.domain.models.ParentEntity;
import sanchez.sanchez.sergio.domain.models.SchoolEntity;
import sanchez.sanchez.sergio.domain.models.SonEntity;
import sanchez.sanchez.sergio.domain.repository.IParentRepository;

/**
 * Parent Module
 */
@Module
public class ParentModule {

    /**
     * Provide Parent Entity Data Mapper
     * @return
     */
    @Provides @PerActivity
    public AbstractDataMapper<ParentDTO, ParentEntity> provideParentEntityDataMapper(final ApiEndPointsHelper apiEndPointsHelper) {
        return new ParentEntityDataMapper(apiEndPointsHelper);
    }

    /**
     * Provide School Entity DataMapper
     * @return
     */
    @Provides @PerActivity
    public AbstractDataMapper<SchoolDTO, SchoolEntity> provideSchoolEntityDataMapper(){
        return new SchoolEntityDataMapper();
    }

    /**
     * Provide Image Entity DataMapper
     * @return
     */
    @Provides @PerActivity
    public AbstractDataMapper<ImageDTO, ImageEntity> provideImageEntityDataMapper(){
        return new ImageEntityDataMapper();
    }

    /**
     * Provide Son Entity Data Mapper
     * @return
     */
    @Provides @PerActivity
    public AbstractDataMapper<SonDTO, SonEntity> provideSonEntityDataMapper(final AbstractDataMapper<SchoolDTO, SchoolEntity> schoolEntityDataMapper,
                                                                            final ApiEndPointsHelper apiEndPointsHelper){
        return new SonEntityDataMapper(schoolEntityDataMapper, apiEndPointsHelper);
    }

    /**
     * Provide Parents Service
     * @return
     */
    @Provides @PerActivity
    public IParentsService provideParentsService(final Retrofit retrofit){
        return retrofit.create(IParentsService.class);
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
                                                                              final IParentRepository parentRepository){
        Preconditions.checkNotNull(threadExecutor, "Thread Executor can not be null");
        Preconditions.checkNotNull(postExecutionThread, "Post Execution can not be null");
        Preconditions.checkNotNull(parentRepository, "Parents Repository can not be null");
        return new UpdateSelfInformationInteract(threadExecutor, postExecutionThread, parentRepository);
    }

}
