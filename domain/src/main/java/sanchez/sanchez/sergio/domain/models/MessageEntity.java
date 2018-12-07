package sanchez.sanchez.sergio.domain.models;

import java.io.Serializable;
import java.util.Date;

/**
 * Message Entity
 */
public final class MessageEntity implements Serializable {

    /**
     * Identity
     */
    private String identity;

    /**
     * Text
     */
    private String text;

    /**
     * Create At
     */
    private Date createAt;

    /**
     * Conversation
     */
    private String conversation;

    public MessageEntity(){}

    /**
     * Message Entity
     * @param identity
     * @param text
     * @param createAt
     * @param conversation
     */
    public MessageEntity(String identity, String text, Date createAt, String conversation) {
        this.identity = identity;
        this.text = text;
        this.createAt = createAt;
        this.conversation = conversation;
    }

    public String getIdentity() {
        return identity;
    }

    public void setIdentity(String identity) {
        this.identity = identity;
    }

    public String getText() {
        return text;
    }

    public void setText(String text) {
        this.text = text;
    }

    public Date getCreateAt() {
        return createAt;
    }

    public void setCreateAt(Date createAt) {
        this.createAt = createAt;
    }

    public String getConversation() {
        return conversation;
    }

    public void setConversation(String conversation) {
        this.conversation = conversation;
    }

    @Override
    public String toString() {
        return "MessageEntity{" +
                "identity='" + identity + '\'' +
                ", text='" + text + '\'' +
                ", createAt=" + createAt +
                ", conversation='" + conversation + '\'' +
                '}';
    }
}
