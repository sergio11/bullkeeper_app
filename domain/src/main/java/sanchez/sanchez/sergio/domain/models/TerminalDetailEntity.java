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
     * Total Sms
     */
    private long totalSms;

    /**
     * Total Calls
     */
    private long totalCalls;

    /**
     * Total Contacts
     */
    private long totalContacts;

    /**
     * Screen Status
     */
    private ScreenStatusEnum screenStatusEnum;

    /**
     * Location Permission Enabled
     */
    private boolean locationPermissionEnabled;

    /**
     * Call History Permission Enabled
     */
    private boolean callsHistoryPermissionEnabled;

    /**
     * Contacts List Permission Enabled
     */
    private boolean contactsListPermissionEnabled;

    /**
     * Text Message Permission Enabled
     */
    private boolean textMessagePermissionEnabled;

    /**
     * Storage Permission Enabled
     */
    private boolean storagePermissionEnabled;

    /**
     * Usage Stats Allowed
     */
    private boolean usageStatsAllowed;

    /**
     * Admin Access Allowed
     */
    private boolean adminAccessAllowed;

    /**
     * Apps Overlay Enabled
     */
    private boolean appsOverlayEnabled;

    /**
     * High Accuraccy Location Enabled
     */
    private boolean highAccuraccyLocationEnabled;

    /**
     *
     */
    public TerminalDetailEntity(){}

    /**
     *
     * @param identity
     * @param appVersionCode
     * @param appVersionName
     * @param codeName
     * @param deviceName
     * @param manufacturer
     * @param detached
     * @param marketName
     * @param model
     * @param osVersion
     * @param sdkVersion
     * @param bedTimeEnabled
     * @param screenEnabled
     * @param cameraEnabled
     * @param settingsEnabled
     * @param batteryLevel
     * @param isBatteryCharging
     * @param status
     * @param terminalHeartbeatEntity
     * @param carrierName
     * @param phoneNumber
     * @param totalApps
     * @param totalSms
     * @param totalCalls
     * @param totalContacts
     * @param screenStatusEnum
     * @param locationPermissionEnabled
     * @param callsHistoryPermissionEnabled
     * @param contactsListPermissionEnabled
     * @param textMessagePermissionEnabled
     * @param storagePermissionEnabled
     * @param usageStatsAllowed
     * @param adminAccessAllowed
     * @param appsOverlayEnabled
     * @param highAccuraccyLocationEnabled
     */
    public TerminalDetailEntity(String identity, String appVersionCode, String appVersionName, String codeName, String deviceName, String manufacturer, boolean detached, String marketName, String model, String osVersion, String sdkVersion, boolean bedTimeEnabled, boolean screenEnabled, boolean cameraEnabled, boolean settingsEnabled, int batteryLevel, boolean isBatteryCharging, TerminalStatusEnum status, TerminalHeartbeatEntity terminalHeartbeatEntity,
                                String carrierName, String phoneNumber, long totalApps,
                                long totalSms, long totalCalls, long totalContacts, ScreenStatusEnum screenStatusEnum, boolean locationPermissionEnabled, boolean callsHistoryPermissionEnabled, boolean contactsListPermissionEnabled, boolean textMessagePermissionEnabled, boolean storagePermissionEnabled, boolean usageStatsAllowed, boolean adminAccessAllowed, boolean appsOverlayEnabled, boolean highAccuraccyLocationEnabled) {
        super(identity, appVersionCode, appVersionName, codeName, deviceName, manufacturer, detached, marketName, model, osVersion, sdkVersion, bedTimeEnabled, screenEnabled, cameraEnabled, settingsEnabled, batteryLevel, isBatteryCharging, status, terminalHeartbeatEntity, carrierName, phoneNumber);
        this.totalApps = totalApps;
        this.totalSms = totalSms;
        this.totalCalls = totalCalls;
        this.totalContacts = totalContacts;
        this.screenStatusEnum = screenStatusEnum;
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

    public ScreenStatusEnum getScreenStatusEnum() {
        return screenStatusEnum;
    }

    public void setScreenStatusEnum(ScreenStatusEnum screenStatusEnum) {
        this.screenStatusEnum = screenStatusEnum;
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
        return "TerminalDetailEntity{" +
                "totalApps=" + totalApps +
                ", totalSms=" + totalSms +
                ", totalCalls=" + totalCalls +
                ", totalContacts=" + totalContacts +
                ", screenStatusEnum=" + screenStatusEnum +
                ", locationPermissionEnabled=" + locationPermissionEnabled +
                ", callsHistoryPermissionEnabled=" + callsHistoryPermissionEnabled +
                ", contactsListPermissionEnabled=" + contactsListPermissionEnabled +
                ", textMessagePermissionEnabled=" + textMessagePermissionEnabled +
                ", storagePermissionEnabled=" + storagePermissionEnabled +
                ", usageStatsAllowed=" + usageStatsAllowed +
                ", adminAccessAllowed=" + adminAccessAllowed +
                ", appsOverlayEnabled=" + appsOverlayEnabled +
                ", highAccuraccyLocationEnabled=" + highAccuraccyLocationEnabled +
                '}';
    }
}
