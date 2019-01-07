package sanchez.sanchez.sergio.domain.interactor.apprules;

import com.fernandocejas.arrow.checks.Preconditions;
import java.util.List;
import io.reactivex.Observable;
import sanchez.sanchez.sergio.domain.executor.IPostExecutionThread;
import sanchez.sanchez.sergio.domain.executor.IThreadExecutor;
import sanchez.sanchez.sergio.domain.interactor.UseCase;
import sanchez.sanchez.sergio.domain.models.AppStatsEntity;
import sanchez.sanchez.sergio.domain.repository.IAppRulesRepository;
import sanchez.sanchez.sergio.domain.utils.ISupportVisitable;
import sanchez.sanchez.sergio.domain.utils.ISupportVisitor;


/**
 * Get Statistics Of The Five Most Used Applications Interact
 */
public final class GetStatisticsOfTheFiveMostUsedApplicationsInteract
    extends UseCase<List<AppStatsEntity>, GetStatisticsOfTheFiveMostUsedApplicationsInteract.Params> {

    /**
     * App Rules Repository
     */
    private final IAppRulesRepository appRulesRepository;

    /**
     * Abstract class for a Use Case
     *
     * @param threadExecutor
     * @param postExecutionThread
     */
    public GetStatisticsOfTheFiveMostUsedApplicationsInteract(
            final IThreadExecutor threadExecutor,
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
    protected Observable<List<AppStatsEntity>> buildUseCaseObservable(Params params) {
        Preconditions.checkNotNull(params.kid, "Kid can not be null");
        Preconditions.checkState(!params.kid.isEmpty(), "Kid can not be empty");
        Preconditions.checkNotNull(params.terminal, "Terminal can not be null");
        Preconditions.checkState(!params.terminal.isEmpty(), "Terminal can not be empty");

        return appRulesRepository.getStatisticsOfTheFiveMostUsedApplications(
                params.getKid(), params.getTerminal()
        );
    }

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
         *
         * @param kid
         * @param terminal
         */
        private Params(final String kid, final String terminal) {
            this.kid = kid;
            this.terminal = terminal;
        }

        public String getKid() {
            return kid;
        }

        public String getTerminal() {
            return terminal;
        }

        /**
         *
         * @param kid
         * @param terminal
         * @return
         */
        public static Params create(final String kid, final String terminal) {
            Preconditions.checkNotNull(kid, "Kid can not be null");
            Preconditions.checkState(!kid.isEmpty(), "Kid can not be empty");
            Preconditions.checkNotNull(terminal, "Terminal can not be null");
            Preconditions.checkState(!terminal.isEmpty(), "Terminal can not be empty");

            return new Params(kid, terminal);
        }
    }

    /**
     * Get Statistics Of The Five Most Used Applications Api Error
     */
    public enum GetStatisticsOfTheFiveMostUsedApplicationsApiErrors
            implements ISupportVisitable<GetStatisticsOfTheFiveMostUsedApplicationsApiErrors
                        .IGetStatisticsOfTheFiveMostUsedApplicationsApiErrorsApiErrorsVisitor> {

        /**
         * No App Stats Found
         */
        NO_APP_STATS_FOUND(){
            @Override
            public <E> void accept(final IGetStatisticsOfTheFiveMostUsedApplicationsApiErrorsApiErrorsVisitor visitor, E data) {
                visitor.visitNoAppStatsFound(visitor);
            }
        };

        /**
         * Get Statistics Of The Five Most Used Applications Api Errors Visitor
         */
        public interface IGetStatisticsOfTheFiveMostUsedApplicationsApiErrorsApiErrorsVisitor extends ISupportVisitor {

            /**
             * Visit No App Stats Found
             * @param apiErrorsVisitor
             */
            void visitNoAppStatsFound(final IGetStatisticsOfTheFiveMostUsedApplicationsApiErrorsApiErrorsVisitor apiErrorsVisitor);
        }
    }
}
