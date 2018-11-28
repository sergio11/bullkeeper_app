package sanchez.sanchez.sergio.data.net.models.request;

import com.fasterxml.jackson.annotation.JsonProperty;
import java.io.Serializable;

/**
 * Save Device DTO
 */
public final class SaveDeviceDTO implements Serializable {

    /**
     * Device Id
     */
    @JsonProperty("device_id")
    private String deviceId;

    /**
     * Registration Token
     */
    @JsonProperty("registration_token")
    private String registrationToken;

    public SaveDeviceDTO(){}

    public SaveDeviceDTO(String deviceId, String registrationToken) {
        this.deviceId = deviceId;
        this.registrationToken = registrationToken;
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

    @Override
    public String toString() {
        return "SaveDeviceDTO{" +
                "deviceId='" + deviceId + '\'' +
                ", registrationToken='" + registrationToken + '\'' +
                '}';
    }
}
