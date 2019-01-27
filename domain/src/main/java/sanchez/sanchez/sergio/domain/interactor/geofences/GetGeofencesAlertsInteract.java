package sanchez.sanchez.sergio.domain.interactor.geofences;

import com.fernandocejas.arrow.checks.Preconditions;
import java.util.List;
import io.reactivex.Observable;
import sanchez.sanchez.sergio.domain.executor.IPostExecutionThread;
import sanchez.sanchez.sergio.domain.executor.IThreadExecutor;
import sanchez.sanchez.sergio.domain.interactor.UseCase;
import sanchez.sanchez.sergio.domain.models.GeofenceAlertEntity;
import sanchez.sanchez.sergio.domain.repository.IGeofencesRepository;
import sanchez.sanchez.sergio.domain.utils.ISupportVisitable;
import sanchez.sanchez.sergio.domain.utils.ISupportVisitor;

/**
 * Get Geofences Alerts Interact
 */
public final class GetGeofencesAlertsInteract
    extends UseCase<List<GeofenceAlertEntity>, GetGeofencesAlertsInteract.Params> {

    /**
     * Geofence Repository
     */
    private final IGeofencesRepository geofencesRepository;


    /**
     * @param threadExecutor
     * @param postExecutionThread
     * @param geofencesRepository
     */
    public GetGeofencesAlertsInteract(
            final IThreadExecutor threadExecutor,
            final IPostExecutionThread postExecutionThread,
            final IGeofencesRepository geofencesRepository) {
        super(threadExecutor, postExecutionThread);
        this.geofencesRepository = geofencesRepository;
    }

    /**
     *
     * @param params
     * @return
     */
    @Override
    protected Observable<List<GeofenceAlertEntity>> buildUseCaseObservable(final Params params) {
        Preconditions.checkNotNull(params, "Params can not be null");
        Preconditions.checkNotNull(params.getKid(), "Kid can not be null");
        Preconditions.checkState(!params.getKid().isEmpty(), "Kid can not be empty");
        Preconditions.checkNotNull(params.getGeofence(), "Geofence can not be null");
        Preconditions.checkState(!params.getGeofence().isEmpty(), "Geofence can not be empty");

        return geofencesRepository.getGeofenceAlerts(params.getKid(), params.getGeofence());

    }

    /**
     * Params
     */
    public static class Params {

        /**
         * Kid
         */
        private final String kid;

        /**
         * Geofence
         */
        private final String geofence;

        /**
         * Params
         * @param kid
         * @param geofence
         */
        private Params(
                final String kid,
                final String geofence) {
            this.kid = kid;
            this.geofence = geofence;
        }

        public String getKid() {
            return kid;
        }

        public String getGeofence() {
            return geofence;
        }

        /**
         * Create
         * @param kid
         * @param geofence
         * @return
         */
        public static Params create(
                final String kid,
                final String geofence) {
            return new Params(kid, geofence);
        }
    }

    /**
     * Get Geofences Alerts Api Error
     */
    public enum GetGeofencesAlertsApiErrors
            implements ISupportVisitable<GetGeofencesAlertsApiErrors.IGetGeofencesAlertsApiErrorsVisitor> {

        /**
         * No Geofences Alerts Found Exception
         */
        NO_GEOFENCES_ALERTS_FOUND_EXCEPTION(){
            @Override
            public <E> void accept(final IGetGeofencesAlertsApiErrorsVisitor visitor, E data) {
                visitor.visitNoGeofencesAlertsFound(visitor);
            }
        };

        /**
         * Get Geofences Alerts
         */
        public interface IGetGeofencesAlertsApiErrorsVisitor extends ISupportVisitor {

            /**
             * Visit No Geofences Alerts Found
             * @param apiErrorsVisitor
             */
            void visitNoGeofencesAlertsFound(final IGetGeofencesAlertsApiErrorsVisitor apiErrorsVisitor);
        }
    }
}
