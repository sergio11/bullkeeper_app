package sanchez.sanchez.sergio.bullkeeper.ui.fragment.signup;


import android.content.Context;
import android.os.Build;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.design.widget.TextInputLayout;
import android.support.v4.app.DialogFragment;
import android.support.v4.content.ContextCompat;
import android.support.v7.widget.AppCompatEditText;
import android.text.Editable;
import android.text.SpannableStringBuilder;
import android.text.Spanned;
import android.text.TextPaint;
import android.text.TextWatcher;
import android.text.method.LinkMovementMethod;
import android.text.style.ClickableSpan;
import android.view.View;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.ProgressBar;
import android.widget.TextView;
import com.mobsandgeeks.saripaar.annotation.ConfirmEmail;
import com.mobsandgeeks.saripaar.annotation.ConfirmPassword;
import com.mobsandgeeks.saripaar.annotation.Email;
import com.mobsandgeeks.saripaar.annotation.Length;
import com.mobsandgeeks.saripaar.annotation.NotEmpty;
import com.mobsandgeeks.saripaar.annotation.Past;
import com.nulabinc.zxcvbn.Zxcvbn;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Locale;
import java.util.regex.Pattern;
import javax.inject.Inject;
import butterknife.BindView;
import butterknife.OnClick;
import sanchez.sanchez.sergio.bullkeeper.navigation.INavigator;
import sanchez.sanchez.sergio.bullkeeper.ui.activity.legal.LegalContentActivity;
import sanchez.sanchez.sergio.bullkeeper.core.ui.SupportToolbarApp;
import sanchez.sanchez.sergio.bullkeeper.core.ui.components.SupportEditTextDatePicker;
import sanchez.sanchez.sergio.domain.models.GuardianEntity;
import sanchez.sanchez.sergio.domain.utils.IAppUtils;
import sanchez.sanchez.sergio.bullkeeper.R;
import sanchez.sanchez.sergio.bullkeeper.di.components.IntroComponent;
import sanchez.sanchez.sergio.bullkeeper.ui.activity.intro.IIntroActivityHandler;
import sanchez.sanchez.sergio.bullkeeper.ui.dialog.ConfirmationDialogFragment;
import sanchez.sanchez.sergio.bullkeeper.core.ui.SupportMvpValidationMvpFragment;
import timber.log.Timber;

/**
 * Intro Fragment
 */
public class SignupMvpFragment extends
        SupportMvpValidationMvpFragment<SignupFragmentPresenter, ISignupView, IIntroActivityHandler,
                                IntroComponent> implements ISignupView {

    private final static int MIN_AGE_DEFAULT = 18;
    private final static int MAX_AGE_DEFAULT = 80;

    public static String TAG = "INTRO_FRAGMENT";

    private final static String EMAIL_FIELD_NAME = "email";
    private final static String FIRST_NAME_FIELD_NAME = "first_name";
    private final static String LAST_NAME_FIELD_NAME = "last_name";
    private final static String BIRTHDATE_FIELD_NAME = "birthdate";

    @Inject
    protected IAppUtils appUtils;

    /**
     * Navigator
     */
    @Inject
    protected INavigator navigator;

    /**
     * Name Input Layout
     */
    @BindView(R.id.nameInputLayout)
    protected TextInputLayout nameInputLayout;

    /**
     * Name Input
     */
    @BindView(R.id.nameInput)
    @NotEmpty(messageResId = R.string.name_not_empty_error)
    @Length(min = 3, max = 15)
    protected AppCompatEditText nameInput;


    /**
     * Surname Input Layout
     */
    @BindView(R.id.surnameInputLayout)
    protected TextInputLayout surnameInputLayout;

    /**
     * Surname Input
     */
    @BindView(R.id.surnameInput)
    @NotEmpty(messageResId = R.string.surname_not_empty_error)
    @Length(min = 3, max = 15)
    protected AppCompatEditText surnameInput;


    /**
     * Birthday Input Layout
     */
    @BindView(R.id.birthdayInputLayout)
    protected TextInputLayout birthdayInputLayout;

    /**
     * Birthday Input
     */
    @BindView(R.id.birthdayInput)
    @NotEmpty(messageResId = R.string.birthday_not_empty_error)
    @Past(dateFormatResId = R.string.date_format)
    protected SupportEditTextDatePicker birthdayInput;

    /**
     * Email Input Layout
     */
    @BindView(R.id.emailInputLayout)
    protected TextInputLayout emailInputLayout;

    /**
     * Email Input
     */
    @BindView(R.id.emailInput)
    @NotEmpty(messageResId = R.string.email_not_empty_error)
    @Email(messageResId = R.string.email_invalid_error)
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
    @NotEmpty(messageResId = R.string.email_not_empty_error)
    @ConfirmEmail(messageResId = R.string.repeat_email_invalid_error)
    protected AppCompatEditText repeatEmailInput;

    /**
     * Password Input Layout
     */
    @BindView(R.id.passwordInputLayout)
    protected TextInputLayout passwordInputLayout;

    /**
     * Password Input
     */
    @BindView(R.id.passwordInput)
    protected AppCompatEditText passwordInput;

    /**
     * Confirm Password Input Layout
     */
    @BindView(R.id.confirmPasswordInputLayout)
    protected TextInputLayout confirmPasswordInputLayout;

    /**
     * Confirm Password Input
     */
    @BindView(R.id.confirmPasswordInput)
    @ConfirmPassword
    protected AppCompatEditText confirmPasswordInput;

    /**
     * Accept Terms And Conditions
     */
    @BindView(R.id.input_accept_terms)
    protected CheckBox acceptTerms;

    /**
     * Create Account Button
     */
    @BindView(R.id.createAccountButton)
    protected Button createAccountButton;

    /**
     * Password Progress
     */
    @BindView(R.id.input_password_progress)
    protected ProgressBar progressBar;


    public SignupMvpFragment() { }

    /**
     * New Instance
     * @return
     */
    public static SignupMvpFragment newInstance() {
        return new SignupMvpFragment();
    }

    /**
     * Reset Errors
     */
    private void resetErrors(){

        nameInputLayout.setError(null);
        surnameInputLayout.setError(null);
        birthdayInputLayout.setError(null);
        emailInputLayout.setError(null);
        repeatEmailInputLayout.setError(null);
        passwordInputLayout.setError(null);
        confirmPasswordInputLayout.setError(null);
        acceptTerms.setError(null);
    }

    /**
     * Reset Fields
     */
    private void resetFields(){

        resetErrors();

        nameInput.setText("");
        surnameInput.setText("");
        birthdayInput.setText("");
        emailInput.setText("");
        repeatEmailInput.setText("");
        passwordInput.setText("");
        confirmPasswordInput.setText("");

    }

    /**
     * Configure Terms Of Service And Privacy Policy
     */
    private void configureTermsOfServiceAndPrivacyPolicy(){

        final String termsOfService = getString(R.string.terms_of_service);
        final String privacyPolicy = getString(R.string.privacy_policy);

        final String acceptTermsText = String.format(Locale.getDefault(),
                getString(R.string.registration_terms_and_conditions), termsOfService, privacyPolicy);

        final int termsOfServicesStart = acceptTermsText.indexOf(termsOfService);
        final int privacyPolicyStart = acceptTermsText.indexOf(privacyPolicy);

        final SpannableStringBuilder spannable = new SpannableStringBuilder(acceptTermsText);

        // Terms of Service Deep Link
        spannable.setSpan(new DeepLinkSpan(acceptTerms, termsOfService, getContext()) {
                              @Override
                              public void onClick(View widget) {
                                  super.onClick(widget);
                                  // Show Terms Of Service
                                  activityHandler.showLegalContent(LegalContentActivity
                                          .LegalTypeEnum.TERMS_OF_SERVICE);
                              }
                          }, termsOfServicesStart,
                termsOfServicesStart + termsOfService.length(), Spanned.SPAN_INCLUSIVE_EXCLUSIVE);

        // Privacy Policy Deep Link
        spannable.setSpan(new DeepLinkSpan(acceptTerms, privacyPolicy, getContext()) {
                              @Override
                              public void onClick(View widget) {
                                  super.onClick(widget);
                                  // Show Privacy Policy
                                  activityHandler.showLegalContent(LegalContentActivity
                                          .LegalTypeEnum.PRIVACY_POLICY);
                              }
                          },  privacyPolicyStart,
                privacyPolicyStart + privacyPolicy.length(), Spanned.SPAN_INCLUSIVE_EXCLUSIVE);

        acceptTerms.setText(spannable);
        acceptTerms.setMovementMethod(LinkMovementMethod.getInstance());

    }

    /**
     * Validate Password
     */
    private boolean validatePassword(final String passwordText) {

        boolean isValid = false;

        if (passwordText.length() < 8) {
            // A minimum of 8 characters
            passwordInputLayout.setError(getString(R.string.error_password_invalid_length));
        } else if(!Pattern.compile("[a-z]+").matcher(passwordText).find()) {
            // At least one lower case letter
            passwordInputLayout.setError(getString(R.string.error_password_lower_case_letter_required));
        } else if(!Pattern.compile("[A-Z]+").matcher(passwordText).find()) {
            // At least one upper case letter
            passwordInputLayout.setError(getString(R.string.error_password_upper_case_letter_required));
        }else  if(!Pattern.compile("[0-9]+|[^A-Za-z0-9]+").matcher(passwordText).find()) {
            // At least one number or special character
            passwordInputLayout.setError(getString(R.string.error_number_or_special_character_required));
        } else if(Pattern.compile("(.)\\1\\1+").matcher(passwordText.toUpperCase()).find()) {
            // No more than two consecutive repeating characters or numbers
            passwordInputLayout.setError(getString(R.string.error_consecutive_repeating_characters_or_number_not_allowed));
        } else {
            passwordInputLayout.setErrorEnabled(false);
            isValid = true;
        }

        return isValid;

    }

    /**
     * Sanitize Data
     */
    private void sanitizeData() {

        nameInput.setText(nameInput.getText().toString()
                .trim().replaceAll("\\s{2,}", " ").replaceAll("\\.", ""));
        surnameInput.setText(surnameInput.getText().toString()
                .trim().replaceAll("\\s{2,}", " ").replaceAll("\\.", ""));
        passwordInput.setText(passwordInput.getText().toString().trim().replaceAll("\\s{2,}", " "));
        emailInput.setText(emailInput.getText().toString().trim().replaceAll("\\s+", ""));
    }

    /**
     * On View Created
     * @param view
     * @param savedInstanceState
     */
    @Override
    public void onViewCreated(View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);


        configureTermsOfServiceAndPrivacyPolicy();

        final Zxcvbn zxcvbn = new Zxcvbn();

        passwordInput.setOnFocusChangeListener(new View.OnFocusChangeListener() {
            @Override
            public void onFocusChange(View v, boolean hasFocus) {
                if(progressBar != null)
                    if(hasFocus)
                        progressBar.setVisibility(View.VISIBLE);
                    else
                        progressBar.setVisibility(View.GONE);
            }
        });

        // Password Change Listener
        passwordInput.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) { }

            @Override
            public void onTextChanged(CharSequence charSequence, int start, int before, int count) {
                final String passwordText = charSequence.toString()
                        .trim().replaceAll(" +", " ");
                if(!passwordText.isEmpty()) {
                    final int passwordScore = zxcvbn.measure(passwordText).getScore();
                    passwordInputLayout.setErrorEnabled(false);
                    // Set Password Score
                    progressBar.setProgress(passwordScore);
                    validatePassword(passwordText);
                }
            }

            @Override
            public void afterTextChanged(Editable editable) { }
        });

        birthdayInput.setMinAge(MIN_AGE_DEFAULT);
        birthdayInput.setMaxAge(MAX_AGE_DEFAULT);

    }

    /**
     * Get Layout Resource
     * @return
     */
    @Override
    protected int getLayoutRes() {
        return R.layout.fragment_signup;
    }

    /**
     * Initialize Injector
     */
    @Override
    protected void initializeInjector(IntroComponent component) {
        component.inject(this);
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

        if (viewId.equals(R.id.nameInput)) {
            nameInputLayout.setError(message);
        } else if(viewId.equals(R.id.surnameInput)) {
            surnameInputLayout.setError(message);
        } else if(viewId.equals(R.id.birthdayInput)) {
            birthdayInputLayout.setError(message);
        } else if(viewId.equals(R.id.emailInput)) {
            emailInputLayout.setError(message);
        } else if(viewId.equals(R.id.passwordInput)) {
            passwordInputLayout.setError(message);
        } else if(viewId.equals(R.id.confirmPasswordInput)) {
            confirmPasswordInputLayout.setError(message);
        } else if(viewId.equals(R.id.repeatEmailInput)) {
            repeatEmailInputLayout.setError(message);
        }


    }

    /**
     * On Validation Succeeded
     */
    @Override
    public void onValidationSucceeded() {

        if (acceptTerms.isChecked()) {

            resetErrors();

            final String name = nameInput.getText().toString();
            final String surname = surnameInput.getText().toString();
            final String birthday = birthdayInput.getDateSelectedAsText();
            final String email = emailInput.getText().toString();
            final String password = passwordInput.getText().toString();
            final String confirmPassword = confirmPasswordInput.getText().toString();
            // Disable Button
            createAccountButton.setEnabled(false);
            // Signup user
            getPresenter().signup(name, surname, birthday, email, password, confirmPassword, "+34673445695");

        } else {
            acceptTerms.setError(getString(R.string.terms_of_service_not_accepted));
        }

    }

    /**
     * Provide Presenter
     * @return
     */
    @NonNull
    @Override
    public SignupFragmentPresenter providePresenter() {
        return component.signupFragmentPresenter();
    }

    /**
     * On Back to Login
     */
    @OnClick(R.id.backToLogin)
    public void onBackToLogin(){
        // Reset Fields
        resetFields();
        // Go back to login
        activityHandler.goToLogin();
    }

    /**
     * On Create Account
     */
    @OnClick(R.id.createAccountButton)
    public void onCreateAccount(){
        //Sanitize Data
        sanitizeData();
        // Reset Errors
        resetErrors();
        // Validate
        validator.validate();
    }


    /**
     * On Signup Success
     */
    @Override
    public void onSignupSuccess(final GuardianEntity guardianEntity) {

        resetFields();

        final String message = String.format(appUtils.getCurrentLocale(), getString(R.string.signup_success),
                guardianEntity.getFullName());

        showConfirmationDialog(message, new ConfirmationDialogFragment.ConfirmationDialogListener() {
            @Override
            public void onAccepted(DialogFragment dialog) {
                activityHandler.openMailApp();
                activityHandler.goToLogin(guardianEntity.getEmail());
            }

            @Override
            public void onRejected(DialogFragment dialog) {
                activityHandler.goToLogin(guardianEntity.getEmail());
            }
        });

        createAccountButton.setEnabled(true);
    }

    /**
     * On Validation Errors
     * @param errors
     */
    @Override
    public void onValidationErrors(List<LinkedHashMap<String, String>> errors) {

        for (LinkedHashMap<String, String> error : errors) {

            Timber.d("Field -> %s, Message -> %s", error.get("field"), error.get("message"));

            switch (error.get("field")) {
                case EMAIL_FIELD_NAME:
                    emailInputLayout.setError(error.get("message"));
                    break;
                case FIRST_NAME_FIELD_NAME:
                    nameInputLayout.setError(error.get("message"));
                    break;
                case LAST_NAME_FIELD_NAME:
                    surnameInputLayout.setError(error.get("message"));
                    break;
                case BIRTHDATE_FIELD_NAME:
                    birthdayInputLayout.setError(error.get("message"));
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


    /**
     * Deep Link Span
     */
    public abstract class DeepLinkSpan extends ClickableSpan {

        private final TextView textView;
        private final String clickableText;
        private final Context appContext;

        private DeepLinkSpan(final TextView textView, final String clickableText,
                            final Context appContext) {
            this.textView = textView;
            this.clickableText = clickableText;
            this.appContext = appContext;
        }

        /**
         * On Click
         * @param widget
         */
        @Override
        public void onClick(View widget) {

            // Prevent CheckBox state from being toggled when link is clicked
            if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.KITKAT) {
                widget.cancelPendingInputEvents();
            }
        }


        /**
         * Update Draw State
         * @param ds
         */
        @Override
        public void updateDrawState(TextPaint ds) {

            ds.setUnderlineText(true);

            if (textView.isPressed() && textView.getSelectionStart() != -1 && textView.getText()
                    .toString()
                    .substring(textView.getSelectionStart(), textView.getSelectionEnd())
                    .equals(clickableText)) {
                ds.setColor(ContextCompat.getColor(appContext, R.color.darkModerateBlue));
            } else {
                ds.setColor(ContextCompat.getColor(appContext, R.color.darkModerateBlue));
            }
        }

    }
}
