package sanchez.sanchez.sergio.domain.models;

import java.io.Serializable;
import java.util.Date;

/**
 * Kid Request Entity
 */
public final class KidRequestEntity implements Serializable {

    /**
     * Identity
     */
    private String identity;

    /**
     * Request Type
     */
    private RequestTypeEnum type;

    /**
     * Request At
     */
    private Date requestAt = new Date();

    /**
     * Expired At
     */
    private Date expiredAt;

    /**
     * Location
     */
    private LocationEntity location;

    /**
     * Kid
     */
    private KidEntity kid;

    /**
     * Terminal
     */
    private TerminalEntity terminal;


    public KidRequestEntity(){}

    /**
     *
     * @param identity
     * @param type
     * @param requestAt
     * @param expiredAt
     * @param location
     * @param kid
     * @param terminal
     */
    public KidRequestEntity(final String identity, final RequestTypeEnum type, final Date requestAt,
                            final Date expiredAt, final LocationEntity location, final KidEntity kid,
                            final TerminalEntity terminal) {
        this.identity = identity;
        this.type = type;
        this.requestAt = requestAt;
        this.expiredAt = expiredAt;
        this.location = location;
        this.kid = kid;
        this.terminal = terminal;
    }

    public String getIdentity() {
        return identity;
    }

    public void setIdentity(String identity) {
        this.identity = identity;
    }

    public RequestTypeEnum getType() {
        return type;
    }

    public void setType(RequestTypeEnum type) {
        this.type = type;
    }

    public Date getRequestAt() {
        return requestAt;
    }

    public void setRequestAt(Date requestAt) {
        this.requestAt = requestAt;
    }

    public Date getExpiredAt() {
        return expiredAt;
    }

    public void setExpiredAt(Date expiredAt) {
        this.expiredAt = expiredAt;
    }

    public LocationEntity getLocation() {
        return location;
    }

    public void setLocation(LocationEntity location) {
        this.location = location;
    }

    public KidEntity getKid() {
        return kid;
    }

    public void setKid(KidEntity kid) {
        this.kid = kid;
    }

    public TerminalEntity getTerminal() {
        return terminal;
    }

    public void setTerminal(TerminalEntity terminal) {
        this.terminal = terminal;
    }

    @Override
    public String toString() {
        return "KidRequestEntity{" +
                "identity='" + identity + '\'' +
                ", type=" + type +
                ", requestAt=" + requestAt +
                ", expiredAt=" + expiredAt +
                ", location=" + location +
                ", kid=" + kid +
                ", terminal=" + terminal +
                '}';
    }
}
