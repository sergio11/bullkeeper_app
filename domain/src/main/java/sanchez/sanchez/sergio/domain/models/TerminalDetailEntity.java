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
     * Last Time Used
     */
    private String lastTimeUsed;

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



    public TerminalDetailEntity(){}

    public TerminalDetailEntity(String identity, String appVersionCode, String appVersionName, String codeName, String deviceName, String manufacturer, String marketName, String model, String osVersion, String sdkVersion, boolean bedTimeEnabled, boolean screenEnabled, boolean cameraEnabled, boolean settingsEnabled, long totalApps, long totalSms, long totalCalls, long totalContacts, ScreenStatusEnum screenStatusEnum, String lastTimeUsed, boolean locationPermissionEnabled, boolean callsHistoryPermissionEnabled, boolean contactsListPermissionEnabled, boolean textMessagePermissionEnabled, boolean storagePermissionEnabled) {
        super(identity, appVersionCode, appVersionName, codeName, deviceName, manufacturer, marketName, model, osVersion, sdkVersion, bedTimeEnabled, screenEnabled, cameraEnabled, settingsEnabled);
        this.totalApps = totalApps;
        this.totalSms = totalSms;
        this.totalCalls = totalCalls;
        this.totalContacts = totalContacts;
        this.screenStatusEnum = screenStatusEnum;
        this.lastTimeUsed = lastTimeUsed;
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

    public ScreenStatusEnum getScreenStatusEnum() {
        return screenStatusEnum;
    }

    public void setScreenStatusEnum(ScreenStatusEnum screenStatusEnum) {
        this.screenStatusEnum = screenStatusEnum;
    }

    public String getLastTimeUsed() {
        return lastTimeUsed;
    }

    public void setLastTimeUsed(String lastTimeUsed) {
        this.lastTimeUsed = lastTimeUsed;
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
        return "TerminalDetailEntity{" +
                "totalApps=" + totalApps +
                ", totalSms=" + totalSms +
                ", totalCalls=" + totalCalls +
                ", totalContacts=" + totalContacts +
                ", screenStatusEnum=" + screenStatusEnum +
                ", lastTimeUsed='" + lastTimeUsed + '\'' +
                ", locationPermissionEnabled=" + locationPermissionEnabled +
                ", callsHistoryPermissionEnabled=" + callsHistoryPermissionEnabled +
                ", contactsListPermissionEnabled=" + contactsListPermissionEnabled +
                ", textMessagePermissionEnabled=" + textMessagePermissionEnabled +
                ", storagePermissionEnabled=" + storagePermissionEnabled +
                '}';
    }
}
