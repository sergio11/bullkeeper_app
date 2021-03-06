package sanchez.sanchez.sergio.domain.repository;

import java.util.List;
import io.reactivex.Observable;
import sanchez.sanchez.sergio.domain.models.AdultLevelEnum;
import sanchez.sanchez.sergio.domain.models.BullyingLevelEnum;
import sanchez.sanchez.sergio.domain.models.CommentEntity;
import sanchez.sanchez.sergio.domain.models.DrugsLevelEnum;
import sanchez.sanchez.sergio.domain.models.SentimentLevelEnum;
import sanchez.sanchez.sergio.domain.models.ViolenceLevelEnum;

/**
 * Comments Repository
 */
public interface ICommentsRepository {


    /**
     * Get Comments
     * @param identities
     * @param daysAgo
     * @param socialMedia
     * @param author
     * @param violenceLevelEnum
     * @param drugsLevelEnum
     * @param bullyingLevelEnum
     * @param adultLevelEnum
     * @param sentimentLevelEnum
     * @return
     */
    Observable<List<CommentEntity>> getComments(
            final String[] identities,
            final int daysAgo,
            final String[] socialMedia,
            final String author,
            final ViolenceLevelEnum violenceLevelEnum,
            final DrugsLevelEnum drugsLevelEnum,
            final BullyingLevelEnum bullyingLevelEnum,
            final AdultLevelEnum adultLevelEnum,
            final SentimentLevelEnum sentimentLevelEnum);

    /**
     * Get Comments
     * @param identities
     * @param daysAgo
     * @param socialMedia
     * @param violenceLevelEnum
     * @param drugsLevelEnum
     * @param bullyingLevelEnum
     * @param adultLevelEnum
     * @param sentimentLevelEnum
     * @return
     */
    Observable<List<CommentEntity>> getComments(
            final String[] identities,
            final int daysAgo,
            final String[] socialMedia,
            final ViolenceLevelEnum violenceLevelEnum,
            final DrugsLevelEnum drugsLevelEnum,
            final BullyingLevelEnum bullyingLevelEnum,
            final AdultLevelEnum adultLevelEnum,
            final SentimentLevelEnum sentimentLevelEnum);


    /**
     * Get Comments
     * @param identities
     * @param daysAgo
     * @param socialMedia
     * @param sentimentLevelEnum
     * @return
     */
    Observable<List<CommentEntity>> getComments(
            final String[] identities,
            final int daysAgo,
            final String[] socialMedia,
            final SentimentLevelEnum sentimentLevelEnum);

    /**
     * Get Comments
     * @param identities
     * @param daysAgo
     * @return
     */
    Observable<List<CommentEntity>> getComments(
            final String[] identities,
            final int daysAgo,
            final SentimentLevelEnum sentimentLevelEnum);

    /**
     * Get Comment By Id
     * @param id
     * @return
     */
    Observable<CommentEntity> getCommentById(final String id);

}
