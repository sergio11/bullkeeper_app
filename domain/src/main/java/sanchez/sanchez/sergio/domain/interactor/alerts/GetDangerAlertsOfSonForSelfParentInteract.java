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
 * Get Danger Alerts Of Kid For Self Guardian
 */
public final class GetDangerAlertsOfSonForSelfParentInteract
        extends UseCase<List<AlertEntity>, GetDangerAlertsOfSonForSelfParentInteract.Params> {

    /**
     * Alerts Repository
     */
    private final IAlertsRepository alertsRepository;

    /**
     * @param threadExecutor
     * @param postExecutionThread
     */
    public GetDangerAlertsOfSonForSelfParentInteract(final IThreadExecutor threadExecutor,
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
        return alertsRepository.getTenDangerAlertsForTheChild(params.getKid());
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
     * Get Danger Alerts Of Kid for self parent
     */
    public enum GetDangerAlertsOfKidForSelfGuardianApiErrors
            implements ISupportVisitable<GetDangerAlertsOfKidForSelfGuardianApiErrors.IGetDangerAlertsOfKidForSelfGuardianErrorVisitor> {

        /**
         * No Alerts By Kid Founded
         */
        NO_ALERTS_BY_KID_FOUNDED(){
            @Override
            public <E> void accept(IGetDangerAlertsOfKidForSelfGuardianErrorVisitor visitor, E data) {
                visitor.visitNoAlertsByKidFounded(this);
            }
        };


        /**
         * Get Danger Alerts Of Kid For Self Guardian Error Visitor
         */
        public interface IGetDangerAlertsOfKidForSelfGuardianErrorVisitor extends ISupportVisitor {

            /**
             * Visit No Alerts By Kid Found
             * @param apiErrors
             */
            void visitNoAlertsByKidFounded(final GetDangerAlertsOfKidForSelfGuardianApiErrors apiErrors);
        }

    }

}
