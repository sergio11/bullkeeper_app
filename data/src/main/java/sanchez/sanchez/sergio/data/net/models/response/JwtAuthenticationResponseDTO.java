package sanchez.sanchez.sergio.data.net.models.response;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonProperty;

import java.io.Serializable;

/**
 * Jwt Authentication Response DTO
 */
@JsonIgnoreProperties(ignoreUnknown = true)
public final class JwtAuthenticationResponseDTO implements Serializable {

    @JsonProperty("token")
    private String token;


    public String getToken() {
        return token;
    }

    public void setToken(String token) {
        this.token = token;
    }

    @Override
    public String toString() {
        return "JwtAuthenticationResponseDTO{" +
                "token='" + token + '\'' +
                '}';
    }
}
