package sanchez.sanchez.sergio.domain.models;

import java.io.Serializable;

/**
 * Authentication Response Entity
 */
public final class AuthenticationResponseEntity implements Serializable {

    /**
     * Identity
     */
    private String identity;

    /**
     * Token
     */
    private String token;

    public AuthenticationResponseEntity(){}

    /**
     *
     * @param identity
     * @param token
     */
    public AuthenticationResponseEntity(String identity, String token) {
        this.identity = identity;
        this.token = token;
    }

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
        return "AuthenticationResponseEntity{" +
                "identity='" + identity + '\'' +
                ", token='" + token + '\'' +
                '}';
    }
}
