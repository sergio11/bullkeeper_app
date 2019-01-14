package sanchez.sanchez.sergio.domain.interactor.funtime;

import com.fernandocejas.arrow.checks.Preconditions;
import io.reactivex.Observable;
import sanchez.sanchez.sergio.domain.executor.IPostExecutionThread;
import sanchez.sanchez.sergio.domain.executor.IThreadExecutor;
import sanchez.sanchez.sergio.domain.interactor.UseCase;
import sanchez.sanchez.sergio.domain.models.FunTimeScheduledEntity;
import sanchez.sanchez.sergio.domain.repository.IFunTimeRepository;
import sanchez.sanchez.sergio.domain.utils.ISupportVisitable;
import sanchez.sanchez.sergio.domain.utils.ISupportVisitor;

/**
 * Get Fun Time By Child Id Interact
 */
public final class GetFunTimeByChildIdInteract
        extends UseCase<FunTimeScheduledEntity, GetFunTimeByChildIdInteract.Params> {

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
    public GetFunTimeByChildIdInteract(final IThreadExecutor threadExecutor, final IPostExecutionThread postExecutionThread,
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
        Preconditions.checkNotNull(params.getKid(), "Child Id can not be null");
        Preconditions.checkState(!params.getKid().isEmpty(),
                "Child Id can not be empty");
        // Get Fun Time Scheduled By Kid
        return funTimeRepository.getFunTimeScheduledByKid(params.getKid());
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
         *
         * @param kid
         */
        private Params(final String kid) {
            this.kid = kid;
        }

        /**
         * Get Child Id
         * @return
         */
        public String getKid() {
            return kid;
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
     * Get Fun Time Api Errors
     */
    public enum GetFunTimeApiErrors
            implements ISupportVisitable<GetFunTimeApiErrors.IGetFunTimeApiErrorsVisitor> {

        /**
         * Fun Time Not Found
         */
        FUN_TIME_NOT_FOUND(){
            @Override
            public <E> void accept(final IGetFunTimeApiErrorsVisitor visitor, E data) {
                visitor.visitNoFunTimeFound(visitor);
            }
        };

        /**
         * Get Fun Time Api Errors Visitor
         */
        public interface IGetFunTimeApiErrorsVisitor extends ISupportVisitor {

            /**
             * Visit No Fun Time Found
             * @param apiErrorsVisitor
             */
            void visitNoFunTimeFound(final IGetFunTimeApiErrorsVisitor apiErrorsVisitor);
        }
    }
}
