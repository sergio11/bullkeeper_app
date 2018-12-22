package sanchez.sanchez.sergio.domain.interactor.phonenumbersblocked;

import com.fernandocejas.arrow.checks.Preconditions;
import java.util.List;
import io.reactivex.Observable;
import sanchez.sanchez.sergio.domain.executor.IPostExecutionThread;
import sanchez.sanchez.sergio.domain.executor.IThreadExecutor;
import sanchez.sanchez.sergio.domain.interactor.UseCase;
import sanchez.sanchez.sergio.domain.models.CallDetailEntity;
import sanchez.sanchez.sergio.domain.models.PhoneNumberBlockedEntity;
import sanchez.sanchez.sergio.domain.repository.IPhoneNumbersBlockedRepository;
import sanchez.sanchez.sergio.domain.utils.ISupportVisitable;
import sanchez.sanchez.sergio.domain.utils.ISupportVisitor;

/**
 * Get Phone Numbers Blocked Interact
 */
public final class GetPhoneNumbersBlockedInteract extends
        UseCase<List<PhoneNumberBlockedEntity>, GetPhoneNumbersBlockedInteract.Params> {

    /**
     * Phone Numbers Repository
     */
    private final IPhoneNumbersBlockedRepository phoneNumbersBlockedRepository;

    /**
     * Abstract class for a Use Case
     *
     * @param threadExecutor
     * @param postExecutionThread
     * @param phoneNumbersBlockedRepository
     */
    public GetPhoneNumbersBlockedInteract(
            final IThreadExecutor threadExecutor,
            final IPostExecutionThread postExecutionThread,
            final IPhoneNumbersBlockedRepository phoneNumbersBlockedRepository) {
        super(threadExecutor, postExecutionThread);
        this.phoneNumbersBlockedRepository = phoneNumbersBlockedRepository;
    }

    /**
     *
     * @param params
     * @return
     */
    @Override
    protected Observable<List<PhoneNumberBlockedEntity>> buildUseCaseObservable(Params params) {
        Preconditions.checkNotNull(params, "Params can not be null");
        Preconditions.checkNotNull(params.getKid(), "Kid can not be null");
        Preconditions.checkState(!params.getKid().isEmpty(), "Kid can not be empty");
        Preconditions.checkNotNull(params.getTerminal(), "Params can not be null");
        Preconditions.checkState(!params.getTerminal().isEmpty(), "Terminal can not be empty");

        return phoneNumbersBlockedRepository.getPhoneNumberBlocked(params.getKid(), params.getTerminal());
    }

    /**
     * Params
     */
    public static class Params {

        /**
         * Kid
         */
        private final String kid;

        /**
         * Terminal
         */
        private final String terminal;

        /**
         *
         * @param kid
         * @param terminal
         */
        private Params(final String kid, final String terminal) {
            this.kid = kid;
            this.terminal = terminal;
        }

        public String getKid() {
            return kid;
        }

        public String getTerminal() {
            return terminal;
        }

        /**
         * Create
         * @param kid
         * @param terminal
         * @return
         */
        public static Params create(final String kid, final String terminal) {
            return new Params(kid, terminal);
        }

        /**
         * To String
         * @return
         */
        @Override
        public String toString() {
            return "Params{" +
                    "kid='" + kid + '\'' +
                    ", terminal='" + terminal + '\'' +
                    '}';
        }
    }

    /**
     * Get Phone Numbers Blocked List Api Errors
     */
    public enum GetPhoneNumbersBlockedListApiErrors
            implements ISupportVisitable<GetPhoneNumbersBlockedListApiErrors.IGetPhoneNumbersListApiErrorsVisitor> {

        /**
         * No Phone Numbers Blocked Found
         */
        NO_PHONE_NUMBERS_BLOCKED_FOUND(){
            @Override
            public <E> void accept(final IGetPhoneNumbersListApiErrorsVisitor visitor, E data) {
                visitor.visitNoPhoneNumbersBlockedFound(visitor);
            }
        };

        /**
         * Get Phone Numbers List Api Errors Visitor
         */
        public interface IGetPhoneNumbersListApiErrorsVisitor extends ISupportVisitor {

            /**
             * Visit No Phone Numbers Blocked
             * @param apiErrorsVisitor
             */
            void visitNoPhoneNumbersBlockedFound(final IGetPhoneNumbersListApiErrorsVisitor apiErrorsVisitor);
        }
    }
}
