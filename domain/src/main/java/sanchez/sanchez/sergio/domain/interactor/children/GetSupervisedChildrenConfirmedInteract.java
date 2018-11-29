package sanchez.sanchez.sergio.domain.interactor.children;

import java.util.List;

import io.reactivex.Observable;
import sanchez.sanchez.sergio.domain.executor.IPostExecutionThread;
import sanchez.sanchez.sergio.domain.executor.IThreadExecutor;
import sanchez.sanchez.sergio.domain.interactor.UseCase;
import sanchez.sanchez.sergio.domain.models.SupervisedChildrenEntity;
import sanchez.sanchez.sergio.domain.repository.ISupervisedChildrenRepository;

/**
 * Get Supervised Children Confirmed Interact
 */
public class GetSupervisedChildrenConfirmedInteract
        extends UseCase<List<SupervisedChildrenEntity>, Void> {


    private final ISupervisedChildrenRepository supervisedChildrenRepository;


    /**
     * Abstract class for a Use Case
     *
     * @param threadExecutor
     * @param postExecutionThread
     */
    public GetSupervisedChildrenConfirmedInteract(final IThreadExecutor threadExecutor,
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
    protected Observable<List<SupervisedChildrenEntity>> buildUseCaseObservable(Void aVoid) {
        return null;
    }
}
