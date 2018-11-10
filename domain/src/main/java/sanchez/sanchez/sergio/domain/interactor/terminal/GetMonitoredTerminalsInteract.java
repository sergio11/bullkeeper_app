package sanchez.sanchez.sergio.domain.interactor.terminal;

import com.fernandocejas.arrow.checks.Preconditions;
import java.util.List;
import io.reactivex.Observable;
import sanchez.sanchez.sergio.domain.executor.IPostExecutionThread;
import sanchez.sanchez.sergio.domain.executor.IThreadExecutor;
import sanchez.sanchez.sergio.domain.interactor.UseCase;
import sanchez.sanchez.sergio.domain.models.TerminalEntity;
import sanchez.sanchez.sergio.domain.repository.ITerminalRepository;
import sanchez.sanchez.sergio.domain.utils.ISupportVisitable;
import sanchez.sanchez.sergio.domain.utils.ISupportVisitor;

/**
 * Get Monitored Terminals Interact
 */
public final class GetMonitoredTerminalsInteract extends UseCase<List<TerminalEntity>, GetMonitoredTerminalsInteract.Params> {

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
    public GetMonitoredTerminalsInteract(final IThreadExecutor threadExecutor, final IPostExecutionThread postExecutionThread,
                                         final ITerminalRepository terminalRepository) {
        super(threadExecutor, postExecutionThread);
        this.terminalRepository = terminalRepository;
    }

    /**
     * @param params
     * @return
     */
    @Override
    protected Observable<List<TerminalEntity>> buildUseCaseObservable(Params params) {
        Preconditions.checkNotNull(params, "Params can not empty");

        return terminalRepository.getMonitoredTerminals(params.getChildId());
    }

    /**
     * Params
     */
    public static class Params {

        private final String childId;

        private Params(String childId) {
            this.childId = childId;
        }

        public String getChildId() {
            return childId;
        }

        public static Params create(final String childId) {
            Preconditions.checkNotNull(childId, "Child id can not be null");
            Preconditions.checkState(!childId.isEmpty(), "Child id can not be empty");
            return new Params(childId);
        }
    }


    /**
     * Get Monitored Terminals Api Errors
     */
    public enum GetMonitoredTerminalsApiErrors
            implements ISupportVisitable<GetMonitoredTerminalsApiErrors.IGetMonitoredTerminalsApiErrorsVisitor> {

        /**
         * No Terminals found
         */
        NO_TERMINALS_FOUND(){
            @Override
            public <E> void accept(IGetMonitoredTerminalsApiErrorsVisitor visitor, E data) {
                visitor.visitNoMonitoredTerminalsFound(visitor);
            }
        };

        /**
         * Get Monitored Terminals Api Error Visitor
         */
        public interface IGetMonitoredTerminalsApiErrorsVisitor extends ISupportVisitor {

            /**
             * visit no monitored terminals found
             * @param apiErrorsVisitor
             */
            void visitNoMonitoredTerminalsFound(final IGetMonitoredTerminalsApiErrorsVisitor apiErrorsVisitor);
        }
    }

}
