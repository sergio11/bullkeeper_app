package sanchez.sanchez.sergio.data.net.models.response;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonProperty;

import java.io.Serializable;

/**
 * Geofence DTO
 */
@JsonIgnoreProperties(ignoreUnknown = true)
public final class GeofenceDTO implements Serializable {

    /**
     * Identity
     */
    @JsonProperty("identity")
    private String identity;

    /**
     * Name
     */
    @JsonProperty("name")
    private String name;

    /**
     * Address
     */
    @JsonProperty("address")
    private String address;

    /**
     * Latitude
     */
    @JsonProperty("lat")
    private double lat;

    /**
     * Longitude
     */
    @JsonProperty("log")
    private double log;

    /**
     * Radius
     */
    @JsonProperty("radius")
    private float radius;

    /**
     * Type
     */
    @JsonProperty("type")
    private String type;

    /**
     * Kid
     */
    @JsonProperty("kid")
    private String kid;


    public GeofenceDTO(){}

    /**
     *
     * @param identity
     * @param name
     * @param lat
     * @param log
     * @param radius
     * @param type
     * @param kid
     */
    public GeofenceDTO(final String identity, final String name, final String address,
                       final double lat, final double log, final float radius,
                       final String type, final String kid) {
        this.identity = identity;
        this.name = name;
        this.lat = lat;
        this.log = log;
        this.radius = radius;
        this.type = type;
        this.kid = kid;
    }

    public String getIdentity() {
        return identity;
    }

    public void setIdentity(String identity) {
        this.identity = identity;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    public double getLat() {
        return lat;
    }

    public void setLat(double lat) {
        this.lat = lat;
    }

    public double getLog() {
        return log;
    }

    public void setLog(double log) {
        this.log = log;
    }

    public float getRadius() {
        return radius;
    }

    public void setRadius(float radius) {
        this.radius = radius;
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    public String getKid() {
        return kid;
    }

    public void setKid(String kid) {
        this.kid = kid;
    }

    @Override
    public String toString() {
        return "GeofenceDTO{" +
                "identity='" + identity + '\'' +
                ", name='" + name + '\'' +
                ", address='" + address + '\'' +
                ", lat=" + lat +
                ", log=" + log +
                ", radius=" + radius +
                ", type='" + type + '\'' +
                ", kid='" + kid + '\'' +
                '}';
    }
}
