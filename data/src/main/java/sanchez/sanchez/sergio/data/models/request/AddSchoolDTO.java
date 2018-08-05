package sanchez.sanchez.sergio.data.models.request;

import com.fasterxml.jackson.annotation.JsonProperty;

/**
 * Add School DTO
 */
public final class AddSchoolDTO {

    @JsonProperty("name")
    private String name;

    @JsonProperty("residence")
    private String residence;

    @JsonProperty("province")
    private String province;

    @JsonProperty("latitude")
    private Double latitude;

    @JsonProperty("longitude")
    private Double longitude;

    @JsonProperty("tfno")
    private String tfno;

    @JsonProperty("email")
    private String email;

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
