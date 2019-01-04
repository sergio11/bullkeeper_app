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
     * Kid
     */
    private String kid;

    /**
     * Terminal
     */
    private String terminal;


    public AppStatsEntity(){}

    /**
     *
     * @param identity
     * @param firstTime
     * @param lastTime
     * @param lastTimeUsed
     * @param totalTimeInForeground
     * @param packageName
     * @param kid
     * @param terminal
     */
    public AppStatsEntity(String identity, Date firstTime, Date lastTime, Date lastTimeUsed, Long totalTimeInForeground, String packageName, String kid, String terminal) {
        this.identity = identity;
        this.firstTime = firstTime;
        this.lastTime = lastTime;
        this.lastTimeUsed = lastTimeUsed;
        this.totalTimeInForeground = totalTimeInForeground;
        this.packageName = packageName;
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
                ", kid='" + kid + '\'' +
                ", terminal='" + terminal + '\'' +
                '}';
    }
}
