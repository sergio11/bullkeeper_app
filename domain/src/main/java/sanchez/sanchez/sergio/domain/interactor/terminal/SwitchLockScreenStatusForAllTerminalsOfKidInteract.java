package sanchez.sanchez.sergio.domain.interactor.terminal;

import com.fernandocejas.arrow.checks.Preconditions;
import io.reactivex.Observable;
import sanchez.sanchez.sergio.domain.executor.IPostExecutionThread;
import sanchez.sanchez.sergio.domain.executor.IThreadExecutor;
import sanchez.sanchez.sergio.domain.interactor.UseCase;
import sanchez.sanchez.sergio.domain.repository.ITerminalRepository;

/**
 * Switch Lock Screen Status For All Terminals Of Kid Interact
 */
public final class SwitchLockScreenStatusForAllTerminalsOfKidInteract
            extends UseCase<String, SwitchLockScreenStatusForAllTerminalsOfKidInteract.Params> {

    /**
     * Terminal Repository
     */
    private final ITerminalRepository terminalRepository;


    /**
     * Abstract class for a Use Case
     *
     * @param threadExecutor
     * @param postExecutionThread
     */
    public SwitchLockScreenStatusForAllTerminalsOfKidInteract(
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
        Preconditions.checkNotNull(params.getStatus(), "Status can not be null");
        return terminalRepository.switchLockScreenStatus(
                params.getKid(), params.getStatus()
        );
    }

    /**
     * Params
     */
    public static class Params {

        // Kid
        private final String kid;
        // Status
        private final Boolean status;

        /**
         * Params
         * @param kid
         * @param status
         */
        private Params(final String kid, final Boolean status) {
            this.kid = kid;
            this.status = status;
        }

        public String getKid() {
            return kid;
        }

        public Boolean getStatus() {
            return status;
        }

        /**
         * Create
         * @param kid
         * @param status
         * @return
         */
        public static Params create(final String kid,
                                    final Boolean status) {
            return new Params(kid, status);
        }
    }
}
