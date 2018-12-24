package sanchez.sanchez.sergio.domain.interactor.contacts;

import com.fernandocejas.arrow.checks.Preconditions;
import java.util.List;
import io.reactivex.Observable;
import sanchez.sanchez.sergio.domain.executor.IPostExecutionThread;
import sanchez.sanchez.sergio.domain.executor.IThreadExecutor;
import sanchez.sanchez.sergio.domain.interactor.UseCase;
import sanchez.sanchez.sergio.domain.models.ContactEntity;
import sanchez.sanchez.sergio.domain.repository.IContactsRepository;
import sanchez.sanchez.sergio.domain.utils.ISupportVisitable;
import sanchez.sanchez.sergio.domain.utils.ISupportVisitor;

/**
 * Get Contact List Interact
 */
public final class GetContactListInteract extends
        UseCase<List<ContactEntity>, GetContactListInteract.Params> {

    /**
     * Contact Repository
     */
    private final IContactsRepository contactsRepository;

    /**
     *
     * @param threadExecutor
     * @param postExecutionThread
     * @param contactsRepository
     */
    public GetContactListInteract(final IThreadExecutor threadExecutor,
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
    protected Observable<List<ContactEntity>> buildUseCaseObservable(final Params params) {
        Preconditions.checkNotNull(params, "Params can not be null");
        Preconditions.checkNotNull(params.getKid(), "Kid can not be null");
        Preconditions.checkState(!params.getKid().isEmpty(), "Kid id can not be null");
        Preconditions.checkNotNull(params.getTerminal(), "Terminal can not be null");
        Preconditions.checkState(!params.getTerminal().isEmpty(),
                "Terminal can not be null");

        return params.getQuery() != null ?
                contactsRepository.getContactsList(
                        params.getKid(), params.getTerminal(), params.getQuery()) :
                contactsRepository.getContactsList(params.getKid(), params.getTerminal());

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
         * Query
         */
        private final String query;

        /**
         *
         * @param kid
         * @param terminal
         * @param query
         */
        private Params(final String kid, final String terminal, final String query) {
            this.kid = kid;
            this.terminal = terminal;
            this.query = query;
        }


        public String getKid() {
            return kid;
        }

        public String getTerminal() {
            return terminal;
        }

        public String getQuery() {
            return query;
        }


        /**
         * Create
         * @param kid
         * @param terminal
         * @return
         */
        public static Params create(final String kid, final String terminal){
            return create(kid, terminal, null);
        }

        /**
         * Create
         * @param kid
         * @param terminal
         * @param query
         * @return
         */
        public static Params create(final String kid, final String terminal, final String query){
            return new Params(kid, terminal, query);
        }

        @Override
        public String toString() {
            return "Params{" +
                    "kid='" + kid + '\'' +
                    ", terminal='" + terminal + '\'' +
                    '}';
        }
    }

    /**
     * Get Contact List Api Errors
     */
    public enum GetContactListApiErrors
            implements ISupportVisitable<GetContactListApiErrors.IGetContactListApiErrorsVisitor> {

        /**
         * No Contacts Found
         */
        NO_CONTACTS_FOUND(){
            @Override
            public <E> void accept(final IGetContactListApiErrorsVisitor visitor, E data) {
                visitor.visitNoContactsFound(visitor);
            }
        };

        /**
         * Get Contact List Api Errors Visitor
         */
        public interface IGetContactListApiErrorsVisitor extends ISupportVisitor {

            /**
             * Visit No Contacts Found
             * @param apiErrorsVisitor
             */
            void visitNoContactsFound(final IGetContactListApiErrorsVisitor apiErrorsVisitor);
        }
    }
}
