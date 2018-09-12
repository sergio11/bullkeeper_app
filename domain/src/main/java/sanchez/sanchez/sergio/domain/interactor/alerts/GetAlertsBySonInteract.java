package sanchez.sanchez.sergio.domain.interactor.alerts;

import com.fernandocejas.arrow.checks.Preconditions;

import java.util.List;

import io.reactivex.Observable;
import sanchez.sanchez.sergio.domain.executor.IPostExecutionThread;
import sanchez.sanchez.sergio.domain.executor.IThreadExecutor;
import sanchez.sanchez.sergio.domain.interactor.UseCase;
import sanchez.sanchez.sergio.domain.models.AlertEntity;
import sanchez.sanchez.sergio.domain.repository.IAlertsRepository;
import sanchez.sanchez.sergio.domain.utils.ISupportVisitable;
import sanchez.sanchez.sergio.domain.utils.ISupportVisitor;

/**
 *  Get Alerts By Son Interact
 */
public final class GetAlertsBySonInteract extends UseCase<List<AlertEntity>, GetAlertsBySonInteract.Params> {


    private final IAlertsRepository alertsRepository;

    /**
     * @param threadExecutor
     * @param postExecutionThread
     */
    public GetAlertsBySonInteract(final IThreadExecutor threadExecutor,
                                  final IPostExecutionThread postExecutionThread, final IAlertsRepository alertsRepository) {
        super(threadExecutor, postExecutionThread);
        this.alertsRepository = alertsRepository;
    }

    /**
     *
     * @param params
     * @return
     */
    @Override
    protected Observable<List<AlertEntity>> buildUseCaseObservable(Params params) {
        Preconditions.checkNotNull(params, "Params can not be null");
        Preconditions.checkState(params.getSonIdentity() != null, "Son Identity ca not be null");
        Preconditions.checkState(!params.getSonIdentity().isEmpty(), "Son Identity can not be null");
        return alertsRepository.getAlertsBySon(params.getSonIdentity());
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

    /**
     * Get Alerts By Son Api Errors
     */
    public enum GetAlertsBySonApiErrors implements ISupportVisitable<GetAlertsBySonApiErrors.IGetAlertsBySonErrorVisitor> {

        /**
         * Bad Credentials Error
         */
        NO_ALERTS_BY_SON_FOUNDED(){
            @Override
            public <E> void accept(IGetAlertsBySonErrorVisitor visitor, E data) {
                visitor.visitNoAlertsBySonFounded(this);
            }
        };


        /**
         * Get Alerts Api Error Visitor
         */
        public interface IGetAlertsBySonErrorVisitor extends ISupportVisitor {

            /**
             * Visit No Alerts By Son Founded
             * @param apiErrors
             */
            void visitNoAlertsBySonFounded(final GetAlertsBySonApiErrors apiErrors);
        }

    }
}
