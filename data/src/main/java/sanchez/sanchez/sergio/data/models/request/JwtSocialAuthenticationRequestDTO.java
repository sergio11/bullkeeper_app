package sanchez.sanchez.sergio.data.models.request;

import com.fasterxml.jackson.annotation.JsonProperty;

/**
 * Jwt Social Authentication Request
 */
public final class JwtSocialAuthenticationRequestDTO {

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
        return "JwtSocialAuthenticationRequestDTO{" +
                "token='" + token + '\'' +
                '}';
    }
}
