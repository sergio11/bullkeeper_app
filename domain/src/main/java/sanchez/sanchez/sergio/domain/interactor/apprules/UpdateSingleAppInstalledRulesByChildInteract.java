package sanchez.sanchez.sergio.domain.interactor.apprules;

import com.fernandocejas.arrow.checks.Preconditions;
import io.reactivex.Observable;
import sanchez.sanchez.sergio.domain.executor.IPostExecutionThread;
import sanchez.sanchez.sergio.domain.executor.IThreadExecutor;
import sanchez.sanchez.sergio.domain.interactor.UseCase;
import sanchez.sanchez.sergio.domain.models.AppInstalledRuleEntity;
import sanchez.sanchez.sergio.domain.repository.IAppRulesRepository;

/**
 * Update Single App Installed Rules By Child Interact
 */
public final class UpdateSingleAppInstalledRulesByChildInteract extends UseCase<String, UpdateSingleAppInstalledRulesByChildInteract.Params> {

    /**
     * App Rules Repository
     */
    private final IAppRulesRepository appRulesRepository;

    /**
     * Abstract class for a Use Case
     *
     * @param threadExecutor
     * @param postExecutionThread
     * @param appRulesRepository
     */
    public UpdateSingleAppInstalledRulesByChildInteract(final IThreadExecutor threadExecutor, final IPostExecutionThread postExecutionThread,
                                                        final IAppRulesRepository appRulesRepository) {
        super(threadExecutor, postExecutionThread);
        this.appRulesRepository = appRulesRepository;
    }

    /**
     * @param params
     * @return
     */
    @Override
    protected Observable<String> buildUseCaseObservable(Params params) {
        Preconditions.checkNotNull(params, "Params can not be null");

        return appRulesRepository.updateSingleAppInstalledRulesByChild(
                params.getChildId(), params.getTerminalId(), params.getAppId(),
                params.getAppRules());
    }

    /**
     * Params
     */
    public static class Params {

        private final String childId;
        private final String terminalId;
        private final String appId;
        private final AppInstalledRuleEntity appRules;

        /**
         * Params
         * @param childId
         * @param terminalId
         * @param appId
         * @param appRules
         */
        private Params(
                final String childId,
                final String terminalId,
                final String appId,
                final AppInstalledRuleEntity appRules) {
            this.childId = childId;
            this.terminalId = terminalId;
            this.appId = appId;
            this.appRules = appRules;
        }

        public String getChildId() {
            return childId;
        }

        public String getTerminalId() {
            return terminalId;
        }

        public String getAppId() {
            return appId;
        }

        public AppInstalledRuleEntity getAppRules() {
            return appRules;
        }

        /**
         * Create
         * @param childId
         * @param terminalId
         * @param appId
         * @param appRules
         * @return
         */
        public static Params create(
                final String childId,
                final String terminalId,
                final String appId,
                final AppInstalledRuleEntity appRules){
            Preconditions.checkNotNull(childId, "Child id can not be null");
            Preconditions.checkState(!childId.isEmpty(), "Child id can not be empty");
            Preconditions.checkNotNull(appId, "App id can not be null");
            Preconditions.checkState(!appId.isEmpty(), "App id can not be empty");
            Preconditions.checkNotNull(terminalId, "Terminal Id can not be null");
            Preconditions.checkState(!terminalId.isEmpty(), "Terminal id can not be empty");
            Preconditions.checkNotNull(appRules, "App Rule can not be null");
            return new Params(childId, terminalId, appId, appRules);
        }
    }
}
