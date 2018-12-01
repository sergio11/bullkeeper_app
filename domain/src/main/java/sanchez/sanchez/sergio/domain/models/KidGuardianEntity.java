package sanchez.sanchez.sergio.domain.models;

import java.io.Serializable;
import java.util.Date;

/**
 * Kid Guardian Entity
 */
public final class KidGuardianEntity implements Serializable {

    /**
     * Identity
     */
    private String identity;

    /**
     * Kid
     */
    private KidEntity kid;

    /**
     * Guardian
     */
    private GuardianEntity guardian;

    /**
     * Is Confirmed
     */
    private boolean isConfirmed;

    /**
     * Role
     */
    private GuardianRolesEnum role;

    /**
     * Request At
     */
    private Date requestAt;

    public KidGuardianEntity(){}

    /**
     *
     * @param identity
     * @param kid
     * @param guardian
     * @param isConfirmed
     * @param role
     * @param requestAt
     */
    public KidGuardianEntity(final String identity, final KidEntity kid,
                             final GuardianEntity guardian, final boolean isConfirmed,
                             final GuardianRolesEnum role, final Date requestAt) {
        this.identity = identity;
        this.kid = kid;
        this.guardian = guardian;
        this.isConfirmed = isConfirmed;
        this.role = role;
        this.requestAt = requestAt;
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

    public GuardianEntity getGuardian() {
        return guardian;
    }

    public void setGuardian(GuardianEntity guardian) {
        this.guardian = guardian;
    }

    public boolean isConfirmed() {
        return isConfirmed;
    }

    public void setConfirmed(boolean confirmed) {
        isConfirmed = confirmed;
    }

    public GuardianRolesEnum getRole() {
        return role;
    }

    public void setRole(GuardianRolesEnum role) {
        this.role = role;
    }

    public Date getRequestAt() {
        return requestAt;
    }

    public void setRequestAt(Date requestAt) {
        this.requestAt = requestAt;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        KidGuardianEntity that = (KidGuardianEntity) o;

        return getIdentity() != null ? getIdentity().equals(that.getIdentity()) : that.getIdentity() == null;
    }

    @Override
    public int hashCode() {
        return getIdentity() != null ? getIdentity().hashCode() : 0;
    }
}
