package sanchez.sanchez.sergio.domain.interactor.statistics;

import java.util.List;
import io.reactivex.Observable;
import sanchez.sanchez.sergio.domain.executor.IPostExecutionThread;
import sanchez.sanchez.sergio.domain.executor.IThreadExecutor;
import sanchez.sanchez.sergio.domain.interactor.UseCase;
import sanchez.sanchez.sergio.domain.models.SummaryMyKidResultEntity;
import sanchez.sanchez.sergio.domain.repository.IAnalysisStatisticsRepository;
import sanchez.sanchez.sergio.domain.utils.ISupportVisitable;
import sanchez.sanchez.sergio.domain.utils.ISupportVisitor;

/**
 * Get Statistics Summary Interact
 */
public final class GetStatisticsSummaryInteract
        extends UseCase<List<SummaryMyKidResultEntity>,
        Void> {

    /**
     * Analysis Statistics Repository
     */
    private final IAnalysisStatisticsRepository analysisStatisticsRepository;

    /**
     * Abstract class for a Use Case
     *
     * @param threadExecutor
     * @param postExecutionThread
     * @param analysisStatisticsRepository
     */
    public GetStatisticsSummaryInteract(
            final IThreadExecutor threadExecutor,
            final IPostExecutionThread postExecutionThread,
            final IAnalysisStatisticsRepository analysisStatisticsRepository) {
        super(threadExecutor, postExecutionThread);
        this.analysisStatisticsRepository = analysisStatisticsRepository;
    }

    /**
     *
     * @param params
     * @return
     */
    @Override
    protected Observable<List<SummaryMyKidResultEntity>> buildUseCaseObservable(final Void params) {
        return analysisStatisticsRepository.getStatisticsSummary();

    }

    /**
     * Get Statistics Summary API Errors
     *
     * */
    public enum GetStatisticsSummaryApiErrors
            implements ISupportVisitable<GetStatisticsSummaryApiErrors.IGetStatisticsSummaryApiErrorsVisitor> {

        /**
         * No Statistics Found
         */
        NO_ANALYSIS_STATISTICS_SUMMARY_FOUND(){
            @Override
            public <E> void accept(IGetStatisticsSummaryApiErrorsVisitor visitor, E data) {
                visitor.visitNoStatisticsFound(this);
            }
        };

        /**
         * Get Statistics Summary Api Errors Visitor
         */
        public interface IGetStatisticsSummaryApiErrorsVisitor extends ISupportVisitor {

            /**
             * Visit No statistics found
             * @param apiErrorsVisitor
             */
            void visitNoStatisticsFound(final GetStatisticsSummaryApiErrors apiErrorsVisitor);
        }
    }


}
