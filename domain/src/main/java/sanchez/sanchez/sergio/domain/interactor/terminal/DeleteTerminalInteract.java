package sanchez.sanchez.sergio.domain.interactor.terminal;

import com.fernandocejas.arrow.checks.Preconditions;

import io.reactivex.Observable;
import sanchez.sanchez.sergio.domain.executor.IPostExecutionThread;
import sanchez.sanchez.sergio.domain.executor.IThreadExecutor;
import sanchez.sanchez.sergio.domain.interactor.UseCase;
import sanchez.sanchez.sergio.domain.repository.ITerminalRepository;

/**
 * Delete Terminal Interact
 */
public final class DeleteTerminalInteract extends UseCase<String, DeleteTerminalInteract.Params> {

    /**
     * Terminal Repository
     */
    private final ITerminalRepository terminalRepository;

    /**
     * @param threadExecutor
     * @param postExecutionThread
     */
    public DeleteTerminalInteract(final IThreadExecutor threadExecutor,
                                  final IPostExecutionThread postExecutionThread,
                                  final ITerminalRepository terminalRepository) {
        super(threadExecutor, postExecutionThread);
        this.terminalRepository = terminalRepository;
    }

    /**
     * Build Use Case
     * @param params
     * @return
     */
    @Override
    protected Observable<String> buildUseCaseObservable(Params params) {
        Preconditions.checkNotNull(params, "Params can not be null");
        Preconditions.checkNotNull(params.getChildId(), "Child Id can not be null");
        Preconditions.checkState(!params.getChildId().isEmpty(), "Child Id can not be empty");
        Preconditions.checkNotNull(params.getTerminalId(), "Terminal Id can not be null");
        Preconditions.checkState(!params.getTerminalId().isEmpty(), "Terminal Id can not be empty");

        return terminalRepository.deleteTerminal(params.getChildId(),
                params.getTerminalId());
    }

    /**
     * Params
     */
    public static class Params {

        private final String childId;
        private final String terminalId;

        /**
         * Params
         * @param childId
         * @param terminalId
         */
        private Params(final String childId, final String terminalId) {
            this.childId = childId;
            this.terminalId = terminalId;
        }

        public String getChildId() {
            return childId;
        }

        public String getTerminalId() {
            return terminalId;
        }

        /**
         * Create
         * @param childId
         * @param terminalId
         * @return
         */
        public static DeleteTerminalInteract.Params create(final String childId, final String terminalId) {
            return new DeleteTerminalInteract.Params(childId, terminalId);
        }
    }

}
