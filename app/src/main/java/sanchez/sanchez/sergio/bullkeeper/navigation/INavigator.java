package sanchez.sanchez.sergio.bullkeeper.navigation;

import android.app.Activity;
import android.os.Build;
import android.support.annotation.DrawableRes;
import android.support.annotation.RequiresApi;
import android.support.v7.app.AppCompatActivity;
import java.util.Date;
import sanchez.sanchez.sergio.bullkeeper.ui.activity.legal.LegalContentActivity;
import sanchez.sanchez.sergio.bullkeeper.ui.dialog.ChangeUserEmailDialogFragment;
import sanchez.sanchez.sergio.bullkeeper.ui.dialog.ChangeUserPasswordDialogFragment;
import sanchez.sanchez.sergio.bullkeeper.ui.dialog.NoticeDialogFragment;
import sanchez.sanchez.sergio.domain.models.AlertLevelEnum;
import sanchez.sanchez.sergio.domain.models.DimensionCategoryEnum;
import sanchez.sanchez.sergio.domain.models.SchoolEntity;
import sanchez.sanchez.sergio.domain.models.SentimentLevelEnum;
import sanchez.sanchez.sergio.domain.models.SocialMediaEnum;
import sanchez.sanchez.sergio.domain.models.SocialMediaStatusEnum;
import sanchez.sanchez.sergio.domain.models.SocialMediaTypeEnum;

/**
 * Navigator
 */
public interface INavigator {

    /**
     * Navigate To Intro
     */
    void navigateToIntro(final Activity activity, final boolean closeSession);

    /**
     * Navigate To Intro
     */
    void navigateToIntro(final Activity activity);


    /**
     * Navigate To Home
     */
    void navigateToHome(final Activity activity);

    /**
     * Navigate To Home
     */
    void navigateToHome(final Activity activity, boolean fromSignInSuccess);

    /**
     * Show App Menu Dialog
     */
    void showAppMenuDialog(final AppCompatActivity activity);

    /**
     * Navigate to My Kids
     */
    void navigateToMyKids(final Activity activity);

    /**
     * Navigate To Alert Detail
     */
    void navigateToAlertDetail(final Activity activity, final String alertId, final String sonId);

    /**
     * Navigate to Alert List
     */
    void navigateToAlertList(final Activity activity);

    /**
     * Navigate To Alert List
     * @param alertLevelEnum
     */
    void navigateToAlertList(final Activity activity, final AlertLevelEnum alertLevelEnum);

    /**
     * Navigate to Alert List
     * @param sonIdentity
     */
    void navigateToAlertList(final Activity activity, final String sonIdentity);

    /**
     * Navigate to Alert List
     * @param alertLevelEnum
     * @param sonIdentity
     */
    void navigateToAlertList(final Activity activity, final AlertLevelEnum alertLevelEnum, final String sonIdentity);


    /**
     * Navigate To App Tutorial
     */
    void navigateToAppTutorial(final Activity activity);

    /**
     * Navigate to User Settings
     */
    void navigateToUserSettings(final Activity activity);

    /**
     * Navigate to alerts Settings
     */
    void navigateToAlertsSettings(final Activity activity);

    /**
     * Navigate To Alerts Settings With Alert Level Filter Enabled
     */
    void navigateToAlertsSettingsWithAlertLevelFilterEnabled(final Activity activity);

    /**
     * Show App Help App Dialog
     */
    void showAppHelpDialog(final AppCompatActivity activity, final String title, final String cueVideo);

    /**
     * Navigate to User Profile
     */
    void navigateToUserProfile(final Activity activity);

    /**
     * Navigate To My Kids Profile
     */
    void navigateToMyKidsProfile(final Activity activity, final String identity);

    /**
     * Navigate To Add Kids
     */
    void navigateToAddKids(final Activity activity);


    /**
     * Navigate To Comments
     * @param identity
     */
    void navigateToComments(final Activity activity, final String identity);

    /**
     * Navigate To Comments
     * @param identity
     * @param socialMediaEnum
     */
    void navigateToComments(final Activity activity, final String identity, final SocialMediaEnum socialMediaEnum);

    /**
     * Navigate To Comments
     * @param identity
     * @param dimensionCategoryEnum
     */
    void navigateToComments(final Activity activity, final String identity, final DimensionCategoryEnum dimensionCategoryEnum);


    /**
     * Navigate To Comments
     * @param identity
     * @param dimensionCategoryEnum
     * @param socialMediaEnum
     */
    void navigateToComments(final Activity activity, final String identity, final DimensionCategoryEnum dimensionCategoryEnum,
                            final SocialMediaEnum socialMediaEnum);

    /**
     * Navigate To Comment Detail
     * @param identity
     */
    void navigateToCommentDetail(final Activity activity, final String identity);

    /**
     * Navigate To My Kids Detail
     * @param identity
     */
    void navigateToMyKidsDetail(final Activity activity, final String identity);

    /**
     * Navigate To App Rules
     * @param activity
     * @param identity
     */
    void navigateToAppRules(final Activity activity, final String identity);


    /**
     * Navigate To Terminals
     * @param activity
     * @param identity
     */
    void navigateToTerminalsList(final Activity activity, final String identity);

    /**
     * Navigate To Contacts
     * @param activity
     * @param identity
     */
    void navigateToContactsList(final Activity activity, final String identity);

    /**
     * Navigate To Phone Number List
     * @param activity
     * @param identity
     */
    void navigateToPhoneNumbersList(final Activity activity, final String identity);

    /**
     * Navigate To Fun Time
     * @param activity
     * @param identity
     */
    void navigateToFunTime(final Activity activity, final String identity);

    /**
     * Navigate To Scheduled Block List
     * @param activity
     * @param identity
     */
    void navigateToScheduledBlockList(final Activity activity, final String identity);


    /**
     * Navigate To Kid Results Settings
     */
    void navigateToKidResultsSettings(final Activity activity);

    /**
     * Navigate To Comments Settings
     */
    void navigateToCommentsSettings(final Activity activity);


    /**
     * Navigate To Save Scheduled Block Mvp Activity
     * @param identity
     */
    void navigateToSaveScheduledBlockMvpActivity(final Activity activity, final String childId, final String identity);

    /**
     * Navigate To Save Scheduled Block Mvp Activity
     */
    void navigateToSaveScheduledBlockMvpActivity(final Activity activity, final String childId);

    /**
     * Show Four Dimensions Dialog
     * @param activity
     * @param dimensionIdx
     * @param dimensionValue
     */
    void showFourDimensionsDialog(final AppCompatActivity activity,
                                  int dimensionIdx, final String dimensionValue);

    /**
     * Show Comments Extracted Dialog
     * @param activity
     * @param socialMediaIdx
     * @param socialMediaValue
     * @param kidIdentity
     */
    void showCommentsExtractedDialog(final AppCompatActivity activity,
                                     int socialMediaIdx, final String socialMediaValue,
                                     final String kidIdentity);

    /**
     * Show Comments Extracted Dialog
     * @param activity
     * @param socialMediaEnum
     * @param socialMediaValue
     */
    void showSocialActivityDialog(final AppCompatActivity activity,
                                  final SocialMediaEnum socialMediaEnum,
                                  final String socialMediaValue);

    /**
     * Show Sentiment Analysis Dialog
     * @param activity
     * @param sentimentLevelEnum
     * @param sentimentValue
     */
    void showSentimentAnalysisDialog(final AppCompatActivity activity,
                                     final SentimentLevelEnum sentimentLevelEnum,
                                     final String sentimentValue);

    /**
     * Show Alert Level Dialog
     * @param activity
     * @param alertLevelEnum
     * @param alertLevelValue
     */
    void showAlertLevelDialog(final AppCompatActivity activity,
                                     final AlertLevelEnum alertLevelEnum,
                                     final String alertLevelValue, final String kidIdentity);

    /**
     * Show Likes By Social Media Dialog
     * @param activity
     * @param socialMedia
     * @param totalLikesValue
     */
    void showLikesBySocialMediaDialog(final AppCompatActivity activity,
                                      final int socialMedia, final String totalLikesValue);

    /**
     * Show Photo Viewer Dialog
     * @param activity
     * @param photoUrl
     */
    void showPhotoViewerDialog(final AppCompatActivity activity,
                               final String photoUrl);

    /**
     * Show Photo Viewer Dialog
     * @param activity
     * @param photoRes
     */
    void showPhotoViewerDialog(final AppCompatActivity activity,
                          final @DrawableRes int photoRes);

    /**
     * Show App Rules Info Dialog
     * @param activity
     */
    void showAppRulesInfoDialog(final AppCompatActivity activity);

    /**
     * Show Family Locator Info Dialog
     * @param activity
     */
    void showFamilyLocatorInfoDialog(final AppCompatActivity activity);

    /**
     * Show Social Media Status Dialog
     * @param activity
     * @param socialMediaTypeEnum
     * @param socialMediaStatusEnum
     */
    void showSocialMediaStatusDialog(final AppCompatActivity activity,
                                     final SocialMediaTypeEnum socialMediaTypeEnum,
                                     final SocialMediaStatusEnum socialMediaStatusEnum);

    /**
     * Show Social Media Status Dialog
     * @param activity
     * @param socialMediaTypeEnum
     * @param socialMediaStatusEnum
     * @param userSocialFullName
     * @param userSocialProfilePicture
     */
    void showSocialMediaStatusDialog(final AppCompatActivity activity, final SocialMediaTypeEnum socialMediaTypeEnum,
                                     final SocialMediaStatusEnum socialMediaStatusEnum, final String userSocialFullName, final String userSocialProfilePicture);

    /**
     * Navigate To Kids Results Activity
     * @param identity
     */
    void navigateToKidsResultsActivity(final Activity activity, final String identity);

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
     * Show Notice Dialog
     * @param activity
     * @param title
     * @param isSuccess
     */
    void showNoticeDialog(final AppCompatActivity activity, final String title, final boolean isSuccess);


    /**
     * Show Notice Dialog
     * @param activity
     * @param title
     * @param isSuccess
     * @param noticeDialogListener
     */
    void showNoticeDialog(final AppCompatActivity activity, final String title, final boolean isSuccess,  final NoticeDialogFragment.NoticeDialogListener noticeDialogListener);


    /**
     * Show Legal Content Activity
     */
    void showLegalContentActivity(final Activity activity);

    /**
     * Show Legal Content Activity
     * @param legalTypeEnum
     */
    void showLegalContentActivity(final Activity activity, final LegalContentActivity.LegalTypeEnum legalTypeEnum);

    /**
     * Show Search School Activity
     * @param activity
     * @param requestCode
     */
    void showSearchSchoolActivity(final Activity activity, final int requestCode);

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
    void showAddSchool(final Activity activity, final int requestCode);

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
    void showTerminalDetail(final Activity activity, final String childId, final String terminalId);

    /**
     * Show Child Alerts Detail Dialog
     * @param activity
     * @param alertLevelEnum
     * @param alertLevelValue
     * @param kidIdentityValue
     */
    void showChildAlertsDetailDialog(final AppCompatActivity activity, final AlertLevelEnum alertLevelEnum,
                                     final String alertLevelValue, final String kidIdentityValue);

    /**
     *  Navigate To Invitations
     */
    void navigateToInvitations(final Activity activity);

    /**
     * Navigate To Invitation Detail
     * @param activity
     * @param invitationId
     */
    void navigateToInvitationDetail(final Activity activity, final String invitationId);

    /**
     * Navigate To Conversation List
     * @param activity
     */
    void navigateToConversationList(final Activity activity);

    /**
     * Navigate To Conversation Message List
     * @param activity
     * @param memberOne
     * @param memberTwo
     */
    void navigateToConversationMessageList(final Activity activity,
                                           final String memberOne, final String memberTwo);


    /**
     * Navigate To Conversation Message List
     * @param activity
     * @param id
     */
    void navigateToConversationMessageList(final Activity activity, final String id);


    /**
     * Navigate to Search Guardian Activity
     * @param activity
     * @param requestCode
     */
    void navigateToSearchGuardianActivity(final Activity activity, final int requestCode);

    /**
     * Navigate To App Detail Activity
     * @param activity
     * @param kid
     * @param terminal
     * @param app
     */
    void navigateToAppDetailActivity(final Activity activity, final String kid, final String terminal, final String app);

    /**
     * Navigate To SMS Detail Activity
     * @param activity
     * @param kid
     * @param terminal
     * @param sms
     */
    void navigateToSmsDetailActivity(final Activity activity, final String kid, final String terminal, final String sms);

    /**
     * Navigate To Call Detail Activity
     * @param activity
     * @param kid
     * @param terminal
     * @param call
     */
    void navigateToCallDetailActivity(final Activity activity, final String kid, final String terminal, final String call);

    /**
     * Navigate To Contact Detail Activity
     * @param activity
     * @param kid
     * @param terminal
     * @param contact
     */
    void navigateToContactDetailActivity(final Activity activity, final String kid, final String terminal, final String contact);

    /**
     * Navigate To Phone Numbers Blocked List
     * @param activity
     * @param kid
     * @param terminal
     */
    void navigateToPhoneNumbersBlockedList(final Activity activity,
                                                      final String kid, final String terminal);


    /**
     * Navigate To Kid Request List
     * @param activity
     * @param kid
     */
    void navigateToKidRequestList(final Activity activity, final String kid);

    /**
     * Navigate To Kid Request Detail
     * @param activity
     * @param kid
     * @param identity
     */
    void navigateToKidRequestDetail(final Activity activity,
                                    final String kid, final String identity);

    /**
     * Navigate To Geofences List
     * @param activity
     * @param kid
     */
    void navigateToGeofencesList(final Activity activity, final String kid);

    /**
     * Navigate To Select Geofence
     * @param activity
     * @param kid
     */
    void navigateToSelectGeofence(final Activity activity, final String kid, final int requestCode);

    /**
     * Navigate To Save Geofence
     * @param kid
     * @param id
     */
    void navigateToSaveGeofence(final Activity activity, final String kid, final String id);

    /**
     * Navigate To Save Geofence
     * @param kid
     */
    void navigateToSaveGeofence(final Activity activity, final String kid);

    /**
     * Navigate To Save Geofence
     * @param kid
     */
    void navigateToSaveGeofence(final Activity activity, final String kid,
                                final int requestCode);

    /**
     * Navigate To App Search List Mvp Activity
     * @param activity
     * @param kid
     */
    void navigateToAppSearchListMvpActivity(final Activity activity, final String kid, final int requestCode);


    /**
     * Navigate To Day Scheduled Detail Activity
     * @param activity
     * @param kid
     * @param terminal
     * @param day
     */
    void navigateToDayScheduledDetailActivity(final Activity activity, final String kid,
                                              final String terminal, final String day, final boolean isFunTimeEnabled);


    /**
     * Navigate Summary My Kids Results Activity
     * @param activity
     */
    void navigateToSummaryMyKidsResultsActivity(final Activity activity);

    /**
     *
     * @param activity
     * @param kid
     * @param terminal
     * @param appIconEncoded
     * @param appName
     * @param packageName
     * @param totalTimeInForeground
     * @param firstTime
     * @param lastTime
     * @param lastTimeUsed
     */
    void showAppStatsDialog(final AppCompatActivity activity, final String kid, final String terminal,
                            final String app, final String appIconEncoded, final String appName,
                            final String packageName, final Long totalTimeInForeground, final Date firstTime,
                            final Date lastTime, final Date lastTimeUsed);

    /**
     * Show About Developer Dialog
     */
    void showAboutDeveloperDialog(final AppCompatActivity activity);

    /**
     * Start Phone Call
     * @param activity
     * @param phoneNumber
     */
    void startPhoneCall(final AppCompatActivity activity, final String phoneNumber);

    /**
     *
     * @param activity
     * @param listener
     */
    void showChangeUserEmailDialogFragment(final AppCompatActivity activity, final
                                           ChangeUserEmailDialogFragment.OnChangeUserEmailDialogListener listener);


    /**
     *
     * @param activity
     * @param listener
     */
    void showChangeUserPasswordDialogFragment(final AppCompatActivity activity, final
        ChangeUserPasswordDialogFragment.OnChangeUserPasswordDialogListener listener);

    /**
     * Show Manage Overlay Settings
     * @param activity
     */
    @RequiresApi(Build.VERSION_CODES.M)
    void showManageOverlaySettings(final AppCompatActivity activity);


    /**
     * @param activity
     * @param kid
     * @param terminal
     * @param photo
     */
    void navigateToDevicePhotoDetail(final AppCompatActivity activity, final String kid, final String terminal, final String photo);

}
