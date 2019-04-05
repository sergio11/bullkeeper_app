package sanchez.sanchez.sergio.domain.interactor.guardians;

import io.reactivex.Observable;
import sanchez.sanchez.sergio.domain.executor.IPostExecutionThread;
import sanchez.sanchez.sergio.domain.executor.IThreadExecutor;
import sanchez.sanchez.sergio.domain.interactor.UseCase;
import sanchez.sanchez.sergio.domain.models.UserPreferenceEntity;
import sanchez.sanchez.sergio.domain.repository.IGuardianRepository;

/**
 * Get Preferences For Self User Interact
 **/
public final class GetPreferencesForSelfUserInteract extends UseCase<UserPreferenceEntity, Void> {

    /**
     * Guardian Repository
     */
    private final IGuardianRepository guardianRepository;

    /**
     * @param threadExecutor
     * @param postExecutionThread
     * @param guardianRepository
     */
    public GetPreferencesForSelfUserInteract(
            final IThreadExecutor threadExecutor,
            final IPostExecutionThread postExecutionThread,
            final IGuardianRepository guardianRepository) {
        super(threadExecutor, postExecutionThread);
        this.guardianRepository = guardianRepository;
    }

    /**
     *
     * @param aVoid
     * @return
     */
    @Override
    protected Observable<UserPreferenceEntity> buildUseCaseObservable(Void aVoid) {
        return guardianRepository.getUserPreference();
    }
}
