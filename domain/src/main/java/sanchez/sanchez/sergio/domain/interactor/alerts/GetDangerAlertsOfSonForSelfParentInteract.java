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
 * Get Danger Alerts Of Son For Self Parent
 */
public final class GetDangerAlertsOfSonForSelfParentInteract extends UseCase<List<AlertEntity>, GetDangerAlertsOfSonForSelfParentInteract.Params> {

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
        return alertsRepository.getTenDangerAlertsForTheChild(params.getSonId());
    }

    /**
     * Params
     */
    public static class Params {

        private final String sonId;

        private Params(String sonId) {
            this.sonId = sonId;
        }

        public String getSonId() {
            return sonId;
        }

        /**
         * Create
         * @param sonId
         * @return
         */
        public static Params create(final String sonId) {
            return new Params(sonId);
        }
    }

    /**
     * Get Danger Alerts Of Son for self parent
     */
    public enum GetDangerAlertsOfSonForSelfParentApiErrors implements ISupportVisitable<GetDangerAlertsOfSonForSelfParentApiErrors.IGetDangerAlertsOfSonForSelfParentErrorVisitor> {

        /**
         * No Alerts By Son Founded
         */
        NO_ALERTS_BY_SON_FOUNDED(){
            @Override
            public <E> void accept(IGetDangerAlertsOfSonForSelfParentErrorVisitor visitor, E data) {
                visitor.visitNoAlertsBySonFounded(this);
            }
        };


        /**
         * Get Danger Alerts Of Son For Self Parent Error Visitor
         */
        public interface IGetDangerAlertsOfSonForSelfParentErrorVisitor extends ISupportVisitor {

            /**
             * Visit No Alerts By Son Founded
             * @param apiErrors
             */
            void visitNoAlertsBySonFounded(final GetDangerAlertsOfSonForSelfParentApiErrors apiErrors);
        }

    }

}
