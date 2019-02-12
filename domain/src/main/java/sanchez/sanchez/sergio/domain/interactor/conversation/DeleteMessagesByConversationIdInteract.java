package sanchez.sanchez.sergio.domain.interactor.conversation;

import com.fernandocejas.arrow.checks.Preconditions;
import java.util.List;
import io.reactivex.Observable;
import sanchez.sanchez.sergio.domain.executor.IPostExecutionThread;
import sanchez.sanchez.sergio.domain.executor.IThreadExecutor;
import sanchez.sanchez.sergio.domain.interactor.UseCase;
import sanchez.sanchez.sergio.domain.repository.IConversationRepository;

/**
 * Delete Messages By Conversation Id Interact
 */
public final class DeleteMessagesByConversationIdInteract
        extends UseCase<String, DeleteMessagesByConversationIdInteract.Params> {

    /**
     * Conversation Repository
     */
    private final IConversationRepository conversationRepository;

    /**
     * Abstract class for a Use Case
     *
     * @param threadExecutor
     * @param postExecutionThread
     */
    public DeleteMessagesByConversationIdInteract(
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
        Preconditions.checkNotNull(params.getMessageIds(), "Message ids can not be null");

        return conversationRepository.deleteConversationMessages(params.getId(), params.getMessageIds());
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
         * Message Ids
         */
        private List<String> messageIds;

        /**
         *
         * @param id
         * @param messageIds
         */
        private Params(final String id, final List<String> messageIds) {
            this.id = id;
            this.messageIds = messageIds;
        }

        /**
         * @param id
         */
        private Params(final String id) {
            this.id = id;
        }


        public String getId() {
            return id;
        }

        public List<String> getMessageIds() {
            return messageIds;
        }

        public void setMessageIds(List<String> messageIds) {
            this.messageIds = messageIds;
        }

        /**
         *
         * @param kid
         */
        public static Params create(final String kid) {
            return new Params(kid);
        }

        /**
         *
         * @param kid
         */
        public static Params create(final String kid, final List<String> messageIds) {
            return new Params(kid, messageIds);
        }
    }
}
