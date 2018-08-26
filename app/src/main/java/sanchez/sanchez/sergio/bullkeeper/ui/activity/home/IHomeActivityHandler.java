package sanchez.sanchez.sergio.bullkeeper.ui.activity.home;

import sanchez.sanchez.sergio.bullkeeper.ui.support.IBasicActivityHandler;

/**
 * Home Activity Handler
 */
public interface IHomeActivityHandler extends IBasicActivityHandler {

    /**
     * Go to My Kids
     */
    void goToMyKids();


    /**
     * Go to Alert Detail
     * @param identity
     */
    void goToAlertDetail(final String identity);

    /**
     * Go To Alerts
     */
    void goToAlerts();

}
