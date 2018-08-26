package sanchez.sanchez.sergio.bullkeeper.ui.fragment.signin;

import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.design.widget.TextInputLayout;
import android.support.v4.app.DialogFragment;
import android.support.v7.widget.AppCompatEditText;
import android.view.View;

import com.mobsandgeeks.saripaar.Validator;
import com.mobsandgeeks.saripaar.annotation.Email;
import com.mobsandgeeks.saripaar.annotation.NotEmpty;
import com.mobsandgeeks.saripaar.annotation.Password;
import butterknife.BindView;
import butterknife.OnClick;
import sanchez.sanchez.sergio.bullkeeper.R;
import sanchez.sanchez.sergio.bullkeeper.di.components.IntroComponent;
import sanchez.sanchez.sergio.bullkeeper.ui.activity.intro.IIntroActivityHandler;
import sanchez.sanchez.sergio.bullkeeper.ui.dialog.ConfirmationDialogFragment;
import sanchez.sanchez.sergio.bullkeeper.ui.support.SupportMvpValidationMvpFragment;

/**
 * Intro Fragment
 */
public class SigninMvpFragment extends
        SupportMvpValidationMvpFragment<SigninFragmentPresenter, ISigninView, IIntroActivityHandler,
                                IntroComponent>
implements ISigninView, Validator.ValidationListener{

    public static String TAG = "SIGNIN_FRAGMENT";

    private static final String EMAIL_ARG = "email";

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

    public SigninMvpFragment() { }

    /**
     * New Instance
     * @return
     */
    public static SigninMvpFragment newInstance() {
        SigninMvpFragment fragment = new SigninMvpFragment();
        return fragment;
    }

    /**
     * New Instance
     * @param email
     * @return
     */
    public static SigninMvpFragment newInstance(final String email) {
        SigninMvpFragment fragment = new SigninMvpFragment();
        final Bundle args = new Bundle();
        args.putString(EMAIL_ARG, email);
        fragment.setArguments(args);
        return fragment;
    }

    /**
     * Get Layout Resource
     * @return
     */
    @Override
    protected int getLayoutRes() {
        return R.layout.fragment_signin;
    }

    /**
     * Initialize Injector
     * @param component
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
    public SigninFragmentPresenter providePresenter() {
        return component.signinFragmentPresenter();
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

    /**
     * On Validation Failed
     */
    @Override
    protected void onValidationFailed() {
        showNoticeDialog(R.string.forms_is_not_valid);
    }

    /**
     * On View Created
     * @param view
     * @param savedInstanceState
     */
    @Override
    public void onViewCreated(View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);

        if(getArguments() != null && getArguments().containsKey(EMAIL_ARG)) {
            emailInput.setText(getArguments().getString(EMAIL_ARG));
        }
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

    /**
     * On Login Failed
     */
    @Override
    public void onLoginFailed() {
        showNoticeDialog(R.string.login_failed);
    }

    /**
     * On Bad Credentials
     */
    @Override
    public void onBadCredentials() {
        showNoticeDialog(R.string.bad_credentials_error);
    }

    /**
     * On Account Disabled
     */
    @Override
    public void onAccountDisabled() {
        showConfirmationDialog(R.string.account_is_not_activated, new ConfirmationDialogFragment.ConfirmationDialogListener() {
            @Override
            public void onAccepted(DialogFragment dialog) {
                activityHandler.openMailApp();
            }

            @Override
            public void onRejected(DialogFragment dialog) {}
        });
    }
}
