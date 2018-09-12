package sanchez.sanchez.sergio.domain.interactor.children;

import com.fernandocejas.arrow.checks.Preconditions;

import io.reactivex.Observable;
import sanchez.sanchez.sergio.domain.executor.IPostExecutionThread;
import sanchez.sanchez.sergio.domain.executor.IThreadExecutor;
import sanchez.sanchez.sergio.domain.interactor.UseCase;
import sanchez.sanchez.sergio.domain.models.SonEntity;
import sanchez.sanchez.sergio.domain.repository.IChildrenRepository;

/**
 * Get Son By Id Interact
 */
public class GetSonByIdInteract extends UseCase<SonEntity, GetSonByIdInteract.Params> {

    /**
     * Children Repository
     */
    private final IChildrenRepository childrenRepository;


    /**
     * @param threadExecutor
     * @param postExecutionThread
     */
    public GetSonByIdInteract(final IThreadExecutor threadExecutor,
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
    protected Observable<SonEntity> buildUseCaseObservable(Params params) {
        Preconditions.checkNotNull(params, "Params can not be null");
        Preconditions.checkNotNull(params.getSonId(), "Son Id can not be null");
        Preconditions.checkState(!params.sonId.isEmpty(), "Son Id can not be empty");
        return childrenRepository.getSonById(params.sonId);
    }




    /**
     * Params
     */
    public static class Params {

        private final String sonId;

        public Params(String sonId) {
            this.sonId = sonId;
        }

        public String getSonId() {
            return sonId;
        }

        /**
         * Create
         * @param sonId
         * @return
         */
        public static Params create(final String sonId) {
            return new Params(sonId);
        }
    }
}
