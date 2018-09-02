package sanchez.sanchez.sergio.bullkeeper.navigation.impl;

import android.content.Context;
import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import javax.inject.Inject;

import sanchez.sanchez.sergio.bullkeeper.ui.dialog.AppHelpDialog;
import sanchez.sanchez.sergio.domain.models.AlertLevelEnum;
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
import sanchez.sanchez.sergio.bullkeeper.ui.fragment.alertslist.FilterAlertsDialog;
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
    public void navigateToAlertDetail(final String identity) {
        final Intent intentToAlertDetail = AlertDetailMvpActivity.getCallingIntent(context, identity);
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
     * Show Filter Alerts Dialog
     * @param appCompatActivity
     */
    @Override
    public void showFilterAlertsDialog(AppCompatActivity appCompatActivity) {
        FilterAlertsDialog.show(appCompatActivity);
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
    public void showFourDimensionsDialog(AppCompatActivity appCompatActivity, int dimensionIdx, int value, int total) {
        FourDimensionsDialog.show(appCompatActivity, dimensionIdx, value, total);
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
     * Navigate To Kids Results Activity
     * @param identity
     */
    @Override
    public void navigateToKidsResultsActivity(String identity) {
        context.startActivity(KidsResultsActivity.getCallingIntent(context, identity));
    }
}
