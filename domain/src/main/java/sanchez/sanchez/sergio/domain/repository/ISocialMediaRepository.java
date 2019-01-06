package sanchez.sanchez.sergio.domain.repository;

import java.util.List;

import io.reactivex.Observable;
import sanchez.sanchez.sergio.domain.models.SocialMediaEntity;

/**
 * Social Media Repository
 */
public interface ISocialMediaRepository {

    /**
     * Get All Social Media By Kid Id
     * @param kid
     * @return
     */
    Observable<List<SocialMediaEntity>> getAllSocialMediaBySonId(final String kid);

    /**
     * Save All Social Media
     * @param kid
     * @param socialMedias
     * @return
     */
    Observable<List<SocialMediaEntity>> saveAllSocialMedia(final String kid,
                                                           final List<SocialMediaEntity> socialMedias);

}
