package sanchez.sanchez.sergio.masom_app.ui.fragment.signup;

import android.app.DatePickerDialog;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.design.widget.TextInputLayout;
import android.support.v7.widget.AppCompatEditText;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.DatePicker;
import com.mobsandgeeks.saripaar.annotation.ConfirmPassword;
import com.mobsandgeeks.saripaar.annotation.Email;
import com.mobsandgeeks.saripaar.annotation.Length;
import com.mobsandgeeks.saripaar.annotation.NotEmpty;
import com.mobsandgeeks.saripaar.annotation.Password;
import com.mobsandgeeks.saripaar.annotation.Past;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Locale;
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
public class SignupFragment extends
        SupportValidationFragment<SignupFragmentPresenter, ISignupView, IIntroActivityHandler>
implements ISignupView, DatePickerDialog.OnDateSetListener{

    public static String TAG = "INTRO_FRAGMENT";

    private IntroComponent introComponent;

    private DatePickerDialog datePickerDialog;

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
    @Past(dateFormat = "yyyy/MM/dd")
    protected AppCompatEditText birthdayInput;

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
     * Password Input Layout
     */
    @BindView(R.id.passwordInputLayout)
    protected TextInputLayout passwordInputLayout;

    /**
     * Password Input
     */
    @BindView(R.id.passwordInput)
    @Password(scheme = Password.Scheme.ALPHA_NUMERIC_MIXED_CASE)
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


    public SignupFragment() { }

    /**
     * New Instance
     * @return
     */
    public static SignupFragment newInstance() {
        SignupFragment fragment = new SignupFragment();
        return fragment;
    }

    /**
     * Reset Errors
     */
    private void resetErrors(){

        nameInputLayout.setError("");
        surnameInputLayout.setError("");
        birthdayInputLayout.setError("");
        emailInputLayout.setError("");
        passwordInputLayout.setError("");
        confirmPasswordInputLayout.setError("");
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
        passwordInput.setText("");
        confirmPasswordInput.setText("");

    }


    @Override
    protected void initializeInjector() {
        introComponent = IntroComponent.class
                .cast(((HasComponent<IntroComponent>) getActivity())
                        .getComponent());
        introComponent.inject(this);
    }

    @Override
    public void onViewCreated(View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);

        Integer START_YEAR = 1970;
        Integer START_MONTH = 1;
        Integer START_DAY = 1;

        datePickerDialog = new DatePickerDialog(
                getActivity(), R.style.CommonDatePickerStyle, SignupFragment.this, START_YEAR,
                START_MONTH, START_DAY);

        // On Focus Listener
        birthdayInput.setOnFocusChangeListener(new View.OnFocusChangeListener() {
            @Override
            public void onFocusChange(View view, boolean hasFocus) {

                if(datePickerDialog != null) {
                    if(hasFocus)
                        datePickerDialog.show();
                    else
                        datePickerDialog.dismiss();
                }
            }
        });

        // On Click Listener
        birthdayInput.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if(datePickerDialog != null)
                    datePickerDialog.show();
            }
        });

    }

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
        }


    }

    @Override
    public void onValidationSucceeded() {

        resetErrors();

        final String name = nameInput.getText().toString();
        final String surname = surnameInput.getText().toString();
        final String birthday = birthdayInput.getText().toString();
        final String email = emailInput.getText().toString();
        final String password = passwordInput.getText().toString();
        final String confirmPassword = confirmPasswordInput.getText().toString();

        showShortMessage("Validation Success");
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
        return inflater.inflate(R.layout.fragment_signup, container, false);
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
        // Reset Errors
        resetErrors();
        // Validate
        validator.validate();
    }

    /**
     * On Date Set
     * @param datePicker
     * @param year
     * @param month
     * @param day
     */
    @Override
    public void onDateSet(DatePicker datePicker, int year, int month, int day) {

        final Calendar calendar = Calendar.getInstance();
        calendar.set(year, month, day);

        SimpleDateFormat format = new SimpleDateFormat("yyyy/MM/dd", Locale.getDefault());
        String strDate = format.format(calendar.getTime());
        // Set Data format
        birthdayInput.setText(strDate);
    }
}
