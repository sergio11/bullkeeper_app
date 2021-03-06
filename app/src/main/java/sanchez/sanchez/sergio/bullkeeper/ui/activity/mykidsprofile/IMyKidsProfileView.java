package sanchez.sanchez.sergio.bullkeeper.ui.activity.mykidsprofile;

import java.util.LinkedHashMap;
import java.util.List;
import sanchez.sanchez.sergio.bullkeeper.core.ui.ISupportView;
import sanchez.sanchez.sergio.domain.models.KidEntity;
import sanchez.sanchez.sergio.domain.models.SocialMediaEntity;


/**
 * My Kids Profile View
 */
public interface IMyKidsProfileView extends ISupportView {

    /**
     * on Kid Guardian Loaded
     * @param kidEntity
     */
    void onKidLoaded(final KidEntity kidEntity);

    /**
     * On Kid Load Failed
     */
    void onKidLoadFailed();


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

    /**
     * On Supervised Children Confirmed Not Found
     */
    void onSupervisedChildrenConfirmedNotFound();

    /**
     * On Edition Not Allowed
     */
    void onEditionNotAllowed();

}
