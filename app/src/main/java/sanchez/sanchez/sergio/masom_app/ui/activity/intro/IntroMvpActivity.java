package sanchez.sanchez.sergio.masom_app.ui.activity.intro;

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

import sanchez.sanchez.sergio.masom_app.R;
import sanchez.sanchez.sergio.masom_app.di.HasComponent;
import sanchez.sanchez.sergio.masom_app.di.components.DaggerIntroComponent;
import sanchez.sanchez.sergio.masom_app.di.components.IntroComponent;
import sanchez.sanchez.sergio.masom_app.ui.fragment.intro.IntroMvpFragment;
import sanchez.sanchez.sergio.masom_app.ui.fragment.password.ForgotPasswordMvpFragment;
import sanchez.sanchez.sergio.masom_app.ui.fragment.signin.SigninMvpFragment;
import sanchez.sanchez.sergio.masom_app.ui.fragment.signup.SignupMvpFragment;
import sanchez.sanchez.sergio.masom_app.ui.support.SupportMvpActivity;

/**
 * Intro MVP Activity
 */
public class IntroMvpActivity
        extends SupportMvpActivity<IntroPresenter, IIntroView>
        implements HasComponent<IntroComponent>, IIntroActivityHandler
        , IIntroView{


    private IntroComponent introComponent;

    /**
     * Get Calling Intent
     * @param context
     * @return
     */
    public static Intent getCallingIntent(final Context context) {
        return new Intent(context, IntroMvpActivity.class);
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
            addFragment(R.id.fragmentContainer, IntroMvpFragment.newInstance(), false);

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

    @Override
    public void openMailApp() {
        Intent intent = new Intent(Intent.ACTION_MAIN);
        intent.addCategory(Intent.CATEGORY_APP_EMAIL);
        startActivity(Intent.createChooser(intent, ""));
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
