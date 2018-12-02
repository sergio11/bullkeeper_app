package sanchez.sanchez.sergio.data.net.models.request;

import com.fasterxml.jackson.annotation.JsonProperty;

import java.io.Serializable;

/**
 * Save Kid Guardian DTO
 */
public final class SaveKidGuardianDTO implements Serializable {

    /**
     * Identity
     */
    @JsonProperty("identity")
    private String identity;

    /**
     * Kid
     */
    @JsonProperty("kid")
    private String kid;

    /**
     * Guardian
     */
    @JsonProperty("guardian")
    private String guardian;

    /**
     * Role
     */
    @JsonProperty("role")
    private String role;

    /**
     * Is Confirmed
     */
    @JsonProperty("is_confirmed")
    private boolean isConfirmed;

    public SaveKidGuardianDTO(){}

    /**
     *
     * @param identity
     * @param kid
     * @param guardian
     * @param role
     */
    public SaveKidGuardianDTO(String identity, String kid, String guardian, String role, boolean isConfirmed) {
        this.identity = identity;
        this.kid = kid;
        this.guardian = guardian;
        this.role = role;
        this.isConfirmed = isConfirmed;
    }

    public String getIdentity() {
        return identity;
    }

    public void setIdentity(String identity) {
        this.identity = identity;
    }

    public String getKid() {
        return kid;
    }

    public void setKid(String kid) {
        this.kid = kid;
    }

    public String getGuardian() {
        return guardian;
    }

    public void setGuardian(String guardian) {
        this.guardian = guardian;
    }

    public String getRole() {
        return role;
    }

    public void setRole(String role) {
        this.role = role;
    }

    public boolean isConfirmed() {
        return isConfirmed;
    }

    public void setConfirmed(boolean confirmed) {
        isConfirmed = confirmed;
    }

    @Override
    public String toString() {
        return "SaveKidGuardianDTO{" +
                "identity='" + identity + '\'' +
                ", kid='" + kid + '\'' +
                ", guardian='" + guardian + '\'' +
                ", role='" + role + '\'' +
                ", isConfirmed=" + isConfirmed +
                '}';
    }
}
