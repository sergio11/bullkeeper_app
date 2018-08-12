package sanchez.sanchez.sergio.data.models.response;

import com.fasterxml.jackson.annotation.JsonProperty;

import java.io.Serializable;

/**
 * Social Media DTO
 */
public final class SocialMediaDTO implements Serializable {

    @JsonProperty("identity")
    private String identity;

    @JsonProperty("access_token")
    private String accessToken;

    @JsonProperty("refresh_token")
    private String refreshToken;

    @JsonProperty("type")
    private String type;

    @JsonProperty("invalid_token")
    private String invalidToken;

    @JsonProperty("user_social_name")
    private String userSocialName;

    @JsonProperty("user_picture")
    private String userPicture;

    @JsonProperty("user")
    private String son;


    public String getIdentity() {
        return identity;
    }

    public void setIdentity(String identity) {
        this.identity = identity;
    }

    public String getAccessToken() {
        return accessToken;
    }

    public void setAccessToken(String accessToken) {
        this.accessToken = accessToken;
    }

    public String getRefreshToken() {
        return refreshToken;
    }

    public void setRefreshToken(String refreshToken) {
        this.refreshToken = refreshToken;
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    public String getInvalidToken() {
        return invalidToken;
    }

    public void setInvalidToken(String invalidToken) {
        this.invalidToken = invalidToken;
    }

    public String getUserSocialName() {
        return userSocialName;
    }

    public void setUserSocialName(String userSocialName) {
        this.userSocialName = userSocialName;
    }

    public String getUserPicture() {
        return userPicture;
    }

    public void setUserPicture(String userPicture) {
        this.userPicture = userPicture;
    }

    public String getSon() {
        return son;
    }

    public void setSon(String son) {
        this.son = son;
    }

    @Override
    public String toString() {
        return "SocialMediaDTO{" +
                "identity='" + identity + '\'' +
                ", accessToken='" + accessToken + '\'' +
                ", refreshToken='" + refreshToken + '\'' +
                ", type='" + type + '\'' +
                ", invalidToken='" + invalidToken + '\'' +
                ", userSocialName='" + userSocialName + '\'' +
                ", userPicture='" + userPicture + '\'' +
                ", son='" + son + '\'' +
                '}';
    }
}