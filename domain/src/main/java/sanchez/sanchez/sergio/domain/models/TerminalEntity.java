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
    private String marketName;
    private String model;
    private String osVersion;
    private String sdkVersion;
    private boolean bedTimeEnabled;
    private boolean screenEnabled;
    private boolean cameraEnabled;
    private boolean settingsEnabled;

    public TerminalEntity(){}

    /**
     *
     * @param identity
     * @param appVersionCode
     * @param appVersionName
     * @param codeName
     * @param deviceName
     * @param manufacturer
     * @param marketName
     * @param model
     * @param osVersion
     * @param sdkVersion
     * @param bedTimeEnabled
     * @param screenEnabled
     * @param cameraEnabled
     * @param settingsEnabled
     */
    public TerminalEntity(String identity, String appVersionCode, String appVersionName,
                          String codeName, String deviceName, String manufacturer,
                          String marketName, String model, String osVersion,
                          String sdkVersion, boolean bedTimeEnabled,
                          boolean screenEnabled, boolean cameraEnabled,
                          boolean settingsEnabled) {
        this.identity = identity;
        this.appVersionCode = appVersionCode;
        this.appVersionName = appVersionName;
        this.codeName = codeName;
        this.deviceName = deviceName;
        this.manufacturer = manufacturer;
        this.marketName = marketName;
        this.model = model;
        this.osVersion = osVersion;
        this.sdkVersion = sdkVersion;
        this.bedTimeEnabled = bedTimeEnabled;
        this.screenEnabled = screenEnabled;
        this.cameraEnabled = cameraEnabled;
        this.settingsEnabled = settingsEnabled;
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

    @Override
    public String toString() {
        return "TerminalEntity{" +
                "identity='" + identity + '\'' +
                ", appVersionCode='" + appVersionCode + '\'' +
                ", appVersionName='" + appVersionName + '\'' +
                ", codeName='" + codeName + '\'' +
                ", deviceName='" + deviceName + '\'' +
                ", manufacturer='" + manufacturer + '\'' +
                ", marketName='" + marketName + '\'' +
                ", model='" + model + '\'' +
                ", osVersion='" + osVersion + '\'' +
                ", sdkVersion='" + sdkVersion + '\'' +
                ", bedTimeEnabled=" + bedTimeEnabled +
                ", screenEnabled=" + screenEnabled +
                ", cameraEnabled=" + cameraEnabled +
                ", settingsEnabled=" + settingsEnabled +
                '}';
    }
}
