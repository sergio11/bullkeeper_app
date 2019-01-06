package sanchez.sanchez.sergio.bullkeeper.ui.fragment.signin;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.design.widget.TextInputLayout;
import android.support.v4.app.DialogFragment;
import android.support.v7.widget.AppCompatEditText;
import android.view.View;
import com.facebook.AccessToken;
import com.facebook.CallbackManager;
import com.facebook.FacebookCallback;
import com.facebook.FacebookException;
import com.facebook.login.LoginManager;
import com.facebook.login.LoginResult;
import com.mobsandgeeks.saripaar.Validator;
import com.mobsandgeeks.saripaar.annotation.Email;
import com.mobsandgeeks.saripaar.annotation.NotEmpty;
import com.mobsandgeeks.saripaar.annotation.Password;
import java.util.Arrays;
import java.util.List;
import javax.inject.Inject;
import butterknife.BindView;
import butterknife.OnClick;
import sanchez.sanchez.sergio.bullkeeper.R;
import sanchez.sanchez.sergio.bullkeeper.di.components.IntroComponent;
import sanchez.sanchez.sergio.bullkeeper.ui.activity.intro.IIntroActivityHandler;
import sanchez.sanchez.sergio.bullkeeper.ui.dialog.ConfirmationDialogFragment;
import sanchez.sanchez.sergio.bullkeeper.core.ui.SupportMvpValidationMvpFragment;
import sanchez.sanchez.sergio.bullkeeper.core.ui.SupportToolbarApp;
import timber.log.Timber;

/**
 * Intro Fragment
 */
public class SigninMvpFragment extends
        SupportMvpValidationMvpFragment<SigninFragmentPresenter, ISigninView, IIntroActivityHandler,
                                IntroComponent>
implements ISigninView, Validator.ValidationListener, FacebookCallback<LoginResult> {

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

    @Inject
    protected Context appContext;

    private CallbackManager callbackManager;

    /**
     * User Read Permissions
     */
    private final List<String> userReadPermissions = Arrays.asList("email", "user_birthday", "user_hometown",
            "user_location", "public_profile");

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
        LoginManager.getInstance().logInWithReadPermissions(this, userReadPermissions);
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
        showNoticeDialog(R.string.forms_is_not_valid, false);
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

        callbackManager = CallbackManager.Factory.create();

        LoginManager.getInstance().registerCallback(
                callbackManager, this);
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

    /**
     * On Login Success
     */
    @Override
    public void onLoginSuccess() {
        // Go to Home
        activityHandler.gotToHome(true);
    }

    /**
     * On Login Failed
     */
    @Override
    public void onLoginFailed() {
        showNoticeDialog(R.string.login_failed, false);
    }

    /**
     * On Bad Credentials
     */
    @Override
    public void onBadCredentials() {
        showNoticeDialog(R.string.bad_credentials_error, false);
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

    /**
     * On Activity Result
     * @param requestCode
     * @param resultCode
     * @param data
     */
    @Override
    public void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        callbackManager.onActivityResult(requestCode, resultCode, data);
    }

    /**
     * On Success
     * @param loginResult
     */
    @Override
    public void onSuccess(LoginResult loginResult) {
        Timber.d("Facebook Login Success");
        AccessToken accessToken = loginResult.getAccessToken();
        if (accessToken != null)
            getPresenter().signin(accessToken);
        else
            showShortMessage(R.string.login_facebook_error);
    }

    /**
     * On Cancel
     */
    @Override
    public void onCancel() {
        Timber.d("Facebook Login Canceled");
        showShortMessage(R.string.login_facebook_canceled);
    }

    /**
     * On Error
     * @param error
     */
    @Override
    public void onError(FacebookException error) {
        Timber.d("Facebook Login Exception");
        showShortMessage(R.string.login_facebook_error);
    }

    /**
     * Get App Icon Mode
     * @return
     */
    @Override
    protected int getAppIconMode() {
        return SupportToolbarApp.DISABLE_GO_TO_HOME;
    }
}
