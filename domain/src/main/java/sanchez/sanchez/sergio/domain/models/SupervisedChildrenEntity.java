package sanchez.sanchez.sergio.domain.models;

import java.io.Serializable;

/**
 * Supervised Children Entity
 */
public final class SupervisedChildrenEntity implements Serializable {

    /**
     * Identity
     */
    private String identity;

    /**
     * Kid
     */
    private KidEntity kid;

    /**
     * Guardian Roles Enum
     */
    private GuardianRolesEnum guardianRolesEnum;

    /**
     * Pending Message Count
     */
    private long pendingMessageCount;

    public SupervisedChildrenEntity(){}

    /**
     *
     * @param identity
     * @param kid
     * @param guardianRolesEnum
     * @param  pendingMessageCount
     */
    public SupervisedChildrenEntity(final String identity, final KidEntity kid,
                                    final GuardianRolesEnum guardianRolesEnum,
                                    final long pendingMessageCount) {
        this.identity = identity;
        this.kid = kid;
        this.guardianRolesEnum = guardianRolesEnum;
        this.pendingMessageCount = pendingMessageCount;
    }

    public String getIdentity() {
        return identity;
    }

    public void setIdentity(String identity) {
        this.identity = identity;
    }

    public KidEntity getKid() {
        return kid;
    }

    public void setKid(KidEntity kid) {
        this.kid = kid;
    }

    public GuardianRolesEnum getGuardianRolesEnum() {
        return guardianRolesEnum;
    }

    public void setGuardianRolesEnum(GuardianRolesEnum guardianRolesEnum) {
        this.guardianRolesEnum = guardianRolesEnum;
    }

    public long getPendingMessageCount() {
        return pendingMessageCount;
    }

    public void setPendingMessageCount(long pendingMessageCount) {
        this.pendingMessageCount = pendingMessageCount;
    }

    @Override
    public String toString() {
        return "SupervisedChildrenEntity{" +
                "identity='" + identity + '\'' +
                ", kid=" + kid +
                ", guardianRolesEnum=" + guardianRolesEnum +
                ", pendingMessageCount=" + pendingMessageCount +
                '}';
    }
}
