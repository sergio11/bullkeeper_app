package sanchez.sanchez.sergio.data.models.response;

import com.fasterxml.jackson.annotation.JsonProperty;

import java.io.Serializable;
import java.util.List;

/**
 * Social Media Likes Statistics DTO
 */
public final class SocialMediaLikesStatisticsDTO implements Serializable {

    @JsonProperty("title")
    private String title;

    @JsonProperty("subtitle")
    private String subtitle;

    @JsonProperty("likes")
    private List<SocialMediaLikesDTO> data;

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getSubtitle() {
        return subtitle;
    }

    public void setSubtitle(String subtitle) {
        this.subtitle = subtitle;
    }

    public List<SocialMediaLikesDTO> getData() {
        return data;
    }

    public void setData(List<SocialMediaLikesDTO> data) {
        this.data = data;
    }

    class SocialMediaLikesDTO {

        @JsonProperty("type")
        private String type;

        @JsonProperty("likes")
        private Integer likes;

        @JsonProperty("label")
        private String label;

        public String getType() {
            return type;
        }

        public void setType(String type) {
            this.type = type;
        }

        public Integer getLikes() {
            return likes;
        }

        public void setLikes(Integer likes) {
            this.likes = likes;
        }

        public String getLabel() {
            return label;
        }

        public void setLabel(String label) {
            this.label = label;
        }

        @Override
        public String toString() {
            return "SocialMediaLikesDTO{" +
                    "type='" + type + '\'' +
                    ", likes=" + likes +
                    ", label='" + label + '\'' +
                    '}';
        }
    }

    @Override
    public String toString() {
        return "SocialMediaLikesStatisticsDTO{" +
                "title='" + title + '\'' +
                ", subtitle='" + subtitle + '\'' +
                ", data=" + data +
                '}';
    }
}
