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
        Preconditions.checkNotNull(params.getKid(), "Kid can not be null");
        Preconditions.checkState(!params.getKid().isEmpty(), "Kid can not be empty");
        return conversationRepository.getConversationMessages(params.getKid());
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
         * @param kid
         */
        private Params(String kid) {
            this.kid = kid;
        }

        public String getKid() {
            return kid;
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
