package sanchez.sanchez.sergio.domain.interactor.apprules;

import com.fernandocejas.arrow.checks.Preconditions;
import io.reactivex.Observable;
import sanchez.sanchez.sergio.domain.executor.IPostExecutionThread;
import sanchez.sanchez.sergio.domain.executor.IThreadExecutor;
import sanchez.sanchez.sergio.domain.interactor.UseCase;
import sanchez.sanchez.sergio.domain.models.AppInstalledDetailEntity;
import sanchez.sanchez.sergio.domain.repository.IAppRulesRepository;

/**
 * Get App Installed Detail Interact
 */
public final class GetAppInstalledDetailInteract
        extends UseCase<AppInstalledDetailEntity, GetAppInstalledDetailInteract.Params> {

    /**
     * App Rules Repository
     */
    private final IAppRulesRepository appRulesRepository;

    /**
     * @param threadExecutor
     * @param postExecutionThread
     * @param appRulesRepository
     */
    public GetAppInstalledDetailInteract(final IThreadExecutor threadExecutor,
                                         final IPostExecutionThread postExecutionThread,
                                         final IAppRulesRepository appRulesRepository) {
        super(threadExecutor, postExecutionThread);
        this.appRulesRepository = appRulesRepository;
    }

    /**
     *
     * @param params
     * @return
     */
    @Override
    protected Observable<AppInstalledDetailEntity> buildUseCaseObservable(Params params) {
        Preconditions.checkNotNull(params, "Params can not be null");
        Preconditions.checkNotNull(params.getKid(), "Kid can not be null");
        Preconditions.checkState(!params.getKid().isEmpty(), "Kid can not be empty");
        Preconditions.checkNotNull(params.getTerminal(), "Terminal can not be null");
        Preconditions.checkState(!params.getTerminal().isEmpty(), "Terminal can not be empty");
        Preconditions.checkNotNull(params.getApp(), "App can not be null");
        Preconditions.checkState(!params.getApp().isEmpty(), "App can not be empty");

        return appRulesRepository.getAppInstalledDetail(params.getKid(),
                params.getTerminal(), params.getApp());
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
         * Terminal
         */
        private final String terminal;

        /**
         * App
         */
        private final String app;

        /**
         *
         * @param kid
         * @param terminal
         * @param app
         */
        private Params(final String kid, final String terminal, final String app) {
            this.kid = kid;
            this.terminal = terminal;
            this.app = app;
        }

        public String getKid() {
            return kid;
        }

        public String getTerminal() {
            return terminal;
        }

        public String getApp() {
            return app;
        }

        /**
         * Create
         * @param kid
         * @param terminal
         * @param app
         * @return
         */
        public static Params create(final String kid, final String terminal, final String app){
            return new Params(kid, terminal, app);
        }

        @Override
        public String toString() {
            return "Params{" +
                    "kid='" + kid + '\'' +
                    ", terminal='" + terminal + '\'' +
                    ", app='" + app + '\'' +
                    '}';
        }
    }
}
