package sanchez.sanchez.sergio.bullkeeper.ui.activity.intro;

import sanchez.sanchez.sergio.bullkeeper.ui.activity.legal.LegalContentActivity;
import sanchez.sanchez.sergio.bullkeeper.core.ui.IBasicActivityHandler;

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
     * Go to Login with email
     */
    void goToLogin(final String email);

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

    /**
     * Go to Forget Password
     */
    void goToForgetPassword();

    /**
     * Show Legal Content
     * @param legalTypeEnum
     */
    void showLegalContent(final LegalContentActivity.LegalTypeEnum legalTypeEnum);


}
