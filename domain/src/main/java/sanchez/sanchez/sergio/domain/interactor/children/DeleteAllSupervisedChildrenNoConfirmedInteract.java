package sanchez.sanchez.sergio.domain.interactor.children;

import io.reactivex.Observable;
import sanchez.sanchez.sergio.domain.executor.IPostExecutionThread;
import sanchez.sanchez.sergio.domain.executor.IThreadExecutor;
import sanchez.sanchez.sergio.domain.interactor.UseCase;
import sanchez.sanchez.sergio.domain.repository.ISupervisedChildrenRepository;

/**
 * Delete All Supervised Children No Confirmed
 */
public final class DeleteAllSupervisedChildrenNoConfirmedInteract
        extends UseCase<String, Void> {

    /**
     * Supervised Children Repository
     */
    private final ISupervisedChildrenRepository supervisedChildrenRepository;

    /**
     * @param threadExecutor
     * @param postExecutionThread
     */
    public DeleteAllSupervisedChildrenNoConfirmedInteract(final IThreadExecutor threadExecutor,
                                                          final IPostExecutionThread postExecutionThread,
                                                          final ISupervisedChildrenRepository supervisedChildrenRepository) {
        super(threadExecutor, postExecutionThread);
        this.supervisedChildrenRepository = supervisedChildrenRepository;
    }

    /**
     *
     * @param aVoid
     * @return
     */
    @Override
    protected Observable<String> buildUseCaseObservable(Void aVoid) {
        return supervisedChildrenRepository.deleteSupervisedChildrenNoConfirmed();
    }
}
