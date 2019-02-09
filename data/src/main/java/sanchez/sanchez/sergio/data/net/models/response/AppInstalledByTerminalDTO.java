package sanchez.sanchez.sergio.data.net.models.response;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonProperty;

/**
 * App Installed By Terminal DTO
 */
@JsonIgnoreProperties(ignoreUnknown = true)
public final class AppInstalledByTerminalDTO extends AppInstalledDTO {

    /**
     * Terminal
     */
    @JsonProperty("terminal")
    private TerminalDTO terminal;

    public AppInstalledByTerminalDTO(){}

    /**
     *
     * @param terminal
     */
    public AppInstalledByTerminalDTO(TerminalDTO terminal) {
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
     * @param appRule
     * @param iconEncodedString
     * @param disabled
     * @param terminal
     */
    public AppInstalledByTerminalDTO(String identity, String packageName, String category, String categoryKey, long firstInstallTime, long lastUpdateTime, String versionName, String versionCode, String appName, String appRule, String iconEncodedString, Boolean disabled, TerminalDTO terminal) {
        super(identity, packageName, category, categoryKey, firstInstallTime, lastUpdateTime, versionName, versionCode, appName, appRule, iconEncodedString, disabled);
        this.terminal = terminal;
    }

    public TerminalDTO getTerminal() {
        return terminal;
    }

    public void setTerminal(TerminalDTO terminal) {
        this.terminal = terminal;
    }
}



