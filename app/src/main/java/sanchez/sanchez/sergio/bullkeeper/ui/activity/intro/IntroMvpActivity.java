package sanchez.sanchez.sergio.bullkeeper.ui.activity.intro;

import android.content.Context;
import android.content.Intent;
import android.graphics.Color;
import android.os.Build;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.view.View;
import android.view.Window;
import android.view.WindowManager;
import com.fernandocejas.arrow.checks.Preconditions;
import com.google.android.gms.tasks.OnSuccessListener;
import com.google.firebase.iid.FirebaseInstanceId;
import com.google.firebase.iid.InstanceIdResult;
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
import timber.log.Timber;

/**
 * eG10nY0OPd0:APA91bH6aQtrqEEhnacZrMLkdmhJmj4FTAPkddDTWrf4x7IGKZyf4jghjqiViu3TcCL0rcZVdcsamCSqS0AkdARRjZvAnPqiLCEPEJZjwtFOd-jMXyHZorDK3CCHC_wP7snJ8xY-wsRE
 * Intro MVP Activity
 */
public class IntroMvpActivity
        extends SupportMvpActivity<IntroPresenter, IIntroView>
        implements HasComponent<IntroComponent>, IIntroActivityHandler
        , IIntroView{

    private final static String CLOSE_SESSION_ARG = "close_session";


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
        // Making notification bar transparent
        if (Build.VERSION.SDK_INT >= 21) {
            getWindow().getDecorView()
                    .setSystemUiVisibility(View.SYSTEM_UI_FLAG_LAYOUT_STABLE
                            | View.SYSTEM_UI_FLAG_LAYOUT_FULLSCREEN);
        }

        changeStatusBarColor();

        if (savedInstanceState == null)
            addFragment(R.id.fragmentContainer, IntroMvpFragment.newInstance(), true);

        if(getIntent().hasExtra(CLOSE_SESSION_ARG)) {
            final boolean closeSession = getIntent().getBooleanExtra(CLOSE_SESSION_ARG, false);
            if(closeSession) showShortMessage(getString(R.string.close_session_message));
        }

        enableNoticeEventListener = false;

        FirebaseInstanceId.getInstance().getInstanceId().addOnSuccessListener( this,  new OnSuccessListener<InstanceIdResult>() {
            @Override
            public void onSuccess(InstanceIdResult instanceIdResult) {
                String newToken = instanceIdResult.getToken();
                Timber.d("FCM Token -> %s", newToken);

            }
        });

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

    /**
     * Making notification bar transparent
     */
    private void changeStatusBarColor() {
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.LOLLIPOP) {
            Window window = getWindow();
            window.addFlags(WindowManager.LayoutParams.FLAG_DRAWS_SYSTEM_BAR_BACKGROUNDS);
            window.setStatusBarColor(Color.TRANSPARENT);
        }
    }
}
