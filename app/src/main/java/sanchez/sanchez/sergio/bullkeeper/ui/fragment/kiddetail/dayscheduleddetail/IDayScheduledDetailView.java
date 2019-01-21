package sanchez.sanchez.sergio.bullkeeper.ui.fragment.kiddetail.dayscheduleddetail;

import sanchez.sanchez.sergio.bullkeeper.core.ui.ISupportView;
import sanchez.sanchez.sergio.domain.models.DayScheduledEntity;

/**
 * Day Scheduled Detail
 */
public interface IDayScheduledDetailView extends ISupportView {

    /**
     * On Day Scheduled Loaded
     * @param dayScheduledEntity
     */
    void onDayScheduledLoaded(final DayScheduledEntity dayScheduledEntity);


}
