package sanchez.sanchez.sergio.domain.interactor.conversation;

import com.fernandocejas.arrow.checks.Preconditions;

import java.util.List;

import io.reactivex.Observable;
import sanchez.sanchez.sergio.domain.executor.IPostExecutionThread;
import sanchez.sanchez.sergio.domain.executor.IThreadExecutor;
import sanchez.sanchez.sergio.domain.interactor.UseCase;
import sanchez.sanchez.sergio.domain.repository.IConversationRepository;

/**
 * Set Messages As Viewed Interact
 */
public final class SetMessagesAsViewedInteract
        extends UseCase<String, SetMessagesAsViewedInteract.Params> {

    /**
     * Conversation Repository
     */
    private final IConversationRepository conversationRepository;


    /**
     * @param threadExecutor
     * @param postExecutionThread
     * @param conversationRepository
     */
    public SetMessagesAsViewedInteract(final IThreadExecutor threadExecutor,
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
        Preconditions.checkNotNull(params.getConversation(), "Conversation can not be null");
        Preconditions.checkNotNull(params.getMessageIds(), "Message Ids can not be null");

        return conversationRepository.setMessagesAsViewed(params.getConversation(),
                params.getMessageIds());
    }

    /**
     *
     */
    public static class Params {

        /**
         * Conversation
         */
        private final String conversation;

        /**
         * Message Ids
         */
        private final List<String> messageIds;

        /**
         *
         * @param conversation
         * @param messageIds
         */
        private Params(final String conversation, final List<String> messageIds) {
            this.conversation = conversation;
            this.messageIds = messageIds;
        }

        public String getConversation() {
            return conversation;
        }

        public List<String> getMessageIds() {
            return messageIds;
        }

        /**
         * Create
         * @param conversation
         * @param messageIds
         * @return
         */
        public static Params create(final String conversation, final List<String> messageIds) {
            return new Params(conversation, messageIds);
        }

        @Override
        public String toString() {
            return "Params{" +
                    "conversation='" + conversation + '\'' +
                    ", messageIds=" + messageIds +
                    '}';
        }
    }
}
