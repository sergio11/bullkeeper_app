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
        Preconditions.checkNotNull(params.getKid(), "Kid can not be null");
        Preconditions.checkState(!params.getKid().isEmpty(), "Kid can not be empty");
        Preconditions.checkNotNull(params.getMessageIds(), "Message Ids can not be null");

        return conversationRepository.deleteConversationMessages(params.getKid(), params.getMessageIds());
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
         * Message Ids
         */
        private List<String> messageIds = new ArrayList<>();

        /**
         *
         * @param kid
         * @param messageIds
         */
        private Params(final String kid, final List<String> messageIds) {
            this.kid = kid;
            this.messageIds = messageIds;
        }
        /**
         *
         * @param kid
         */
        private Params(final String kid) {
            this.kid = kid;
        }

        public String getKid() {
            return kid;
        }

        public List<String> getMessageIds() {
            return messageIds;
        }

        /**
         * @param kid
         * @param messageIds
         */
        public static Params create(final String kid, final List<String> messageIds) {
            return new Params(kid, messageIds);
        }

        /**
         * @param kid
         */
        public static Params create(final String kid) {
            return new Params(kid);
        }

    }
}
