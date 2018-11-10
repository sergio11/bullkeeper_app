package sanchez.sanchez.sergio.domain.models;

import java.io.Serializable;

/**
 * Terminal Entity
 */
public final class TerminalEntity implements Serializable {


    private String appVersionCode;
    private String appVersionName;
    private String codeName;
    private String deviceName;
    private String manufacturer;
    private String marketName;
    private String model;
    private String osVersion;
    private String sdkVersion;
    private SonEntity sonEntity;

    public TerminalEntity(){}

    public TerminalEntity(String appVersionCode, String appVersionName, String codeName, String deviceName, String manufacturer, String marketName, String model, String osVersion, String sdkVersion, SonEntity sonEntity) {
        this.appVersionCode = appVersionCode;
        this.appVersionName = appVersionName;
        this.codeName = codeName;
        this.deviceName = deviceName;
        this.manufacturer = manufacturer;
        this.marketName = marketName;
        this.model = model;
        this.osVersion = osVersion;
        this.sdkVersion = sdkVersion;
        this.sonEntity = sonEntity;
    }

    public String getAppVersionCode() {
        return appVersionCode;
    }

    public String getAppVersionName() {
        return appVersionName;
    }

    public String getCodeName() {
        return codeName;
    }

    public String getDeviceName() {
        return deviceName;
    }

    public String getManufacturer() {
        return manufacturer;
    }

    public String getMarketName() {
        return marketName;
    }

    public String getModel() {
        return model;
    }

    public String getOsVersion() {
        return osVersion;
    }

    public String getSdkVersion() {
        return sdkVersion;
    }

    public SonEntity getSonEntity() {
        return sonEntity;
    }
}
