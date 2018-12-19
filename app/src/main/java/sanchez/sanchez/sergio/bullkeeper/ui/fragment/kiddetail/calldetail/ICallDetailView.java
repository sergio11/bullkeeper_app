package sanchez.sanchez.sergio.bullkeeper.ui.fragment.kiddetail.calldetail;

import sanchez.sanchez.sergio.bullkeeper.core.ui.ISupportView;
import sanchez.sanchez.sergio.domain.models.CallDetailEntity;

/**
 * Call Detail View
 */
public interface ICallDetailView extends ISupportView {

    /**
     * On Call Detail Loaded
     * @param callDetailEntity
     */
    void onCallDetailLoaded(final CallDetailEntity callDetailEntity);

}
