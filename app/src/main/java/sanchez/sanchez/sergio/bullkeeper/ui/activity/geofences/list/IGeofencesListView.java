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


}
