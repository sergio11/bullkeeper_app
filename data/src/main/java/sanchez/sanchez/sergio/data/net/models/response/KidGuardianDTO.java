package sanchez.sanchez.sergio.data.net.models.response;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.databind.annotation.JsonSerialize;
import java.io.Serializable;
import static com.fasterxml.jackson.databind.annotation.JsonSerialize.Inclusion.NON_NULL;

/**
 * Kid Guardian
 */
@JsonIgnoreProperties(ignoreUnknown = true)
@JsonSerialize(include=NON_NULL)
public final class KidGuardianDTO implements Serializable {


    @JsonProperty("identity")
    private String identity;

    /**
     * Kid
     */
    @JsonProperty("kid")
    private KidDTO kid;

    /**
     * Guardian
     */
    @JsonProperty("guardian")
    private GuardianDTO guardian;

    /**
     * Is Confirmed
     */
    @JsonProperty("is_confirmed")
    private boolean isConfirmed;

    /**
     * Role
     */
    @JsonProperty("role")
    private String role;

    /**
     * Request At
     */
    @JsonProperty("request_at")
    private String requestAt;


    public KidGuardianDTO(){}

    public KidGuardianDTO(String identity, KidDTO kid, GuardianDTO guardian, boolean isConfirmed, String role, String requestAt) {
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

    public KidDTO getKid() {
        return kid;
    }

    public void setKid(KidDTO kid) {
        this.kid = kid;
    }

    public GuardianDTO getGuardian() {
        return guardian;
    }

    public void setGuardian(GuardianDTO guardian) {
        this.guardian = guardian;
    }

    public boolean isConfirmed() {
        return isConfirmed;
    }

    public void setConfirmed(boolean confirmed) {
        isConfirmed = confirmed;
    }

    public String getRole() {
        return role;
    }

    public void setRole(String role) {
        this.role = role;
    }

    public String getRequestAt() {
        return requestAt;
    }

    public void setRequestAt(String requestAt) {
        this.requestAt = requestAt;
    }
}
