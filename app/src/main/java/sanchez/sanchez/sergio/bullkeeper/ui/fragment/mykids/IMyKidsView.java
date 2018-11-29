package sanchez.sanchez.sergio.bullkeeper.ui.fragment.mykids;

import sanchez.sanchez.sergio.bullkeeper.core.ui.ISupportLCEView;
import sanchez.sanchez.sergio.domain.models.SupervisedChildrenEntity;

/**
 * My Kids View
 */
public interface IMyKidsView extends ISupportLCEView<SupervisedChildrenEntity> {

    /**
     * On Pending Requests Avaliable
     */
    void onPendingRequestsAvailable(final long count);

}
