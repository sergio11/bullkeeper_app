package sanchez.sanchez.sergio.bullkeeper.ui.activity.alertlist;

import sanchez.sanchez.sergio.bullkeeper.core.ui.IBasicActivityHandler;

/**
 * Alert List Activity Handler
 */
public interface IAlertListActivityHandler extends IBasicActivityHandler {

    /**
     * Go to Alert Detail
     * @param alertId
     * @param sonId
     */
    void goToAlertDetail(final String alertId, final String sonId);


    /**
     * On Apply Changes
     */
    void onApplyChanges();

}
