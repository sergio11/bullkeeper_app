package sanchez.sanchez.sergio.domain.interactor.kidrequest;

import com.fernandocejas.arrow.checks.Preconditions;

import java.util.ArrayList;
import java.util.List;
import io.reactivex.Observable;
import sanchez.sanchez.sergio.domain.executor.IPostExecutionThread;
import sanchez.sanchez.sergio.domain.executor.IThreadExecutor;
import sanchez.sanchez.sergio.domain.interactor.UseCase;
import sanchez.sanchez.sergio.domain.repository.IKidRequestRepository;

/**
 * Delete All Kid Request Interact
 */
public final class DeleteAllKidRequestInteract
        extends UseCase<String, DeleteAllKidRequestInteract.Params> {

    /**
     * Kid Request Repository
     */
    private final IKidRequestRepository kidRequestRepository;

    /**
     * @param threadExecutor
     * @param postExecutionThread
     * @param kidRequestRepository
     */
    public DeleteAllKidRequestInteract(
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
    protected Observable<String> buildUseCaseObservable(final Params params) {
        Preconditions.checkNotNull(params, "Params can not be null");
        Preconditions.checkNotNull(params.kid, "Kid can not be null");
        Preconditions.checkState(!params.kid.isEmpty(), "Kid can not be empty");
        Preconditions.checkNotNull(params.ids, "Ids can not be null");

        return params.getIds().isEmpty() ?
                kidRequestRepository.deleteAllRequestForKid(params.kid) :
                kidRequestRepository.deleteRequestForKid(params.kid, params.ids);
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
         * Ids
         */
        private final List<String> ids;

        /**
         *
         * @param kid
         * @param ids
         */
        private Params(final String kid, final List<String> ids) {
            this.kid = kid;
            this.ids = ids;
        }

        public String getKid() {
            return kid;
        }

        public List<String> getIds() {
            return ids;
        }

        /**
         * Create
         * @param kid
         * @param ids
         * @return
         */
        public static Params create(final String kid, final List<String> ids) {
            return new Params(kid, ids);
        }

        /**
         * Create
         * @param kid
         * @return
         */
        public static Params create(final String kid) {
            return new Params(kid, new ArrayList<String>());
        }
    }

}
