package sanchez.sanchez.sergio.domain.models;

import java.io.Serializable;
import java.util.Date;

/**
 * Conversation Entity
 */
public final class ConversationEntity implements Serializable {

    /**
     * Identity
     */
    private String identity;

    /**
     * Create At
     */
    private Date createAt;

    /**
     * Update At
     */
    private Date updateAt;

    /**
     * Kid Guardian
     */
    private KidGuardianEntity kidGuardian;

    /**
     * Message Count
     */
    private long messagesCount;

    public ConversationEntity(){}

    /**
     *
     * @param identity
     * @param createAt
     * @param updateAt
     * @param kidGuardian
     * @param messagesCount
     */
    public ConversationEntity(String identity, Date createAt, Date updateAt, KidGuardianEntity kidGuardian, long messagesCount) {
        this.identity = identity;
        this.createAt = createAt;
        this.updateAt = updateAt;
        this.kidGuardian = kidGuardian;
        this.messagesCount = messagesCount;
    }

    public String getIdentity() {
        return identity;
    }

    public void setIdentity(String identity) {
        this.identity = identity;
    }

    public Date getCreateAt() {
        return createAt;
    }

    public void setCreateAt(Date createAt) {
        this.createAt = createAt;
    }

    public Date getUpdateAt() {
        return updateAt;
    }

    public void setUpdateAt(Date updateAt) {
        this.updateAt = updateAt;
    }

    public KidGuardianEntity getKidGuardian() {
        return kidGuardian;
    }

    public void setKidGuardian(KidGuardianEntity kidGuardian) {
        this.kidGuardian = kidGuardian;
    }

    public long getMessagesCount() {
        return messagesCount;
    }

    public void setMessagesCount(long messagesCount) {
        this.messagesCount = messagesCount;
    }

    @Override
    public String toString() {
        return "ConversationEntity{" +
                "identity='" + identity + '\'' +
                ", createAt=" + createAt +
                ", updateAt=" + updateAt +
                ", kidGuardian=" + kidGuardian +
                ", messagesCount=" + messagesCount +
                '}';
    }
}
