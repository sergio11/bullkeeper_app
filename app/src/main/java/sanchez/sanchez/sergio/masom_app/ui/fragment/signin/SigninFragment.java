package sanchez.sanchez.sergio.masom_app.ui.fragment.signin;

import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.design.widget.TextInputLayout;
import android.support.v7.widget.AppCompatEditText;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import com.mobsandgeeks.saripaar.Validator;
import com.mobsandgeeks.saripaar.annotation.Email;
import com.mobsandgeeks.saripaar.annotation.NotEmpty;
import com.mobsandgeeks.saripaar.annotation.Password;
import butterknife.BindView;
import butterknife.OnClick;
import sanchez.sanchez.sergio.masom_app.R;
import sanchez.sanchez.sergio.masom_app.di.HasComponent;
import sanchez.sanchez.sergio.masom_app.di.components.IntroComponent;
import sanchez.sanchez.sergio.masom_app.ui.activity.intro.IIntroActivityHandler;
import sanchez.sanchez.sergio.masom_app.ui.support.SupportValidationFragment;

/**
 * Intro Fragment
 */
public class SigninFragment extends
        SupportValidationFragment<SigninFragmentPresenter, ISigninView, IIntroActivityHandler>
implements ISigninView, Validator.ValidationListener{

    public static String TAG = "SIGNIN_FRAGMENT";


    private IntroComponent introComponent;

    /**
     * Email Input Layout
     */
    @BindView(R.id.emailInputLayout)
    protected TextInputLayout emailInputLayout;

    /**
     * Email Input
     */
    @BindView(R.id.emailInput)
    @NotEmpty
    @Email
    protected AppCompatEditText emailInput;

    /**
     * Password Input Layout
     */
    @BindView(R.id.passwordInputLayout)
    protected TextInputLayout passwordInputLayout;

    /**
     * Password Input
     */
    @BindView(R.id.passwordInput)
    @Password(min = 6, scheme = Password.Scheme.ALPHA_NUMERIC)
    protected AppCompatEditText passwordInput;

    public SigninFragment() { }

    /**
     * New Instance
     * @return
     */
    public static SigninFragment newInstance() {
        SigninFragment fragment = new SigninFragment();
        return fragment;
    }


    @Override
    protected void initializeInjector() {
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
        return inflater.inflate(R.layout.fragment_signin, container, false);
    }

    /**
     * Provide Presenter
     * @return
     */
    @NonNull
    @Override
    public SigninFragmentPresenter providePresenter() {
        return introComponent.signinFragmentPresenter();
    }

    /**
     * On Login
     */
    @OnClick(R.id.loginBtn)
    public void onLogin(){
        validator.validate();
    }

    /**
     * On Login Facebook
     */
    @OnClick(R.id.loginFacebook)
    public void onLoginFacebook(){

        showShortMessage("On Login Facebook");
    }

    /**
     * On Forgot Password
     */
    @OnClick(R.id.forgotPassword)
    public void onForgotPassword(){
        activityHandler.goToForgetPassword();
    }

    /**
     * On Validation Succeeded
     */
    @Override
    public void onValidationSucceeded() {

        // Clear errors
        emailInputLayout.setError("");
        passwordInputLayout.setError("");

        // Init Sign in
        final String email = emailInput.getText().toString();
        final String password = passwordInput.getText().toString();

        getPresenter().signin(email, password);
    }


    @Override
    protected void onValidationFailed() {
        showNoticeDialog(R.string.forms_is_not_valid);
    }

    /**
     * On Field Invalid
     * @param viewId
     * @param message
     */
    @Override
    protected void onFieldInvalid(Integer viewId, String message) {

        if (viewId.equals(R.id.emailInput)) {
            emailInputLayout.setError(message);
        } else if(viewId.equals(R.id.passwordInput)) {
            passwordInputLayout.setError(message);
        }

    }

    @Override
    public void onLoginSuccess() {
        activityHandler.gotToHome();
    }
}
