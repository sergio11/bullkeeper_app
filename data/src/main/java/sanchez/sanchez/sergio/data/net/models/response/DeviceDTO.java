package sanchez.sanchez.sergio.data.net.models.response;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonProperty;
import java.io.Serializable;
import java.util.Date;

/**
 * Device DTO
 */
@JsonIgnoreProperties(ignoreUnknown = true)
public final class DeviceDTO implements Serializable {

    @JsonProperty("device_id")
    private String deviceId;

    @JsonProperty("registration_token")
    private String registrationToken;

    @JsonProperty("type")
    private String type;

    @JsonProperty("create_at")
    private Date createAt;

    @JsonProperty("notification_key_name")
    private String notificationKeyName;

    @JsonProperty("notification_key")
    private String notificationKey;

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
        return "DeviceDTO{" +
                "deviceId='" + deviceId + '\'' +
                ", registrationToken='" + registrationToken + '\'' +
                ", type='" + type + '\'' +
                ", createAt=" + createAt +
                ", notificationKeyName='" + notificationKeyName + '\'' +
                ", notificationKey='" + notificationKey + '\'' +
                '}';
    }
}
