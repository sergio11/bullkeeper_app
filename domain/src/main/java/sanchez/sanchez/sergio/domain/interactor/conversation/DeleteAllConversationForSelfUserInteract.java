package sanchez.sanchez.sergio.domain.interactor.conversation;

import com.fernandocejas.arrow.checks.Preconditions;

import io.reactivex.Observable;
import sanchez.sanchez.sergio.domain.executor.IPostExecutionThread;
import sanchez.sanchez.sergio.domain.executor.IThreadExecutor;
import sanchez.sanchez.sergio.domain.interactor.UseCase;
import sanchez.sanchez.sergio.domain.repository.IConversationRepository;

/**
 * Delete All Conversations For self user Interact
 */
public final class DeleteAllConversationForSelfUserInteract
        extends UseCase<String, Void> {

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
    public DeleteAllConversationForSelfUserInteract(final IThreadExecutor threadExecutor,
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
    protected Observable<String> buildUseCaseObservable(final Void params) {
        Preconditions.checkNotNull(params, "Params can not be null");
        // Delete Conversation
        return conversationRepository.deleteAllConversationsForSelfUser();
    }

}
