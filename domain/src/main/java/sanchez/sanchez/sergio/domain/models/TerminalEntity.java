package sanchez.sanchez.sergio.domain.models;

import java.io.Serializable;

/**
 * Terminal Entity
 */
public class TerminalEntity implements Serializable {

    private String identity;
    private String appVersionCode;
    private String appVersionName;
    private String codeName;
    private String deviceName;
    private String manufacturer;
    private boolean installed;
    private String marketName;
    private String model;
    private String osVersion;
    private String sdkVersion;
    private boolean bedTimeEnabled;
    private boolean screenEnabled;
    private boolean cameraEnabled;
    private boolean settingsEnabled;
    private int batteryLevel;
    private boolean isBatteryCharging;
    private TerminalStatusEnum status;
    private TerminalHeartbeatEntity terminalHeartbeatEntity;

    public TerminalEntity(){}

    /**
     *
     * @param identity
     * @param appVersionCode
     * @param appVersionName
     * @param codeName
     * @param deviceName
     * @param manufacturer
     * @param installed
     * @param marketName
     * @param model
     * @param osVersion
     * @param sdkVersion
     * @param bedTimeEnabled
     * @param screenEnabled
     * @param cameraEnabled
     * @param settingsEnabled
     * @param terminalHeartbeatEntity
     */
    public TerminalEntity(String identity, String appVersionCode, String appVersionName,
                          String codeName, String deviceName, String manufacturer,
                          boolean installed, String marketName, String model, String osVersion,
                          String sdkVersion, boolean bedTimeEnabled,
                          boolean screenEnabled, boolean cameraEnabled,
                          boolean settingsEnabled, int batteryLevel,
                          boolean isBatteryCharging, TerminalStatusEnum status,
                          TerminalHeartbeatEntity terminalHeartbeatEntity) {
        this.identity = identity;
        this.appVersionCode = appVersionCode;
        this.appVersionName = appVersionName;
        this.codeName = codeName;
        this.deviceName = deviceName;
        this.manufacturer = manufacturer;
        this.installed = installed;
        this.marketName = marketName;
        this.model = model;
        this.osVersion = osVersion;
        this.sdkVersion = sdkVersion;
        this.bedTimeEnabled = bedTimeEnabled;
        this.screenEnabled = screenEnabled;
        this.cameraEnabled = cameraEnabled;
        this.settingsEnabled = settingsEnabled;
        this.batteryLevel = batteryLevel;
        this.isBatteryCharging = isBatteryCharging;
        this.status = status;
        this.terminalHeartbeatEntity = terminalHeartbeatEntity;
    }

    public String getIdentity() {
        return identity;
    }

    public void setIdentity(String identity) {
        this.identity = identity;
    }

    public String getAppVersionCode() {
        return appVersionCode;
    }

    public void setAppVersionCode(String appVersionCode) {
        this.appVersionCode = appVersionCode;
    }

    public String getAppVersionName() {
        return appVersionName;
    }

    public void setAppVersionName(String appVersionName) {
        this.appVersionName = appVersionName;
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

    public String getManufacturer() {
        return manufacturer;
    }

    public void setManufacturer(String manufacturer) {
        this.manufacturer = manufacturer;
    }

    public boolean isInstalled() {
        return installed;
    }

    public void setInstalled(boolean installed) {
        this.installed = installed;
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

    public TerminalStatusEnum getStatus() {
        return status;
    }

    public void setStatus(TerminalStatusEnum status) {
        this.status = status;
    }

    public TerminalHeartbeatEntity getTerminalHeartbeatEntity() {
        return terminalHeartbeatEntity;
    }

    public void setTerminalHeartbeatEntity(TerminalHeartbeatEntity terminalHeartbeatEntity) {
        this.terminalHeartbeatEntity = terminalHeartbeatEntity;
    }

    @Override
    public String toString() {
        return "TerminalEntity{" +
                "identity='" + identity + '\'' +
                ", appVersionCode='" + appVersionCode + '\'' +
                ", appVersionName='" + appVersionName + '\'' +
                ", codeName='" + codeName + '\'' +
                ", deviceName='" + deviceName + '\'' +
                ", manufacturer='" + manufacturer + '\'' +
                ", installed=" + installed +
                ", marketName='" + marketName + '\'' +
                ", model='" + model + '\'' +
                ", osVersion='" + osVersion + '\'' +
                ", sdkVersion='" + sdkVersion + '\'' +
                ", bedTimeEnabled=" + bedTimeEnabled +
                ", screenEnabled=" + screenEnabled +
                ", cameraEnabled=" + cameraEnabled +
                ", settingsEnabled=" + settingsEnabled +
                ", batteryLevel=" + batteryLevel +
                ", isBatteryCharging=" + isBatteryCharging +
                ", status=" + status +
                ", terminalHeartbeatEntity=" + terminalHeartbeatEntity +
                '}';
    }
}
