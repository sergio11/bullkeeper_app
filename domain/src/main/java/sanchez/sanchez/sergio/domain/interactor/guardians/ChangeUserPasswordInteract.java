package sanchez.sanchez.sergio.domain.interactor.guardians;

import com.fernandocejas.arrow.checks.Preconditions;

import java.util.LinkedHashMap;
import java.util.List;

import io.reactivex.Observable;
import sanchez.sanchez.sergio.domain.executor.IPostExecutionThread;
import sanchez.sanchez.sergio.domain.executor.IThreadExecutor;
import sanchez.sanchez.sergio.domain.interactor.UseCase;
import sanchez.sanchez.sergio.domain.repository.IGuardianRepository;
import sanchez.sanchez.sergio.domain.utils.ISupportVisitable;
import sanchez.sanchez.sergio.domain.utils.ISupportVisitor;

/**
 * Change User Password Interact
 **/
public final class ChangeUserPasswordInteract extends UseCase<String, ChangeUserPasswordInteract.Params> {

    /**
     * Guardian Repository
     */
    private final IGuardianRepository guardianRepository;


    /**
     * Abstract class for a Use Case
     *
     * @param threadExecutor
     * @param postExecutionThread
     * @param guardianRepository
     */
    public ChangeUserPasswordInteract(final IThreadExecutor threadExecutor,
                                      final IPostExecutionThread postExecutionThread,
                                      final IGuardianRepository guardianRepository) {
        super(threadExecutor, postExecutionThread);
        this.guardianRepository = guardianRepository;
    }

    /**
     *
     * @param params
     * @return
     */
    @Override
    protected Observable<String> buildUseCaseObservable(final Params params) {
        Preconditions.checkNotNull(params, "Params can not be null");
        Preconditions.checkNotNull(params.getNewPassword(), "New Password can not be null");
        Preconditions.checkNotNull(params.getConfirmNewPassword(), "Confirm new password can not be null");

        return guardianRepository.changeUserPassword(params.newPassword, params.confirmNewPassword);
    }

    /**
     * Params
     */
    public static class Params {

        private final String newPassword;
        private final String confirmNewPassword;

        /**
         *
         * @param newPassword
         * @param confirmNewPassword
         */
        private Params(final String newPassword, final String confirmNewPassword) {
            this.newPassword = newPassword;
            this.confirmNewPassword = confirmNewPassword;
        }

        public String getNewPassword() {
            return newPassword;
        }

        public String getConfirmNewPassword() {
            return confirmNewPassword;
        }

        /**
         * Create
         * @param newPassword
         * @param confirmNewPassword
         * @return
         */
        public static Params create(final String newPassword, final String confirmNewPassword){
            Preconditions.checkNotNull(newPassword, "New Password can not be null");
            Preconditions.checkNotNull(confirmNewPassword, "Confirm New Password can not be null");
            return new Params(newPassword, confirmNewPassword);
        }

        @Override
        public String toString() {
            return "Params{" +
                    "newPassword='" + newPassword + '\'' +
                    ", confirmNewPassword='" + confirmNewPassword + '\'' +
                    '}';
        }
    }



    /**
     * Change User Password Api Errors
     */
    public enum ChangeUserPasswordApiErrors implements ISupportVisitable<ChangeUserPasswordApiErrors.IChangeUserPasswordApiErrorVisitor> {

        /**
         * Validation Errors
         */
        VALIDATION_ERROR(){
            @Override
            public <E> void accept(IChangeUserPasswordApiErrorVisitor visitor, E data) {
                visitor.visitValidationError(this, (LinkedHashMap<String, List<LinkedHashMap<String, String>>>) data);
            }
        };

        /**
         * Change User Password Api Error Visitor
         */
        public interface IChangeUserPasswordApiErrorVisitor extends ISupportVisitor {

            /**
             * Visit Validation Error
             * @param apiErrors
             * @param errors
             */
            void visitValidationError(final ChangeUserPasswordApiErrors apiErrors, final LinkedHashMap<String, List<LinkedHashMap<String, String>>> errors);

        }
    }
}
