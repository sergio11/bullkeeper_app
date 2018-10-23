package sanchez.sanchez.sergio.bullkeeper.ui.activity.kidsresults;

import sanchez.sanchez.sergio.bullkeeper.core.ui.ISupportView;
import sanchez.sanchez.sergio.domain.models.SonEntity;


/**
 * Kids Results View
 */
public interface IKidsResultsView extends ISupportView {

    /**
     * On Son Loaded
     * @param sonEntity
     */
    void onSonLoaded(final SonEntity sonEntity);

}
