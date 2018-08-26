package sanchez.sanchez.sergio.bullkeeper.ui.activity.mykidsdetail;

import sanchez.sanchez.sergio.bullkeeper.ui.support.IBasicActivityHandler;

/**
 * My Kids Detail Activity Handler
 */
public interface IMyKidsDetailActivityHandler extends IBasicActivityHandler {

    /**
     * Navigate To Alerts
     */
    void navigateToAlerts();

    /**
     * Navigate To Alerts Detail
     * @param identity
     */
    void navigateToAlertDetail(final String identity);

}
