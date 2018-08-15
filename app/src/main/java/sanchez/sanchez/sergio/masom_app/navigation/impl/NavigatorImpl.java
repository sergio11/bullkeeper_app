package sanchez.sanchez.sergio.masom_app.navigation.impl;

import android.content.Context;
import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import javax.inject.Inject;
import sanchez.sanchez.sergio.masom_app.navigation.INavigator;
import sanchez.sanchez.sergio.masom_app.ui.activity.alertdetail.AlertDetailActivity;
import sanchez.sanchez.sergio.masom_app.ui.activity.alertlist.AlertListActivity;
import sanchez.sanchez.sergio.masom_app.ui.activity.comments.CommentsActivity;
import sanchez.sanchez.sergio.masom_app.ui.activity.home.HomeActivity;
import sanchez.sanchez.sergio.masom_app.ui.activity.intro.IntroActivity;
import sanchez.sanchez.sergio.masom_app.ui.activity.mykids.MyKidsActivity;
import sanchez.sanchez.sergio.masom_app.ui.activity.mykidsprofile.MyKidsProfileActivity;
import sanchez.sanchez.sergio.masom_app.ui.activity.settings.UserSettingsActivity;
import sanchez.sanchez.sergio.masom_app.ui.activity.tutorial.AppTutorialActivity;
import sanchez.sanchez.sergio.masom_app.ui.activity.userprofile.UserProfileActivity;
import sanchez.sanchez.sergio.masom_app.ui.fragment.alertslist.FilterAlertsDialog;
import sanchez.sanchez.sergio.masom_app.ui.fragment.menu.MenuDialogFragment;
import sanchez.sanchez.sergio.masom_app.ui.fragment.question.QuestionAppDialog;

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
     * Navigate To Intro
     */
    @Override
    public void navigateToIntro() {
        Intent intentToLaunch = IntroActivity.getCallingIntent(context);
        context.startActivity(intentToLaunch);
    }

    /**
     * Navigate To Home
     */
    @Override
    public void navigateToHome() {
        Intent intentToHome = HomeActivity.getCallingIntent(context);
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
        Intent intentToMyKids = MyKidsActivity.getCallingIntent(context);
        context.startActivity(intentToMyKids);
    }

    /**
     * Navigate to Alert Detail
     */
    @Override
    public void navigateToAlertDetail(final String identity) {
        final Intent intentToAlertDetail = AlertDetailActivity.getCallingIntent(context, identity);
        context.startActivity(intentToAlertDetail);
    }

    /**
     * Navigate To Alert List
     */
    @Override
    public void navigateToAlertList() {
        final Intent intentToAlertList = AlertListActivity.getCallingIntent(context);
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
        context.startActivity(UserSettingsActivity.getCallingIntent(context));
    }

    /**
     * Show Question App Dialog
     */
    @Override
    public void showQuestionAppDialog(final AppCompatActivity appCompatActivity) {
        QuestionAppDialog.show(appCompatActivity);
    }


    /**
     * Navigate to User Profile
     */
    @Override
    public void navigateToUserProfile() {
        context.startActivity(UserProfileActivity.getCallingIntent(context));
    }

    /**
     * Navigate To My Kids Profile
     */
    @Override
    public void navigateToMyKidsProfile(final String identity) {
        context.startActivity(MyKidsProfileActivity.getCallingIntent(context, identity));
    }

    /**
     * Navigate To Comments
     * @param identity
     */
    @Override
    public void navigateToComments(final String identity) {
        context.startActivity(CommentsActivity.getCallingIntent(context, identity));
    }
}
