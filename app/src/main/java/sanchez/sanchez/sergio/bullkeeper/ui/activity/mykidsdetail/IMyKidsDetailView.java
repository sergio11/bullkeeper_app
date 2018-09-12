package sanchez.sanchez.sergio.bullkeeper.ui.activity.mykidsdetail;

import sanchez.sanchez.sergio.bullkeeper.ui.support.ISupportView;
import sanchez.sanchez.sergio.domain.models.SonEntity;


/**
 * My Kids Detail View
 */
public interface IMyKidsDetailView extends ISupportView {

    /**
     * On Son Loaded
     * @param sonEntity
     */
    void onSonLoaded(final SonEntity sonEntity);

}
