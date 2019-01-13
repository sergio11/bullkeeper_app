package sanchez.sanchez.sergio.data.net.models.response;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonProperty;
import java.io.Serializable;

/**
 * App Installed By Terminal DTO
 */
@JsonIgnoreProperties(ignoreUnknown = true)
public final class AppInstalledByTerminalDTO implements Serializable {

    /**
     * Identity
     */
    @JsonProperty("identity")
    private String identity;

    /**
     * Package Name
     */
    @JsonProperty("package_name")
    private String packageName;

    /**
     * First Install Time
     */
    @JsonProperty("first_install_time")
    private long firstInstallTime;

    /**
     * Last Update Time
     */
    @JsonProperty("last_update_time")
    private long lastUpdateTime;

    /**
     * Version Name
     */
    @JsonProperty("version_name")
    private String versionName;

    /**
     * Version Code
     */
    @JsonProperty("version_code")
    private String versionCode;

    /**
     * App Name
     */
    @JsonProperty("app_name")
    private String appName;

    /**
     * App Rule
     */
    @JsonProperty("app_rule")
    private String appRule;

    /**
     * Icon Encoded String
     */
    @JsonProperty("icon_encoded_string")
    private String iconEncodedString;

    /**
     * Disabled
     */
    @JsonProperty("disabled")
    private Boolean disabled;

    /**
     * Terminal
     */
    @JsonProperty("terminal")
    private TerminalDTO terminal;

    public AppInstalledByTerminalDTO(){}

    /**
     *
     * @param identity
     * @param packageName
     * @param firstInstallTime
     * @param lastUpdateTime
     * @param versionName
     * @param versionCode
     * @param appName
     * @param appRule
     * @param iconEncodedString
     * @param disabled
     * @param terminal
     */
    public AppInstalledByTerminalDTO(String identity, String packageName, long firstInstallTime, long lastUpdateTime,
                                     String versionName, String versionCode, String appName,
                                     String appRule, String iconEncodedString, Boolean disabled,
                                     TerminalDTO terminal) {
        this.identity = identity;
        this.packageName = packageName;
        this.firstInstallTime = firstInstallTime;
        this.lastUpdateTime = lastUpdateTime;
        this.versionName = versionName;
        this.versionCode = versionCode;
        this.appName = appName;
        this.appRule = appRule;
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

    public String getAppRule() {
        return appRule;
    }

    public void setAppRule(String appRule) {
        this.appRule = appRule;
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

    public TerminalDTO getTerminal() {
        return terminal;
    }

    public void setTerminal(TerminalDTO terminal) {
        this.terminal = terminal;
    }

    @Override
    public String toString() {
        return "AppInstalledDTO{" +
                "identity='" + identity + '\'' +
                ", packageName='" + packageName + '\'' +
                ", firstInstallTime=" + firstInstallTime +
                ", lastUpdateTime=" + lastUpdateTime +
                ", versionName='" + versionName + '\'' +
                ", versionCode='" + versionCode + '\'' +
                ", appName='" + appName + '\'' +
                ", appRule='" + appRule + '\'' +
                ", iconEncodedString='" + iconEncodedString + '\'' +
                '}';
    }
}



