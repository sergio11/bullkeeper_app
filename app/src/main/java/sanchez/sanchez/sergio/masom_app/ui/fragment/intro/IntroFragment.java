package sanchez.sanchez.sergio.masom_app.ui.fragment.intro;


import android.support.annotation.NonNull;
import butterknife.OnClick;
import sanchez.sanchez.sergio.masom_app.R;
import sanchez.sanchez.sergio.masom_app.di.HasComponent;
import sanchez.sanchez.sergio.masom_app.di.components.IntroComponent;
import sanchez.sanchez.sergio.masom_app.ui.activity.intro.IIntroActivityHandler;
import sanchez.sanchez.sergio.masom_app.ui.support.SupportFragment;


/**
 * Intro Fragment
 */
public class IntroFragment extends
        SupportFragment<IntroFragmentPresenter, IIntroView, IIntroActivityHandler>
implements IIntroView {

    public static String TAG = "INTRO_FRAGMENT";


    private IntroComponent introComponent;

    public IntroFragment() {}

    /**
     * New Instance
     * @return
     */
    public static IntroFragment newInstance() {
        IntroFragment fragment = new IntroFragment();
        return fragment;
    }

    /**
     * Initialize Injector
     */
    @Override
    protected void initializeInjector() {
        introComponent = IntroComponent.class
                .cast(((HasComponent<IntroComponent>) getActivity())
                        .getComponent());
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
     * Provide Presenter
     * @return
     */
    @NonNull
    @Override
    public IntroFragmentPresenter providePresenter() {
        return introComponent.introFragmentPresenter();
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
