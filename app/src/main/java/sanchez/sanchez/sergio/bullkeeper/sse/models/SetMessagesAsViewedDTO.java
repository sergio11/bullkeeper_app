package sanchez.sanchez.sergio.bullkeeper.sse.models;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonProperty;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

/**
 * Set Messages As Viewed DTO
 */
@JsonIgnoreProperties(ignoreUnknown = true)
public final class SetMessagesAsViewedDTO implements Serializable {

    /**
     * Conversation
     */
    @JsonProperty("conversation")
    private String conversation;

    /**
     * Message Ids
     */
    @JsonProperty("messageIds")
    private ArrayList<String> messageIds;

    public SetMessagesAsViewedDTO(){}

    /**
     *
     * @param conversation
     * @param messageIds
     */
    public SetMessagesAsViewedDTO(final String conversation, final ArrayList<String> messageIds) {
        this.conversation = conversation;
        this.messageIds = messageIds;
    }

    public String getConversation() {
        return conversation;
    }

    public void setConversation(String conversation) {
        this.conversation = conversation;
    }

    public ArrayList<String> getMessageIds() {
        return messageIds;
    }

    public void setMessageIds(ArrayList<String> messageIds) {
        this.messageIds = messageIds;
    }

    @Override
    public String toString() {
        return "SetMessagesAsViewedDTO{" +
                "conversation='" + conversation + '\'' +
                ", messageIds=" + messageIds +
                '}';
    }
}
