package sanchez.sanchez.sergio.domain.interactor.funtime;

import com.fernandocejas.arrow.checks.Preconditions;
import io.reactivex.Observable;
import sanchez.sanchez.sergio.domain.executor.IPostExecutionThread;
import sanchez.sanchez.sergio.domain.executor.IThreadExecutor;
import sanchez.sanchez.sergio.domain.interactor.UseCase;
import sanchez.sanchez.sergio.domain.models.FunTimeScheduledEntity;
import sanchez.sanchez.sergio.domain.repository.IFunTimeRepository;

/**
 * Save Fun Time Interact
 */
public final class SaveFunTimeInteract
        extends UseCase<FunTimeScheduledEntity, SaveFunTimeInteract.Params> {

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
    public SaveFunTimeInteract(
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
        Preconditions.checkNotNull(params.getFunTimeScheduled(),
                "Fun Time Scheduled can not be null");
        // Save Fun Time Scheduled
        return funTimeRepository
                .saveFunTimeScheduled(
                        params.getKid(),
                        params.getTerminal(),
                        params.getFunTimeScheduled());
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
         * Fun Time Scheduled
         */
        private final FunTimeScheduledEntity funTimeScheduled;

        /**
         * @param kid
         * @param terminal
         * @param funTimeScheduled
         */
        private Params(
                final String kid,
                final String terminal,
                final FunTimeScheduledEntity funTimeScheduled) {
            this.kid = kid;
            this.terminal = terminal;
            this.funTimeScheduled = funTimeScheduled;
        }

        /**
         * Get Kid
         * @return
         */
        public String getKid() {
            return kid;
        }

        /**
         * Get Terminal
         * @return
         */
        public String getTerminal() {
            return terminal;
        }

        /**
         * Get Fun Time Scheduled
         * @return
         */
        public FunTimeScheduledEntity getFunTimeScheduled() {
            return funTimeScheduled;
        }

        /**
         * Create
         * @param kid
         * @param terminal
         * @param funTimeScheduled
         * @return
         */
        public static Params create(
                final String kid,
                final String terminal,
                final FunTimeScheduledEntity funTimeScheduled) {
            return new Params(kid, terminal, funTimeScheduled);
        }
    }

}
