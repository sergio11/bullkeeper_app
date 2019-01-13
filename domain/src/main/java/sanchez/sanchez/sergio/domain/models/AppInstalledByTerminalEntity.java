package sanchez.sanchez.sergio.domain.models;

import java.io.Serializable;

/**
 * App Installed By Terminal Entity
 */
public class AppInstalledByTerminalEntity implements Serializable {

    /**
     * Identity
     */
    private String identity;

    /**
     * Package Name
     */
    private String packageName;

    /**
     * First Install Time
     */
    private long firstInstallTime;

    /**
     * Last Update Time
     */
    private long lastUpdateTime;

    /**
     * Version Name
     */
    private String versionName;

    /**
     * Version Code
     */
    private String versionCode;

    /**
     * App Name
     */
    private String appName;

    /**
     * App Rule Enum
     */
    private AppRuleEnum appRuleEnum = AppRuleEnum.NEVER_ALLOWED;

    /**
     * Icon Encoded String
     */
    private String iconEncodedString;

    /**
     * Disabled
     */
    private Boolean disabled = false;

    /**
     * Terminal
     */
    private TerminalEntity terminal;

    public AppInstalledByTerminalEntity(){}

    /**
     * @param identity
     * @param packageName
     * @param firstInstallTime
     * @param lastUpdateTime
     * @param versionName
     * @param versionCode
     * @param appName
     * @param appRuleEnum
     * @param iconEncodedString
     * @param disabled
     * @param terminal
     */
    public AppInstalledByTerminalEntity(final String identity, final String packageName, final long firstInstallTime,
                                        final long lastUpdateTime, final String versionName, final String versionCode,
                                        final String appName, final AppRuleEnum appRuleEnum, final String iconEncodedString,
                                        final Boolean disabled, TerminalEntity terminal) {
        this.identity = identity;
        this.packageName = packageName;
        this.firstInstallTime = firstInstallTime;
        this.lastUpdateTime = lastUpdateTime;
        this.versionName = versionName;
        this.versionCode = versionCode;
        this.appName = appName;
        this.appRuleEnum = appRuleEnum;
        this.iconEncodedString = iconEncodedString;
        this.disabled = disabled;
        this.terminal = terminal;
    }

    public String getIdentity() {
        return identity;
    }

    public void setIdentity(String identity) {
        this.identity = identity;
    }

    public String getPackageName() {
        return packageName;
    }

    public void setPackageName(String packageName) {
        this.packageName = packageName;
    }

    public long getFirstInstallTime() {
        return firstInstallTime;
    }

    public void setFirstInstallTime(long firstInstallTime) {
        this.firstInstallTime = firstInstallTime;
    }

    public long getLastUpdateTime() {
        return lastUpdateTime;
    }

    public void setLastUpdateTime(long lastUpdateTime) {
        this.lastUpdateTime = lastUpdateTime;
    }

    public String getVersionName() {
        return versionName;
    }

    public void setVersionName(String versionName) {
        this.versionName = versionName;
    }

    public String getVersionCode() {
        return versionCode;
    }

    public void setVersionCode(String versionCode) {
        this.versionCode = versionCode;
    }

    public String getAppName() {
        return appName;
    }

    public void setAppName(String appName) {
        this.appName = appName;
    }

    public AppRuleEnum getAppRuleEnum() {
        return appRuleEnum;
    }

    public void setAppRuleEnum(AppRuleEnum appRuleEnum) {
        this.appRuleEnum = appRuleEnum;
    }

    public String getIconEncodedString() {
        return iconEncodedString;
    }

    public void setIconEncodedString(String iconEncodedString) {
        this.iconEncodedString = iconEncodedString;
    }

    public Boolean getDisabled() {
        return disabled;
    }

    public void setDisabled(Boolean disabled) {
        this.disabled = disabled;
    }

    public TerminalEntity getTerminal() {
        return terminal;
    }

    public void setTerminal(TerminalEntity terminal) {
        this.terminal = terminal;
    }

    @Override
    public String toString() {
        return "AppInstalledByTerminalEntity{" +
                "identity='" + identity + '\'' +
                ", packageName='" + packageName + '\'' +
                ", firstInstallTime=" + firstInstallTime +
                ", lastUpdateTime=" + lastUpdateTime +
                ", versionName='" + versionName + '\'' +
                ", versionCode='" + versionCode + '\'' +
                ", appName='" + appName + '\'' +
                ", appRuleEnum=" + appRuleEnum +
                ", iconEncodedString='" + iconEncodedString + '\'' +
                ", disabled=" + disabled +
                ", terminal=" + terminal +
                '}';
    }
}
