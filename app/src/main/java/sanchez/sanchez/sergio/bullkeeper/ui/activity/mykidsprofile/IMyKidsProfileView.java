package sanchez.sanchez.sergio.bullkeeper.ui.activity.mykidsprofile;

import sanchez.sanchez.sergio.bullkeeper.ui.support.ISupportView;
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

}
