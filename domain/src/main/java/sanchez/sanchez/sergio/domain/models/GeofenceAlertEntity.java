package sanchez.sanchez.sergio.domain.models;

import java.io.Serializable;
import java.util.Date;

/**
 * Geofence Alert Entity
 */
public final class GeofenceAlertEntity implements Serializable {

    /**
     * Title
     */
    private String title;

    /**
     * Message
     */
    private String message;

    /**
     * Type
     */
    private GeofenceTransitionTypeEnum type;

    /**
     * Date
     */
    private Date date;

    public GeofenceAlertEntity(){}

    /**
     *
     * @param title
     * @param message
     * @param type
     * @param date
     */
    public GeofenceAlertEntity(final String title, final String message,
                               final GeofenceTransitionTypeEnum type, final Date date) {
        this.title = title;
        this.message = message;
        this.type = type;
        this.date = date;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }

    public GeofenceTransitionTypeEnum getType() {
        return type;
    }

    public void setType(GeofenceTransitionTypeEnum type) {
        this.type = type;
    }

    public Date getDate() {
        return date;
    }

    public void setDate(Date date) {
        this.date = date;
    }

    @Override
    public String toString() {
        return "GeofenceAlertEntity{" +
                "title='" + title + '\'' +
                ", message='" + message + '\'' +
                ", type=" + type +
                ", date=" + date +
                '}';
    }
}
