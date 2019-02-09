package sanchez.sanchez.sergio.domain.models;


/**
 * App Installed By Terminal Entity
 */
public class AppInstalledByTerminalEntity extends AppInstalledEntity {

    /**
     * Terminal
     */
    private TerminalEntity terminal;

    public AppInstalledByTerminalEntity(){}

    /**
     *
     * @param terminal
     */
    public AppInstalledByTerminalEntity(TerminalEntity terminal) {
        this.terminal = terminal;
    }

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
     * @param appRuleEnum
     * @param iconEncodedString
     * @param disabled
     * @param terminal
     */
    public AppInstalledByTerminalEntity(String identity, String packageName, String category, AppModelCategoryEnum categoryKey, long firstInstallTime, long lastUpdateTime, String versionName, String versionCode, String appName, AppRuleEnum appRuleEnum, String iconEncodedString, Boolean disabled, TerminalEntity terminal) {
        super(identity, packageName, category, categoryKey, firstInstallTime, lastUpdateTime, versionName, versionCode, appName, appRuleEnum, iconEncodedString, disabled);
        this.terminal = terminal;
    }

    public TerminalEntity getTerminal() {
        return terminal;
    }

    public void setTerminal(TerminalEntity terminal) {
        this.terminal = terminal;
    }
}
