package sanchez.sanchez.sergio.data.net.models.request;

import com.fasterxml.jackson.annotation.JsonProperty;

import java.io.Serializable;

/**
 * Add Phone Number Blocked DTO
 */
public final class AddPhoneNumberBlockedDTO implements Serializable {


    /**
     * Prefix
     */
    @JsonProperty("prefix")
    private String prefix;

    /**
     * Number
     */
    @JsonProperty("number")
    private String number;

    /**
     * Phone NUmber
     */
    @JsonProperty("phonenumber")
    private String phonenumber;

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
     * @param prefix
     * @param number
     * @param phoneNumber
     * @param terminal
     * @param kid
     */
    public AddPhoneNumberBlockedDTO(final String prefix, final String number,
                                    final String phoneNumber, final String terminal,
                                    final String kid) {
        this.prefix = prefix;
        this.number = number;
        this.phonenumber = phoneNumber;
        this.terminal = terminal;
        this.kid = kid;
    }

    public String getPrefix() {
        return prefix;
    }

    public void setPrefix(String prefix) {
        this.prefix = prefix;
    }

    public String getNumber() {
        return number;
    }

    public void setNumber(String number) {
        this.number = number;
    }

    public String getPhonenumber() {
        return phonenumber;
    }

    public void setPhonenumber(String phonenumber) {
        this.phonenumber = phonenumber;
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
                "prefix='" + prefix + '\'' +
                ", number='" + number + '\'' +
                ", phonenumber='" + phonenumber + '\'' +
                ", terminal='" + terminal + '\'' +
                ", kid='" + kid + '\'' +
                '}';
    }
}
