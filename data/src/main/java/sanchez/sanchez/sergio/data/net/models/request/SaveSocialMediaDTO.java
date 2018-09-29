package sanchez.sanchez.sergio.data.net.models.request;

import com.fasterxml.jackson.annotation.JsonProperty;
import java.io.Serializable;

/**
 * Save Social Media DTO
 */
public final class SaveSocialMediaDTO implements Serializable {

    @JsonProperty("identity")
    private String identity;

    @JsonProperty("access_token")
    private String accessToken;

    @JsonProperty("refresh_token")
    private String refreshToken;

    @JsonProperty("type")
    private String type;

    @JsonProperty("user_social_name")
    private String userSocialName;

    @JsonProperty("user_social_full_name")
    private String userSocialFullName;

    @JsonProperty("user_picture")
    private String userPicture;

    @JsonProperty("son")
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

    public String getUserSocialName() {
        return userSocialName;
    }

    public void setUserSocialName(String userSocialName) {
        this.userSocialName = userSocialName;
    }

    public String getUserSocialFullName() {
        return userSocialFullName;
    }

    public void setUserSocialFullName(String userSocialFullName) {
        this.userSocialFullName = userSocialFullName;
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
        return "SaveSocialMediaDTO{" +
                "identity='" + identity + '\'' +
                ", accessToken='" + accessToken + '\'' +
                ", refreshToken='" + refreshToken + '\'' +
                ", type='" + type + '\'' +
                ", userSocialName='" + userSocialName + '\'' +
                ", userSocialFullName='" + userSocialFullName + '\'' +
                ", userPicture='" + userPicture + '\'' +
                ", son='" + son + '\'' +
                '}';
    }
}
