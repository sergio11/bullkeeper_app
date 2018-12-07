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

    /**
     * From
     */
    private PersonEntity from;

    /**
     * To
     */
    private PersonEntity to;

    /**
     * Viewed
     */
    private boolean viewed;

    public MessageEntity(){}

    /**
     *
     * @param identity
     * @param text
     * @param createAt
     * @param conversation
     * @param from
     * @param to
     * @param viewed
     */
    public MessageEntity(String identity, String text, Date createAt, String conversation, PersonEntity from, PersonEntity to, boolean viewed) {
        this.identity = identity;
        this.text = text;
        this.createAt = createAt;
        this.conversation = conversation;
        this.from = from;
        this.to = to;
        this.viewed = viewed;
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

    public PersonEntity getFrom() {
        return from;
    }

    public void setFrom(PersonEntity from) {
        this.from = from;
    }

    public PersonEntity getTo() {
        return to;
    }

    public void setTo(PersonEntity to) {
        this.to = to;
    }

    public boolean isViewed() {
        return viewed;
    }

    public void setViewed(boolean viewed) {
        this.viewed = viewed;
    }

    @Override
    public String toString() {
        return "MessageEntity{" +
                "identity='" + identity + '\'' +
                ", text='" + text + '\'' +
                ", createAt=" + createAt +
                ", conversation='" + conversation + '\'' +
                ", from=" + from +
                ", to=" + to +
                ", viewed=" + viewed +
                '}';
    }
}
