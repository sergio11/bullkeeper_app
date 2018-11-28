package sanchez.sanchez.sergio.domain.interactor.alerts;

import com.fernandocejas.arrow.checks.Preconditions;
import io.reactivex.Observable;
import sanchez.sanchez.sergio.domain.executor.IPostExecutionThread;
import sanchez.sanchez.sergio.domain.executor.IThreadExecutor;
import sanchez.sanchez.sergio.domain.interactor.UseCase;
import sanchez.sanchez.sergio.domain.repository.IAlertsRepository;

/**
 * Delete Alert Of Kid Interact
 */
public final class DeleteAlertOfKidInteract
        extends UseCase<String, DeleteAlertOfKidInteract.Params> {

    private final IAlertsRepository alertsRepository;

    /**
     * @param threadExecutor
     * @param postExecutionThread
     */
    public DeleteAlertOfKidInteract(final IThreadExecutor threadExecutor,
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
        Preconditions.checkNotNull(params.getKid(), "Kid can not be null");
        Preconditions.checkNotNull(params.getAlert(), "Alert Identity can not be null");
        return alertsRepository.deleteAlertOfSon(params.getKid(), params.getAlert());
    }

    /**
     * Params
     */
    public static class Params {

        private final String kid;
        private final String alert;

        public Params(String kid, String alert) {
            this.kid = kid;
            this.alert = alert;
        }

        public String getKid() {
            return kid;
        }

        public String getAlert() {
            return alert;
        }

        public static Params create(final String son, final String alert){
            return new Params(son, alert);
        }
    }
}
