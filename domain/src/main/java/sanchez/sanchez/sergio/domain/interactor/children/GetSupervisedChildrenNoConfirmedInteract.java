package sanchez.sanchez.sergio.domain.interactor.children;

import java.util.List;

import io.reactivex.Observable;
import sanchez.sanchez.sergio.domain.executor.IPostExecutionThread;
import sanchez.sanchez.sergio.domain.executor.IThreadExecutor;
import sanchez.sanchez.sergio.domain.interactor.UseCase;
import sanchez.sanchez.sergio.domain.models.SupervisedChildrenEntity;
import sanchez.sanchez.sergio.domain.repository.ISupervisedChildrenRepository;
import sanchez.sanchez.sergio.domain.utils.ISupportVisitable;
import sanchez.sanchez.sergio.domain.utils.ISupportVisitor;

/**
 * Get Supervised Children No Confirmed Interact
 */
public final class GetSupervisedChildrenNoConfirmedInteract
        extends UseCase<List<SupervisedChildrenEntity>, Void> {

    /**
     * Supervised Children Repository
     */
    private final ISupervisedChildrenRepository supervisedChildrenRepository;

    /**
     * Abstract class for a Use Case
     *
     * @param threadExecutor
     * @param postExecutionThread
     */
    public GetSupervisedChildrenNoConfirmedInteract(final IThreadExecutor threadExecutor,
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
        return supervisedChildrenRepository.getSupervisedChildrenNoConfirmed();
    }

    /**
     * Get Supervised Children No Confirmed Api Errors
     */
    public enum GetSupervisedChildrenNoConfirmedApiErrors
            implements ISupportVisitable<GetSupervisedChildrenNoConfirmedApiErrors
                    .IGetSupervisedChildrenNoConfirmedApiErrorVisitor> {

        /**
         * No Supervised Children No Confirmed Found
         */
        NO_SUPERVISED_CHILDREN_NO_CONFIRMED_FOUND() {
            @Override
            public <E> void accept(IGetSupervisedChildrenNoConfirmedApiErrorVisitor visitor, E data) {
                visitor.visitNoSupervisedChildrenNoConfirmedFound(this);
            }
        };

        /**
         * Get Supervised Children No Confirmed Api Error Visitor
         */
        public interface IGetSupervisedChildrenNoConfirmedApiErrorVisitor extends ISupportVisitor {

            /**
             * Visit No Supervised Children No Confirmed Found
             * @param error
             */
            void visitNoSupervisedChildrenNoConfirmedFound(
                    final GetSupervisedChildrenNoConfirmedApiErrors error);

        }
    }
}
