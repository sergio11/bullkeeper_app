package sanchez.sanchez.sergio.bullkeeper.sse.models;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonProperty;
import java.io.Serializable;

import sanchez.sanchez.sergio.data.net.models.response.KidDTO;
import sanchez.sanchez.sergio.data.net.models.response.LocationDTO;

/**
 * Kid Request Created DTO
 */
@JsonIgnoreProperties(ignoreUnknown = true)
public final class KidRequestCreatedDTO implements Serializable {

    /**
     * Identity
     */
    @JsonProperty("identity")
    private String identity;

    /**
     * Kid
     */
    @JsonProperty("kid")
    private KidDTO kid;

    /**
     * Terminal
     */
    @JsonProperty("terminal")
    private String terminal;

    /**
     * type
     */
    @JsonProperty("type")
    private String type;

    /**
     * Location DTO
     */
    @JsonProperty("location")
    private LocationDTO location;

    /**
     *
     */
    public KidRequestCreatedDTO(){}

    /**
     *
     * @param identity
     * @param kid
     * @param terminal
     * @param type
     * @param location
     */
    public KidRequestCreatedDTO(String identity, KidDTO kid, String terminal, String type, LocationDTO location) {
        this.identity = identity;
        this.kid = kid;
        this.terminal = terminal;
        this.type = type;
        this.location = location;
    }

    public String getIdentity() {
        return identity;
    }

    public void setIdentity(String identity) {
        this.identity = identity;
    }

    public KidDTO getKid() {
        return kid;
    }

    public void setKid(KidDTO kid) {
        this.kid = kid;
    }

    public String getTerminal() {
        return terminal;
    }

    public void setTerminal(String terminal) {
        this.terminal = terminal;
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    public LocationDTO getLocation() {
        return location;
    }

    public void setLocation(LocationDTO location) {
        this.location = location;
    }

    @Override
    public String toString() {
        return "KidRequestCreatedDTO{" +
                "identity='" + identity + '\'' +
                ", kid='" + kid + '\'' +
                ", terminal='" + terminal + '\'' +
                ", type='" + type + '\'' +
                ", location=" + location +
                '}';
    }
}
