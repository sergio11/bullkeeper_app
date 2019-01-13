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
 * Get All App Installed By Kid Interact
 */
public final class GetAllAppInstalledByKidInteract extends UseCase<List<AppInstalledEntity>, GetAllAppInstalledByKidInteract.Params> {

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
    public GetAllAppInstalledByKidInteract(final IThreadExecutor threadExecutor,
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
        Preconditions.checkNotNull(params.getKid(), "Child id can not be null");
        Preconditions.checkState(!params.getKid().isEmpty(), "Child id can not be empty");
        Preconditions.checkNotNull(params.getQuery(), "Query can not be null");

        return appRulesRepository.getAllAppInstalledByChild(
                params.getKid(), params.getQuery());

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
         * Query
         */
        private final String query;

        /**
         * @param kid
         * @param query
         */
        private Params(final String kid, final String query) {
            this.kid = kid;
            this.query = query;
        }

        public String getKid() {
            return kid;
        }

        public String getQuery() {
            return query;
        }

        public static Params create(final String kid, final String query) {
            Preconditions.checkNotNull(kid, "Child Id can not be null");
            Preconditions.checkState(!kid.isEmpty(), "Child Id can not be empty");
            Preconditions.checkNotNull(query, "Query  can not be null");
            return new Params(kid, query);
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
