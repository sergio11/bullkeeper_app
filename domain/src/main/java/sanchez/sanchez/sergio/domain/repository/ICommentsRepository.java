package sanchez.sanchez.sergio.domain.repository;

import io.reactivex.Observable;
import sanchez.sanchez.sergio.domain.models.CommentsStatisticsBySocialMediaEntity;
import sanchez.sanchez.sergio.domain.models.MostActiveFriendsBySocialMediaStatisticsEntity;
import sanchez.sanchez.sergio.domain.models.SocialMediaLikesStatisticsEntity;

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
     * Get Most Active Friends By Social Media
     * @param kidIdentity
     * @param daysAgo
     * @return
     */
    Observable<MostActiveFriendsBySocialMediaStatisticsEntity> getMostActiveFriendsBySocialMedia(final String kidIdentity,
                                                                                                 final int daysAgo);


}
