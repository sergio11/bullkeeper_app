package sanchez.sanchez.sergio.domain.interactor.alerts;

import com.fernandocejas.arrow.checks.Preconditions;

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
 * Get Warning Alerts Of Son For Self Parent
 */
public final class GetWarningAlertsOfSonForSelfParentInteract extends UseCase<List<AlertEntity>, GetWarningAlertsOfSonForSelfParentInteract.Params> {

    /**
     * Alerts Repository
     */
    private final IAlertsRepository alertsRepository;

    /**
     * @param threadExecutor
     * @param postExecutionThread
     */
    public GetWarningAlertsOfSonForSelfParentInteract(final IThreadExecutor threadExecutor,
                                                      final IPostExecutionThread postExecutionThread, final IAlertsRepository alertsRepository) {
        super(threadExecutor, postExecutionThread);
        this.alertsRepository = alertsRepository;
    }

    /**
     * @param params
     * @return
     */
    @Override
    protected Observable<List<AlertEntity>> buildUseCaseObservable(Params params) {
        Preconditions.checkNotNull(params, "Params can not be null");
        return alertsRepository.getTenWarningAlertsForTheChild(params.getKid());
    }

    /**
     * Params
     */
    public static class Params {

        private final String kid;

        private Params(String kid) {
            this.kid = kid;
        }

        public String getKid() {
            return kid;
        }

        /**
         * Create
         * @param kid
         * @return
         */
        public static Params create(final String kid) {
            return new Params(kid);
        }
    }

    /**
     * Get Warning Alerts Of Kid for self parent
     */
    public enum GetWarningAlertsOfKidForSelfParentApiErrors implements ISupportVisitable<GetWarningAlertsOfKidForSelfParentApiErrors.IGetWarningAlertsOfKidForSelfParentErrorVisitor> {

        /**
         * No Alerts By Kid Founded
         */
        NO_ALERTS_BY_KID_FOUNDED(){
            @Override
            public <E> void accept(IGetWarningAlertsOfKidForSelfParentErrorVisitor visitor, E data) {
                visitor.visitNoAlertsBySonFound(this);
            }
        };


        /**
         * Get Warning Alerts Of Kid For Self Parent Error Visitor
         */
        public interface IGetWarningAlertsOfKidForSelfParentErrorVisitor extends ISupportVisitor {

            /**
             * Visit No Alerts By Kid Founded
             * @param apiErrors
             */
            void visitNoAlertsBySonFound(final GetWarningAlertsOfKidForSelfParentApiErrors apiErrors);
        }

    }

}
