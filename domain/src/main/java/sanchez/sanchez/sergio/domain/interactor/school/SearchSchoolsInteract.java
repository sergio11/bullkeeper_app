package sanchez.sanchez.sergio.domain.interactor.school;

import com.fernandocejas.arrow.checks.Preconditions;

import java.util.List;

import io.reactivex.Observable;
import sanchez.sanchez.sergio.domain.executor.IPostExecutionThread;
import sanchez.sanchez.sergio.domain.executor.IThreadExecutor;
import sanchez.sanchez.sergio.domain.interactor.UseCase;
import sanchez.sanchez.sergio.domain.models.SchoolEntity;
import sanchez.sanchez.sergio.domain.repository.ISchoolRepository;
import sanchez.sanchez.sergio.domain.utils.ISupportVisitable;
import sanchez.sanchez.sergio.domain.utils.ISupportVisitor;

/**
 * Search Schools Interact
 */
public final class SearchSchoolsInteract extends UseCase<List<SchoolEntity>, SearchSchoolsInteract.Params> {

    private final ISchoolRepository schoolRepository;

    /**
     * @param threadExecutor
     * @param postExecutionThread
     */
    public SearchSchoolsInteract(IThreadExecutor threadExecutor,
                                 IPostExecutionThread postExecutionThread,
                                 ISchoolRepository schoolRepository) {
        super(threadExecutor, postExecutionThread);
        this.schoolRepository = schoolRepository;
    }

    /**
     *
     * @param params
     * @return
     */
    @Override
    protected Observable<List<SchoolEntity>> buildUseCaseObservable(final Params params) {
        Preconditions.checkNotNull(params, "Params can not be null");
        return schoolRepository.searchSchool(params.getQueryText());
    }

    /**
     * Params
     */
    public static class Params {

        private final String queryText;

        private Params(String queryText) {
            this.queryText = queryText;
        }

        public String getQueryText() {
            return queryText;
        }

        /**
         * Create
         * @param query
         * @return
         */
        public static Params create(final String query) {
            return new Params(query);
        }
    }

    /**
     * Search School API Error
     */
    public enum SearchSchoolApiErrors implements ISupportVisitable<SearchSchoolApiErrors.ISearchSchoolApiErrorVisitor> {

        /**
         * No School Found
         */
        NO_SCHOOLS_FOUND() {
            @Override
            public <E> void accept(ISearchSchoolApiErrorVisitor visitor, E data) {
                visitor.visitSchoolNotFound(this);
            }
        };

        /**
         * Search School API Error Visitor
         */
        public interface ISearchSchoolApiErrorVisitor extends ISupportVisitor {

            /**
             * visit School not found
             * @param searchSchoolApiErrors
             */
            void visitSchoolNotFound(final SearchSchoolApiErrors searchSchoolApiErrors);

        }
    }


}
