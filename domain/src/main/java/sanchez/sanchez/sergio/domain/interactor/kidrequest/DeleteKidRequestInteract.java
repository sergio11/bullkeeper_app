package sanchez.sanchez.sergio.domain.interactor.kidrequest;

import com.fernandocejas.arrow.checks.Preconditions;

import io.reactivex.Observable;
import sanchez.sanchez.sergio.domain.executor.IPostExecutionThread;
import sanchez.sanchez.sergio.domain.executor.IThreadExecutor;
import sanchez.sanchez.sergio.domain.interactor.UseCase;
import sanchez.sanchez.sergio.domain.repository.IKidRequestRepository;

/**
 * Delete Kid Request Interact
 */
public final class DeleteKidRequestInteract extends UseCase<String, DeleteKidRequestInteract.Params> {

    /**
     * Kid Request Repository
     */
    private final IKidRequestRepository kidRequestRepository;

    /**
     * @param threadExecutor
     * @param postExecutionThread
     * @param kidRequestRepository
     */
    public DeleteKidRequestInteract(
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
    protected Observable<String> buildUseCaseObservable(Params params) {
        Preconditions.checkNotNull(params, "Params can not be null");
        Preconditions.checkNotNull(params.getKid(), "Kid can not be null");
        Preconditions.checkNotNull(params.getId(), "Id can not be null");
        Preconditions.checkState(!params.getKid().isEmpty(), "Kid can not be empty");
        Preconditions.checkState(!params.getId().isEmpty(), "Id can not be empty");

        // Delete Kid Request
        return kidRequestRepository.deleteKidRequest(params.getKid(), params.getId());
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
         * Id
         */
        private final String id;

        /**
         *
         * @param kid
         * @param id
         */
        private Params(final String kid, final String id) {
            this.kid = kid;
            this.id = id;
        }

        public String getKid() {
            return kid;
        }

        public String getId() {
            return id;
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

}
