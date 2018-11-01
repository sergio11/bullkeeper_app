package sanchez.sanchez.sergio.domain.interactor.timeallowance;

import com.fernandocejas.arrow.checks.Preconditions;

import java.util.ArrayList;
import java.util.List;

import io.reactivex.Observable;
import sanchez.sanchez.sergio.domain.executor.IPostExecutionThread;
import sanchez.sanchez.sergio.domain.executor.IThreadExecutor;
import sanchez.sanchez.sergio.domain.interactor.UseCase;
import sanchez.sanchez.sergio.domain.models.ScreenTimeAllowanceEntity;
import sanchez.sanchez.sergio.domain.repository.IScreenTimeAllowanceRepository;
import sanchez.sanchez.sergio.domain.utils.ISupportVisitable;
import sanchez.sanchez.sergio.domain.utils.ISupportVisitor;

/**
 * Get Screen Time Allowance By Chiild Id Interact
 */
public final class GetScreenTimeAllowanceByChildIdInteract extends UseCase<ScreenTimeAllowanceEntity, GetScreenTimeAllowanceByChildIdInteract.Params> {

    /**
     * Screen Time Allowance Repository
     */
    private final IScreenTimeAllowanceRepository screenTimeAllowanceRepository;

    /**
     * Abstract class for a Use Case
     *
     * @param threadExecutor
     * @param postExecutionThread
     * @param screenTimeAllowanceRepository
     */
    public GetScreenTimeAllowanceByChildIdInteract(final IThreadExecutor threadExecutor, final IPostExecutionThread postExecutionThread,
                                                   final IScreenTimeAllowanceRepository screenTimeAllowanceRepository) {
        super(threadExecutor, postExecutionThread);
        this.screenTimeAllowanceRepository = screenTimeAllowanceRepository;
    }

    /**
     *
     * @param params
     * @return
     */
    @Override
    protected Observable<ScreenTimeAllowanceEntity> buildUseCaseObservable(Params params) {
        Preconditions.checkNotNull(params, "Params can not be null");
        Preconditions.checkNotNull(params.getChildId(), "Child Id can not be null");
        Preconditions.checkState(!params.getChildId().isEmpty(),
                "Child Id can not be empty");


        final ScreenTimeAllowanceEntity screenTimeAllowanceEntity = new ScreenTimeAllowanceEntity();
        screenTimeAllowanceEntity.setRemainingTime(14);
        final List<ScreenTimeAllowanceEntity.TimeAllowancePerDayEntity> timeAllowancePerDayEntities =
                new ArrayList<>();
        timeAllowancePerDayEntities.add(new ScreenTimeAllowanceEntity.TimeAllowancePerDayEntity(1, "Monday", 2));
        timeAllowancePerDayEntities.add(new ScreenTimeAllowanceEntity.TimeAllowancePerDayEntity(2, "Tuesday", 2));
        timeAllowancePerDayEntities.add(new ScreenTimeAllowanceEntity.TimeAllowancePerDayEntity(3, "Wednesday", 2));
        timeAllowancePerDayEntities.add(new ScreenTimeAllowanceEntity.TimeAllowancePerDayEntity(4, "Thursday", 2));
        timeAllowancePerDayEntities.add(new ScreenTimeAllowanceEntity.TimeAllowancePerDayEntity(5, "Friday", 2));
        timeAllowancePerDayEntities.add(new ScreenTimeAllowanceEntity.TimeAllowancePerDayEntity(6, "Saturday", 2));
        timeAllowancePerDayEntities.add(new ScreenTimeAllowanceEntity.TimeAllowancePerDayEntity(7, "Sunday", 2));

        screenTimeAllowanceEntity.setTimeAllowancePerDay(timeAllowancePerDayEntities);

        return Observable.just(screenTimeAllowanceEntity);
    }

    /**
     * Params
     */
    public static class Params {

        private final String childId;

        private Params(String childId) {
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


    /**
     * Get Screen Time Allowance Api Errors
     */
    public enum GetScreenTimeAllowanceApiErrors
            implements ISupportVisitable<GetScreenTimeAllowanceApiErrors.IGetScreenTimeAllowanceApiErrorsVisitor> {

        /**
         * No Screen Time allowance Found
         */
        NO_SCREEN_TIME_ALLOWANCE_FOUND(){
            @Override
            public <E> void accept(final IGetScreenTimeAllowanceApiErrorsVisitor visitor, E data) {
                visitor.visitNoScreenAllowanceFound(visitor);
            }
        };

        /**
         * Get Screen Time Allowance Api Errors Visitor
         */
        public interface IGetScreenTimeAllowanceApiErrorsVisitor extends ISupportVisitor {

            /**
             * Visit No Scheduled Block Found
             * @param apiErrorsVisitor
             */
            void visitNoScreenAllowanceFound(final IGetScreenTimeAllowanceApiErrorsVisitor apiErrorsVisitor);
        }
    }
}
