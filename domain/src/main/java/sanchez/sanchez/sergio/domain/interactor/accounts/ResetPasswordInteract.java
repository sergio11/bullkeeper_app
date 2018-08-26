package sanchez.sanchez.sergio.domain.interactor.accounts;

import com.fernandocejas.arrow.checks.Preconditions;

import io.reactivex.Observable;
import sanchez.sanchez.sergio.domain.executor.IPostExecutionThread;
import sanchez.sanchez.sergio.domain.executor.IThreadExecutor;
import sanchez.sanchez.sergio.domain.interactor.UseCase;
import sanchez.sanchez.sergio.domain.repository.IAccountsRepository;

/**
 * Reset Password Interact
 */
public final class ResetPasswordInteract extends UseCase<String, ResetPasswordInteract.Params> {

    private final IAccountsRepository accountsRepository;

    /**
     * @param threadExecutor
     * @param postExecutionThread
     */
    public ResetPasswordInteract(final IThreadExecutor threadExecutor, final IPostExecutionThread postExecutionThread,
                                 final IAccountsRepository accountsRepository) {
        super(threadExecutor, postExecutionThread);
        this.accountsRepository = accountsRepository;
    }

    /**
     * Build User Case Observable
     * @param params
     * @return
     */
    @Override
    protected Observable<String> buildUseCaseObservable(final ResetPasswordInteract.Params params) {
        Preconditions.checkNotNull(params, "Reset Password Request can not be null");
        Preconditions.checkNotNull(params.getEmail(), "Email can not be null");
        // Reset Password
        return accountsRepository.resetPassword(params.getEmail());
    }


    /**
     * Params
     */
    public static final class Params {

        private final String email;

        /**
         * @param email
         */
        private Params(final String email) {
            this.email = email;
        }

        public String getEmail() {
            return email;
        }

        /**
         * Create
         * @param email
         * @return
         */
        public static Params create(final String email) {
            return new Params(email);
        }
    }

}
