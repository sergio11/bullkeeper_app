package sanchez.sanchez.sergio.domain.models;

import java.io.Serializable;
import java.util.Date;
import java.util.List;

/**
 * Alerts Page Entity
 */
public final class AlertsPageEntity implements Serializable {

    private List<AlertEntity> alerts;
    private int total;
    private int returned;
    private int remaining;
    private Date lastQuery;

    public AlertsPageEntity(){}

    /**
     *
     * @param alerts
     * @param total
     * @param returned
     * @param remaining
     * @param lastQuery
     */
    public AlertsPageEntity(List<AlertEntity> alerts, int total, int returned, int remaining, Date lastQuery) {
        this.alerts = alerts;
        this.total = total;
        this.returned = returned;
        this.remaining = remaining;
        this.lastQuery = lastQuery;
    }

    public List<AlertEntity> getAlerts() {
        return alerts;
    }

    public void setAlerts(List<AlertEntity> alerts) {
        this.alerts = alerts;
    }

    public int getTotal() {
        return total;
    }

    public void setTotal(int total) {
        this.total = total;
    }

    public int getReturned() {
        return returned;
    }

    public void setReturned(int returned) {
        this.returned = returned;
    }

    public int getRemaining() {
        return remaining;
    }

    public void setRemaining(int remaining) {
        this.remaining = remaining;
    }

    public Date getLastQuery() {
        return lastQuery;
    }

    public void setLastQuery(Date lastQuery) {
        this.lastQuery = lastQuery;
    }

    @Override
    public String toString() {
        return "AlertsPageEntity{" +
                "alerts=" + alerts +
                ", total=" + total +
                ", returned=" + returned +
                ", remaining=" + remaining +
                ", lastQuery=" + lastQuery +
                '}';
    }
}
