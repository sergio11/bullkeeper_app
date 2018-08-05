package sanchez.sanchez.sergio.data.models.response;

import com.fasterxml.jackson.annotation.JsonProperty;

import java.io.Serializable;
import java.util.List;

/**
 * Most Active Friends DTO
 */
public final class MostActiveFriendsDTO implements Serializable {

    @JsonProperty("title")
    private String title;

    @JsonProperty("users")
    private List<UserDTO> users;

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public List<UserDTO> getUsers() {
        return users;
    }

    public void setUsers(List<UserDTO> users) {
        this.users = users;
    }

    class UserDTO {

        @JsonProperty("external_id")
        private String id;

        @JsonProperty("name")
        private String name;

        @JsonProperty("profile_image")
        private String profileImage;

        @JsonProperty("social_media")
        private String socialMediaType;

        @JsonProperty("value")
        private Long value;

        @JsonProperty("value_label")
        private String valueLabel;


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

        public String getSocialMediaType() {
            return socialMediaType;
        }

        public void setSocialMediaType(String socialMediaType) {
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
            return "UserDTO{" +
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
        return "MostActiveFriendsDTO{" +
                "title='" + title + '\'' +
                ", users=" + users +
                '}';
    }
}
