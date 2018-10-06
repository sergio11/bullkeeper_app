package sanchez.sanchez.sergio.domain.interactor.children;

import com.fernandocejas.arrow.checks.Preconditions;

import io.reactivex.Observable;
import sanchez.sanchez.sergio.domain.executor.IPostExecutionThread;
import sanchez.sanchez.sergio.domain.executor.IThreadExecutor;
import sanchez.sanchez.sergio.domain.interactor.UseCase;
import sanchez.sanchez.sergio.domain.models.AlertsStatisticsEntity;
import sanchez.sanchez.sergio.domain.repository.IChildrenRepository;
import sanchez.sanchez.sergio.domain.utils.ISupportVisitable;
import sanchez.sanchez.sergio.domain.utils.ISupportVisitor;

/**
 * Get Alerts Statistics Interact
 */
public final class GetAlertsStatisticsInteract extends UseCase<AlertsStatisticsEntity, GetAlertsStatisticsInteract.Params> {

    /**
     * Children Repository
     */
    private final IChildrenRepository childrenRepository;

    /**
     * @param threadExecutor
     * @param postExecutionThread
     * @param childrenRepository
     */
    public GetAlertsStatisticsInteract(final IThreadExecutor threadExecutor,
                                       final IPostExecutionThread postExecutionThread,
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
    protected Observable<AlertsStatisticsEntity> buildUseCaseObservable(Params params) {
        Preconditions.checkNotNull(params, "Params can not be null");
        Preconditions.checkNotNull(params.getKidIdentity(), "Kid Identity can not be null");
        Preconditions.checkState(!params.getKidIdentity().isEmpty(), "Kid Identity can not be empty");

        return childrenRepository.getAlertsStatistics(params.getKidIdentity(), params.getDaysAgo());
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
        public static Params create(final String kidIdentity, final int daysAgo){
            return new Params(kidIdentity, daysAgo);
        }
    }

    /**
     * Get Alerts Statistics API Errors
     */
    public enum GetAlertsStatisticsApiErrors implements ISupportVisitable<GetAlertsStatisticsApiErrors.IGetAlertsStatisticsApiErrorsVisitor> {

        /**
         * No Alerts Statistics For This Period
         */
        NO_ALERTS_STATISTICS_FOR_THIS_PERIOD(){
            @Override
            public <E> void accept(IGetAlertsStatisticsApiErrorsVisitor visitor, E data) {
                visitor.visitNoAlertsStatisticsForThisPeriod(this);
            }
        };

        /**
         * Get Alerts Statistics Api Errors Visitor
         */
        public interface IGetAlertsStatisticsApiErrorsVisitor extends ISupportVisitor {

            /**
             * Visit No Alerts Statistics For This Period
             * @param apiErrors
             */
            void visitNoAlertsStatisticsForThisPeriod(final GetAlertsStatisticsApiErrors apiErrors);
        }
    }
}
