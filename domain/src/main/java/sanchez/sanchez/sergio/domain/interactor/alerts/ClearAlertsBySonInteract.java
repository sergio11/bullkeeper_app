package sanchez.sanchez.sergio.domain.interactor.alerts;

import com.fernandocejas.arrow.checks.Preconditions;

import io.reactivex.Observable;
import sanchez.sanchez.sergio.domain.executor.IPostExecutionThread;
import sanchez.sanchez.sergio.domain.executor.IThreadExecutor;
import sanchez.sanchez.sergio.domain.interactor.UseCase;
import sanchez.sanchez.sergio.domain.repository.IAlertsRepository;

/**
 * Clear Alerts By Son Interact
 */
public final class ClearAlertsBySonInteract extends UseCase<String, ClearAlertsBySonInteract.Params> {

    private final IAlertsRepository alertsRepository;

    /**
     * @param threadExecutor
     * @param postExecutionThread
     */
    public ClearAlertsBySonInteract(final IThreadExecutor threadExecutor,
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
        Preconditions.checkNotNull(params.getSonIdentity(), "Son Identity can not be null");
        Preconditions.checkState(!params.getSonIdentity().isEmpty(), "Son identity can not be empty");
        return alertsRepository.clearAlertsOfSon(params.getSonIdentity());
    }

    /**
     * Params
     */
    public static class Params {

        private final String sonIdentity;

        public Params(String sonIdentity) {
            this.sonIdentity = sonIdentity;
        }

        public String getSonIdentity() {
            return sonIdentity;
        }

        /**
         * Create
         * @param sonIdentity
         * @return
         */
        public static Params create(final String sonIdentity) {
            return new Params(sonIdentity);
        }
    }
}
