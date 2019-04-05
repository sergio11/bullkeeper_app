package sanchez.sanchez.sergio.bullkeeper.di.modules;

import android.content.Context;
import com.fernandocejas.arrow.checks.Preconditions;
import dagger.Module;
import dagger.Provides;
import retrofit2.Retrofit;
import sanchez.sanchez.sergio.bullkeeper.di.scopes.PerActivity;
import sanchez.sanchez.sergio.bullkeeper.core.utils.SupportImagePicker;
import sanchez.sanchez.sergio.data.mapper.AbstractDataMapper;
import sanchez.sanchez.sergio.data.net.models.response.ChildrenOfSelfGuardianDTO;
import sanchez.sanchez.sergio.data.net.models.response.ImageDTO;
import sanchez.sanchez.sergio.data.net.models.response.GuardianDTO;
import sanchez.sanchez.sergio.data.net.models.response.KidGuardianDTO;
import sanchez.sanchez.sergio.data.net.models.response.UserPreferenceDTO;
import sanchez.sanchez.sergio.data.net.services.IGuardiansService;
import sanchez.sanchez.sergio.data.repository.GuardianRepositoryImpl;
import sanchez.sanchez.sergio.domain.executor.IPostExecutionThread;
import sanchez.sanchez.sergio.domain.executor.IThreadExecutor;
import sanchez.sanchez.sergio.domain.interactor.guardians.ChangeUserEmailInteract;
import sanchez.sanchez.sergio.domain.interactor.guardians.ChangeUserPasswordInteract;
import sanchez.sanchez.sergio.domain.interactor.guardians.DeleteAccountInteract;
import sanchez.sanchez.sergio.domain.interactor.guardians.GetGuardianInformationInteract;
import sanchez.sanchez.sergio.domain.interactor.guardians.GetPreferencesForSelfUserInteract;
import sanchez.sanchez.sergio.domain.interactor.guardians.GetSelfChildrenInteract;
import sanchez.sanchez.sergio.domain.interactor.guardians.GetSupervisedChildConfirmedByIdInteract;
import sanchez.sanchez.sergio.domain.interactor.guardians.SavePreferencesForSelfUserInteract;
import sanchez.sanchez.sergio.domain.interactor.guardians.SearchGuardiansInteract;
import sanchez.sanchez.sergio.domain.interactor.guardians.UpdateSelfInformationInteract;
import sanchez.sanchez.sergio.domain.models.ChildrenOfSelfGuardianEntity;
import sanchez.sanchez.sergio.domain.models.ImageEntity;
import sanchez.sanchez.sergio.domain.models.GuardianEntity;
import sanchez.sanchez.sergio.domain.models.KidGuardianEntity;
import sanchez.sanchez.sergio.domain.models.UserPreferenceEntity;
import sanchez.sanchez.sergio.domain.repository.IGuardianRepository;
import sanchez.sanchez.sergio.domain.utils.IAppUtils;

/**
 * Guardian Module
 */
@Module
public class GuardianModule {

    /**
     * Provide Guardians Service
     * @return
     */
    @Provides @PerActivity
    public IGuardiansService provideGuardianService(final Retrofit retrofit){
        return retrofit.create(IGuardiansService.class);
    }

    /**
     * Provide Support Image Picker
     * @param appContext
     * @return
     */
    @Provides @PerActivity
    public SupportImagePicker provideSupportImagePicker(final Context appContext) {
        return new SupportImagePicker(appContext);
    }

    /**
     * Provide Parent Repository
     * @param parentsService
     * @param parentDataMapper
     * @param imageDataMapper
     * @param childrenOfSelfGuardianDataMapper
     * @param kidGuardianEntityAbstractDataMapper
     * @param userPreferenceEntityAbstractDataMapper
     * @return
     */
    @Provides @PerActivity
    public IGuardianRepository provideGuardianRepository(final IGuardiansService parentsService,
                                                         final AbstractDataMapper<GuardianDTO, GuardianEntity> parentDataMapper,
                                                         final AbstractDataMapper<ImageDTO, ImageEntity> imageDataMapper,
                                                         final AbstractDataMapper<ChildrenOfSelfGuardianDTO, ChildrenOfSelfGuardianEntity>
                                                            childrenOfSelfGuardianDataMapper,
                                                         final AbstractDataMapper<KidGuardianDTO, KidGuardianEntity> kidGuardianEntityAbstractDataMapper,
                                                         final AbstractDataMapper<UserPreferenceDTO, UserPreferenceEntity> userPreferenceEntityAbstractDataMapper){
        return new GuardianRepositoryImpl(parentsService, parentDataMapper,
                imageDataMapper, childrenOfSelfGuardianDataMapper, kidGuardianEntityAbstractDataMapper, userPreferenceEntityAbstractDataMapper);
    }

    /**
     * Provide Get Self Children Interact
     * @return
     */
    @Provides @PerActivity
    public GetSelfChildrenInteract provideGetSelfChildrenInteract(final IThreadExecutor threadExecutor, final IPostExecutionThread postExecutionThread,
                                                                  final IGuardianRepository parentRepository){
        Preconditions.checkNotNull(threadExecutor, "Thread Executor can not be null");
        Preconditions.checkNotNull(postExecutionThread, "Post Execution can not be null");
        Preconditions.checkNotNull(parentRepository, "Parents Repository can not be null");
        return new GetSelfChildrenInteract(threadExecutor, postExecutionThread, parentRepository);
    }

    /**
     * Provide Get Guardian Information Interact
     * @param threadExecutor
     * @param postExecutionThread
     * @param parentRepository
     * @return
     */
    @Provides @PerActivity
    public GetGuardianInformationInteract provideGetGuardianInformationInteract(final IThreadExecutor threadExecutor, final IPostExecutionThread postExecutionThread,
                                                                                final IGuardianRepository parentRepository) {
        Preconditions.checkNotNull(threadExecutor, "Thread Executor can not be null");
        Preconditions.checkNotNull(postExecutionThread, "Post Execution can not be null");
        Preconditions.checkNotNull(parentRepository, "Parents Repository can not be null");
        return new GetGuardianInformationInteract(threadExecutor, postExecutionThread, parentRepository);
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
                                                                              final IGuardianRepository parentRepository, final IAppUtils appUtils){
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
                                                              final IGuardianRepository parentRepository){
        Preconditions.checkNotNull(threadExecutor, "Thread Executor can not be null");
        Preconditions.checkNotNull(postExecutionThread, "Post Execution can not be null");
        Preconditions.checkNotNull(parentRepository, "Parents Repository can not be null");
        return new DeleteAccountInteract(threadExecutor, postExecutionThread, parentRepository);
    }

    /**
     * Provide Search Guardians Interact
     * @param threadExecutor
     * @param postExecutionThread
     * @param guardianRepository
     * @return
     */
    @Provides @PerActivity
    public SearchGuardiansInteract provideSearchGuardiansInteract(final IThreadExecutor threadExecutor, final IPostExecutionThread postExecutionThread,
                                                                  final IGuardianRepository guardianRepository){
        Preconditions.checkNotNull(threadExecutor, "Thread Executor can not be null");
        Preconditions.checkNotNull(postExecutionThread, "Post Execution can not be null");
        Preconditions.checkNotNull(guardianRepository, "Guardian Repository can not be null");

        return new SearchGuardiansInteract(threadExecutor, postExecutionThread, guardianRepository);
    }

    /**
     *
     * @param threadExecutor
     * @param postExecutionThread
     * @param guardianRepository
     * @return
     */
    @Provides @PerActivity
    public ChangeUserEmailInteract provideChangeUserEmailInteract(final IThreadExecutor threadExecutor, final IPostExecutionThread postExecutionThread,
                                                                  final IGuardianRepository guardianRepository){
        Preconditions.checkNotNull(threadExecutor, "Thread Executor can not be null");
        Preconditions.checkNotNull(postExecutionThread, "Post Execution can not be null");
        Preconditions.checkNotNull(guardianRepository, "Guardian Repository can not be null");

        return new ChangeUserEmailInteract(threadExecutor, postExecutionThread, guardianRepository);
    }

    /**
     * Provide Change User Password Interact
     * @param threadExecutor
     * @param postExecutionThread
     * @param guardianRepository
     * @return
     */
    @Provides @PerActivity
    public ChangeUserPasswordInteract provideChangeUserPasswordInteract(
            final IThreadExecutor threadExecutor, final IPostExecutionThread postExecutionThread,
            final IGuardianRepository guardianRepository
    ){
        Preconditions.checkNotNull(threadExecutor, "Thread Executor can not be null");
        Preconditions.checkNotNull(postExecutionThread, "Post Execution can not be null");
        Preconditions.checkNotNull(guardianRepository, "Guardian Repository can not be null");

        return new ChangeUserPasswordInteract(threadExecutor, postExecutionThread, guardianRepository);
    }

    /**
     *
     * @param threadExecutor
     * @param postExecutionThread
     * @param guardianRepository
     * @return
     */
    @Provides @PerActivity
    public GetSupervisedChildConfirmedByIdInteract provideGetSupervisedChildConfirmedByIdInteract(
            final IThreadExecutor threadExecutor, final IPostExecutionThread postExecutionThread,
            final IGuardianRepository guardianRepository
    ){
        Preconditions.checkNotNull(threadExecutor, "Thread Executor can not be null");
        Preconditions.checkNotNull(postExecutionThread, "Post Execution can not be null");
        Preconditions.checkNotNull(guardianRepository, "Guardian Repository can not be null");
        return new GetSupervisedChildConfirmedByIdInteract(threadExecutor, postExecutionThread, guardianRepository);
    }

    /**
     * Provide Get Preferences For Self User Interact
     * @return
     */
    @Provides @PerActivity
    public GetPreferencesForSelfUserInteract provideGetPreferencesForSelfUserInteract(
            final IThreadExecutor threadExecutor, final IPostExecutionThread postExecutionThread,
            final IGuardianRepository guardianRepository
    ){
        Preconditions.checkNotNull(threadExecutor, "Thread Executor can not be null");
        Preconditions.checkNotNull(postExecutionThread, "Post Execution can not be null");
        Preconditions.checkNotNull(guardianRepository, "Guardian Repository can not be null");
        return new GetPreferencesForSelfUserInteract(threadExecutor, postExecutionThread, guardianRepository);
    }

    /**
     * Provide Save Preferences For Self User Interact
     * @return
     */
    @Provides @PerActivity
    public SavePreferencesForSelfUserInteract provideSavePreferencesForSelfUserInteract(
            final IThreadExecutor threadExecutor, final IPostExecutionThread postExecutionThread,
            final IGuardianRepository guardianRepository
    ){
        Preconditions.checkNotNull(threadExecutor, "Thread Executor can not be null");
        Preconditions.checkNotNull(postExecutionThread, "Post Execution can not be null");
        Preconditions.checkNotNull(guardianRepository, "Guardian Repository can not be null");
        return new SavePreferencesForSelfUserInteract(threadExecutor, postExecutionThread, guardianRepository);
    }
}
