package sanchez.sanchez.sergio.domain.interactor.alerts;

import com.fernandocejas.arrow.checks.Preconditions;

import io.reactivex.Observable;
import sanchez.sanchez.sergio.domain.executor.IPostExecutionThread;
import sanchez.sanchez.sergio.domain.executor.IThreadExecutor;
import sanchez.sanchez.sergio.domain.interactor.UseCase;
import sanchez.sanchez.sergio.domain.models.AlertLevelEnum;
import sanchez.sanchez.sergio.domain.repository.IAlertsRepository;

/**
 * Clear Alerts By Level Interact
 */
public final class ClearAlertsByLevelInteract extends UseCase<String, ClearAlertsByLevelInteract.Params> {

    private final IAlertsRepository alertsRepository;

    /**
     * @param threadExecutor
     * @param postExecutionThread
     */
    public ClearAlertsByLevelInteract(final IThreadExecutor threadExecutor,
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
        Preconditions.checkNotNull(params.getAlertLevelEnum(), "Alert level enum can not be null");
        return alertsRepository.clearSelfAlertsByLevel(params.getAlertLevelEnum());
    }

    /**
     * Params
     */
    public static class Params {

        /**
         * Alert Level Enum
         */
        private final AlertLevelEnum alertLevelEnum;

        /**
         * @param alertLevelEnum
         */
        public Params(final AlertLevelEnum alertLevelEnum) {
            this.alertLevelEnum = alertLevelEnum;
        }

        public AlertLevelEnum getAlertLevelEnum() {
            return alertLevelEnum;
        }

        /**
         * Create
         * @param alertLevelEnum
         * @return
         */
        public static Params create(final AlertLevelEnum alertLevelEnum) {
            return new Params(alertLevelEnum);
        }
    }
}
