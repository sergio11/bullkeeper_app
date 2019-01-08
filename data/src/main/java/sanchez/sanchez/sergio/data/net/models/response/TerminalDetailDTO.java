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

    /**
     * Location Permission Enabled
     */
    @JsonProperty("location_permission_enabled")
    private boolean locationPermissionEnabled;

    /**
     * Call History Permission Enabled
     */
    @JsonProperty("call_history_permission_enabled")
    private boolean callsHistoryPermissionEnabled;

    /**
     * Contacts List Permission Enabled
     */
    @JsonProperty("contacts_list_permission_enabled")
    private boolean contactsListPermissionEnabled;

    /**
     * Text Message Permission Enabled
     */
    @JsonProperty("text_message_permission_enabled")
    private boolean textMessagePermissionEnabled;

    /**
     * Storage Permission Enabled
     */
    @JsonProperty("storage_permission_enabled")
    private boolean storagePermissionEnabled;


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
     * @param bedTimeEnabled
     * @param screenEnabled
     * @param cameraEnabled
     * @param settingsEnabled
     * @param totalApps
     * @param totalSms
     * @param totalCalls
     * @param totalContacts
     * @param lastTimeUsed
     * @param screenStatus
     * @param locationPermissionEnabled
     * @param callsHistoryPermissionEnabled
     * @param contactsListPermissionEnabled
     * @param textMessagePermissionEnabled
     * @param storagePermissionEnabled
     */
    public TerminalDetailDTO(String identity, String appVersionName, String appVersionCode, String osVersion, String sdkVersion, String manufacturer, String marketName, String model, String codeName, String deviceName, String deviceId, boolean bedTimeEnabled, boolean screenEnabled, boolean cameraEnabled, boolean settingsEnabled, long totalApps, long totalSms, long totalCalls, long totalContacts, String lastTimeUsed, String screenStatus, boolean locationPermissionEnabled, boolean callsHistoryPermissionEnabled, boolean contactsListPermissionEnabled, boolean textMessagePermissionEnabled, boolean storagePermissionEnabled) {
        super(identity, appVersionName, appVersionCode, osVersion, sdkVersion, manufacturer, marketName, model, codeName, deviceName, deviceId, bedTimeEnabled, screenEnabled, cameraEnabled, settingsEnabled);
        this.totalApps = totalApps;
        this.totalSms = totalSms;
        this.totalCalls = totalCalls;
        this.totalContacts = totalContacts;
        this.lastTimeUsed = lastTimeUsed;
        this.screenStatus = screenStatus;
        this.locationPermissionEnabled = locationPermissionEnabled;
        this.callsHistoryPermissionEnabled = callsHistoryPermissionEnabled;
        this.contactsListPermissionEnabled = contactsListPermissionEnabled;
        this.textMessagePermissionEnabled = textMessagePermissionEnabled;
        this.storagePermissionEnabled = storagePermissionEnabled;
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

    public boolean isLocationPermissionEnabled() {
        return locationPermissionEnabled;
    }

    public void setLocationPermissionEnabled(boolean locationPermissionEnabled) {
        this.locationPermissionEnabled = locationPermissionEnabled;
    }

    public boolean isCallsHistoryPermissionEnabled() {
        return callsHistoryPermissionEnabled;
    }

    public void setCallsHistoryPermissionEnabled(boolean callsHistoryPermissionEnabled) {
        this.callsHistoryPermissionEnabled = callsHistoryPermissionEnabled;
    }

    public boolean isContactsListPermissionEnabled() {
        return contactsListPermissionEnabled;
    }

    public void setContactsListPermissionEnabled(boolean contactsListPermissionEnabled) {
        this.contactsListPermissionEnabled = contactsListPermissionEnabled;
    }

    public boolean isTextMessagePermissionEnabled() {
        return textMessagePermissionEnabled;
    }

    public void setTextMessagePermissionEnabled(boolean textMessagePermissionEnabled) {
        this.textMessagePermissionEnabled = textMessagePermissionEnabled;
    }

    public boolean isStoragePermissionEnabled() {
        return storagePermissionEnabled;
    }

    public void setStoragePermissionEnabled(boolean storagePermissionEnabled) {
        this.storagePermissionEnabled = storagePermissionEnabled;
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
                ", locationPermissionEnabled=" + locationPermissionEnabled +
                ", callsHistoryPermissionEnabled=" + callsHistoryPermissionEnabled +
                ", contactsListPermissionEnabled=" + contactsListPermissionEnabled +
                ", textMessagePermissionEnabled=" + textMessagePermissionEnabled +
                ", storagePermissionEnabled=" + storagePermissionEnabled +
                '}';
    }
}
