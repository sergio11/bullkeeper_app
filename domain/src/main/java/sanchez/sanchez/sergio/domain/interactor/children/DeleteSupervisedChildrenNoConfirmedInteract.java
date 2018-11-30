package sanchez.sanchez.sergio.domain.interactor.children;

import com.fernandocejas.arrow.checks.Preconditions;

import io.reactivex.Observable;
import sanchez.sanchez.sergio.domain.executor.IPostExecutionThread;
import sanchez.sanchez.sergio.domain.executor.IThreadExecutor;
import sanchez.sanchez.sergio.domain.interactor.UseCase;
import sanchez.sanchez.sergio.domain.repository.ISupervisedChildrenRepository;

/**
 * Delete Supervised Children No Confirmed
 */
public final class DeleteSupervisedChildrenNoConfirmedInteract
        extends UseCase<String, DeleteSupervisedChildrenNoConfirmedInteract.Params> {

    /**
     * Supervised Children Repository
     */
    private final ISupervisedChildrenRepository supervisedChildrenRepository;

    /**
     * @param threadExecutor
     * @param postExecutionThread
     */
    public DeleteSupervisedChildrenNoConfirmedInteract(final IThreadExecutor threadExecutor,
                                                       final IPostExecutionThread postExecutionThread,
                                                       final ISupervisedChildrenRepository supervisedChildrenRepository) {
        super(threadExecutor, postExecutionThread);
        this.supervisedChildrenRepository = supervisedChildrenRepository;
    }

    /**
     *
     * @param params
     * @return
     */
    @Override
    protected Observable<String> buildUseCaseObservable(Params params) {
        Preconditions.checkNotNull(params, "Params can not be null");
        Preconditions.checkState(!params.getKid().isEmpty(),
                "Kid can not be empty");
        return supervisedChildrenRepository
                .deleteSupervisedChildrenNoConfirmed(params.getKid());
    }


    /**
     * Params
     */
    public static class Params {

        private final String kid;

        private Params(final String kid) {
            this.kid = kid;
        }

        public String getKid() {
            return kid;
        }

        /**
         * Create
         * @param id
         */
        public static Params create(final String id) {
            Preconditions.checkNotNull(id, "Id can not be null");
            Preconditions.checkState(!id.isEmpty(), "Id can not be empty");
            return new Params(id);
        }
    }

}
