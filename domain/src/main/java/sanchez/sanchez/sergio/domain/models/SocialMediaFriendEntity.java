package sanchez.sanchez.sergio.domain.models;

import java.io.Serializable;

/**
 * Social Media Friend Entity
 */
public final class SocialMediaFriendEntity implements Serializable {

    private String id;
    private String name;
    private String profileImage;
    private SocialMediaEnum socialMediaEnum = SocialMediaEnum.FACEBOOK;
    private String value;

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getProfileImage() {
        return profileImage;
    }

    public void setProfileImage(String profileImage) {
        this.profileImage = profileImage;
    }

    public SocialMediaEnum getSocialMediaEnum() {
        return socialMediaEnum;
    }

    public void setSocialMediaEnum(SocialMediaEnum socialMediaEnum) {
        this.socialMediaEnum = socialMediaEnum;
    }

    public String getValue() {
        return value;
    }

    public void setValue(String value) {
        this.value = value;
    }
}
