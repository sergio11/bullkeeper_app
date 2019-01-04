package sanchez.sanchez.sergio.bullkeeper.ui.activity.kidrequest;

import sanchez.sanchez.sergio.bullkeeper.core.ui.ISupportLCEView;
import sanchez.sanchez.sergio.domain.models.KidRequestEntity;

/**
 * Kid Request List View
 */
public interface IKidRequestListView
        extends ISupportLCEView<KidRequestEntity> {


    /**
     * On All Kid Request Deleted
     */
    void onAllKidRequestDeleted();

    /**
     * On Kid Request Deleted
     */
    void onKidRequestDeleted();

}
