package sanchez.sanchez.sergio.domain.interactor.scheduled;

import com.fernandocejas.arrow.checks.Preconditions;

import java.util.List;

import io.reactivex.Observable;
import sanchez.sanchez.sergio.domain.executor.IPostExecutionThread;
import sanchez.sanchez.sergio.domain.executor.IThreadExecutor;
import sanchez.sanchez.sergio.domain.interactor.UseCase;
import sanchez.sanchez.sergio.domain.models.ScheduledBlockStatusEntity;
import sanchez.sanchez.sergio.domain.repository.IScheduledBlockRepository;


/**
 * Save Scheduled Block Status Interact
 */
public class SaveScheduledBlockStatusInteract extends UseCase<String, SaveScheduledBlockStatusInteract.Params> {

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
    public SaveScheduledBlockStatusInteract(final IThreadExecutor threadExecutor,
                                            final IPostExecutionThread postExecutionThread,
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
        Preconditions.checkNotNull(params.childId, "Child id can not be null");
        Preconditions.checkState(!params.childId.isEmpty(), "Child Id can not be empty");
        Preconditions.checkNotNull(params.getScheduledBlockStatusEntities(), "Scheduled Block Status Entities");

        return scheduledBlockRepository.saveScheduledBlockStatus(params.getChildId(),
                params.getScheduledBlockStatusEntities());

    }

    /**
     * Params
     */
    public static class Params {

        private final String childId;
        private final List<ScheduledBlockStatusEntity> scheduledBlockStatusEntities;

        /**
         *
         * @param childId
         * @param scheduledBlockStatusEntities
         */
        private Params(final String childId, final List<ScheduledBlockStatusEntity> scheduledBlockStatusEntities) {
            this.childId = childId;
            this.scheduledBlockStatusEntities = scheduledBlockStatusEntities;
        }

        public String getChildId() {
            return childId;
        }

        public List<ScheduledBlockStatusEntity> getScheduledBlockStatusEntities() {
            return scheduledBlockStatusEntities;
        }

        /**
         * Create
         * @param childId
         * @param scheduledBlockStatusEntities
         * @return
         */
        public static Params create(final String childId, final List<ScheduledBlockStatusEntity> scheduledBlockStatusEntities){
           return new Params(childId, scheduledBlockStatusEntities);
        }
    }


}
