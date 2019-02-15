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
     * Unread Messages
     */
    @JsonProperty("unread_messages")
    private long unreadMessages;

    /**
     * Last Message
     */
    @JsonProperty("last_message")
    private String lastMessage;

    /**
     *
     */
    public ConversationDTO(){}

    /**
     *
     * @param identity
     * @param createAt
     * @param updateAt
     * @param memberOne
     * @param memberTwo
     * @param messagesCount
     * @param unreadMessages
     * @param lastMessage
     */
    public ConversationDTO(final String identity, final Date createAt,
                           final Date updateAt, final PersonDTO memberOne,
                           final PersonDTO memberTwo, final long messagesCount,
                           final long unreadMessages, final String lastMessage) {
        this.identity = identity;
        this.createAt = createAt;
        this.updateAt = updateAt;
        this.memberOne = memberOne;
        this.memberTwo = memberTwo;
        this.messagesCount = messagesCount;
        this.unreadMessages = unreadMessages;
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

    public long getUnreadMessages() {
        return unreadMessages;
    }

    public void setUnreadMessages(long unreadMessages) {
        this.unreadMessages = unreadMessages;
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
                ", unreadMessages=" + unreadMessages +
                ", lastMessage='" + lastMessage + '\'' +
                '}';
    }
}
