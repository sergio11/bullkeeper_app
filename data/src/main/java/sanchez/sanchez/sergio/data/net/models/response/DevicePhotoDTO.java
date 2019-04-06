package sanchez.sanchez.sergio.data.net.models.response;

import com.fasterxml.jackson.annotation.JsonProperty;

import java.io.Serializable;

/**
 * Device Photo DTO
 **/
public final class DevicePhotoDTO implements Serializable {

    /**
     * Identity
     */
    @JsonProperty("identity")
    private String identity;

    /**
     * Display Name
     */
    @JsonProperty("display_name")
    private String displayName;

    /**
     * Path
     */
    @JsonProperty("path")
    private String path;

    /**
     * Date Added
     */
    @JsonProperty("date_added")
    private Long dateAdded;

    /**
     * Date Modified
     */
    @JsonProperty("date_modified")
    private Long dateModified;

    /**
     * Date Taken
     */
    @JsonProperty("date_taken")
    private Long dateTaken;

    /**
     * Height
     */
    @JsonProperty("height")
    private Integer height;

    /**
     * Width
     */
    @JsonProperty("width")
    private Integer width;

    /**
     * Orientation
     */
    @JsonProperty("orientation")
    private Integer orientation;

    /**
     * Size
     */
    @JsonProperty("size")
    private Integer size;

    /**
     * Local Id
     */
    @JsonProperty("local_id")
    private String localId;


    /**
     * Image Id
     */
    @JsonProperty("image_id")
    private String imageId;

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

    public DevicePhotoDTO(){}

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
     * @param imageId
     * @param terminal
     * @param kid
     */
    public DevicePhotoDTO(String identity, String displayName, String path, Long dateAdded,
                          Long dateModified, Long dateTaken, Integer height, Integer width,
                          Integer orientation, Integer size, String localId, String imageId,
                          String terminal, String kid) {
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
        this.imageId = imageId;
        this.terminal = terminal;
        this.kid = kid;
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

    public String getImageId() {
        return imageId;
    }

    public void setImageId(String imageId) {
        this.imageId = imageId;
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
        return "DevicePhotoDTO{" +
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
                ", imageId='" + imageId + '\'' +
                ", terminal='" + terminal + '\'' +
                ", kid='" + kid + '\'' +
                '}';
    }
}
