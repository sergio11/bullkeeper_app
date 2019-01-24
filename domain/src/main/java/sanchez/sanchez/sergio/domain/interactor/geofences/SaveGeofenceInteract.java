package sanchez.sanchez.sergio.domain.interactor.geofences;

import io.reactivex.Observable;
import sanchez.sanchez.sergio.domain.executor.IPostExecutionThread;
import sanchez.sanchez.sergio.domain.executor.IThreadExecutor;
import sanchez.sanchez.sergio.domain.interactor.UseCase;
import sanchez.sanchez.sergio.domain.models.GeofenceEntity;
import sanchez.sanchez.sergio.domain.repository.IGeofencesRepository;

/**
 * Save Geofence
 */
public final class SaveGeofenceInteract extends UseCase<GeofenceEntity, SaveGeofenceInteract.Params> {

    /**
     * Geofence Repository
     */
    private final IGeofencesRepository geofencesRepository;

    /**
     * @param threadExecutor
     * @param postExecutionThread
     */
    public SaveGeofenceInteract(
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
    protected Observable<GeofenceEntity> buildUseCaseObservable(Params params) {
        return geofencesRepository.saveGeofences(
                params.getIdentity(), params.getName(),
                params.getLat(), params.getLog(), params.getRadius(),
                params.getAddress(), params.getType(),
                params.isEnabled(), params.getKid()
        );
    }

    /**
     * Params
     */
    public static class Params {

        private final String identity;
        private final String name;
        private final double lat;
        private final double log;
        private final float radius;
        private final String address;
        private final String type;
        private final boolean isEnabled;
        private final String kid;


        /**
         *
         * @param identity
         * @param name
         * @param lat
         * @param log
         * @param radius
         * @param address
         * @param type
         * @param isEnabled
         * @param kid
         */
        private Params(final String identity, final String name, final double lat,
                       final double log, final float radius, final String address,
                       final String type, final boolean isEnabled, final String kid) {
            this.identity = identity;
            this.name = name;
            this.lat = lat;
            this.log = log;
            this.radius = radius;
            this.address = address;
            this.type = type;
            this.isEnabled = isEnabled;
            this.kid = kid;
        }

        public String getIdentity() {
            return identity;
        }

        public String getName() {
            return name;
        }

        public double getLat() {
            return lat;
        }

        public double getLog() {
            return log;
        }

        public float getRadius() {
            return radius;
        }

        public String getAddress() {
            return address;
        }

        public String getType() {
            return type;
        }

        public boolean isEnabled() {
            return isEnabled;
        }

        public String getKid() {
            return kid;
        }

        /**
         *
         * @param identity
         * @param name
         * @param lat
         * @param log
         * @param radius
         * @param type
         * @param isEnabled
         * @param kid
         * @return
         */
        public static Params create(
                final String identity, final String name, final double lat,
                final double log, final float radius, final String address,
                final String type, final boolean isEnabled, final String kid
        ){
            return new Params(identity, name, lat, log, radius, address, type, isEnabled, kid);
        }

        @Override
        public String toString() {
            return "Params{" +
                    "identity='" + identity + '\'' +
                    ", name='" + name + '\'' +
                    ", lat=" + lat +
                    ", log=" + log +
                    ", radius=" + radius +
                    ", type='" + type + '\'' +
                    ", kid='" + kid + '\'' +
                    '}';
        }
    }
}
