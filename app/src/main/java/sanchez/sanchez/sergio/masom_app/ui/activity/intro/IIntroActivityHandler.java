package sanchez.sanchez.sergio.masom_app.ui.activity.intro;

import sanchez.sanchez.sergio.masom_app.ui.support.IBasicActivityHandler;

/**
 * Intro Activity Handler
 */
public interface IIntroActivityHandler extends IBasicActivityHandler {

    /**
     * Go to Intro
     */
    void goToIntro();

    /**
     * Go to Login
     */
    void goToLogin();

    /**
     * Go to Signup
     */
    void goToSignup();

    /**
     * Go to Home
     */
    void gotToHome();

    /**
     * Go to Tutorial
     */
    void goToTutorial();
}
