package sanchez.sanchez.sergio.domain.models;

import java.io.Serializable;
import java.util.List;

/**
 * Most Active Friends By Social Media Statistics Entity
 */
public final class MostActiveFriendsBySocialMediaStatisticsEntity
        implements Serializable {

    /**
     * Title
     */
    private String title;

    /**
     * Friends
     */
    private List<FriendEntity> friends;

    public MostActiveFriendsBySocialMediaStatisticsEntity(){}

    public MostActiveFriendsBySocialMediaStatisticsEntity(String title, List<FriendEntity> friends) {
        this.title = title;
        this.friends = friends;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public List<FriendEntity> getFriends() {
        return friends;
    }

    public void setFriends(List<FriendEntity> friends) {
        this.friends = friends;
    }

    /**
     * Friend Entity
     */
    public static class FriendEntity implements Serializable {

        private String id;
        private String name;
        private String profileImage;
        private SocialMediaEnum socialMediaType;
        private Long value;
        private String valueLabel;

        public FriendEntity(){}

        public FriendEntity(String id, String name, String profileImage, SocialMediaEnum socialMediaType, Long value, String valueLabel) {
            this.id = id;
            this.name = name;
            this.profileImage = profileImage;
            this.socialMediaType = socialMediaType;
            this.value = value;
            this.valueLabel = valueLabel;
        }

        public FriendEntity(String name, Long value) {
            this.name = name;
            this.value = value;
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

        public SocialMediaEnum getSocialMediaType() {
            return socialMediaType;
        }

        public void setSocialMediaType(SocialMediaEnum socialMediaType) {
            this.socialMediaType = socialMediaType;
        }

        public Long getValue() {
            return value;
        }

        public void setValue(Long value) {
            this.value = value;
        }

        public String getValueLabel() {
            return valueLabel;
        }

        public void setValueLabel(String valueLabel) {
            this.valueLabel = valueLabel;
        }

        @Override
        public String toString() {
            return "FriendEntity{" +
                    "id='" + id + '\'' +
                    ", name='" + name + '\'' +
                    ", profileImage='" + profileImage + '\'' +
                    ", socialMediaType='" + socialMediaType + '\'' +
                    ", value=" + value +
                    ", valueLabel='" + valueLabel + '\'' +
                    '}';
        }
    }

    @Override
    public String toString() {
        return "MostActiveFriendsBySocialMediaStatisticsEntity{" +
                "title='" + title + '\'' +
                ", friends=" + friends +
                '}';
    }
}
