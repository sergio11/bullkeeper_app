package sanchez.sanchez.sergio.domain.interactor.conversation;

import com.fernandocejas.arrow.checks.Preconditions;

import io.reactivex.Observable;
import sanchez.sanchez.sergio.domain.executor.IPostExecutionThread;
import sanchez.sanchez.sergio.domain.executor.IThreadExecutor;
import sanchez.sanchez.sergio.domain.interactor.UseCase;
import sanchez.sanchez.sergio.domain.repository.IConversationRepository;

/**
 * Delete Conversation By Id Interact
 */
public final class DeleteConversationByIdInteract
        extends UseCase<String, DeleteConversationByIdInteract.Params> {

    /**
     * Conversation Repository
     */
    private final IConversationRepository conversationRepository;

    /**
     * Abstract class for a Use Case
     *
     * @param threadExecutor
     * @param postExecutionThread
     * @param conversationRepository
     */
    public DeleteConversationByIdInteract(
            final IThreadExecutor threadExecutor,
            final IPostExecutionThread postExecutionThread,
            final IConversationRepository conversationRepository) {
        super(threadExecutor, postExecutionThread);
        this.conversationRepository = conversationRepository;
    }

    /**
     *
     * @param params
     * @return
     */
    @Override
    protected Observable<String> buildUseCaseObservable(Params params) {
        Preconditions.checkNotNull(params, "Params can not be null");
        Preconditions.checkNotNull(params.getId(), "Kid can not be null");
        Preconditions.checkState(!params.getId().isEmpty(), "Kid can not be empty");

        return conversationRepository.deleteConversationById(params.getId());
    }

    /**
     * Params
     */
    public static class Params {

        /**
         * Id
         */
        private final String id;

        /**
         * @param id
         */
        private Params(String id) {
            this.id = id;
        }

        public String getId() {
            return id;
        }

        /**
         *
         * @param kid
         */
        public static Params create(final String kid) {
            return new Params(kid);
        }
    }
}
