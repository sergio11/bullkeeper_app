package sanchez.sanchez.sergio.domain.interactor.parents;

import java.util.List;

import io.reactivex.Observable;
import sanchez.sanchez.sergio.domain.executor.IPostExecutionThread;
import sanchez.sanchez.sergio.domain.executor.IThreadExecutor;
import sanchez.sanchez.sergio.domain.interactor.UseCase;
import sanchez.sanchez.sergio.domain.models.SonEntity;
import sanchez.sanchez.sergio.domain.repository.IParentRepository;

/**
 * Get Self Children Interact
 */
public final class GetSelfChildrenInteract extends UseCase<List<SonEntity>, Void> {

    private final IParentRepository parentRepository;

    /**
     * Get Self Children Interact
     * @param threadExecutor
     * @param postExecutionThread
     * @param parentRepository
     */
    public GetSelfChildrenInteract(final IThreadExecutor threadExecutor, final IPostExecutionThread postExecutionThread,
                                   final IParentRepository parentRepository) {
        super(threadExecutor, postExecutionThread);
        this.parentRepository = parentRepository;
    }

    /**
     *
     * @param params
     * @return
     */
    @Override
    protected Observable<List<SonEntity>> buildUseCaseObservable(final Void params) {
        return parentRepository.getSelfChildren();
    }

}
