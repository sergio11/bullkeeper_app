package sanchez.sanchez.sergio.data.net.models.response;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonProperty;

import java.io.Serializable;
import java.util.Date;

/**
 * Kid Request DTO
 */
@JsonIgnoreProperties(ignoreUnknown = true)
public final class KidRequestDTO implements Serializable {

    /**
     * Identity
     */
    @JsonProperty("identity")
    private String identity;

    /**
     * Type
     */
    @JsonProperty("type")
    private String type;

    /**
     * Request At
     */
    @JsonProperty("request_at")
    private Date requestAt;

    /**
     * Expired At
     */
    @JsonProperty("expired_at")
    private Date expiredAt;

    /**
     * Location
     */
    @JsonProperty("location")
    private LocationDTO location;

    /**
     * Kid
     */
    @JsonProperty("kid")
    private KidDTO kid;

    /**
     * Terminal
     */
    @JsonProperty("terminal")
    private TerminalDTO terminal;

    /**
     * Since
     */
    @JsonProperty("since")
    private String since;

    public KidRequestDTO(){}

    /**
     *
     * @param identity
     * @param type
     * @param requestAt
     * @param expiredAt
     * @param location
     * @param kid
     * @param terminal
     * @param since
     */
    public KidRequestDTO(final String identity, final String type, final Date requestAt,
                         final Date expiredAt, final LocationDTO location, final KidDTO kid,
                         final TerminalDTO terminal, final String since) {
        this.identity = identity;
        this.type = type;
        this.requestAt = requestAt;
        this.expiredAt = expiredAt;
        this.location = location;
        this.kid = kid;
        this.terminal = terminal;
        this.since = since;
    }


    public String getIdentity() {
        return identity;
    }

    public void setIdentity(String identity) {
        this.identity = identity;
    }

    public String getType() {
        return type;
    }

    public Date getRequestAt() {
        return requestAt;
    }

    public Date getExpiredAt() {
        return expiredAt;
    }

    public LocationDTO getLocation() {
        return location;
    }

    public KidDTO getKid() {
        return kid;
    }

    public TerminalDTO getTerminal() {
        return terminal;
    }

    public void setType(String type) {
        this.type = type;
    }

    public void setRequestAt(Date requestAt) {
        this.requestAt = requestAt;
    }

    public void setExpiredAt(Date expiredAt) {
        this.expiredAt = expiredAt;
    }

    public void setLocation(LocationDTO location) {
        this.location = location;
    }

    public void setKid(KidDTO kid) {
        this.kid = kid;
    }

    public void setTerminal(TerminalDTO terminal) {
        this.terminal = terminal;
    }

    public String getSince() {
        return since;
    }

    public void setSince(String since) {
        this.since = since;
    }

    @Override
    public String toString() {
        return "KidRequestDTO [type=" + type + ", requestAt=" + requestAt + ", expiredAt=" + expiredAt + ", location="
                + location + ", kid=" + kid + ", terminal=" + terminal + ", since=" + since + "]";
    }
}
