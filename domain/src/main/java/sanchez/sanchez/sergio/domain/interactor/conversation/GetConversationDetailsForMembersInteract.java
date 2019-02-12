package sanchez.sanchez.sergio.domain.interactor.conversation;

import com.fernandocejas.arrow.checks.Preconditions;
import io.reactivex.Observable;
import sanchez.sanchez.sergio.domain.executor.IPostExecutionThread;
import sanchez.sanchez.sergio.domain.executor.IThreadExecutor;
import sanchez.sanchez.sergio.domain.interactor.UseCase;
import sanchez.sanchez.sergio.domain.models.ConversationEntity;
import sanchez.sanchez.sergio.domain.repository.IConversationRepository;
/**
 * Get Conversation Details For Members Interact
 */
public final class GetConversationDetailsForMembersInteract
        extends UseCase<ConversationEntity, GetConversationDetailsForMembersInteract.Params> {

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
    public GetConversationDetailsForMembersInteract(final IThreadExecutor threadExecutor,
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
        Preconditions.checkNotNull(params.getMemberOne(), "Member One can not be null");
        Preconditions.checkState(!params.getMemberOne().isEmpty(), "Member One can not be empty");
        Preconditions.checkNotNull(params.getMemberTwo(), "Member Two can not be null");
        Preconditions.checkState(!params.getMemberTwo().isEmpty(), "Member Two can not be empty");
        return conversationRepository.getConversationForMembers(params.getMemberOne(),
                params.getMemberTwo()).onErrorResumeNext(
                        conversationRepository.createConversation(params.getMemberOne(),
                                params.getMemberTwo()));
    }

    /**
     * Params
     */
    public static class Params {

        /**
         * Member One
         */
        private final String memberOne;

        /**
         * Member Two
         */
        private final String memberTwo;

        /**
         * @param memberOne
         * @param memberTwo
         */
        private Params(
                final String memberOne,
                final String memberTwo) {
            this.memberOne = memberOne;
            this.memberTwo = memberTwo;
        }

        public String getMemberOne() {
            return memberOne;
        }

        public String getMemberTwo() {
            return memberTwo;
        }

        /**
         * Create
         * @param memberOne
         * @param memberTwo
         */
        public static Params create(final String memberOne,
                                    final String memberTwo) {
            return new Params(memberOne, memberTwo);
        }
    }

}
