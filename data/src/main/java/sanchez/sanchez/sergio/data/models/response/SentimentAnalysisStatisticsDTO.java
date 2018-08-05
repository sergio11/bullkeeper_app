package sanchez.sanchez.sergio.data.models.response;

import com.fasterxml.jackson.annotation.JsonProperty;

import java.io.Serializable;
import java.util.List;

/**
 * Sentiment Analysis Statistics DTO
 */
public final class SentimentAnalysisStatisticsDTO implements Serializable {


    @JsonProperty("title")
    private String title;

    @JsonProperty("subtitle")
    private String subtitle;

    @JsonProperty("sentiment_data")
    private List<SentimentDTO> sentimentData;


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

    public class SentimentDTO {

        @JsonProperty("type")
        private String type;

        @JsonProperty("score")
        private Float score;

        @JsonProperty("label")
        private String label;

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
