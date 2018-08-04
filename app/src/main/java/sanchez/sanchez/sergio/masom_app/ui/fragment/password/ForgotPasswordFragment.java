package sanchez.sanchez.sergio.masom_app.ui.fragment.password;

import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.design.widget.TextInputLayout;
import android.support.v7.widget.AppCompatEditText;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import com.mobsandgeeks.saripaar.annotation.Email;
import com.mobsandgeeks.saripaar.annotation.NotEmpty;
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
public class ForgotPasswordFragment extends
        SupportValidationFragment<ForgotPasswordFragmentPresenter, IForgotPasswordView, IIntroActivityHandler>
implements IForgotPasswordView {

    public static String TAG = "FORGOT_PASSWORD_FRAGMENT";

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


    public ForgotPasswordFragment() { }

    /**
     * New Instance
     * @return
     */
    public static ForgotPasswordFragment newInstance() {
        ForgotPasswordFragment fragment = new ForgotPasswordFragment();
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
        return inflater.inflate(R.layout.fragment_forgot_password, container, false);
    }

    /**
     * Provide Presenter
     * @return
     */
    @NonNull
    @Override
    public ForgotPasswordFragmentPresenter providePresenter() {
        return introComponent.forgotPasswordFragmentPresenter();
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
