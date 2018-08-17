package sanchez.sanchez.sergio.masom_app.navigation;

import android.support.v7.app.AppCompatActivity;

/**
 * Navigator
 */
public interface INavigator {

    /**
     * Navigate To Intro
     */
    void navigateToIntro();

    /**
     * Navigate To Home
     */
    void navigateToHome();

    /**
     * Show App Menu Dialog
     */
    void showAppMenuDialog(final AppCompatActivity appCompatActivity);

    /**
     * Navigate to My Kids
     */
    void navigateToMyKids();

    /**
     * Navigate To Alert Detail
     */
    void navigateToAlertDetail(final String identity);

    /**
     * Navigate to Alert List
     */
    void navigateToAlertList();

    /**
     * Show Filter Alerts Dialog
     */
    void showFilterAlertsDialog(final AppCompatActivity appCompatActivity);

    /**
     * Navigate To App Tutorial
     */
    void navigateToAppTutorial();

    /**
     * Navigate to User Settings
     */
    void navigateToUserSettings();

    /**
     * Show Question App Dialog
     */
    void showQuestionAppDialog(final AppCompatActivity appCompatActivity);

    /**
     * Navigate to User Profile
     */
    void navigateToUserProfile();

    /**
     * Navigate To My Kids Profile
     */
    void navigateToMyKidsProfile(final String identity);

    /**
     * Navigate To Comments
     * @param identity
     */
    void navigateToComments(final String identity);

    /**
     * Navigate To Comment Detail
     * @param identity
     */
    void navigateToCommentDetail(final String identity);

    /**
     * Navigate To My Kids Detail
     * @param identity
     */
    void navigateToMyKidsDetail(final String identity);

    /**
     * Show Four Dimensions Dialog
     * @param appCompatActivity
     */
    void showFourDimensionsDialog(final AppCompatActivity appCompatActivity,
                                  int dimensionIdx, int value, int total);

}
