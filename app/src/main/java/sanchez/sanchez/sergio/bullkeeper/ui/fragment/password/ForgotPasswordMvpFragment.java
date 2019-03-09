package sanchez.sanchez.sergio.bullkeeper.ui.fragment.password;

import android.support.annotation.NonNull;
import android.support.design.widget.TextInputLayout;
import android.support.v4.app.DialogFragment;
import android.support.v7.widget.AppCompatEditText;

import com.mobsandgeeks.saripaar.annotation.ConfirmEmail;
import com.mobsandgeeks.saripaar.annotation.Email;
import com.mobsandgeeks.saripaar.annotation.NotEmpty;

import java.util.LinkedHashMap;
import java.util.List;

import butterknife.BindView;
import butterknife.OnClick;
import sanchez.sanchez.sergio.bullkeeper.R;
import sanchez.sanchez.sergio.bullkeeper.di.components.IntroComponent;
import sanchez.sanchez.sergio.bullkeeper.ui.activity.intro.IIntroActivityHandler;
import sanchez.sanchez.sergio.bullkeeper.ui.dialog.ConfirmationDialogFragment;
import sanchez.sanchez.sergio.bullkeeper.core.ui.SupportMvpValidationMvpFragment;
import sanchez.sanchez.sergio.bullkeeper.core.ui.SupportToolbarApp;

/**
 * Intro Fragment
 */
public class ForgotPasswordMvpFragment extends
        SupportMvpValidationMvpFragment<ForgotPasswordFragmentPresenter,
                                IForgotPasswordView, IIntroActivityHandler, IntroComponent>
implements IForgotPasswordView {

    public static String TAG = "FORGOT_PASSWORD_FRAGMENT";

    private final static String EMAIL_FIELD_NAME = "email";

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
     * Repeat Email Input Layout
     */
    @BindView(R.id.repeatEmailInputLayout)
    protected TextInputLayout repeatEmailInputLayout;

    /**
     * Repeat Email Input
     */
    @BindView(R.id.repeatEmailInput)
    @NotEmpty
    @ConfirmEmail
    protected AppCompatEditText repeatEmailInput;


    public ForgotPasswordMvpFragment() { }

    /**
     * New Instance
     * @return
     */
    public static ForgotPasswordMvpFragment newInstance() {
        ForgotPasswordMvpFragment fragment = new ForgotPasswordMvpFragment();
        return fragment;
    }


    /**
     * Get Layout Resource
     * @return
     */
    @Override
    protected int getLayoutRes() {
        return R.layout.fragment_forgot_password;
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
    public ForgotPasswordFragmentPresenter providePresenter() {
        return component.forgotPasswordFragmentPresenter();
    }


    /**
     * On Validation Succeeded
     */
    @Override
    public void onValidationSucceeded() {

        // Clear errors
        emailInputLayout.setError("");
        repeatEmailInputLayout.setError("");

        // Init Reset Password
        final String mail = emailInput.getText().toString();

        getPresenter().forgotPassword(mail);
    }

    /**
     * On Validation Failed
     */
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
        } else if(viewId.equals(R.id.repeatEmailInput)) {
            repeatEmailInputLayout.setError(message);
        }

    }

    /**
     * ON Send EMail
     */
    @OnClick(R.id.sendEmail)
    public void onSendEmail(){
        if(!activityHandler.isConnectivityAvailable()) {
            showNoticeDialog(R.string.connectivity_not_available, false);
        } else {
            validator.validate();
        }
    }

    /**
     * On Password Reset
     */
    @Override
    public void onPasswordReset() {
        showConfirmationDialog(R.string.password_reset_success, new ConfirmationDialogFragment.ConfirmationDialogListener() {

            @Override
            public void onAccepted(DialogFragment dialog) {
                activityHandler.openMailApp();
                activityHandler.goToIntro();
            }

            @Override
            public void onRejected(DialogFragment dialog) {
                activityHandler.goToIntro();
            }
        });
    }

    /**
     * On Validation Errors
     * @param errors
     */
    @Override
    public void onValidationErrors(final List<LinkedHashMap<String, String>> errors) {

        for(final LinkedHashMap<String, String> error: errors) {

            if(error.get("field").equalsIgnoreCase(EMAIL_FIELD_NAME)) {
                emailInputLayout.setError(error.get("message"));
                break;
            }

        }

        showNoticeDialog(R.string.forms_is_not_valid);
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
