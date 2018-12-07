package sanchez.sanchez.sergio.domain.interactor.conversation;

import com.fernandocejas.arrow.checks.Preconditions;
import java.util.List;
import io.reactivex.Observable;
import sanchez.sanchez.sergio.domain.executor.IPostExecutionThread;
import sanchez.sanchez.sergio.domain.executor.IThreadExecutor;
import sanchez.sanchez.sergio.domain.interactor.UseCase;
import sanchez.sanchez.sergio.domain.models.MessageEntity;
import sanchez.sanchez.sergio.domain.repository.IConversationRepository;
import sanchez.sanchez.sergio.domain.utils.ISupportVisitable;
import sanchez.sanchez.sergio.domain.utils.ISupportVisitor;

/**
 * Get Messages By Conversation Id
 */
public final class GetMessagesByConversationIdInteract
    extends UseCase<List<MessageEntity>, GetMessagesByConversationIdInteract.Params> {

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
    public GetMessagesByConversationIdInteract(
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
    protected Observable<List<MessageEntity>> buildUseCaseObservable(Params params) {
        Preconditions.checkNotNull(params, "Params can not be null");
        Preconditions.checkNotNull(params.getId(), "Id can not be null");
        Preconditions.checkState(!params.getId().isEmpty(), "Id can not be empty");

        return conversationRepository.getMessagesByConversationId(params.getId());
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

    /**
     * Get Messages By Conversation Id
     */
    public enum GetMessagesByConversationIdApiErrors
            implements ISupportVisitable<GetMessagesByConversationIdApiErrors.IGetConversationMessagesErrorVisitor> {

        /**
         * No Messages Found
         */
        NO_MESSAGES_FOUND(){
            @Override
            public <E> void accept(IGetConversationMessagesErrorVisitor visitor, E data) {
                visitor.visitNoMessagesFound(this);
            }
        };


        /**
         * Get Conversation Messages Error Visitor
         */
        public interface IGetConversationMessagesErrorVisitor extends ISupportVisitor {

            /**
             * Visit No Messages found
             * @param apiErrors
             */
            void visitNoMessagesFound(final GetMessagesByConversationIdApiErrors apiErrors);
        }

    }
}
