package sanchez.sanchez.sergio.domain.models;


import java.io.Serializable;

/**
 * Social Media Friend Entity
 */
public final class SocialMediaFriendEntity implements Serializable {

    protected String id;
    protected String name;
    protected String profileImage;
    protected SocialMediaEnum socialMediaEnum = SocialMediaEnum.FACEBOOK;
    protected Long value;
    protected String label;

    public SocialMediaFriendEntity(){}

    public SocialMediaFriendEntity(String id, String name, String profileImage, SocialMediaEnum socialMediaEnum, Long value, String label) {
        this.id = id;
        this.name = name;
        this.profileImage = profileImage;
        this.socialMediaEnum = socialMediaEnum;
        this.value = value;
        this.label = label;
    }

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

    public Long getValue() {
        return value;
    }

    public void setValue(Long value) {
        this.value = value;
    }

    public String getLabel() {
        return label;
    }

    public void setLabel(String label) {
        this.label = label;
    }
}
