package sanchez.sanchez.sergio.bullkeeper.ui.activity.home;

import sanchez.sanchez.sergio.bullkeeper.ui.activity.legal.LegalContentActivity;
import sanchez.sanchez.sergio.bullkeeper.core.ui.IBasicActivityHandler;
import sanchez.sanchez.sergio.domain.models.AlertLevelEnum;

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
     * @param alertId
     * @param sonId
     */
    void goToAlertDetail(final String alertId, final String sonId);

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

    /**
     * Show Legal Content
     * @param legalTypeEnum
     */
    void showLegalContent(final LegalContentActivity.LegalTypeEnum legalTypeEnum);

    /**
     * Show Child Alerts Detail Dialog
     * @param alertLevelEnum
     * @param alertLevelValue
     * @param kidIdentityValue
     */
    void showChildAlertsDetailDialog(final AlertLevelEnum alertLevelEnum,
                                     final String alertLevelValue, final String kidIdentityValue);

}
