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

    /**
     * Pending Messages For Member One
     */
    private long pendingMessagesForMemberOne;

    /**
     * Pending Messages For Member Two
     */
    private long pendingMessagesForMemberTwo;

    /**
     * Last Message For Member One
     */
    private String lastMessageForMemberOne;

    /**
     * Last Message For Member Two
     */
    private String lastMessageForMemberTwo;

    /**
     * Last Message
     */
    private String lastMessage;

    public ConversationEntity(){}

    /**
     *
     * @param identity
     * @param createAt
     * @param updateAt
     * @param memberOne
     * @param memberTwo
     * @param messagesCount
     * @param pendingMessagesForMemberOne
     * @param pendingMessagesForMemberTwo
     * @param lastMessageForMemberOne
     * @param lastMessageForMemberTwo
     * @param lastMessage
     */
    public ConversationEntity(String identity, Date createAt, Date updateAt,
                              PersonEntity memberOne, PersonEntity memberTwo, long messagesCount,
                              long pendingMessagesForMemberOne, long pendingMessagesForMemberTwo,
                              String lastMessageForMemberOne, String lastMessageForMemberTwo,
                              String lastMessage) {
        this.identity = identity;
        this.createAt = createAt;
        this.updateAt = updateAt;
        this.memberOne = memberOne;
        this.memberTwo = memberTwo;
        this.messagesCount = messagesCount;
        this.pendingMessagesForMemberOne = pendingMessagesForMemberOne;
        this.pendingMessagesForMemberTwo = pendingMessagesForMemberTwo;
        this.lastMessageForMemberOne = lastMessageForMemberOne;
        this.lastMessageForMemberTwo = lastMessageForMemberTwo;
        this.lastMessage = lastMessage;
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

    public long getPendingMessagesForMemberOne() {
        return pendingMessagesForMemberOne;
    }

    public void setPendingMessagesForMemberOne(long pendingMessagesForMemberOne) {
        this.pendingMessagesForMemberOne = pendingMessagesForMemberOne;
    }

    public long getPendingMessagesForMemberTwo() {
        return pendingMessagesForMemberTwo;
    }

    public void setPendingMessagesForMemberTwo(long pendingMessagesForMemberTwo) {
        this.pendingMessagesForMemberTwo = pendingMessagesForMemberTwo;
    }

    public String getLastMessageForMemberOne() {
        return lastMessageForMemberOne;
    }

    public void setLastMessageForMemberOne(String lastMessageForMemberOne) {
        this.lastMessageForMemberOne = lastMessageForMemberOne;
    }

    public String getLastMessageForMemberTwo() {
        return lastMessageForMemberTwo;
    }

    public void setLastMessageForMemberTwo(String lastMessageForMemberTwo) {
        this.lastMessageForMemberTwo = lastMessageForMemberTwo;
    }

    public String getLastMessage() {
        return lastMessage;
    }

    public void setLastMessage(String lastMessage) {
        this.lastMessage = lastMessage;
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
                ", pendingMessagesForMemberOne=" + pendingMessagesForMemberOne +
                ", pendingMessagesForMemberTwo=" + pendingMessagesForMemberTwo +
                ", lastMessageForMemberOne='" + lastMessageForMemberOne + '\'' +
                ", lastMessageForMemberTwo='" + lastMessageForMemberTwo + '\'' +
                ", lastMessage='" + lastMessage + '\'' +
                '}';
    }
}
