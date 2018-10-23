package sanchez.sanchez.sergio.domain.interactor.comments;

import com.fernandocejas.arrow.checks.Preconditions;

import io.reactivex.Observable;
import sanchez.sanchez.sergio.domain.executor.IPostExecutionThread;
import sanchez.sanchez.sergio.domain.executor.IThreadExecutor;
import sanchez.sanchez.sergio.domain.interactor.UseCase;
import sanchez.sanchez.sergio.domain.models.SocialMediaLikesStatisticsEntity;
import sanchez.sanchez.sergio.domain.repository.ICommentsRepository;
import sanchez.sanchez.sergio.domain.utils.ISupportVisitable;
import sanchez.sanchez.sergio.domain.utils.ISupportVisitor;

/**
 * Get Social Media Likes Statistics Interact
 */
public final class GetSocialMediaLikesStatisticsInteract extends UseCase<SocialMediaLikesStatisticsEntity, GetSocialMediaLikesStatisticsInteract.Params> {

    /**
     * Comments Repository
     */
    private final ICommentsRepository commentsRepository;

    /**
     * @param threadExecutor
     * @param postExecutionThread
     * @param commentsRepository
     */
    public GetSocialMediaLikesStatisticsInteract(final IThreadExecutor threadExecutor, final IPostExecutionThread postExecutionThread,
                                                 final ICommentsRepository commentsRepository) {
        super(threadExecutor, postExecutionThread);
        this.commentsRepository = commentsRepository;
    }

    /**
     * Build Use Case Observable
     * @param params
     * @return
     */
    @Override
    protected Observable<SocialMediaLikesStatisticsEntity> buildUseCaseObservable(Params params) {
        Preconditions.checkNotNull(params, "Params can not be null");
        Preconditions.checkState(!params.getKidIdentity().isEmpty(), "Kid Identity can not be empty");
        Preconditions.checkState(params.getDaysAgo() > 0, "Days ago must be greater than 0");

        return commentsRepository.getSocialMediaLikesStatistics(params.getKidIdentity(),
                params.getDaysAgo());

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
     * Get Social Media Likes Statistics Interact API Errors
     */
    public enum GetSocialMediaLikesStatisticsApiErrors
            implements ISupportVisitable<GetSocialMediaLikesStatisticsApiErrors.IGetSocialMediaLikesStatisticsApiErrorsVisitor> {

        /**
         * No Comments Extracted
         */
        NO_LIKES_FOUND_IN_THIS_PERIOD(){
            @Override
            public <E> void accept(IGetSocialMediaLikesStatisticsApiErrorsVisitor visitor, E data) {
                visitor.visitNoLikesFoundInThisPeriod(this);
            }
        };

        /**
         * Get Social Media Likes Statistics Api Errors Visitor
         */
        public interface IGetSocialMediaLikesStatisticsApiErrorsVisitor extends ISupportVisitor {


            /**
             * Visit No Likes Found In This Period
             * @param apiErrorsVisitor
             */
            void visitNoLikesFoundInThisPeriod(final GetSocialMediaLikesStatisticsApiErrors apiErrorsVisitor);
        }
    }
}
