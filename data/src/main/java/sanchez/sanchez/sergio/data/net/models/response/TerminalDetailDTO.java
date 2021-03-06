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

    /**
     * Usage Stats Allowed
     */
    @JsonProperty("usage_stats_allowed")
    private boolean usageStatsAllowed;

    /**
     * Admin Access Allowed
     */
    @JsonProperty("admin_access_allowed")
    private boolean adminAccessAllowed;

    /**
     * Apps Overlay Enabled
     */
    @JsonProperty("apps_overlay_enabled")
    private boolean appsOverlayEnabled;

    /**
     * High Accuraccy Location Enabled
     */
    @JsonProperty("high_accuraccy_location_enabled")
    private boolean highAccuraccyLocationEnabled;


    public TerminalDetailDTO(){
        super();
    }

    public TerminalDetailDTO(long totalApps, long totalSms, long totalCalls, long totalContacts, boolean locationPermissionEnabled, boolean callsHistoryPermissionEnabled, boolean contactsListPermissionEnabled, boolean textMessagePermissionEnabled, boolean storagePermissionEnabled, boolean usageStatsAllowed, boolean adminAccessAllowed, boolean appsOverlayEnabled, boolean highAccuraccyLocationEnabled) {
        this.totalApps = totalApps;
        this.totalSms = totalSms;
        this.totalCalls = totalCalls;
        this.totalContacts = totalContacts;
        this.locationPermissionEnabled = locationPermissionEnabled;
        this.callsHistoryPermissionEnabled = callsHistoryPermissionEnabled;
        this.contactsListPermissionEnabled = contactsListPermissionEnabled;
        this.textMessagePermissionEnabled = textMessagePermissionEnabled;
        this.storagePermissionEnabled = storagePermissionEnabled;
        this.usageStatsAllowed = usageStatsAllowed;
        this.adminAccessAllowed = adminAccessAllowed;
        this.appsOverlayEnabled = appsOverlayEnabled;
        this.highAccuraccyLocationEnabled = highAccuraccyLocationEnabled;
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

    public boolean isUsageStatsAllowed() {
        return usageStatsAllowed;
    }

    public void setUsageStatsAllowed(boolean usageStatsAllowed) {
        this.usageStatsAllowed = usageStatsAllowed;
    }

    public boolean isAdminAccessAllowed() {
        return adminAccessAllowed;
    }

    public void setAdminAccessAllowed(boolean adminAccessAllowed) {
        this.adminAccessAllowed = adminAccessAllowed;
    }

    public boolean isAppsOverlayEnabled() {
        return appsOverlayEnabled;
    }

    public void setAppsOverlayEnabled(boolean appsOverlayEnabled) {
        this.appsOverlayEnabled = appsOverlayEnabled;
    }

    public boolean isHighAccuraccyLocationEnabled() {
        return highAccuraccyLocationEnabled;
    }

    public void setHighAccuraccyLocationEnabled(boolean highAccuraccyLocationEnabled) {
        this.highAccuraccyLocationEnabled = highAccuraccyLocationEnabled;
    }

    @Override
    public String toString() {
        return "TerminalDetailDTO{" +
                "totalApps=" + totalApps +
                ", totalSms=" + totalSms +
                ", totalCalls=" + totalCalls +
                ", totalContacts=" + totalContacts +
                ", locationPermissionEnabled=" + locationPermissionEnabled +
                ", callsHistoryPermissionEnabled=" + callsHistoryPermissionEnabled +
                ", contactsListPermissionEnabled=" + contactsListPermissionEnabled +
                ", textMessagePermissionEnabled=" + textMessagePermissionEnabled +
                ", storagePermissionEnabled=" + storagePermissionEnabled +
                ", usageStatsAllowed=" + usageStatsAllowed +
                ", adminAccessAllowed=" + adminAccessAllowed +
                ", appsOverlayEnabled=" + appsOverlayEnabled +
                ", highAccuraccyLocationEnabled=" + highAccuraccyLocationEnabled +
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
                ", bedTimeEnabled=" + bedTimeEnabled +
                ", screenEnabled=" + screenEnabled +
                ", cameraEnabled=" + cameraEnabled +
                ", settingsEnabled=" + settingsEnabled +
                ", phoneCallsEnabled=" + phoneCallsEnabled +
                ", batteryLevel=" + batteryLevel +
                ", isBatteryCharging=" + isBatteryCharging +
                ", status='" + status + '\'' +
                ", screenStatus='" + screenStatus + '\'' +
                ", deviceStatus='" + deviceStatus + '\'' +
                ", heartbeat=" + heartbeat +
                ", carrierName='" + carrierName + '\'' +
                ", phoneNumber='" + phoneNumber + '\'' +
                '}';
    }
}
