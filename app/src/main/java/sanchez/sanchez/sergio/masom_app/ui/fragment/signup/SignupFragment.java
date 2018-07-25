package sanchez.sanchez.sergio.masom_app.ui.fragment.signup;

import android.os.Bundle;
import android.support.annotation.NonNull;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import sanchez.sanchez.sergio.masom_app.R;
import sanchez.sanchez.sergio.masom_app.di.HasComponent;
import sanchez.sanchez.sergio.masom_app.di.components.IntroComponent;
import sanchez.sanchez.sergio.masom_app.ui.activity.intro.IIntroActivityHandler;
import sanchez.sanchez.sergio.masom_app.ui.support.SupportFragment;

/**
 * Intro Fragment
 */
public class SignupFragment extends
        SupportFragment<SignupFragmentPresenter, ISignupView, IIntroActivityHandler> {

    public static String TAG = "INTRO_FRAGMENT";


    private IntroComponent introComponent;

    public SignupFragment() {
        // Required empty public constructor
        setRetainInstance(Boolean.TRUE);
    }

    /**
     * New Instance
     * @return
     */
    public static SignupFragment newInstance() {
        SignupFragment fragment = new SignupFragment();
        return fragment;
    }

    /**
     * On Create
     * @param savedInstanceState
     */
    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        introComponent = IntroComponent.class
                .cast(((HasComponent<IntroComponent>) getActivity())
                        .getComponent());
        introComponent.inject(this);
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
    public SignupFragmentPresenter providePresenter() {
        return introComponent.signupFragmentPresenter();
    }

}
