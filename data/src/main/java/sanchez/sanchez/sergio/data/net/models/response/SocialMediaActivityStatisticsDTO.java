package sanchez.sanchez.sergio.data.net.models.response;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonProperty;
import java.io.Serializable;
import java.util.List;

/**
 * Social Media Activity Statistics DTO
 */
@JsonIgnoreProperties(ignoreUnknown = true)
public final class SocialMediaActivityStatisticsDTO implements Serializable {

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
     * Activities
     */
    @JsonProperty("activities")
    private List<ActivityDTO> activities;

    public SocialMediaActivityStatisticsDTO(){}

    /**
     * @param title
     * @param subtitle
     * @param activities
     */
    public SocialMediaActivityStatisticsDTO(String title, String subtitle, List<ActivityDTO> activities) {
        this.title = title;
        this.subtitle = subtitle;
        this.activities = activities;
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

    public List<ActivityDTO> getActivities() {
        return activities;
    }

    public void setActivities(List<ActivityDTO> activities) {
        this.activities = activities;
    }

    /**
     * Activity DTO
     */
    public static class ActivityDTO {

        /**
         * Type
         */
        @JsonProperty("type")
        private String type;

        /**
         * Value
         */
        @JsonProperty("value")
        private Integer value;

        /**
         * Label
         */
        @JsonProperty("label")
        private String label;

        public ActivityDTO(){}

        /**
         * Activity DTO
         * @param type
         * @param value
         * @param label
         */
        public ActivityDTO(String type, Integer value, String label) {
            this.type = type;
            this.value = value;
            this.label = label;
        }

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
            return "ActivityDTO{" +
                    "type='" + type + '\'' +
                    ", value=" + value +
                    ", label='" + label + '\'' +
                    '}';
        }
    }


    @Override
    public String toString() {
        return "SocialMediaActivityStatisticsDTO{" +
                "title='" + title + '\'' +
                ", subtitle='" + subtitle + '\'' +
                ", activities=" + activities +
                '}';
    }
}
