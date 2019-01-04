package sanchez.sanchez.sergio.domain.interactor.kidrequest;

import com.fernandocejas.arrow.checks.Preconditions;

import java.util.List;

import io.reactivex.Observable;
import sanchez.sanchez.sergio.domain.executor.IPostExecutionThread;
import sanchez.sanchez.sergio.domain.executor.IThreadExecutor;
import sanchez.sanchez.sergio.domain.interactor.UseCase;
import sanchez.sanchez.sergio.domain.models.KidRequestEntity;
import sanchez.sanchez.sergio.domain.repository.IKidRequestRepository;
import sanchez.sanchez.sergio.domain.utils.ISupportVisitable;
import sanchez.sanchez.sergio.domain.utils.ISupportVisitor;

/**
 * Get Kid Request Interact
 */
public final class GetKidRequestInteract extends
        UseCase<List<KidRequestEntity>, GetKidRequestInteract.Params> {

    /**
     * Kid Request Repository
     */
    private final IKidRequestRepository kidRequestRepository;

    /**
     * @param threadExecutor
     * @param postExecutionThread
     * @param kidRequestRepository
     */
    public GetKidRequestInteract(
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
    protected Observable<List<KidRequestEntity>> buildUseCaseObservable(final Params params) {
        Preconditions.checkNotNull(params, "Params can not be null");
        Preconditions.checkNotNull(params.getKid(), "Kid can not be null");
        Preconditions.checkState(!params.getKid().isEmpty(), "Kid can not be empty");

        return kidRequestRepository.getAllRequestForKid(params.getKid());

    }

    /**
     * Params
     */
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
         * Create
         * @param kid
         * @return
         */
        public static Params create(final String kid) {
            return new Params(kid);
        }
    }


    /**
     * Get Kid Request Api Error
     */
    public enum GetKidRequestApiErrors
            implements ISupportVisitable<GetKidRequestApiErrors.IGetKidRequestApiErrorsVisitor> {

        /**
         * No Kid Request Found
         */
        NO_KID_REQUEST_FOUND(){
            @Override
            public <E> void accept(final IGetKidRequestApiErrorsVisitor visitor, E data) {
                visitor.visitNoKidRequestFound(visitor);
            }
        };

        /**
         * Get Kid Request Api Errors Visitor
         */
        public interface IGetKidRequestApiErrorsVisitor extends ISupportVisitor {

            /**
             * Visit No Kid Request Found
             * @param apiErrorsVisitor
             */
            void visitNoKidRequestFound(final IGetKidRequestApiErrorsVisitor apiErrorsVisitor);
        }
    }
}
