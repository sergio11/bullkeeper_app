package sanchez.sanchez.sergio.domain.interactor.conversation;

import com.fernandocejas.arrow.checks.Preconditions;
import java.util.ArrayList;
import java.util.List;
import io.reactivex.Observable;
import sanchez.sanchez.sergio.domain.executor.IPostExecutionThread;
import sanchez.sanchez.sergio.domain.executor.IThreadExecutor;
import sanchez.sanchez.sergio.domain.interactor.UseCase;
import sanchez.sanchez.sergio.domain.repository.IConversationRepository;

/**
 * Delete Conversation Messages
 */
public final class DeleteConversationMessagesInteract
        extends UseCase<String, DeleteConversationMessagesInteract.Params> {

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
    public DeleteConversationMessagesInteract(final IThreadExecutor threadExecutor,
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
        Preconditions.checkNotNull(params.getConversation(), "Kid can not be null");
        Preconditions.checkState(!params.getConversation().isEmpty(), "Kid can not be empty");
        Preconditions.checkNotNull(params.getMessageIds(), "Message Ids can not be null");

        return conversationRepository.deleteConversationMessages(params.getConversation(), params.getMessageIds());
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
         * Message Ids
         */
        private List<String> messageIds = new ArrayList<>();

        /**
         *
         * @param conversation
         * @param messageIds
         */
        private Params(final String conversation, final List<String> messageIds) {
            this.conversation = conversation;
            this.messageIds = messageIds;
        }
        /**
         *
         * @param conversation
         */
        private Params(final String conversation) {
            this.conversation = conversation;
        }

        /**
         * Get Conversation
         * @return
         */
        public String getConversation() {
            return conversation;
        }

        /**
         * Get Message Ids
         * @return
         */
        public List<String> getMessageIds() {
            return messageIds;
        }

        /**
         * @param conversation
         * @param messageIds
         */
        public static Params create(final String conversation, final List<String> messageIds) {
            return new Params(conversation, messageIds);
        }

        /**
         * @param conversation
         */
        public static Params create(final String conversation) {
            return new Params(conversation);
        }

    }
}
