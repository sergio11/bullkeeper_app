package sanchez.sanchez.sergio.data.net.models.response;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonProperty;
import java.io.Serializable;

import sanchez.sanchez.sergio.domain.models.AppModelCategoryEnum;


/**
 * App Installed DTO
 */
@JsonIgnoreProperties(ignoreUnknown = true)
public class AppInstalledDTO implements Serializable {

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
     * Category
     */
    @JsonProperty("category")
    private String category;

    /**
     * Category Key
     */
    @JsonProperty("cat_key")
    private String categoryKey;

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

    public AppInstalledDTO(){}

    /**
     *
     * @param identity
     * @param packageName
     * @param category
     * @param categoryKey
     * @param firstInstallTime
     * @param lastUpdateTime
     * @param versionName
     * @param versionCode
     * @param appName
     * @param appRule
     * @param iconEncodedString
     * @param disabled
     */
    public AppInstalledDTO(String identity, String packageName, String category,
                           String categoryKey, long firstInstallTime, long lastUpdateTime,
                           String versionName, String versionCode, String appName,
                           String appRule, String iconEncodedString, Boolean disabled) {
        this.identity = identity;
        this.packageName = packageName;
        this.category = category;
        this.categoryKey = categoryKey;
        this.firstInstallTime = firstInstallTime;
        this.lastUpdateTime = lastUpdateTime;
        this.versionName = versionName;
        this.versionCode = versionCode;
        this.appName = appName;
        this.appRule = appRule;
        this.iconEncodedString = iconEncodedString;
        this.disabled = disabled;
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

    public String getCategory() {
        return category;
    }

    public void setCategory(String category) {
        this.category = category;
    }

    public String getCategoryKey() {
        return categoryKey;
    }

    public void setCategoryKey(String categoryKey) {
        this.categoryKey = categoryKey;
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



