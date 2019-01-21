package sanchez.sanchez.sergio.domain.models;

import java.io.Serializable;
import java.util.Date;

/**
 * App Stats Entity
 */
public final class AppStatsEntity implements Serializable {

    /**
     * Identity
     */
    private String identity;

    /**
     * First Time
     */
    private Date firstTime;

    /**
     * Last Time
     */
    private Date lastTime;

    /**
     * Last Time Used
     */
    private Date lastTimeUsed;

    /**
     * Total Time In Foreground
     */
    private Long totalTimeInForeground;

    /**
     * Package Name
     */
    private String packageName;

    /**
     * App Name
     */
    private String appName;

    /**
     * Icon Encoded
     */
    private String iconEncodedString;

    /**
     * App
     */
    private String app;

    /**
     * Kid
     */
    private String kid;

    /**
     * Terminal
     */
    private String terminal;


    public AppStatsEntity(){}

    public AppStatsEntity(String identity, Date firstTime, Date lastTime, Date lastTimeUsed,
                          Long totalTimeInForeground, String packageName, String appName,
                          String iconEncodedString, String app, String kid, String terminal) {
        this.identity = identity;
        this.firstTime = firstTime;
        this.lastTime = lastTime;
        this.lastTimeUsed = lastTimeUsed;
        this.totalTimeInForeground = totalTimeInForeground;
        this.packageName = packageName;
        this.appName = appName;
        this.iconEncodedString = iconEncodedString;
        this.app = app;
        this.kid = kid;
        this.terminal = terminal;
    }

    public String getIdentity() {
        return identity;
    }

    public void setIdentity(String identity) {
        this.identity = identity;
    }

    public Date getFirstTime() {
        return firstTime;
    }

    public void setFirstTime(Date firstTime) {
        this.firstTime = firstTime;
    }

    public Date getLastTime() {
        return lastTime;
    }

    public void setLastTime(Date lastTime) {
        this.lastTime = lastTime;
    }

    public Date getLastTimeUsed() {
        return lastTimeUsed;
    }

    public void setLastTimeUsed(Date lastTimeUsed) {
        this.lastTimeUsed = lastTimeUsed;
    }

    public Long getTotalTimeInForeground() {
        return totalTimeInForeground;
    }

    public void setTotalTimeInForeground(Long totalTimeInForeground) {
        this.totalTimeInForeground = totalTimeInForeground;
    }

    public String getPackageName() {
        return packageName;
    }

    public void setPackageName(String packageName) {
        this.packageName = packageName;
    }

    public String getAppName() {
        return appName;
    }

    public void setAppName(String appName) {
        this.appName = appName;
    }

    public String getIconEncodedString() {
        return iconEncodedString;
    }

    public void setIconEncodedString(String iconEncodedString) {
        this.iconEncodedString = iconEncodedString;
    }

    public String getApp() {
        return app;
    }

    public void setApp(String app) {
        this.app = app;
    }

    public String getKid() {
        return kid;
    }

    public void setKid(String kid) {
        this.kid = kid;
    }

    public String getTerminal() {
        return terminal;
    }

    public void setTerminal(String terminal) {
        this.terminal = terminal;
    }

    @Override
    public String toString() {
        return "AppStatsEntity{" +
                "identity='" + identity + '\'' +
                ", firstTime=" + firstTime +
                ", lastTime=" + lastTime +
                ", lastTimeUsed=" + lastTimeUsed +
                ", totalTimeInForeground=" + totalTimeInForeground +
                ", packageName='" + packageName + '\'' +
                ", appName='" + appName + '\'' +
                ", iconEncodedString='" + iconEncodedString + '\'' +
                ", kid='" + kid + '\'' +
                ", terminal='" + terminal + '\'' +
                '}';
    }
}
