package sanchez.sanchez.sergio.data.net.models.response;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonProperty;

import java.io.Serializable;
import java.util.Date;

/**
 * App Stats DTO
 */
@JsonIgnoreProperties(ignoreUnknown = true)
public final class AppStatsDTO implements Serializable {

    /**
     * Identity
     */
    @JsonProperty("identity")
    private String identity;

    /**
     * First Time
     */
    @JsonProperty("first_time")
    private Date firstTime;

    /**
     * Last Time
     */
    @JsonProperty("last_time")
    private Date lastTime;

    /**
     * Last Time Used
     */
    @JsonProperty("last_time_used")
    private Date lastTimeUsed;

    /**
     * Total Time in foreground
     */
    @JsonProperty("total_time_in_foreground")
    private Long totalTimeInForeground;

    /**
     * Package Name
     */
    @JsonProperty("package_name")
    private String packageName;

    /**
     * Package Name
     */
    @JsonProperty("iconEncodedString")
    private String iconEncodedString;

    /**
     * App Name
     */
    @JsonProperty("appName")
    private String appName;

    /**
     * Kid
     */
    @JsonProperty("kid")
    private String kid;

    /**
     * Terminal
     */
    @JsonProperty("terminal")
    private String terminal;

    public AppStatsDTO(){}

    public AppStatsDTO(String identity, Date firstTime, Date lastTime, Date lastTimeUsed, Long totalTimeInForeground, String packageName, String iconEncodedString, String appName, String kid, String terminal) {
        this.identity = identity;
        this.firstTime = firstTime;
        this.lastTime = lastTime;
        this.lastTimeUsed = lastTimeUsed;
        this.totalTimeInForeground = totalTimeInForeground;
        this.packageName = packageName;
        this.iconEncodedString = iconEncodedString;
        this.appName = appName;
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

    public String getIconEncodedString() {
        return iconEncodedString;
    }

    public void setIconEncodedString(String iconEncodedString) {
        this.iconEncodedString = iconEncodedString;
    }

    public String getAppName() {
        return appName;
    }

    public void setAppName(String appName) {
        this.appName = appName;
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
        return "AppStatsDTO{" +
                "identity='" + identity + '\'' +
                ", firstTime=" + firstTime +
                ", lastTime=" + lastTime +
                ", lastTimeUsed=" + lastTimeUsed +
                ", totalTimeInForeground=" + totalTimeInForeground +
                ", packageName='" + packageName + '\'' +
                ", iconEncodedString='" + iconEncodedString + '\'' +
                ", appName='" + appName + '\'' +
                ", kid='" + kid + '\'' +
                ", terminal='" + terminal + '\'' +
                '}';
    }
}
