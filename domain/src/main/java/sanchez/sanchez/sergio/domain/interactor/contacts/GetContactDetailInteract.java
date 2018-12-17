package sanchez.sanchez.sergio.domain.interactor.contacts;

import com.fernandocejas.arrow.checks.Preconditions;
import io.reactivex.Observable;
import sanchez.sanchez.sergio.domain.executor.IPostExecutionThread;
import sanchez.sanchez.sergio.domain.executor.IThreadExecutor;
import sanchez.sanchez.sergio.domain.interactor.UseCase;
import sanchez.sanchez.sergio.domain.models.ContactEntity;
import sanchez.sanchez.sergio.domain.repository.IContactsRepository;

/**
 * Get Contact Detail Interact
 */
public final class GetContactDetailInteract
        extends UseCase<ContactEntity, GetContactDetailInteract.Params> {

    /**
     * Contact Repository
     */
    private final IContactsRepository contactsRepository;

    /**
     * @param threadExecutor
     * @param postExecutionThread
     * @param contactsRepository
     */
    public GetContactDetailInteract(final IThreadExecutor threadExecutor,
                                    final IPostExecutionThread postExecutionThread,
                                    final IContactsRepository contactsRepository) {
        super(threadExecutor, postExecutionThread);
        this.contactsRepository = contactsRepository;
    }

    /**
     * @param params
     * @return
     */
    @Override
    protected Observable<ContactEntity> buildUseCaseObservable(final Params params) {
        Preconditions.checkNotNull(params, "Params can not be null");
        Preconditions.checkNotNull(params.getKid(), "Kid can not be null");
        Preconditions.checkState(!params.getKid().isEmpty(), "Kid id can not be null");
        Preconditions.checkNotNull(params.getTerminal(), "Params can not be null");
        Preconditions.checkState(!params.getTerminal().isEmpty(),
                "Terminal Id can not be null");
        Preconditions.checkNotNull(params.getContact(), "Contact can not be null");
        Preconditions.checkState(!params.getContact().isEmpty(),
                "Contact id can not be null");

        return contactsRepository.getContactDetail(params.getKid(),
                params.getTerminal(), params.getContact());
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
         * Contact
         */
        private final String contact;

        /**
         * Params
         * @param kid
         * @param terminal
         * @param contact
         */
        private Params(final String kid, final String terminal, final String contact) {
            this.kid = kid;
            this.terminal = terminal;
            this.contact = contact;
        }

        public String getKid() {
            return kid;
        }

        public String getTerminal() {
            return terminal;
        }

        public String getContact() {
            return contact;
        }

        /**
         * Create
         * @param kid
         * @param terminal
         * @param contact
         * @return
         */
        public static Params create(
                final String kid,
                final String terminal,
                final String contact){
            return new Params(kid, terminal, contact);
        }

        @Override
        public String toString() {
            return "Params{" +
                    "kid='" + kid + '\'' +
                    ", terminal='" + terminal + '\'' +
                    ", contact='" + contact + '\'' +
                    '}';
        }
    }
}
