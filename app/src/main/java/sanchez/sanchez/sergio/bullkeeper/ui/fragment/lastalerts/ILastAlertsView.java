package sanchez.sanchez.sergio.bullkeeper.ui.fragment.lastalerts;

import sanchez.sanchez.sergio.bullkeeper.ui.support.ISupportLCEView;
import sanchez.sanchez.sergio.domain.models.AlertEntity;

/**
 * Last Alerts View
 */
public interface ILastAlertsView extends ISupportLCEView<AlertEntity> {

    /**
     * On Alert Deleted
     */
    void onAlertDeleted();

}
