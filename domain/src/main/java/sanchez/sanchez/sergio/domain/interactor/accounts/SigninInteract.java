package sanchez.sanchez.sergio.domain.interactor.accounts;

import com.fernandocejas.arrow.checks.Preconditions;
import io.reactivex.Observable;
import sanchez.sanchez.sergio.domain.executor.IPostExecutionThread;
import sanchez.sanchez.sergio.domain.executor.IThreadExecutor;
import sanchez.sanchez.sergio.domain.interactor.UseCase;
import sanchez.sanchez.sergio.domain.repository.IAccountsRepository;
import sanchez.sanchez.sergio.domain.utils.ISupportVisitable;
import sanchez.sanchez.sergio.domain.utils.ISupportVisitor;

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

    /**
     * Signin Api Errors
     */
    public enum SigninApiErrors implements ISupportVisitable<SigninApiErrors.ISigninApiErrorVisitor> {

        /**
         * Bad Credentials Error
         */
        BAD_CREDENTIALS(){
            @Override
            public <E> void accept(ISigninApiErrorVisitor visitor, E data) {
                visitor.visitBadCredentials(this);
            }
        },
        /**
         * Account Disabled
         */
        ACCOUNT_DISABLED() {
            @Override
            public <E> void accept(ISigninApiErrorVisitor visitor, E data) {
                visitor.visitAccountDisabled(this);
            }
        },
        /**
         * Account Pending To Be Remove
         */
        ACCOUNT_PENDING_TO_BE_REMOVE() {
            @Override
            public <E> void accept(ISigninApiErrorVisitor visitor, E data) {
                visitor.visitAccountPendingToBeRemove(this);
            }
        };


        /**
         * Signin Api Error Visitor
         */
        public interface ISigninApiErrorVisitor extends ISupportVisitor {
            /**
             * Visit Bad Credentials
             * @param error
             */
            void visitBadCredentials(final SigninApiErrors error);

            /**
             * Visit Account Disabled
             * @param errors
             */
            void visitAccountDisabled(final SigninApiErrors errors);

            /**
             * Visit Account Pending To Be Remove
             * @param error
             */
            void visitAccountPendingToBeRemove(final SigninApiErrors error);
        }

    }

}
