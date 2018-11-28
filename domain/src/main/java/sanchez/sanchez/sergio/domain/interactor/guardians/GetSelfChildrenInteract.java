package sanchez.sanchez.sergio.domain.interactor.guardians;

import java.util.List;

import javax.inject.Inject;

import io.reactivex.Observable;
import sanchez.sanchez.sergio.domain.executor.IPostExecutionThread;
import sanchez.sanchez.sergio.domain.executor.IThreadExecutor;
import sanchez.sanchez.sergio.domain.interactor.UseCase;
import sanchez.sanchez.sergio.domain.models.ChildrenOfSelfGuardianEntity;
import sanchez.sanchez.sergio.domain.repository.IGuardianRepository;
import sanchez.sanchez.sergio.domain.utils.ISupportVisitable;
import sanchez.sanchez.sergio.domain.utils.ISupportVisitor;

/**
 * Get Self Children Interact
 */
public final class GetSelfChildrenInteract extends UseCase<ChildrenOfSelfGuardianEntity, Void> {

    private final IGuardianRepository parentRepository;

    /**
     * Get Self Children Interact
     * @param threadExecutor
     * @param postExecutionThread
     * @param parentRepository
     */
    @Inject
    public GetSelfChildrenInteract(final IThreadExecutor threadExecutor, final IPostExecutionThread postExecutionThread,
                                   final IGuardianRepository parentRepository) {
        super(threadExecutor, postExecutionThread);
        this.parentRepository = parentRepository;
    }

    /**
     *
     * @param params
     * @return
     */
    @Override
    protected Observable<ChildrenOfSelfGuardianEntity> buildUseCaseObservable(final Void params) {
        return parentRepository.getSelfChildren();
    }


    /**
     *
     */
    public enum GetChildrenApiErrors implements ISupportVisitable<GetChildrenApiErrors.IGetChildrenApiErrorVisitor> {

        /**
         * No Children Found For Self Guardian
         */
        NO_CHILDREN_FOUND_FOR_SELF_GUARDIAN() {
            @Override
            public <E> void accept(IGetChildrenApiErrorVisitor visitor, E data) {
                visitor.visitNoChildrenFoundForSelfGuardian(this);
            }
        };

        /**
         * Get Children Api Error Visitor
         */
        public interface IGetChildrenApiErrorVisitor extends ISupportVisitor {
            /**
             * Visit No Children Found For Self Guardian
             *
             * @param error
             */
            void visitNoChildrenFoundForSelfGuardian(final GetChildrenApiErrors error);

        }
    }

}
