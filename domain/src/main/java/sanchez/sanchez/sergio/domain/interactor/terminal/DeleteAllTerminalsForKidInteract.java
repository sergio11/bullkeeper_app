package sanchez.sanchez.sergio.domain.interactor.terminal;

import com.fernandocejas.arrow.checks.Preconditions;

import io.reactivex.Observable;
import sanchez.sanchez.sergio.domain.executor.IPostExecutionThread;
import sanchez.sanchez.sergio.domain.executor.IThreadExecutor;
import sanchez.sanchez.sergio.domain.interactor.UseCase;
import sanchez.sanchez.sergio.domain.repository.ITerminalRepository;

/**
 * Delete All Terminals For Kid Interact
 */
public final class DeleteAllTerminalsForKidInteract extends UseCase<String, DeleteAllTerminalsForKidInteract.Params> {

    /**
     * Terminal Repository
     */
    private final ITerminalRepository terminalRepository;

    /**
     * @param threadExecutor
     * @param postExecutionThread
     */
    public DeleteAllTerminalsForKidInteract(final IThreadExecutor threadExecutor,
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
        Preconditions.checkNotNull(params.getKid(), "Kid Id can not be null");
        Preconditions.checkState(!params.getKid().isEmpty(), "Kid Id can not be empty");

        return terminalRepository.delete(params.getKid());
    }

    /**
     * Params
     */
    public static class Params {

        private final String kid;

        /**
         * Params
         * @param kid
         */
        private Params(final String kid) {
            this.kid = kid;
        }

        public String getKid() {
            return kid;
        }

        /**
         * Create
         * @param kid
         * @return
         */
        public static DeleteAllTerminalsForKidInteract.Params create(final String kid) {
            return new DeleteAllTerminalsForKidInteract.Params(kid);
        }
    }

}
