package sanchez.sanchez.sergio.data.net.models.response;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonProperty;

import java.io.Serializable;

/**
 * School DTO
 */
@JsonIgnoreProperties(ignoreUnknown = true)
public final class SchoolDTO implements Serializable {


    @JsonProperty("identity")
    private String Identity;

    @JsonProperty("name")
    private String name;

    @JsonProperty("residence")
    private String residence;

    @JsonProperty("latitude")
    private Double latitude;

    @JsonProperty("longitude")
    private Double longitude;

    @JsonProperty("province")
    private String province;

    @JsonProperty("tfno")
    private String tfno;

    @JsonProperty("email")
    private String email;


    public String getIdentity() {
        return Identity;
    }

    public void setIdentity(String identity) {
        Identity = identity;
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

    public String getProvince() {
        return province;
    }

    public void setProvince(String province) {
        this.province = province;
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
        return "SchoolDTO{" +
                "Identity='" + Identity + '\'' +
                ", name='" + name + '\'' +
                ", residence='" + residence + '\'' +
                ", latitude=" + latitude +
                ", longitude=" + longitude +
                ", province='" + province + '\'' +
                ", tfno='" + tfno + '\'' +
                ", email='" + email + '\'' +
                '}';
    }
}
