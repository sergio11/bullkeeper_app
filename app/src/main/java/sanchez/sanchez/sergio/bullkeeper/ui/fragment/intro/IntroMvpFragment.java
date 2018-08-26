package sanchez.sanchez.sergio.bullkeeper.ui.fragment.intro;


import android.support.annotation.NonNull;
import butterknife.OnClick;
import sanchez.sanchez.sergio.bullkeeper.R;
import sanchez.sanchez.sergio.bullkeeper.di.components.IntroComponent;
import sanchez.sanchez.sergio.bullkeeper.ui.activity.intro.IIntroActivityHandler;
import sanchez.sanchez.sergio.bullkeeper.ui.support.SupportMvpFragment;


/**
 * Intro Fragment
 */
public class IntroMvpFragment extends
        SupportMvpFragment<IntroFragmentPresenter, IIntroView,
                        IIntroActivityHandler, IntroComponent>
implements IIntroView {

    public static String TAG = "INTRO_FRAGMENT";


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

}
