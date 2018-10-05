package sanchez.sanchez.sergio.data.repository;

import com.fernandocejas.arrow.checks.Preconditions;
import io.reactivex.Observable;
import sanchez.sanchez.sergio.data.mapper.AbstractDataMapper;
import sanchez.sanchez.sergio.data.net.models.response.CommentsStatisticsBySocialMediaDTO;
import sanchez.sanchez.sergio.data.net.services.ICommentsService;
import sanchez.sanchez.sergio.domain.models.CommentsStatisticsBySocialMediaEntity;
import sanchez.sanchez.sergio.domain.repository.ICommentsRepository;

/**
 * Comments Repository Impl
 */
public final class CommentsRepositoryImpl implements ICommentsRepository {

    /**
     * Comments Service
     */
    private final ICommentsService commentsService;

    /**
     * Comments Statistics Data Mapper
     */
    private final AbstractDataMapper<CommentsStatisticsBySocialMediaDTO,
            CommentsStatisticsBySocialMediaEntity>  commentsStatisticsDataMapper;

    /**
     * Comments Repository Impl
     * @param commentsService
     * @param commentsStatisticsDataMapper
     */
    public CommentsRepositoryImpl(final ICommentsService commentsService, final AbstractDataMapper<CommentsStatisticsBySocialMediaDTO,
            CommentsStatisticsBySocialMediaEntity> commentsStatisticsDataMapper) {
        this.commentsService = commentsService;
        this.commentsStatisticsDataMapper = commentsStatisticsDataMapper;
    }

    /**
     * Get Comments Statistics By Social Media
     * @param sonId
     * @return
     */
    @Override
    public Observable<CommentsStatisticsBySocialMediaEntity> getCommentsStatisticsBySocialMedia(final String sonId, final int daysAgo) {
        Preconditions.checkNotNull(sonId, "Son Id can not be null");
        Preconditions.checkState(!sonId.isEmpty(), "Son Id can not be empty");
        Preconditions.checkState(daysAgo > 0, "Days Ago must be greater  than 0");

        return commentsService.getCommentsStatisticsBySocialMedia(new String[]{ sonId }, daysAgo)
                .map(response -> response != null && response.getData() != null ?
                    response.getData() : null)
                .map(commentsStatisticsDataMapper::transform);
    }
}
