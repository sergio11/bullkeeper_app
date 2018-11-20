package sanchez.sanchez.sergio.domain.models;

/**
 * Terminal Detail Entity
 */
public final class TerminalDetailEntity extends TerminalEntity {

    /**
     * Total Apps
     */
    private long totalApps;

    /**
     * Last Time Used
     */
    private String lastTimeUsed;

    public TerminalDetailEntity(){}

    public TerminalDetailEntity(long totalApps, String lastTimeUsed) {
        this.totalApps = totalApps;
        this.lastTimeUsed = lastTimeUsed;
    }

    public TerminalDetailEntity(String identity, String appVersionCode, String appVersionName, String codeName, String deviceName, String manufacturer, String marketName, String model, String osVersion, String sdkVersion, long totalApps, String lastTimeUsed) {
        super(identity, appVersionCode, appVersionName, codeName, deviceName, manufacturer, marketName, model, osVersion, sdkVersion);
        this.totalApps = totalApps;
        this.lastTimeUsed = lastTimeUsed;
    }

    public long getTotalApps() {
        return totalApps;
    }

    public void setTotalApps(long totalApps) {
        this.totalApps = totalApps;
    }

    public String getLastTimeUsed() {
        return lastTimeUsed;
    }

    public void setLastTimeUsed(String lastTimeUsed) {
        this.lastTimeUsed = lastTimeUsed;
    }
}
