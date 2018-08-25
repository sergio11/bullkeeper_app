package sanchez.sanchez.sergio.masom_app.di.modules;

import com.fernandocejas.arrow.checks.Preconditions;

import dagger.Module;
import dagger.Provides;
import retrofit2.Retrofit;
import sanchez.sanchez.sergio.data.repository.IAccountsRepositoryImpl;
import sanchez.sanchez.sergio.data.services.IAuthenticationService;
import sanchez.sanchez.sergio.domain.executor.IPostExecutionThread;
import sanchez.sanchez.sergio.domain.executor.IThreadExecutor;
import sanchez.sanchez.sergio.domain.interactor.accounts.SigninInteract;
import sanchez.sanchez.sergio.domain.repository.IAccountsRepository;
import sanchez.sanchez.sergio.masom_app.di.scopes.PerActivity;

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
     * Provide Accounts Repository
     * @param authenticationService
     * @return
     */
    @Provides @PerActivity
    public IAccountsRepository provideAccountsRepository(final IAuthenticationService authenticationService) {
        return new IAccountsRepositoryImpl(authenticationService);
    }

    /**
     * Provide Signin Interact
     * @param accountsRepository
     * @return
     */
    @Provides @PerActivity
    public SigninInteract provideSigninInteract(final IThreadExecutor threadExecutor, final IPostExecutionThread postExecutionThread,
                                                final IAccountsRepository accountsRepository){

        Preconditions.checkNotNull(accountsRepository, "Accounts Repository can not be null");

        return new SigninInteract(threadExecutor, postExecutionThread, accountsRepository);
    }

}
