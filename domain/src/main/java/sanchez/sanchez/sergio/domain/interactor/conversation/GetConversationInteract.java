package sanchez.sanchez.sergio.domain.interactor.conversation;

import com.fernandocejas.arrow.checks.Preconditions;

import io.reactivex.Observable;
import sanchez.sanchez.sergio.domain.executor.IPostExecutionThread;
import sanchez.sanchez.sergio.domain.executor.IThreadExecutor;
import sanchez.sanchez.sergio.domain.interactor.UseCase;
import sanchez.sanchez.sergio.domain.models.ConversationEntity;
import sanchez.sanchez.sergio.domain.repository.IConversationRepository;

/**
 * Get Conversation Interact
 */
public final class GetConversationInteract
        extends UseCase<ConversationEntity, GetConversationInteract.Params> {

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
    public GetConversationInteract(final IThreadExecutor threadExecutor,
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
        Preconditions.checkNotNull(params.getKid(), "Kid can not be null");
        Preconditions.checkState(!params.getKid().isEmpty(), "Kid can not be empty");

        return conversationRepository.getConversation(params.getKid());
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
}
