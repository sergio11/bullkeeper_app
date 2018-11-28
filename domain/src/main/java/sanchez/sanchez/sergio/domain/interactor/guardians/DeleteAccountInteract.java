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

    private final IGuardianRepository parentRepository;

    /**
     * Abstract class for a Use Case
     *
     * @param threadExecutor
     * @param postExecutionThread
     */
    public DeleteAccountInteract(IThreadExecutor threadExecutor,
                                 IPostExecutionThread postExecutionThread,
                                 IGuardianRepository parentRepository) {
        super(threadExecutor, postExecutionThread);
        this.parentRepository = parentRepository;
    }

    /**
     *
     * @param params
     * @return
     */
    @Override
    protected Observable<String> buildUseCaseObservable(Void params) {
        return parentRepository.deleteSelfAccount();
    }


}
