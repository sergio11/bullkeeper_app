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
     * Total SMS
     */
    @JsonProperty("total_sms")
    private long totalSms;

    /**
     * Total Calls
     */
    @JsonProperty("total_calls")
    private long totalCalls;

    /**
     * Total Contacts
     */
    @JsonProperty("total_contacts")
    private long totalContacts;

    /**
     * Last Time Used
     */
    @JsonProperty("last_time_used")
    private String lastTimeUsed;

    /**
     * Screen Status
     */
    @JsonProperty("screen_status")
    private String screenStatus;


    public TerminalDetailDTO(){
        super();
    }

    /**
     *
     * @param identity
     * @param appVersionName
     * @param appVersionCode
     * @param osVersion
     * @param sdkVersion
     * @param manufacturer
     * @param marketName
     * @param model
     * @param codeName
     * @param deviceName
     * @param deviceId
     * @param totalApps
     * @param totalSms
     * @param totalCalls
     * @param totalContacts
     * @param lastTimeUsed
     * @param screenStatus
     */
    public TerminalDetailDTO(String identity, String appVersionName, String appVersionCode,
                             String osVersion, String sdkVersion, String manufacturer,
                             String marketName, String model, String codeName,
                             String deviceName, String deviceId, long totalApps,
                             long totalSms, long totalCalls, long totalContacts,
                             String lastTimeUsed, String screenStatus) {
        super(identity, appVersionName, appVersionCode, osVersion, sdkVersion, manufacturer, marketName, model, codeName, deviceName, deviceId);
        this.totalApps = totalApps;
        this.totalSms = totalSms;
        this.totalCalls = totalCalls;
        this.totalContacts = totalContacts;
        this.lastTimeUsed = lastTimeUsed;
        this.screenStatus = screenStatus;
    }

    public long getTotalApps() {
        return totalApps;
    }

    public void setTotalApps(long totalApps) {
        this.totalApps = totalApps;
    }

    public long getTotalSms() {
        return totalSms;
    }

    public void setTotalSms(long totalSms) {
        this.totalSms = totalSms;
    }

    public long getTotalCalls() {
        return totalCalls;
    }

    public void setTotalCalls(long totalCalls) {
        this.totalCalls = totalCalls;
    }

    public long getTotalContacts() {
        return totalContacts;
    }

    public void setTotalContacts(long totalContacts) {
        this.totalContacts = totalContacts;
    }

    public String getLastTimeUsed() {
        return lastTimeUsed;
    }

    public void setLastTimeUsed(String lastTimeUsed) {
        this.lastTimeUsed = lastTimeUsed;
    }

    public String getScreenStatus() {
        return screenStatus;
    }

    public void setScreenStatus(String screenStatus) {
        this.screenStatus = screenStatus;
    }

    @Override
    public String toString() {
        return "TerminalDetailDTO{" +
                "totalApps=" + totalApps +
                ", totalSms=" + totalSms +
                ", totalCalls=" + totalCalls +
                ", totalContacts=" + totalContacts +
                ", lastTimeUsed='" + lastTimeUsed + '\'' +
                ", screenStatus='" + screenStatus + '\'' +
                '}';
    }
}
