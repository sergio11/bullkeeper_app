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

        private SocialMediaEnum socialMediaEnum;
        private Float score;
        private String label;

        public SentimentEntity(){}

        /**
         *
         * @param socialMediaEnum
         * @param score
         * @param label
         */
        public SentimentEntity(final SocialMediaEnum socialMediaEnum, Float score, String label) {
            this.socialMediaEnum = socialMediaEnum;
            this.score = score;
            this.label = label;
        }

        public SocialMediaEnum getSocialMediaEnum() {
            return socialMediaEnum;
        }

        public void setSocialMediaEnum(SocialMediaEnum socialMediaEnum) {
            this.socialMediaEnum = socialMediaEnum;
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
    }


}
