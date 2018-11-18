package sanchez.sanchez.sergio.bullkeeper.navigation;

import android.support.annotation.DrawableRes;
import android.support.v7.app.AppCompatActivity;
import sanchez.sanchez.sergio.bullkeeper.ui.activity.legal.LegalContentActivity;
import sanchez.sanchez.sergio.bullkeeper.ui.dialog.NoticeDialogFragment;
import sanchez.sanchez.sergio.domain.models.AlertLevelEnum;
import sanchez.sanchez.sergio.domain.models.DimensionCategoryEnum;
import sanchez.sanchez.sergio.domain.models.SchoolEntity;
import sanchez.sanchez.sergio.domain.models.SentimentLevelEnum;
import sanchez.sanchez.sergio.domain.models.SocialMediaEnum;
import sanchez.sanchez.sergio.domain.models.SocialMediaFriendEntity;
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
    void navigateToAlertDetail(final String alertId, final String sonId);

    /**
     * Navigate to Alert List
     */
    void navigateToAlertList();

    /**
     * Navigate To Alert List
     * @param alertLevelEnum
     */
    void navigateToAlertList(final AlertLevelEnum alertLevelEnum);

    /**
     * Navigate to Alert List
     * @param sonIdentity
     */
    void navigateToAlertList(final String sonIdentity);

    /**
     * Navigate to Alert List
     * @param alertLevelEnum
     * @param sonIdentity
     */
    void navigateToAlertList(final AlertLevelEnum alertLevelEnum, final String sonIdentity);


    /**
     * Navigate To App Tutorial
     */
    void navigateToAppTutorial();

    /**
     * Navigate to User Settings
     */
    void navigateToUserSettings();

    /**
     * Navigate to alerts Settings
     */
    void navigateToAlertsSettings();

    /**
     * Navigate To Alerts Settings With Alert Level Filter Enabled
     */
    void navigateToAlertsSettingsWithAlertLevelFilterEnabled();

    /**
     * Show App Help App Dialog
     */
    void showAppHelpDialog(final AppCompatActivity appCompatActivity, final String title, final String cueVideo);

    /**
     * Navigate to User Profile
     */
    void navigateToUserProfile();

    /**
     * Navigate To My Kids Profile
     */
    void navigateToMyKidsProfile(final String identity);

    /**
     * Navigate To Add Kids
     */
    void navigateToAddKids();


    /**
     * Navigate To Comments
     * @param identity
     */
    void navigateToComments(final String identity);

    /**
     * Navigate To Comments
     * @param identity
     * @param socialMediaEnum
     */
    void navigateToComments(final String identity, final SocialMediaEnum socialMediaEnum);

    /**
     * Navigate To Comments
     * @param identity
     * @param dimensionCategoryEnum
     */
    void navigateToComments(final String identity, final DimensionCategoryEnum dimensionCategoryEnum);


    /**
     * Navigate To Comments
     * @param identity
     * @param dimensionCategoryEnum
     * @param socialMediaEnum
     */
    void navigateToComments(final String identity, final DimensionCategoryEnum dimensionCategoryEnum,
                            final SocialMediaEnum socialMediaEnum);

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
     * Navigate To Kid Results Settings
     */
    void navigateToKidResultsSettings();

    /**
     * Navigate To Comments Settings
     */
    void navigateToCommentsSettings();

    /**
     * Navigate To Relations
     * @param kidIdentity
     */
    void navigateToRelations(final String kidIdentity);

    /**
     * Navigate To Relation Detail
     * @param socialMediaFriendEntity
     */
    void navigateToRelationDetail(final SocialMediaFriendEntity socialMediaFriendEntity);

    /**
     * Navigate To Relations Settings
     */
    void navigateToRelationSettings();

    /**
     * Navigate To Save Scheduled Block Mvp Activity
     * @param identity
     */
    void navigateToSaveScheduledBlockMvpActivity(final String childId, final String identity);

    /**
     * Navigate To Save Scheduled Block Mvp Activity
     */
    void navigateToSaveScheduledBlockMvpActivity(final String childId);

    /**
     * Show Four Dimensions Dialog
     * @param appCompatActivity
     * @param dimensionIdx
     * @param dimensionValue
     */
    void showFourDimensionsDialog(final AppCompatActivity appCompatActivity,
                                  int dimensionIdx, final String dimensionValue);

    /**
     * Show Comments Extracted Dialog
     * @param appCompatActivity
     * @param socialMediaIdx
     * @param socialMediaValue
     * @param kidIdentity
     */
    void showCommentsExtractedDialog(final AppCompatActivity appCompatActivity,
                                     int socialMediaIdx, final String socialMediaValue,
                                     final String kidIdentity);

    /**
     * Show Comments Extracted Dialog
     * @param appCompatActivity
     * @param socialMediaEnum
     * @param socialMediaValue
     */
    void showSocialActivityDialog(final AppCompatActivity appCompatActivity,
                                  final SocialMediaEnum socialMediaEnum,
                                  final String socialMediaValue);

    /**
     * Show Sentiment Analysis Dialog
     * @param appCompatActivity
     * @param sentimentLevelEnum
     * @param sentimentValue
     */
    void showSentimentAnalysisDialog(final AppCompatActivity appCompatActivity,
                                     final SentimentLevelEnum sentimentLevelEnum,
                                     final String sentimentValue);

    /**
     * Show Alert Level Dialog
     * @param appCompatActivity
     * @param alertLevelEnum
     * @param alertLevelValue
     */
    void showAlertLevelDialog(final AppCompatActivity appCompatActivity,
                                     final AlertLevelEnum alertLevelEnum,
                                     final String alertLevelValue, final String kidIdentity);

    /**
     * Show Likes By Social Media Dialog
     * @param appCompatActivity
     * @param socialMedia
     * @param totalLikesValue
     */
    void showLikesBySocialMediaDialog(final AppCompatActivity appCompatActivity,
                                      final int socialMedia, final String totalLikesValue);

    /**
     * Show Photo Viewer Dialog
     * @param appCompatActivity
     * @param photoUrl
     */
    void showPhotoViewerDialog(final AppCompatActivity appCompatActivity,
                               final String photoUrl);

    /**
     * Show Photo Viewer Dialog
     * @param appCompatActivity
     * @param photoRes
     */
    void showPhotoViewerDialog(final AppCompatActivity appCompatActivity,
                          final @DrawableRes int photoRes);

    /**
     * Show App Rules Info Dialog
     * @param appCompatActivity
     */
    void showAppRulesInfoDialog(final AppCompatActivity appCompatActivity);

    /**
     * Show Family Locator Info Dialog
     * @param appCompatActivity
     */
    void showFamilyLocatorInfoDialog(final AppCompatActivity appCompatActivity);

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
     * Show Social Media Status Dialog
     * @param appCompatActivity
     * @param socialMediaTypeEnum
     * @param socialMediaStatusEnum
     * @param userSocialFullName
     * @param userSocialProfilePicture
     */
    void showSocialMediaStatusDialog(final AppCompatActivity appCompatActivity, final SocialMediaTypeEnum socialMediaTypeEnum,
                                     final SocialMediaStatusEnum socialMediaStatusEnum, final String userSocialFullName, final String userSocialProfilePicture);

    /**
     * Navigate To Kids Results Activity
     * @param identity
     */
    void navigateToKidsResultsActivity(final String identity);

    /**
     * Show Notice Dialog
     * @param activity
     * @param title
     */
    void showNoticeDialog(final AppCompatActivity activity, final String title);

    /**
     * Show Notice Dialog
     * @param activity
     * @param title
     */
    void showNoticeDialog(final AppCompatActivity activity, final String title, final NoticeDialogFragment.NoticeDialogListener noticeDialogListener);


    /**
     * Show Legal Content Activity
     */
    void showLegalContentActivity();

    /**
     * Show Legal Content Activity
     * @param legalTypeEnum
     */
    void showLegalContentActivity(final LegalContentActivity.LegalTypeEnum legalTypeEnum);

    /**
     * Show Search School Activity
     * @param activity
     * @param requestCode
     */
    void showSearchSchoolActivity(final AppCompatActivity activity, final int requestCode);

    /**
     * Show School Detail
     * @param activity
     * @param schoolEntity
     */
    void showSchoolDetail(final AppCompatActivity activity, final SchoolEntity schoolEntity);

    /**
     * Show Add School
     * @param activity
     * @param requestCode
     */
    void showAddSchool(final AppCompatActivity activity, final int requestCode);

    /**
     * Show Search School Location
     * @param activity
     */
    void showSearchSchoolLocation(final AppCompatActivity activity, final boolean showCurrentLocation);

    /**
     * Show Search School
     * @param activity
     * @param latitude
     * @param longitude
     */
    void showSearchSchoolLocation(final AppCompatActivity activity, final double latitude, final double longitude);

    /**
     * Show Terminal Detail
     * @param childId
     * @param terminalId
     */
    void showTerminalDetail(final String childId, final String terminalId);

}
