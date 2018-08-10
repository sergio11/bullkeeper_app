package sanchez.sanchez.sergio.masom_app.ui.activity.alertlist;

import java.util.List;
import sanchez.sanchez.sergio.domain.models.AlertEntity;
import sanchez.sanchez.sergio.masom_app.ui.support.ISupportView;

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
