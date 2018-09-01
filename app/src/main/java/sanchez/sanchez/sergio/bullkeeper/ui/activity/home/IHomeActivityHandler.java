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

    /**
     * Go To User Profile
     */
    void goToUserProfile();

    /**
     * Go To Child Detail
     * @param identity
     */
    void goToChildDetail(final String identity);

    /**
     * Go To Add Child
     */
    void goToAddChild();

    /**
     * Show How add Child Help Dialog
     */
    void showHowAddChildHelpDialog();

}
