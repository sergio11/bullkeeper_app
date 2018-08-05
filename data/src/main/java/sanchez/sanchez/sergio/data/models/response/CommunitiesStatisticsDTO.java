package sanchez.sanchez.sergio.data.models.response;

import com.fasterxml.jackson.annotation.JsonProperty;

import java.io.Serializable;
import java.util.List;

/**
 * Communities Statistics DTO
 */
public final class CommunitiesStatisticsDTO implements Serializable {

    @JsonProperty("title")
    private String title;

    @JsonProperty("communities")
    private List<CommunityDTO> communities;

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public List<CommunityDTO> getCommunities() {
        return communities;
    }

    public void setCommunities(List<CommunityDTO> communities) {
        this.communities = communities;
    }

    class CommunityDTO {

        @JsonProperty("type")
        private String type;

        @JsonProperty("value")
        private Integer value;

        @JsonProperty("label")
        private String label;

        public String getType() {
            return type;
        }

        public void setType(String type) {
            this.type = type;
        }

        public Integer getValue() {
            return value;
        }

        public void setValue(Integer value) {
            this.value = value;
        }

        public String getLabel() {
            return label;
        }

        public void setLabel(String label) {
            this.label = label;
        }

        @Override
        public String toString() {
            return "CommunityDTO{" +
                    "type='" + type + '\'' +
                    ", value=" + value +
                    ", label='" + label + '\'' +
                    '}';
        }
    }

    @Override
    public String toString() {
        return "CommunitiesStatisticsDTO{" +
                "title='" + title + '\'' +
                ", communities=" + communities +
                '}';
    }
}
