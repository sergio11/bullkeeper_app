package sanchez.sanchez.sergio.domain.interactor.alerts;

import com.fernandocejas.arrow.checks.Preconditions;

import io.reactivex.Observable;
import sanchez.sanchez.sergio.domain.executor.IPostExecutionThread;
import sanchez.sanchez.sergio.domain.executor.IThreadExecutor;
import sanchez.sanchez.sergio.domain.interactor.UseCase;
import sanchez.sanchez.sergio.domain.models.AlertLevelEnum;
import sanchez.sanchez.sergio.domain.repository.IAlertsRepository;

/**
 * Clear Alerts Of Kid By Level Interact
 */
public final class ClearAlertsOfKidByLevelInteract
        extends UseCase<String, ClearAlertsOfKidByLevelInteract.Params> {

    private final IAlertsRepository alertsRepository;

    /**
     * @param threadExecutor
     * @param postExecutionThread
     */
    public ClearAlertsOfKidByLevelInteract(final IThreadExecutor threadExecutor,
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
    protected Observable<String> buildUseCaseObservable(Params params) {
        Preconditions.checkNotNull(params, "Params can not be null");
        Preconditions.checkNotNull(params.getKid(), "Kid can not be null");
        Preconditions.checkNotNull(!params.getKid().isEmpty(), "Kid can not be empty");
        Preconditions.checkNotNull(params.getAlertLevelEnum(), "Alert level enum can not be null");
        return alertsRepository.clearAlertsOfSonByLevel(params.getKid(), params.getAlertLevelEnum());
    }

    /**
     * Params
     */
    public static class Params {

        /**
         * Kid
         */
        private final String kid;

        /**
         * Alert Level Enum
         */
        private final AlertLevelEnum alertLevelEnum;

        /**
         * @param kid
         * @param alertLevelEnum
         */
        public Params(final String kid, final AlertLevelEnum alertLevelEnum) {
            this.kid = kid;
            this.alertLevelEnum = alertLevelEnum;
        }

        public String getKid() {
            return kid;
        }

        public AlertLevelEnum getAlertLevelEnum() {
            return alertLevelEnum;
        }

        /**
         * Create
         * @param kid
         * @param alertLevelEnum
         * @return
         */
        public static Params create(final String kid, final AlertLevelEnum alertLevelEnum) {
            return new Params(kid, alertLevelEnum);
        }
    }
}
