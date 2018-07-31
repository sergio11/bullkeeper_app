package sanchez.sanchez.sergio.masom_app.ui.activity.intro;

import android.content.Context;
import android.content.Intent;
import android.graphics.Color;
import android.os.Build;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.v4.app.Fragment;
import android.view.View;
import android.view.Window;
import android.view.WindowManager;

import com.cleveroad.slidingtutorial.TutorialOptions;
import com.cleveroad.slidingtutorial.TutorialPageProvider;
import com.cleveroad.slidingtutorial.TutorialSupportFragment;

import sanchez.sanchez.sergio.masom_app.R;
import sanchez.sanchez.sergio.masom_app.di.HasComponent;
import sanchez.sanchez.sergio.masom_app.di.components.DaggerIntroComponent;
import sanchez.sanchez.sergio.masom_app.di.components.IntroComponent;
import sanchez.sanchez.sergio.masom_app.ui.fragment.intro.IntroFragment;
import sanchez.sanchez.sergio.masom_app.ui.fragment.signin.SigninFragment;
import sanchez.sanchez.sergio.masom_app.ui.fragment.signup.SignupFragment;
import sanchez.sanchez.sergio.masom_app.ui.fragment.tutorial.FifthPageFragment;
import sanchez.sanchez.sergio.masom_app.ui.fragment.tutorial.FirstPageFragment;
import sanchez.sanchez.sergio.masom_app.ui.fragment.tutorial.FourthPageFragment;
import sanchez.sanchez.sergio.masom_app.ui.fragment.tutorial.SecondPageFragment;
import sanchez.sanchez.sergio.masom_app.ui.fragment.tutorial.ThirdPageFragment;
import sanchez.sanchez.sergio.masom_app.ui.support.SupportActivity;

public class IntroActivity
        extends SupportActivity<IntroPresenter, IIntroView>
        implements HasComponent<IntroComponent>, IIntroActivityHandler
        , IIntroView{


    private IntroComponent introComponent;

    /**
     * Get Calling Intent
     * @param context
     * @return
     */
    public static Intent getCallingIntent(final Context context) {
        return new Intent(context, IntroActivity.class);
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_intro);

        // Making notification bar transparent
        if (Build.VERSION.SDK_INT >= 21) {
            getWindow().getDecorView()
                    .setSystemUiVisibility(View.SYSTEM_UI_FLAG_LAYOUT_STABLE
                            | View.SYSTEM_UI_FLAG_LAYOUT_FULLSCREEN);
        }

        changeStatusBarColor();

        if (savedInstanceState == null) {
            addFragment(R.id.fragmentContainer, IntroFragment.newInstance());
        }
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
                IntroFragment.newInstance(), IntroFragment.TAG,
                R.anim.grow_from_middle, R.anim.shrink_to_middle);
    }

    /**
     * Go To Login
     */
    @Override
    public void goToLogin() {
        replaceFragment(R.id.fragmentContainer, SigninFragment.newInstance(),
                SigninFragment.TAG, R.anim.grow_from_middle, R.anim.shrink_to_middle);
    }

    /**
     * Go To Signup
     */
    @Override
    public void goToSignup() {
        replaceFragment(R.id.fragmentContainer, SignupFragment.newInstance(),
                SignupFragment.TAG, R.anim.grow_from_middle, R.anim.shrink_to_middle);
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

        final TutorialOptions tutorialOptions = TutorialSupportFragment
                .newTutorialOptionsBuilder(this)
                .setUseAutoRemoveTutorialFragment(true)
                .setUseInfiniteScroll(true)
                .setTutorialPageProvider(new TutorialPageProvider<Fragment>() {
                    @NonNull
                    @Override
                    public Fragment providePage(int position) {
                        switch (position) {
                            case 0:
                                return new FirstPageFragment();
                            case 1:
                                return new SecondPageFragment();
                            case 2:
                                return new ThirdPageFragment();
                            case 3:
                                return new FourthPageFragment();
                            case 4:
                                return new FifthPageFragment();
                            default:
                                throw new IllegalArgumentException("Unknown position: " + position);
                        }
                    }
                })
                .setOnSkipClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View view) {
                        goToIntro();
                    }
                })
	            .setPagesCount(5)
                .build();

        final TutorialSupportFragment tutorialFragment = TutorialSupportFragment
                .newInstance(tutorialOptions);

        replaceFragment(R.id.fragmentContainer, tutorialFragment,
                "TUTORIAL_FRAGMENT", R.anim.grow_from_middle, R.anim.shrink_to_middle);

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
