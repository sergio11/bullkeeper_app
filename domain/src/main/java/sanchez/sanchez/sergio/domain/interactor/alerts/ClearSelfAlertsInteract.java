package sanchez.sanchez.sergio.domain.interactor.alerts;

import io.reactivex.Observable;
import sanchez.sanchez.sergio.domain.executor.IPostExecutionThread;
import sanchez.sanchez.sergio.domain.executor.IThreadExecutor;
import sanchez.sanchez.sergio.domain.interactor.UseCase;
import sanchez.sanchez.sergio.domain.repository.IAlertsRepository;

/**
 * Clear Self Alerts Interact
 */
public final class ClearSelfAlertsInteract extends UseCase<String, Void> {

    private final IAlertsRepository alertsRepository;

    /**
     * @param threadExecutor
     * @param postExecutionThread
     */
    public ClearSelfAlertsInteract(final IThreadExecutor threadExecutor,
                                   final IPostExecutionThread postExecutionThread,
                                   final IAlertsRepository alertsRepository) {
        super(threadExecutor, postExecutionThread);
        this.alertsRepository = alertsRepository;
    }

    @Override
    protected Observable<String> buildUseCaseObservable(Void aVoid) {
        return alertsRepository.clearSelfAlerts();
    }

}
