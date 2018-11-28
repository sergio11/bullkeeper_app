package sanchez.sanchez.sergio.bullkeeper.ui.activity.kidsresults;

import sanchez.sanchez.sergio.bullkeeper.core.ui.ISupportView;
import sanchez.sanchez.sergio.domain.models.KidEntity;


/**
 * Kids Results View
 */
public interface IKidsResultsView extends ISupportView {

    /**
     * On Son Loaded
     * @param kidEntity
     */
    void onSonLoaded(final KidEntity kidEntity);

}
