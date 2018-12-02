package sanchez.sanchez.sergio.domain.interactor.children;

import com.fernandocejas.arrow.checks.Preconditions;

import io.reactivex.Observable;
import sanchez.sanchez.sergio.domain.executor.IPostExecutionThread;
import sanchez.sanchez.sergio.domain.executor.IThreadExecutor;
import sanchez.sanchez.sergio.domain.interactor.UseCase;
import sanchez.sanchez.sergio.domain.models.SupervisedChildrenEntity;
import sanchez.sanchez.sergio.domain.repository.ISupervisedChildrenRepository;

/**
 * Get Supervised Children No Confirmed Detail Interact
 */
public final class GetSupervisedChildrenNoConfirmedDetailInteract
    extends UseCase<SupervisedChildrenEntity, GetSupervisedChildrenNoConfirmedDetailInteract.Params> {

    /**
     * Suoervised Children Repository
     */
    private final ISupervisedChildrenRepository supervisedChildrenRepository;

    /**
     * Abstract class for a Use Case
     *
     * @param threadExecutor
     * @param postExecutionThread
     */
    public GetSupervisedChildrenNoConfirmedDetailInteract(final IThreadExecutor threadExecutor,
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
    protected Observable<SupervisedChildrenEntity> buildUseCaseObservable(Params params) {
        Preconditions.checkNotNull(params, "Params can no be null");
        Preconditions.checkNotNull(params.getKid(), "Kid can not be null");
        Preconditions.checkState(!params.getKid().isEmpty(), "Kid id can not be empty");

        return supervisedChildrenRepository.getSupervisedChildrenNoConfirmedDetail(params.getKid());

    }

    /**
     * Params
     */
    public static class Params {

        /**
         * Kid
         */
        private final String kid;

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
        public static Params create(final String kid){
            return new Params(kid);
        }
    }

}
