package sanchez.sanchez.sergio.domain.models;

import java.io.Serializable;

/**
 * Contact Entity
 */
public class ContactEntity implements Serializable {

    /**
     * Identity
     */
    private String identity;

    /**
     * Name
     */
    private String name;

    /**
     * Phone Number
     */
    private String phoneNumber;

    /**
     * Local Id
     */
    private String localId;

    /**
     * Photo Encoded String
     */
    private String photoEncodedString;

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


    public ContactEntity(){}

    /**
     *
     * @param identity
     * @param name
     * @param phoneNumber
     * @param localId
     * @param photoEncodedString
     * @param kid
     * @param terminal
     * @param isBlocked
     */
    public ContactEntity(final String identity, final String name, final String phoneNumber,
                         final String localId, final String photoEncodedString,
                         final String kid, final String terminal,
                         final boolean isBlocked) {
        this.identity = identity;
        this.name = name;
        this.phoneNumber = phoneNumber;
        this.localId = localId;
        this.photoEncodedString = photoEncodedString;
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

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getPhoneNumber() {
        return phoneNumber;
    }

    public void setPhoneNumber(String phoneNumber) {
        this.phoneNumber = phoneNumber;
    }

    public String getLocalId() {
        return localId;
    }

    public void setLocalId(String localId) {
        this.localId = localId;
    }

    public String getPhotoEncodedString() {
        return photoEncodedString;
    }

    public void setPhotoEncodedString(String photoEncodedString) {
        this.photoEncodedString = photoEncodedString;
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
        return "ContactEntity{" +
                "identity='" + identity + '\'' +
                ", name='" + name + '\'' +
                ", phoneNumber='" + phoneNumber + '\'' +
                ", localId='" + localId + '\'' +
                ", photoEncodedString='" + photoEncodedString + '\'' +
                ", kid='" + kid + '\'' +
                ", terminal='" + terminal + '\'' +
                ", isBlocked=" + isBlocked +
                '}';
    }
}
