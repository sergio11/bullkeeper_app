package sanchez.sanchez.sergio.bullkeeper.ui.activity.userprofile;

import java.util.LinkedHashMap;
import java.util.List;

import sanchez.sanchez.sergio.bullkeeper.ui.support.ISupportView;
import sanchez.sanchez.sergio.domain.models.ParentEntity;


/**
 * User Profile View
 */
public interface IUserProfileView extends ISupportView {

    /**
     * On Self Information Loaded
     * @param parentEntity
     */
    void onSelfInformationLoaded(final ParentEntity parentEntity);

    /**
     * On Self Information Update
     * @param parentEntity
     */
    void onSelfInformationUpdate(final ParentEntity parentEntity);


    /**
     * On Validation Errors
     * @param errors
     */
    void onValidationErrors(final List<LinkedHashMap<String, String>> errors);

    /**
     * On Account Deleted
     */
    void onAccountDeleted();

}
