package sanchez.sanchez.sergio.bullkeeper.ui.activity.mykidsdetail;

import sanchez.sanchez.sergio.bullkeeper.core.ui.ISupportView;
import sanchez.sanchez.sergio.domain.models.KidEntity;


/**
 * My Kids Detail View
 */
public interface IMyKidsDetailView extends ISupportView {

    /**
     * On Kid Loaded
     * @param kidEntity
     */
    void onKidLoaded(final KidEntity kidEntity);

}
