package sanchez.sanchez.sergio.domain.models;

import java.io.Serializable;
import java.util.List;

/**
 * Children Of Self Guardian
 */
public final class ChildrenOfSelfGuardianEntity implements Serializable  {

    /**
     * Total
     */
    private long total;

    /**
     * Confirmed
     */
    private long confirmed;

    /**
     * No Confirmed
     */
    private long noConfirmed;

    /**
     * Supervised Children Entities
     */
    private List<SupervisedChildrenEntity> supervisedChildrenEntities;

    public ChildrenOfSelfGuardianEntity(){}

    public ChildrenOfSelfGuardianEntity(long total, long confirmed, long noConfirmed, List<SupervisedChildrenEntity> supervisedChildrenEntities) {
        this.total = total;
        this.confirmed = confirmed;
        this.noConfirmed = noConfirmed;
        this.supervisedChildrenEntities = supervisedChildrenEntities;
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

    public List<SupervisedChildrenEntity> getSupervisedChildrenEntities() {
        return supervisedChildrenEntities;
    }

    public void setSupervisedChildrenEntities(List<SupervisedChildrenEntity> supervisedChildrenEntities) {
        this.supervisedChildrenEntities = supervisedChildrenEntities;
    }

    @Override
    public String toString() {
        return "ChildrenOfSelfGuardianEntity{" +
                "total=" + total +
                ", confirmed=" + confirmed +
                ", noConfirmed=" + noConfirmed +
                ", supervisedChildrenEntities=" + supervisedChildrenEntities +
                '}';
    }
}
