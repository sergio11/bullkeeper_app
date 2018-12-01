package sanchez.sanchez.sergio.domain.interactor.guardians;

import com.fernandocejas.arrow.checks.Preconditions;

import java.util.List;

import io.reactivex.Observable;
import sanchez.sanchez.sergio.domain.executor.IPostExecutionThread;
import sanchez.sanchez.sergio.domain.executor.IThreadExecutor;
import sanchez.sanchez.sergio.domain.interactor.UseCase;
import sanchez.sanchez.sergio.domain.models.GuardianEntity;
import sanchez.sanchez.sergio.domain.repository.IGuardianRepository;
import sanchez.sanchez.sergio.domain.utils.ISupportVisitable;
import sanchez.sanchez.sergio.domain.utils.ISupportVisitor;

/**
 * Search Guardian Interact
 */
public final class SearchGuardiansInteract extends UseCase<List<GuardianEntity>, SearchGuardiansInteract.Params> {

    /**
     * Guardian Repository
     */
    private final IGuardianRepository guardianRepository;

    /**
     * Abstract class for a Use Case
     *
     * @param threadExecutor
     * @param postExecutionThread
     * @param guardianRepository
     */
    public SearchGuardiansInteract(final IThreadExecutor threadExecutor,
                                   final IPostExecutionThread postExecutionThread,
                                   final IGuardianRepository guardianRepository) {
        super(threadExecutor, postExecutionThread);
        this.guardianRepository = guardianRepository;
    }

    /**
     *
     * @param params
     * @return
     */
    @Override
    protected Observable<List<GuardianEntity>> buildUseCaseObservable(Params params) {
        Preconditions.checkNotNull(params, "Params can not be null");
        Preconditions.checkNotNull(params.getText(), "Text can not be null");
        Preconditions.checkState(!params.getText().isEmpty(), "Text can not be empty");
        // Search Guardians
        return guardianRepository.search(params.getText());
    }

    /**
     * Params
     */
    public static class Params {

        /**
         * Text
         */
        private final String text;

        private Params(String text) {
            this.text = text;
        }

        public String getText() {
            return text;
        }

        /**
         * Create
         * @param text
         * @return
         */
        public static Params create(final String text) {
            return new Params(text);
        }
    }


    /**
     * Search Guardians API Error
     */
    public enum SearchGuardiansApiErrors
            implements ISupportVisitable<SearchGuardiansApiErrors.ISearchGuardiansApiErrorVisitor> {

        /**
         * Guardians Not Found
         */
        GUARDIANS_NOT_FOUND() {
            @Override
            public <E> void accept(ISearchGuardiansApiErrorVisitor visitor, E data) {
                visitor.visitNoGuardiansFound(this);
            }
        };

        /**
         * Search Guardians Api Error Visitor
         */
        public interface ISearchGuardiansApiErrorVisitor extends ISupportVisitor {

            /**
             * Visit No Guardians Found
             * @param error
             */
            void visitNoGuardiansFound(final SearchGuardiansApiErrors error);

        }
    }
}
