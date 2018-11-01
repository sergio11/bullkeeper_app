package sanchez.sanchez.sergio.bullkeeper.ui.activity.mykidsdetail;

import sanchez.sanchez.sergio.bullkeeper.core.ui.IBasicActivityHandler;

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
     * @param alertId
     * @param sonId
     */
    void navigateToAlertDetail(final String alertId, final String sonId);

    /**
     * Navigate To Warning Alerts
     * @param sonId
     */
    void navigateToWarningAlerts(final String sonId);

    /**
     * Navigate To Save Scheduled Block
     * @param identity
     */
    void navigateToSaveScheduledBlock(final String identity);

    /**
     * Navigate To Save Scheduled Block
     */
    void navigateToSaveScheduledBlock();

    /**
     * Show App Rules Dialog
     */
    void showAppRulesDialog();

}
