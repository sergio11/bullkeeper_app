package sanchez.sanchez.sergio.data.net.models.response;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonProperty;
import java.io.Serializable;
import java.util.List;

/**
 * Validation Error DTO
 */
@JsonIgnoreProperties(ignoreUnknown = true)
public final class ValidationErrorDTO implements Serializable {

    @JsonProperty("field_errors")
    private List<FieldErrorDTO> fieldErrors;

    public List<FieldErrorDTO> getFieldErrors() {
        return fieldErrors;
    }

    public void setFieldErrors(List<FieldErrorDTO> fieldErrors) {
        this.fieldErrors = fieldErrors;
    }

    @Override
    public String toString() {
        return "ValidationErrorDTO{" +
                "fieldErrors=" + fieldErrors +
                '}';
    }
}
