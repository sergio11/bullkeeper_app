package sanchez.sanchez.sergio.data.net.models.response;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonProperty;
import java.io.Serializable;
import java.util.Date;

/**
 * Call Detail
 */
@JsonIgnoreProperties(ignoreUnknown = true)
public final class CallDetailDTO implements Serializable {

    /**
     * Identity
     */
    @JsonProperty("identity")
    private String identity;

    /**
     * Phone Number
     */
    @JsonProperty("phone_number")
    private String phoneNumber;

    /**
     * Call Day Time
     */
    @JsonProperty("call_day_time")
    private Date callDayTime;

    /**
     * Call Duration
     */
    @JsonProperty("call_duration")
    private String callDuration;

    /**
     * Call Type
     */
    @JsonProperty("call_type")
    private String callType;

    /**
     * Kid
     */
    @JsonProperty("kid")
    private String kid;

    /**
     * Terminal
     */
    @JsonProperty("terminal")
    private String terminal;

    /**
     * Is Blocked
     */
    @JsonProperty("is_blocked")
    private boolean isBlocked;

    /**
     *
     */
    public CallDetailDTO(){}

    /**
     *
     * @param identity
     * @param phoneNumber
     * @param callDayTime
     * @param callDuration
     * @param callType
     * @param kid
     * @param terminal
     * @param isBlocked
     */
    public CallDetailDTO(final String identity, final String phoneNumber, final Date callDayTime,
                         final String callDuration, final String callType, final String kid,
                         final String terminal, final boolean isBlocked) {
        this.identity = identity;
        this.phoneNumber = phoneNumber;
        this.callDayTime = callDayTime;
        this.callDuration = callDuration;
        this.callType = callType;
        this.kid = kid;
        this.terminal = terminal;
        this.isBlocked = isBlocked;
    }

    public String getIdentity() {
        return identity;
    }

    public void setIdentity(String identity) {
        this.identity = identity;
    }

    public String getPhoneNumber() {
        return phoneNumber;
    }

    public void setPhoneNumber(String phoneNumber) {
        this.phoneNumber = phoneNumber;
    }

    public Date getCallDayTime() {
        return callDayTime;
    }

    public void setCallDayTime(Date callDayTime) {
        this.callDayTime = callDayTime;
    }

    public String getCallDuration() {
        return callDuration;
    }

    public void setCallDuration(String callDuration) {
        this.callDuration = callDuration;
    }

    public String getCallType() {
        return callType;
    }

    public void setCallType(String callType) {
        this.callType = callType;
    }

    public String getKid() {
        return kid;
    }

    public void setKid(String kid) {
        this.kid = kid;
    }

    public String getTerminal() {
        return terminal;
    }

    public void setTerminal(String terminal) {
        this.terminal = terminal;
    }

    public boolean isBlocked() {
        return isBlocked;
    }

    public void setBlocked(boolean blocked) {
        isBlocked = blocked;
    }

    @Override
    public String toString() {
        return "CallDetailDTO{" +
                "identity='" + identity + '\'' +
                ", phoneNumber='" + phoneNumber + '\'' +
                ", callDayTime=" + callDayTime +
                ", callDuration='" + callDuration + '\'' +
                ", callType='" + callType + '\'' +
                ", kid='" + kid + '\'' +
                ", terminal='" + terminal + '\'' +
                ", isBlocked=" + isBlocked +
                '}';
    }
}
