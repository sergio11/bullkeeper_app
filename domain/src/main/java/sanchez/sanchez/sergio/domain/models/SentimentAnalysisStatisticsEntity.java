package sanchez.sanchez.sergio.domain.models;

import java.io.Serializable;
import java.util.List;

/**
 * Sentiment Analysis Statistics Entity
 */
public final class SentimentAnalysisStatisticsEntity  implements Serializable {

    /**
     * Title
     */
    private String title;

    /**
     * Subtitle
     */
    private String subtitle;

    /**
     * Sentiment Data
     */
    private List<SentimentEntity> sentimentData;

    public SentimentAnalysisStatisticsEntity(){}

    /**
     *
     * @param title
     * @param subtitle
     * @param sentimentData
     */
    public SentimentAnalysisStatisticsEntity(final String title, final String subtitle,
                                             final List<SentimentEntity> sentimentData) {
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

    public List<SentimentEntity> getSentimentData() {
        return sentimentData;
    }

    public void setSentimentData(List<SentimentEntity> sentimentData) {
        this.sentimentData = sentimentData;
    }

    /**
     * Sentiment Entity
     */
    public static class SentimentEntity {

        /**
         * Sentiment Level Enum
         */
        private SentimentLevelEnum sentimentLevelEnum;

        /**
         * Score
         */
        private Float score;

        /**
         * Label
         */
        private String label;

        public SentimentEntity(){}

        /**
         *
         * @param sentimentLevelEnum
         * @param score
         * @param label
         */
        public SentimentEntity(final SentimentLevelEnum sentimentLevelEnum, Float score, String label) {
            this.sentimentLevelEnum = sentimentLevelEnum;
            this.score = score;
            this.label = label;
        }


        public SentimentLevelEnum getSentimentLevelEnum() {
            return sentimentLevelEnum;
        }

        public void setSentimentLevelEnum(SentimentLevelEnum sentimentLevelEnum) {
            this.sentimentLevelEnum = sentimentLevelEnum;
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
            return "SentimentEntity{" +
                    "sentimentLevelEnum=" + sentimentLevelEnum +
                    ", score=" + score +
                    ", label='" + label + '\'' +
                    '}';
        }
    }

    @Override
    public String toString() {
        return "SentimentAnalysisStatisticsEntity{" +
                "title='" + title + '\'' +
                ", subtitle='" + subtitle + '\'' +
                ", sentimentData=" + sentimentData +
                '}';
    }
}
