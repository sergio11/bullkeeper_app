package sanchez.sanchez.sergio.domain.models;

import java.io.Serializable;
import java.util.List;

/**
 * Social Media Likes Statistics Entity
 */
public final class SocialMediaLikesStatisticsEntity implements Serializable {

    private String title;

    private String subtitle;

    private List<SocialMediaLikesEntity> socialMediaLikesEntities;

    public SocialMediaLikesStatisticsEntity(){}

    /**
     *
     * @param title
     * @param subtitle
     * @param socialMediaLikesEntities
     */
    public SocialMediaLikesStatisticsEntity(String title, String subtitle, List<SocialMediaLikesEntity> socialMediaLikesEntities) {
        this.title = title;
        this.subtitle = subtitle;
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

        private SocialMediaEnum type;
        private int likes;
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
