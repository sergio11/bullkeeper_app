package sanchez.sanchez.sergio.domain.models;

import java.io.Serializable;

/**
 * App Allowed By Scheduled Entity
 */
public final class AppAllowedByScheduledEntity implements Serializable {

    /**
     * App Installed
     */
    private AppInstalledEntity app;

    /**
     * Terminal
     */
    private TerminalEntity terminal;


    public AppAllowedByScheduledEntity(){}

    /**
     *
     * @param app
     * @param terminal
     */
    public AppAllowedByScheduledEntity(final AppInstalledEntity app, final TerminalEntity terminal) {
        this.app = app;
        this.terminal = terminal;
    }

    public AppInstalledEntity getApp() {
        return app;
    }

    public void setApp(AppInstalledEntity app) {
        this.app = app;
    }

    public TerminalEntity getTerminal() {
        return terminal;
    }

    public void setTerminal(TerminalEntity terminal) {
        this.terminal = terminal;
    }

    @Override
    public String toString() {
        return "AppAllowedByScheduledEntity{" +
                "app=" + app +
                ", terminal=" + terminal +
                '}';
    }
}
