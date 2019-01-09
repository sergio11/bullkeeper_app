package sanchez.sanchez.sergio.bullkeeper.ui.activity.geofences.save;

import java.util.LinkedHashMap;
import java.util.List;
import sanchez.sanchez.sergio.bullkeeper.core.ui.ISupportView;

/**
 * Save Geofence View
 */
public interface ISaveGeofenceView extends ISupportView {

    /**
     * On Validation Errors
     * @param errors
     */
    void onValidationErrors(final List<LinkedHashMap<String, String>> errors);

    /**
     * On Geofence Deleted
     */
    void onGeofenceDeleted();


}
