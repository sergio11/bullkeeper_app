package sanchez.sanchez.sergio.domain.models;

import java.io.Serializable;

/**
 * Child Location Entity
 */
public final class ChildLocationEntity implements Serializable {

    private String address;
    private double lat;
    private double lng;

    public ChildLocationEntity(){}

    public ChildLocationEntity(String address, double lat, double lng) {
        this.address = address;
        this.lat = lat;
        this.lng = lng;
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

    public double getLng() {
        return lng;
    }

    public void setLng(double lng) {
        this.lng = lng;
    }
}
