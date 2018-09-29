package sanchez.sanchez.sergio.domain.models;

import java.io.Serializable;
import java.util.Date;

/**
 * Device Entity
 */
public class DeviceEntity implements Serializable {

    private String deviceId;
    private String registrationToken;
    private String type;
    private Date createAt;
    private String notificationKeyName;
    private String notificationKey;

    public DeviceEntity(){}

    public DeviceEntity(String deviceId, String registrationToken, String type, Date createAt, String notificationKeyName, String notificationKey) {
        this.deviceId = deviceId;
        this.registrationToken = registrationToken;
        this.type = type;
        this.createAt = createAt;
        this.notificationKeyName = notificationKeyName;
        this.notificationKey = notificationKey;
    }

    public String getDeviceId() {
        return deviceId;
    }

    public void setDeviceId(String deviceId) {
        this.deviceId = deviceId;
    }

    public String getRegistrationToken() {
        return registrationToken;
    }

    public void setRegistrationToken(String registrationToken) {
        this.registrationToken = registrationToken;
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    public Date getCreateAt() {
        return createAt;
    }

    public void setCreateAt(Date createAt) {
        this.createAt = createAt;
    }

    public String getNotificationKeyName() {
        return notificationKeyName;
    }

    public void setNotificationKeyName(String notificationKeyName) {
        this.notificationKeyName = notificationKeyName;
    }

    public String getNotificationKey() {
        return notificationKey;
    }

    public void setNotificationKey(String notificationKey) {
        this.notificationKey = notificationKey;
    }

    @Override
    public String toString() {
        return "DeviceEntity{" +
                "deviceId='" + deviceId + '\'' +
                ", registrationToken='" + registrationToken + '\'' +
                ", type='" + type + '\'' +
                ", createAt='" + createAt + '\'' +
                ", notificationKeyName='" + notificationKeyName + '\'' +
                ", notificationKey='" + notificationKey + '\'' +
                '}';
    }
}
