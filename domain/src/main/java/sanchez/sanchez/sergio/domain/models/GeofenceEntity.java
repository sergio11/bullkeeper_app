package sanchez.sanchez.sergio.domain.models;

import java.io.Serializable;

/**
 * Geofence Entity
 */
public final class GeofenceEntity implements Serializable {

    /**
     * Identity
     */
    private String identity;

    /**
     * Name
     */
    private String name;

    /**
     * Latitude
     */
    private double lat;

    /**
     * Longitude
     */
    private double log;

    /**
     * Radius
     */
    private float radius;

    /**
     * Type
     */
    private String type;

    /**
     * Type
     */
    private String kid;

    /**
     *
     */
    public GeofenceEntity(){}

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
    public GeofenceEntity(String identity, String name, double lat, double log, float radius, String type, String kid) {
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
        return "GeofenceEntity{" +
                "identity='" + identity + '\'' +
                ", name='" + name + '\'' +
                ", lat=" + lat +
                ", log=" + log +
                ", radius=" + radius +
                ", type='" + type + '\'' +
                ", kid='" + kid + '\'' +
                '}';
    }
}
