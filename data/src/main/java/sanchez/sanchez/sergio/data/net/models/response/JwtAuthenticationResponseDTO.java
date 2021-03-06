package sanchez.sanchez.sergio.data.net.models.response;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonProperty;

import java.io.Serializable;

/**
 * Jwt Authentication Response DTO
 */
@JsonIgnoreProperties(ignoreUnknown = true)
public final class JwtAuthenticationResponseDTO implements Serializable {

    /**
     * Identity
     */
    @JsonProperty("identity")
    private String identity;

    /**
     * Token
     */
    @JsonProperty("token")
    private String token;


    public String getIdentity() {
        return identity;
    }

    public void setIdentity(String identity) {
        this.identity = identity;
    }

    public String getToken() {
        return token;
    }

    public void setToken(String token) {
        this.token = token;
    }

    @Override
    public String toString() {
        return "JwtAuthenticationResponseDTO{" +
                "identity='" + identity + '\'' +
                ", token='" + token + '\'' +
                '}';
    }
}
