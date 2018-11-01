package sanchez.sanchez.sergio.bullkeeper.ui.fragment.timeallowance;

import sanchez.sanchez.sergio.bullkeeper.core.ui.ISupportLCEView;
import sanchez.sanchez.sergio.domain.models.ScreenTimeAllowanceEntity;

/**
 * Time Allowance Fragment View
 */
interface ITimeAllowanceFragmentView extends ISupportLCEView<ScreenTimeAllowanceEntity.TimeAllowancePerDayEntity> {

    /**
     * Set Remaining Time
     * @param remainingTime
     */
    void setRemainingTime(final int remainingTime);

}
