package sanchez.sanchez.sergio.bullkeeper.ui.activity.alertlist;

import sanchez.sanchez.sergio.bullkeeper.ui.support.IBasicActivityHandler;

/**
 * Alert List Activity Handler
 */
public interface IAlertListActivityHandler extends IBasicActivityHandler {

    /**
     * Go to Alert Detail
     * @param identity
     */
    void goToAlertDetail(final String identity);


    /**
     * On Apply Changes
     */
    void onApplyChanges();

}
