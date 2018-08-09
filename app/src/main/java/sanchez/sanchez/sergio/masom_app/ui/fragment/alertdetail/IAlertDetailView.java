package sanchez.sanchez.sergio.masom_app.ui.fragment.alertdetail;

import sanchez.sanchez.sergio.domain.models.AlertEntity;
import sanchez.sanchez.sergio.masom_app.ui.support.ISupportView;

public interface IAlertDetailView extends ISupportView {

    /**
     * On Alert info loaded
     * @param alertEntity
     */
    void onAlertInfoLoaded(final AlertEntity alertEntity);

}
