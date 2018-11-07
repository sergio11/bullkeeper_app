package sanchez.sanchez.sergio.bullkeeper.ui.activity.savescheduledblock;

import java.util.LinkedHashMap;
import java.util.List;
import sanchez.sanchez.sergio.bullkeeper.core.ui.ISupportView;
import sanchez.sanchez.sergio.domain.models.ScheduledBlockEntity;


/**
 * Scheduled Block View
 */
public interface ISaveScheduledBlockView extends ISupportView {


    /**
     * Scheduled Block Entity
     * @param scheduledBlockEntity
     */
    void onScheduledBlockLoaded(final ScheduledBlockEntity scheduledBlockEntity);

    /**
     * On Validation Errors
     * @param errors
     */
    void onValidationErrors(final List<LinkedHashMap<String, String>> errors);

    /**
     * On Scheduled Block Deleted
     */
    void onScheduledBlockDeleted();

    /**
     * On Scheduled Block Saved
     * @param scheduledBlockEntity
     */
    void onScheduledBlockSaved(final ScheduledBlockEntity scheduledBlockEntity);

}
