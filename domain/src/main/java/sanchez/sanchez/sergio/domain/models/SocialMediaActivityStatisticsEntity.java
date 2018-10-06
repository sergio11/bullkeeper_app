package sanchez.sanchez.sergio.domain.models;

import java.io.Serializable;
import java.util.List;

/**
 * Social Media Activity Statistics
 */
public final class SocialMediaActivityStatisticsEntity implements Serializable {

    /**
     * Title
     */
    private String title;

    /**
     * Subtitle
     */
    private String subtitle;

    /**
     * Activities
     */
    private List<ActivityEntity> activities;


    public SocialMediaActivityStatisticsEntity(){}

    /**
     * Social Media Activity Statistics
     * @param title
     * @param subtitle
     * @param activities
     */
    public SocialMediaActivityStatisticsEntity(final String title, final String subtitle,
                                               final List<ActivityEntity> activities) {
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

    public List<ActivityEntity> getActivities() {
        return activities;
    }

    public void setActivities(List<ActivityEntity> activities) {
        this.activities = activities;
    }

    /**
     * Activity Entity
     */
    public static class ActivityEntity implements Serializable {

        private SocialMediaEnum socialMediaEnum;
        private Integer value;
        private String label;

        public ActivityEntity(){}

        public ActivityEntity(SocialMediaEnum socialMediaEnum, Integer value, String label) {
            this.socialMediaEnum = socialMediaEnum;
            this.value = value;
            this.label = label;
        }

        public SocialMediaEnum getSocialMediaEnum() {
            return socialMediaEnum;
        }

        public void setSocialMediaEnum(SocialMediaEnum socialMediaEnum) {
            this.socialMediaEnum = socialMediaEnum;
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
            return "ActivityEntity{" +
                    "socialMediaEnum=" + socialMediaEnum +
                    ", value=" + value +
                    ", label='" + label + '\'' +
                    '}';
        }
    }


}
