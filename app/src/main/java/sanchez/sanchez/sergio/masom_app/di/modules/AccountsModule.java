package sanchez.sanchez.sergio.masom_app.di.modules;

import com.fernandocejas.arrow.checks.Preconditions;

import dagger.Module;
import dagger.Provides;
import retrofit2.Retrofit;
import sanchez.sanchez.sergio.data.mapper.AbstractDataMapper;
import sanchez.sanchez.sergio.data.mapper.impl.ParentEntityDataMapper;
import sanchez.sanchez.sergio.data.net.models.response.ParentDTO;
import sanchez.sanchez.sergio.data.repository.AccountsRepositoryImpl;
import sanchez.sanchez.sergio.data.net.services.IAuthenticationService;
import sanchez.sanchez.sergio.data.net.services.IParentsService;
import sanchez.sanchez.sergio.domain.executor.IPostExecutionThread;
import sanchez.sanchez.sergio.domain.executor.IThreadExecutor;
import sanchez.sanchez.sergio.domain.interactor.accounts.RegisterParentInteract;
import sanchez.sanchez.sergio.domain.interactor.accounts.ResetPasswordInteract;
import sanchez.sanchez.sergio.domain.interactor.accounts.SigninInteract;
import sanchez.sanchez.sergio.domain.models.ParentEntity;
import sanchez.sanchez.sergio.domain.repository.IAccountsRepository;
import sanchez.sanchez.sergio.domain.utils.IAppUtils;
import sanchez.sanchez.sergio.masom_app.di.scopes.PerActivity;

/**
 * Accounts Module
 */
@Module
public class AccountsModule {

    /**
     * Provide Parent Entity Data Mapper
     * @return
     */
    @Provides @PerActivity
    public AbstractDataMapper<ParentDTO, ParentEntity> provideParentEntityDataMapper() {
        return new ParentEntityDataMapper();
    }

    /**
     * Provide Authentication Service
     * @param retrofit
     * @return
     */
    @Provides @PerActivity
    public IAuthenticationService provideAuthenticationService(final Retrofit retrofit){
        return retrofit.create(IAuthenticationService.class);
    }

    /**
     * Provide Parents Service
     * @param retrofit
     * @return
     */
    @Provides @PerActivity
    public IParentsService provideParentsService(final Retrofit retrofit) {
        return retrofit.create(IParentsService.class);
    }

    /**
     * Provide Accounts Repository
     * @param authenticationService
     * @return
     */
    @Provides @PerActivity
    public IAccountsRepository provideAccountsRepository(
            final IAuthenticationService authenticationService, final IParentsService parentsService,
            final AbstractDataMapper<ParentDTO, ParentEntity> parentDataMapper) {
        return new AccountsRepositoryImpl(authenticationService, parentsService, parentDataMapper);
    }


    /**
     * Provide Signin Interact
     * @param accountsRepository
     * @return
     */
    @Provides @PerActivity
    public SigninInteract provideSigninInteract(final IThreadExecutor threadExecutor, final IPostExecutionThread postExecutionThread,
                                                final IAccountsRepository accountsRepository){
        Preconditions.checkNotNull(threadExecutor, "Thread can not be null");
        Preconditions.checkNotNull(postExecutionThread, "Post Execution can not be null");
        Preconditions.checkNotNull(accountsRepository, "Accounts Repository can not be null");
        return new SigninInteract(threadExecutor, postExecutionThread, accountsRepository);
    }

    /**
     * Provide Reset Password Interact
     * @param threadExecutor
     * @param postExecutionThread
     * @param accountsRepository
     * @return
     */
    @Provides @PerActivity
    public ResetPasswordInteract provideResetPasswordInteract(final IThreadExecutor threadExecutor, final IPostExecutionThread postExecutionThread,
                                                              final IAccountsRepository accountsRepository) {
        Preconditions.checkNotNull(threadExecutor, "Thread Executor can not be null");
        Preconditions.checkNotNull(postExecutionThread, "Post Execution can not be null");
        Preconditions.checkNotNull(accountsRepository, "Accounts Repository can not be null");
        return new ResetPasswordInteract(threadExecutor, postExecutionThread, accountsRepository);
    }

    /**
     * Provide Register Parent Interact
     * @param threadExecutor
     * @param postExecutionThread
     * @param accountsRepository
     * @return
     */
    @Provides @PerActivity
    public RegisterParentInteract provideRegisterParentInteract(final IThreadExecutor threadExecutor, final IPostExecutionThread postExecutionThread,
                                                                final IAccountsRepository accountsRepository, final IAppUtils appUtils){
        Preconditions.checkNotNull(threadExecutor, "Thread Executor can not be null");
        Preconditions.checkNotNull(postExecutionThread, "Post Execution can not be null");
        Preconditions.checkNotNull(accountsRepository, "Accounts Repository can not be null");
        Preconditions.checkNotNull(appUtils, "App Utils can not ben null");
        return new RegisterParentInteract(threadExecutor, postExecutionThread, accountsRepository, appUtils);
    }

}
