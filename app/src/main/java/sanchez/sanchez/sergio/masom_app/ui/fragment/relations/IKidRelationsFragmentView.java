package sanchez.sanchez.sergio.masom_app.ui.fragment.relations;


import java.util.List;

import sanchez.sanchez.sergio.domain.models.SocialMediaFriendEntity;
import sanchez.sanchez.sergio.masom_app.ui.support.ISupportView;

/**
 * Kid Relations Fragment View
 */
interface IKidRelationsFragmentView extends ISupportView {

    /**
     * On Kid Relations Loaded
     * @param socialMediaFriendEntityList
     */
    void onKidRelationsLoaded(final List<SocialMediaFriendEntity> socialMediaFriendEntityList);

}
