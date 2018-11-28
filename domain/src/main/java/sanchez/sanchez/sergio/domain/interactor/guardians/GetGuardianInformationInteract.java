package sanchez.sanchez.sergio.domain.interactor.guardians;

import javax.inject.Inject;

import io.reactivex.Observable;
import sanchez.sanchez.sergio.domain.executor.IPostExecutionThread;
import sanchez.sanchez.sergio.domain.executor.IThreadExecutor;
import sanchez.sanchez.sergio.domain.interactor.UseCase;
import sanchez.sanchez.sergio.domain.models.GuardianEntity;
import sanchez.sanchez.sergio.domain.repository.IGuardianRepository;

/**
 * Get Self Children Interact
 */
public final class GetGuardianInformationInteract extends UseCase<GuardianEntity, Void> {

    private final IGuardianRepository parentRepository;

    /**
     * Get Guardian Information Interact
     * @param threadExecutor
     * @param postExecutionThread
     * @param parentRepository
     */
    @Inject
    public GetGuardianInformationInteract(final IThreadExecutor threadExecutor, final IPostExecutionThread postExecutionThread,
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
    protected Observable<GuardianEntity> buildUseCaseObservable(final Void params) {
        return parentRepository.getGuardianSelfInformation();
    }


}
