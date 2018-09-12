package sanchez.sanchez.sergio.domain.interactor.alerts;

import com.fernandocejas.arrow.checks.Preconditions;

import io.reactivex.Observable;
import sanchez.sanchez.sergio.domain.executor.IPostExecutionThread;
import sanchez.sanchez.sergio.domain.executor.IThreadExecutor;
import sanchez.sanchez.sergio.domain.interactor.UseCase;
import sanchez.sanchez.sergio.domain.repository.IAlertsRepository;

/**
 * Delete Alert Of Son Interact
 */
public final class DeleteAlertOfSonInteract extends UseCase<String, DeleteAlertOfSonInteract.Params> {

    private final IAlertsRepository alertsRepository;

    /**
     * @param threadExecutor
     * @param postExecutionThread
     */
    public DeleteAlertOfSonInteract(final IThreadExecutor threadExecutor,
                                    final IPostExecutionThread postExecutionThread,
                                    final IAlertsRepository alertsRepository) {
        super(threadExecutor, postExecutionThread);
        this.alertsRepository = alertsRepository;
    }

    /**
     *
     * @param params
     * @return
     */
    @Override
    protected Observable<String> buildUseCaseObservable(Params params) {
        Preconditions.checkNotNull(params, "Params can not be null");
        Preconditions.checkNotNull(params.getSon(), "Son Identity can not be null");
        Preconditions.checkNotNull(params.getAlert(), "Alert Identity can not be null");
        return alertsRepository.deleteAlertOfSon(params.getSon(), params.getAlert());
    }

    public static class Params {

        private final String son;
        private final String alert;

        public Params(String son, String alert) {
            this.son = son;
            this.alert = alert;
        }

        public String getSon() {
            return son;
        }

        public String getAlert() {
            return alert;
        }

        public static Params create(final String son, final String alert){
            return new Params(son, alert);
        }
    }
}
