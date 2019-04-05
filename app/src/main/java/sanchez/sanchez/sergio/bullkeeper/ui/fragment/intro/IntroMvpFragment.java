package sanchez.sanchez.sergio.bullkeeper.ui.fragment.intro;


import android.content.Context;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.view.View;
import android.widget.Button;

import javax.inject.Inject;

import butterknife.BindView;
import butterknife.OnClick;
import sanchez.sanchez.sergio.bullkeeper.R;
import sanchez.sanchez.sergio.bullkeeper.di.components.IntroComponent;
import sanchez.sanchez.sergio.bullkeeper.ui.activity.intro.IIntroActivityHandler;
import sanchez.sanchez.sergio.bullkeeper.core.ui.SupportMvpFragment;
import sanchez.sanchez.sergio.bullkeeper.core.ui.SupportToolbarApp;


/**
 * Intro Fragment
 */
public class IntroMvpFragment extends
        SupportMvpFragment<IntroFragmentPresenter, IIntroView,
                        IIntroActivityHandler, IntroComponent>
implements IIntroView {

    public static String TAG = "INTRO_FRAGMENT";

    /**
     * Dependencies
     * ===========
     */

    @Inject
    protected Context appContext;

    /**
     * Views
     * ============
     */

    @BindView(R.id.signinButton)
    protected Button signinButton;

    @BindView(R.id.signupButton)
    protected Button signupButton;

    public IntroMvpFragment() {}

    /**
     * New Instance
     * @return
     */
    public static IntroMvpFragment newInstance() {
        IntroMvpFragment fragment = new IntroMvpFragment();
        return fragment;
    }


    /**
     * Get Layout Resource
     * @return
     */
    @Override
    protected int getLayoutRes() {
        return R.layout.fragment_intro;
    }

    /**
     * Initialize Injector
     */
    @Override
    protected void initializeInjector(IntroComponent component) {
        component.inject(this);
    }


    /**
     * Provide Presenter
     * @return
     */
    @NonNull
    @Override
    public IntroFragmentPresenter providePresenter() {
        return component.introFragmentPresenter();
    }

    /**
     * On Show Tutorial Handler
     */
    @OnClick(R.id.showTutorial)
    public void onShowTutorial(){
        activityHandler.goToTutorial();
    }

    /**
     * On Signin Handler
     */
    @OnClick(R.id.signinButton)
    public void onSignin(){
        // Go to login
        activityHandler.goToLogin();
    }

    /**
     * On Signup Handler
     */
    @OnClick(R.id.signupButton)
    public void onSignup(){
        // Go to Signup
        activityHandler.goToSignup();
    }

    /**
     * Get App Icon Mode
     * @return
     */
    @Override
    protected int getAppIconMode() {
        return SupportToolbarApp.DISABLE_GO_TO_HOME;
    }


    @Override
    public void onViewCreated(View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);

        uiUtils.startBounceAnimationForView(signinButton);
        uiUtils.startBounceAnimationForView(signupButton);
    }
}
