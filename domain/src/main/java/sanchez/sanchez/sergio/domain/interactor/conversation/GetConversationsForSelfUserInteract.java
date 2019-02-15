package sanchez.sanchez.sergio.domain.interactor.conversation;

import java.util.List;
import io.reactivex.Observable;
import sanchez.sanchez.sergio.domain.executor.IPostExecutionThread;
import sanchez.sanchez.sergio.domain.executor.IThreadExecutor;
import sanchez.sanchez.sergio.domain.interactor.UseCase;
import sanchez.sanchez.sergio.domain.models.ConversationEntity;
import sanchez.sanchez.sergio.domain.repository.IConversationRepository;
import sanchez.sanchez.sergio.domain.utils.ISupportVisitable;
import sanchez.sanchez.sergio.domain.utils.ISupportVisitor;

/**
 * Get Conversation For Self User Interact
 */
public final class GetConversationsForSelfUserInteract
        extends UseCase<List<ConversationEntity>, Void> {

    /**
     * Conversation Repository
     */
    private final IConversationRepository conversationRepository;

    /**
     *
     * @param threadExecutor
     * @param postExecutionThread
     * @param conversationRepository
     */
    public GetConversationsForSelfUserInteract(
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
    protected Observable<List<ConversationEntity>> buildUseCaseObservable(Void params) {
        return conversationRepository.getConversationsForSelfUser();
    }

    /**
     * Get Conversation For Self User Api Errors
     */
    public enum GetConversationsForSelfUserApiErrors
            implements ISupportVisitable<GetConversationsForSelfUserApiErrors.IGetConversationsForSelfUserErrorVisitor> {

        /**
         * No Conversations Found
         */
        NO_CONVERSATIONS_FOUND(){
            @Override
            public <E> void accept(IGetConversationsForSelfUserErrorVisitor visitor, E data) {
                visitor.visitNoConversationsFound(this);
            }
        };


        /**
         * Get Conversation For Self User Error Visitor
         */
        public interface IGetConversationsForSelfUserErrorVisitor extends ISupportVisitor {

            /**
             * Visit No Conversations Found
             * @param apiErrors
             */
            void visitNoConversationsFound(final GetConversationsForSelfUserApiErrors apiErrors);
        }

    }
}
