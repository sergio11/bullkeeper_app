package sanchez.sanchez.sergio.bullkeeper.sse.models;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonProperty;
import java.io.Serializable;

/**
 * Deleted Conversation DTO
 */
@JsonIgnoreProperties(ignoreUnknown = true)
public final class DeletedConversationDTO implements Serializable {

    /**
     * Conversation
     */
    @JsonProperty("conversation")
    private String conversation;

    /**
     * Member One
     */
    @JsonProperty("member_one")
    private String memberOne;

    /**
     * Member Two
     */
    @JsonProperty("member_two")
    private String memberTwo;


    public DeletedConversationDTO(){}

    /**
     *
     * @param conversation
     * @param memberOne
     * @param memberTwo
     */
    public DeletedConversationDTO(final String conversation,
                                  final String memberOne,
                                  final String memberTwo) {
        this.conversation = conversation;
        this.memberOne = memberOne;
        this.memberTwo = memberTwo;
    }

    public String getConversation() {
        return conversation;
    }

    public void setConversation(String conversation) {
        this.conversation = conversation;
    }

    public String getMemberOne() {
        return memberOne;
    }

    public void setMemberOne(String memberOne) {
        this.memberOne = memberOne;
    }

    public String getMemberTwo() {
        return memberTwo;
    }

    public void setMemberTwo(String memberTwo) {
        this.memberTwo = memberTwo;
    }

    @Override
    public String toString() {
        return "DeletedConversationDTO{" +
                "conversation='" + conversation + '\'' +
                ", memberOne='" + memberOne + '\'' +
                ", memberTwo='" + memberTwo + '\'' +
                '}';
    }
}
