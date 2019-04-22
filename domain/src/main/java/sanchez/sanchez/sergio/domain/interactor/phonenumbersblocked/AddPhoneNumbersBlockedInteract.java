package sanchez.sanchez.sergio.domain.interactor.phonenumbersblocked;

import com.fernandocejas.arrow.checks.Preconditions;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.List;

import io.reactivex.Observable;
import sanchez.sanchez.sergio.domain.executor.IPostExecutionThread;
import sanchez.sanchez.sergio.domain.executor.IThreadExecutor;
import sanchez.sanchez.sergio.domain.interactor.UseCase;
import sanchez.sanchez.sergio.domain.models.PhoneNumberBlockedEntity;
import sanchez.sanchez.sergio.domain.models.PhoneNumberNotAllowed;
import sanchez.sanchez.sergio.domain.repository.IPhoneNumbersBlockedRepository;

/**
 * Add Phone Numbers Blocked Interact
 */
public final class AddPhoneNumbersBlockedInteract extends
        UseCase<List<PhoneNumberBlockedEntity>, AddPhoneNumbersBlockedInteract.Params> {

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
    protected Observable<List<PhoneNumberBlockedEntity>> buildUseCaseObservable(Params params) {
        Preconditions.checkNotNull(params, "Params can not be null");
        Preconditions.checkNotNull(params.getKid(), "Kid can not be null");
        Preconditions.checkState(!params.getKid().isEmpty(), "Kid can not be empty");
        Preconditions.checkNotNull(params.getTerminal(), "Terminal can not be null");
        Preconditions.checkState(!params.getTerminal().isEmpty(), "Terminal can not be empty");
        Preconditions.checkNotNull(params.getPhoneNumberBlockedList(), "Phone Number Blocked List can not be null");
        Preconditions.checkState(!params.getPhoneNumberBlockedList().isEmpty(), "Phone Number Blocked List  can not be empty");


        return phoneNumbersBlockedRepository.addPhoneNumberBlocked(params.getKid(), params.getTerminal(),
                params.getPhoneNumberBlockedList());
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
         * Phone Number Blocked List
         */
        private final List<PhoneNumberNotAllowed> phoneNumberBlockedList;


        /**
         *
         * @param kid
         * @param terminal
         * @param prefix
         * @param number
         * @param phoneNumber
         */
        private Params(final String kid, final String terminal, final String prefix, final String number, final String phoneNumber) {
            this(kid, terminal, Collections.singletonList(new PhoneNumberNotAllowed(prefix, number, phoneNumber)));
        }

        /**
         *
         * @param kid
         * @param terminal
         * @param phoneNumberBlocked
         */
        private Params(final String kid, final String terminal, final PhoneNumberNotAllowed phoneNumberBlocked) {
            this(kid, terminal, Collections.singletonList(phoneNumberBlocked));
        }

        /**
         *
         * @param kid
         * @param terminal
         * @param phoneNumberBlockedList
         */
        private Params(final String kid, final String terminal, final List<PhoneNumberNotAllowed> phoneNumberBlockedList) {
            this.kid = kid;
            this.terminal = terminal;
            this.phoneNumberBlockedList = phoneNumberBlockedList;
        }

        public String getKid() {
            return kid;
        }

        public String getTerminal() {
            return terminal;
        }

        public List<PhoneNumberNotAllowed> getPhoneNumberBlockedList() {
            return phoneNumberBlockedList;
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
         * @param phoneNumber
         * @return
         */
        public static Params create(final String kid, final String terminal,
                                    final String phoneNumber) {
            return new Params(kid, terminal, "", phoneNumber, phoneNumber);
        }


        /**
         * Create
         * @param kid
         * @param terminal
         * @param phoneNumberBlocked
         * @return
         */
        public static Params create(final String kid, final String terminal, final PhoneNumberNotAllowed phoneNumberBlocked) {
            return new Params(kid, terminal, phoneNumberBlocked);
        }

        /**
         * Create
         * @param kid
         * @param terminal
         * @param phoneNumberBlockedList
         * @return
         */
        public static Params create(final String kid, final String terminal, final List<PhoneNumberNotAllowed> phoneNumberBlockedList) {
            return new Params(kid, terminal, phoneNumberBlockedList);
        }


    }

}
