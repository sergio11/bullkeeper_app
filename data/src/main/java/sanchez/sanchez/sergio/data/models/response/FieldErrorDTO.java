package sanchez.sanchez.sergio.data.models.response;

import com.fasterxml.jackson.annotation.JsonProperty;

import java.io.Serializable;

/**
 * Field Error DTO
 */
public final class FieldErrorDTO implements Serializable {

    @JsonProperty("field")
    private String field;

    @JsonProperty("message")
    private String message;

    public String getField() {
        return field;
    }

    public void setField(String field) {
        this.field = field;
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }

    @Override
    public String toString() {
        return "FieldErrorDTO{" +
                "field='" + field + '\'' +
                ", message='" + message + '\'' +
                '}';
    }
}
