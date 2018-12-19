package sanchez.sanchez.sergio.domain.models;

import java.io.Serializable;
import java.util.Date;

/**
 * Call Detail Entity
 */
public final class CallDetailEntity implements Serializable {

    /**
     * Identity
     */
    private String identity;

    /**
     * Phone Number
     */
    private String phoneNumber;

    /**
     * Call Day Time
     */
    private Date callDayTime;

    /**
     * Call Duration
     */
    private String callDuration;

    /**
     * Call Type
     */
    private CallTypeEnum callType;

    /**
     * Kid
     */
    private String kid;

    /**
     * Terminal
     */
    private String terminal;

    /**
     * Is Blocked
     */
    private boolean isBlocked;

    /**
     *
     */
    public CallDetailEntity(){}

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
    public CallDetailEntity(final String identity, final String phoneNumber, final Date callDayTime,
                            final String callDuration, final CallTypeEnum callType, final String kid,
                            final String terminal, boolean isBlocked) {
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

    public CallTypeEnum getCallType() {
        return callType;
    }

    public void setCallType(CallTypeEnum callType) {
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
        return "CallDetailEntity{" +
                "identity='" + identity + '\'' +
                ", phoneNumber='" + phoneNumber + '\'' +
                ", callDayTime=" + callDayTime +
                ", callDuration='" + callDuration + '\'' +
                ", callType=" + callType +
                ", kid='" + kid + '\'' +
                ", terminal='" + terminal + '\'' +
                ", isBlocked=" + isBlocked +
                '}';
    }
}
