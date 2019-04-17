package sanchez.sanchez.sergio.domain.interactor.geofences;

import com.fernandocejas.arrow.checks.Preconditions;

import io.reactivex.Observable;
import sanchez.sanchez.sergio.domain.executor.IPostExecutionThread;
import sanchez.sanchez.sergio.domain.executor.IThreadExecutor;
import sanchez.sanchez.sergio.domain.interactor.UseCase;
import sanchez.sanchez.sergio.domain.repository.IGeofencesRepository;


/**
 * Enable Geofence
 */
public final class EnableGeofenceInteract extends UseCase<String, EnableGeofenceInteract.Params> {

    /**
     * Geofence Repository
     */
    private final IGeofencesRepository geofencesRepository;

    /**
     * @param threadExecutor
     * @param postExecutionThread
     */
    public EnableGeofenceInteract(
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
    protected Observable<String> buildUseCaseObservable(final Params params) {
        Preconditions.checkNotNull(params, "Params can not be null");

        return geofencesRepository.enableGeofence(params.getKid(), params.getGeofence());
    }

    /**
     * Params
     */
    public static class Params {

        private final String kid;
        private final String geofence;

        /**
         *
         * @param kid
         * @param geofence
         */
        private Params(final String kid, final String geofence) {
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
         *
         * @param kid
         * @param geofence
         * @return
         */
        public static Params create(
                final String kid, final String geofence
        ){
            return new Params(kid, geofence);
        }

        @Override
        public String toString() {
            return "Params{" +
                    "kid='" + kid + '\'' +
                    ", geofence='" + geofence + '\'' +
                    '}';
        }
    }
}
