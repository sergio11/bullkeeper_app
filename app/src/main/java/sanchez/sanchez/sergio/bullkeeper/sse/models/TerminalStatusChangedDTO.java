package sanchez.sanchez.sergio.bullkeeper.sse.models;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonProperty;

import java.io.Serializable;

/**
 * Terminal Status Changed DTO
 **/
@JsonIgnoreProperties(ignoreUnknown = true)
public final class TerminalStatusChangedDTO implements Serializable {

    /**
     * Kid
     */
    @JsonProperty("kid")
    private String kid;

    /**
     * Terminal
     */
    @JsonProperty("terminal")
    private String terminal;


    /**
     * Status
     */
    @JsonProperty("status")
    private String status;


    public TerminalStatusChangedDTO(){}

    /**
     *
     * @param kid
     * @param terminal
     * @param status
     */
    public TerminalStatusChangedDTO(final String kid, final String terminal, final String status) {
        this.kid = kid;
        this.terminal = terminal;
        this.status = status;
    }

    public String getKid() {
        return kid;
    }

    public void setKid(String kid) {
        this.kid = kid;
    }

    public String getTerminal() {
        return terminal;
    }

    public void setTerminal(String terminal) {
        this.terminal = terminal;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    @Override
    public String toString() {
        return "TerminalStatusChangedDTO{" +
                "kid='" + kid + '\'' +
                ", terminal='" + terminal + '\'' +
                ", status='" + status + '\'' +
                '}';
    }
}
