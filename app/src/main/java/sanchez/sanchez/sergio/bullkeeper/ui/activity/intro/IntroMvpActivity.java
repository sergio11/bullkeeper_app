package sanchez.sanchez.sergio.bullkeeper.ui.activity.intro;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.NonNull;

import com.crashlytics.android.answers.ContentViewEvent;
import com.fernandocejas.arrow.checks.Preconditions;
import sanchez.sanchez.sergio.bullkeeper.R;
import sanchez.sanchez.sergio.bullkeeper.di.HasComponent;
import sanchez.sanchez.sergio.bullkeeper.di.components.DaggerIntroComponent;
import sanchez.sanchez.sergio.bullkeeper.di.components.IntroComponent;
import sanchez.sanchez.sergio.bullkeeper.ui.activity.legal.LegalContentActivity;
import sanchez.sanchez.sergio.bullkeeper.ui.fragment.intro.IntroMvpFragment;
import sanchez.sanchez.sergio.bullkeeper.ui.fragment.password.ForgotPasswordMvpFragment;
import sanchez.sanchez.sergio.bullkeeper.ui.fragment.signin.SigninMvpFragment;
import sanchez.sanchez.sergio.bullkeeper.ui.fragment.signup.SignupMvpFragment;
import sanchez.sanchez.sergio.bullkeeper.core.ui.SupportMvpActivity;

/**
 * eG10nY0OPd0:APA91bH6aQtrqEEhnacZrMLkdmhJmj4FTAPkddDTWrf4x7IGKZyf4jghjqiViu3TcCL0rcZVdcsamCSqS0AkdARRjZvAnPqiLCEPEJZjwtFOd-jMXyHZorDK3CCHC_wP7snJ8xY-wsRE
 * Intro MVP Activity
 */
public class IntroMvpActivity
        extends SupportMvpActivity<IntroPresenter, IIntroView>
        implements HasComponent<IntroComponent>, IIntroActivityHandler
        , IIntroView{

    private final static String CLOSE_SESSION_ARG = "close_session";

    private final String CONTENT_FULL_NAME = "INTRO";
    private final String CONTENT_TYPE_NAME = "APP";


    private IntroComponent introComponent;

    /**
     * Get Calling Intent
     * @param context
     * @return
     */
    public static Intent getCallingIntent(final Context context, final boolean closeSession) {
        final Intent introIntent =  new Intent(context, IntroMvpActivity.class);
        introIntent.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TASK);
        introIntent.addFlags(Intent.FLAG_ACTIVITY_SINGLE_TOP);
        introIntent.putExtra(CLOSE_SESSION_ARG, closeSession);
        return introIntent;
    }


    /**
     * Initialize Injector
     */
    @Override
    protected void initializeInjector() {
        introComponent = DaggerIntroComponent.builder()
                .applicationComponent(getApplicationComponent())
                .activityModule(getActivityModule())
                .build();
        introComponent.inject(this);
    }

    /**
     * Get Layout Res
     * @return
     */
    @Override
    protected int getLayoutRes() {
        return R.layout.activity_intro;
    }

    /**
     * On View Ready
     * @param savedInstanceState
     */
    @Override
    protected void onViewReady(Bundle savedInstanceState) {

        if (savedInstanceState == null)
            addFragment(R.id.fragmentContainer, IntroMvpFragment.newInstance(), true);

        if(appUtils.isValidString(preferencesRepositoryImpl.getAuthToken())){
            navigatorImpl.navigateToHome();
            finish();
        }

        if(getIntent().hasExtra(CLOSE_SESSION_ARG)) {
            final boolean closeSession = getIntent().getBooleanExtra(CLOSE_SESSION_ARG, false);
            if(closeSession) showShortMessage(getString(R.string.close_session_message));
        }

        enableNoticeEventListener = false;
    }

    /**
     * On Create Content View Event
     * @return
     */
    @Override
    protected ContentViewEvent onCreateContentViewEvent() {
        return new ContentViewEvent().putContentName(CONTENT_FULL_NAME)
                .putContentType(CONTENT_TYPE_NAME);
    }

    /**
     * Provide Presenter
     * @return
     */
    @NonNull
    @Override
    public IntroPresenter providePresenter() {
        return introComponent.introPresenter();
    }

    /**
     * Get Component
     * @return
     */
    @Override
    public IntroComponent getComponent() {
        return introComponent;
    }

    /**
     * Go To Intro
     */
    @Override
    public void goToIntro() {
        replaceFragment(R.id.fragmentContainer,
                IntroMvpFragment.newInstance(), true, IntroMvpFragment.TAG,
                R.anim.grow_from_middle, R.anim.shrink_to_middle);
    }

    /**
     * Go To Login
     */
    @Override
    public void goToLogin() {
        replaceFragment(R.id.fragmentContainer, SigninMvpFragment.newInstance(), true,
                SigninMvpFragment.TAG, R.anim.grow_from_middle, R.anim.shrink_to_middle);
    }

    /**
     * Go to Login with email
     * @param email
     */
    @Override
    public void goToLogin(String email) {
        Preconditions.checkNotNull(email, "Email can not be null");
        Preconditions.checkState(!email.isEmpty(), "Email can not be empty");
        replaceFragment(R.id.fragmentContainer, SigninMvpFragment.newInstance(email), true,
                SigninMvpFragment.TAG, R.anim.grow_from_middle, R.anim.shrink_to_middle);
    }

    /**
     * Go To Signup
     */
    @Override
    public void goToSignup() {
        replaceFragment(R.id.fragmentContainer, SignupMvpFragment.newInstance(), true,
                SignupMvpFragment.TAG, R.anim.grow_from_middle, R.anim.shrink_to_middle);
    }

    /**
     * Go to Home
     */
    @Override
    public void gotToHome() {
        navigatorImpl.navigateToHome();
        finish();
    }

    /**
     * Go To Tutorial
     */
    @Override
    public void goToTutorial() {
        navigatorImpl.navigateToAppTutorial();
    }

    /**
     * Go to Forget Password
     */
    @Override
    public void goToForgetPassword() {
        replaceFragment(R.id.fragmentContainer, ForgotPasswordMvpFragment.newInstance(), true,
                ForgotPasswordMvpFragment.TAG, R.anim.grow_from_middle, R.anim.shrink_to_middle);
    }

    /**
     * Show Legal Content
     * @param legalTypeEnum
     */
    @Override
    public void showLegalContent(LegalContentActivity.LegalTypeEnum legalTypeEnum) {
        navigatorImpl.showLegalContentActivity(legalTypeEnum);
    }

}
