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
     * Installed
     */
    @JsonProperty("installed")
    protected boolean installed;


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
     * Screen Enabled
     */
    @JsonProperty("screen_enabled")
    protected boolean screenEnabled;

    /**
     * Camera Enabled
     */
    @JsonProperty("camera_enabled")
    protected boolean cameraEnabled;

    /**
     * Settings Enabled
     */
    @JsonProperty("settings_enabled")
    protected boolean settingsEnabled;

    /**
     * Battery Level
     */
    @JsonProperty("battery_level")
    protected int batteryLevel;

    /**
     * Is Battery Charging
     */
    @JsonProperty("is_battery_charging")
    protected boolean isBatteryCharging;

    /**
     * Status
     */
    @JsonProperty("status")
    protected String status;

    /**
     * HeartBeat
     */
    @JsonProperty("heartbeat")
    protected TerminalHeartbeatDTO heartbeat;


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
     * @param installed
     * @param deviceName
     * @param deviceId
     * @param bedTimeEnabled
     * @param screenEnabled
     * @param cameraEnabled
     * @param settingsEnabled
     * @param batteryLevel
     * @param isBatteryCharging
     * @param status
     * @param heartbeat
     */
    public TerminalDTO(String identity, String appVersionName, String appVersionCode,
                       String osVersion, String sdkVersion, String manufacturer,
                       String marketName, String model, String codeName,
                       boolean installed, String deviceName, String deviceId, boolean bedTimeEnabled,
                       boolean screenEnabled, boolean cameraEnabled,
                       boolean settingsEnabled, final int batteryLevel,
                       final boolean isBatteryCharging, final String status,
                       final TerminalHeartbeatDTO heartbeat) {
        this.identity = identity;
        this.appVersionName = appVersionName;
        this.appVersionCode = appVersionCode;
        this.osVersion = osVersion;
        this.sdkVersion = sdkVersion;
        this.manufacturer = manufacturer;
        this.marketName = marketName;
        this.model = model;
        this.codeName = codeName;
        this.installed = installed;
        this.deviceName = deviceName;
        this.deviceId = deviceId;
        this.bedTimeEnabled = bedTimeEnabled;
        this.screenEnabled = screenEnabled;
        this.cameraEnabled = cameraEnabled;
        this.settingsEnabled = settingsEnabled;
        this.batteryLevel = batteryLevel;
        this.isBatteryCharging = isBatteryCharging;
        this.status = status;
        this.heartbeat = heartbeat;
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

    public boolean isScreenEnabled() {
        return screenEnabled;
    }

    public void setScreenEnabled(boolean screenEnabled) {
        this.screenEnabled = screenEnabled;
    }

    public boolean isCameraEnabled() {
        return cameraEnabled;
    }

    public void setCameraEnabled(boolean cameraEnabled) {
        this.cameraEnabled = cameraEnabled;
    }

    public boolean isSettingsEnabled() {
        return settingsEnabled;
    }

    public void setSettingsEnabled(boolean settingsEnabled) {
        this.settingsEnabled = settingsEnabled;
    }

    public int getBatteryLevel() {
        return batteryLevel;
    }

    public void setBatteryLevel(int batteryLevel) {
        this.batteryLevel = batteryLevel;
    }

    public boolean isBatteryCharging() {
        return isBatteryCharging;
    }

    public void setBatteryCharging(boolean batteryCharging) {
        isBatteryCharging = batteryCharging;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public TerminalHeartbeatDTO getHeartbeat() {
        return heartbeat;
    }

    public void setHeartbeat(TerminalHeartbeatDTO heartbeat) {
        this.heartbeat = heartbeat;
    }

    public boolean isInstalled() {
        return installed;
    }

    public void setInstalled(boolean installed) {
        this.installed = installed;
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
                ", screenEnabled=" + screenEnabled +
                ", cameraEnabled=" + cameraEnabled +
                ", settingsEnabled=" + settingsEnabled +
                ", batteryLevel=" + batteryLevel +
                ", isBatteryCharging=" + isBatteryCharging +
                ", status='" + status + '\'' +
                ", heartbeat=" + heartbeat +
                '}';
    }
}
