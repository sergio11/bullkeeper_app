package sanchez.sanchez.sergio.data.net.models.request;

import com.fasterxml.jackson.annotation.JsonProperty;

/**
 * Jwt Social Authentication Request
 */
public final class JwtSocialAuthenticationRequestDTO {

    @JsonProperty("token")
    private String token;

    public JwtSocialAuthenticationRequestDTO(){}

    public JwtSocialAuthenticationRequestDTO(String token) {
        this.token = token;
    }

    public String getToken() {
        return token;
    }

    public void setToken(String token) {
        this.token = token;
    }

    @Override
    public String toString() {
        return "JwtSocialAuthenticationRequestDTO{" +
                "token='" + token + '\'' +
                '}';
    }
}
