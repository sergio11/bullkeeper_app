package sanchez.sanchez.sergio.domain.models;

import java.io.Serializable;

/**
 * Device Photo
 **/
public final class DevicePhotoEntity implements Serializable {

    private String identity;
    private String displayName;
    private String path;
    private Long dateAdded;
    private Long dateModified;
    private Long dateTaken;
    private Integer height;
    private Integer width;
    private Integer orientation;
    private Integer size;
    private String localId;
    private String terminal;
    private String kid;
    private String imageUrl;

    public DevicePhotoEntity(){}

    /**
     *
     * @param identity
     * @param displayName
     * @param path
     * @param dateAdded
     * @param dateModified
     * @param dateTaken
     * @param height
     * @param width
     * @param orientation
     * @param size
     * @param localId
     * @param terminal
     * @param kid
     * @param imageUrl
     */
    public DevicePhotoEntity(String identity, String displayName, String path, Long dateAdded,
                             Long dateModified, Long dateTaken, Integer height, Integer width,
                             Integer orientation, Integer size, String localId, String terminal,
                             String kid, String imageUrl) {
        this.identity = identity;
        this.displayName = displayName;
        this.path = path;
        this.dateAdded = dateAdded;
        this.dateModified = dateModified;
        this.dateTaken = dateTaken;
        this.height = height;
        this.width = width;
        this.orientation = orientation;
        this.size = size;
        this.localId = localId;
        this.terminal = terminal;
        this.kid = kid;
        this.imageUrl = imageUrl;
    }

    public String getIdentity() {
        return identity;
    }

    public void setIdentity(String identity) {
        this.identity = identity;
    }

    public String getDisplayName() {
        return displayName;
    }

    public void setDisplayName(String displayName) {
        this.displayName = displayName;
    }

    public String getPath() {
        return path;
    }

    public void setPath(String path) {
        this.path = path;
    }

    public Long getDateAdded() {
        return dateAdded;
    }

    public void setDateAdded(Long dateAdded) {
        this.dateAdded = dateAdded;
    }

    public Long getDateModified() {
        return dateModified;
    }

    public void setDateModified(Long dateModified) {
        this.dateModified = dateModified;
    }

    public Long getDateTaken() {
        return dateTaken;
    }

    public void setDateTaken(Long dateTaken) {
        this.dateTaken = dateTaken;
    }

    public Integer getHeight() {
        return height;
    }

    public void setHeight(Integer height) {
        this.height = height;
    }

    public Integer getWidth() {
        return width;
    }

    public void setWidth(Integer width) {
        this.width = width;
    }

    public Integer getOrientation() {
        return orientation;
    }

    public void setOrientation(Integer orientation) {
        this.orientation = orientation;
    }

    public Integer getSize() {
        return size;
    }

    public void setSize(Integer size) {
        this.size = size;
    }

    public String getLocalId() {
        return localId;
    }

    public void setLocalId(String localId) {
        this.localId = localId;
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

    public String getImageUrl() {
        return imageUrl;
    }

    public void setImageUrl(String imageUrl) {
        this.imageUrl = imageUrl;
    }

    @Override
    public String toString() {
        return "DevicePhotoEntity{" +
                "identity='" + identity + '\'' +
                ", displayName='" + displayName + '\'' +
                ", path='" + path + '\'' +
                ", dateAdded=" + dateAdded +
                ", dateModified=" + dateModified +
                ", dateTaken=" + dateTaken +
                ", height=" + height +
                ", width=" + width +
                ", orientation=" + orientation +
                ", size=" + size +
                ", localId='" + localId + '\'' +
                ", terminal='" + terminal + '\'' +
                ", kid='" + kid + '\'' +
                ", imageUrl='" + imageUrl + '\'' +
                '}';
    }
}
