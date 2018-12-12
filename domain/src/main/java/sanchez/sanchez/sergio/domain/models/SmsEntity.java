package sanchez.sanchez.sergio.domain.models;

import java.io.Serializable;
import java.util.Date;

/**
 * Sms Entity
 */
public final class SmsEntity implements Serializable {

    /**
     * Identity
     */
    private String identity;

    /**
     * Address
     */
    private String address;

    /**
     * Read State
     */
    private SmsReadStateEnum readState;

    /**
     * Date
     */
    private Date date;

    /**
     * Folder Name
     */
    private SmsFolderNameEnum folderName;

    /**
     * Message
     */
    private String message;

    /**
     * Terminal
     */
    private String terminal;

    /**
     * Kid
     */
    private String kid;

    public SmsEntity(){}

    /**
     *
     * @param identity
     * @param address
     * @param readState
     * @param date
     * @param folderName
     * @param message
     * @param terminal
     * @param kid
     */
    public SmsEntity(final String identity, final String address,
                     final SmsReadStateEnum readState, final Date date,
                     final SmsFolderNameEnum folderName, final String message,
                     final String terminal,
                     final String kid) {
        this.identity = identity;
        this.address = address;
        this.readState = readState;
        this.date = date;
        this.folderName = folderName;
        this.terminal = terminal;
        this.kid = kid;
    }

    public String getIdentity() {
        return identity;
    }

    public void setIdentity(String identity) {
        this.identity = identity;
    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    public SmsReadStateEnum getReadState() {
        return readState;
    }

    public void setReadState(SmsReadStateEnum readState) {
        this.readState = readState;
    }

    public Date getDate() {
        return date;
    }

    public void setDate(Date date) {
        this.date = date;
    }

    public SmsFolderNameEnum getFolderName() {
        return folderName;
    }

    public void setFolderName(SmsFolderNameEnum folderName) {
        this.folderName = folderName;
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
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
        return "SmsEntity{" +
                "identity='" + identity + '\'' +
                ", address='" + address + '\'' +
                ", readState=" + readState +
                ", date=" + date +
                ", folderName=" + folderName +
                ", message='" + message + '\'' +
                ", terminal='" + terminal + '\'' +
                ", kid='" + kid + '\'' +
                '}';
    }
}
