package sanchez.sanchez.sergio.data.repository;

import com.fernandocejas.arrow.checks.Preconditions;
import java.util.List;
import io.reactivex.Observable;
import sanchez.sanchez.sergio.data.mapper.AbstractDataMapper;
import sanchez.sanchez.sergio.data.net.models.request.SaveSocialMediaDTO;
import sanchez.sanchez.sergio.data.net.models.response.SocialMediaDTO;
import sanchez.sanchez.sergio.data.net.services.ISocialMediaService;
import sanchez.sanchez.sergio.domain.models.SocialMediaEntity;
import sanchez.sanchez.sergio.domain.repository.ISocialMediaRepository;

/**
 * Social Media Repository Impl
 */
public final class SocialMediaRepositoryImpl implements ISocialMediaRepository {

    private final ISocialMediaService socialMediaService;
    private final AbstractDataMapper<SocialMediaDTO, SocialMediaEntity> socialMediaEntityDataMapper;
    private final AbstractDataMapper<SaveSocialMediaDTO, SocialMediaEntity> saveSocialMediaDataMapper;

    /**
     *
     * @param socialMediaService
     * @param socialMediaEntityDataMapper
     * @param saveSocialMediaDataMapper
     */
    public SocialMediaRepositoryImpl(final ISocialMediaService socialMediaService,
                                     final AbstractDataMapper<SocialMediaDTO, SocialMediaEntity> socialMediaEntityDataMapper,
                                     final AbstractDataMapper<SaveSocialMediaDTO, SocialMediaEntity> saveSocialMediaDataMapper) {
        this.socialMediaService = socialMediaService;
        this.socialMediaEntityDataMapper = socialMediaEntityDataMapper;
        this.saveSocialMediaDataMapper = saveSocialMediaDataMapper;
    }

    /**
     * Get All Social Media By Son Id
     * @param kid
     * @return
     */
    @Override
    public Observable<List<SocialMediaEntity>> getAllSocialMediaBySonId(String kid) {
        Preconditions.checkNotNull(kid, "Son Id can not be null");
        Preconditions.checkState(!kid.isEmpty(), "Son Id can not be empty");
        return socialMediaService.getAllSocialMediaBySonId(kid)
                .map(response -> response != null && response.getData() != null ? response.getData() : null)
                .map(socialMediaEntityDataMapper::transform);
    }

    /**
     * Save All Social Media
     * @param kid
     * @param socialMedias
     * @return
     */
    @Override
    public Observable<List<SocialMediaEntity>> saveAllSocialMedia(String kid, List<SocialMediaEntity> socialMedias) {
        Preconditions.checkNotNull(kid, "Son Id can not be null");
        Preconditions.checkState(!kid.isEmpty(), "Son Id can not be empty");
        Preconditions.checkNotNull(socialMedias, "Social Media List can not be null");

        return socialMediaService.saveAllSocialMedia(kid, saveSocialMediaDataMapper.transformInverse(socialMedias))
                .map(response -> response != null && response.getData() != null ? response.getData() : null)
                .map(socialMediaEntityDataMapper::transform);
    }
}
