package sanchez.sanchez.sergio.domain.interactor.comments;

import com.fernandocejas.arrow.checks.Preconditions;

import io.reactivex.Observable;
import sanchez.sanchez.sergio.domain.executor.IPostExecutionThread;
import sanchez.sanchez.sergio.domain.executor.IThreadExecutor;
import sanchez.sanchez.sergio.domain.interactor.UseCase;
import sanchez.sanchez.sergio.domain.models.MostActiveFriendsBySocialMediaStatisticsEntity;
import sanchez.sanchez.sergio.domain.repository.ICommentsRepository;
import sanchez.sanchez.sergio.domain.utils.ISupportVisitable;
import sanchez.sanchez.sergio.domain.utils.ISupportVisitor;

/**
 * Most Active Friends By Social Media Statistics Interact
 */
public final class MostActiveFriendsBySocialMediaStatisticsInteract
    extends UseCase<MostActiveFriendsBySocialMediaStatisticsEntity, MostActiveFriendsBySocialMediaStatisticsInteract.Params> {

    /**
     * Comments Repository
     */
    private final ICommentsRepository commentsRepository;


    /**
     * @param threadExecutor
     * @param postExecutionThread
     */
    public MostActiveFriendsBySocialMediaStatisticsInteract(final IThreadExecutor threadExecutor,
                                                            final IPostExecutionThread postExecutionThread,
                                                            final ICommentsRepository commentsRepository) {
        super(threadExecutor, postExecutionThread);
        this.commentsRepository = commentsRepository;
    }

    /**
     * @param params
     * @return
     */
    @Override
    protected Observable<MostActiveFriendsBySocialMediaStatisticsEntity> buildUseCaseObservable(Params params) {
        Preconditions.checkNotNull(params, "Params can not be null");
        Preconditions.checkState(!params.getKidIdentity().isEmpty(), "Kid Identity can not be empty");
        Preconditions.checkState(params.getDaysAgo() > 0, "Days ago must be greater than 0");


        return commentsRepository.getMostActiveFriendsBySocialMedia(params.getKidIdentity(), params.getDaysAgo());
    }

    /**
     * Params
     */
    public static class Params {

        private final String kidIdentity;
        private final int daysAgo;

        private Params(final String kidIdentity, final int daysAgo) {
            this.kidIdentity = kidIdentity;
            this.daysAgo = daysAgo;
        }

        public String getKidIdentity() {
            return kidIdentity;
        }

        public int getDaysAgo() {
            return daysAgo;
        }

        /**
         * Create
         * @param kidIdentity
         * @param daysAgo
         * @return
         */
        public static Params create(final String kidIdentity, final int daysAgo) {
            return new Params(kidIdentity, daysAgo);
        }
    }

    /**
     * Most Active Friends By Social Media Statistics API Errors
     */
    public enum  MostActiveFriendsBySocialMediaStatisticsApiErrors
            implements ISupportVisitable<MostActiveFriendsBySocialMediaStatisticsApiErrors.IMostActiveFriendsBySocialMediaStatisticsApiErrorsVisitor> {

        /**
         * No Active Friends Found In This Period
         */
        NO_ACTIVE_FRIENDS_FOUND_IN_THIS_PERIOD(){
            @Override
            public <E> void accept(IMostActiveFriendsBySocialMediaStatisticsApiErrorsVisitor visitor, E data) {
                visitor.visitNoActiveFoundInThisPeriod(this);
            }
        };

        /**
         * Most Active Friends By Social Media Statistics API Errors Visitor
         */
        public interface IMostActiveFriendsBySocialMediaStatisticsApiErrorsVisitor extends ISupportVisitor {

            /**
             * Visit No Active Found In This Period
             * @param apiErrorsVisitor
             */
            void visitNoActiveFoundInThisPeriod(final MostActiveFriendsBySocialMediaStatisticsApiErrors apiErrorsVisitor);
        }
    }

}
