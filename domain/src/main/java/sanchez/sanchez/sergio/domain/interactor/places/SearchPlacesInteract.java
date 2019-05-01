package sanchez.sanchez.sergio.domain.interactor.places;

import com.fernandocejas.arrow.checks.Preconditions;
import java.util.List;
import java.util.Locale;
import io.reactivex.Observable;
import sanchez.sanchez.sergio.domain.executor.IPostExecutionThread;
import sanchez.sanchez.sergio.domain.executor.IThreadExecutor;
import sanchez.sanchez.sergio.domain.interactor.UseCase;
import sanchez.sanchez.sergio.domain.models.PlaceSuggestionEntity;
import sanchez.sanchez.sergio.domain.repository.IPlacesRepository;

/**
 * Search Places Interact
 **/
public final class SearchPlacesInteract extends UseCase<List<PlaceSuggestionEntity>, SearchPlacesInteract.Params> {

    /**
     * Places Repository
     */
    private final IPlacesRepository placesRepository;


    /**
     * @param threadExecutor
     * @param postExecutionThread
     * @param placesRepository
     */
    public SearchPlacesInteract(IThreadExecutor threadExecutor,
                                IPostExecutionThread postExecutionThread,
                                final IPlacesRepository placesRepository) {
        super(threadExecutor, postExecutionThread);
        this.placesRepository = placesRepository;
    }

    /**
     *
     * @param params
     * @return
     */
    @Override
    protected Observable<List<PlaceSuggestionEntity>> buildUseCaseObservable(final Params params) {
        Preconditions.checkNotNull(params, "Param can not be null");
        Preconditions.checkNotNull(params.getQuery(), "Query can not be null");
        Preconditions.checkState(!params.getQuery().isEmpty(), "Query can not be null");

        return params.getRadius() == null ? placesRepository.autosuggestPlacesAt(String.format(Locale.US, "%f,%f",
                params.getLatitude(), params.getLongitude()), params.getQuery()) :
                placesRepository.autosuggestPlacesIn(String.format(Locale.US, "%f,%f;r=%s",
                        params.getLatitude(), params.getLongitude(), params.getRadius())
                        , params.getQuery());
    }

    /**
     * Params
     */
    public static class Params {

        private final double latitude;
        private final double longitude;
        private final String query;
        private final String radiusInMeters;

        /**
         *
         * @param latitude
         * @param longitude
         * @param query
         * @param radiusInMeters
         */
        private Params(double latitude, double longitude, String query, String radiusInMeters) {
            this.latitude = latitude;
            this.longitude = longitude;
            this.query = query;
            this.radiusInMeters = radiusInMeters;
        }

        public double getLatitude() {
            return latitude;
        }

        public double getLongitude() {
            return longitude;
        }

        public String getQuery() {
            return query;
        }

        public String getRadius() {
            return radiusInMeters;
        }

        /**
         *
         * @param latitude
         * @param longitude
         * @param query
         * @return
         */
        public static Params create(double latitude, double longitude, String query, String radiusInMeters){
            return new Params(latitude, longitude, query, radiusInMeters);
        }

        /**
         *
         * @param latitude
         * @param longitude
         * @param query
         * @return
         */
        public static Params create(double latitude, double longitude, String query){
            return create(latitude, longitude, query, null);
        }

        @Override
        public String toString() {
            return "Params{" +
                    "latitude=" + latitude +
                    ", longitude=" + longitude +
                    ", query='" + query + '\'' +
                    ", radius=" + radiusInMeters +
                    '}';
        }
    }
}
