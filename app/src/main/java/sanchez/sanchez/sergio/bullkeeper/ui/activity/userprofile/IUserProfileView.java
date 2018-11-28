package sanchez.sanchez.sergio.bullkeeper.ui.activity.userprofile;

import java.util.LinkedHashMap;
import java.util.List;

import sanchez.sanchez.sergio.bullkeeper.core.ui.ISupportView;
import sanchez.sanchez.sergio.domain.models.GuardianEntity;


/**
 * User Profile View
 */
public interface IUserProfileView extends ISupportView {

    /**
     * On Self Information Loaded
     * @param guardianEntity
     */
    void onSelfInformationLoaded(final GuardianEntity guardianEntity);

    /**
     * On Self Information Update
     * @param guardianEntity
     */
    void onSelfInformationUpdate(final GuardianEntity guardianEntity);


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
