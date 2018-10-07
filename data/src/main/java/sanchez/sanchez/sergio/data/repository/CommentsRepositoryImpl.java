package sanchez.sanchez.sergio.data.repository;

import com.fernandocejas.arrow.checks.Preconditions;

import java.util.List;
import io.reactivex.Observable;
import sanchez.sanchez.sergio.data.mapper.AbstractDataMapper;
import sanchez.sanchez.sergio.data.net.models.response.CommentDTO;
import sanchez.sanchez.sergio.data.net.models.response.CommentsStatisticsBySocialMediaDTO;
import sanchez.sanchez.sergio.data.net.models.response.SocialMediaLikesStatisticsDTO;
import sanchez.sanchez.sergio.data.net.services.ICommentsService;
import sanchez.sanchez.sergio.domain.models.AdultLevelEnum;
import sanchez.sanchez.sergio.domain.models.BullyingLevelEnum;
import sanchez.sanchez.sergio.domain.models.CommentEntity;
import sanchez.sanchez.sergio.domain.models.CommentsStatisticsBySocialMediaEntity;
import sanchez.sanchez.sergio.domain.models.DrugsLevelEnum;
import sanchez.sanchez.sergio.domain.models.SocialMediaLikesStatisticsEntity;
import sanchez.sanchez.sergio.domain.models.ViolenceLevelEnum;
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
     * Social Media Likes Statistics Data Mapper
     */
    private final AbstractDataMapper<SocialMediaLikesStatisticsDTO, SocialMediaLikesStatisticsEntity>
            socialMediaLikesStatisticsDataMapper;

    /**
     * Comments Data Mapper
     */
    private final AbstractDataMapper<CommentDTO, CommentEntity> commentsDataMapper;

    /**
     * Comments Repository Impl
     * @param commentsService
     * @param commentsStatisticsDataMapper
     * @param socialMediaLikesStatisticsDataMapper
     * @param commentsDataMapper
     */
    public CommentsRepositoryImpl(final ICommentsService commentsService, final AbstractDataMapper<CommentsStatisticsBySocialMediaDTO,
            CommentsStatisticsBySocialMediaEntity> commentsStatisticsDataMapper,
                                  final AbstractDataMapper<SocialMediaLikesStatisticsDTO, SocialMediaLikesStatisticsEntity>
                                          socialMediaLikesStatisticsDataMapper,
                                  final AbstractDataMapper<CommentDTO, CommentEntity> commentsDataMapper) {
        this.commentsService = commentsService;
        this.commentsStatisticsDataMapper = commentsStatisticsDataMapper;
        this.socialMediaLikesStatisticsDataMapper = socialMediaLikesStatisticsDataMapper;
        this.commentsDataMapper = commentsDataMapper;
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

    /**
     * Get Social Media Likes
     * @param kidIdentity
     * @param daysAgo
     * @return
     */
    @Override
    public Observable<SocialMediaLikesStatisticsEntity> getSocialMediaLikesStatistics(final String kidIdentity, int daysAgo) {
        Preconditions.checkNotNull(kidIdentity, "Kid identity can not be null");
        Preconditions.checkState(!kidIdentity.isEmpty(), "Kid identity can not be empty");
        Preconditions.checkState(daysAgo > 0, "Days ago must be greater than 0");

        return commentsService.getSocialMediaLikesStatistics(new String[] { kidIdentity }, daysAgo)
                .map(response -> response != null && response.getData() != null ?
                    response.getData() : null)
                .map(socialMediaLikesStatisticsDataMapper::transform);

    }

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
    @Override
    public Observable<List<CommentEntity>> getComments(final String[] identities, final int daysAgo, final String[] socialMedia, final String author,
                                                       final ViolenceLevelEnum violenceLevelEnum, final DrugsLevelEnum drugsLevelEnum,
                                                       final BullyingLevelEnum bullyingLevelEnum, final AdultLevelEnum adultLevelEnum) {
        Preconditions.checkNotNull(identities, "Identities can not be null");
        Preconditions.checkState(identities.length > 0, "Identities can not be empty");
        Preconditions.checkState(daysAgo > 0, "Days Ago must be greater than 0");
        Preconditions.checkNotNull(socialMedia, "Social Media can not be null");
        Preconditions.checkState(socialMedia.length > 0, "Social Media can not be empty");
        Preconditions.checkNotNull(author, "Author identity can not be null");
        Preconditions.checkState(!author.isEmpty(), "Author identity can not be empty");
        Preconditions.checkNotNull(violenceLevelEnum, "Violence Level can not be null");
        Preconditions.checkNotNull(drugsLevelEnum, "Drugs Level can not be null");
        Preconditions.checkNotNull(bullyingLevelEnum, "Bullying Level can not be null");
        Preconditions.checkNotNull(adultLevelEnum, "Adult Level can not be null");

        return commentsService.getComments(identities, author, socialMedia,
                daysAgo , violenceLevelEnum.name(), drugsLevelEnum.name(),
                bullyingLevelEnum.name(), adultLevelEnum.name())
                .map(response -> response != null && response.getData() != null ?
                        response.getData(): null)
                .map(commentsDataMapper::transform);
    }

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
    @Override
    public Observable<List<CommentEntity>> getComments(String[] identities, int daysAgo, String[] socialMedia, ViolenceLevelEnum violenceLevelEnum,
                                                       DrugsLevelEnum drugsLevelEnum, BullyingLevelEnum bullyingLevelEnum, AdultLevelEnum adultLevelEnum) {
        Preconditions.checkNotNull(identities, "Identities can not be null");
        Preconditions.checkState(identities.length > 0, "Identities can not be empty");
        Preconditions.checkState(daysAgo > 0, "Days Ago must be greater than 0");
        Preconditions.checkNotNull(socialMedia, "Social Media can not be null");
        Preconditions.checkState(socialMedia.length > 0, "Social Media can not be empty");
        Preconditions.checkNotNull(violenceLevelEnum, "Violence Level can not be null");
        Preconditions.checkNotNull(drugsLevelEnum, "Drugs Level can not be null");
        Preconditions.checkNotNull(bullyingLevelEnum, "Bullying Level can not be null");
        Preconditions.checkNotNull(adultLevelEnum, "Adult Level can not be null");

        return commentsService.getComments(identities, socialMedia,
                daysAgo , violenceLevelEnum.name(), drugsLevelEnum.name(),
                bullyingLevelEnum.name(), adultLevelEnum.name())
                .map(response -> response != null && response.getData() != null ?
                        response.getData(): null)
                .map(commentsDataMapper::transform);
    }

    /**
     * Get Comments
     * @param identities
     * @param daysAgo
     * @param socialMedia
     * @return
     */
    @Override
    public Observable<List<CommentEntity>> getComments(String[] identities, int daysAgo, String[] socialMedia) {
        Preconditions.checkNotNull(identities, "Identities can not be null");
        Preconditions.checkState(identities.length > 0, "Identities can not be empty");
        Preconditions.checkState(daysAgo > 0, "Days Ago must be greater than 0");
        Preconditions.checkNotNull(socialMedia, "Social Media can not be null");
        Preconditions.checkState(socialMedia.length > 0, "Social Media can not be empty");

        return commentsService.getComments(identities, socialMedia, daysAgo)
                .map(response -> response != null && response.getData() != null ?
                        response.getData() : null)
                .map(commentsDataMapper::transform);
    }


    /**
     * Get Comments
     * @param identities
     * @param daysAgo
     * @return
     */
    @Override
    public Observable<List<CommentEntity>> getComments(String[] identities, int daysAgo) {
        Preconditions.checkNotNull(identities, "Identities can not be null");
        Preconditions.checkState(identities.length > 0, "Identities can not be empty");
        Preconditions.checkState(daysAgo > 0, "Days Ago must be greater than 0");

        return commentsService.getComments(identities, daysAgo)
                .map(response -> response != null && response.getData() != null ?
                    response.getData() : null)
                .map(commentsDataMapper::transform);
    }




}
