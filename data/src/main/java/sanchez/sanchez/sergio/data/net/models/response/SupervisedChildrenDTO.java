package sanchez.sanchez.sergio.data.net.models.response;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonProperty;

import java.io.Serializable;

/**
 * Supervised Children
 */
@JsonIgnoreProperties(ignoreUnknown = true)
public final class SupervisedChildrenDTO implements Serializable {

    /**
     * Identity
     */
    @JsonProperty("identity")
    private String identity;

    /**
     * Kid
     */
    @JsonProperty("kid")
    private KidDTO kid;

    /**
     * Role
     */
    @JsonProperty("role")
    private String role;

    /**
     * Pending Message Count
     */
    @JsonProperty("pending_message_count")
    private long pendingMessageCount;


    public SupervisedChildrenDTO() {}

    /**
     *
     * @param identity
     * @param kid
     * @param role
     * @param pendingMessageCount
     */
    public SupervisedChildrenDTO(final String identity, final KidDTO kid,
                                 final String role, long pendingMessageCount) {
        super();
        this.identity = identity;
        this.kid = kid;
        this.role = role;
        this.pendingMessageCount = pendingMessageCount;
    }

    public String getIdentity() {
        return identity;
    }


    public KidDTO getKid() {
        return kid;
    }


    public String getRole() {
        return role;
    }


    public void setIdentity(String identity) {
        this.identity = identity;
    }


    public void setKid(KidDTO kid) {
        this.kid = kid;
    }


    public void setRole(String role) {
        this.role = role;
    }

    public long getPendingMessageCount() {
        return pendingMessageCount;
    }

    public void setPendingMessageCount(long pendingMessageCount) {
        this.pendingMessageCount = pendingMessageCount;
    }

    @Override
    public String toString() {
        return "SupervisedChildrenDTO{" +
                "identity='" + identity + '\'' +
                ", kid=" + kid +
                ", role='" + role + '\'' +
                ", pendingMessageCount=" + pendingMessageCount +
                '}';
    }
}
