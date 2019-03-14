package sanchez.sanchez.sergio.data.net.models.response;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonProperty;
import java.io.Serializable;
import java.util.Date;


/**
 * Geofence Alert DTO
 */
@JsonIgnoreProperties(ignoreUnknown = true)
public final class GeofenceAlertDTO implements Serializable {

    /**
     * Date
     */
    @JsonProperty("date")
    private Date date;

    /**
     * Type
     */
    @JsonProperty("type")
    private String type;

    /**
     * Title
     */
    @JsonProperty("title")
    private String title;

    /**
     * Message
     */
    @JsonProperty("description")
    private String message;

    public GeofenceAlertDTO(){}

    /**
     *
     * @param date
     * @param type
     * @param title
     * @param message
     */
    public GeofenceAlertDTO(final Date date, final String type,
                            final String title, final String message) {
        this.date = date;
        this.type = type;
        this.title = title;
        this.message = message;
    }

    public Date getDate() {
        return date;
    }

    public void setDate(Date date) {
        this.date = date;
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
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

    @Override
    public String toString() {
        return "GeofenceAlertDTO{" +
                "date=" + date +
                ", type='" + type + '\'' +
                ", title='" + title + '\'' +
                ", message='" + message + '\'' +
                '}';
    }
}
