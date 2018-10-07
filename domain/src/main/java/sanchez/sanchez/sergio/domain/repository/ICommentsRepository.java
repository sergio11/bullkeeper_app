package sanchez.sanchez.sergio.domain.repository;

import java.util.List;

import io.reactivex.Observable;
import sanchez.sanchez.sergio.domain.models.AdultLevelEnum;
import sanchez.sanchez.sergio.domain.models.BullyingLevelEnum;
import sanchez.sanchez.sergio.domain.models.CommentEntity;
import sanchez.sanchez.sergio.domain.models.CommentsStatisticsBySocialMediaEntity;
import sanchez.sanchez.sergio.domain.models.DrugsLevelEnum;
import sanchez.sanchez.sergio.domain.models.SocialMediaLikesStatisticsEntity;
import sanchez.sanchez.sergio.domain.models.ViolenceLevelEnum;

/**
 * Comments Repository
 */
public interface ICommentsRepository {

    /**
     * Get Comments Statustics By Social Media
     * @param sonId
     * @return
     */
    Observable<CommentsStatisticsBySocialMediaEntity>
        getCommentsStatisticsBySocialMedia(final String sonId, final int daysAgo);


    /**
     * Get Social Media Likes Statistics
     * @param kidIdentity
     * @param daysAgo
     * @return
     */
    Observable<SocialMediaLikesStatisticsEntity> getSocialMediaLikesStatistics(final String kidIdentity,
                                                                               final int daysAgo);


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
            final AdultLevelEnum adultLevelEnum);

    /**
     * Get Comments
     * @param identities
     * @param daysAgo
     * @param socialMedia
     * @param violenceLevelEnum
     * @param drugsLevelEnum
     * @param bullyingLevelEnum
     * @param adultLevelEnum
     * @return
     */
    Observable<List<CommentEntity>> getComments(
            final String[] identities,
            final int daysAgo,
            final String[] socialMedia,
            final ViolenceLevelEnum violenceLevelEnum,
            final DrugsLevelEnum drugsLevelEnum,
            final BullyingLevelEnum bullyingLevelEnum,
            final AdultLevelEnum adultLevelEnum);


    /**
     * Get Comments
     * @param identities
     * @param daysAgo
     * @param socialMedia
     * @return
     */
    Observable<List<CommentEntity>> getComments(
            final String[] identities,
            final int daysAgo,
            final String[] socialMedia);

    /**
     * Get Comments
     * @param identities
     * @param daysAgo
     * @return
     */
    Observable<List<CommentEntity>> getComments(
            final String[] identities,
            final int daysAgo);


}
