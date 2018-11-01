package sanchez.sanchez.sergio.domain.models;

import java.io.Serializable;

/**
 * App Installed Entity
 */
public class AppInstalledEntity implements Serializable {

    private String identity;
    private String packageName;
    private long firstInstallTime;
    private long lastUpdateTime;
    private String versionName;
    private String versionCode;
    private String appName;
    private AppRuleEnum appRuleEnum = AppRuleEnum.NEVER_ALLOWED;

    public AppInstalledEntity(){}

    /**
     * @param identity
     * @param packageName
     * @param firstInstallTime
     * @param lastUpdateTime
     * @param versionName
     * @param versionCode
     * @param appName
     * @param appRuleEnum
     */
    public AppInstalledEntity(final String identity, final String packageName, final long firstInstallTime,
                              final long lastUpdateTime, final String versionName, final String versionCode,
                              final String appName, final AppRuleEnum appRuleEnum) {
        this.identity = identity;
        this.packageName = packageName;
        this.firstInstallTime = firstInstallTime;
        this.lastUpdateTime = lastUpdateTime;
        this.versionName = versionName;
        this.versionCode = versionCode;
        this.appName = appName;
        this.appRuleEnum = appRuleEnum;
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
}
