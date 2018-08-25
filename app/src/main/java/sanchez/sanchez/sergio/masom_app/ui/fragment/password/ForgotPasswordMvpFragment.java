package sanchez.sanchez.sergio.masom_app.ui.fragment.password;

import android.support.annotation.NonNull;
import android.support.design.widget.TextInputLayout;
import android.support.v4.app.DialogFragment;
import android.support.v7.widget.AppCompatEditText;

import com.mobsandgeeks.saripaar.annotation.ConfirmEmail;
import com.mobsandgeeks.saripaar.annotation.Email;
import com.mobsandgeeks.saripaar.annotation.NotEmpty;
import butterknife.BindView;
import butterknife.OnClick;
import sanchez.sanchez.sergio.masom_app.R;
import sanchez.sanchez.sergio.masom_app.di.components.IntroComponent;
import sanchez.sanchez.sergio.masom_app.ui.activity.intro.IIntroActivityHandler;
import sanchez.sanchez.sergio.masom_app.ui.dialog.ConfirmationDialogFragment;
import sanchez.sanchez.sergio.masom_app.ui.support.SupportMvpValidationMvpFragment;

/**
 * Intro Fragment
 */
public class ForgotPasswordMvpFragment extends
        SupportMvpValidationMvpFragment<ForgotPasswordFragmentPresenter,
                                IForgotPasswordView, IIntroActivityHandler, IntroComponent>
implements IForgotPasswordView {

    public static String TAG = "FORGOT_PASSWORD_FRAGMENT";

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
        validator.validate();
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
}
