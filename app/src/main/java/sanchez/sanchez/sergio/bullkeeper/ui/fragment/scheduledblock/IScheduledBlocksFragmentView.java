package sanchez.sanchez.sergio.bullkeeper.ui.fragment.scheduledblock;

import sanchez.sanchez.sergio.bullkeeper.core.ui.ISupportLCEView;
import sanchez.sanchez.sergio.domain.models.ScheduledBlockEntity;

/**
 * Scheduled Blocks Fragment View
 */
interface IScheduledBlocksFragmentView extends ISupportLCEView<ScheduledBlockEntity> {

    /**
     * On Scheduled Block Deleted
     */
    void onScheduledBlockDeleted();

    /**
     * On All Scheduled Blocks Deleted
     */
    void onAllScheduledBlockDeleted();

    /**
     * On Scheduled Block Status Saved
     */
    void onScheduledBlockStatusSaved();

}
