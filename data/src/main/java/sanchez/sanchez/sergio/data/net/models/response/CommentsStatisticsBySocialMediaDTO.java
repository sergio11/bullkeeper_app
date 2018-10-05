package sanchez.sanchez.sergio.data.net.models.response;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonProperty;
import java.io.Serializable;
import java.util.List;
import sanchez.sanchez.sergio.domain.models.SocialMediaTypeEnum;

/**
 * Comments Statistics By Social Media DTO
 */
@JsonIgnoreProperties(ignoreUnknown = true)
public final class CommentsStatisticsBySocialMediaDTO implements Serializable {

    /**
     * Title
     */
    @JsonProperty("title")
    private String title;

    /**
     * Data
     */
    @JsonProperty("comments")
    private List<CommentsBySocialMediaDTO> data;

    public CommentsStatisticsBySocialMediaDTO(){}

    /**
     *
     * @param title
     * @param data
     */
    public CommentsStatisticsBySocialMediaDTO(final String title, final List<CommentsBySocialMediaDTO> data) {
        this.title = title;
        this.data = data;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public List<CommentsBySocialMediaDTO> getData() {
        return data;
    }

    public void setData(List<CommentsBySocialMediaDTO> data) {
        this.data = data;
    }

    /**
     * Comments By Social Media DTO
     */
    public static class CommentsBySocialMediaDTO {

        /**
         * Social Media Type Enum
         */
        @JsonProperty("social_media_type")
        private SocialMediaTypeEnum socialMediaTypeEnum;

        /**
         * Total
         */
        @JsonProperty("total")
        private int total;

        /**
         * Label
         */
        @JsonProperty("label")
        private String label;

        public CommentsBySocialMediaDTO(){}

        /**
         *
         * @param socialMediaTypeEnum
         * @param total
         * @param label
         */
        public CommentsBySocialMediaDTO(SocialMediaTypeEnum socialMediaTypeEnum, int total, String label) {
            this.socialMediaTypeEnum = socialMediaTypeEnum;
            this.total = total;
            this.label = label;
        }

        public SocialMediaTypeEnum getSocialMediaTypeEnum() {
            return socialMediaTypeEnum;
        }

        public int getTotal() {
            return total;
        }

        public String getLabel() {
            return label;
        }
    }

    @Override
    public String toString() {
        return "CommentsStatisticsBySocialMediaDTO{" +
                "title='" + title + '\'' +
                ", data=" + data +
                '}';
    }
}
