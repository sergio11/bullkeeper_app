package sanchez.sanchez.sergio.domain.interactor.scheduled;

import com.fernandocejas.arrow.checks.Preconditions;

import io.reactivex.Observable;
import sanchez.sanchez.sergio.domain.executor.IPostExecutionThread;
import sanchez.sanchez.sergio.domain.executor.IThreadExecutor;
import sanchez.sanchez.sergio.domain.interactor.UseCase;
import sanchez.sanchez.sergio.domain.repository.IScheduledBlockRepository;

/**
 * Delete Scheduled Block Interact
 */
public class DeleteScheduledBlockInteract extends UseCase<String, DeleteScheduledBlockInteract.Params> {

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
    public DeleteScheduledBlockInteract(final IThreadExecutor threadExecutor, final IPostExecutionThread postExecutionThread,
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
        return null;
    }


    /**
     * Params
     */
    public static class Params {

        private final String childId;

        private Params(final String childId) {
            this.childId = childId;
        }

        public static Params create(final String childId) {
            return new Params(childId);
        }
    }
}
