package sanchez.sanchez.sergio.domain.interactor.phonenumbersblocked;

import com.fernandocejas.arrow.checks.Preconditions;

import java.util.Collections;
import java.util.List;

import io.reactivex.Observable;
import sanchez.sanchez.sergio.domain.executor.IPostExecutionThread;
import sanchez.sanchez.sergio.domain.executor.IThreadExecutor;
import sanchez.sanchez.sergio.domain.interactor.UseCase;
import sanchez.sanchez.sergio.domain.repository.IPhoneNumbersBlockedRepository;

/**
 * Unlock Phone Numbers Blocked Interact
 */
public final class DeletePhoneNumbersBlockedInteract extends
        UseCase<String, DeletePhoneNumbersBlockedInteract.Params> {

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
    public DeletePhoneNumbersBlockedInteract(
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
    protected Observable<String> buildUseCaseObservable(final Params params) {
        Preconditions.checkNotNull(params, "Params can not be null");
        Preconditions.checkNotNull(params.getKid(), "Kid can not be null");
        Preconditions.checkState(!params.getKid().isEmpty(), "Kid can not be empty");
        Preconditions.checkNotNull(params.getTerminal(), "Terminal can not be null");
        Preconditions.checkState(!params.getTerminal().isEmpty(), "Terminal can not be empty");
        Preconditions.checkNotNull(params.getPhoneNumberList(), "Phone Number List can not be null");
        Preconditions.checkState(!params.getPhoneNumberList().isEmpty(), "Phone Number List can not be empty");

        return phoneNumbersBlockedRepository.deletePhoneNumberBlocked(params.getKid(), params.getTerminal(),
                params.getPhoneNumberList());
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
         * Phone Number List
         */
        private final List<String> phoneNumberList;

        /**
         *
         * @param kid
         * @param terminal
         * @param phoneNumber
         */
        private Params(final String kid, final String terminal, final String phoneNumber) {
            this.kid = kid;
            this.terminal = terminal;
            this.phoneNumberList = Collections.singletonList(phoneNumber);
        }

        /**
         *
         * @param kid
         * @param terminal
         * @param phoneNumberList
         */
        private Params(final String kid, final String terminal, List<String> phoneNumberList) {
            this.kid = kid;
            this.terminal = terminal;
            this.phoneNumberList = phoneNumberList;
        }

        public String getKid() {
            return kid;
        }

        public String getTerminal() {
            return terminal;
        }

        public List<String> getPhoneNumberList() {
            return phoneNumberList;
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
         *
         * @param kid
         * @param terminal
         * @param phoneNumberList
         * @return
         */
        public static Params create(final String kid, final String terminal, final List<String> phoneNumberList) {
            return new Params(kid, terminal, phoneNumberList);
        }

        @Override
        public String toString() {
            return "Params{" +
                    "kid='" + kid + '\'' +
                    ", terminal='" + terminal + '\'' +
                    ", phoneNumberList=" + phoneNumberList +
                    '}';
        }
    }
}
