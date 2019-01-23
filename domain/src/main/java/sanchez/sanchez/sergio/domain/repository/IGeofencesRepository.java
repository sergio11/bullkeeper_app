package sanchez.sanchez.sergio.domain.repository;

import java.util.List;
import io.reactivex.Observable;
import sanchez.sanchez.sergio.domain.models.GeofenceEntity;

/**
 * Geofences Repository
 */
public interface IGeofencesRepository {

    /**
     * Get All Geofences By Kid Id
     * @param kid
     * @return
     */
    Observable<List<GeofenceEntity>> getAllGeofencesByKidId(final String kid);


    /**
     * Delete All Geofences By Kid Id
     * @param kid
     * @return
     */
    Observable<String> deleteAllGeofencesBykidId(final String kid);

    /**
     * Delete Geofence
     * @param kid
     * @param id
     * @return
     */
    Observable<String> deleteGeofenceById(final String kid, final String id);

    /**
     * Get Geofence By Id
     * @param kid
     * @param id
     * @return
     */
    Observable<GeofenceEntity> getGeofenceById(final String kid, final String id);

    /**
     * Save Geofence
     * @param identity
     * @param name
     * @param lat
     * @param log
     * @param radius
     * @param type
     * @param kid
     * @return
     */
    Observable<GeofenceEntity> saveGeofences(
            final String identity, final String name, final double lat,
            final double log, final float radius, final String address, final String type,
            final String kid
    );

}
