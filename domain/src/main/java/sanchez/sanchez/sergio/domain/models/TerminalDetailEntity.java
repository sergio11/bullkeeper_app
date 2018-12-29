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
     * Bed Time Enabled
     */
    private boolean bedTimeEnabled;

    /**
     * Lock Screen Enabled
     */
    private boolean lockScreenEnabled;

    /**
     * Lock Camera Enabled
     */
    private boolean lockCameraEnabled;


    public TerminalDetailEntity(){}

    /**
     *
     * @param identity
     * @param appVersionCode
     * @param appVersionName
     * @param codeName
     * @param deviceName
     * @param manufacturer
     * @param marketName
     * @param model
     * @param osVersion
     * @param sdkVersion
     * @param totalApps
     * @param totalSms
     * @param totalCalls
     * @param totalContacts
     * @param screenStatusEnum
     * @param lastTimeUsed
     * @param bedTimeEnabled
     * @param lockScreenEnabled
     * @param lockCameraEnabled
     */
    public TerminalDetailEntity(String identity, String appVersionCode, String appVersionName, String codeName, String deviceName, String manufacturer, String marketName, String model, String osVersion, String sdkVersion,
                                long totalApps, long totalSms, long totalCalls,
                                long totalContacts, ScreenStatusEnum screenStatusEnum,
                                String lastTimeUsed, boolean bedTimeEnabled, boolean lockScreenEnabled,
                                boolean lockCameraEnabled) {
        super(identity, appVersionCode, appVersionName, codeName, deviceName, manufacturer, marketName, model, osVersion, sdkVersion);
        this.totalApps = totalApps;
        this.totalSms = totalSms;
        this.totalCalls = totalCalls;
        this.totalContacts = totalContacts;
        this.screenStatusEnum = screenStatusEnum;
        this.lastTimeUsed = lastTimeUsed;
        this.bedTimeEnabled = bedTimeEnabled;
        this.lockScreenEnabled = lockScreenEnabled;
        this.lockCameraEnabled = lockCameraEnabled;
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

    public boolean isBedTimeEnabled() {
        return bedTimeEnabled;
    }

    public void setBedTimeEnabled(boolean bedTimeEnabled) {
        this.bedTimeEnabled = bedTimeEnabled;
    }

    public boolean isLockScreenEnabled() {
        return lockScreenEnabled;
    }

    public void setLockScreenEnabled(boolean lockScreenEnabled) {
        this.lockScreenEnabled = lockScreenEnabled;
    }

    public boolean isLockCameraEnabled() {
        return lockCameraEnabled;
    }

    public void setLockCameraEnabled(boolean lockCameraEnabled) {
        this.lockCameraEnabled = lockCameraEnabled;
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
                ", bedTimeEnabled=" + bedTimeEnabled +
                ", lockScreenEnabled=" + lockScreenEnabled +
                ", lockCameraEnabled=" + lockCameraEnabled +
                '}';
    }
}
