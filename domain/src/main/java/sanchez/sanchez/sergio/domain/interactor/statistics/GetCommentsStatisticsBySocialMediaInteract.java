package sanchez.sanchez.sergio.domain.interactor.statistics;

import com.fernandocejas.arrow.checks.Preconditions;

import io.reactivex.Observable;
import sanchez.sanchez.sergio.domain.executor.IPostExecutionThread;
import sanchez.sanchez.sergio.domain.executor.IThreadExecutor;
import sanchez.sanchez.sergio.domain.interactor.UseCase;
import sanchez.sanchez.sergio.domain.models.CommentsStatisticsBySocialMediaEntity;
import sanchez.sanchez.sergio.domain.repository.IAnalysisStatisticsRepository;
import sanchez.sanchez.sergio.domain.utils.ISupportVisitable;
import sanchez.sanchez.sergio.domain.utils.ISupportVisitor;

/**
 * Get Comments Statistics By Social Media Interact
 */
public final class GetCommentsStatisticsBySocialMediaInteract extends UseCase<CommentsStatisticsBySocialMediaEntity, GetCommentsStatisticsBySocialMediaInteract.Params> {

    /**
     * Comments Repository
     */
    private final IAnalysisStatisticsRepository analysisStatisticsRepository;

    /**
     *
     * @param threadExecutor
     * @param postExecutionThread
     * @param analysisStatisticsRepository
     */
    public GetCommentsStatisticsBySocialMediaInteract(final IThreadExecutor threadExecutor,
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
    protected Observable<CommentsStatisticsBySocialMediaEntity> buildUseCaseObservable(final Params params) {
        Preconditions.checkNotNull(params, "Params can not be null");
        Preconditions.checkState(!params.getKid().isEmpty(), "Son Id can not be empty");

        return analysisStatisticsRepository.getCommentsStatisticsBySocialMedia(params.getKid(), params.getDaysAgo());
    }

    /**
     * Params
     */
    public static class Params {

        private final String kid;
        private final int daysAgo;

        /**
         *
         * @param kid
         * @param daysAgo
         */
        private Params(final String kid, final int daysAgo) {
            this.kid = kid;
            this.daysAgo = daysAgo;
        }

        public String getKid() {
            return kid;
        }

        public int getDaysAgo() {
            return daysAgo;
        }

        /**
         * Create
         * @param kid
         * @param daysAgo
         * @return
         */
        public static Params create(final String kid, final int daysAgo) {
            return new Params(kid, daysAgo);
        }
    }

    /**
     * Get Comments Statistics By Social Media API Errors
     */
    public enum GetCommentsStatisticsBySocialMediaApiErrors
            implements ISupportVisitable<GetCommentsStatisticsBySocialMediaApiErrors.IGetCommentsStatisticsBySocialMediaApiErrorsVisitor> {

        /**
         * No Comments Extracted
         */
        NO_COMMENTS_EXTRACTED(){
            @Override
            public <E> void accept(IGetCommentsStatisticsBySocialMediaApiErrorsVisitor visitor, E data) {
                visitor.visitNoCommentsExtractedError(visitor);
            }
        };

        /**
         * Get Comments Statistics By Social Media Api Errors
         */
        public interface IGetCommentsStatisticsBySocialMediaApiErrorsVisitor extends ISupportVisitor {


            /**
             * Visit No Comments Extracted Error
             * @param apiErrorsVisitor
             */
            void visitNoCommentsExtractedError(final IGetCommentsStatisticsBySocialMediaApiErrorsVisitor apiErrorsVisitor);
        }
    }

}
