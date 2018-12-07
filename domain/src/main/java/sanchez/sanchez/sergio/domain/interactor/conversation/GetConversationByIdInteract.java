package sanchez.sanchez.sergio.domain.interactor.conversation;

import com.fernandocejas.arrow.checks.Preconditions;

import io.reactivex.Observable;
import sanchez.sanchez.sergio.domain.executor.IPostExecutionThread;
import sanchez.sanchez.sergio.domain.executor.IThreadExecutor;
import sanchez.sanchez.sergio.domain.interactor.UseCase;
import sanchez.sanchez.sergio.domain.models.ConversationEntity;
import sanchez.sanchez.sergio.domain.repository.IConversationRepository;
import sanchez.sanchez.sergio.domain.utils.ISupportVisitable;
import sanchez.sanchez.sergio.domain.utils.ISupportVisitor;

/**
 * Get Conversation by id interact
 */
public final class GetConversationByIdInteract
        extends UseCase<ConversationEntity, GetConversationByIdInteract.Params> {

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
    public GetConversationByIdInteract(
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
    protected Observable<ConversationEntity> buildUseCaseObservable(Params params) {
        Preconditions.checkNotNull(params, "Params can not be null");
        Preconditions.checkNotNull(params.getId(), "Kid can not be null");
        Preconditions.checkState(!params.getId().isEmpty(), "Kid can not be empty");

        return conversationRepository.getConversationById(params.getId());
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
     * Get Conversation By Id Api Errors
     */
    public enum GetConversationByIdApiErrors
            implements ISupportVisitable<GetConversationByIdApiErrors.IGetConversationByIdErrorVisitor> {

        /**
         * Conversation Not Found
         */
        CONVERSATION_NOT_FOUND_EXCEPTION(){
            @Override
            public <E> void accept(IGetConversationByIdErrorVisitor visitor, E data) {
                visitor.visitConversationNotFound(this);
            }
        };


        /**
         * Get Conversation By Id Error Visitor
         */
        public interface IGetConversationByIdErrorVisitor extends ISupportVisitor {

            /**
             * Visit Conversation Not Found
             * @param apiErrors
             */
            void visitConversationNotFound(final GetConversationByIdApiErrors apiErrors);
        }

    }
}
