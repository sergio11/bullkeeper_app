package sanchez.sanchez.sergio.domain.models;

import java.io.Serializable;
import java.util.List;

/**
 * Comments By Social Media Entity
 */
public final class CommentsStatisticsBySocialMediaEntity implements Serializable {

    private String title;

    private List<CommentsBySocialMediaEntity> commentsBySocialMediaEntities;


    public CommentsStatisticsBySocialMediaEntity(){}

    /**
     *
     * @param title
     * @param commentsBySocialMediaEntities
     */
    public CommentsStatisticsBySocialMediaEntity(String title, List<CommentsBySocialMediaEntity> commentsBySocialMediaEntities) {
        this.title = title;
        this.commentsBySocialMediaEntities = commentsBySocialMediaEntities;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public List<CommentsBySocialMediaEntity> getCommentsBySocialMediaEntities() {
        return commentsBySocialMediaEntities;
    }

    public void setCommentsBySocialMediaEntities(List<CommentsBySocialMediaEntity> commentsBySocialMediaEntities) {
        this.commentsBySocialMediaEntities = commentsBySocialMediaEntities;
    }

    public static class CommentsBySocialMediaEntity implements Serializable {

        /**
         * Social Media Type Enum
         */
        private SocialMediaTypeEnum socialMediaTypeEnum;

        /**
         * Total
         */
        private int total;

        /**
         * Label
         */
        private String label;


        public CommentsBySocialMediaEntity(){}

        public CommentsBySocialMediaEntity(SocialMediaTypeEnum socialMediaTypeEnum, int total, String label) {
            this.socialMediaTypeEnum = socialMediaTypeEnum;
            this.total = total;
            this.label = label;
        }

        public SocialMediaTypeEnum getSocialMediaTypeEnum() {
            return socialMediaTypeEnum;
        }

        public void setSocialMediaTypeEnum(SocialMediaTypeEnum socialMediaTypeEnum) {
            this.socialMediaTypeEnum = socialMediaTypeEnum;
        }

        public int getTotal() {
            return total;
        }

        public void setTotal(int total) {
            this.total = total;
        }

        public String getLabel() {
            return label;
        }

        public void setLabel(String label) {
            this.label = label;
        }
    }
}
