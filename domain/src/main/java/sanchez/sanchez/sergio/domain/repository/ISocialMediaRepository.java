package sanchez.sanchez.sergio.domain.repository;

import java.util.List;

import io.reactivex.Observable;
import sanchez.sanchez.sergio.domain.models.SocialMediaEntity;

/**
 * Social Media Repository
 */
public interface ISocialMediaRepository {

    /**
     * Get All Social Media By Son Id
     * @param sonId
     * @return
     */
    Observable<List<SocialMediaEntity>> getAllSocialMediaBySonId(final String sonId);

    /**
     * Save All Social Media
     * @param idSon
     * @param socialMedias
     * @return
     */
    Observable<List<SocialMediaEntity>> saveAllSocialMedia(final String idSon,
                                                           final List<SocialMediaEntity> socialMedias);
}
