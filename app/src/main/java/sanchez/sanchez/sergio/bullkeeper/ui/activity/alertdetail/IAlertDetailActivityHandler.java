package sanchez.sanchez.sergio.bullkeeper.ui.activity.alertdetail;

import sanchez.sanchez.sergio.bullkeeper.core.ui.IBasicActivityHandler;

/**
 * Alert Detail Activity Handler
 */
public interface IAlertDetailActivityHandler extends IBasicActivityHandler {


    /**
     * Go To Kid Statistics
     * @param kid
     */
    void goToKidStatistics(final String kid);

    /**
     * Go To Edit Kid
     * @param kid
     */
    void goToEditKid(final String kid);

    /**
     * Go To General Statistics
     */
    void goToGeneralStatistics();

    /**
     * Go To Information Extraction
     * @param kid
     */
    void goToInformationExtraction(final String kid);

    /**
     * Go To Geofences
     * @param kid
     */
    void goToGeofences(final String kid);

    /**
     * Go To Apps Installed
     * @param kid
     */
    void goToAppsInstalled(final String kid);

    /**
     * Go To Terminals
     * @param kid
     */
    void goToTerminals(final String kid);

    /**
     * Go To Contacts
     * @param kid
     */
    void goToContacts(final String kid);

    /**
     * Go To Phone Numbers
     * @param kid
     */
    void goToPhoneNumbers(final String kid);

    /**
     * Go To Fun Time
     * @param kid
     */
    void goToFunTime(final String kid);

    /**
     * Go To Scheduled Block List
     * @param kid
     */
    void goToScheduledBlockList(final String kid);


    /**
     * Go To Kid Request List
     * @param kid
     */
    void goToKidRequestList(final String kid);
}
