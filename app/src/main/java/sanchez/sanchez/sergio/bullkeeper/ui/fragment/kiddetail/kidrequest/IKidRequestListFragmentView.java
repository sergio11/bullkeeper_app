package sanchez.sanchez.sergio.bullkeeper.ui.fragment.kiddetail.kidrequest;

import sanchez.sanchez.sergio.bullkeeper.core.ui.ISupportLCEView;
import sanchez.sanchez.sergio.domain.models.KidRequestEntity;

/**
 * Kid Request List Fragment View
 */
interface IKidRequestListFragmentView extends ISupportLCEView<KidRequestEntity> {


    /**
     * On Kid Request Deleted
     */
    void onKidRequestDeleted();

}
