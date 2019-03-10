package sanchez.sanchez.sergio.domain.interactor.guardians;

import io.reactivex.Observable;
import sanchez.sanchez.sergio.domain.executor.IPostExecutionThread;
import sanchez.sanchez.sergio.domain.executor.IThreadExecutor;
import sanchez.sanchez.sergio.domain.interactor.UseCase;
import sanchez.sanchez.sergio.domain.repository.IGuardianRepository;

/**
 * Delete Account Interact
 */
public final class DeleteAccountInteract extends UseCase<String, Void> {

    /**
     * Guardian Repository
     */
    private final IGuardianRepository guardianRepository;

    /**
     * Abstract class for a Use Case
     *
     * @param threadExecutor
     * @param postExecutionThread
     */
    public DeleteAccountInteract(IThreadExecutor threadExecutor,
                                 IPostExecutionThread postExecutionThread,
                                 IGuardianRepository guardianRepository) {
        super(threadExecutor, postExecutionThread);
        this.guardianRepository = guardianRepository;
    }

    /**
     *
     * @param params
     * @return
     */
    @Override
    protected Observable<String> buildUseCaseObservable(Void params) {
        return guardianRepository.deleteSelfAccount();
    }


}
