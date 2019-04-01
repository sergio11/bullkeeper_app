package sanchez.sanchez.sergio.bullkeeper.navigation.impl;

import android.Manifest;
import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.net.Uri;
import android.os.Build;
import android.provider.Settings;
import android.support.annotation.DrawableRes;
import android.support.annotation.RequiresApi;
import android.support.v4.app.ActivityCompat;
import android.support.v7.app.AppCompatActivity;
import com.fernandocejas.arrow.checks.Preconditions;
import java.util.Date;
import javax.inject.Inject;
import sanchez.sanchez.sergio.bullkeeper.ui.activity.alertlist.AlertsSettingsMvpActivity;
import sanchez.sanchez.sergio.bullkeeper.ui.activity.appdetail.AppDetailMvpActivity;
import sanchez.sanchez.sergio.bullkeeper.ui.activity.appsearch.AppSearchListMvpActivity;
import sanchez.sanchez.sergio.bullkeeper.ui.activity.calldetail.CallDetailMvpActivity;
import sanchez.sanchez.sergio.bullkeeper.ui.activity.commentssettings.CommentsSettingsMvpActivity;
import sanchez.sanchez.sergio.bullkeeper.ui.activity.contactdetail.ContactDetailMvpActivity;
import sanchez.sanchez.sergio.bullkeeper.ui.activity.conversationmessages.ConversationMessageListMvpActivity;
import sanchez.sanchez.sergio.bullkeeper.ui.activity.conversationslist.ConversationListMvpActivity;
import sanchez.sanchez.sergio.bullkeeper.ui.activity.dayscheduleddetail.DayScheduledMvpActivity;
import sanchez.sanchez.sergio.bullkeeper.ui.activity.geofences.list.GeofencesListMvpActivity;
import sanchez.sanchez.sergio.bullkeeper.ui.activity.geofences.save.SaveGeofenceMvpActivity;
import sanchez.sanchez.sergio.bullkeeper.ui.activity.invitationdetail.InvitationDetailMvpActivity;
import sanchez.sanchez.sergio.bullkeeper.ui.activity.invitations.InvitationsListMvpActivity;
import sanchez.sanchez.sergio.bullkeeper.ui.activity.kidrequest.KidRequestListMvpActivity;
import sanchez.sanchez.sergio.bullkeeper.ui.activity.kidrequestdetail.KidRequestDetailMvpActivity;
import sanchez.sanchez.sergio.bullkeeper.ui.activity.kidresultssettings.KidResultsSettingsMvpActivity;
import sanchez.sanchez.sergio.bullkeeper.ui.activity.legal.LegalContentActivity;
import sanchez.sanchez.sergio.bullkeeper.ui.activity.phonenumbersblocked.PhoneNumbersBlockedListMvpActivity;
import sanchez.sanchez.sergio.bullkeeper.ui.activity.savescheduledblock.SaveScheduledBlockMvpActivity;
import sanchez.sanchez.sergio.bullkeeper.ui.activity.school.create.AddSchoolMvpActivity;
import sanchez.sanchez.sergio.bullkeeper.ui.activity.school.create.SearchSchoolLocationDialog;
import sanchez.sanchez.sergio.bullkeeper.ui.activity.school.detail.SchoolDialogFragment;
import sanchez.sanchez.sergio.bullkeeper.ui.activity.school.search.SearchSchoolMvpActivity;
import sanchez.sanchez.sergio.bullkeeper.ui.activity.searchguardian.SearchGuardiansMvpActivity;
import sanchez.sanchez.sergio.bullkeeper.ui.activity.smsdetail.SmsDetailMvpActivity;
import sanchez.sanchez.sergio.bullkeeper.ui.activity.summarymykidsresults.SummaryMyKidsResultsActivity;
import sanchez.sanchez.sergio.bullkeeper.ui.activity.terminaldetail.TerminalDetailMvpActivity;
import sanchez.sanchez.sergio.bullkeeper.ui.dialog.AboutDeveloperDialogFragment;
import sanchez.sanchez.sergio.bullkeeper.ui.dialog.AppHelpDialog;
import sanchez.sanchez.sergio.bullkeeper.ui.dialog.ChangeUserEmailDialogFragment;
import sanchez.sanchez.sergio.bullkeeper.ui.dialog.ChangeUserPasswordDialogFragment;
import sanchez.sanchez.sergio.bullkeeper.ui.dialog.NoticeDialogFragment;
import sanchez.sanchez.sergio.bullkeeper.ui.fragment.charts.appstats.AppStatsDialog;
import sanchez.sanchez.sergio.bullkeeper.ui.fragment.kiddetail.apprules.AppRulesInfoDialog;
import sanchez.sanchez.sergio.bullkeeper.ui.fragment.charts.activity.ActivityBySocialMediaDialog;
import sanchez.sanchez.sergio.bullkeeper.ui.fragment.charts.alerts.SystemAlertsDialog;
import sanchez.sanchez.sergio.bullkeeper.ui.fragment.charts.comments.CommentsExtractedBySocialMediaDialog;
import sanchez.sanchez.sergio.bullkeeper.ui.fragment.charts.likes.LikesBySocialMediaDialog;
import sanchez.sanchez.sergio.bullkeeper.ui.fragment.charts.sentiment.SentimentAnalysisDialog;
import sanchez.sanchez.sergio.bullkeeper.ui.fragment.kiddetail.familylocator.FamilyLocatorInfoDialog;
import sanchez.sanchez.sergio.bullkeeper.ui.fragment.profile.ChildAlertsDetailDialog;
import sanchez.sanchez.sergio.domain.models.AlertLevelEnum;
import sanchez.sanchez.sergio.domain.models.DimensionCategoryEnum;
import sanchez.sanchez.sergio.domain.models.SchoolEntity;
import sanchez.sanchez.sergio.domain.models.SentimentLevelEnum;
import sanchez.sanchez.sergio.domain.models.SocialMediaEnum;
import sanchez.sanchez.sergio.domain.models.SocialMediaStatusEnum;
import sanchez.sanchez.sergio.domain.models.SocialMediaTypeEnum;
import sanchez.sanchez.sergio.bullkeeper.navigation.INavigator;
import sanchez.sanchez.sergio.bullkeeper.ui.activity.alertdetail.AlertDetailMvpActivity;
import sanchez.sanchez.sergio.bullkeeper.ui.activity.alertlist.AlertListMvpActivity;
import sanchez.sanchez.sergio.bullkeeper.ui.activity.commentdetail.CommentDetailMvpActivity;
import sanchez.sanchez.sergio.bullkeeper.ui.activity.comments.CommentsMvpActivity;
import sanchez.sanchez.sergio.bullkeeper.ui.activity.home.HomeMvpActivity;
import sanchez.sanchez.sergio.bullkeeper.ui.activity.intro.IntroMvpActivity;
import sanchez.sanchez.sergio.bullkeeper.ui.activity.kidsresults.KidsResultsActivity;
import sanchez.sanchez.sergio.bullkeeper.ui.activity.mykids.MyKidsMvpActivity;
import sanchez.sanchez.sergio.bullkeeper.ui.activity.mykidsdetail.MyKidsDetailMvpActivity;
import sanchez.sanchez.sergio.bullkeeper.ui.activity.mykidsprofile.MyKidsProfileMvpActivity;
import sanchez.sanchez.sergio.bullkeeper.ui.activity.settings.UserSettingsMvpActivity;
import sanchez.sanchez.sergio.bullkeeper.ui.activity.tutorial.AppTutorialActivity;
import sanchez.sanchez.sergio.bullkeeper.ui.activity.userprofile.UserProfileMvpActivity;
import sanchez.sanchez.sergio.bullkeeper.ui.dialog.PhotoViewerDialog;
import sanchez.sanchez.sergio.bullkeeper.ui.dialog.SocialMediaStatusDialog;
import sanchez.sanchez.sergio.bullkeeper.ui.fragment.charts.dimensions.FourDimensionsDialog;
import sanchez.sanchez.sergio.bullkeeper.ui.fragment.menu.MenuDialogFragment;

/**
 * Class used to navigate through the application.
 */
public class NavigatorImpl implements INavigator {

    /**
     * Context
     */
    private final Context context;

    /**
     *
     * @param context
     */
    @Inject
    public NavigatorImpl(final Context context) {
        this.context = context;
    }

    /**
     * Naavigate To Intro
     * @param closeSession
     */
    @Override
    public void navigateToIntro(final Activity activity, boolean closeSession) {
        Intent intentToLaunch = IntroMvpActivity.getCallingIntent(context, closeSession);
        activity.startActivity(intentToLaunch);
    }

    /**
     * Navigate To Intro
     */
    @Override
    public void navigateToIntro(final Activity activity) {
        navigateToIntro(activity, false);
    }

    /**
     * Navigate To Home
     */
    @Override
    public void navigateToHome(final Activity activity) {
        Intent intentToHome = HomeMvpActivity.getCallingIntent(context);
        activity.startActivity(intentToHome);
    }

    /**
     * Navigate To Home
     * @param fromSignInSuccess
     */
    @Override
    public void navigateToHome(final Activity activity, boolean fromSignInSuccess) {
        Intent intentToHome = HomeMvpActivity.getCallingIntent(context, fromSignInSuccess);
        activity.startActivity(intentToHome);
    }

    /**
     * Show App Menu Dialog
     */
    @Override
    public void showAppMenuDialog(final AppCompatActivity activity) {
        MenuDialogFragment.show(activity);
    }

    /**
     * Navigate to My Kids
     */
    @Override
    public void navigateToMyKids(final Activity activity) {
        Intent intentToMyKids = MyKidsMvpActivity.getCallingIntent(context);
        activity.startActivity(intentToMyKids);
    }

    /**
     * Navigate to Alert Detail
     */
    @Override
    public void navigateToAlertDetail(final Activity activity, final String alertId, final String sonId) {
        final Intent intentToAlertDetail = AlertDetailMvpActivity.getCallingIntent(context, alertId, sonId);
        activity.startActivity(intentToAlertDetail);
    }

    /**
     * Navigate To Alert List
     */
    @Override
    public void navigateToAlertList(final Activity activity) {
        final Intent intentToAlertList = AlertListMvpActivity.getCallingIntent(context);
        activity.startActivity(intentToAlertList);
    }

    /**
     * Navigate To Alert List
     * @param alertLevelEnum
     */
    @Override
    public void navigateToAlertList(final Activity activity, AlertLevelEnum alertLevelEnum) {
        final Intent intentToAlertList = AlertListMvpActivity.getCallingIntent(context, alertLevelEnum);
        activity.startActivity(intentToAlertList);
    }

    /**
     * Navigate To Alert List
     * @param sonIdentity
     */
    @Override
    public void navigateToAlertList(final Activity activity, String sonIdentity) {
        final Intent intentToAlertList = AlertListMvpActivity.getCallingIntent(context, sonIdentity);
        activity.startActivity(intentToAlertList);
    }

    /**
     * Navigate To Alert List
     * @param alertLevelEnum
     * @param sonIdentity
     */
    @Override
    public void navigateToAlertList(final Activity activity, AlertLevelEnum alertLevelEnum, String sonIdentity) {
        final Intent intentToAlertList = AlertListMvpActivity.getCallingIntent(context, alertLevelEnum, sonIdentity);
        activity.startActivity(intentToAlertList);
    }


    /**
     * Navigate To App Tutorial
     */
    @Override
    public void navigateToAppTutorial(final Activity activity) {
        activity.startActivity(AppTutorialActivity.getCallingIntent(context));
    }

    /**
     * Navigate to User Settings
     */
    @Override
    public void navigateToUserSettings(final Activity activity) {
        activity.startActivity(UserSettingsMvpActivity.getCallingIntent(context));
    }

    /**
     * Navigate to Alerts Settings
     */
    @Override
    public void navigateToAlertsSettings(final Activity activity) {
        activity.startActivity(AlertsSettingsMvpActivity.getCallingIntent(context));
    }

    /**
     * Navigate To Alerts Settings With Alert Level Filter Enabled
     */
    @Override
    public void navigateToAlertsSettingsWithAlertLevelFilterEnabled(final Activity activity) {
        activity.startActivity(AlertsSettingsMvpActivity.getCallingIntent(context, true));
    }

    /**
     * Show App Help Dialog
     * @param activity
     * @param title
     * @param cueVideo
     */
    @Override
    public void showAppHelpDialog(AppCompatActivity activity, String title, String cueVideo) {
        AppHelpDialog.show(activity, title, cueVideo);
    }


    /**
     * Navigate to User Profile
     */
    @Override
    public void navigateToUserProfile(final Activity activity) {
        activity.startActivity(UserProfileMvpActivity.getCallingIntent(context));
    }

    /**
     * Navigate To My Kids Profile
     */
    @Override
    public void navigateToMyKidsProfile(final Activity activity, final String identity) {
        activity.startActivity(MyKidsProfileMvpActivity.getCallingIntent(context, identity));
    }

    /**
     * Navigate To Add Kids
     */
    @Override
    public void navigateToAddKids(final Activity activity) {
        activity.startActivity(MyKidsProfileMvpActivity.getCallingIntent(context));
    }

    /**
     * Navigate To Comments
     * @param identity
     */
    @Override
    public void navigateToComments(final Activity activity, final String identity) {
        activity.startActivity(CommentsMvpActivity.getCallingIntent(context, identity));
    }

    /**
     * Navigate To Comments
     * @param identity
     * @param socialMediaEnum
     */
    @Override
    public void navigateToComments(final Activity activity, final String identity, final SocialMediaEnum socialMediaEnum) {
        Preconditions.checkNotNull(identity, "Identity can not be null");
        Preconditions.checkState(!identity.isEmpty(), "Identity can not be empty");
        Preconditions.checkNotNull(socialMediaEnum, "Social Media Enum can not be null");

        activity.startActivity(CommentsMvpActivity.getCallingIntent(context, identity, socialMediaEnum));
    }

    /**
     * Navigate To Comments
     * @param identity
     * @param dimensionCategoryEnum
     */
    @Override
    public void navigateToComments(final Activity activity, final String identity, final DimensionCategoryEnum dimensionCategoryEnum) {
        Preconditions.checkNotNull(identity, "Identity can not be null");
        Preconditions.checkState(!identity.isEmpty(), "Identity can not be empty");
        Preconditions.checkNotNull(dimensionCategoryEnum, "Dimension Category can not be null");

        activity.startActivity(CommentsMvpActivity.getCallingIntent(context, identity, dimensionCategoryEnum));
    }

    /**
     * Navigate to comments
     * @param identity
     * @param dimensionCategoryEnum
     * @param socialMediaEnum
     */
    @Override
    public void navigateToComments(final Activity activity, final String identity, final DimensionCategoryEnum dimensionCategoryEnum,
                                   final SocialMediaEnum socialMediaEnum) {
        Preconditions.checkNotNull(identity, "Identity can not be null");
        Preconditions.checkState(!identity.isEmpty(), "Identity can not be empty");
        Preconditions.checkNotNull(dimensionCategoryEnum, "Dimension Category can not be null");
        Preconditions.checkNotNull(socialMediaEnum, "Dimension Category can not be null");

        activity.startActivity(CommentsMvpActivity.getCallingIntent(context, identity, dimensionCategoryEnum, socialMediaEnum));
    }

    /**
     * Navigate To Comment Detail
     * @param identity
     */
    @Override
    public void navigateToCommentDetail(final Activity activity, final String identity) {
        activity.startActivity(CommentDetailMvpActivity.getCallingIntent(context, identity));
    }

    /**
     * Navigate To My Kids Detail
     * @param activity
     * @param identity
     */
    @Override
    public void navigateToMyKidsDetail(final Activity activity, final String identity) {
        activity.startActivity(MyKidsDetailMvpActivity.getCallingIntent(context, identity));
    }

    /**
     *
     * @param activity
     * @param identity
     */
    @Override
    public void navigateToAppRules(final Activity activity, final String identity) {
        Preconditions.checkNotNull(activity, "Activity can not be null");
        Preconditions.checkNotNull(identity, "Identity can not be null");
        Preconditions.checkState(!identity.isEmpty(), "Identity can not be empty");

        activity.startActivity(MyKidsDetailMvpActivity.getCallingIntent(context, identity,
                MyKidsDetailMvpActivity.APP_RULES_TAB));
    }

    /**
     *
     * @param activity
     * @param identity
     */
    @Override
    public void navigateToTerminalsList(final Activity activity, final String identity) {
        Preconditions.checkNotNull(activity, "Activity can not be null");
        Preconditions.checkNotNull(identity, "Identity can not be null");
        Preconditions.checkState(!identity.isEmpty(), "Identity can not be empty");

        activity.startActivity(MyKidsDetailMvpActivity.getCallingIntent(context, identity,
                MyKidsDetailMvpActivity.TERMINALS_TAB));
    }

    /**
     *
     * @param activity
     * @param identity
     */
    @Override
    public void navigateToContactsList(final Activity activity, final String identity) {
        Preconditions.checkNotNull(activity, "Activity can not be null");
        Preconditions.checkNotNull(identity, "Identity can not be null");
        Preconditions.checkState(!identity.isEmpty(), "Identity can not be empty");

        activity.startActivity(MyKidsDetailMvpActivity.getCallingIntent(context, identity,
                MyKidsDetailMvpActivity.CONTACTS_LIST_TAB));
    }

    /**
     *
     * @param activity
     * @param identity
     */
    @Override
    public void navigateToPhoneNumbersList(final Activity activity, final String identity) {
        Preconditions.checkNotNull(activity, "Activity can not be null");
        Preconditions.checkNotNull(identity, "Identity can not be null");
        Preconditions.checkState(!identity.isEmpty(), "Identity can not be empty");

        activity.startActivity(MyKidsDetailMvpActivity.getCallingIntent(context, identity,
                MyKidsDetailMvpActivity.CALLS_LIST_TAB));
    }

    /**
     *
     * @param activity
     * @param identity
     */
    @Override
    public void navigateToFunTime(final Activity activity, final String identity) {
        Preconditions.checkNotNull(activity, "Activity can not be null");
        Preconditions.checkNotNull(identity, "Identity can not be null");
        Preconditions.checkState(!identity.isEmpty(), "Identity can not be empty");

        activity.startActivity(MyKidsDetailMvpActivity.getCallingIntent(context, identity,
                MyKidsDetailMvpActivity.FUN_TIME_TAB));
    }

    /**
     *
     * @param activity
     * @param identity
     */
    @Override
    public void navigateToScheduledBlockList(final Activity activity, final String identity) {
        Preconditions.checkNotNull(activity, "Activity can not be null");
        Preconditions.checkNotNull(identity, "Identity can not be null");
        Preconditions.checkState(!identity.isEmpty(), "Identity can not be empty");

        activity.startActivity(MyKidsDetailMvpActivity.getCallingIntent(context, identity,
                MyKidsDetailMvpActivity.SCHEDULED_BLOCKS_TAB));
    }

    /**
     * Navigate To Kid Results Settings
     * @param activity
     */
    @Override
    public void navigateToKidResultsSettings(final Activity activity) {
        activity.startActivity(KidResultsSettingsMvpActivity.getCallingIntent(context));
    }

    /**
     * Navigate To Comments Settings
     * @param activity
     */
    @Override
    public void navigateToCommentsSettings(final Activity activity) {
        activity.startActivity(CommentsSettingsMvpActivity.getCallingIntent(context));
    }

    /**
     * Navigate To Save Scheduled Block Mvp Activity
     * @param activity
     * @param childId
     * @param identity
     */
    @Override
    public void navigateToSaveScheduledBlockMvpActivity(final Activity activity, final String childId, final String identity) {
        Preconditions.checkNotNull(childId, "Child Id can not be null");
        Preconditions.checkState(!childId.isEmpty(), "Child Id can not be empty");
        Preconditions.checkNotNull(identity, "Identity can not be null");
        Preconditions.checkState(!identity.isEmpty(), "Identity can not be empty");
        activity.startActivity(SaveScheduledBlockMvpActivity.getCallingIntent(context, identity, childId));
    }

    /**
     * Navigate To Save Scheduled Block Mvp Activity
     * @param activity
     * @param childId
     */
    @Override
    public void navigateToSaveScheduledBlockMvpActivity(final Activity activity, final String childId) {
        Preconditions.checkNotNull(childId, "Child Id can not be null");
        Preconditions.checkState(!childId.isEmpty(), "Child Id can not be empty");
        activity.startActivity(SaveScheduledBlockMvpActivity.getCallingIntent(context, childId));
    }

    /**
     * Show Four Dimensions Dialog
     * @param activity
     * @param dimensionIdx
     * @param dimensionValue
     */
    @Override
    public void showFourDimensionsDialog(AppCompatActivity activity, int dimensionIdx, final String dimensionValue) {
        FourDimensionsDialog.show(activity, dimensionIdx, dimensionValue);
    }

    /**
     * Show Comments Extracted Dialog
     * @param activity
     * @param socialMediaIdx
     * @param socialMediaValue
     */
    @Override
    public void showCommentsExtractedDialog(final AppCompatActivity activity, final int socialMediaIdx,
                                            final String socialMediaValue, final String kidIdentityValue) {
        CommentsExtractedBySocialMediaDialog.show(activity, socialMediaIdx, socialMediaValue, kidIdentityValue);
    }

    /**
     * Show Social Activity Dialog
     * @param activity
     * @param socialMediaEnum
     * @param socialMediaValue
     */
    @Override
    public void showSocialActivityDialog(AppCompatActivity activity, final SocialMediaEnum socialMediaEnum, final String socialMediaValue) {
        ActivityBySocialMediaDialog.show(activity, socialMediaEnum, socialMediaValue);
    }

    /**
     * Show Sentiment Analysis Dialog
     * @param activity
     * @param sentimentLevelEnum
     * @param sentimentValue
     */
    @Override
    public void showSentimentAnalysisDialog(final AppCompatActivity activity,
                                            final SentimentLevelEnum sentimentLevelEnum, final String sentimentValue) {
        SentimentAnalysisDialog.show(activity, sentimentLevelEnum, sentimentValue);
    }

    /**
     * Show Alert Level Dialog
     * @param activity
     * @param alertLevelEnum
     * @param alertLevelValue
     * @param kidIdentity
     */
    @Override
    public void showAlertLevelDialog(final AppCompatActivity activity,
                                     final AlertLevelEnum alertLevelEnum, final String alertLevelValue,
                                     final String kidIdentity) {
        SystemAlertsDialog.show(activity, alertLevelEnum, alertLevelValue, kidIdentity);
    }

    /**
     * Show Likes By Social Media Dialog
     * @param activity
     * @param socialMedia
     * @param totalLikesValue
     */
    @Override
    public void showLikesBySocialMediaDialog(final AppCompatActivity activity, final int socialMedia,
                                             final String totalLikesValue) {
        LikesBySocialMediaDialog.show(activity, socialMedia, totalLikesValue);
    }

    /**
     * Show Photo Viewer Dialog
     * @param activity
     * @param photoUrl
     */
    @Override
    public void showPhotoViewerDialog(final AppCompatActivity activity,
                                      final String photoUrl) {
        PhotoViewerDialog.show(activity, photoUrl);
    }

    /**
     * Show Photo Viewer Dialog
     * @param activity
     * @param photoRes
     */
    @Override
    public void showPhotoViewerDialog(final AppCompatActivity activity,
                                      final @DrawableRes int photoRes) {
        PhotoViewerDialog.show(activity, photoRes);
    }

    /**
     * Show App Rules Info Dialog
     * @param activity
     */
    @Override
    public void showAppRulesInfoDialog(final AppCompatActivity activity) {
        AppRulesInfoDialog.show(activity);
    }

    /**
     * Show Family Locator Info Dialog
     * @param activity
     */
    @Override
    public void showFamilyLocatorInfoDialog(final AppCompatActivity activity) {
        FamilyLocatorInfoDialog.show(activity);
    }

    /**
     * Show Social Media Status Dialog
     * @param activity
     * @param socialMediaTypeEnum
     * @param socialMediaStatusEnum
     */
    @Override
    public void showSocialMediaStatusDialog(final AppCompatActivity activity, SocialMediaTypeEnum socialMediaTypeEnum, SocialMediaStatusEnum socialMediaStatusEnum) {
        SocialMediaStatusDialog.show(activity, socialMediaTypeEnum, socialMediaStatusEnum);
    }

    /**
     * Show Social Media Status Dialog
     * @param activity
     * @param socialMediaTypeEnum
     * @param socialMediaStatusEnum
     */
    @Override
    public void showSocialMediaStatusDialog(final AppCompatActivity activity, final SocialMediaTypeEnum socialMediaTypeEnum,
                                            final SocialMediaStatusEnum socialMediaStatusEnum, final String userSocialFullName, final String userSocialProfilePicture) {
        SocialMediaStatusDialog.show(activity, socialMediaTypeEnum, socialMediaStatusEnum, userSocialProfilePicture, userSocialFullName);
    }

    /**
     * Navigate To Kids Results Activity
     * @param identity
     */
    @Override
    public void navigateToKidsResultsActivity(final Activity activity, String identity) {
        activity.startActivity(KidsResultsActivity.getCallingIntent(context, identity));
    }

    /**
     * Show Notice Dialog
     * @param title
     */
    @Override
    public void showNoticeDialog(final AppCompatActivity activity, final String title) {
        Preconditions.checkNotNull(activity, "Activity can not be null");
        Preconditions.checkNotNull(title, "Title can not be null");
        NoticeDialogFragment.showDialog(activity, title);
    }

    /**
     * Show Notice Dialog
     * @param activity
     * @param title
     * @param noticeDialogListener
     */
    @Override
    public void showNoticeDialog(final AppCompatActivity activity, String title, NoticeDialogFragment.NoticeDialogListener noticeDialogListener) {
        Preconditions.checkNotNull(activity, "Activity can not be null");
        Preconditions.checkNotNull(title, "Title can not be null");
        Preconditions.checkNotNull(noticeDialogListener, "Notice Dialog Listener can not be null");
        NoticeDialogFragment.showDialog(activity, title, true, noticeDialogListener);
    }

    /**
     * Show Notice Dialog
     * @param activity
     * @param title
     * @param isSuccess
     */
    @Override
    public void showNoticeDialog(final AppCompatActivity activity, String title, boolean isSuccess) {
        Preconditions.checkNotNull(activity, "Activity can not be null");
        Preconditions.checkNotNull(title, "Title can not be null");
        NoticeDialogFragment.showDialog(activity, title, isSuccess);

    }

    /**
     * Show Notice Dialog
     * @param activity
     * @param title
     * @param isSuccess
     * @param noticeDialogListener
     */
    @Override
    public void showNoticeDialog(final AppCompatActivity activity, String title, boolean isSuccess, NoticeDialogFragment.NoticeDialogListener noticeDialogListener) {
        Preconditions.checkNotNull(activity, "Activity can not be null");
        Preconditions.checkNotNull(title, "Title can not be null");
        Preconditions.checkNotNull(noticeDialogListener, "Notice Dialog Listener can not be null");
        NoticeDialogFragment.showDialog(activity, title, isSuccess, noticeDialogListener);
    }

    /**
     * Show Legal Content Activity
     */
    @Override
    public void showLegalContentActivity(final Activity activity) {
        activity.startActivity(LegalContentActivity.getCallingIntent(context));
    }

    /**
     * Show Legal Content Activitys
     * @param legalTypeEnum
     */
    @Override
    public void showLegalContentActivity(final Activity activity, final LegalContentActivity.LegalTypeEnum legalTypeEnum) {
        Preconditions.checkNotNull(legalTypeEnum, "Legal Type Enum can not be null");
        activity.startActivity(LegalContentActivity.getCallingIntent(context, legalTypeEnum));
    }

    /**
     * Show Search School Activity
     */
    @Override
    public void showSearchSchoolActivity(final Activity activity, final int requestCode) {
        Preconditions.checkNotNull(activity, "Activity can not be null");
        activity.startActivityForResult(SearchSchoolMvpActivity.getCallingIntent(context), requestCode);
    }

    /**
     * Show School Detail
     * @param activity
     */
    @Override
    public void showSchoolDetail(final AppCompatActivity activity, final SchoolEntity schoolEntity) {
        Preconditions.checkNotNull(activity, "Activity can not be null");
        Preconditions.checkNotNull(schoolEntity, "School can not be null");
        SchoolDialogFragment.show(activity, schoolEntity);
    }

    /**
     * Show Add School
     * @param activity
     * @param requestCode
     */
    @Override
    public void showAddSchool(Activity activity, int requestCode) {
        Preconditions.checkNotNull(activity, "Activity can not be null");
        activity.startActivityForResult(AddSchoolMvpActivity.getCallingIntent(context),
                requestCode);
    }

    /**
     * Show Search School Location
     * @param activity
     */
    @Override
    public void showSearchSchoolLocation(final AppCompatActivity activity, boolean showCurrentLocation) {
        Preconditions.checkNotNull(activity, "Activity can not be null");
        SearchSchoolLocationDialog.show(activity, showCurrentLocation);
    }

    /**
     * Show Search School Location
     * @param activity
     * @param latitude
     * @param longitude
     */
    @Override
    public void showSearchSchoolLocation(final AppCompatActivity activity, final double latitude, final double longitude) {
        Preconditions.checkNotNull(activity, "Activity can not be null");
        SearchSchoolLocationDialog.show(activity, latitude, longitude);
    }

    /**
     * Show Terminal Detail
     * @param childId
     * @param terminalId
     */
    @Override
    public void showTerminalDetail(final Activity activity, String childId, String terminalId) {
        Preconditions.checkNotNull(childId, "Child id can not be null");
        Preconditions.checkState(!childId.isEmpty(), "Child id can not be empty");
        Preconditions.checkNotNull(terminalId, "Terminal Id can not be null");
        Preconditions.checkState(!terminalId.isEmpty(), "Terminal id can not be empty");

        activity.startActivity(TerminalDetailMvpActivity.getCallingIntent(context, childId, terminalId));

    }

    /**
     * Show Child Alerts Detail Dialog
     * @param alertLevelEnum
     * @param alertLevelValue
     * @param kidIdentityValue
     */
    @Override
    public void showChildAlertsDetailDialog(final AppCompatActivity activity, final AlertLevelEnum alertLevelEnum, final String alertLevelValue,
                                            final String kidIdentityValue) {
        Preconditions.checkNotNull(alertLevelEnum, "Alert Level can not be null");
        Preconditions.checkNotNull(alertLevelValue, "Alert Level value can not be null");
        Preconditions.checkNotNull(kidIdentityValue, "Kid Identity value can not be null");

        ChildAlertsDetailDialog.show(activity, alertLevelEnum, alertLevelValue, kidIdentityValue);
    }

    /**
     * Navigate To Invitations
     */
    @Override
    public void navigateToInvitations(final Activity activity) {
        Preconditions.checkNotNull(activity, "Activity can not be null");
        activity.startActivity(InvitationsListMvpActivity.getCallingIntent(activity));
    }

    /**
     * Navigate To Invitation Detail
     * @param activity
     * @param invitationId
     */
    @Override
    public void navigateToInvitationDetail(Activity activity, String invitationId) {
        Preconditions.checkNotNull(activity, "Activity can not be null");
        Preconditions.checkNotNull(invitationId, "Invitation Id can not be null");
        Preconditions.checkState(!invitationId.isEmpty(), "Invitation id can not be null");
        activity.startActivity(InvitationDetailMvpActivity.getCallingIntent(activity, invitationId));
    }

    /**
     * Navigate To Conversation List
     * @param activity
     */
    @Override
    public void navigateToConversationList(Activity activity) {
        Preconditions.checkNotNull(activity, "Activity can not be null");
        activity.startActivity(ConversationListMvpActivity.getCallingIntent(activity));
    }

    /**
     * @param activity
     * @param memberOne
     * @param memberTwo
     */
    @Override
    public void navigateToConversationMessageList(final Activity activity, final String memberOne, final String memberTwo) {
        Preconditions.checkNotNull(activity, "Activity can not be null");
        Preconditions.checkNotNull(memberOne, "Member One can not be null");
        Preconditions.checkState(!memberOne.isEmpty(), "Member One can not be empty");
        Preconditions.checkNotNull(memberTwo, "Member Two can not be null");
        Preconditions.checkState(!memberTwo.isEmpty(), "Member Two can not be empty");
        activity.startActivity(ConversationMessageListMvpActivity
                .getCallingIntent(activity, memberOne, memberTwo));
    }

    /**
     * Navigate To Conversation Message List
     * @param activity
     * @param id
     */
    @Override
    public void navigateToConversationMessageList(final Activity activity, final String id) {
        Preconditions.checkNotNull(activity, "Activity can not be null");
        Preconditions.checkNotNull(id, "Id can not be null");
        activity.startActivity(ConversationMessageListMvpActivity
                .getCallingIntent(activity, id));
    }

    /**
     * Navigate To Search Guardian Activity
     * @param activity
     * @param requestCode
     */
    @Override
    public void navigateToSearchGuardianActivity(Activity activity, int requestCode) {
        Preconditions.checkNotNull(activity, "Activity can not be null");
        activity.startActivityForResult(SearchGuardiansMvpActivity.getCallingIntent(activity), requestCode);
    }

    /**
     *
     * @param activity
     * @param kid
     * @param terminal
     * @param app
     */
    @Override
    public void navigateToAppDetailActivity(final Activity activity, final String kid,
                                            final String terminal, final String app) {
        Preconditions.checkNotNull(activity, "Activity can not be null");
        Preconditions.checkNotNull(kid, "Kid can not be null");
        Preconditions.checkState(!kid.isEmpty(), "Kid can not be empty");
        Preconditions.checkNotNull(terminal, "Terminal can not be null");
        Preconditions.checkState(!terminal.isEmpty(), "Terminal can not be empty");
        Preconditions.checkNotNull(app, "App can not be null");
        Preconditions.checkState(!app.isEmpty(), "App can not be empty");

        activity.startActivity(AppDetailMvpActivity.getCallingIntent(context, kid, terminal, app));
    }

    /**
     * Navigate To Sms Detail Activity
     * @param activity
     * @param kid
     * @param terminal
     * @param sms
     */
    @Override
    public void navigateToSmsDetailActivity(final Activity activity,
                                            final String kid,
                                            final String terminal,
                                            final String sms) {
        Preconditions.checkNotNull(activity, "Activity can not be null");
        Preconditions.checkNotNull(kid, "Kid can not be null");
        Preconditions.checkState(!kid.isEmpty(), "Kid can not be empty");
        Preconditions.checkNotNull(terminal, "Terminal can not be null");
        Preconditions.checkState(!terminal.isEmpty(), "Terminal can not be empty");
        Preconditions.checkNotNull(sms, "sms can not be null");
        Preconditions.checkState(!sms.isEmpty(), "sms can not be empty");

        activity.startActivity(SmsDetailMvpActivity.getCallingIntent(context, kid, terminal, sms));
    }

    /**
     * Navigate To Call Detail Activity
     * @param activity
     * @param kid
     * @param terminal
     * @param call
     */
    @Override
    public void navigateToCallDetailActivity(final Activity activity, final String kid, final String terminal,
                                             final String call) {
        Preconditions.checkNotNull(activity, "Activity can not be null");
        Preconditions.checkNotNull(kid, "Kid can not be null");
        Preconditions.checkState(!kid.isEmpty(), "Kid can not be empty");
        Preconditions.checkNotNull(terminal, "Terminal can not be null");
        Preconditions.checkState(!terminal.isEmpty(), "Terminal can not be empty");
        Preconditions.checkNotNull(call, "Call can not be null");
        Preconditions.checkState(!call.isEmpty(), "Call can not be empty");

        activity.startActivity(CallDetailMvpActivity.getCallingIntent(context, kid, terminal, call));
    }

    /**
     * Navigate To Contact Detail Activity
     * @param activity
     * @param kid
     * @param terminal
     * @param contact
     */
    @Override
    public void navigateToContactDetailActivity(Activity activity, String kid, String terminal, String contact) {
        Preconditions.checkNotNull(activity, "Activity can not be null");
        Preconditions.checkNotNull(kid, "Kid can not be null");
        Preconditions.checkState(!kid.isEmpty(), "Kid can not be empty");
        Preconditions.checkNotNull(terminal, "Terminal can not be null");
        Preconditions.checkState(!terminal.isEmpty(), "Terminal can not be empty");
        Preconditions.checkNotNull(contact, "Contact can not be null");
        Preconditions.checkState(!contact.isEmpty(), "Contact can not be empty");

        activity.startActivity(ContactDetailMvpActivity.getCallingIntent(context,
                kid, terminal, contact));
    }

    /**
     * Navigate To Phone Numbers Blocked List
     * @param activity
     * @param kid
     * @param terminal
     */
    @Override
    public void navigateToPhoneNumbersBlockedList(Activity activity, String kid, String terminal) {
        Preconditions.checkNotNull(activity, "Activity can not be null");
        Preconditions.checkNotNull(kid, "Kid can not be null");
        Preconditions.checkState(!kid.isEmpty(), "Kid can not be empty");
        Preconditions.checkNotNull(terminal, "Terminal can not be null");
        Preconditions.checkState(!terminal.isEmpty(), "Terminal can not be empty");

        activity.startActivity(PhoneNumbersBlockedListMvpActivity.getCallingIntent(context,
                kid, terminal));
    }

    /**
     * Navigate To Kid Request List
     * @param activity
     * @param kid
     */
    @Override
    public void navigateToKidRequestList(final Activity activity, final String kid) {
        Preconditions.checkNotNull(activity, "Activity can not be null");
        Preconditions.checkNotNull(kid, "Kid can not be null");
        Preconditions.checkState(!kid.isEmpty(), "Kid can not be empty");

        activity.startActivity(KidRequestListMvpActivity
                .getCallingIntent(context, kid));

    }

    /**
     * Navigate To Kid Request Detail
     * @param activity
     * @param kid
     * @param identity
     */
    @Override
    public void navigateToKidRequestDetail(
            final Activity activity,
            final String kid,
            final String identity) {
        Preconditions.checkNotNull(activity, "Activity can not be null");
        Preconditions.checkNotNull(kid, "Kid can not be null");
        Preconditions.checkState(!kid.isEmpty(), "Kid can not be empty");
        Preconditions.checkNotNull(identity, "identity can not be null");
        Preconditions.checkState(!identity.isEmpty(), "identity can not be empty");

        activity.startActivity(KidRequestDetailMvpActivity
                .getCallingIntent(context, kid, identity));
    }

    /**
     * Navi
     * @param kid
     */
    @Override
    public void navigateToGeofencesList(final Activity activity, final String kid) {
        Preconditions.checkNotNull(kid, "Kid can not be null");
        Preconditions.checkState(!kid.isEmpty(), "Kid can not be empty");

        activity.startActivity(GeofencesListMvpActivity.getCallingIntent(activity,
                kid));
    }

    /**
     * Navigate To Select Geofence
     * @param activity
     * @param kid
     * @param requestCode
     */
    @Override
    public void navigateToSelectGeofence(Activity activity, String kid, int requestCode) {
        Preconditions.checkNotNull(kid, "Kid can not be null");
        Preconditions.checkState(!kid.isEmpty(), "Kid can not be empty");

        activity.startActivityForResult(GeofencesListMvpActivity.getCallingIntent(activity, kid), requestCode);
    }

    /**
     * Navigate To Save Geofence
     * @param kid
     * @param id
     */
    @Override
    public void navigateToSaveGeofence(final Activity activity, final String kid, final String id) {
        Preconditions.checkNotNull(kid, "Kid can not be null");
        Preconditions.checkState(!kid.isEmpty(), "Kid can not be empty");
        Preconditions.checkNotNull(id, "Id can not be null");
        Preconditions.checkState(!id.isEmpty(), "Id can not be empty");

        activity.startActivity(SaveGeofenceMvpActivity.getCallingIntent(
                activity, kid, id
        ));

    }

    /**
     * Navigate To Save Geofence
     * @param kid
     */
    @Override
    public void navigateToSaveGeofence(final Activity activity, final String kid) {
        Preconditions.checkNotNull(kid, "Kid can not be null");
        Preconditions.checkState(!kid.isEmpty(), "Kid can not be empty");

        activity.startActivity(SaveGeofenceMvpActivity.getCallingIntent(
                activity, kid
        ));
    }

    /**
     * Navigate To Save Geofence
     * @param activity
     * @param kid
     * @param requestCode
     */
    @Override
    public void navigateToSaveGeofence(final Activity activity, final String kid,
                                       final int requestCode) {
        Preconditions.checkNotNull(kid, "Kid can not be null");
        Preconditions.checkState(!kid.isEmpty(), "Kid can not be empty");

        activity.startActivityForResult(SaveGeofenceMvpActivity.getCallingIntent(activity, kid),
                requestCode);
    }

    /**
     * Navigate To App Search List Mvp Activity
     * @param activity
     * @param kid
     */
    @Override
    public void navigateToAppSearchListMvpActivity(Activity activity, String kid, final int requestCode) {
        Preconditions.checkNotNull(activity, "Activity can not be null");
        Preconditions.checkNotNull(kid, "Kid can not be null");
        Preconditions.checkState(!kid.isEmpty(), "Kid can not be empty");

        activity.startActivityForResult(AppSearchListMvpActivity.getCallingIntent(activity,
                kid), requestCode);
    }

    /**
     * Navigate To Day Scheduled Detail Activity
     * @param activity
     * @param kid
     * @param terminal
     * @param day
     */
    @Override
    public void navigateToDayScheduledDetailActivity(final Activity activity, final String kid,
                                                     final String terminal, final String day,
                                                     final boolean isFunTimeEnabled) {
        Preconditions.checkNotNull(activity, "Activity can not be null");
        Preconditions.checkNotNull(kid, "Kid can not be null");
        Preconditions.checkState(!kid.isEmpty(), "Kid can not be empty");
        Preconditions.checkNotNull(terminal, "Terminal can not be null");
        Preconditions.checkState(!terminal.isEmpty(), "Terminal can not be empty");
        Preconditions.checkNotNull(day, "Day can not be null");
        Preconditions.checkState(!day.isEmpty(), "Day can not be empty");

        activity.startActivity(DayScheduledMvpActivity.getCallingIntent(activity,
                kid, terminal, day, isFunTimeEnabled));
    }

    /**
     * Navigate Summary My Kids Results Activity
     * @param activity
     */
    @Override
    public void navigateToSummaryMyKidsResultsActivity(final Activity activity) {
        Preconditions.checkNotNull(activity, "Activity can not be null");

        activity.startActivity(SummaryMyKidsResultsActivity.getCallingIntent(activity));
    }

    /**
     * Show App Stats Dialog
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
    @Override
    public void showAppStatsDialog(AppCompatActivity activity, final String kid,
                                   final String terminal, final String app,
                                   final String appIconEncoded, final String appName,
                                   final String packageName, final Long totalTimeInForeground,
                                   final Date firstTime, final Date lastTime, final Date lastTimeUsed) {
        Preconditions.checkNotNull(activity, "Activity can not be null");
        Preconditions.checkNotNull(kid, "Kid can not be null");
        Preconditions.checkState(!kid.isEmpty(), "Kid can not be empty");
        Preconditions.checkNotNull(terminal, "Terminal can not be null");
        Preconditions.checkState(!terminal.isEmpty(), "Terminal can not be empty");
        Preconditions.checkNotNull(app, "App can not be null");
        Preconditions.checkState(!app.isEmpty(), "App can not be empty");
        Preconditions.checkNotNull(appIconEncoded, "App Icon Encoded can not be null");
        Preconditions.checkNotNull(appName, "App Name can not be null");
        Preconditions.checkState(!appName.isEmpty(), "App Name can not be empty");
        Preconditions.checkNotNull(packageName, "Package Name can not be null");
        Preconditions.checkState(!packageName.isEmpty(), "Package Name can not be empty");
        Preconditions.checkNotNull(totalTimeInForeground, "Total Time in Foreground can not be null");
        Preconditions.checkNotNull(firstTime, "First Time can not be null");
        Preconditions.checkNotNull(lastTime, "Last Time can not be null");
        Preconditions.checkNotNull(lastTimeUsed, "Last Time Used can not be null");

        AppStatsDialog.show(activity, kid, terminal, app, appIconEncoded, appName,
                packageName, totalTimeInForeground, firstTime, lastTime,
                lastTimeUsed);

    }

    /**
     * Show About Developer Dialog
     * @param activity
     */
    @Override
    public void showAboutDeveloperDialog(final AppCompatActivity activity) {
        Preconditions.checkNotNull(activity, "Activity can not be null");

        AboutDeveloperDialogFragment.show(activity);
    }

    /**
     * Start Phone Call
     * @param phoneNumber
     */
    @Override
    public void startPhoneCall(final AppCompatActivity activity, String phoneNumber) {
        Preconditions.checkNotNull(phoneNumber, "Phone number can not be null");
        Intent intent = new Intent(Intent.ACTION_CALL, Uri.parse("tel:" + phoneNumber));
        if (ActivityCompat.checkSelfPermission(activity, Manifest.permission.CALL_PHONE) == PackageManager.PERMISSION_GRANTED) {
            activity.startActivity(intent);
        }
    }

    /**
     *
     * @param activity
     * @param listener
     */
    @Override
    public void showChangeUserEmailDialogFragment(final AppCompatActivity activity,
                                                  final ChangeUserEmailDialogFragment.OnChangeUserEmailDialogListener listener) {
        Preconditions.checkNotNull(activity, "Activity can not be null");
        Preconditions.checkNotNull(listener, "Listener can not be null");

        ChangeUserEmailDialogFragment.showDialog(activity, listener);

    }

    /**
     * Show Change User Password Dialog Fragment
     * @param activity
     * @param listener
     */
    @Override
    public void showChangeUserPasswordDialogFragment(AppCompatActivity activity, ChangeUserPasswordDialogFragment.OnChangeUserPasswordDialogListener listener) {
        Preconditions.checkNotNull(activity, "Activity can not be null");
        Preconditions.checkNotNull(listener, "Listener can not be null");

        ChangeUserPasswordDialogFragment.showDialog(activity, listener);
    }


    /**
     * Show Manage Overlay Settings
     * @param activity
     */
    @RequiresApi(Build.VERSION_CODES.M)
    @Override
    public void showManageOverlaySettings(final AppCompatActivity activity) {
        Preconditions.checkNotNull(activity, "Activity can not be null");
        activity.startActivity(new Intent(Settings.ACTION_MANAGE_OVERLAY_PERMISSION,
                Uri.parse("package:" + context.getPackageName())));
    }
}
