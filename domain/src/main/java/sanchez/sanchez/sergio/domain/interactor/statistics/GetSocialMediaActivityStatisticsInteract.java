package sanchez.sanchez.sergio.domain.interactor.statistics;

import com.fernandocejas.arrow.checks.Preconditions;

import io.reactivex.Observable;
import sanchez.sanchez.sergio.domain.executor.IPostExecutionThread;
import sanchez.sanchez.sergio.domain.executor.IThreadExecutor;
import sanchez.sanchez.sergio.domain.interactor.UseCase;
import sanchez.sanchez.sergio.domain.models.SocialMediaActivityStatisticsEntity;
import sanchez.sanchez.sergio.domain.repository.IAnalysisStatisticsRepository;
import sanchez.sanchez.sergio.domain.utils.ISupportVisitable;
import sanchez.sanchez.sergio.domain.utils.ISupportVisitor;

/**
 * Get Social Media Activity Statistics Interact
 */
public final class GetSocialMediaActivityStatisticsInteract
        extends UseCase<SocialMediaActivityStatisticsEntity,
                GetSocialMediaActivityStatisticsInteract.Params> {

    /**
     * Analysis Statistics Repository
     */
    private final IAnalysisStatisticsRepository analysisStatisticsRepository;


    /**
     * @param threadExecutor
     * @param postExecutionThread
     * @param analysisStatisticsRepository
     */
    public GetSocialMediaActivityStatisticsInteract(
            final IThreadExecutor threadExecutor,
            final IPostExecutionThread postExecutionThread,
            final IAnalysisStatisticsRepository analysisStatisticsRepository) {
        super(threadExecutor, postExecutionThread);
        this.analysisStatisticsRepository = analysisStatisticsRepository;
    }

    /**
     * Build Use Case Observable
     * @param params
     * @return
     */
    @Override
    protected Observable<SocialMediaActivityStatisticsEntity> buildUseCaseObservable(final Params params) {
        Preconditions.checkNotNull(params, "Params can not be null");

        return analysisStatisticsRepository.getSocialMediaActivityStatistics(params.getKidIdentity(),
                params.getDaysAgo());
    }

    /**
     * Params
     */
    public static class Params {

        private final String kidIdentity;
        private final int daysAgo;

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
        public static Params create(final String kidIdentity,
                                    final int daysAgo) {
            return new Params(kidIdentity, daysAgo);
        }
    }

    /**
     * Get Social Media Activity Statistics API Errors
     */
    public enum  GetSocialMediaActivityStatisticsApiErrors
            implements ISupportVisitable<GetSocialMediaActivityStatisticsApiErrors.IGetSocialMediaActivityStatisticsApiErrorsVisitor> {

        /**
         * Social Media Activity Statistics Not Found
         */
        SOCIAL_MEDIA_ACTIVITY_STATISTICS_NOT_FOUND(){
            @Override
            public <E> void accept(IGetSocialMediaActivityStatisticsApiErrorsVisitor visitor, E data) {
                visitor.visitSocialMediaActivityStatisticsNotFound(this);
            }
        };

        /**
         * Get Social Media Activity Statistics API Errors Visitor
         */
        public interface IGetSocialMediaActivityStatisticsApiErrorsVisitor extends ISupportVisitor {

            /**
             * Visit Social Media Activity Statistics Not Found
             * @param apiErrorsVisitor
             */
            void visitSocialMediaActivityStatisticsNotFound(final GetSocialMediaActivityStatisticsApiErrors apiErrorsVisitor);
        }
    }

}
