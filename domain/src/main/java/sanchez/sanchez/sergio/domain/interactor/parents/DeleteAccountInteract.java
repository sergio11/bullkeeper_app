package sanchez.sanchez.sergio.domain.interactor.parents;

import io.reactivex.Observable;
import sanchez.sanchez.sergio.domain.executor.IPostExecutionThread;
import sanchez.sanchez.sergio.domain.executor.IThreadExecutor;
import sanchez.sanchez.sergio.domain.interactor.UseCase;
import sanchez.sanchez.sergio.domain.repository.IParentRepository;
import sanchez.sanchez.sergio.domain.utils.ISupportVisitable;
import sanchez.sanchez.sergio.domain.utils.ISupportVisitor;

/**
 * Delete Account Interact
 */
public final class DeleteAccountInteract extends UseCase<String, Void> {

    private final IParentRepository parentRepository;

    /**
     * Abstract class for a Use Case
     *
     * @param threadExecutor
     * @param postExecutionThread
     */
    public DeleteAccountInteract(IThreadExecutor threadExecutor,
                                 IPostExecutionThread postExecutionThread,
                                 IParentRepository parentRepository) {
        super(threadExecutor, postExecutionThread);
        this.parentRepository = parentRepository;
    }

    /**
     *
     * @param params
     * @return
     */
    @Override
    protected Observable<String> buildUseCaseObservable(Void params) {
        return parentRepository.deleteSelfAccount();
    }


    /**
     * Delete Account Api Errors
     */
    public enum DeleteAccountApiErrors implements ISupportVisitable<DeleteAccountApiErrors.IDeleteAccountApiErrorVisitor> {

        /**
         * No Children Found For Self Parent
         */
        NO_CHILDREN_FOUND_FOR_SELF_PARENT() {
            @Override
            public <E> void accept(IDeleteAccountApiErrorVisitor visitor, E data) {
                visitor.visitNoChildrenFoundForSelfParent(this);
            }
        };

        /**
         * Delete Account API Error Visitor
         */
        public interface IDeleteAccountApiErrorVisitor extends ISupportVisitor {
            /**
             * Visit No Children Found For Self Parent
             *
             * @param error
             */
            void visitNoChildrenFoundForSelfParent(final DeleteAccountApiErrors error);

        }
    }


}
