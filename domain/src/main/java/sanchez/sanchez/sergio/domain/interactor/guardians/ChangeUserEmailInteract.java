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
 * Change User Email Interact
 **/
public final class ChangeUserEmailInteract extends UseCase<String, ChangeUserEmailInteract.Params> {

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
    public ChangeUserEmailInteract(final IThreadExecutor threadExecutor,
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
        Preconditions.checkNotNull(params.getCurrentEmail(), "Current Email can not be null");
        Preconditions.checkNotNull(params.getNewEmail(), "New Email can not be null");

        return guardianRepository.changeUserEmail(params.currentEmail, params.newEmail);
    }

    /**
     *
     */
    public static class Params {

        private final String currentEmail;
        private final String newEmail;

        /**
         *
         * @param currentEmail
         * @param newEmail
         */
        private Params(final String currentEmail, final String newEmail) {
            this.currentEmail = currentEmail;
            this.newEmail = newEmail;
        }

        public String getCurrentEmail() {
            return currentEmail;
        }

        public String getNewEmail() {
            return newEmail;
        }

        /**
         * Create
         * @param currentEmail
         * @param newEmail
         * @return
         */
        public static Params create(final String currentEmail, final String newEmail){
            Preconditions.checkNotNull(currentEmail, "Current Email can not be null");
            Preconditions.checkNotNull(newEmail, "New Email can not be null");
            return new Params(currentEmail, newEmail);
        }

        @Override
        public String toString() {
            return "Params{" +
                    "currentEmail='" + currentEmail + '\'' +
                    ", newEmail='" + newEmail + '\'' +
                    '}';
        }
    }



    /**
     * Change User Email Api Errors
     */
    public enum ChangeUserEmailApiErrors implements ISupportVisitable<ChangeUserEmailApiErrors.IChangeUserEmailApiErrorVisitor> {

        /**
         * Validation Errors
         */
        VALIDATION_ERROR(){
            @Override
            public <E> void accept(IChangeUserEmailApiErrorVisitor visitor, E data) {
                visitor.visitValidationError(this, (LinkedHashMap<String, List<LinkedHashMap<String, String>>>) data);
            }
        };

        /**
         * Change User Email Api Error Visitor
         */
        public interface IChangeUserEmailApiErrorVisitor extends ISupportVisitor {

            /**
             * Visit Validation Error
             * @param apiErrors
             * @param errors
             */
            void visitValidationError(final ChangeUserEmailApiErrors apiErrors, final LinkedHashMap<String, List<LinkedHashMap<String, String>>> errors);

        }
    }
}
