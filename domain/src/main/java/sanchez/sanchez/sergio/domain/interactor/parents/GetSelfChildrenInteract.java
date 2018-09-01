package sanchez.sanchez.sergio.domain.interactor.parents;

import java.util.List;

import javax.inject.Inject;

import io.reactivex.Observable;
import sanchez.sanchez.sergio.domain.executor.IPostExecutionThread;
import sanchez.sanchez.sergio.domain.executor.IThreadExecutor;
import sanchez.sanchez.sergio.domain.interactor.UseCase;
import sanchez.sanchez.sergio.domain.models.SonEntity;
import sanchez.sanchez.sergio.domain.repository.IParentRepository;
import sanchez.sanchez.sergio.domain.utils.ISupportVisitable;
import sanchez.sanchez.sergio.domain.utils.ISupportVisitor;

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
    @Inject
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



    public enum GetChildrenApiErrors implements ISupportVisitable<GetChildrenApiErrors.IGetChildrenApiErrorVisitor> {

        /**
         * No Children Found For Self Parent
         */
        NO_CHILDREN_FOUND_FOR_SELF_PARENT() {
            @Override
            public <E> void accept(IGetChildrenApiErrorVisitor visitor, E data) {
                visitor.visitNoChildrenFoundForSelfParent(this);
            }
        };

        /**
         * Get Children Api Error Visitor
         */
        public interface IGetChildrenApiErrorVisitor extends ISupportVisitor {
            /**
             * Visit No Children Found For Self Parent
             *
             * @param error
             */
            void visitNoChildrenFoundForSelfParent(final GetChildrenApiErrors error);

        }
    }

}
