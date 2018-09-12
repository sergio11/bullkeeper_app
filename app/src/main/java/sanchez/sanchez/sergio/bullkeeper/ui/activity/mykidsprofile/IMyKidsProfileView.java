package sanchez.sanchez.sergio.bullkeeper.ui.activity.mykidsprofile;

import java.util.LinkedHashMap;
import java.util.List;

import sanchez.sanchez.sergio.bullkeeper.ui.support.ISupportView;
import sanchez.sanchez.sergio.domain.models.SocialMediaEntity;
import sanchez.sanchez.sergio.domain.models.SonEntity;


/**
 * My Kids Profile View
 */
public interface IMyKidsProfileView extends ISupportView {

    /**
     * Son Entity
     * @param sonEntity
     */
    void onSonProfileLoaded(final SonEntity sonEntity);

    /**
     * On Social Media Loaded
     * @param socialMediaEntities
     */
    void onSocialMediaLoaded(final List<SocialMediaEntity> socialMediaEntities);

    /**
     * On Validation Errors
     * @param errors
     */
    void onValidationErrors(final List<LinkedHashMap<String, String>> errors);

}
