package sanchez.sanchez.sergio.data.models.response;

import com.fasterxml.jackson.annotation.JsonProperty;

import java.io.Serializable;
import java.util.Date;
import java.util.List;

/**
 * Alerts Page DTO
 */
public class AlertsPageDTO implements Serializable {

    @JsonProperty("alerts")
    public List<AlertDTO> alerts;

    @JsonProperty("total")
    private Integer total;

    @JsonProperty("returned")
    private Integer returned;

    @JsonProperty("remaining")
    private Integer remaining;

    @JsonProperty("last_query")
    private Date lastQuery;

    public List<AlertDTO> getAlerts() {
        return alerts;
    }

    public void setAlerts(List<AlertDTO> alerts) {
        this.alerts = alerts;
    }

    public Integer getTotal() {
        return total;
    }

    public void setTotal(Integer total) {
        this.total = total;
    }

    public Integer getReturned() {
        return returned;
    }

    public void setReturned(Integer returned) {
        this.returned = returned;
    }

    public Integer getRemaining() {
        return remaining;
    }

    public void setRemaining(Integer remaining) {
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
        return "AlertsPageDTO{" +
                "alerts=" + alerts +
                ", total=" + total +
                ", returned=" + returned +
                ", remaining=" + remaining +
                ", lastQuery=" + lastQuery +
                '}';
    }
}
