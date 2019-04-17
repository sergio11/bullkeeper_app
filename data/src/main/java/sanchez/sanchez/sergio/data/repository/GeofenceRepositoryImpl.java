package sanchez.sanchez.sergio.data.repository;

import com.fernandocejas.arrow.checks.Preconditions;
import java.util.List;
import io.reactivex.Observable;
import sanchez.sanchez.sergio.data.mapper.AbstractDataMapper;
import sanchez.sanchez.sergio.data.net.models.request.SaveGeofenceDTO;
import sanchez.sanchez.sergio.data.net.models.response.GeofenceAlertDTO;
import sanchez.sanchez.sergio.data.net.models.response.GeofenceDTO;
import sanchez.sanchez.sergio.data.net.services.IGeofenceService;
import sanchez.sanchez.sergio.domain.models.GeofenceAlertEntity;
import sanchez.sanchez.sergio.domain.models.GeofenceEntity;
import sanchez.sanchez.sergio.domain.repository.IGeofencesRepository;

/**
 * Geofence Repository Impl
 */
public final class GeofenceRepositoryImpl implements IGeofencesRepository {

    /**
     * Geofence Data Mapper
     */
    private final AbstractDataMapper<GeofenceDTO, GeofenceEntity> geofenceEntityAbstractDataMapper;

    /**
     * Geofence Alert Data Mapper
     */
    private final AbstractDataMapper<GeofenceAlertDTO, GeofenceAlertEntity> geofenceAlertEntityAbstractDataMapper;

    /**
     * Geofence Service
     */
    private final IGeofenceService geofenceService;

    /**
     *
     * @param geofenceEntityAbstractDataMapper
     * @param geofenceService
     */
    public GeofenceRepositoryImpl(
            final AbstractDataMapper<GeofenceDTO, GeofenceEntity> geofenceEntityAbstractDataMapper,
            final AbstractDataMapper<GeofenceAlertDTO, GeofenceAlertEntity> geofenceAlertEntityAbstractDataMapper,
            final IGeofenceService geofenceService) {
        this.geofenceEntityAbstractDataMapper = geofenceEntityAbstractDataMapper;
        this.geofenceAlertEntityAbstractDataMapper = geofenceAlertEntityAbstractDataMapper;
        this.geofenceService = geofenceService;
    }

    /**
     * Get All Geofences By Kid Id
     * @param kid
     * @return
     */
    @Override
    public Observable<List<GeofenceEntity>> getAllGeofencesByKidId(final String kid) {
        Preconditions.checkNotNull(kid, "Kid can not be null");
        Preconditions.checkState(!kid.isEmpty(), "Kid can not be empty");

        return geofenceService.getGeofencesByKid(kid)
                .map(listAPIResponse -> listAPIResponse != null
                        && listAPIResponse.getData() != null ? listAPIResponse.getData(): null)
                .map(geofenceEntityAbstractDataMapper::transform);
    }

    /**
     * Delete All Geofences By Kid
     * @param kid
     * @return
     */
    @Override
    public Observable<String> deleteAllGeofencesBykidId(final String kid) {
        Preconditions.checkNotNull(kid, "Kid can not be null");
        Preconditions.checkState(!kid.isEmpty(), "Kid can not be empty");

        return geofenceService.deleteAllGeofencesByKidId(kid)
                .map(stringAPIResponse -> stringAPIResponse != null &&
                    stringAPIResponse.getData() != null ? stringAPIResponse.getData(): null);

    }

    /**
     *
     * @param kid
     * @param id
     * @return
     */
    @Override
    public Observable<String> deleteGeofenceById(final String kid, final String id) {
        Preconditions.checkNotNull(kid, "Kid can not be null");
        Preconditions.checkState(!kid.isEmpty(), "Kid can not be empty");
        Preconditions.checkNotNull(id, "Id can not be null");
        Preconditions.checkState(!id.isEmpty(), "Id can not be empty");

        return geofenceService.deleteGeofence(kid, id)
                .map(stringAPIResponse -> stringAPIResponse != null &&
                        stringAPIResponse.getData() != null ? stringAPIResponse.getData(): null);
    }

    /**
     * Get Gofence By Id
     * @param kid
     * @param id
     * @return
     */
    @Override
    public Observable<GeofenceEntity> getGeofenceById(final String kid, final String id) {
        Preconditions.checkNotNull(kid, "Kid can not be null");
        Preconditions.checkState(!kid.isEmpty(), "Kid can not be empty");
        Preconditions.checkNotNull(id, "Id can not be null");
        Preconditions.checkState(!id.isEmpty(), "Id can not be empty");

        return geofenceService.getGeofenceById(kid, id)
                .map(response -> response != null &&
                        response.getData() != null ? response.getData(): null)
                .map(geofenceEntityAbstractDataMapper::transform);
    }

    /**
     * Get Geofence Alerts
     * @param kid
     * @param geofence
     * @return
     */
    @Override
    public Observable<List<GeofenceAlertEntity>> getGeofenceAlerts(final String kid, final String geofence) {
        Preconditions.checkNotNull(kid, "Kid can not be null");
        Preconditions.checkState(!kid.isEmpty(), "Kid can not be empty");
        Preconditions.checkNotNull(geofence, "Geofence can not be null");
        Preconditions.checkState(!geofence.isEmpty(), "Geofence can not be empty");

        return geofenceService.getGeofenceAlerts(kid, geofence)
                .map(response -> response != null &&
                        response.getData() != null ? response.getData(): null)
                .map(geofenceAlertEntityAbstractDataMapper::transform);
    }

    /**
     * Delete Geofence Alerts
     * @param kid
     * @param geofence
     * @return
     */
    @Override
    public Observable<String> deleteGeofenceAlerts(final String kid, final String geofence) {
        Preconditions.checkNotNull(kid, "Kid can not be null");
        Preconditions.checkState(!kid.isEmpty(), "Kid can not be empty");
        Preconditions.checkNotNull(geofence, "Geofence can not be null");
        Preconditions.checkState(!geofence.isEmpty(), "Geofence can not be empty");

        return geofenceService.deleteGeofenceAlerts(kid, geofence)
                .map(stringAPIResponse -> stringAPIResponse != null &&
                        stringAPIResponse.getData() != null ? stringAPIResponse.getData(): null);
    }

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
    @Override
    public Observable<GeofenceEntity> saveGeofences(final String identity, final String name,
                                                    final double lat, final double log, final float radius,
                                                    final String address, final String type,
                                                    final boolean isEnabled, final String kid) {
        Preconditions.checkNotNull(identity, "identity can not be null");
        Preconditions.checkNotNull(name, "Name can not be null");
        Preconditions.checkState(!name.isEmpty(), "Name can not be empty");
        Preconditions.checkNotNull(address, "Address can not be null");
        Preconditions.checkState(!address.isEmpty(), "Address can not be empty");
        Preconditions.checkNotNull(type, "Type can not be null");
        Preconditions.checkState(!type.isEmpty(), "Type can not be empty");
        Preconditions.checkNotNull(kid, "Kid can not be null");
        Preconditions.checkState(!kid.isEmpty(), "Kid can not be empty");

        // Save Geofence
        return geofenceService.saveGeofence(kid, new SaveGeofenceDTO(identity,
                name, lat, log, address, radius, type, isEnabled, kid))
                .map(response -> response != null && response.getData() != null ?
                    response.getData(): null)
                .map(geofenceEntityAbstractDataMapper::transform);
    }

    /**
     * Enable Geofence
     * @param kid
     * @param geofence
     * @return
     */
    @Override
    public Observable<String> enableGeofence(final String kid, final String geofence) {
        Preconditions.checkNotNull(kid, "Kid can not be null");
        Preconditions.checkNotNull(geofence, "Geofence can not be null");

        return geofenceService.enableGeofence(kid, geofence)
                .map(response -> response != null && response.getData() != null ?
                        response.getData(): null);
    }

    /**
     * Disable Geofence
     * @param kid
     * @param geofence
     * @return
     */
    @Override
    public Observable<String> disableGeofence(final String kid, final String geofence) {
        Preconditions.checkNotNull(kid, "Kid can not be null");
        Preconditions.checkNotNull(geofence, "Geofence can not be null");
        return geofenceService.disableGeofence(kid, geofence)
                .map(response -> response != null && response.getData() != null ?
                        response.getData(): null);
    }
}
