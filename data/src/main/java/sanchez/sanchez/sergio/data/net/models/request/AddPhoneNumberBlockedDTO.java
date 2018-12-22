package sanchez.sanchez.sergio.data.net.models.request;

import com.fasterxml.jackson.annotation.JsonProperty;

import java.io.Serializable;

/**
 * Add Phone Number Blocked DTO
 */
public final class AddPhoneNumberBlockedDTO implements Serializable {

    /**
     * Phone Number
     */
    @JsonProperty("phone_number")
    private String phoneNumber;

    /**
     * Terminal
     */
    @JsonProperty("terminal")
    private String terminal;

    /**
     * Kid
     */
    @JsonProperty("kid")
    private String kid;

    public AddPhoneNumberBlockedDTO(){}

    /**
     *
     * @param phoneNumber
     * @param terminal
     * @param kid
     */
    public AddPhoneNumberBlockedDTO(String phoneNumber, String terminal, String kid) {
        this.phoneNumber = phoneNumber;
        this.terminal = terminal;
        this.kid = kid;
    }

    public String getPhoneNumber() {
        return phoneNumber;
    }

    public void setPhoneNumber(String phoneNumber) {
        this.phoneNumber = phoneNumber;
    }

    public String getTerminal() {
        return terminal;
    }

    public void setTerminal(String terminal) {
        this.terminal = terminal;
    }

    public String getKid() {
        return kid;
    }

    public void setKid(String kid) {
        this.kid = kid;
    }

    @Override
    public String toString() {
        return "AddPhoneNumberBlockedDTO{" +
                "phoneNumber='" + phoneNumber + '\'' +
                ", terminal='" + terminal + '\'' +
                ", kid='" + kid + '\'' +
                '}';
    }
}
