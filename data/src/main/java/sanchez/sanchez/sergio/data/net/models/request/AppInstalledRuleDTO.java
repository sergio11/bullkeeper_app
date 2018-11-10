package sanchez.sanchez.sergio.data.net.models.request;

import java.io.Serializable;

/**
 * App Installed Rule DTO
 */
public final class AppInstalledRuleDTO implements Serializable {

    /**
     * Identity
     */
    private String identity;

    /**
     * App Rule Enum
     */
    private String appRule;

    public AppInstalledRuleDTO(){}

    /**
     * App Installed Rule DTO
     * @param identity
     * @param appRule
     */
    public AppInstalledRuleDTO(final String identity, final String appRule) {
        this.identity = identity;
        this.appRule = appRule;
    }

    public String getIdentity() {
        return identity;
    }

    public void setIdentity(String identity) {
        this.identity = identity;
    }

    public String getAppRule() {
        return appRule;
    }

    public void setAppRule(String appRule) {
        this.appRule = appRule;
    }

    @Override
    public String toString() {
        return "AppInstalledRuleDTO{" +
                "identity='" + identity + '\'' +
                ", appRule='" + appRule + '\'' +
                '}';
    }
}
