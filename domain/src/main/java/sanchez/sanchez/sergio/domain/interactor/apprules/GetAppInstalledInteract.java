package sanchez.sanchez.sergio.domain.interactor.apprules;

import com.fernandocejas.arrow.checks.Preconditions;
import java.util.List;
import io.reactivex.Observable;
import sanchez.sanchez.sergio.domain.executor.IPostExecutionThread;
import sanchez.sanchez.sergio.domain.executor.IThreadExecutor;
import sanchez.sanchez.sergio.domain.interactor.UseCase;
import sanchez.sanchez.sergio.domain.models.AppInstalledEntity;
import sanchez.sanchez.sergio.domain.repository.IAppRulesRepository;
import sanchez.sanchez.sergio.domain.utils.ISupportVisitable;
import sanchez.sanchez.sergio.domain.utils.ISupportVisitor;

/**
 * Get App Installed Interact
 */
public final class GetAppInstalledInteract extends UseCase<List<AppInstalledEntity>, GetAppInstalledInteract.Params> {

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
    public GetAppInstalledInteract(final IThreadExecutor threadExecutor,
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
    protected Observable<List<AppInstalledEntity>> buildUseCaseObservable(final Params params) {
        Preconditions.checkNotNull(params, "Params can not be null");
        Preconditions.checkNotNull(params.getChildId(), "Child id can not be null");
        Preconditions.checkState(!params.getChildId().isEmpty(), "Child id can not be empty");
        Preconditions.checkNotNull(params.getTerminalId(), "Terminal Id can not be null");
        Preconditions.checkState(!params.getTerminalId().isEmpty(), "Terminal id can not be empty");
        Preconditions.checkNotNull(params.getQuery(), "Query can not be null");

        return appRulesRepository.getAppInstalledByChild(
                params.getChildId(), params.getTerminalId(),
                params.getQuery());

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
         * Query
         */
        private final String query;

        /**
         * @param childId
         * @param terminalId
         * @param query
         */
        private Params(final String childId, final String terminalId, final String query) {
            this.childId = childId;
            this.terminalId = terminalId;
            this.query = query;
        }

        public String getChildId() {
            return childId;
        }

        public String getTerminalId() {
            return terminalId;
        }

        public String getQuery() {
            return query;
        }

        public static Params create(final String childId, final String terminalId, final String query) {
            Preconditions.checkNotNull(childId, "Child Id can not be null");
            Preconditions.checkState(!childId.isEmpty(), "Child Id can not be empty");
            Preconditions.checkNotNull(terminalId, "Terminal Id can not be null");
            Preconditions.checkState(!terminalId.isEmpty(), "Terminal Id can not empty");
            Preconditions.checkNotNull(query, "Query  can not be null");
            return new Params(childId, terminalId, query);
        }
    }

    /**
     * Get App Rules Api Errors
     */
    public enum GetAppRulesApiErrors
            implements ISupportVisitable<GetAppRulesApiErrors.IGetAppRulesApiErrorsVisitor> {

        /**
         * No App Rules
         */
        NO_APPS_INSTALLED_FOUND(){
            @Override
            public <E> void accept(final IGetAppRulesApiErrorsVisitor visitor, E data) {
                visitor.visitNoAppRulesFound(visitor);
            }
        };

        /**
         * Get App Rules Api Errors Visitor
         */
        public interface IGetAppRulesApiErrorsVisitor extends ISupportVisitor {

            /**
             * Visit No App Rules Found
             * @param apiErrorsVisitor
             */
            void visitNoAppRulesFound(final IGetAppRulesApiErrorsVisitor apiErrorsVisitor);
        }
    }
}
