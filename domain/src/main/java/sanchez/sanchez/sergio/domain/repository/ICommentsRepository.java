package sanchez.sanchez.sergio.domain.repository;

import io.reactivex.Observable;
import sanchez.sanchez.sergio.domain.models.CommentsStatisticsBySocialMediaEntity;

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


}
