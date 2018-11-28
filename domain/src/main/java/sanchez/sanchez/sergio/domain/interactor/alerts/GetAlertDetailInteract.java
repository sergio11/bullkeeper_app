package sanchez.sanchez.sergio.domain.interactor.alerts;

import com.fernandocejas.arrow.checks.Preconditions;

import io.reactivex.Observable;
import sanchez.sanchez.sergio.domain.executor.IPostExecutionThread;
import sanchez.sanchez.sergio.domain.executor.IThreadExecutor;
import sanchez.sanchez.sergio.domain.interactor.UseCase;
import sanchez.sanchez.sergio.domain.models.AlertEntity;
import sanchez.sanchez.sergio.domain.repository.IAlertsRepository;
import sanchez.sanchez.sergio.domain.utils.ISupportVisitable;
import sanchez.sanchez.sergio.domain.utils.ISupportVisitor;

/**
 * Get Alert Detail Interact
 */
public final class GetAlertDetailInteract extends UseCase<AlertEntity, GetAlertDetailInteract.Params> {

    private final IAlertsRepository alertsRepository;

    /**
     * @param threadExecutor
     * @param postExecutionThread
     */
    public GetAlertDetailInteract(final IThreadExecutor threadExecutor,
                                  final IPostExecutionThread postExecutionThread,
                                  final IAlertsRepository alertsRepository) {
        super(threadExecutor, postExecutionThread);
        this.alertsRepository = alertsRepository;
    }

    /**
     *
     * @param params
     * @return
     */
    @Override
    protected Observable<AlertEntity> buildUseCaseObservable(Params params) {
        Preconditions.checkNotNull(params, "Params can not be null");
        return alertsRepository.getAlertById(params.getKid(), params.getAlertId());
    }

    /**
     * Get Alert Detail Api Errors
     */
    public enum GetAlertDetailApiErrors implements
            ISupportVisitable<GetAlertDetailApiErrors.IGetAlertDetailApiErrorVisitor> {

        /**
         * Alert Not Found
         */
        ALERT_NOT_FOUND(){
            @Override
            public <E> void accept(IGetAlertDetailApiErrorVisitor visitor, E data) {
                visitor.visitAlertNotFound(this);
            }
        };


        /**
         * Get Alert Detail Api Error Visitor
         */
        public interface IGetAlertDetailApiErrorVisitor extends ISupportVisitor {

            /**
             * Visit Alert Not Found
             * @param error
             */
            void visitAlertNotFound(final GetAlertDetailApiErrors error);
        }

    }


    /**
     * Params
     */
    public static class Params {

        private final String kid;
        private final String alertId;

        public Params(String kid, String alertId) {
            this.kid = kid;
            this.alertId = alertId;
        }

        public String getKid() {
            return kid;
        }

        public String getAlertId() {
            return alertId;
        }

        /**
         * Create
         * @param kid
         * @param alertId
         * @return
         */
        public static Params create(final String kid, final String alertId) {
            return new Params(kid, alertId);
        }

    }
}
