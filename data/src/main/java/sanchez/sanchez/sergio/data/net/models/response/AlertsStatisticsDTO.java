package sanchez.sanchez.sergio.data.net.models.response;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonProperty;

import java.io.Serializable;
import java.util.List;

/**
 * Alerts Statistics DTO
 */
@JsonIgnoreProperties(ignoreUnknown = true)
public final class AlertsStatisticsDTO implements Serializable {

    @JsonProperty("title")
    private String title;

    @JsonProperty("alerts")
    private List<AlertLevelDTO> data;

    public AlertsStatisticsDTO(){}

    public AlertsStatisticsDTO(String title, List<AlertLevelDTO> data) {
        this.title = title;
        this.data = data;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public List<AlertLevelDTO> getData() {
        return data;
    }

    public void setData(List<AlertLevelDTO> data) {
        this.data = data;
    }

    /**
     * Alert Level DTO
     */
    public static class AlertLevelDTO {

        @JsonProperty("level")
        private String level;

        @JsonProperty("total")
        private Integer total;

        @JsonProperty("label")
        private String label;

        public AlertLevelDTO(){}

        public AlertLevelDTO(String level, Integer total, String label) {
            this.level = level;
            this.total = total;
            this.label = label;
        }

        public String getLevel() {
            return level;
        }

        public void setLevel(String level) {
            this.level = level;
        }

        public Integer getTotal() {
            return total;
        }

        public void setTotal(Integer total) {
            this.total = total;
        }

        public String getLabel() {
            return label;
        }

        public void setLabel(String label) {
            this.label = label;
        }

        @Override
        public String toString() {
            return "AlertLevelDTO{" +
                    "level='" + level + '\'' +
                    ", total=" + total +
                    ", label='" + label + '\'' +
                    '}';
        }
    }

    @Override
    public String toString() {
        return "AlertsStatisticsDTO{" +
                "title='" + title + '\'' +
                ", data=" + data +
                '}';
    }
}
