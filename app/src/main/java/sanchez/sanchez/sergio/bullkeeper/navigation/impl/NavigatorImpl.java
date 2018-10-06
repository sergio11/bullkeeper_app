package sanchez.sanchez.sergio.bullkeeper.navigation.impl;

import android.content.Context;
import android.content.Intent;
import android.support.annotation.DrawableRes;
import android.support.v7.app.AppCompatActivity;
import com.fernandocejas.arrow.checks.Preconditions;
import javax.inject.Inject;
import sanchez.sanchez.sergio.bullkeeper.ui.activity.alertlist.AlertsSettingsMvpActivity;
import sanchez.sanchez.sergio.bullkeeper.ui.activity.legal.LegalContentActivity;
import sanchez.sanchez.sergio.bullkeeper.ui.activity.school.create.AddSchoolMvpActivity;
import sanchez.sanchez.sergio.bullkeeper.ui.activity.school.create.SearchSchoolLocationDialog;
import sanchez.sanchez.sergio.bullkeeper.ui.activity.school.detail.SchoolDialogFragment;
import sanchez.sanchez.sergio.bullkeeper.ui.activity.school.search.SearchSchoolMvpActivity;
import sanchez.sanchez.sergio.bullkeeper.ui.dialog.AppHelpDialog;
import sanchez.sanchez.sergio.bullkeeper.ui.dialog.NoticeDialogFragment;
import sanchez.sanchez.sergio.bullkeeper.ui.fragment.charts.activity.ActivityBySocialMediaDialog;
import sanchez.sanchez.sergio.bullkeeper.ui.fragment.charts.alerts.SystemAlertsDialog;
import sanchez.sanchez.sergio.bullkeeper.ui.fragment.charts.comments.CommentsExtractedBySocialMediaDialog;
import sanchez.sanchez.sergio.bullkeeper.ui.fragment.charts.likes.LikesBySocialMediaDialog;
import sanchez.sanchez.sergio.bullkeeper.ui.fragment.charts.sentiment.SentimentAnalysisDialog;
import sanchez.sanchez.sergio.domain.models.AlertLevelEnum;
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

    private final Context context;

    @Inject
    public NavigatorImpl(final Context context) {
        this.context = context;
    }

    /**
     * Naavigate To Intro
     * @param closeSession
     */
    @Override
    public void navigateToIntro(boolean closeSession) {
        Intent intentToLaunch = IntroMvpActivity.getCallingIntent(context, closeSession);
        context.startActivity(intentToLaunch);
    }

    /**
     * Navigate To Intro
     */
    @Override
    public void navigateToIntro() {
        navigateToIntro(false);
    }

    /**
     * Navigate To Home
     */
    @Override
    public void navigateToHome() {
        Intent intentToHome = HomeMvpActivity.getCallingIntent(context);
        context.startActivity(intentToHome);
    }

    /**
     * Show App Menu Dialog
     */
    @Override
    public void showAppMenuDialog(final AppCompatActivity appCompatActivity) {
        MenuDialogFragment.show(appCompatActivity);
    }

    /**
     * Navigate to My Kids
     */
    @Override
    public void navigateToMyKids() {
        Intent intentToMyKids = MyKidsMvpActivity.getCallingIntent(context);
        context.startActivity(intentToMyKids);
    }

    /**
     * Navigate to Alert Detail
     */
    @Override
    public void navigateToAlertDetail(final String alertId, final String sonId) {
        final Intent intentToAlertDetail = AlertDetailMvpActivity.getCallingIntent(context, alertId, sonId);
        context.startActivity(intentToAlertDetail);
    }

    /**
     * Navigate To Alert List
     */
    @Override
    public void navigateToAlertList() {
        final Intent intentToAlertList = AlertListMvpActivity.getCallingIntent(context);
        context.startActivity(intentToAlertList);
    }

    /**
     * Navigate To Alert List
     * @param alertLevelEnum
     */
    @Override
    public void navigateToAlertList(AlertLevelEnum alertLevelEnum) {
        final Intent intentToAlertList = AlertListMvpActivity.getCallingIntent(context, alertLevelEnum);
        context.startActivity(intentToAlertList);
    }

    /**
     * Navigate To Alert List
     * @param sonIdentity
     */
    @Override
    public void navigateToAlertList(String sonIdentity) {
        final Intent intentToAlertList = AlertListMvpActivity.getCallingIntent(context, sonIdentity);
        context.startActivity(intentToAlertList);
    }

    /**
     * Navigate To Alert List
     * @param alertLevelEnum
     * @param sonIdentity
     */
    @Override
    public void navigateToAlertList(AlertLevelEnum alertLevelEnum, String sonIdentity) {
        final Intent intentToAlertList = AlertListMvpActivity.getCallingIntent(context, alertLevelEnum, sonIdentity);
        context.startActivity(intentToAlertList);
    }


    /**
     * Navigate To App Tutorial
     */
    @Override
    public void navigateToAppTutorial() {
        context.startActivity(AppTutorialActivity.getCallingIntent(context));
    }

    /**
     * Navigate to User Settings
     */
    @Override
    public void navigateToUserSettings() {
        context.startActivity(UserSettingsMvpActivity.getCallingIntent(context));
    }

    /**
     * Navigate to Alerts Settings
     */
    @Override
    public void navigateToAlertsSettings() {
        context.startActivity(AlertsSettingsMvpActivity.getCallingIntent(context));
    }

    /**
     * Navigate To Alerts Settings With Alert Level Filter Enabled
     */
    @Override
    public void navigateToAlertsSettingsWithAlertLevelFilterEnabled() {
        context.startActivity(AlertsSettingsMvpActivity.getCallingIntent(context, true));
    }

    /**
     * Show App Help Dialog
     * @param appCompatActivity
     * @param title
     * @param cueVideo
     */
    @Override
    public void showAppHelpDialog(AppCompatActivity appCompatActivity, String title, String cueVideo) {
        AppHelpDialog.show(appCompatActivity, title, cueVideo);
    }


    /**
     * Navigate to User Profile
     */
    @Override
    public void navigateToUserProfile() {
        context.startActivity(UserProfileMvpActivity.getCallingIntent(context));
    }

    /**
     * Navigate To My Kids Profile
     */
    @Override
    public void navigateToMyKidsProfile(final String identity) {
        context.startActivity(MyKidsProfileMvpActivity.getCallingIntent(context, identity));
    }

    /**
     * Navigate To Add Kids
     */
    @Override
    public void navigateToAddKids() {
        context.startActivity(MyKidsProfileMvpActivity.getCallingIntent(context));
    }

    /**
     * Navigate To Comments
     * @param identity
     */
    @Override
    public void navigateToComments(final String identity) {
        context.startActivity(CommentsMvpActivity.getCallingIntent(context, identity));
    }

    /**
     * Navigate To Comments
     * @param identity
     * @param socialMediaEnum
     */
    @Override
    public void navigateToComments(String identity, SocialMediaEnum socialMediaEnum) {
        Preconditions.checkNotNull(identity, "Identity can not be null");
        Preconditions.checkState(!identity.isEmpty(), "Identity can not be empty");
        Preconditions.checkNotNull(socialMediaEnum, "Social Media Enum can not be null");

        context.startActivity(CommentsMvpActivity.getCallingIntent(context, identity));
    }

    /**
     * Navigate To Comment Detail
     * @param identity
     */
    @Override
    public void navigateToCommentDetail(String identity) {
        context.startActivity(CommentDetailMvpActivity.getCallingIntent(context, identity));
    }

    /**
     * Navigate To My Kids Detail
     * @param identity
     */
    @Override
    public void navigateToMyKidsDetail(final String identity) {
        context.startActivity(MyKidsDetailMvpActivity.getCallingIntent(context, identity));
    }

    /**
     * Show Four Dimensions Dialog
     * @param appCompatActivity
     */
    @Override
    public void showFourDimensionsDialog(AppCompatActivity appCompatActivity, int dimensionIdx, final String dimensionValue) {
        FourDimensionsDialog.show(appCompatActivity, dimensionIdx, dimensionValue);
    }

    /**
     * Show Comments Extracted Dialog
     * @param appCompatActivity
     * @param socialMediaIdx
     * @param socialMediaValue
     */
    @Override
    public void showCommentsExtractedDialog(final AppCompatActivity appCompatActivity, final int socialMediaIdx,
                                            final String socialMediaValue, final String kidIdentityValue) {
        CommentsExtractedBySocialMediaDialog.show(appCompatActivity, socialMediaIdx, socialMediaValue, kidIdentityValue);
    }

    /**
     * Show Social Activity Dialog
     * @param appCompatActivity
     * @param socialMediaEnum
     * @param socialMediaValue
     */
    @Override
    public void showSocialActivityDialog(AppCompatActivity appCompatActivity, final SocialMediaEnum socialMediaEnum, final String socialMediaValue) {
        ActivityBySocialMediaDialog.show(appCompatActivity, socialMediaEnum, socialMediaValue);
    }

    /**
     * Show Sentiment Analysis Dialog
     * @param appCompatActivity
     * @param sentimentLevelEnum
     * @param sentimentValue
     */
    @Override
    public void showSentimentAnalysisDialog(final AppCompatActivity appCompatActivity,
                                            final SentimentLevelEnum sentimentLevelEnum, final String sentimentValue) {
        SentimentAnalysisDialog.show(appCompatActivity, sentimentLevelEnum, sentimentValue);
    }

    /**
     * Show Alert Level Dialog
     * @param appCompatActivity
     * @param alertLevelEnum
     * @param alertLevelValue
     * @param kidIdentity
     */
    @Override
    public void showAlertLevelDialog(final AppCompatActivity appCompatActivity,
                                     final AlertLevelEnum alertLevelEnum, final String alertLevelValue,
                                     final String kidIdentity) {
        SystemAlertsDialog.show(appCompatActivity, alertLevelEnum, alertLevelValue, kidIdentity);
    }

    /**
     * Show Likes By Social Media Dialog
     * @param appCompatActivity
     * @param socialMedia
     * @param totalLikesValue
     */
    @Override
    public void showLikesBySocialMediaDialog(final AppCompatActivity appCompatActivity, final int socialMedia,
                                             final String totalLikesValue) {
        LikesBySocialMediaDialog.show(appCompatActivity, socialMedia, totalLikesValue);
    }

    /**
     * Show Photo Viewer Dialog
     * @param appCompatActivity
     * @param photoUrl
     */
    @Override
    public void showPhotoViewerDialog(final AppCompatActivity appCompatActivity,
                                      final String photoUrl) {
        PhotoViewerDialog.show(appCompatActivity, photoUrl);
    }

    /**
     * Show Photo Viewer Dialog
     * @param appCompatActivity
     * @param photoRes
     */
    @Override
    public void showPhotoViewerDialog(final AppCompatActivity appCompatActivity,
                                      final @DrawableRes int photoRes) {
        PhotoViewerDialog.show(appCompatActivity, photoRes);
    }

    /**
     * Show Social Media Status Dialog
     * @param appCompatActivity
     * @param socialMediaTypeEnum
     * @param socialMediaStatusEnum
     */
    @Override
    public void showSocialMediaStatusDialog(AppCompatActivity appCompatActivity, SocialMediaTypeEnum socialMediaTypeEnum, SocialMediaStatusEnum socialMediaStatusEnum) {
        SocialMediaStatusDialog.show(appCompatActivity, socialMediaTypeEnum, socialMediaStatusEnum);
    }

    /**
     * Show Social Media Status Dialog
     * @param appCompatActivity
     * @param socialMediaTypeEnum
     * @param socialMediaStatusEnum
     */
    @Override
    public void showSocialMediaStatusDialog(final AppCompatActivity appCompatActivity, final SocialMediaTypeEnum socialMediaTypeEnum,
                                            final SocialMediaStatusEnum socialMediaStatusEnum, final String userSocialFullName, final String userSocialProfilePicture) {
        SocialMediaStatusDialog.show(appCompatActivity, socialMediaTypeEnum, socialMediaStatusEnum, userSocialProfilePicture , userSocialFullName);
    }

    /**
     * Navigate To Kids Results Activity
     * @param identity
     */
    @Override
    public void navigateToKidsResultsActivity(String identity) {
        context.startActivity(KidsResultsActivity.getCallingIntent(context, identity));
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
    public void showNoticeDialog(AppCompatActivity activity, String title, NoticeDialogFragment.NoticeDialogListener noticeDialogListener) {
        Preconditions.checkNotNull(activity, "Activity can not be null");
        Preconditions.checkNotNull(title, "Title can not be null");
        Preconditions.checkNotNull(noticeDialogListener, "Notice Dialog Listener can not be null");
        NoticeDialogFragment.showDialog(activity, title, noticeDialogListener);
    }

    /**
     * Show Legal Content Activity
     */
    @Override
    public void showLegalContentActivity() {
        context.startActivity(LegalContentActivity.getCallingIntent(context));
    }

    /**
     * Show Legal Content Activitys
     * @param legalTypeEnum
     */
    @Override
    public void showLegalContentActivity(LegalContentActivity.LegalTypeEnum legalTypeEnum) {
        Preconditions.checkNotNull(legalTypeEnum, "Legal Type Enum can not be null");
        context.startActivity(LegalContentActivity.getCallingIntent(context, legalTypeEnum));
    }

    /**
     * Show Search School Activity
     */
    @Override
    public void showSearchSchoolActivity(final AppCompatActivity activity, final int requestCode) {
        Preconditions.checkNotNull(activity, "Activity can not be null");
        activity.startActivityForResult(SearchSchoolMvpActivity.getCallingIntent(context), requestCode);
    }

    /**
     * Show School Detail
     * @param activity
     */
    @Override
    public void showSchoolDetail(AppCompatActivity activity, final SchoolEntity schoolEntity) {
        Preconditions.checkNotNull(activity, "Activity can not be null");
        Preconditions.checkNotNull(schoolEntity, "School can not be null");
        SchoolDialogFragment.show(activity,  schoolEntity);
    }

    /**
     * Show Add School
     * @param activity
     * @param requestCode
     */
    @Override
    public void showAddSchool(AppCompatActivity activity, int requestCode) {
        Preconditions.checkNotNull(activity, "Activity can not be null");
        activity.startActivityForResult(AddSchoolMvpActivity.getCallingIntent(context),
                requestCode);
    }

    /**
     * Show Search School Location
     * @param activity
     */
    @Override
    public void showSearchSchoolLocation(AppCompatActivity activity, boolean showCurrentLocation) {
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
    public void showSearchSchoolLocation(AppCompatActivity activity, final double latitude, final double longitude) {
        Preconditions.checkNotNull(activity, "Activity can not be null");
        SearchSchoolLocationDialog.show(activity, latitude, longitude);
    }
}
