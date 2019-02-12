package sanchez.sanchez.sergio.domain.interactor.conversation;

import com.fernandocejas.arrow.checks.Preconditions;

import io.reactivex.Observable;
import sanchez.sanchez.sergio.domain.executor.IPostExecutionThread;
import sanchez.sanchez.sergio.domain.executor.IThreadExecutor;
import sanchez.sanchez.sergio.domain.interactor.UseCase;
import sanchez.sanchez.sergio.domain.repository.IConversationRepository;

/**
 * Delete Conversation Interact
 */
public final class DeleteConversationInteract
        extends UseCase<String, DeleteConversationInteract.Params> {

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
    public DeleteConversationInteract(final IThreadExecutor threadExecutor,
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
    protected Observable<String> buildUseCaseObservable(final Params params) {
        Preconditions.checkNotNull(params, "Params can not be null");
        Preconditions.checkNotNull(params.getConversation(), "Kid can not be null");
        Preconditions.checkState(!params.getConversation().isEmpty(), "Kid can not be empty");
        // Delete Conversation
        return conversationRepository.deleteConversationMessages(params.getConversation());
    }

    /**
     * Params
     */
    public static class Params {

        /**
         * Conversation
         */
        private final String conversation;

        /**
         * @param conversation
         */
        private Params(String conversation) {
            this.conversation = conversation;
        }

        public String getConversation() {
            return conversation;
        }

        /**
         *
         * @param conversation
         */
        public static Params create(final String conversation) {
            return new Params(conversation);
        }
    }
}
