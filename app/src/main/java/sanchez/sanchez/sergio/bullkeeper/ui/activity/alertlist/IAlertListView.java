package sanchez.sanchez.sergio.bullkeeper.ui.activity.alertlist;

import sanchez.sanchez.sergio.bullkeeper.ui.support.ISupportLCEView;
import sanchez.sanchez.sergio.domain.models.AlertEntity;

/**
 * Alert List View
 */
public interface IAlertListView extends ISupportLCEView<AlertEntity> {

    /**
     * On Alerts Cleared
     */
    void onAlertsCleared();


}
