package sanchez.sanchez.sergio.masom_app.ui.fragment.intro;


import android.os.Bundle;
import android.support.annotation.NonNull;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
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

    @Override
    protected void initializeInjector() {
        introComponent = IntroComponent.class
                .cast(((HasComponent<IntroComponent>) getActivity())
                        .getComponent());
    }

    /**
     * On Create View
     * @param inflater
     * @param container
     * @param savedInstanceState
     * @return
     */
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        super.onCreateView(inflater, container, savedInstanceState);
        return inflater.inflate(R.layout.fragment_intro, container, false);
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
        activityHandler.showLongMessage("Show Tutorial ...");
    }

    /**
     * On Signin Handler
     */
    @OnClick(R.id.signinButton)
    public void onSignin(){

        activityHandler.showLongMessage("Signin Ready...");
    }

    /**
     * On Signup Handler
     */
    @OnClick(R.id.signupButton)
    public void onSignup(){

        activityHandler.showLongMessage("Signup Ready...");

    }

}
