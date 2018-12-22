package sanchez.sanchez.sergio.domain.models;

import java.io.Serializable;
import java.util.Date;

/**
 * Phone Number Blocked Entity
 */
public final class PhoneNumberBlockedEntity implements Serializable {

    /**
     * Identity
     */
    private String identity;

    /**
     * Blocked At
     */
    private Date blockedAt;

    /**
     * Phone Number
     */
    private String phoneNumber;

    /**
     * Terminal
     */
    private String terminal;

    /**
     * Kid
     */
    private String kid;

    /**
     *
     */
    public PhoneNumberBlockedEntity(){}

    /**
     *
     * @param identity
     * @param blockedAt
     * @param phoneNumber
     * @param terminal
     * @param kid
     */
    public PhoneNumberBlockedEntity(final String identity, final Date blockedAt, final String phoneNumber,
                                    final String terminal, final String kid) {
        this.identity = identity;
        this.blockedAt = blockedAt;
        this.phoneNumber = phoneNumber;
        this.terminal = terminal;
        this.kid = kid;
    }

    public String getIdentity() {
        return identity;
    }

    public void setIdentity(String identity) {
        this.identity = identity;
    }

    public Date getBlockedAt() {
        return blockedAt;
    }

    public void setBlockedAt(Date blockedAt) {
        this.blockedAt = blockedAt;
    }

    public String getPhoneNumber() {
        return phoneNumber;
    }

    public void setPhoneNumber(String phoneNumber) {
        this.phoneNumber = phoneNumber;
    }

    public String getTerminal() {
        return terminal;
    }

    public void setTerminal(String terminal) {
        this.terminal = terminal;
    }

    public String getKid() {
        return kid;
    }

    public void setKid(String kid) {
        this.kid = kid;
    }

    @Override
    public String toString() {
        return "PhoneNumberBlockedEntity{" +
                "identity='" + identity + '\'' +
                ", blockedAt=" + blockedAt +
                ", phoneNumber='" + phoneNumber + '\'' +
                ", terminal='" + terminal + '\'' +
                ", kid='" + kid + '\'' +
                '}';
    }
}
