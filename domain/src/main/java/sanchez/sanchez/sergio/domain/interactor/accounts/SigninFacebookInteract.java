package sanchez.sanchez.sergio.domain.interactor.accounts;

import com.fernandocejas.arrow.checks.Preconditions;

import io.reactivex.Observable;
import io.reactivex.functions.Function;
import sanchez.sanchez.sergio.domain.executor.IPostExecutionThread;
import sanchez.sanchez.sergio.domain.executor.IThreadExecutor;
import sanchez.sanchez.sergio.domain.interactor.UseCase;
import sanchez.sanchez.sergio.domain.models.AuthenticationResponseEntity;
import sanchez.sanchez.sergio.domain.repository.IAccountsRepository;

/**
 * Signin Facebook Interact
 */
public final class SigninFacebookInteract extends UseCase<AuthenticationResponseEntity, SigninFacebookInteract.Params> {

    private final IAccountsRepository accountsRepository;

    /**
     * @param threadExecutor
     * @param postExecutionThread
     */
    public SigninFacebookInteract(final IThreadExecutor threadExecutor, final IPostExecutionThread postExecutionThread,
                                  final IAccountsRepository accountsRepository) {
        super(threadExecutor, postExecutionThread);
        this.accountsRepository = accountsRepository;
    }

    /**
     *
     * @param params
     * @return
     */
    @Override
    protected Observable<AuthenticationResponseEntity> buildUseCaseObservable(final SigninFacebookInteract.Params params) {

        Preconditions.checkNotNull(params, "Signin Facebook can not be null");
        Preconditions.checkNotNull(params.getAccessToken(), "Access Token can not be null");
        return accountsRepository.getAuthorizationTokenByFacebook(params.getAccessToken());
    }


    /**
     * Params
     */
    public static final class Params {

        private final String accessToken;

        /**
         * @param accessToken
         */
        private Params(final String accessToken) {
            this.accessToken = accessToken;
        }

        public String getAccessToken() {
            return accessToken;
        }

        /**
         * Create
         * @param accessToken
         * @return
         */
        public static Params create(final String accessToken) {
            return new Params(accessToken);
        }
    }



}
