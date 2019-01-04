package sanchez.sanchez.sergio.domain.interactor.kidrequest;

import com.fernandocejas.arrow.checks.Preconditions;
import io.reactivex.Observable;
import sanchez.sanchez.sergio.domain.executor.IPostExecutionThread;
import sanchez.sanchez.sergio.domain.executor.IThreadExecutor;
import sanchez.sanchez.sergio.domain.interactor.UseCase;
import sanchez.sanchez.sergio.domain.models.KidRequestEntity;
import sanchez.sanchez.sergio.domain.repository.IKidRequestRepository;
import sanchez.sanchez.sergio.domain.utils.ISupportVisitable;
import sanchez.sanchez.sergio.domain.utils.ISupportVisitor;

/**
 * Get Kid Request Detail Interact
 */
public final class GetKidRequestDetailInteract extends
        UseCase<KidRequestEntity, GetKidRequestDetailInteract.Params> {

    /**
     * Kid Request Repository
     */
    private final IKidRequestRepository kidRequestRepository;

    /**
     * @param threadExecutor
     * @param postExecutionThread
     * @param kidRequestRepository
     */
    public GetKidRequestDetailInteract(
            final IThreadExecutor threadExecutor,
            final IPostExecutionThread postExecutionThread,
            final IKidRequestRepository kidRequestRepository) {
        super(threadExecutor, postExecutionThread);
        this.kidRequestRepository = kidRequestRepository;
    }

    /**
     *
     * @param params
     * @return
     */
    @Override
    protected Observable<KidRequestEntity> buildUseCaseObservable(final Params params) {
        Preconditions.checkNotNull(params, "Params can not be null");
        Preconditions.checkNotNull(params.getKid(), "Kid can not be null");
        Preconditions.checkState(!params.getKid().isEmpty(), "Kid can not be empty");

        return kidRequestRepository.getKidRequestDetail(params.getKid(), params.getIdentity());
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
         * Identity
         */
        private final String identity;

        /**
         * @param kid
         * @param identity
         */
        private Params(final String kid, final String identity) {
            this.kid = kid;
            this.identity = identity;
        }

        public String getKid() {
            return kid;
        }

        public String getIdentity() {
            return identity;
        }

        /**
         * Create
         * @param kid
         * @param id
         * @return
         */
        public static Params create(final String kid, final String id) {
            return new Params(kid, id);
        }
    }


    /**
     * Get Kid Request Api Error
     */
    public enum GetKidRequestDetailApiErrors
            implements ISupportVisitable<GetKidRequestDetailApiErrors.IGetKidRequestDetailApiErrorsVisitor> {

        /**
         * Kid Request Not Found
         */
        KID_REQUEST_NOT_FOUND(){
            @Override
            public <E> void accept(final IGetKidRequestDetailApiErrorsVisitor visitor, E data) {
                visitor.visitKidRequestNotFound(visitor);
            }
        };

        /**
         * Get Kid Request Detail Api Errors Visitor
         */
        public interface IGetKidRequestDetailApiErrorsVisitor extends ISupportVisitor {

            /**
             * Visit Kid Request Not Found
             * @param apiErrorsVisitor
             */
            void visitKidRequestNotFound(final IGetKidRequestDetailApiErrorsVisitor apiErrorsVisitor);
        }
    }
}
