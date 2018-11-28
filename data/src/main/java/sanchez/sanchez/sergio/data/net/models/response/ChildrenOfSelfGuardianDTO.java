package sanchez.sanchez.sergio.data.net.models.response;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonProperty;

import java.io.Serializable;
import java.util.List;

/**
 * Children Of Self Guardian
 */
@JsonIgnoreProperties(ignoreUnknown = true)
public final class ChildrenOfSelfGuardianDTO implements Serializable {

    /**
     * Total
     */
    @JsonProperty("total")
    private long total;

    /**
     * Confirmed
     */
    @JsonProperty("confirmed")
    private long confirmed;

    /**
     * No Confirmed
     */
    @JsonProperty("no_confirmed")
    private long noConfirmed;

    /**
     * Supervised Children
     */
    @JsonProperty("supervised_children")
    private List<SupervisedChildrenDTO> supervisedChildrenList;

    public ChildrenOfSelfGuardianDTO(){}

    public ChildrenOfSelfGuardianDTO(long total, long confirmed, long noConfirmed, List<SupervisedChildrenDTO> supervisedChildrenList) {
        this.total = total;
        this.confirmed = confirmed;
        this.noConfirmed = noConfirmed;
        this.supervisedChildrenList = supervisedChildrenList;
    }

    public long getTotal() {
        return total;
    }

    public void setTotal(long total) {
        this.total = total;
    }

    public long getConfirmed() {
        return confirmed;
    }

    public void setConfirmed(long confirmed) {
        this.confirmed = confirmed;
    }

    public long getNoConfirmed() {
        return noConfirmed;
    }

    public void setNoConfirmed(long noConfirmed) {
        this.noConfirmed = noConfirmed;
    }

    public List<SupervisedChildrenDTO> getSupervisedChildrenList() {
        return supervisedChildrenList;
    }

    public void setSupervisedChildrenList(List<SupervisedChildrenDTO> supervisedChildrenList) {
        this.supervisedChildrenList = supervisedChildrenList;
    }
}
