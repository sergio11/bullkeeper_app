package sanchez.sanchez.sergio.data.net.models.response;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonProperty;
import java.io.Serializable;
import java.util.Date;

/**
 * Conversation DTO
 */
@JsonIgnoreProperties(ignoreUnknown = true)
public class ConversationDTO implements Serializable {

    /**
     * Identity
     */
    @JsonProperty("identity")
    private String identity;

    /**
     * Create At
     */
    @JsonProperty("create_at")
    private Date createAt;

    /**
     * Update At
     */
    @JsonProperty("update_at")
    private Date updateAt;


    /**
     * Member One
     */
    @JsonProperty("member_one")
    private PersonDTO memberOne;

    /**
     * Member One
     */
    @JsonProperty("member_two")
    private PersonDTO memberTwo;

    /**
     * Messages Count
     */
    @JsonProperty("messages_count")
    private long messagesCount;

    /**
     * Pending Messages For Member One
     */
    @JsonProperty("pending_messages_for_member_one")
    private long pendingMessagesForMemberOne;

    /**
     * Pending Messages For Member Two
     */
    @JsonProperty("pending_messages_for_member_two")
    private long pendingMessagesForMemberTwo;

    /**
     * Last Message For Member One
     */
    @JsonProperty("last_message_for_member_one")
    private String lastMessageForMemberOne;

    /**
     * Last Message For Member Two
     */
    @JsonProperty("last_message_for_member_two")
    private String lastMessageForMemberTwo;

    /**
     * Last Message
     */
    @JsonProperty("last_message")
    private String lastMessage;

    /**
     *
     */
    public ConversationDTO() {
    }

    /**
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
     */
    public ConversationDTO(String identity, Date createAt, Date updateAt,
                           PersonDTO memberOne, PersonDTO memberTwo, long messagesCount,
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

    public PersonDTO getMemberOne() {
        return memberOne;
    }

    public void setMemberOne(PersonDTO memberOne) {
        this.memberOne = memberOne;
    }

    public PersonDTO getMemberTwo() {
        return memberTwo;
    }

    public void setMemberTwo(PersonDTO memberTwo) {
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
        return "ConversationDTO{" +
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
