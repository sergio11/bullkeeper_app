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

}
