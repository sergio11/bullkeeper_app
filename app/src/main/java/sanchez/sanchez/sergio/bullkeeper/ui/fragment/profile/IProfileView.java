package sanchez.sanchez.sergio.bullkeeper.ui.fragment.profile;

import sanchez.sanchez.sergio.bullkeeper.core.ui.ISupportView;
import sanchez.sanchez.sergio.domain.models.ChildrenOfSelfGuardianEntity;
import sanchez.sanchez.sergio.domain.models.GuardianEntity;

/**
 * Home View
 */
public interface IProfileView extends ISupportView {

    /**
     * On User Profile Loaded
     * @param guardianEntity
     */
    void onUserProfileLoaded(final GuardianEntity guardianEntity);

    /**
     * On Children Loaded
     * @param children
     */
    void onChildrenLoaded(final ChildrenOfSelfGuardianEntity children);

    /**
     * On No Children Founded
     */
    void onNoChildrenFounded();

}
