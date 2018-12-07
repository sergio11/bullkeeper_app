package sanchez.sanchez.sergio.data.net.models.request;

import com.fasterxml.jackson.annotation.JsonProperty;

import java.io.Serializable;

/**
 * Add Message DTO
 */
public final class AddMessageDTO implements Serializable {

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
     *
     * @param text
     * @param from
     * @param to
     */
    public AddMessageDTO(String text, String from, String to) {
        this.text = text;
        this.from = from;
        this.to = to;
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
                "text='" + text + '\'' +
                ", from='" + from + '\'' +
                ", to='" + to + '\'' +
                '}';
    }
}
