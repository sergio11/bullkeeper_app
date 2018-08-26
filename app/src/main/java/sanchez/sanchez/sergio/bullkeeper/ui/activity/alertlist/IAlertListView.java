package sanchez.sanchez.sergio.bullkeeper.ui.activity.alertlist;

import java.util.List;
import sanchez.sanchez.sergio.domain.models.AlertEntity;
import sanchez.sanchez.sergio.bullkeeper.ui.support.ISupportView;

/**
 * Alert List View
 */
public interface IAlertListView extends ISupportView {

    /**
     * On Alerts Loaded
     * @param lastAlerts
     */
    void onAlertsLoaded(final List<AlertEntity> lastAlerts);

}
