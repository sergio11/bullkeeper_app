package sanchez.sanchez.sergio.domain.interactor.conversation;

import com.fernandocejas.arrow.checks.Preconditions;
import io.reactivex.Observable;
import sanchez.sanchez.sergio.domain.executor.IPostExecutionThread;
import sanchez.sanchez.sergio.domain.executor.IThreadExecutor;
import sanchez.sanchez.sergio.domain.interactor.UseCase;
import sanchez.sanchez.sergio.domain.models.MessageEntity;
import sanchez.sanchez.sergio.domain.repository.IConversationRepository;

/**
 * Add Message By Conversation Id Interact
 */
public final class AddMessageByConversationIdInteract
        extends UseCase<MessageEntity, AddMessageByConversationIdInteract.Params> {

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
    public AddMessageByConversationIdInteract(
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
        return conversationRepository.addMessageByConversationId(params.getConversationId(),
                params.getFrom(), params.getTo(), params.getText());
    }

    /**
     * Params
     */
    public static class Params {

        /**
         * Conversation Id
         */
        private final String conversationId;

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
         * @param conversationId
         * @param from
         * @param to
         * @param text
         */
        private Params(final String conversationId, final String from,
                       final String to, final String text) {
            this.conversationId = conversationId;
            this.from = from;
            this.to = to;
            this.text = text;
        }

        public String getConversationId() {
            return conversationId;
        }

        public String getFrom() {
            return from;
        }

        public String getTo() {
            return to;
        }

        public String getText() {
            return text;
        }

        /**
         * Create
         * @param conversationId
         * @param from
         * @param to
         * @param text
         * @return
         */
        public static Params create(final String conversationId, final String from,
                                    final String to, final String text){
            return new Params(conversationId, from, to, text);
        }
    }
}
