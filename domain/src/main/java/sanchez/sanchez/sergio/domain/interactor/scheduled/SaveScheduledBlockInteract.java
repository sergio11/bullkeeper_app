package sanchez.sanchez.sergio.domain.interactor.scheduled;

import com.fernandocejas.arrow.checks.Preconditions;

import org.joda.time.LocalDateTime;

import java.util.LinkedHashMap;
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
 * Save Scheduled Block Interact
 */
public class SaveScheduledBlockInteract extends UseCase<ScheduledBlockEntity, SaveScheduledBlockInteract.Params> {

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
    public SaveScheduledBlockInteract(final IThreadExecutor threadExecutor,
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
    protected Observable<ScheduledBlockEntity> buildUseCaseObservable(Params params) {
        Preconditions.checkNotNull(params, "Params can not be null");
        return null;


    }

    /**
     * Params
     */
    public static class Params {

        private final String identity;
        private final String name;
        private final LocalDateTime startAt;
        private final LocalDateTime endAt;
        private final int[] weeklyFrequency;
        private final boolean recurringWeeklyEnabled;

        private Params(String identity, String name, LocalDateTime startAt,
                       LocalDateTime endAt, int[] weeklyFrequency, boolean recurringWeeklyEnabled) {
            this.identity = identity;
            this.name = name;
            this.startAt = startAt;
            this.endAt = endAt;
            this.weeklyFrequency = weeklyFrequency;
            this.recurringWeeklyEnabled = recurringWeeklyEnabled;
        }

        /**
         * Create
         * @param identity
         * @param name
         * @param startAt
         * @param endAt
         * @param weeklyFrequency
         * @param recurringWeeklyEnabled
         * @return
         */
        public static Params create(final String identity, final String name, final LocalDateTime startAt,
                                    final LocalDateTime endAt, final int[] weeklyFrequency, final boolean recurringWeeklyEnabled){
           return new Params(identity, name, startAt, endAt, weeklyFrequency, recurringWeeklyEnabled);
        }
    }

    /**
     * Save Scheduled Block Interact
     */
    public enum SaveScheduledBlockApiErrors implements ISupportVisitable<SaveScheduledBlockApiErrors.ISaveScheduledBlockVisitor> {

        /**
         * Validation Errors
         */
        VALIDATION_ERROR(){
            @Override
            public <E> void accept(ISaveScheduledBlockVisitor visitor, E data) {
                visitor.visitValidationError(this, (LinkedHashMap<String, List<LinkedHashMap<String, String>>>) data);
            }
        };

        /**
         * Save Scheduled Block Visitor
         */
        public interface ISaveScheduledBlockVisitor extends ISupportVisitor {

            /**
             * Visit Validation Error
             * @param apiErrors
             * @param errors
             */
            void visitValidationError(final SaveScheduledBlockApiErrors apiErrors, final LinkedHashMap<String, List<LinkedHashMap<String, String>>> errors);

        }
    }

}
