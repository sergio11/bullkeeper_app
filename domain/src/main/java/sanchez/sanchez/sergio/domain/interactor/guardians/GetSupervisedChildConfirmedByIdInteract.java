package sanchez.sanchez.sergio.domain.interactor.guardians;

import com.fernandocejas.arrow.checks.Preconditions;
import io.reactivex.Observable;
import sanchez.sanchez.sergio.domain.executor.IPostExecutionThread;
import sanchez.sanchez.sergio.domain.executor.IThreadExecutor;
import sanchez.sanchez.sergio.domain.interactor.UseCase;
import sanchez.sanchez.sergio.domain.models.KidGuardianEntity;
import sanchez.sanchez.sergio.domain.repository.IGuardianRepository;
import sanchez.sanchez.sergio.domain.utils.ISupportVisitable;
import sanchez.sanchez.sergio.domain.utils.ISupportVisitor;

/**
 *
 */
public final class GetSupervisedChildConfirmedByIdInteract extends UseCase<KidGuardianEntity, GetSupervisedChildConfirmedByIdInteract.Params> {

    /**
     * Guardian Repository
     */
    private final IGuardianRepository guardianRepository;

    /**
     * @param threadExecutor
     * @param postExecutionThread
     * @param guardianRepository
     */
    public GetSupervisedChildConfirmedByIdInteract(final IThreadExecutor threadExecutor, final IPostExecutionThread postExecutionThread,
                                                   final IGuardianRepository guardianRepository) {
        super(threadExecutor, postExecutionThread);
        this.guardianRepository = guardianRepository;
    }

    /**
     *
     * @param params
     * @return
     */
    @Override
    protected Observable<KidGuardianEntity> buildUseCaseObservable(final Params params) {
        Preconditions.checkNotNull(params, "Params can not be null");
        Preconditions.checkNotNull(params.getKid(), "Params kid can not be null");
        return guardianRepository.getSupervisedChildConfirmedById(params.getKid());
    }

    public static class Params {

        private final String kid;

        /**
         *
         * @param kid
         */
        private Params(final String kid) {
            this.kid = kid;
        }

        public String getKid() {
            return kid;
        }

        /**
         *
         * @param kid
         * @return
         */
        public static Params create(final String kid) {
            Preconditions.checkNotNull(kid, "Kid can not be null");
            Preconditions.checkState(!kid.isEmpty(), "Kid can not be empty");
            return new Params(kid);
        }

    }


    /**
     * Get Supervised Child Confirmed By Id API Error
     */
    public enum GetSupervisedChildConfirmedByIdApiErrors
            implements ISupportVisitable<GetSupervisedChildConfirmedByIdApiErrors.IGetSupervisedChildConfirmedByIdApiErrorVisitor> {

        /**
         * Supervised Children Confirmed Not Found
         */
        SUPERVISED_CHILDREN_CONFIRMED_NOT_FOUND_EXCEPTION() {
            @Override
            public <E> void accept(IGetSupervisedChildConfirmedByIdApiErrorVisitor visitor, E data) {
                visitor.visitSupervisedChildrenConfirmedNotFound(this);
            }
        };

        /**
         * Get Supervised Child Confirmed By Id
         */
        public interface IGetSupervisedChildConfirmedByIdApiErrorVisitor extends ISupportVisitor {

            /**
             * Visit No Guardians Found
             * @param error
             */
            void visitSupervisedChildrenConfirmedNotFound(final GetSupervisedChildConfirmedByIdApiErrors error);

        }
    }
}
