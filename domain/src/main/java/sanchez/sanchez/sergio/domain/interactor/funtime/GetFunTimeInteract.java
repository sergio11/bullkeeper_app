package sanchez.sanchez.sergio.domain.interactor.funtime;

import com.fernandocejas.arrow.checks.Preconditions;
import io.reactivex.Observable;
import sanchez.sanchez.sergio.domain.executor.IPostExecutionThread;
import sanchez.sanchez.sergio.domain.executor.IThreadExecutor;
import sanchez.sanchez.sergio.domain.interactor.UseCase;
import sanchez.sanchez.sergio.domain.models.FunTimeScheduledEntity;
import sanchez.sanchez.sergio.domain.repository.IFunTimeRepository;
import sanchez.sanchez.sergio.domain.utils.ISupportVisitable;
import sanchez.sanchez.sergio.domain.utils.ISupportVisitor;

/**
 * Get Fun Time Interact
 */
public final class GetFunTimeInteract
        extends UseCase<FunTimeScheduledEntity, GetFunTimeInteract.Params> {

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
    public GetFunTimeInteract(
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
    protected Observable<FunTimeScheduledEntity> buildUseCaseObservable(final Params params) {
        Preconditions.checkNotNull(params, "Params can not be null");
        Preconditions.checkNotNull(params.getKid(), "Kid can not be null");
        Preconditions.checkState(!params.getKid().isEmpty(),
                "Kid can not be empty");
        Preconditions.checkNotNull(params.getTerminal(), "Terminal can not be null");
        Preconditions.checkState(!params.getTerminal().isEmpty(),
                "Terminal can not be empty");
        // Get Fun Time Scheduled
        return funTimeRepository
                .getFunTimeScheduled(params.getKid(), params.getTerminal());
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
         * Create
         * @param kid
         * @param terminal
         * @return
         */
        public static Params create(final String kid, final String terminal) {
            return new Params(kid, terminal);
        }
    }


    /**
     * Get Fun Time Api Errors
     */
    public enum GetFunTimeApiErrors
            implements ISupportVisitable<GetFunTimeApiErrors.IGetFunTimeApiErrorsVisitor> {

        /**
         * Fun Time Not Found
         */
        FUN_TIME_NOT_FOUND(){
            @Override
            public <E> void accept(final IGetFunTimeApiErrorsVisitor visitor, E data) {
                visitor.visitNoFunTimeFound(visitor);
            }
        };

        /**
         * Get Fun Time Api Errors Visitor
         */
        public interface IGetFunTimeApiErrorsVisitor extends ISupportVisitor {

            /**
             * Visit No Fun Time Found
             * @param apiErrorsVisitor
             */
            void visitNoFunTimeFound(final IGetFunTimeApiErrorsVisitor apiErrorsVisitor);
        }
    }
}
