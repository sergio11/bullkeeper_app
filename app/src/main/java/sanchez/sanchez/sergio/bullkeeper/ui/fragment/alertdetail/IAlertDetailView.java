package sanchez.sanchez.sergio.bullkeeper.ui.fragment.alertdetail;

import sanchez.sanchez.sergio.domain.models.AlertEntity;
import sanchez.sanchez.sergio.bullkeeper.core.ui.ISupportView;

public interface IAlertDetailView extends ISupportView {

    /**
     * On Alert info loaded
     * @param alertEntity
     */
    void onAlertInfoLoaded(final AlertEntity alertEntity);

    /**
     * On Alert not found
     */
    void onAlertNotFound();

    /**
     * On Alert Deleted
     */
    void onAlertDeleted();

}
