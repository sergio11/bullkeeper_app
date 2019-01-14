package sanchez.sanchez.sergio.bullkeeper.ui.fragment.kiddetail.funtime;

import sanchez.sanchez.sergio.bullkeeper.core.ui.ISupportLCEView;
import sanchez.sanchez.sergio.domain.models.DayScheduledEntity;

/**
 * Fun Time Fragment View
 */
interface IFunTimeFragmentView extends ISupportLCEView<DayScheduledEntity> {

    /**
     * Set Fun Time Status
     * @param isEnabled
     */
    void setFunTimeStatus(final boolean isEnabled);

}
