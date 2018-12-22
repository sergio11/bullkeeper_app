package sanchez.sanchez.sergio.data.net.models.response;

import com.fasterxml.jackson.annotation.JsonProperty;

import java.io.Serializable;

/**
 * Location DTO
 */
public final class LocationDTO implements Serializable {

    /**
     * Latitude
     */
    @JsonProperty("latitude")
    private double lat;

    /**
     * Longitude
     */
    @JsonProperty("longitude")
    private double log;

    /**
     * Address
     */
    @JsonProperty("address")
    private String address;

    public LocationDTO(){}

    /**
     * Location DTO
     * @param lat
     * @param log
     * @param address
     */
    public LocationDTO(double lat, double log, String address) {
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
        return "LocationDTO{" +
                "lat=" + lat +
                ", log=" + log +
                ", address='" + address + '\'' +
                '}';
    }
}
