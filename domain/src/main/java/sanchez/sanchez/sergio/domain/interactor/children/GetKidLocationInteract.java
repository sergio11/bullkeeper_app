package sanchez.sanchez.sergio.domain.interactor.children;

import com.fernandocejas.arrow.checks.Preconditions;

import io.reactivex.Observable;
import sanchez.sanchez.sergio.domain.executor.IPostExecutionThread;
import sanchez.sanchez.sergio.domain.executor.IThreadExecutor;
import sanchez.sanchez.sergio.domain.interactor.UseCase;
import sanchez.sanchez.sergio.domain.models.LocationEntity;
import sanchez.sanchez.sergio.domain.repository.IChildrenRepository;
import sanchez.sanchez.sergio.domain.utils.ISupportVisitable;
import sanchez.sanchez.sergio.domain.utils.ISupportVisitor;

/**
 * Get Kid Location Interact
 */
public final class GetKidLocationInteract
    extends UseCase<LocationEntity, GetKidLocationInteract.Params> {

    /**
     * Children Repository
     */
    private final IChildrenRepository childrenRepository;


    /**
     * Abstract class for a Use Case
     *
     * @param threadExecutor
     * @param postExecutionThread
     * @param childrenRepository
     */
    public GetKidLocationInteract(
            final IThreadExecutor threadExecutor,
            final IPostExecutionThread postExecutionThread,
            final IChildrenRepository childrenRepository) {
        super(threadExecutor, postExecutionThread);
        this.childrenRepository = childrenRepository;
    }

    /**
     * @param params
     * @return
     */
    @Override
    protected Observable<LocationEntity> buildUseCaseObservable(Params params) {
        Preconditions.checkNotNull(params, "Params can not be null");
        Preconditions.checkNotNull(params.getKid(), "Kid can not be null");
        Preconditions.checkState(!params.getKid().isEmpty(), "Kid can not be empty");

        return childrenRepository.getKidLocation(params.getKid());
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
        public Params(final String kid) {
            this.kid = kid;
        }

        public String getKid() {
            return kid;
        }

        /**
         * Create
         * @param kid
         * @return
         */
        public static Params create(final String kid) {
            return new Params(kid);
        }
    }

    /**
     * Get Kid Location Api Errors
     */
    public enum GetKidLocationApiErrors
            implements ISupportVisitable<GetKidLocationApiErrors.IGetKidLocationApiErrorsVisitor> {

        /**
         * No Current Location Found
         */
        NO_CURRENT_LOCATION_FOUND(){
            @Override
            public <E> void accept(final IGetKidLocationApiErrorsVisitor visitor, E data) {
                visitor.visitNoLocationFound(visitor);
            }
        };

        /**
         * Get Kid Location Api Errors Visitor
         */
        public interface IGetKidLocationApiErrorsVisitor extends ISupportVisitor {

            /**
             * Visit No Location Found
             * @param apiErrorsVisitor
             */
            void visitNoLocationFound(final IGetKidLocationApiErrorsVisitor apiErrorsVisitor);
        }
    }
}
