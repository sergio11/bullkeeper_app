package sanchez.sanchez.sergio.data.net.models.response;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonProperty;
import java.io.Serializable;
import java.util.List;

/**
 * Social Media Likes Statistics DTO
 */
@JsonIgnoreProperties(ignoreUnknown = true)
public final class SocialMediaLikesStatisticsDTO implements Serializable {

    /**
     * Title
     */
    @JsonProperty("title")
    private String title;

    /**
     * Subtitle
     */
    @JsonProperty("subtitle")
    private String subtitle;

    /**
     * Total Likes
     */
    @JsonProperty("total_likes")
    private int totalLikes;

    /**
     * Likes
     */
    @JsonProperty("likes")
    private List<SocialMediaLikesDTO> data;

    public SocialMediaLikesStatisticsDTO(){}

    /**
     *
     * @param title
     * @param subtitle
     * @param data
     */
    public SocialMediaLikesStatisticsDTO(final String title, final String subtitle,
                                         final int totalLikes, final List<SocialMediaLikesDTO> data) {
        this.title = title;
        this.subtitle = subtitle;
        this.totalLikes = totalLikes;
        this.data = data;
    }

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

    public int getTotalLikes() {
        return totalLikes;
    }

    public void setTotalLikes(int totalLikes) {
        this.totalLikes = totalLikes;
    }

    public List<SocialMediaLikesDTO> getData() {
        return data;
    }

    public void setData(List<SocialMediaLikesDTO> data) {
        this.data = data;
    }

    public static class SocialMediaLikesDTO {

        @JsonProperty("type")
        private String type;

        @JsonProperty("likes")
        private Integer likes;

        @JsonProperty("label")
        private String label;

        public SocialMediaLikesDTO(){}

        public SocialMediaLikesDTO(String type, Integer likes, String label) {
            this.type = type;
            this.likes = likes;
            this.label = label;
        }

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
