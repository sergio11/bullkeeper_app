package sanchez.sanchez.sergio.data.net.models.request;

import com.fasterxml.jackson.annotation.JsonProperty;
import java.io.Serializable;

/**
 * Add Message DTO
 */
public final class AddMessageDTO implements Serializable {

    /**
     * Conversation
     */
    @JsonProperty("conversation")
    private String conversation;

    /**
     * Text
     */
    @JsonProperty("text")
    private String text;

    /**
     * From
     */
    @JsonProperty("from")
    private String from;

    /**
     * To
     */
    @JsonProperty("to")
    private String to;


    public AddMessageDTO(){}

    /**
     * @param conversation
     * @param text
     * @param from
     * @param to
     */
    public AddMessageDTO(
            final String conversation,
            final String text,
            final String from,
            final String to) {
        this.conversation = conversation;
        this.text = text;
        this.from = from;
        this.to = to;
    }

    public String getConversation() {
        return conversation;
    }

    public void setConversation(String conversation) {
        this.conversation = conversation;
    }

    public String getText() {
        return text;
    }

    public void setText(String text) {
        this.text = text;
    }

    public String getFrom() {
        return from;
    }

    public void setFrom(String from) {
        this.from = from;
    }

    public String getTo() {
        return to;
    }

    public void setTo(String to) {
        this.to = to;
    }

    @Override
    public String toString() {
        return "AddMessageDTO{" +
                "conversation='" + conversation + '\'' +
                ", text='" + text + '\'' +
                ", from='" + from + '\'' +
                ", to='" + to + '\'' +
                '}';
    }
}
