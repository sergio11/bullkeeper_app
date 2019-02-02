package sanchez.sanchez.sergio.domain.interactor.guardians;


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
public final class GetSelfChildrenInteract extends UseCase<ChildrenOfSelfGuardianEntity, GetSelfChildrenInteract.Params> {

    /**
     * Parent Repository
     */
    private final IGuardianRepository guardianRepository;

    /**
     * Get Self Children Interact
     * @param threadExecutor
     * @param postExecutionThread
     * @param guardianRepository
     */
    @Inject
    public GetSelfChildrenInteract(final IThreadExecutor threadExecutor, final IPostExecutionThread postExecutionThread,
                                   final IGuardianRepository guardianRepository) {
        super(threadExecutor, postExecutionThread);
        this.guardianRepository = guardianRepository;
    }

    /**
     * @param params
     * @return
     */
    @Override
    protected Observable<ChildrenOfSelfGuardianEntity> buildUseCaseObservable(Params params) {
        return params != null && params.getQueryText() != null &&
                !params.getQueryText().isEmpty() ?
                    guardianRepository.getSelfChildren(params.getQueryText()) :
                    guardianRepository.getSelfChildren();
    }


    /**
     * Params
     */
    public static class Params {

        private final String queryText;

        /**
         *
         * @param queryText
         */
        private Params(String queryText) {
            this.queryText = queryText;
        }

        public String getQueryText() {
            return queryText;
        }

        /**
         * Create
         * @param queryText
         * @return
         */
        public static Params create(final String queryText) {
            return new Params(queryText);
        }

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
