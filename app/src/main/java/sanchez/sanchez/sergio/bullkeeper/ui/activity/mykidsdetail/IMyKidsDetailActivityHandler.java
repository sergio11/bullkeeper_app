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
     * @param childId
     * @param identity
     */
    void navigateToSaveScheduledBlock(final String childId, final String identity);

    /**
     * Navigate To Save Scheduled Block
     */
    void navigateToSaveScheduledBlock();

    /**
     * Show App Rules Dialog
     */
    void showAppRulesDialog();

    /**
     * Show Family Locator Dialog
     */
    void showFamilyLocatorDialog();

    /**
     * Navigate To Terminal Detail
     * @param childId
     * @param terminalId
     */
    void navigateToTerminalDetail(final String childId, final String terminalId);

    /**
     * Navigate To App Installed Detail
     * @param kid
     * @param terminal
     * @param app
     */
    void navigateToAppInstalledDetail(final String kid, final String terminal, final String app);

    /**
     * Navigate To SMS Detail
     * @param kid
     * @param terminal
     * @param sms
     */
    void navigateToSmsDetail(final String kid, final String terminal, final String sms);

    /**
     * Navigate To Call Detail
     * @param kid
     * @param terminal
     * @param call
     */
    void navigateToCallDetail(final String kid, final String terminal, final String call);

    /**
     * Navigate To Contact Detail
     * @param kid
     * @param terminal
     * @param contact
     */
    void navigateToContactDetail(final String kid, final String terminal, final String contact);

    /**
     * Navigate To Phone Numbers Black List
     * @param kid
     * @param terminal
     */
    void navigateToPhoneNumbersBlackList(final String kid, final String terminal);

    /**
     * Navigate To Kid Request Detail
     * @param kid
     * @param identity
     */
    void navigateToKidRequestDetail(final String kid, final String identity);

    /**
     * Navigate To Geofences
     * @param kid
     */
    void navigateToGeofences(final String kid);

    /**
     * Navigate To Save Geofence
     * @param kid
     */
    void navigateToSaveGeofence(final String kid);

    /**
     * Navigate To Day Scheduled Detail
     * @param kid
     * @param terminal
     * @param day
     */
    void navigateToDayScheduledDetail(final String kid, final String terminal,
                                      final String day, final boolean isFunTimeEnabled);

}
