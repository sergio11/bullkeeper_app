package sanchez.sanchez.sergio.data.mapper.impl;

import sanchez.sanchez.sergio.data.mapper.AbstractDataMapper;
import sanchez.sanchez.sergio.data.net.models.response.CommentDTO;
import sanchez.sanchez.sergio.domain.models.AdultLevelEnum;
import sanchez.sanchez.sergio.domain.models.BullyingLevelEnum;
import sanchez.sanchez.sergio.domain.models.CommentEntity;
import sanchez.sanchez.sergio.domain.models.DrugsLevelEnum;
import sanchez.sanchez.sergio.domain.models.SentimentLevelEnum;
import sanchez.sanchez.sergio.domain.models.SocialMediaEnum;
import sanchez.sanchez.sergio.domain.models.ViolenceLevelEnum;
import timber.log.Timber;

/**
 * Comment Entity Data Mapper
 */
public final class CommentEntityDataMapper
    extends AbstractDataMapper<CommentDTO, CommentEntity> {

    /**
     * Transform
     * @param originModel
     * @return
     */
    @Override
    public CommentEntity transform(CommentDTO originModel) {
        final CommentEntity commentEntity = new CommentEntity();

        commentEntity.setIdentity(originModel.getIdentity());
        commentEntity.setAuthorName(originModel.getAuthorName());
        commentEntity.setAuthorPhoto(originModel.getAuthorPhoto());
        commentEntity.setCreatedTime(originModel.getCreatedTime());
        commentEntity.setExtractedAt(originModel.getExtractedAt());
        commentEntity.setExtractedAtSince(originModel.getExtractedAtSince());
        commentEntity.setLikes(originModel.getLikes());
        commentEntity.setMessage(originModel.getMessage());


        // Parse Adult Level
        try {
            commentEntity.setAdultLevelEnum(AdultLevelEnum.valueOf(originModel.getAdult()));
        } catch (final IllegalArgumentException ex) {
            Timber.e("Unknown Adult Level");
            commentEntity.setAdultLevelEnum(null);
        }

        // Parse Bullying Level
        try {
            commentEntity.setBullyingLevelEnum(BullyingLevelEnum.valueOf(originModel.getBullying()));
        } catch (final IllegalArgumentException ex) {
            Timber.e("Unknown Bullying level");
            commentEntity.setBullyingLevelEnum(null);
        }

        // Parse Drugs Level Enum
        try {
            commentEntity.setDrugsLevelEnum(DrugsLevelEnum.valueOf(originModel.getDrugs()));
        } catch (final IllegalArgumentException ex) {
            Timber.e("Unknown Drugs Level");
            commentEntity.setDrugsLevelEnum(null);
        }

        // Parse Sentiment Level Enum
        try {
            commentEntity.setSentimentLevelEnum(SentimentLevelEnum.valueOf(originModel.getSentiment()));
        } catch (final IllegalArgumentException ex) {
            Timber.e("Unknown Sentiment Level");
            commentEntity.setSentimentLevelEnum(null);
        }

        // Parse Violence Level Enum
        try {
            commentEntity.setViolenceLevelEnum(ViolenceLevelEnum.valueOf(originModel.getViolence()));
        } catch (final IllegalArgumentException ex) {
            Timber.e("Unknown Violence Level");
            commentEntity.setViolenceLevelEnum(null);
        }

        // Parse Social Media
        try {
            commentEntity.setSocialMedia(SocialMediaEnum.valueOf(originModel.getSocialMedia()));
        } catch (final IllegalArgumentException ex) {
            Timber.e("Unknown Social Media");
            commentEntity.setSocialMedia(null);
        }

        return commentEntity;
    }

    /**
     * Transform Inverse
     * @param originModel
     * @return
     */
    @Override
    public CommentDTO transformInverse(CommentEntity originModel) {
        final CommentDTO commentDTO = new CommentDTO();
        commentDTO.setIdentity(originModel.getIdentity());
        commentDTO.setAuthorName(originModel.getAuthorName());
        commentDTO.setAuthorPhoto(originModel.getAuthorPhoto());
        commentDTO.setCreatedTime(originModel.getCreatedTime());
        commentDTO.setExtractedAt(originModel.getExtractedAt());
        commentDTO.setExtractedAtSince(originModel.getExtractedAtSince());
        commentDTO.setMessage(originModel.getMessage());
        commentDTO.setLikes(originModel.getLikes());

        if(originModel.getAdultLevelEnum() != null)
            commentDTO.setAdult(originModel.getAdultLevelEnum().name());

        if(originModel.getBullyingLevelEnum() != null)
            commentDTO.setBullying(originModel.getBullyingLevelEnum().name());

        if (originModel.getDrugsLevelEnum() != null)
            commentDTO.setDrugs(originModel.getDrugsLevelEnum().name());

        if(originModel.getSentimentLevelEnum() != null)
            commentDTO.setSentiment(originModel.getSentimentLevelEnum().name());

        if(originModel.getViolenceLevelEnum() != null)
            commentDTO.setViolence(originModel.getViolenceLevelEnum().name());

        if(originModel.getSocialMedia() != null)
            commentDTO.setSocialMedia(originModel.getSocialMedia().name());
        return commentDTO;
    }
}
