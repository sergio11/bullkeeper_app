package sanchez.sanchez.sergio.bullkeeper.di.modules;

import com.fernandocejas.arrow.checks.Preconditions;

import dagger.Module;
import dagger.Provides;
import retrofit2.Retrofit;
import sanchez.sanchez.sergio.data.mapper.AbstractDataMapper;
import sanchez.sanchez.sergio.data.net.models.response.GuardianDTO;
import sanchez.sanchez.sergio.data.net.models.response.JwtAuthenticationResponseDTO;
import sanchez.sanchez.sergio.data.net.services.IGuardiansService;
import sanchez.sanchez.sergio.data.repository.AccountsRepositoryImpl;
import sanchez.sanchez.sergio.data.net.services.IAuthenticationService;
import sanchez.sanchez.sergio.domain.executor.IPostExecutionThread;
import sanchez.sanchez.sergio.domain.executor.IThreadExecutor;
import sanchez.sanchez.sergio.domain.interactor.accounts.RegisterGuardianInteract;
import sanchez.sanchez.sergio.domain.interactor.accounts.ResetPasswordInteract;
import sanchez.sanchez.sergio.domain.interactor.accounts.SigninFacebookInteract;
import sanchez.sanchez.sergio.domain.interactor.accounts.SigninInteract;
import sanchez.sanchez.sergio.domain.models.AuthenticationResponseEntity;
import sanchez.sanchez.sergio.domain.models.GuardianEntity;
import sanchez.sanchez.sergio.domain.repository.IAccountsRepository;
import sanchez.sanchez.sergio.domain.utils.IAppUtils;
import sanchez.sanchez.sergio.bullkeeper.di.scopes.PerActivity;

/**
 * Accounts Module
 */
@Module
public class AccountsModule {

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
    public IGuardiansService provideParentsService(final Retrofit retrofit) {
        return retrofit.create(IGuardiansService.class);
    }

    /**
     * Provide Accounts Repository
     * @param authenticationService
     * @return
     */
    @Provides @PerActivity
    public IAccountsRepository provideAccountsRepository(
            final IAuthenticationService authenticationService,
            final IGuardiansService guardianService,
            final AbstractDataMapper<GuardianDTO, GuardianEntity> guardianDataMapper,
            final AbstractDataMapper<JwtAuthenticationResponseDTO, AuthenticationResponseEntity>
                responseEntityAbstractDataMapper) {
        return new AccountsRepositoryImpl(authenticationService,
                guardianService, guardianDataMapper, responseEntityAbstractDataMapper);
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
    public RegisterGuardianInteract provideRegisterParentInteract(final IThreadExecutor threadExecutor, final IPostExecutionThread postExecutionThread,
                                                                  final IAccountsRepository accountsRepository, final IAppUtils appUtils){
        Preconditions.checkNotNull(threadExecutor, "Thread Executor can not be null");
        Preconditions.checkNotNull(postExecutionThread, "Post Execution can not be null");
        Preconditions.checkNotNull(accountsRepository, "Accounts Repository can not be null");
        Preconditions.checkNotNull(appUtils, "App Utils can not ben null");
        return new RegisterGuardianInteract(threadExecutor, postExecutionThread, accountsRepository, appUtils);
    }

    @Provides @PerActivity
    public SigninFacebookInteract provideSigninFacebookInteract(final IThreadExecutor threadExecutor, final IPostExecutionThread postExecutionThread,
                                                                final IAccountsRepository accountsRepository) {
        Preconditions.checkNotNull(threadExecutor, "Thread Executor can not be null");
        Preconditions.checkNotNull(postExecutionThread, "Post Execution can not be null");
        Preconditions.checkNotNull(accountsRepository, "Accounts Repository can not be null");
        return new SigninFacebookInteract(threadExecutor, postExecutionThread, accountsRepository);
    }

}
