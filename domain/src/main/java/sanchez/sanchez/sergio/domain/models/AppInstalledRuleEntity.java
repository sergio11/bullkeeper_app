package sanchez.sanchez.sergio.domain.models;

import java.io.Serializable;

/**
 * App Installed Rule Entity
 */
public final class AppInstalledRuleEntity implements Serializable {

    private String identity;
    private AppRuleEnum appRuleEnum;

    /**
     * App Installed Rule Entity
     * @param identity
     * @param appRuleEnum
     */
    public AppInstalledRuleEntity(final String identity, final AppRuleEnum appRuleEnum) {
        this.identity = identity;
        this.appRuleEnum = appRuleEnum;
    }

    public AppInstalledRuleEntity(){}

    public String getIdentity() {
        return identity;
    }

    public void setIdentity(String identity) {
        this.identity = identity;
    }

    public AppRuleEnum getAppRuleEnum() {
        return appRuleEnum;
    }

    public void setAppRuleEnum(AppRuleEnum appRuleEnum) {
        this.appRuleEnum = appRuleEnum;
    }

    @Override
    public String toString() {
        return "AppInstalledRuleEntity{" +
                "identity='" + identity + '\'' +
                ", appRuleEnum=" + appRuleEnum +
                '}';
    }
}
