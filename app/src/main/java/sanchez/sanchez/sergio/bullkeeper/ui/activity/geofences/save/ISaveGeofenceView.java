package sanchez.sanchez.sergio.bullkeeper.ui.activity.geofences.save;

import java.util.LinkedHashMap;
import java.util.List;
import sanchez.sanchez.sergio.bullkeeper.core.ui.ISupportView;
import sanchez.sanchez.sergio.domain.models.GeofenceEntity;
import sanchez.sanchez.sergio.domain.models.PlaceSuggestionEntity;

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

    /**
     * On Geofence Loaded
     * @param geofenceEntity
     */
    void onGeofenceLoaded(final GeofenceEntity geofenceEntity);

    /**
     * On Suggested Places Loaded
     * @param suggestionEntityList
     */
    void onSuggestedPlacesLoaded(final List<PlaceSuggestionEntity> suggestionEntityList);

    /**
     * on No Suggested Places Found
     */
    void onNoSuggestedPlacesFound();


}
