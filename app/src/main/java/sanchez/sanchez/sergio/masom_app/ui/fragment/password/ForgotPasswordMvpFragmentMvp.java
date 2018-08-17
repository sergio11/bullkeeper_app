package sanchez.sanchez.sergio.masom_app.ui.fragment.password;

import android.support.annotation.NonNull;
import android.support.design.widget.TextInputLayout;
import android.support.v7.widget.AppCompatEditText;
import com.mobsandgeeks.saripaar.annotation.Email;
import com.mobsandgeeks.saripaar.annotation.NotEmpty;
import butterknife.BindView;
import butterknife.OnClick;
import sanchez.sanchez.sergio.masom_app.R;
import sanchez.sanchez.sergio.masom_app.di.components.IntroComponent;
import sanchez.sanchez.sergio.masom_app.ui.activity.intro.IIntroActivityHandler;
import sanchez.sanchez.sergio.masom_app.ui.support.SupportMvpValidationMvpFragment;

/**
 * Intro Fragment
 */
public class ForgotPasswordMvpFragmentMvp extends
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


    public ForgotPasswordMvpFragmentMvp() { }

    /**
     * New Instance
     * @return
     */
    public static ForgotPasswordMvpFragmentMvp newInstance() {
        ForgotPasswordMvpFragmentMvp fragment = new ForgotPasswordMvpFragmentMvp();
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

        // Init Sign in
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
        }

    }

    /**
     * ON Send EMail
     */
    @OnClick(R.id.sendEmail)
    public void onSendEmail(){
        validator.validate();
    }
}
