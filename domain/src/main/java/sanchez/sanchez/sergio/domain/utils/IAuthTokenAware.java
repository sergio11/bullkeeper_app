package sanchez.sanchez.sergio.domain.utils;

/**
 * Auth Token Aware
 */
public interface IAuthTokenAware {

    /**
     * Get Auth Token
     * @return
     */
    String getAuthToken();

    /**
     * Set Auth Token
     * @param token
     */
    void setAuthToken(final String token);
}
