package sanchez.sanchez.sergio.domain.interactor.alerts;

import java.util.List;

import io.reactivex.Observable;
import sanchez.sanchez.sergio.domain.executor.IPostExecutionThread;
import sanchez.sanchez.sergio.domain.executor.IThreadExecutor;
import sanchez.sanchez.sergio.domain.interactor.UseCase;
import sanchez.sanchez.sergio.domain.models.AlertEntity;
import sanchez.sanchez.sergio.domain.repository.IAlertsRepository;
import sanchez.sanchez.sergio.domain.utils.ISupportVisitable;
import sanchez.sanchez.sergio.domain.utils.ISupportVisitor;

/**
 * Get Self Alerts Interact
 */
public final class GetSelfAlertsInteract extends UseCase<List<AlertEntity>, Void> {

    /**
     * Alerts Repository
     */
    private final IAlertsRepository alertsRepository;

    /**
     * @param threadExecutor
     * @param postExecutionThread
     */
    public GetSelfAlertsInteract(final IThreadExecutor threadExecutor, final IPostExecutionThread postExecutionThread,
                                 final IAlertsRepository alertsRepository) {
        super(threadExecutor, postExecutionThread);
        this.alertsRepository = alertsRepository;
    }

    /**
     * Build User Case Observable
     * @param aVoid
     * @return
     */
    @Override
    protected Observable<List<AlertEntity>> buildUseCaseObservable(Void aVoid) {
        return alertsRepository.getSelfAlerts();
    }

    /**
     * Get Self Alerts API Error
     */
    public enum GetSelfAlertsApiErrors implements ISupportVisitable<GetSelfAlertsApiErrors.IGetSelfAlertsApiErrorVisitor> {

        /**
         * No Alerts Found
         */
        NO_ALERTS_FOUND() {
            @Override
            public <E> void accept(IGetSelfAlertsApiErrorVisitor visitor, E data) {
                visitor.visitNoAlertsFounded(this);
            }
        };

        /**
         * Get Self Alerts Api Error Visitor
         */
        public interface IGetSelfAlertsApiErrorVisitor extends ISupportVisitor {
            /**
             * Visit No Alerts Founded
             *
             * @param error
             */
            void visitNoAlertsFounded(final GetSelfAlertsApiErrors error);

        }
    }


}
