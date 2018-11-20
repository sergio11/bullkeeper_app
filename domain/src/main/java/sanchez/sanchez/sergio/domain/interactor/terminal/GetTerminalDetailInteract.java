package sanchez.sanchez.sergio.domain.interactor.terminal;

import com.fernandocejas.arrow.checks.Preconditions;

import io.reactivex.Observable;
import sanchez.sanchez.sergio.domain.executor.IPostExecutionThread;
import sanchez.sanchez.sergio.domain.executor.IThreadExecutor;
import sanchez.sanchez.sergio.domain.interactor.UseCase;
import sanchez.sanchez.sergio.domain.models.TerminalDetailEntity;
import sanchez.sanchez.sergio.domain.repository.ITerminalRepository;

/**
 * Get Terminal Detail Interact
 */
public class GetTerminalDetailInteract extends UseCase<TerminalDetailEntity, GetTerminalDetailInteract.Params> {

    /**
     * Terminal Repository
     */
    private final ITerminalRepository terminalRepository;


    /**
     * @param threadExecutor
     * @param postExecutionThread
     */
    public GetTerminalDetailInteract(final IThreadExecutor threadExecutor,
                                     final IPostExecutionThread postExecutionThread,
                                     final ITerminalRepository terminalRepository) {
        super(threadExecutor, postExecutionThread);
        this.terminalRepository = terminalRepository;
    }

    /**
     * Build Use Case Observable
     * @param params
     * @return
     */
    @Override
    protected Observable<TerminalDetailEntity> buildUseCaseObservable(Params params) {
        Preconditions.checkNotNull(params, "Params can not be null");
        Preconditions.checkNotNull(params.getChildId(), "Child Id can not be null");
        Preconditions.checkState(!params.getChildId().isEmpty(), "Child Id can not be empty");
        Preconditions.checkNotNull(params.getTerminalId(), "Terminal Id can not be null");
        Preconditions.checkState(!params.getTerminalId().isEmpty(), "Terminal Id can not be empty");

        return terminalRepository.getTerminalDetail(params.getChildId(), params.getTerminalId());
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
        public static Params create(final String childId, final String terminalId) {
            return new Params(childId, terminalId);
        }
    }
}
