package sanchez.sanchez.sergio.data.mapper.impl;

import java.util.ArrayList;
import java.util.List;
import sanchez.sanchez.sergio.data.mapper.AbstractDataMapper;
import sanchez.sanchez.sergio.data.net.models.response.CommentsStatisticsBySocialMediaDTO;
import sanchez.sanchez.sergio.domain.models.CommentsStatisticsBySocialMediaEntity;

/**
 * Comments By Social Media Data Mapper
 */
public final class CommentsBySocialMediaDataMapper extends AbstractDataMapper<CommentsStatisticsBySocialMediaDTO,
        CommentsStatisticsBySocialMediaEntity> {

    /**
     * Transform
     * @param originModel
     * @return
     */
    @Override
    public CommentsStatisticsBySocialMediaEntity transform(final CommentsStatisticsBySocialMediaDTO originModel) {
        final CommentsStatisticsBySocialMediaEntity commentsStatisticsBySocialMediaEntity = new CommentsStatisticsBySocialMediaEntity();
        commentsStatisticsBySocialMediaEntity.setTitle(originModel.getTitle());
        commentsStatisticsBySocialMediaEntity.setSubtitle(originModel.getSubtitle());
        commentsStatisticsBySocialMediaEntity.setTotalComments(originModel.getTotalComments());
        final List<CommentsStatisticsBySocialMediaEntity.CommentsBySocialMediaEntity> commentsBySocialMediaEntities = new ArrayList<>();
        for(final CommentsStatisticsBySocialMediaDTO.CommentsBySocialMediaDTO commentsBySocialMediaDTO: originModel.getData()) {
            final CommentsStatisticsBySocialMediaEntity.CommentsBySocialMediaEntity commentsBySocialMediaEntity = new CommentsStatisticsBySocialMediaEntity.CommentsBySocialMediaEntity();
            commentsBySocialMediaEntity.setLabel(commentsBySocialMediaDTO.getLabel());
            commentsBySocialMediaEntity.setTotal(commentsBySocialMediaDTO.getTotal());
            commentsBySocialMediaEntity.setSocialMediaTypeEnum(commentsBySocialMediaDTO.getSocialMediaTypeEnum());
            commentsBySocialMediaEntities.add(commentsBySocialMediaEntity);
        }
        commentsStatisticsBySocialMediaEntity.setCommentsBySocialMediaEntities(commentsBySocialMediaEntities);
        return commentsStatisticsBySocialMediaEntity;
    }

    /**
     * Transform Inverse
     * @param originModel
     * @return
     */
    @Override
    public CommentsStatisticsBySocialMediaDTO transformInverse(final CommentsStatisticsBySocialMediaEntity originModel) {
        throw new UnsupportedOperationException();
    }
}
