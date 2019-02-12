package sanchez.sanchez.sergio.domain.interactor.conversation;

import com.fernandocejas.arrow.checks.Preconditions;

import io.reactivex.Observable;
import sanchez.sanchez.sergio.domain.executor.IPostExecutionThread;
import sanchez.sanchez.sergio.domain.executor.IThreadExecutor;
import sanchez.sanchez.sergio.domain.interactor.UseCase;
import sanchez.sanchez.sergio.domain.models.MessageEntity;
import sanchez.sanchez.sergio.domain.repository.IConversationRepository;

/**
 * Add Message Interact
 */
public final class AddMessageInteract
        extends UseCase<MessageEntity, AddMessageInteract.Params> {

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
    public AddMessageInteract(
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
    protected Observable<MessageEntity> buildUseCaseObservable(Params params) {
        Preconditions.checkNotNull(params, "Params can not be null");
        return conversationRepository.addMessage(params.getConversation(),
                params.getFrom(), params.getTo(), params.getText());
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
         * From
         */
        private final String from;

        /**
         * To
         */
        private final String to;

        /**
         * Text
         */
        private final String text;

        /**
         *
         * @param conversation
         * @param from
         * @param to
         * @param text
         */
        private Params(final String conversation, final String from,
                       final String to, final String text) {
            this.conversation = conversation;
            this.from = from;
            this.to = to;
            this.text = text;
        }

        /**
         * Get Conversation
         * @return
         */
        public String getConversation() {
            return conversation;
        }

        /**
         * Get From
         * @return
         */
        public String getFrom() {
            return from;
        }

        /**
         * Get To
         * @return
         */
        public String getTo() {
            return to;
        }

        /**
         * Get Text
         * @return
         */
        public String getText() {
            return text;
        }

        /**
         * Create
         * @param conversation
         * @param from
         * @param to
         * @param text
         * @return
         */
        public static Params create(final String conversation, final String from,
                                    final String to, final String text){
            return new Params(conversation, from, to, text);
        }
    }
}
