package sanchez.sanchez.sergio.domain.interactor.alerts;

import com.fernandocejas.arrow.checks.Preconditions;

import io.reactivex.Observable;
import sanchez.sanchez.sergio.domain.executor.IPostExecutionThread;
import sanchez.sanchez.sergio.domain.executor.IThreadExecutor;
import sanchez.sanchez.sergio.domain.interactor.UseCase;
import sanchez.sanchez.sergio.domain.models.AlertLevelEnum;
import sanchez.sanchez.sergio.domain.repository.IAlertsRepository;

/**
 * Clear Alerts Of Son By Level Interact
 */
public final class ClearAlertsOfSonByLevelInteract extends UseCase<String, ClearAlertsOfSonByLevelInteract.Params> {

    private final IAlertsRepository alertsRepository;

    /**
     * @param threadExecutor
     * @param postExecutionThread
     */
    public ClearAlertsOfSonByLevelInteract(final IThreadExecutor threadExecutor,
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
        Preconditions.checkNotNull(params.getSonId(), "Son Id can not be null");
        Preconditions.checkNotNull(!params.getSonId().isEmpty(), "Son Id can not be empty");
        Preconditions.checkNotNull(params.getAlertLevelEnum(), "Alert level enum can not be null");
        return alertsRepository.clearAlertsOfSonByLevel(params.getSonId(), params.getAlertLevelEnum());
    }

    /**
     * Params
     */
    public static class Params {

        /**
         * Son Identity
         */
        private final String sonId;

        /**
         * Alert Level Enum
         */
        private final AlertLevelEnum alertLevelEnum;

        /**
         * @param sonId
         * @param alertLevelEnum
         */
        public Params(final String sonId, final AlertLevelEnum alertLevelEnum) {
            this.sonId = sonId;
            this.alertLevelEnum = alertLevelEnum;
        }

        public String getSonId() {
            return sonId;
        }

        public AlertLevelEnum getAlertLevelEnum() {
            return alertLevelEnum;
        }

        /**
         * Create
         * @param sonId
         * @param alertLevelEnum
         * @return
         */
        public static Params create(final String sonId, final AlertLevelEnum alertLevelEnum) {
            return new Params(sonId, alertLevelEnum);
        }
    }
}
