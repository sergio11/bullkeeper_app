package sanchez.sanchez.sergio.domain.interactor.scheduled;

import com.fernandocejas.arrow.checks.Preconditions;
import io.reactivex.Observable;
import sanchez.sanchez.sergio.domain.executor.IPostExecutionThread;
import sanchez.sanchez.sergio.domain.executor.IThreadExecutor;
import sanchez.sanchez.sergio.domain.interactor.UseCase;
import sanchez.sanchez.sergio.domain.repository.IScheduledBlockRepository;

/**
 * Delete All Scheduled Blocks Interact
 */
public class DeleteAllScheduledBlocksInteract extends UseCase<String, DeleteAllScheduledBlocksInteract.Params> {

    /**
     * Scheduled Block Repository
     */
    private final IScheduledBlockRepository scheduledBlockRepository;

    /**
     * Abstract class for a Use Case
     *
     * @param threadExecutor
     * @param postExecutionThread
     */
    public DeleteAllScheduledBlocksInteract(final IThreadExecutor threadExecutor, final IPostExecutionThread postExecutionThread,
                                            final IScheduledBlockRepository scheduledBlockRepository) {
        super(threadExecutor, postExecutionThread);
        this.scheduledBlockRepository = scheduledBlockRepository;
    }

    /**
     *
     * @param params
     * @return
     */
    @Override
    protected Observable<String> buildUseCaseObservable(Params params) {
        Preconditions.checkNotNull(params, "Params can not be null");
        Preconditions.checkNotNull(params.getChildId(), "Child Id can not be null");
        Preconditions.checkState(!params.getChildId().isEmpty(), "Child Id can not empty");

        return scheduledBlockRepository.deleteAllScheduledBlockByChildId(params.getChildId());
    }


    /**
     * Params
     */
    public static class Params {

        /**
         * Child Id
         */
        private final String childId;

        /**
         * @param childId
         */
        private Params(final String childId) {
            this.childId = childId;
        }

        public String getChildId() {
            return childId;
        }

        /**
         * Create
         * @param childId
         * @return
         */
        public static Params create(final String childId) {
            return new Params(childId);
        }
    }
}
