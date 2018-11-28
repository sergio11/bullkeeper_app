package sanchez.sanchez.sergio.domain.interactor.accounts;

import com.fernandocejas.arrow.checks.Preconditions;

import java.util.LinkedHashMap;
import java.util.List;

import io.reactivex.Observable;
import sanchez.sanchez.sergio.domain.executor.IPostExecutionThread;
import sanchez.sanchez.sergio.domain.executor.IThreadExecutor;
import sanchez.sanchez.sergio.domain.interactor.UseCase;
import sanchez.sanchez.sergio.domain.models.GuardianEntity;
import sanchez.sanchez.sergio.domain.repository.IAccountsRepository;
import sanchez.sanchez.sergio.domain.utils.IAppUtils;
import sanchez.sanchez.sergio.domain.utils.ISupportVisitable;
import sanchez.sanchez.sergio.domain.utils.ISupportVisitor;

/**
 * Register Parent Interact
 */
public final class RegisterGuardianInteract extends UseCase<GuardianEntity, RegisterGuardianInteract.Params> {

    /**
     * Account Repository
     */
    private final IAccountsRepository accountsRepository;

    /**
     * App Utils
     */
    private final IAppUtils appUtils;

    /**
     * @param threadExecutor
     * @param postExecutionThread
     */
    public RegisterGuardianInteract(final IThreadExecutor threadExecutor, final IPostExecutionThread postExecutionThread,
                                    final IAccountsRepository accountsRepository, final IAppUtils appUtils) {
        super(threadExecutor, postExecutionThread);
        this.accountsRepository = accountsRepository;
        this.appUtils = appUtils;
    }

    @Override
    protected Observable<GuardianEntity> buildUseCaseObservable(final RegisterGuardianInteract.Params params) {

        Preconditions.checkNotNull(params, "Register params can not be null");

        final String locale = appUtils.getCurrentLocale().toString();

        return accountsRepository.registerParent(params.getFirstName(), params.getLastName(), params.getBirthdate(),
                params.getEmail(), params.getPasswordClear(), params.getConfirmPassword(), locale,
                params.getTelephone());
    }


    /**
     * Params
     */
    public static final class Params {

        private final String firstName;
        private final String lastName;
        private final String birthdate;
        private final String email;
        private final String passwordClear;
        private final String confirmPassword;
        private final String telephone;

        private Params(String firstName, String lastName, String birthdate, String email, String passwordClear, String confirmPassword, String telephone) {
            this.firstName = firstName;
            this.lastName = lastName;
            this.birthdate = birthdate;
            this.email = email;
            this.passwordClear = passwordClear;
            this.confirmPassword = confirmPassword;
            this.telephone = telephone;
        }

        public String getFirstName() {
            return firstName;
        }

        public String getLastName() {
            return lastName;
        }

        public String getBirthdate() {
            return birthdate;
        }

        public String getEmail() {
            return email;
        }

        public String getPasswordClear() {
            return passwordClear;
        }

        public String getConfirmPassword() {
            return confirmPassword;
        }

        public String getTelephone() {
            return telephone;
        }

        /**
         * Create
         * @param firstName
         * @param lastName
         * @param birthdate
         * @param email
         * @param passwordClear
         * @param confirmPassword
         * @param telephone
         * @return
         */
        public static Params create(final String firstName, final String lastName, final String birthdate, final String email,
                                    final String passwordClear, final String confirmPassword, final String telephone) {
            return new Params(firstName, lastName, birthdate, email, passwordClear, confirmPassword, telephone);
        }
    }


    /**
     * Signup Api Errors
     */
    public enum SignupApiErrors implements ISupportVisitable<SignupApiErrors.ISignupApiErrorVisitor> {

        /**
         * Validation Errors
         */
        VALIDATION_ERROR(){
            @Override
            public <E> void accept(ISignupApiErrorVisitor visitor, E data) {
                visitor.visitValidationError(this, (LinkedHashMap<String, List<LinkedHashMap<String, String>>>) data);
            }
        };


        /**
         * Signup Api Error Visitor
         */
        public interface ISignupApiErrorVisitor extends ISupportVisitor {
            /**
             * Visit Validation Errors
             * @param apiErrors
             */
            void visitValidationError(final SignupApiErrors apiErrors, final LinkedHashMap<String, List<LinkedHashMap<String, String>>> errors);
        }

    }


}
