package sanchez.sanchez.sergio.data.net.models.response;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonProperty;
import java.io.Serializable;

/**
 * App Allowed By Scheduled DTO
 */
@JsonIgnoreProperties(ignoreUnknown = true)
public class AppAllowedByScheduledDTO implements Serializable {

    /**
     * App Installed
     */
    @JsonProperty("app")
    private AppInstalledDTO app;

    /**
     * Terminal
     */
    @JsonProperty("terminal")
    private TerminalDTO terminal;

    public AppAllowedByScheduledDTO(){}

    /**
     *
     * @param app
     * @param terminal
     */
    public AppAllowedByScheduledDTO(final AppInstalledDTO app, final TerminalDTO terminal) {
        this.app = app;
        this.terminal = terminal;
    }

    public AppInstalledDTO getApp() {
        return app;
    }

    public void setApp(AppInstalledDTO app) {
        this.app = app;
    }

    public TerminalDTO getTerminal() {
        return terminal;
    }

    public void setTerminal(TerminalDTO terminal) {
        this.terminal = terminal;
    }

    @Override
    public String toString() {
        return "AppAllowedByScheduledDTO{" +
                "app=" + app +
                ", terminal=" + terminal +
                '}';
    }
}