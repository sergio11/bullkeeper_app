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
 * Get Conversation Messages Interact
 */
public final class GetConversationMessagesInteract
        extends UseCase<List<MessageEntity>, GetConversationMessagesInteract.Params> {

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
    public GetConversationMessagesInteract(final IThreadExecutor threadExecutor,
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
        return conversationRepository.getConversationMessages(params.getId());
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
         * Create
         * @param id
         */
        public static Params create(final String id) {
            return new Params(id);
        }
    }

    /**
     * Get Conversation Messages
     */
    public enum GetConversationMessagesApiErrors
            implements ISupportVisitable<GetConversationMessagesApiErrors.IGetConversationMessagesErrorVisitor> {

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
             * Visit Conversation Messages API Errors
             * @param apiErrors
             */
            void visitNoMessagesFound(final GetConversationMessagesApiErrors apiErrors);
        }

    }
}
