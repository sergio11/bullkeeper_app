package sanchez.sanchez.sergio.domain.models;

import java.io.Serializable;
import java.util.List;

/**
 * Social Media Likes Statistics Entity
 */
public final class SocialMediaLikesStatisticsEntity implements Serializable {

    /**
     * Title
     */
    private String title;

    /**
     * Subtitle
     */
    private String subtitle;

    /**
     * Total Likes
     */
    private int totalLikes;

    /**
     * Social Media Likes Entities
     */
    private List<SocialMediaLikesEntity> socialMediaLikesEntities;

    public SocialMediaLikesStatisticsEntity(){}

    /**
     *
     * @param title
     * @param subtitle
     * @param totalLikes
     * @param socialMediaLikesEntities
     */
    public SocialMediaLikesStatisticsEntity(String title, String subtitle, final int totalLikes,
                                            List<SocialMediaLikesEntity> socialMediaLikesEntities) {
        this.title = title;
        this.subtitle = subtitle;
        this.totalLikes = totalLikes;
        this.socialMediaLikesEntities = socialMediaLikesEntities;
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

    public List<SocialMediaLikesEntity> getSocialMediaLikesEntities() {
        return socialMediaLikesEntities;
    }

    public void setSocialMediaLikesEntities(List<SocialMediaLikesEntity> socialMediaLikesEntities) {
        this.socialMediaLikesEntities = socialMediaLikesEntities;
    }

    /**
     * Social Media Likes Entity
     */
    public static class SocialMediaLikesEntity {

        /**
         * Type
         */
        private SocialMediaEnum type;

        /**
         * Likes
         */
        private int likes;

        /**
         * Label
         */
        private String label;

        public SocialMediaLikesEntity(){}

        public SocialMediaLikesEntity(SocialMediaEnum type, int likes, String label) {
            this.type = type;
            this.likes = likes;
            this.label = label;
        }

        public SocialMediaEnum getType() {
            return type;
        }

        public void setType(SocialMediaEnum type) {
            this.type = type;
        }

        public int getLikes() {
            return likes;
        }

        public void setLikes(int likes) {
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
            return "SocialMediaLikesEntity{" +
                    "type=" + type +
                    ", likes=" + likes +
                    ", label='" + label + '\'' +
                    '}';
        }
    }
}
