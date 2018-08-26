package sanchez.sanchez.sergio.domain.interactor.accounts;

import com.fernandocejas.arrow.checks.Preconditions;

import io.reactivex.Observable;
import sanchez.sanchez.sergio.domain.executor.IPostExecutionThread;
import sanchez.sanchez.sergio.domain.executor.IThreadExecutor;
import sanchez.sanchez.sergio.domain.interactor.UseCase;
import sanchez.sanchez.sergio.domain.models.ParentEntity;
import sanchez.sanchez.sergio.domain.repository.IAccountsRepository;
import sanchez.sanchez.sergio.domain.utils.IAppUtils;

/**
 * Register Parent Interact
 */
public final class RegisterParentInteract extends UseCase<ParentEntity, RegisterParentInteract.Params> {

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
    public RegisterParentInteract(final IThreadExecutor threadExecutor, final IPostExecutionThread postExecutionThread,
                                  final IAccountsRepository accountsRepository, final IAppUtils appUtils) {
        super(threadExecutor, postExecutionThread);
        this.accountsRepository = accountsRepository;
        this.appUtils = appUtils;
    }

    @Override
    protected Observable<ParentEntity> buildUseCaseObservable(final RegisterParentInteract.Params params) {

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


}
