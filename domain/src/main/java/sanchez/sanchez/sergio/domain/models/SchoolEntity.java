package sanchez.sanchez.sergio.domain.models;

import java.io.Serializable;
import java.util.Locale;

/**
 * School Entity
 */
public final class SchoolEntity implements Serializable {

    private String identity;
    private String name;
    private String residence;
    private Double latitude;
    private Double longitude;
    private String province;
    private String tfno;
    private String email;

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

    /**
     * Get Location
     * @return
     */
    public String getLocation(){
        return (residence != null && !residence.isEmpty())
                && (province != null && !province.isEmpty()) ?
                    String.format(Locale.getDefault(), "%s - %s", residence, province) : "";
    }

    @Override
    public String toString() {
        return "SchoolEntity{" +
                "identity='" + identity + '\'' +
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
