package sanchez.sanchez.sergio.data.net.models.response;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonProperty;
import java.io.Serializable;
import java.util.List;

/**
 * Most Active Friends By Social Media Statistics
 */
@JsonIgnoreProperties(ignoreUnknown = true)
public final class MostActiveFriendsBySocialMediaStatisticsDTO
        implements Serializable {

    /**
     * Title
     */
    @JsonProperty("title")
    private String title;

    /**
     * Friends
     */
    @JsonProperty("friends")
    private List<FriendDTO> friends;

    public MostActiveFriendsBySocialMediaStatisticsDTO(){}

    /**
     *
     * @param title
     * @param friends
     */
    public MostActiveFriendsBySocialMediaStatisticsDTO(final String title, final List<FriendDTO> friends) {
        this.title = title;
        this.friends = friends;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public List<FriendDTO> getData() {
        return friends;
    }

    public void setData(List<FriendDTO> data) {
        this.friends = data;
    }

    /**
     * Friend DTO
     */
    public static class FriendDTO implements Serializable {

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
            return "FriendDTO{" +
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
        return "MostActiveFriendsBySocialMediaStatisticsDTO{" +
                "title='" + title + '\'' +
                ", data=" + friends +
                '}';
    }
}
