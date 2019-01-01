package sanchez.sanchez.sergio.domain.interactor.terminal;

import com.fernandocejas.arrow.checks.Preconditions;

import io.reactivex.Observable;
import sanchez.sanchez.sergio.domain.executor.IPostExecutionThread;
import sanchez.sanchez.sergio.domain.executor.IThreadExecutor;
import sanchez.sanchez.sergio.domain.interactor.UseCase;
import sanchez.sanchez.sergio.domain.repository.ITerminalRepository;

/**
 * Switch Lock Camera Status Interact
 */
public final class SwitchLockCameraStatusInteract
        extends UseCase<String, SwitchLockCameraStatusInteract.Params> {

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
    public SwitchLockCameraStatusInteract(
            final IThreadExecutor threadExecutor,
            final IPostExecutionThread postExecutionThread,
            final ITerminalRepository terminalRepository) {
        super(threadExecutor, postExecutionThread);
        this.terminalRepository = terminalRepository;
    }

    /**
     *
     * @param params
     * @return
     */
    @Override
    protected Observable<String> buildUseCaseObservable(Params params) {
        Preconditions.checkNotNull(params, "Params can not be null");
        Preconditions.checkNotNull(params.getKid(), "Kid can not be null");
        Preconditions.checkState(!params.getKid().isEmpty(), "Kid can not be empty");
        Preconditions.checkNotNull(params.getTerminal(), "Terminal can not be null");
        Preconditions.checkState(!params.getTerminal().isEmpty(), "Terminal can not be empty");
        Preconditions.checkNotNull(params.getStatus(), "Status can not be null");

        return terminalRepository.switchLockCameraStatus(
                params.getKid(), params.getTerminal(), params.getStatus()
        );

    }

    /**
     * Params
     */
    public static class Params {

        // Kid
        private final String kid;
        // Terminal
        private final String terminal;
        // Status
        private final Boolean status;

        /**
         * Params
         * @param kid
         * @param terminal
         * @param status
         */
        private Params(final String kid, String terminal, final Boolean status) {
            this.kid = kid;
            this.terminal = terminal;
            this.status = status;
        }

        public String getKid() {
            return kid;
        }

        public String getTerminal() {
            return terminal;
        }

        public Boolean getStatus() {
            return status;
        }

        /**
         * Create
         * @param kid
         * @param terminal
         * @param status
         * @return
         */
        public static Params create(final String kid, String terminal,
                                    final Boolean status) {
            return new Params(kid, terminal, status);
        }
    }
}
