package sanchez.sanchez.sergio.data.net.models.response;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonProperty;
import java.io.Serializable;

/**
 * Terminal DTO
 */
@JsonIgnoreProperties(ignoreUnknown = true)
public class TerminalDTO implements Serializable {

    /**
     * Identity
     */
    @JsonProperty("identity")
    protected String identity;

    /**
     * App Version Name
     */
    @JsonProperty("app_version_name")
    protected String appVersionName;


    /**
     * App Version Code
     */
    @JsonProperty("app_version_code")
    protected String appVersionCode;

    /**
     * Os Version
     */
    @JsonProperty("os_version")
    protected String osVersion;

    /**
     * Sdk Version
     */
    @JsonProperty("sdk_version")
    protected String sdkVersion;

    /**
     * Manufacturer
     */
    @JsonProperty("manufacturer")
    protected String manufacturer;

    /**
     * Market Name
     */
    @JsonProperty("market_name")
    protected String marketName;

    /**
     * Model
     */
    @JsonProperty("model")
    protected String model;

    /**
     * Code Name
     */
    @JsonProperty("code_name")
    protected String codeName;


    /**
     * Device Name
     */
    @JsonProperty("device_name")
    protected String deviceName;

    /**
     * Device Id
     */
    @JsonProperty("device_id")
    protected String deviceId;

    /**
     * Bed Time Enabled
     */
    @JsonProperty("bed_time_enabled")
    protected boolean bedTimeEnabled;

    /**
     * Lock Screen Enabled
     */
    @JsonProperty("lock_screen_enabled")
    protected boolean lockScreenEnabled;

    /**
     * Lock Camera Enabled
     */
    @JsonProperty("lock_camera_enabled")
    protected boolean lockCameraEnabled;

    /**
     * Settings Enabled
     */
    @JsonProperty("settings_enabled")
    protected boolean settingsEnabled;


    public TerminalDTO(){}

    /**
     *
     * @param identity
     * @param appVersionName
     * @param appVersionCode
     * @param osVersion
     * @param sdkVersion
     * @param manufacturer
     * @param marketName
     * @param model
     * @param codeName
     * @param deviceName
     * @param deviceId
     * @param bedTimeEnabled
     * @param lockScreenEnabled
     * @param lockCameraEnabled
     * @param settingsEnabled
     */
    public TerminalDTO(String identity, String appVersionName, String appVersionCode,
                       String osVersion, String sdkVersion, String manufacturer,
                       String marketName, String model, String codeName,
                       String deviceName, String deviceId, boolean bedTimeEnabled,
                       boolean lockScreenEnabled, boolean lockCameraEnabled,
                       boolean settingsEnabled) {
        this.identity = identity;
        this.appVersionName = appVersionName;
        this.appVersionCode = appVersionCode;
        this.osVersion = osVersion;
        this.sdkVersion = sdkVersion;
        this.manufacturer = manufacturer;
        this.marketName = marketName;
        this.model = model;
        this.codeName = codeName;
        this.deviceName = deviceName;
        this.deviceId = deviceId;
        this.bedTimeEnabled = bedTimeEnabled;
        this.lockScreenEnabled = lockScreenEnabled;
        this.lockCameraEnabled = lockCameraEnabled;
        this.settingsEnabled = settingsEnabled;
    }

    public String getIdentity() {
        return identity;
    }

    public void setIdentity(String identity) {
        this.identity = identity;
    }

    public String getAppVersionName() {
        return appVersionName;
    }

    public void setAppVersionName(String appVersionName) {
        this.appVersionName = appVersionName;
    }

    public String getAppVersionCode() {
        return appVersionCode;
    }

    public void setAppVersionCode(String appVersionCode) {
        this.appVersionCode = appVersionCode;
    }

    public String getOsVersion() {
        return osVersion;
    }

    public void setOsVersion(String osVersion) {
        this.osVersion = osVersion;
    }

    public String getSdkVersion() {
        return sdkVersion;
    }

    public void setSdkVersion(String sdkVersion) {
        this.sdkVersion = sdkVersion;
    }

    public String getManufacturer() {
        return manufacturer;
    }

    public void setManufacturer(String manufacturer) {
        this.manufacturer = manufacturer;
    }

    public String getMarketName() {
        return marketName;
    }

    public void setMarketName(String marketName) {
        this.marketName = marketName;
    }

    public String getModel() {
        return model;
    }

    public void setModel(String model) {
        this.model = model;
    }

    public String getCodeName() {
        return codeName;
    }

    public void setCodeName(String codeName) {
        this.codeName = codeName;
    }

    public String getDeviceName() {
        return deviceName;
    }

    public void setDeviceName(String deviceName) {
        this.deviceName = deviceName;
    }

    public String getDeviceId() {
        return deviceId;
    }

    public void setDeviceId(String deviceId) {
        this.deviceId = deviceId;
    }

    public boolean isBedTimeEnabled() {
        return bedTimeEnabled;
    }

    public void setBedTimeEnabled(boolean bedTimeEnabled) {
        this.bedTimeEnabled = bedTimeEnabled;
    }

    public boolean isLockScreenEnabled() {
        return lockScreenEnabled;
    }

    public void setLockScreenEnabled(boolean lockScreenEnabled) {
        this.lockScreenEnabled = lockScreenEnabled;
    }

    public boolean isLockCameraEnabled() {
        return lockCameraEnabled;
    }

    public void setLockCameraEnabled(boolean lockCameraEnabled) {
        this.lockCameraEnabled = lockCameraEnabled;
    }

    public boolean isSettingsEnabled() {
        return settingsEnabled;
    }

    public void setSettingsEnabled(boolean settingsEnabled) {
        this.settingsEnabled = settingsEnabled;
    }

    @Override
    public String toString() {
        return "TerminalDTO{" +
                "identity='" + identity + '\'' +
                ", appVersionName='" + appVersionName + '\'' +
                ", appVersionCode='" + appVersionCode + '\'' +
                ", osVersion='" + osVersion + '\'' +
                ", sdkVersion='" + sdkVersion + '\'' +
                ", manufacturer='" + manufacturer + '\'' +
                ", marketName='" + marketName + '\'' +
                ", model='" + model + '\'' +
                ", codeName='" + codeName + '\'' +
                ", deviceName='" + deviceName + '\'' +
                ", deviceId='" + deviceId + '\'' +
                ", bedTimeEnabled=" + bedTimeEnabled +
                ", lockScreenEnabled=" + lockScreenEnabled +
                ", lockCameraEnabled=" + lockCameraEnabled +
                ", settingsEnabled=" + settingsEnabled +
                '}';
    }
}
