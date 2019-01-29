package sanchez.sanchez.sergio.data.net.models.response;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonProperty;
import java.io.Serializable;
import java.util.Date;

/**
 * Phone Number Blocked DTO
 */
@JsonIgnoreProperties(ignoreUnknown = true)
public final class PhoneNumberBlockedDTO implements Serializable {


    /**
     * Identity
     */
    @JsonProperty("identity")
    private String identity;

    /**
     * Blocked At
     */
    @JsonProperty("blocked_at")
    private Date blockedAt;

    /**
     * Prefix
     */
    @JsonProperty("prefix")
    private String prefix;

    /**
     * Number
     */
    @JsonProperty("number")
    private String number;

    /**
     * Phone Number
     */
    @JsonProperty("phonenumber")
    private String phoneNumber;

    /**
     * Terminal
     */
    @JsonProperty("terminal")
    private String terminal;

    /**
     * Kid
     */
    @JsonProperty("kid")
    private String kid;

    public PhoneNumberBlockedDTO(){}

    /**
     *
     * @param identity
     * @param blockedAt
     * @param prefix
     * @param number
     * @param phoneNumber
     * @param terminal
     * @param kid
     */
    public PhoneNumberBlockedDTO(final String identity, final Date blockedAt, final String prefix,
                                 final String number, final String phoneNumber, final String terminal,
                                 final String kid) {
        this.identity = identity;
        this.blockedAt = blockedAt;
        this.prefix = prefix;
        this.number = number;
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

    public String getPrefix() {
        return prefix;
    }

    public void setPrefix(String prefix) {
        this.prefix = prefix;
    }

    public String getNumber() {
        return number;
    }

    public void setNumber(String number) {
        this.number = number;
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
        return "PhoneNumberBlockedDTO{" +
                "identity='" + identity + '\'' +
                ", blockedAt=" + blockedAt +
                ", prefix='" + prefix + '\'' +
                ", number='" + number + '\'' +
                ", phoneNumber='" + phoneNumber + '\'' +
                ", terminal='" + terminal + '\'' +
                ", kid='" + kid + '\'' +
                '}';
    }
}
