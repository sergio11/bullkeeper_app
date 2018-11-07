package sanchez.sanchez.sergio.domain.interactor.scheduled;

import com.fernandocejas.arrow.checks.Preconditions;
import io.reactivex.Observable;
import sanchez.sanchez.sergio.domain.executor.IPostExecutionThread;
import sanchez.sanchez.sergio.domain.executor.IThreadExecutor;
import sanchez.sanchez.sergio.domain.interactor.UseCase;
import sanchez.sanchez.sergio.domain.repository.IScheduledBlockRepository;

/**
 * Delete Scheduled Block By Id Interact
 */
public class DeleteScheduledBlockByIdInteract extends UseCase<String, DeleteScheduledBlockByIdInteract.Params> {

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
    public DeleteScheduledBlockByIdInteract(final IThreadExecutor threadExecutor, final IPostExecutionThread postExecutionThread,
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
        Preconditions.checkNotNull(params.getBlockId(), "Child Id can not be null");
        Preconditions.checkState(!params.getBlockId().isEmpty(), "Block Id can not empty");

        return scheduledBlockRepository.deleteScheduledBlockById(params.getChildId(),
                params.getBlockId());
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
         * Block Id
         */
        private final String blockId;

        /**
         *
         * @param childId
         * @param blockId
         */
        private Params(final String childId, final String blockId) {
            this.childId = childId;
            this.blockId = blockId;
        }

        public String getChildId() {
            return childId;
        }

        public String getBlockId() {
            return blockId;
        }

        /**
         * Create
         * @param childId
         * @param blockId
         * @return
         */
        public static Params create(final String childId, final String blockId) {
            return new Params(childId, blockId);
        }
    }
}
