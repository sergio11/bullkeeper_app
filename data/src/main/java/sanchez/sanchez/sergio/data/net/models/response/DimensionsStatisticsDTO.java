package sanchez.sanchez.sergio.data.net.models.response;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonProperty;
import java.io.Serializable;
import java.util.List;

/**
 * Dimensions Statistics DTO
 */
@JsonIgnoreProperties(ignoreUnknown = true)
public final class DimensionsStatisticsDTO implements Serializable {

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
     * Dimensions
     */
    @JsonProperty("dimensions")
    private List<DimensionDTO> dimensions;

    public DimensionsStatisticsDTO(){}

    /**
     *
     * @param title
     * @param subtitle
     * @param dimensions
     */
    public DimensionsStatisticsDTO(final String title,
                                   final String subtitle,
                                   final List<DimensionDTO> dimensions) {
        this.title = title;
        this.subtitle = subtitle;
        this.dimensions = dimensions;
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

    public List<DimensionDTO> getDimensions() {
        return dimensions;
    }

    public void setDimensions(List<DimensionDTO> dimensions) {
        this.dimensions = dimensions;
    }

    @JsonIgnoreProperties(ignoreUnknown = true)
    public static class DimensionDTO {

        @JsonProperty("type")
        private String type;

        @JsonProperty("value")
        private Integer value;

        @JsonProperty("label")
        private String label;

        public DimensionDTO(){}

        public DimensionDTO(String type, Integer value, String label) {
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
            return "DimensionDTO{" +
                    "type='" + type + '\'' +
                    ", value=" + value +
                    ", label='" + label + '\'' +
                    '}';
        }
    }

    @Override
    public String toString() {
        return "DimensionsStatisticsDTO{" +
                "title='" + title + '\'' +
                ", subtitle='" + subtitle + '\'' +
                ", dimensions=" + dimensions +
                '}';
    }
}
