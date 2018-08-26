package sanchez.sanchez.sergio.data.net.models.request;

import com.fasterxml.jackson.annotation.JsonProperty;
import java.io.Serializable;

/**
 * Reset Password Request DTO
 */
public final class ResetPasswordRequestDTO implements Serializable {

    @JsonProperty("email")
    private String email;

    public ResetPasswordRequestDTO(){}

    public ResetPasswordRequestDTO(String email) {
        this.email = email;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    @Override
    public String toString() {
        return "ResetPasswordRequestDTO{" +
                "email='" + email + '\'' +
                '}';
    }
}
