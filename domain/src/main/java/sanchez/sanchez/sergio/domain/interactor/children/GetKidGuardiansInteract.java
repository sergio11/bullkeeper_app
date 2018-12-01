package sanchez.sanchez.sergio.domain.interactor.children;

import com.fernandocejas.arrow.checks.Preconditions;
import java.util.List;

import io.reactivex.Observable;
import sanchez.sanchez.sergio.domain.executor.IPostExecutionThread;
import sanchez.sanchez.sergio.domain.executor.IThreadExecutor;
import sanchez.sanchez.sergio.domain.interactor.UseCase;
import sanchez.sanchez.sergio.domain.models.KidGuardianEntity;
import sanchez.sanchez.sergio.domain.repository.IChildrenRepository;
import sanchez.sanchez.sergio.domain.utils.ISupportVisitable;
import sanchez.sanchez.sergio.domain.utils.ISupportVisitor;

/**
 * Get Kid Guardian Interact
 */
public final class GetKidGuardiansInteract
        extends UseCase<List<KidGuardianEntity>, GetKidGuardiansInteract.Params> {

    /**
     * Children Repository
     */
    private final IChildrenRepository childrenRepository;



    /**
     * @param threadExecutor
     * @param postExecutionThread
     * @param childrenRepository
     */
    public GetKidGuardiansInteract(final IThreadExecutor threadExecutor,
                                   final IPostExecutionThread postExecutionThread,
                                   final IChildrenRepository childrenRepository) {
        super(threadExecutor, postExecutionThread);

        this.childrenRepository = childrenRepository;
    }

    /**
     *
     * @param params
     * @return
     */
    @Override
    protected Observable<List<KidGuardianEntity>> buildUseCaseObservable(Params params) {
        Preconditions.checkNotNull(params, "Params can not be null");
        Preconditions.checkNotNull(params.getKid(), "Kid can not be null");
        Preconditions.checkState(!params.getKid().isEmpty(), "Kid can not be empty");
        return childrenRepository.getGuardians(params.getKid());
    }

    /**
     * Params
     */
    public static class Params {

        /**
         * Kid
         */
        private final String kid;

        /**
         *
         * @param kid
         */
        private Params(String kid) {
            this.kid = kid;
        }

        public String getKid() {
            return kid;
        }

        /**
         *
         * @param kid
         * @return
         */
        public static Params create(final String kid) {
            Preconditions.checkNotNull(kid, "Kid can not be null");
            Preconditions.checkState(!kid.isEmpty(), "Kid can not be empty");
            return new Params(kid);
        }
    }


    /**
     * Get Kid Guardians API Errors
     */
    public enum GetKidGuardiansApiErrors
            implements ISupportVisitable<GetKidGuardiansApiErrors
                            .IGetKidGuardiansApiErrorsVisitor> {

        /**
         * No Kid Guardian Found
         */
        NO_KID_GUARDIAN_FOUND() {
            @Override
            public <E> void accept(IGetKidGuardiansApiErrorsVisitor visitor, E data) {
                visitor.visitKidGuardianFound(this);
            }
        };

        /**
         * Get Kid Guardians API Errors Visitor
         */
        public interface IGetKidGuardiansApiErrorsVisitor extends ISupportVisitor {

            /**
             * Visit No Kid Guardian Found
             * @param error
             */
            void visitKidGuardianFound(
                    final GetKidGuardiansApiErrors error);

        }
    }
}
