package sanchez.sanchez.sergio.domain.models;

import java.io.Serializable;

/**
 * Location Entity
 */
public final class LocationEntity implements Serializable {

    /**
     * Latitude
     */
    private double lat;

    /**
     * Longitude
     */
    private double log;

    /**
     * Address
     */
    private String address;

    public LocationEntity(){}

    /**
     * Location Entity
     * @param lat
     * @param log
     * @param address
     */
    public LocationEntity(double lat, double log, String address) {
        this.lat = lat;
        this.log = log;
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

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    @Override
    public String toString() {
        return "LocationEntity{" +
                "lat=" + lat +
                ", log=" + log +
                ", address='" + address + '\'' +
                '}';
    }
}
