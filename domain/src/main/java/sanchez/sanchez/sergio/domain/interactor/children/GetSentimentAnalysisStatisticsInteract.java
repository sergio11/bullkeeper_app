package sanchez.sanchez.sergio.domain.interactor.children;

import com.fernandocejas.arrow.checks.Preconditions;

import io.reactivex.Observable;
import sanchez.sanchez.sergio.domain.executor.IPostExecutionThread;
import sanchez.sanchez.sergio.domain.executor.IThreadExecutor;
import sanchez.sanchez.sergio.domain.interactor.UseCase;
import sanchez.sanchez.sergio.domain.models.SentimentAnalysisStatisticsEntity;
import sanchez.sanchez.sergio.domain.repository.IChildrenRepository;
import sanchez.sanchez.sergio.domain.utils.ISupportVisitable;
import sanchez.sanchez.sergio.domain.utils.ISupportVisitor;

/**
 * Get Sentiment Analysis Statistics Interact
 */
public final class GetSentimentAnalysisStatisticsInteract extends UseCase<SentimentAnalysisStatisticsEntity,
        GetSentimentAnalysisStatisticsInteract.Params> {

    /**
     * Children Repository
     */
    private final IChildrenRepository childrenRepository;


    /**
     * @param threadExecutor
     * @param postExecutionThread
     * @param childrenRepository
     */
    public GetSentimentAnalysisStatisticsInteract(final IThreadExecutor threadExecutor, final IPostExecutionThread postExecutionThread,
                                                  final IChildrenRepository childrenRepository) {
        super(threadExecutor, postExecutionThread);
        this.childrenRepository = childrenRepository;
    }

    /**
     *
     * @param params
     * @return
     */
    @Override
    protected Observable<SentimentAnalysisStatisticsEntity> buildUseCaseObservable(Params params) {
        Preconditions.checkNotNull(params, "Params can not be null");
        Preconditions.checkNotNull(params.getKidIdentity(), "Kid Identity can not be null");
        Preconditions.checkState(!params.getKidIdentity().isEmpty(), "Kid Identity can not be empty");
        Preconditions.checkState(params.getDaysAgo() > 0, "Days ago must be greater than 0");


        return childrenRepository.getSentimentAnalysisStatistics(params.getKidIdentity(),
                params.getDaysAgo());
    }

    /**
     * Params
     */
    public static class Params {

        private final String kidIdentity;
        private final int daysAgo;

        /**
         * Params
         * @param kidIdentity
         * @param daysAgo
         */
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
     * Get Sentiment Analysis Statistics API Errors
     */
    public enum GetSentimentAnalysisStatisticsApiErrors implements ISupportVisitable<GetSentimentAnalysisStatisticsApiErrors.IGetSentimentAnalysisStatisticsApiErrorsVisitor> {

        /**
         * No Sentiment Analysis Statistics For this period
         */
        NO_SENTIMENT_ANALYSIS_STATISTICS_FOR_THIS_PERIOD(){
            @Override
            public <E> void accept(IGetSentimentAnalysisStatisticsApiErrorsVisitor visitor, E data) {
                visitor.visitNoSentimentAnalysisStatisticsForThisPeriod(this);
            }
        };

        /**
         * Get Sentiment Analysis Statistics Api Errors Visitor
         */
        public interface IGetSentimentAnalysisStatisticsApiErrorsVisitor extends ISupportVisitor {

            /**
             * Visit No Sentiment Analysis Statistics For This Period
             * @param apiErrors
             */
            void visitNoSentimentAnalysisStatisticsForThisPeriod(final GetSentimentAnalysisStatisticsApiErrors apiErrors);
        }
    }
}
