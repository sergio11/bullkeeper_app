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
     */
    public TerminalEntity(String identity, String appVersionCode, String appVersionName, String codeName, String deviceName, String manufacturer,
                          String marketName, String model, String osVersion, String sdkVersion) {
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
                '}';
    }
}
