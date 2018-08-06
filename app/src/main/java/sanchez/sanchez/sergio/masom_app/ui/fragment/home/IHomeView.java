package sanchez.sanchez.sergio.masom_app.ui.fragment.home;

import java.util.List;
import sanchez.sanchez.sergio.domain.models.AlertEntity;
import sanchez.sanchez.sergio.masom_app.ui.support.ISupportView;

public interface IHomeView extends ISupportView {

    /**
     * On Last Alerts Loaded
     * @param lastAlerts
     */
    void onLastAlertsLoaded(final List<AlertEntity> lastAlerts);

}
