package sanchez.sanchez.sergio.domain.interactor.parents;

import javax.inject.Inject;

import io.reactivex.Observable;
import sanchez.sanchez.sergio.domain.executor.IPostExecutionThread;
import sanchez.sanchez.sergio.domain.executor.IThreadExecutor;
import sanchez.sanchez.sergio.domain.interactor.UseCase;
import sanchez.sanchez.sergio.domain.models.ParentEntity;
import sanchez.sanchez.sergio.domain.repository.IParentRepository;
import sanchez.sanchez.sergio.domain.utils.ISupportVisitable;
import sanchez.sanchez.sergio.domain.utils.ISupportVisitor;

/**
 * Get Self Children Interact
 */
public final class GetParentInformationInteract extends UseCase<ParentEntity, Void> {

    private final IParentRepository parentRepository;

    /**
     * Get Self Children Interact
     * @param threadExecutor
     * @param postExecutionThread
     * @param parentRepository
     */
    @Inject
    public GetParentInformationInteract(final IThreadExecutor threadExecutor, final IPostExecutionThread postExecutionThread,
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
    protected Observable<ParentEntity> buildUseCaseObservable(final Void params) {
        return parentRepository.getParentSelfInformation();
    }

    /**
     * Get Parent Information Api Errors
     */
    public enum GetParentInformationApiErrors implements ISupportVisitable<GetParentInformationApiErrors.IGetSelfInformationApiErrorVisitor> {

        /**
         * No Children Found For Self Parent
         */
        NO_CHILDREN_FOUND_FOR_SELF_PARENT() {
            @Override
            public <E> void accept(IGetSelfInformationApiErrorVisitor visitor, E data) {
                visitor.visitNoChildrenFoundForSelfParent(this);
            }
        };

        /**
         * Get Self Information API Error Visitor
         */
        public interface IGetSelfInformationApiErrorVisitor extends ISupportVisitor {
            /**
             * Visit No Children Found For Self Parent
             *
             * @param error
             */
            void visitNoChildrenFoundForSelfParent(final GetParentInformationApiErrors error);

        }
    }

}
