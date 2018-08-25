package sanchez.sanchez.sergio.domain.interactor.accounts;

import com.fernandocejas.arrow.checks.Preconditions;

import io.reactivex.Observable;
import sanchez.sanchez.sergio.domain.executor.IPostExecutionThread;
import sanchez.sanchez.sergio.domain.executor.IThreadExecutor;
import sanchez.sanchez.sergio.domain.interactor.UseCase;
import sanchez.sanchez.sergio.domain.repository.IAccountsRepository;

/**
 * Signin Interact
 */
public final class SigninInteract extends UseCase<String, SigninInteract.Params> {

    private final IAccountsRepository accountsRepository;

    /**
     * @param threadExecutor
     * @param postExecutionThread
     */
    public SigninInteract(final IThreadExecutor threadExecutor, final IPostExecutionThread postExecutionThread,
                          final IAccountsRepository accountsRepository) {
        super(threadExecutor, postExecutionThread);
        this.accountsRepository = accountsRepository;
    }

    @Override
    protected Observable<String> buildUseCaseObservable(final SigninInteract.Params params) {

        Preconditions.checkNotNull(params, "Authentication Request can not be null");
        Preconditions.checkNotNull(params.getEmail(), "Email can not be null");
        Preconditions.checkNotNull(params.getPassword(), "Password can not be null");

        return accountsRepository.getAuthorizationToken(params.getEmail(), params.getPassword());
    }


    /**
     * Params
     */
    public static final class Params {

        private final String email;
        private final String password;

        /**
         * Authentication Request
         * @param email
         * @param password
         */
        private Params(final String email, final String password) {
            this.email = email;
            this.password = password;
        }

        public String getEmail() {
            return email;
        }

        public String getPassword() {
            return password;
        }

        /**
         * Create
         * @param email
         * @param password
         * @return
         */
        public static Params create(final String email, final String password) {
            return new Params(email, password);
        }
    }


}
