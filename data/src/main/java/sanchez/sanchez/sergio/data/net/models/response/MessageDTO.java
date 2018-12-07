package sanchez.sanchez.sergio.data.net.models.response;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonProperty;
import java.io.Serializable;
import java.util.Date;

/**
 * Message DTO
 */
@JsonIgnoreProperties(ignoreUnknown = true)
public class MessageDTO implements Serializable {

    /**
     * Identity
     */
    @JsonProperty("identity")
    private String identity;

    /**
     * Text
     */
    @JsonProperty("text")
    private String text;

    /**
     * Create At
     */
    @JsonProperty("create_at")
    private Date createAt;

    /**
     * Conversation
     */
    @JsonProperty("conversation")
    private String conversation;

    /**
     * From
     */
    @JsonProperty("from")
    private PersonDTO from;

    /**
     * To
     */
    @JsonProperty("to")
    private PersonDTO to;

    /**
     * Viewed
     */
    @JsonProperty("viewed")
    private boolean viewed;


    public MessageDTO(){}

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
    public MessageDTO(String identity, String text, Date createAt, String conversation, PersonDTO from, PersonDTO to, boolean viewed) {
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

    public PersonDTO getFrom() {
        return from;
    }

    public void setFrom(PersonDTO from) {
        this.from = from;
    }

    public PersonDTO getTo() {
        return to;
    }

    public void setTo(PersonDTO to) {
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
        return "MessageDTO{" +
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
