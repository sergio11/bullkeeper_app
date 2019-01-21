package sanchez.sanchez.sergio.domain.interactor.funtime;

import com.fernandocejas.arrow.checks.Preconditions;
import io.reactivex.Observable;
import sanchez.sanchez.sergio.domain.executor.IPostExecutionThread;
import sanchez.sanchez.sergio.domain.executor.IThreadExecutor;
import sanchez.sanchez.sergio.domain.interactor.UseCase;
import sanchez.sanchez.sergio.domain.models.DayScheduledEntity;
import sanchez.sanchez.sergio.domain.repository.IFunTimeRepository;
import sanchez.sanchez.sergio.domain.utils.ISupportVisitable;
import sanchez.sanchez.sergio.domain.utils.ISupportVisitor;

/**
 * Get Fun Time Day Scheduled Interact
 */
public final class GetFunTimeDayScheduledInteract
        extends UseCase<DayScheduledEntity, GetFunTimeDayScheduledInteract.Params> {

    /**
     * Fun Time Repository
     */
    private final IFunTimeRepository funTimeRepository;

    /**
     * Abstract class for a Use Case
     *
     * @param threadExecutor
     * @param postExecutionThread
     * @param funTimeRepository
     */
    public GetFunTimeDayScheduledInteract(
            final IThreadExecutor threadExecutor,
            final IPostExecutionThread postExecutionThread,
            final IFunTimeRepository funTimeRepository) {
        super(threadExecutor, postExecutionThread);
        this.funTimeRepository = funTimeRepository;
    }

    /**
     * @param params
     * @return
     */
    @Override
    protected Observable<DayScheduledEntity> buildUseCaseObservable(final Params params) {
        Preconditions.checkNotNull(params, "Params can not be null");
        Preconditions.checkNotNull(params.getKid(), "Kid can not be null");
        Preconditions.checkState(!params.getKid().isEmpty(),
                "Kid can not be empty");
        Preconditions.checkNotNull(params.getTerminal(), "Terminal can not be null");
        Preconditions.checkState(!params.getTerminal().isEmpty(),
                "Terminal can not be empty");
        // Get Fun Time Day Scheduled
        return funTimeRepository
                .getFunTimeDayScheduled(
                        params.getKid(),
                        params.getTerminal(),
                        params.getDay());
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
         * Day
         */
        private final String day;

        /**
         * @param kid
         * @param terminal
         * @param day
         */
        private Params(final String kid, final String terminal, final String day) {
            this.kid = kid;
            this.terminal = terminal;
            this.day = day;
        }

        public String getKid() {
            return kid;
        }

        public String getTerminal() {
            return terminal;
        }

        public String getDay() {
            return day;
        }

        /**
         * Create
         * @param kid
         * @param terminal
         * @param day
         * @return
         */
        public static Params create(final String kid, final String terminal, final String day) {
            return new Params(kid, terminal, day);
        }
    }


    /**
     * Get Fun Time Api Errors
     */
    public enum GetFunTimeDayScheduledApiErrors
            implements ISupportVisitable<GetFunTimeDayScheduledApiErrors.IGetFunTimeDayScheduledApiErrorsVisitor> {

        /**
         * Fun Time Day Scheduled Not Found
         */
        FUN_TIME_DAY_SCHEDULED_NOT_FOUND(){
            @Override
            public <E> void accept(final IGetFunTimeDayScheduledApiErrorsVisitor visitor, E data) {
                visitor.visitFunTimeDayScheduledNotFound(visitor);
            }
        };

        /**
         * Get Fun Time Api Errors Visitor
         */
        public interface IGetFunTimeDayScheduledApiErrorsVisitor extends ISupportVisitor {

            /**
             * Visit Fun Time Day Scheduled Not Found
             * @param apiErrorsVisitor
             */
            void visitFunTimeDayScheduledNotFound(final IGetFunTimeDayScheduledApiErrorsVisitor apiErrorsVisitor);
        }
    }
}
