package sanchez.sanchez.sergio.domain.interactor.children;

import com.fernandocejas.arrow.checks.Preconditions;

import java.util.List;

import io.reactivex.Observable;
import sanchez.sanchez.sergio.domain.executor.IPostExecutionThread;
import sanchez.sanchez.sergio.domain.executor.IThreadExecutor;
import sanchez.sanchez.sergio.domain.interactor.UseCase;
import sanchez.sanchez.sergio.domain.models.DimensionEntity;
import sanchez.sanchez.sergio.domain.repository.IChildrenRepository;
import sanchez.sanchez.sergio.domain.utils.ISupportVisitable;
import sanchez.sanchez.sergio.domain.utils.ISupportVisitor;

/**
 * Get Four Dimensions Statistics By Child
 */
public final class GetFourDimensionsStatisticsByChildInteract extends UseCase<List<DimensionEntity>,
        GetFourDimensionsStatisticsByChildInteract.Params> {

    /**
     * Children Repository
     */
    private final IChildrenRepository childrenRepository;

    /**
     * @param threadExecutor
     * @param postExecutionThread
     */
    public GetFourDimensionsStatisticsByChildInteract(final IThreadExecutor threadExecutor,
                                                      final IPostExecutionThread postExecutionThread,
                                                      final IChildrenRepository childrenRepository) {
        super(threadExecutor, postExecutionThread);
        this.childrenRepository = childrenRepository;
    }

    /**
     * Build Use Case Observable
     * @param params
     * @return
     */
    @Override
    protected Observable<List<DimensionEntity>> buildUseCaseObservable(Params params) {
        Preconditions.checkNotNull(params, "Params can not be null");
        Preconditions.checkNotNull(params.getSonId(), "Son Id can not be null");
        Preconditions.checkNotNull(!params.getSonId().isEmpty(), "Son Id can not be empty");
        Preconditions.checkState(params.daysAgo > 0, "Days Ago must be greater than 0");

        return childrenRepository.getDimensionsStatisticsByChild(params.getSonId(), params.getDaysAgo());
    }

    /**
     * Params
     */
    public static class Params {

        private final String sonId;
        private final int daysAgo;

        private Params(final String sonId, final int daysAgo) {
            this.sonId = sonId;
            this.daysAgo = daysAgo;
        }

        public String getSonId() {
            return sonId;
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
