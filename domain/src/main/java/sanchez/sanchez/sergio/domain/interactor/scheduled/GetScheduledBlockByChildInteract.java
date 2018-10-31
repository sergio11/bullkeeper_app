package sanchez.sanchez.sergio.domain.interactor.scheduled;

import com.fernandocejas.arrow.checks.Preconditions;
import java.util.List;
import io.reactivex.Observable;
import sanchez.sanchez.sergio.domain.executor.IPostExecutionThread;
import sanchez.sanchez.sergio.domain.executor.IThreadExecutor;
import sanchez.sanchez.sergio.domain.interactor.UseCase;
import sanchez.sanchez.sergio.domain.models.ScheduledBlockEntity;
import sanchez.sanchez.sergio.domain.repository.IScheduledBlockRepository;
import sanchez.sanchez.sergio.domain.utils.ISupportVisitable;
import sanchez.sanchez.sergio.domain.utils.ISupportVisitor;

/**
 * Get Scheduled Block By Child Interact
 */
public final class GetScheduledBlockByChildInteract extends UseCase<List<ScheduledBlockEntity>, GetScheduledBlockByChildInteract.Params> {

    /**
     * Scheduled Block Repository
     */
    private final IScheduledBlockRepository scheduledBlockRepository;

    /**
     * Abstract class for a Use Case
     *
     * @param threadExecutor
     * @param postExecutionThread
     * @param scheduledBlockRepository
     */
    public GetScheduledBlockByChildInteract(final IThreadExecutor threadExecutor, final IPostExecutionThread postExecutionThread,
                                            final IScheduledBlockRepository scheduledBlockRepository) {
        super(threadExecutor, postExecutionThread);
        this.scheduledBlockRepository = scheduledBlockRepository;
    }

    /**
     * @param params
     * @return
     */
    @Override
    protected Observable<List<ScheduledBlockEntity>> buildUseCaseObservable(final Params params) {
        Preconditions.checkNotNull(params, "Params can not be null");
        Preconditions.checkNotNull(params.getChildId(), "Child Id can not be null");
        Preconditions.checkState(!params.getChildId().isEmpty(), "Child Id can not be empty");

        return scheduledBlockRepository.getScheduledBlockByChildId(params.getChildId());
    }


    /**
     * Params
     */
    public static class Params {

        private final String childId;

        private Params(final String childId) {
            this.childId = childId;
        }

        public String getChildId() {
            return childId;
        }

        public static Params create(final String childId) {
            return new Params(childId);
        }
    }


    /**
     * Get Scheduled Block By Child Api Errors
     */
    public enum GetScheduledBlockByChildApiErrors
            implements ISupportVisitable<GetScheduledBlockByChildApiErrors.IGetScheduledBlockByChildApiErrorsVisitor> {

        /**
         * No Scheduled Block Found
         */
        NO_SCHEDULED_BLOCK_FOUND(){
            @Override
            public <E> void accept(final IGetScheduledBlockByChildApiErrorsVisitor visitor, E data) {
                visitor.visitNoScheduledBlockFound(visitor);
            }
        };

        /**
         * Get Scheduled Block By Child Api Errors Visitor
         */
        public interface IGetScheduledBlockByChildApiErrorsVisitor extends ISupportVisitor {

            /**
             * Visit No Scheduled Block Found
             * @param apiErrorsVisitor
             */
            void visitNoScheduledBlockFound(final IGetScheduledBlockByChildApiErrorsVisitor apiErrorsVisitor);
        }
    }
}
