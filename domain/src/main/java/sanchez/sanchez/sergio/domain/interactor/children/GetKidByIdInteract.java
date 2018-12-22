package sanchez.sanchez.sergio.domain.interactor.children;

import com.fernandocejas.arrow.checks.Preconditions;

import io.reactivex.Observable;
import sanchez.sanchez.sergio.domain.executor.IPostExecutionThread;
import sanchez.sanchez.sergio.domain.executor.IThreadExecutor;
import sanchez.sanchez.sergio.domain.interactor.UseCase;
import sanchez.sanchez.sergio.domain.models.KidEntity;
import sanchez.sanchez.sergio.domain.repository.IChildrenRepository;

/**
 * Get Son By Id Interact
 */
public class GetKidByIdInteract extends UseCase<KidEntity, GetKidByIdInteract.Params> {

    /**
     * Children Repository
     */
    private final IChildrenRepository childrenRepository;


    /**
     * @param threadExecutor
     * @param postExecutionThread
     */
    public GetKidByIdInteract(final IThreadExecutor threadExecutor,
                              final IPostExecutionThread postExecutionThread,
                              final IChildrenRepository childrenRepository) {
        super(threadExecutor, postExecutionThread);
        this.childrenRepository = childrenRepository;
    }

    /**
     * Build Use Case Observable
     * @param params
     * @return
     */
    @Override
    protected Observable<KidEntity> buildUseCaseObservable(Params params) {
        Preconditions.checkNotNull(params, "Params can not be null");
        Preconditions.checkNotNull(params.getKid(), "Kid can not be null");
        Preconditions.checkState(!params.kid.isEmpty(), "Kid can not be empty");
        return childrenRepository.getKidById(params.kid);
    }

    /**
     * Params
     */
    public static class Params {

        private final String kid;

        public Params(String kid) {
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
}
