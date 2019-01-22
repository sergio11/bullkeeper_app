package sanchez.sanchez.sergio.domain.models;

/**
 * Geofence Transition Type Enum
 */
public enum GeofenceTransitionTypeEnum {
    // indicates when the user enters the monitored region.
    TRANSITION_ENTER,
    // indicates when the user leaves the region.
    TRANSITION_EXIT,
    // The user entered the area and spent some time there
    TRANSITION_DWELL
}
