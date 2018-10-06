package sanchez.sanchez.sergio.data.net.models.response;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonProperty;
import java.io.Serializable;
import java.util.List;

/**
 * Sentiment Analysis Statistics DTO
 */
@JsonIgnoreProperties(ignoreUnknown = true)
public final class SentimentAnalysisStatisticsDTO implements Serializable {

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
     * Sentiment Data
     */
    @JsonProperty("sentiment_data")
    private List<SentimentDTO> sentimentData;

    public SentimentAnalysisStatisticsDTO(){}

    /**
     *
     * @param title
     * @param subtitle
     * @param sentimentData
     */
    public SentimentAnalysisStatisticsDTO(final String title, final String subtitle,
                                          final List<SentimentDTO> sentimentData) {
        this.title = title;
        this.subtitle = subtitle;
        this.sentimentData = sentimentData;
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

    public List<SentimentDTO> getSentimentData() {
        return sentimentData;
    }

    public void setSentimentData(List<SentimentDTO> sentimentData) {
        this.sentimentData = sentimentData;
    }

    /**
     * Sentiment DTO
     */
    public class SentimentDTO {

        /**
         * Type
         */
        @JsonProperty("type")
        private String type;

        /**
         * Score
         */
        @JsonProperty("score")
        private Float score;

        /**
         * Label
         */
        @JsonProperty("label")
        private String label;

        public SentimentDTO(){}

        /**
         * Sentiment DTO
         * @param type
         * @param score
         * @param label
         */
        public SentimentDTO(String type, Float score, String label) {
            this.type = type;
            this.score = score;
            this.label = label;
        }

        public String getType() {
            return type;
        }

        public void setType(String type) {
            this.type = type;
        }

        public Float getScore() {
            return score;
        }

        public void setScore(Float score) {
            this.score = score;
        }

        public String getLabel() {
            return label;
        }

        public void setLabel(String label) {
            this.label = label;
        }

        @Override
        public String toString() {
            return "SentimentDTO{" +
                    "type='" + type + '\'' +
                    ", score=" + score +
                    ", label='" + label + '\'' +
                    '}';
        }
    }

    @Override
    public String toString() {
        return "SentimentAnalysisStatisticsDTO{" +
                "title='" + title + '\'' +
                ", subtitle='" + subtitle + '\'' +
                ", sentimentData=" + sentimentData +
                '}';
    }
}
