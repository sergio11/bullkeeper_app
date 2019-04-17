package sanchez.sanchez.sergio.bullkeeper.ui.activity.geofences.list;

import sanchez.sanchez.sergio.bullkeeper.core.ui.ISupportLCEView;
import sanchez.sanchez.sergio.domain.models.GeofenceEntity;

/**
 * Geofences List View
 */
public interface IGeofencesListView
        extends ISupportLCEView<GeofenceEntity> {


    /**
     * On All Geofences Deleted
     */
    void onAllGeofencesDeleted();

    /**
     * On Geofence Deleted
     */
    void onGeofenceDeleted();

    /**
     * Geofence Status Changed Successfully
     * @param geofence
     * @param newStatus
     */
    void onGeofenceStatusChangedSuccessfully(final String geofence, final boolean newStatus);

    /**
     * Geofence Status Changed Failed
     * @param geofence
     * @param newStatus
     */
    void onGeofenceStatusChangedFailed(final String geofence, final boolean newStatus);


}
