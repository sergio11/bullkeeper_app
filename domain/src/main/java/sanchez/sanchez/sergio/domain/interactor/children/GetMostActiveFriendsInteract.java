package sanchez.sanchez.sergio.domain.interactor.children;

import com.fernandocejas.arrow.checks.Preconditions;
import io.reactivex.Observable;
import sanchez.sanchez.sergio.domain.executor.IPostExecutionThread;
import sanchez.sanchez.sergio.domain.executor.IThreadExecutor;
import sanchez.sanchez.sergio.domain.interactor.UseCase;
import sanchez.sanchez.sergio.domain.models.MostActiveFriendsEntity;
import sanchez.sanchez.sergio.domain.repository.ICommentsRepository;
import sanchez.sanchez.sergio.domain.utils.ISupportVisitable;
import sanchez.sanchez.sergio.domain.utils.ISupportVisitor;

/**
 * Get Most Active Friends
 */
public final class GetMostActiveFriendsInteract extends UseCase<MostActiveFriendsEntity, GetMostActiveFriendsInteract.Params> {

    /**
     * Comment Repository
     */
    private final ICommentsRepository commentsRepository;

    /**
     * @param threadExecutor
     * @param postExecutionThread
     * @param commentsRepository
     */
    public GetMostActiveFriendsInteract(final IThreadExecutor threadExecutor,
                                        final IPostExecutionThread postExecutionThread,
                                        final ICommentsRepository commentsRepository) {
        super(threadExecutor, postExecutionThread);
        this.commentsRepository = commentsRepository;
    }

    /**
     *
     * @param params
     * @return
     */
    @Override
    protected Observable<MostActiveFriendsEntity> buildUseCaseObservable(Params params) {
        Preconditions.checkNotNull(params, "Params can not be null");

        return commentsRepository.getMostActiveFriends(new String[]{ params.getKidIdentity() },
                params.getDaysAgo());
    }

    /**
     * Params
     */
    public static class Params {

        private final String kidIdentity;
        private final int daysAgo;

        /**
         * @param kidIdentity
         * @param daysAgo
         */
        private Params(String kidIdentity, int daysAgo) {
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
     * Error Handling
     * =======================
     */

    /**
     * Get Comments Api Error
     */
    public enum GetMostActiveFriendsApiErrors implements ISupportVisitable<GetMostActiveFriendsApiErrors.IGetMostActiveFriendsApiErrorsVisitor> {

        /**
         * No Active Friends In this period
         */
        NO_ACTIVE_FRIENDS_IN_THIS_PERIOD(){
            @Override
            public <E> void accept(IGetMostActiveFriendsApiErrorsVisitor visitor, E data) {
                visitor.visitNoActiveFriendsInThisPeriod(this);
            }
        };

        /**
         * Get Most Active Friends Api Errors Visitor
         */
        public interface IGetMostActiveFriendsApiErrorsVisitor extends ISupportVisitor {

            /**
             * Visit No Active Friends in this period
             * @param apiErrors
             */
            void visitNoActiveFriendsInThisPeriod(final GetMostActiveFriendsApiErrors apiErrors);
        }
    }
}
