package sanchez.sanchez.sergio.domain.interactor.statistics;

import com.fernandocejas.arrow.checks.Preconditions;
import io.reactivex.Observable;
import sanchez.sanchez.sergio.domain.executor.IPostExecutionThread;
import sanchez.sanchez.sergio.domain.executor.IThreadExecutor;
import sanchez.sanchez.sergio.domain.interactor.UseCase;
import sanchez.sanchez.sergio.domain.models.DimensionsStatisticsEntity;
import sanchez.sanchez.sergio.domain.repository.IAnalysisStatisticsRepository;
import sanchez.sanchez.sergio.domain.utils.ISupportVisitable;
import sanchez.sanchez.sergio.domain.utils.ISupportVisitor;

/**
 * Get Four Dimensions Statistics By Child
 */
public final class GetFourDimensionsStatisticsByChildInteract extends UseCase<DimensionsStatisticsEntity,
        GetFourDimensionsStatisticsByChildInteract.Params> {

    /**
     * Analysis Statistics Repository
     */
    private final IAnalysisStatisticsRepository analysisStatisticsRepository;

    /**
     *
     * @param threadExecutor
     * @param postExecutionThread
     * @param analysisStatisticsRepository
     */
    public GetFourDimensionsStatisticsByChildInteract(final IThreadExecutor threadExecutor,
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
    protected Observable<DimensionsStatisticsEntity> buildUseCaseObservable(Params params) {
        Preconditions.checkNotNull(params, "Params can not be null");
        Preconditions.checkNotNull(params.getKid(), "Kid Id can not be null");
        Preconditions.checkNotNull(!params.getKid().isEmpty(), "Kid Id can not be empty");
        Preconditions.checkState(params.daysAgo > 0, "Days Ago must be greater than 0");

        return analysisStatisticsRepository.getDimensionsStatistics(params.getKid(), params.getDaysAgo());
    }

    /**
     * Params
     */
    public static class Params {

        private final String kid;
        private final int daysAgo;

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

        public static Params create(final String sonId, final int daysAgo) {
            return new Params(sonId, daysAgo);
        }
    }


    /**
     * Get Four Dimensions Statistics API Errors
     */
    public enum GetFourDimensionsStatisticsApiErrors implements ISupportVisitable<GetFourDimensionsStatisticsApiErrors.IGetFourDimensionsStatisticsApiErrorsVisitor> {

        /**
         * No Dimensions Statistics For This Period
         */
        NO_DIMENSIONS_STATISTICS_FOR_THIS_PERIOD(){
            @Override
            public <E> void accept(IGetFourDimensionsStatisticsApiErrorsVisitor visitor, E data) {
                visitor.visitNoDimensionsStatisticsForThisPeriodError(visitor);
            }
        };

        /**
         * Get Four Dimensions API Error Visitor
         */
        public interface IGetFourDimensionsStatisticsApiErrorsVisitor extends ISupportVisitor {


            /**
             * Visit No Dimensions Statistics For This Period Error
             * @param apiErrorsVisitor
             */
            void visitNoDimensionsStatisticsForThisPeriodError(final IGetFourDimensionsStatisticsApiErrorsVisitor apiErrorsVisitor);
        }
    }
}
