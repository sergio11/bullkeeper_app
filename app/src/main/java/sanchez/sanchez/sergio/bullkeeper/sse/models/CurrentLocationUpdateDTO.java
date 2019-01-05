package sanchez.sanchez.sergio.bullkeeper.sse.models;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonProperty;

import java.io.Serializable;

/**
 * Current Location Update DTO
 */
@JsonIgnoreProperties(ignoreUnknown = true)
public final class CurrentLocationUpdateDTO implements Serializable {

    /**
     * Kid
     */
    @JsonProperty("kid")
    private String kid;

    /**
     * Lat
     */
    @JsonProperty("lat")
    private double lat;

    /**
     * Log
     */
    @JsonProperty("log")
    private double log;

    /**
     * Address
     */
    @JsonProperty("address")
    private String address;

    /**
     *
     */
    public CurrentLocationUpdateDTO(){}

    /**
     *
     * @param kid
     * @param lat
     * @param log
     * @param address
     */
    public CurrentLocationUpdateDTO(String kid, double lat, double log, String address) {
        this.kid = kid;
        this.lat = lat;
        this.log = log;
        this.address = address;
    }

    public String getKid() {
        return kid;
    }

    public void setKid(String kid) {
        this.kid = kid;
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
        return "CurrentLocationUpdateDTO{" +
                "kid='" + kid + '\'' +
                ", lat=" + lat +
                ", log=" + log +
                ", address='" + address + '\'' +
                '}';
    }
}
