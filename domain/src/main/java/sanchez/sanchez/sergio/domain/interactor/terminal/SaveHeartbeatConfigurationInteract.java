package sanchez.sanchez.sergio.domain.interactor.terminal;

import com.fernandocejas.arrow.checks.Preconditions;
import io.reactivex.Observable;
import sanchez.sanchez.sergio.domain.executor.IPostExecutionThread;
import sanchez.sanchez.sergio.domain.executor.IThreadExecutor;
import sanchez.sanchez.sergio.domain.interactor.UseCase;
import sanchez.sanchez.sergio.domain.models.TerminalHeartbeatEntity;
import sanchez.sanchez.sergio.domain.repository.ITerminalRepository;

/**
 * Save Heart Beat Configuration Interact
 */
public final class SaveHeartbeatConfigurationInteract
        extends UseCase<TerminalHeartbeatEntity, SaveHeartbeatConfigurationInteract.Params> {

    /**
     * Terminal Repository
     */
    private final ITerminalRepository terminalRepository;


    /**
     * Abstract class for a Use Case
     *
     * @param threadExecutor
     * @param postExecutionThread
     * @param terminalRepository
     */
    public SaveHeartbeatConfigurationInteract(final IThreadExecutor threadExecutor,
                                              final IPostExecutionThread postExecutionThread,
                                              final ITerminalRepository terminalRepository) {
        super(threadExecutor, postExecutionThread);
        this.terminalRepository = terminalRepository;
    }

    /**
     *
     * @param params
     * @return
     */
    @Override
    protected Observable<TerminalHeartbeatEntity> buildUseCaseObservable(final Params params) {
        Preconditions.checkNotNull(params, "Params can not be null");
        Preconditions.checkNotNull(params.childId, "Child Id can not be null");
        Preconditions.checkState(!params.childId.isEmpty(), "Child Id can not be empty");
        Preconditions.checkNotNull(params.terminalId, "Terminal id can not be null");
        Preconditions.checkState(!params.terminalId.isEmpty(), "Terminal Id can not be empty");
        return terminalRepository.saveHeartbeatConfiguration(params.childId,
                params.terminalId, params.alertThresholdInMinutes, params.isAlertModeEnabled);
    }


    /**
     * Params
     */
    public static class Params {

        private final String childId;
        private final String terminalId;
        private final int alertThresholdInMinutes;
        private final boolean isAlertModeEnabled;

        /**
         *
         * @param childId
         * @param terminalId
         * @param alertThresholdInMinutes
         * @param isAlertModeEnabled
         */
        private Params(final String childId, final String terminalId,
                       int alertThresholdInMinutes, boolean isAlertModeEnabled) {
            this.childId = childId;
            this.terminalId = terminalId;
            this.alertThresholdInMinutes = alertThresholdInMinutes;
            this.isAlertModeEnabled = isAlertModeEnabled;
        }

        public String getChildId() {
            return childId;
        }

        public String getTerminalId() {
            return terminalId;
        }

        public int getAlertThresholdInMinutes() {
            return alertThresholdInMinutes;
        }

        public boolean isAlertModeEnabled() {
            return isAlertModeEnabled;
        }

        /**
         * Create
         * @param childId
         * @param terminalId
         * @param alertThresholdInMinutes
         * @param isAlertModeEnabled
         * @return
         */
        public static Params create(final String childId, final String terminalId,
                                    int alertThresholdInMinutes, boolean isAlertModeEnabled){
            return new Params(childId, terminalId, alertThresholdInMinutes, isAlertModeEnabled);
        }
    }
}
