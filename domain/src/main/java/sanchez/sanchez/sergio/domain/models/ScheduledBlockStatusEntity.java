package sanchez.sanchez.sergio.domain.models;

/**
 * Scheduled Block Status Entity
 */
public final class ScheduledBlockStatusEntity {

    /**
     * Identity
     */
    private String identity;

    /**
     * Enable
     */
    private boolean enable;

    public ScheduledBlockStatusEntity(){}

    public ScheduledBlockStatusEntity(String identity, boolean enable) {
        this.identity = identity;
        this.enable = enable;
    }

    public String getIdentity() {
        return identity;
    }

    public void setIdentity(String identity) {
        this.identity = identity;
    }

    public boolean isEnable() {
        return enable;
    }

    public void setEnable(boolean enable) {
        this.enable = enable;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        ScheduledBlockStatusEntity that = (ScheduledBlockStatusEntity) o;

        return getIdentity() != null ? getIdentity().equals(that.getIdentity()) : that.getIdentity() == null;
    }

    @Override
    public int hashCode() {
        return getIdentity() != null ? getIdentity().hashCode() : 0;
    }
}
