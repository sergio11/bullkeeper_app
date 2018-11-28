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

    public SupervisedChildrenEntity(){}

    /**
     *
     * @param identity
     * @param kid
     * @param guardianRolesEnum
     */
    public SupervisedChildrenEntity(String identity, KidEntity kid, GuardianRolesEnum guardianRolesEnum) {
        this.identity = identity;
        this.kid = kid;
        this.guardianRolesEnum = guardianRolesEnum;
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

    @Override
    public String toString() {
        return "SupervisedChildrenEntity{" +
                "identity='" + identity + '\'' +
                ", kid=" + kid +
                ", guardianRolesEnum=" + guardianRolesEnum +
                '}';
    }
}
