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
        Preconditions.checkNotNull(params.getNumber(), "Number can not be null");
        Preconditions.checkState(!params.getNumber().isEmpty(), "Number can not be empty");
        Preconditions.checkNotNull(params.getPhoneNumber(), "Phone Number can not be null");
        Preconditions.checkState(!params.getPhoneNumber().isEmpty(), "Phone Number can not be empty");

        return phoneNumbersBlockedRepository.addPhoneNumberBlocked(params.getKid(), params.getTerminal(),
                params.getPrefix(), params.getNumber(), params.getPhoneNumber());
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
         * Prefix
         */
        private final String prefix;

        /**
         * Number
         */
        private final String number;

        /**
         * Phone Number
         */
        private final String phoneNumber;

        /**
         *
         * @param kid
         * @param terminal
         * @param prefix
         * @param number
         * @param phoneNumber
         */
        private Params(final String kid, final String terminal, final String prefix, final String number, final String phoneNumber) {
            this.kid = kid;
            this.terminal = terminal;
            this.prefix = prefix;
            this.number = number;
            this.phoneNumber = phoneNumber;
        }

        /**
         *
         * @param kid
         * @param terminal
         * @param number
         */
        private Params(final String kid, final String terminal, final String number) {
            this(kid, terminal, "", number, number);
        }

        public String getKid() {
            return kid;
        }

        public String getTerminal() {
            return terminal;
        }

        public String getPrefix() {
            return prefix;
        }

        public String getNumber() {
            return number;
        }

        public String getPhoneNumber() {
            return phoneNumber;
        }

        /**
         * Create
         * @param kid
         * @param terminal
         * @param prefix
         * @param number
         * @param phoneNumber
         * @return
         */
        public static Params create(final String kid, final String terminal, final String prefix, final String number,
                                    final String phoneNumber) {
            return new Params(kid, terminal, prefix, number, phoneNumber);
        }


        /**
         * Create
         * @param kid
         * @param terminal
         * @param number
         * @return
         */
        public static Params create(final String kid, final String terminal, final String number) {
            return new Params(kid, terminal, number);
        }

        @Override
        public String toString() {
            return "Params{" +
                    "kid='" + kid + '\'' +
                    ", terminal='" + terminal + '\'' +
                    ", prefix='" + prefix + '\'' +
                    ", number='" + number + '\'' +
                    ", phoneNumber='" + phoneNumber + '\'' +
                    '}';
        }
    }
}
