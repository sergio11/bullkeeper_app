package sanchez.sanchez.sergio.domain.interactor.apprules;

import com.fernandocejas.arrow.checks.Preconditions;

import io.reactivex.Observable;
import sanchez.sanchez.sergio.domain.executor.IPostExecutionThread;
import sanchez.sanchez.sergio.domain.executor.IThreadExecutor;
import sanchez.sanchez.sergio.domain.interactor.UseCase;
import sanchez.sanchez.sergio.domain.repository.IAppRulesRepository;

/**
 * Disable App Interact
 */
public final class ChangeAppStatusInteract extends UseCase<String, ChangeAppStatusInteract.Params> {

    /**
     * App Rules Repository
     */
    private final IAppRulesRepository appRulesRepository;

    /**
     * Abstract class for a Use Case
     * @param threadExecutor
     * @param postExecutionThread
     * @param appRulesRepository
     */
    public ChangeAppStatusInteract(final IThreadExecutor threadExecutor,
                                   final IPostExecutionThread postExecutionThread,
                                   final IAppRulesRepository appRulesRepository) {
        super(threadExecutor, postExecutionThread);
        this.appRulesRepository = appRulesRepository;
    }

    /**
     * Build Use Case Observable
     * @param params
     * @return
     */
    @Override
    protected Observable<String> buildUseCaseObservable(final Params params) {
        Preconditions.checkNotNull(params, "Params can not be null");
        Preconditions.checkNotNull(params.getChildId(), "Child id can not be null");
        Preconditions.checkState(!params.getChildId().isEmpty(), "Child id can not be empty");
        Preconditions.checkNotNull(params.getTerminalId(), "Terminal Id can not be null");
        Preconditions.checkState(!params.getTerminalId().isEmpty(), "Terminal id can not be empty");
        Preconditions.checkNotNull(params.getApp(), "App can not be null");

        return appRulesRepository.switchAppStatusInTheTerminal(
                params.getChildId(), params.getTerminalId(),
                params.getApp(), params.getStatus());

    }

    /**
     * Params
     */
    public static class Params {

        /**
         * Child Id
         */
        private final String childId;

        /**
         * Terminal Id
         */
        private final String terminalId;

        /**
         * App
         */
        private final String app;

        /**
         * Status
         */
        private final Boolean status;

        /**
         * @param childId
         * @param terminalId
         * @param app
         * @param status
         */
        private Params(final String childId, final String terminalId, final String app,
                       final Boolean status) {
            this.childId = childId;
            this.terminalId = terminalId;
            this.app = app;
            this.status = status;
        }

        public String getChildId() {
            return childId;
        }

        public String getTerminalId() {
            return terminalId;
        }

        public String getApp() {
            return app;
        }

        public Boolean getStatus() {
            return status;
        }

        /**
         *
         * @param childId
         * @param terminalId
         * @param app
         * @param status
         * @return
         */
        public static Params create(final String childId, final String terminalId,
                                    final String app, final Boolean status) {
            Preconditions.checkNotNull(childId, "Child Id can not be null");
            Preconditions.checkState(!childId.isEmpty(), "Child Id can not be empty");
            Preconditions.checkNotNull(terminalId, "Terminal Id can not be null");
            Preconditions.checkState(!terminalId.isEmpty(), "Terminal Id can not empty");
            Preconditions.checkNotNull(app, "App  can not be null");
            Preconditions.checkNotNull(status, "Status  can not be null");
            return new Params(childId, terminalId, app, status);
        }
    }

}
