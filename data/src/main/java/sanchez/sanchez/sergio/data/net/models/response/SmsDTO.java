package sanchez.sanchez.sergio.data.net.models.response;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonProperty;
import java.io.Serializable;
import java.util.Date;

/**
 * SMS DTO
 */
@JsonIgnoreProperties(ignoreUnknown = true)
public final class SmsDTO implements Serializable {

    /**
     * Identity
     */
    @JsonProperty("identity")
    private String identity;


    /**
     * Address
     */
    @JsonProperty("address")
    private String address;


    /**
     * Message
     */
    @JsonProperty("message")
    private String message;


    /**
     * Read State
     */
    @JsonProperty("read_state")
    private String readState;


    /**
     * Date
     */
    @JsonProperty("date")
    private Date date;


    /**
     * Folder Name
     */
    @JsonProperty("folder_name")
    private String folderName;


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


    public SmsDTO(){}

    /**
     *
     * @param identity
     * @param address
     * @param message
     * @param readState
     * @param date
     * @param folderName
     * @param terminal
     * @param kid
     */
    public SmsDTO(final String identity, final String address, final String message,
                  final String readState, final Date date, final String folderName,
                  final String terminal, final String kid) {
        this.identity = identity;
        this.address = address;
        this.message = message;
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

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }

    public String getReadState() {
        return readState;
    }

    public void setReadState(String readState) {
        this.readState = readState;
    }

    public Date getDate() {
        return date;
    }

    public void setDate(Date date) {
        this.date = date;
    }

    public String getFolderName() {
        return folderName;
    }

    public void setFolderName(String folderName) {
        this.folderName = folderName;
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
        return "SmsDTO{" +
                "identity='" + identity + '\'' +
                ", address='" + address + '\'' +
                ", message='" + message + '\'' +
                ", readState='" + readState + '\'' +
                ", date='" + date + '\'' +
                ", folderName='" + folderName + '\'' +
                ", terminal='" + terminal + '\'' +
                ", kid='" + kid + '\'' +
                '}';
    }
}
