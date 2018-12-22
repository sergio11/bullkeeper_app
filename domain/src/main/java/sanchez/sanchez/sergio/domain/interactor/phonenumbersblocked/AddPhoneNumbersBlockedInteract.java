package sanchez.sanchez.sergio.domain.interactor.phonenumbersblocked;

import com.fernandocejas.arrow.checks.Preconditions;
import io.reactivex.Observable;
import sanchez.sanchez.sergio.domain.executor.IPostExecutionThread;
import sanchez.sanchez.sergio.domain.executor.IThreadExecutor;
import sanchez.sanchez.sergio.domain.interactor.UseCase;
import sanchez.sanchez.sergio.domain.models.PhoneNumberBlockedEntity;
import sanchez.sanchez.sergio.domain.repository.IPhoneNumbersBlockedRepository;

/**
 * Add Phone Numbers Blocked Interact
 */
public final class AddPhoneNumbersBlockedInteract extends
        UseCase<PhoneNumberBlockedEntity, AddPhoneNumbersBlockedInteract.Params> {

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
    public AddPhoneNumbersBlockedInteract(
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
    protected Observable<PhoneNumberBlockedEntity> buildUseCaseObservable(Params params) {
        Preconditions.checkNotNull(params, "Params can not be null");
        Preconditions.checkNotNull(params.getKid(), "Kid can not be null");
        Preconditions.checkState(!params.getKid().isEmpty(), "Kid can not be empty");
        Preconditions.checkNotNull(params.getTerminal(), "Terminal can not be null");
        Preconditions.checkState(!params.getTerminal().isEmpty(), "Terminal can not be empty");
        Preconditions.checkNotNull(params.getPhoneNumber(), "Phone Number can not be null");
        Preconditions.checkState(!params.getPhoneNumber().isEmpty(), "Phone Number can not be empty");

        return phoneNumbersBlockedRepository.addPhoneNumberBlocked(params.getKid(), params.getTerminal(),
                params.getPhoneNumber());
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
         * Phone Number
         */
        private final String phoneNumber;

        /**
         *
         * @param kid
         * @param terminal
         * @param phoneNumber
         */
        private Params(final String kid, final String terminal, final String phoneNumber) {
            this.kid = kid;
            this.terminal = terminal;
            this.phoneNumber = phoneNumber;
        }

        public String getKid() {
            return kid;
        }

        public String getTerminal() {
            return terminal;
        }

        public String getPhoneNumber() {
            return phoneNumber;
        }

        /**
         * Create
         * @param kid
         * @param terminal
         * @param phoneNumber
         * @return
         */
        public static Params create(final String kid, final String terminal,
                                    final String phoneNumber) {
            return new Params(kid, terminal, phoneNumber);
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
                    ", phoneNumber='" + phoneNumber + '\'' +
                    '}';
        }
    }
}
