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
     * Member One
     */
    private PersonEntity memberOne;

    /**
     * Member Two
     */
    private PersonEntity memberTwo;

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
     * @param memberOne
     * @param memberTwo
     * @param messagesCount
     */
    public ConversationEntity(final String identity, final Date createAt,
                              final Date updateAt, final PersonEntity memberOne,
                              final PersonEntity memberTwo, long messagesCount) {
        this.identity = identity;
        this.createAt = createAt;
        this.updateAt = updateAt;
        this.memberOne = memberOne;
        this.memberTwo = memberTwo;
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

    public PersonEntity getMemberOne() {
        return memberOne;
    }

    public void setMemberOne(PersonEntity memberOne) {
        this.memberOne = memberOne;
    }

    public PersonEntity getMemberTwo() {
        return memberTwo;
    }

    public void setMemberTwo(PersonEntity memberTwo) {
        this.memberTwo = memberTwo;
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
                ", memberOne=" + memberOne +
                ", memberTwo=" + memberTwo +
                ", messagesCount=" + messagesCount +
                '}';
    }
}
