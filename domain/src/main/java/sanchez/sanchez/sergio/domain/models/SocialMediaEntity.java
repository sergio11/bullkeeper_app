package sanchez.sanchez.sergio.domain.models;

import java.io.Serializable;

/**
 * Social Media Entity
 */
public final class SocialMediaEntity implements Serializable {

    private String identity;
    private String accessToken;
    private String refreshToken;
    private SocialMediaTypeEnum type;
    private Boolean invalidToken;
    private String userSocialName;
    private String userPicture;
    private String userFullName;
    private String kid;

    public SocialMediaEntity(){}

    public SocialMediaEntity(String identity, String accessToken, String refreshToken,
                             SocialMediaTypeEnum type, Boolean invalidToken,
                             String userSocialName, String userPicture, String userFullName, String kid) {
        this.identity = identity;
        this.accessToken = accessToken;
        this.refreshToken = refreshToken;
        this.type = type;
        this.invalidToken = invalidToken;
        this.userSocialName = userSocialName;
        this.userPicture = userPicture;
        this.userFullName = userFullName;
        this.kid = kid;
    }

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

    public SocialMediaTypeEnum getType() {
        return type;
    }

    public void setType(SocialMediaTypeEnum type) {
        this.type = type;
    }

    public Boolean hasInvalidToken() {
        return invalidToken;
    }

    public void setInvalidToken(Boolean invalidToken) {
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

    public String getUserFullName() {
        return userFullName;
    }

    public void setUserFullName(String userFullName) {
        this.userFullName = userFullName;
    }

    public String getKid() {
        return kid;
    }

    public void setKid(String kid) {
        this.kid = kid;
    }

    @Override
    public String toString() {
        return "SocialMediaEntity{" +
                "identity='" + identity + '\'' +
                ", accessToken='" + accessToken + '\'' +
                ", refreshToken='" + refreshToken + '\'' +
                ", type=" + type +
                ", invalidToken=" + invalidToken +
                ", userSocialName='" + userSocialName + '\'' +
                ", userPicture='" + userPicture + '\'' +
                ", userFullName='" + userFullName + '\'' +
                '}';
    }
}
