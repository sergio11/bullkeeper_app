package sanchez.sanchez.sergio.data.net.models.request;

import com.fasterxml.jackson.annotation.JsonProperty;

/**
 * Add School DTO
 */
public final class AddSchoolDTO {

    /**
     * Name
     */
    @JsonProperty("name")
    private String name;

    /**
     * Residence
     */
    @JsonProperty("residence")
    private String residence;

    /**
     * Province
     */
    @JsonProperty("province")
    private String province;

    /**
     * Latitude
     */
    @JsonProperty("latitude")
    private Double latitude;

    /**
     * Longitude
     */
    @JsonProperty("longitude")
    private Double longitude;

    /**
     * Tfno
     */
    @JsonProperty("tfno")
    private String tfno;

    /**
     * Email
     */
    @JsonProperty("email")
    private String email;

    public AddSchoolDTO(){}

    public AddSchoolDTO(String name, String residence, String province, Double latitude, Double longitude, String tfno, String email) {
        this.name = name;
        this.residence = residence;
        this.province = province;
        this.latitude = latitude;
        this.longitude = longitude;
        this.tfno = tfno;
        this.email = email;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getResidence() {
        return residence;
    }

    public void setResidence(String residence) {
        this.residence = residence;
    }

    public String getProvince() {
        return province;
    }

    public void setProvince(String province) {
        this.province = province;
    }

    public Double getLatitude() {
        return latitude;
    }

    public void setLatitude(Double latitude) {
        this.latitude = latitude;
    }

    public Double getLongitude() {
        return longitude;
    }

    public void setLongitude(Double longitude) {
        this.longitude = longitude;
    }

    public String getTfno() {
        return tfno;
    }

    public void setTfno(String tfno) {
        this.tfno = tfno;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    @Override
    public String toString() {
        return "AddSchoolDTO{" +
                "name='" + name + '\'' +
                ", residence='" + residence + '\'' +
                ", province='" + province + '\'' +
                ", latitude=" + latitude +
                ", longitude=" + longitude +
                ", tfno='" + tfno + '\'' +
                ", email='" + email + '\'' +
                '}';
    }
}
