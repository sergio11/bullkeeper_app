package sanchez.sanchez.sergio.data.net.models.response;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonProperty;


/**
 * Terminal Detail DTO
 */
@JsonIgnoreProperties(ignoreUnknown = true)
public final class TerminalDetailDTO extends TerminalDTO {

    /**
     * Total Apps
     */
    @JsonProperty("total_apps")
    private long totalApps;

    /**
     * Last Time Used
     */
    @JsonProperty("last_time_used")
    private String lastTimeUsed;


    public TerminalDetailDTO(){
        super();
    }

    public TerminalDetailDTO(String identity, String appVersionName, String appVersionCode, String osVersion, String sdkVersion,
                             String manufacturer, String marketName, String model, String codeName, String deviceName,
                             String deviceId, long totalApps, String lastTimeUsed) {
        super(identity, appVersionName, appVersionCode, osVersion, sdkVersion, manufacturer, marketName, model, codeName, deviceName, deviceId);
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

    @Override
    public String toString() {
        return "TerminalDetailDTO{" +
                "totalApps=" + totalApps +
                ", lastTimeUsed='" + lastTimeUsed + '\'' +
                ", identity='" + identity + '\'' +
                ", appVersionName='" + appVersionName + '\'' +
                ", appVersionCode='" + appVersionCode + '\'' +
                ", osVersion='" + osVersion + '\'' +
                ", sdkVersion='" + sdkVersion + '\'' +
                ", manufacturer='" + manufacturer + '\'' +
                ", marketName='" + marketName + '\'' +
                ", model='" + model + '\'' +
                ", codeName='" + codeName + '\'' +
                ", deviceName='" + deviceName + '\'' +
                ", deviceId='" + deviceId + '\'' +
                '}';
    }
}
