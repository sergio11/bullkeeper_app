package sanchez.sanchez.sergio.data.net.models.request;

import com.fasterxml.jackson.annotation.JsonProperty;

/**
 * Authentication Request
 */
public final class JwtAuthenticationRequestDTO {

    /**
     * Email
     */
    @JsonProperty("email")
    private String email;

    /**
     * Password
     */
    @JsonProperty("password")
    private String password;

    public JwtAuthenticationRequestDTO(){}

    /**
     *
     * @param email
     * @param password
     */
    public JwtAuthenticationRequestDTO(String email, String password) {
        this.email = email;
        this.password = password;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    @Override
    public String toString() {
        return "JwtAuthenticationRequestDTO{" +
                "email='" + email + '\'' +
                ", password='" + password + '\'' +
                '}';
    }
}
