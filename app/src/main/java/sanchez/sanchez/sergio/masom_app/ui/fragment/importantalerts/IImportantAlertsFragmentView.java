package sanchez.sanchez.sergio.masom_app.ui.fragment.importantalerts;


import java.util.List;

import sanchez.sanchez.sergio.domain.models.AlertEntity;
import sanchez.sanchez.sergio.masom_app.ui.support.ISupportView;

/**
 * Important Alerts Fragment View
 */
interface IImportantAlertsFragmentView extends ISupportView {

    /**
     * On Alerts Loaded
     * @param alertEntityList
     */
    void onAlertsLoaded(final List<AlertEntity> alertEntityList);

}
