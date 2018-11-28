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
 *  Get Alerts By Kid Interact
 */
public final class GetAlertsByKidInteract
            extends UseCase<List<AlertEntity>, GetAlertsByKidInteract.Params> {


    private final IAlertsRepository alertsRepository;

    /**
     * @param threadExecutor
     * @param postExecutionThread
     */
    public GetAlertsByKidInteract(final IThreadExecutor threadExecutor,
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
        Preconditions.checkState(params.getKid() != null, "Kid ca not be null");
        Preconditions.checkState(!params.getKid().isEmpty(), "Kid can not be null");
        return alertsRepository.getAlertsBySon(params.getKid());
    }

    /**
     * Params
     */
    public static class Params {

        private final String kid;

        public Params(String kid) {
            this.kid = kid;
        }

        public String getKid() {
            return kid;
        }

        /**
         * Create
         * @param kid
         * @return
         */
        public static Params create(final String kid) {
            return new Params(kid);
        }
    }

    /**
     * Get Alerts By Kid Api Errors
     */
    public enum GetAlertsByKidApiErrors
            implements ISupportVisitable<GetAlertsByKidApiErrors.IGetAlertsByKidErrorVisitor> {

        /**
         * Bad Credentials Error
         */
        NO_ALERTS_BY_KID_FOUNDED(){
            @Override
            public <E> void accept(IGetAlertsByKidErrorVisitor visitor, E data) {
                visitor.visitNoAlertsByKidFound(this);
            }
        };


        /**
         * Get Alerts Api Error Visitor
         */
        public interface IGetAlertsByKidErrorVisitor extends ISupportVisitor {

            /**
             * Visit No Alerts By Kid Founded
             * @param apiErrors
             */
            void visitNoAlertsByKidFound(final GetAlertsByKidApiErrors apiErrors);
        }

    }
}
