package sanchez.sanchez.sergio.bullkeeper.sse.models;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonProperty;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

/**
 * Delete Messages DTO
 */
@JsonIgnoreProperties(ignoreUnknown = true)
public final class DeletedMessagesDTO implements Serializable {

    /**
     * Conversation
     */
    @JsonProperty("conversation")
    private String conversation;

    /**
     * Ids
     */
    @JsonProperty("ids")
    private ArrayList<String> ids;

    public DeletedMessagesDTO(){}

    /**
     * @param conversation
     * @param ids
     */
    public DeletedMessagesDTO(final String conversation, final ArrayList<String> ids) {
        this.conversation = conversation;
        this.ids = ids;
    }

    public String getConversation() {
        return conversation;
    }

    public void setConversation(String conversation) {
        this.conversation = conversation;
    }

    public ArrayList<String> getIds() {
        return ids;
    }

    public void setIds(ArrayList<String> ids) {
        this.ids = ids;
    }

    @Override
    public String toString() {
        return "DeletedMessagesDTO{" +
                "conversation='" + conversation + '\'' +
                ", ids=" + ids +
                '}';
    }
}
