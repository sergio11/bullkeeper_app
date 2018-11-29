package sanchez.sanchez.sergio.domain.interactor.children;

import io.reactivex.Observable;
import sanchez.sanchez.sergio.domain.executor.IPostExecutionThread;
import sanchez.sanchez.sergio.domain.executor.IThreadExecutor;
import sanchez.sanchez.sergio.domain.interactor.UseCase;
import sanchez.sanchez.sergio.domain.repository.ISupervisedChildrenRepository;

/**
 * Delete Supervised Children Confirmed Interact
 */
public final class DeleteSupervisedChildrenConfirmedInteract
        extends UseCase<String, DeleteSupervisedChildrenConfirmedInteract.Params> {


    private final ISupervisedChildrenRepository supervisedChildrenRepository;

    /**
     * Abstract class for a Use Case
     *
     * @param threadExecutor
     * @param postExecutionThread
     */
    public DeleteSupervisedChildrenConfirmedInteract(final IThreadExecutor threadExecutor,
                                                     final IPostExecutionThread postExecutionThread,
                                                     final ISupervisedChildrenRepository supervisedChildrenRepository) {
        super(threadExecutor, postExecutionThread);
        this.supervisedChildrenRepository = supervisedChildrenRepository;
    }

    /**
     * Build Use Case Observable
     * @param params
     * @return
     */
    @Override
    protected Observable<String> buildUseCaseObservable(Params params) {
        return null;
    }


    /**
     * Params
     */
    public static class Params {

        /**
         * Kid
         */
        private final String kid;

        public Params(final String kid) {
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
        public static Params create(final String kid){
            return new Params(kid);
        }
    }
}
