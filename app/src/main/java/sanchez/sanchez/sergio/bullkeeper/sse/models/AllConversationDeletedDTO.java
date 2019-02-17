package sanchez.sanchez.sergio.bullkeeper.sse.models;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonProperty;
import java.io.Serializable;

/**
 * All Conversation Deleted DTO
 */
@JsonIgnoreProperties(ignoreUnknown = true)
public final class AllConversationDeletedDTO implements Serializable {

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


    public AllConversationDeletedDTO(){}

    /**
     *
     * @param memberOne
     * @param memberTwo
     */
    public AllConversationDeletedDTO(final String memberOne, final String memberTwo) {
        this.memberOne = memberOne;
        this.memberTwo = memberTwo;
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
        return "AllConversationDeletedDTO{" +
                "memberOne='" + memberOne + '\'' +
                ", memberTwo='" + memberTwo + '\'' +
                '}';
    }
}
