package sanchez.sanchez.sergio.domain.interactor.scheduled;

import com.fernandocejas.arrow.checks.Preconditions;
import org.joda.time.LocalTime;
import java.util.LinkedHashMap;
import java.util.List;
import io.reactivex.Observable;
import io.reactivex.ObservableSource;
import io.reactivex.functions.Function;
import sanchez.sanchez.sergio.domain.executor.IPostExecutionThread;
import sanchez.sanchez.sergio.domain.executor.IThreadExecutor;
import sanchez.sanchez.sergio.domain.interactor.UseCase;
import sanchez.sanchez.sergio.domain.models.ImageEntity;
import sanchez.sanchez.sergio.domain.models.ScheduledBlockEntity;
import sanchez.sanchez.sergio.domain.repository.IScheduledBlockRepository;
import sanchez.sanchez.sergio.domain.utils.IAppUtils;
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
     * App Utils
     */
    private final IAppUtils appUtils;



    /**
     * Abstract class for a Use Case
     *
     * @param threadExecutor
     * @param postExecutionThread
     * @param scheduledBlockRepository
     * @param appUtils
     */
    public SaveScheduledBlockInteract(final IThreadExecutor threadExecutor,
                                      final IPostExecutionThread postExecutionThread,
                                      final IScheduledBlockRepository scheduledBlockRepository,
                                      final IAppUtils appUtils) {
        super(threadExecutor, postExecutionThread);
        this.scheduledBlockRepository = scheduledBlockRepository;
        this.appUtils = appUtils;
    }

    /**
     * Save Scheduled Block
     * @param params
     * @return
     */
    private Observable<ScheduledBlockEntity> saveScheduledBlock(final Params params) {
        return scheduledBlockRepository.saveScheduledBlock(params.getIdentity(), params.getName(), params.isEnable(),
                params.getStartAt(), params.getEndAt(), params.getWeeklyFrequency(),
                params.isRecurringWeeklyEnabled(), params.getChildId());
    }

    /**
     *
     * @param params
     * @return
     */
    @Override
    protected Observable<ScheduledBlockEntity> buildUseCaseObservable(final Params params) {
        Preconditions.checkNotNull(params, "Params can not be null");
        Preconditions.checkNotNull(params.name, "Name can not be null");
        Preconditions.checkState(!params.name.isEmpty(), "Name can not be empty");
        Preconditions.checkNotNull(params.startAt, "Start at can not be null");
        Preconditions.checkNotNull(params.endAt, "Ent At can not be null");
        Preconditions.checkNotNull(params.weeklyFrequency, "Weekly Frequency can not be null");
        Preconditions.checkState(params.weeklyFrequency.length == 7, "Weekly Frequency should have 7 elements");
        Preconditions.checkNotNull(params.childId, "Child id can not be null");
        Preconditions.checkState(!params.childId.isEmpty(), "Child Id can not be empty");

        return appUtils.isValidString(params.image) ?
                saveScheduledBlock(params).flatMap(new Function<ScheduledBlockEntity, ObservableSource<ScheduledBlockEntity>>() {
                    @Override
                    public ObservableSource<ScheduledBlockEntity> apply(final ScheduledBlockEntity scheduledBlockEntity) throws Exception {
                        return scheduledBlockRepository.uploadImage(scheduledBlockEntity.getChildId(),
                                scheduledBlockEntity.getIdentity(), params.image).map(new Function<ImageEntity, ScheduledBlockEntity>() {
                            @Override
                            public ScheduledBlockEntity apply(ImageEntity imageEntity) throws Exception {
                                return scheduledBlockEntity;
                            }
                        });
                    }
                }) :  saveScheduledBlock(params);
    }

    /**
     * Params
     */
    public static class Params {

        private final String identity;
        private final String name;
        private final boolean enable;
        private final LocalTime startAt;
        private final LocalTime endAt;
        private final int[] weeklyFrequency;
        private final boolean recurringWeeklyEnabled;
        private final String childId;
        private final String image;

        /**
         *
         * @param identity
         * @param name
         * @param enable
         * @param startAt
         * @param endAt
         * @param weeklyFrequency
         * @param recurringWeeklyEnabled
         * @param childId
         */
        private Params(final String identity, final String name, final boolean enable,
                       final LocalTime startAt, final LocalTime endAt,
                       final int[] weeklyFrequency, final boolean recurringWeeklyEnabled,
                       final String childId, final String image) {
            this.identity = identity;
            this.name = name;
            this.enable = enable;
            this.startAt = startAt;
            this.endAt = endAt;
            this.weeklyFrequency = weeklyFrequency;
            this.recurringWeeklyEnabled = recurringWeeklyEnabled;
            this.childId = childId;
            this.image = image;
        }

        public String getIdentity() {
            return identity;
        }

        public String getName() {
            return name;
        }

        public boolean isEnable() {
            return enable;
        }

        public LocalTime getStartAt() {
            return startAt;
        }

        public LocalTime getEndAt() {
            return endAt;
        }

        public int[] getWeeklyFrequency() {
            return weeklyFrequency;
        }

        public boolean isRecurringWeeklyEnabled() {
            return recurringWeeklyEnabled;
        }

        public String getChildId() {
            return childId;
        }

        public String getImage() {
            return image;
        }

        /**
         * Create
         * @param identity
         * @param name
         * @param enable
         * @param startAt
         * @param endAt
         * @param weeklyFrequency
         * @param recurringWeeklyEnabled
         * @param childId
         * @return
         */
        public static Params create(final String identity, final String name, final boolean enable, final LocalTime startAt,
                                    final LocalTime endAt, final int[] weeklyFrequency, final boolean recurringWeeklyEnabled,
                                    final String childId, final String image){
           return new Params(identity, name, enable, startAt,
                   endAt, weeklyFrequency, recurringWeeklyEnabled, childId, image);
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
