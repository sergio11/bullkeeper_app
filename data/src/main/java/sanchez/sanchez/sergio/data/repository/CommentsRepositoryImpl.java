package sanchez.sanchez.sergio.data.repository;

import com.fernandocejas.arrow.checks.Preconditions;

import java.util.List;
import io.reactivex.Observable;
import sanchez.sanchez.sergio.data.mapper.AbstractDataMapper;
import sanchez.sanchez.sergio.data.net.models.response.CommentDTO;
import sanchez.sanchez.sergio.data.net.services.ICommentsService;
import sanchez.sanchez.sergio.domain.models.AdultLevelEnum;
import sanchez.sanchez.sergio.domain.models.BullyingLevelEnum;
import sanchez.sanchez.sergio.domain.models.CommentEntity;
import sanchez.sanchez.sergio.domain.models.DrugsLevelEnum;
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
     * Comments Data Mapper
     */
    private final AbstractDataMapper<CommentDTO, CommentEntity> commentsDataMapper;

    /**
     * Comments Repository Impl
     * @param commentsService
     * @param commentsDataMapper
     */
    public CommentsRepositoryImpl(
            final ICommentsService commentsService,
            final AbstractDataMapper<CommentDTO, CommentEntity> commentsDataMapper
    ) {
        this.commentsService = commentsService;
        this.commentsDataMapper = commentsDataMapper;
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

    /**
     * Get Comment By id
     * @param id
     * @return
     */
    @Override
    public Observable<CommentEntity> getCommentById(String id) {
        Preconditions.checkNotNull(id, "Id can not be null");
        Preconditions.checkState(!id.isEmpty(), "Id can not be empty");

        return commentsService.getCommentById(id)
                .map(response -> response != null && response.getData() != null ?
                    response.getData(): null)
                .map(commentsDataMapper::transform);
    }

}
