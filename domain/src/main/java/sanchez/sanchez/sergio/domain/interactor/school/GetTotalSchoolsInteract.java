package sanchez.sanchez.sergio.domain.interactor.school;

import io.reactivex.Observable;
import sanchez.sanchez.sergio.domain.executor.IPostExecutionThread;
import sanchez.sanchez.sergio.domain.executor.IThreadExecutor;
import sanchez.sanchez.sergio.domain.interactor.UseCase;
import sanchez.sanchez.sergio.domain.repository.ISchoolRepository;

/**
 * Get Total Schools
 */
public final class GetTotalSchoolsInteract extends UseCase<Integer, Void> {

    private final ISchoolRepository schoolRepository;

    /**
     * @param threadExecutor
     * @param postExecutionThread
     * @param schoolRepository
     */
    public GetTotalSchoolsInteract(final IThreadExecutor threadExecutor,
                                   final IPostExecutionThread postExecutionThread, final ISchoolRepository schoolRepository) {
        super(threadExecutor, postExecutionThread);
        this.schoolRepository = schoolRepository;
    }

    /**
     * Build Use Case Observable
     * @param aVoid
     * @return
     */
    @Override
    protected Observable<Integer> buildUseCaseObservable(Void aVoid) {
        return schoolRepository.getTotalSchools();
    }
}
