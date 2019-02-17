package sanchez.sanchez.sergio.bullkeeper.sse.models;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonProperty;
import java.io.Serializable;

/**
 * All Messages Deleted DTO
 */
@JsonIgnoreProperties(ignoreUnknown = true)
public final class AllMessagesDeletedDTO implements Serializable {

    /**
     * Conversation
     */
    @JsonProperty("conversation")
    private String conversation;


    public AllMessagesDeletedDTO(){}

    /**
     * @param conversation
     */
    public AllMessagesDeletedDTO(final String conversation) {
        this.conversation = conversation;
    }

    public String getConversation() {
        return conversation;
    }

    public void setConversation(String conversation) {
        this.conversation = conversation;
    }

    @Override
    public String toString() {
        return "AllMessagesDeletedDTO{" +
                "conversation='" + conversation + '\'' +
                '}';
    }
}
