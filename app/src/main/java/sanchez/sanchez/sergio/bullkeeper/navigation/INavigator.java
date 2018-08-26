package sanchez.sanchez.sergio.bullkeeper.navigation;

import android.support.v7.app.AppCompatActivity;

import sanchez.sanchez.sergio.domain.models.SocialMediaStatusEnum;
import sanchez.sanchez.sergio.domain.models.SocialMediaTypeEnum;

/**
 * Navigator
 */
public interface INavigator {

    /**
     * Navigate To Intro
     */
    void navigateToIntro(final boolean closeSession);

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

    /**
     * Show Photo Viewer Dialog
     * @param appCompatActivity
     * @param photoUrl
     */
    void showPhotoViewerDialog(final AppCompatActivity appCompatActivity,
                               final String photoUrl);

    /**
     * Show Social Media Status Dialog
     * @param appCompatActivity
     * @param socialMediaTypeEnum
     * @param socialMediaStatusEnum
     */
    void showSocialMediaStatusDialog(final AppCompatActivity appCompatActivity,
                                     final SocialMediaTypeEnum socialMediaTypeEnum,
                                     final SocialMediaStatusEnum socialMediaStatusEnum);

    /**
     * Navigate To Kids Results Activity
     * @param identity
     */
    void navigateToKidsResultsActivity(final String identity);

}
