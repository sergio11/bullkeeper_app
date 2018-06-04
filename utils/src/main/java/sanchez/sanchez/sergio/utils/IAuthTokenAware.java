package sanchez.sanchez.sergio.utils;

/**
 * Auth Token Aware
 */
public interface IAuthTokenAware {
    void setToken(String token);
    String getToken();
}
