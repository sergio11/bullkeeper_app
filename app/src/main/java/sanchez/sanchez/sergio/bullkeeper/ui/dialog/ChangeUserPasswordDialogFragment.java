package sanchez.sanchez.sergio.bullkeeper.ui.dialog;

import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.design.widget.TextInputLayout;
import android.support.v4.app.DialogFragment;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.AppCompatEditText;
import android.text.Editable;
import android.text.TextWatcher;
import android.view.View;
import android.widget.ProgressBar;
import android.widget.TextView;
import com.mobsandgeeks.saripaar.annotation.ConfirmPassword;
import com.mobsandgeeks.saripaar.annotation.Password;
import com.nulabinc.zxcvbn.Zxcvbn;
import java.util.regex.Pattern;
import javax.inject.Inject;
import butterknife.BindView;
import butterknife.OnClick;
import sanchez.sanchez.sergio.bullkeeper.AndroidApplication;
import sanchez.sanchez.sergio.bullkeeper.R;
import sanchez.sanchez.sergio.bullkeeper.core.sounds.ISoundManager;
import sanchez.sanchez.sergio.bullkeeper.core.ui.SupportValidationDialogFragment;

/**
 * Change User Password
 */
public final class ChangeUserPasswordDialogFragment extends SupportValidationDialogFragment {

    public static final String TAG = "CHANGE_USER_PASSWORD_DIALOG_FRAGMENT";


    /**
     * On Change User Password Dialog Listener
     */
    private OnChangeUserPasswordDialogListener onChangeUserPasswordDialogListener;

    /**
     * Dependencies
     * ==============
     */

    @Inject
    protected ISoundManager soundManager;


    /**
     * Views
     * ==============
     */

    /**
     * Dialog Title
     */
    @BindView(R.id.dialogTitle)
    protected TextView dialogTitleTextView;

    /**
     * Password Input Layout
     */
    @BindView(R.id.passwordInputLayout)
    protected TextInputLayout passwordInputLayout;

    /**
     * Password Input
     */
    @BindView(R.id.passwordInput)
    @Password
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
     * Input Password Progress
     */
    @BindView(R.id.input_password_progress)
    protected ProgressBar inputPasswordProgress;

    /**
     * Validation Error Text View
     */
    @BindView(R.id.validationErrorTextView)
    protected TextView validationErrorTextView;

    /**
     *
     * @param onChangeUserPasswordDialogListener
     */
    public void setOnChangeUserPasswordDialogListener(OnChangeUserPasswordDialogListener onChangeUserPasswordDialogListener) {
        this.onChangeUserPasswordDialogListener = onChangeUserPasswordDialogListener;
    }

    /**
     * Show Dialog
     * @param activity
     * @param onChangeUserPasswordDialogListener
     * @return
     */
    public static ChangeUserPasswordDialogFragment showDialog(final AppCompatActivity activity,
                                                              final OnChangeUserPasswordDialogListener onChangeUserPasswordDialogListener){

        final ChangeUserPasswordDialogFragment changeUserPasswordDialogFragment = new ChangeUserPasswordDialogFragment();
        changeUserPasswordDialogFragment.setStyle(DialogFragment.STYLE_NO_TITLE, R.style.CommonDialogFragmentTheme);
        // Config Arguments
        final Bundle args = new Bundle();
        args.putString(TITLE_ARG, activity.getString(R.string.user_profile_change_password_dialog_title));
        changeUserPasswordDialogFragment.setArguments(args);

        // Config Listener
        if(onChangeUserPasswordDialogListener != null)
            changeUserPasswordDialogFragment.setOnChangeUserPasswordDialogListener(onChangeUserPasswordDialogListener);

        changeUserPasswordDialogFragment.setCancelable(false);
        changeUserPasswordDialogFragment.show(activity.getSupportFragmentManager(), TAG);
        return changeUserPasswordDialogFragment;
    }

    /**
     * On View Created
     * @param view
     * @param savedInstanceState
     */
    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        // Set Dialog Title Text View
        dialogTitleTextView.setText(title);
        soundManager.playSound(ISoundManager.DIALOG_CONFIRM_SOUND);

        final Zxcvbn zxcvbn = new Zxcvbn();

        passwordInput.setOnFocusChangeListener(new View.OnFocusChangeListener() {
            @Override
            public void onFocusChange(View v, boolean hasFocus) {
                if(inputPasswordProgress != null)
                    if(hasFocus)
                        inputPasswordProgress.setVisibility(View.VISIBLE);
                    else
                        inputPasswordProgress.setVisibility(View.GONE);
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
                    inputPasswordProgress.setProgress(passwordScore);
                    validatePassword(passwordText);
                }
            }

            @Override
            public void afterTextChanged(Editable editable) { }
        });
    }

    /**
     * On Reset Errors
     */
    @Override
    protected void onResetErrors() {
        passwordInput.setError(null);
        confirmPasswordInput.setError(null);
    }

    /**
     * on Reset Fields
     */
    @Override
    protected void onResetFields() {
        super.onResetFields();
        passwordInput.setText("");
        confirmPasswordInput.setText("");
    }

    /**
     * On Validation Failed
     */
    @Override
    protected void onValidationFailed() {
        validationErrorTextView.setVisibility(View.VISIBLE);
    }

    /**
     * On Filed Invalid
     * @param viewId
     * @param message
     */
    @Override
    protected void onFieldInvalid(final Integer viewId, final String message) {
        if(viewId.equals(R.id.passwordInput)) {
            passwordInputLayout.setError(message);
        } else if(viewId.equals(R.id.confirmPasswordInput)) {
            confirmPasswordInputLayout.setError(message);
        }
    }

    /**
     * on Validation Succeeded
     */
    @Override
    public void onValidationSucceeded() {
        super.onValidationSucceeded();

        final String newPassword = passwordInput.getText().toString();
        final String repeatPassword = confirmPasswordInput.getText().toString();
        if(validatePassword(newPassword)) {
            validationErrorTextView.setVisibility(View.GONE);
            dismiss();
            if(onChangeUserPasswordDialogListener != null) {
                onChangeUserPasswordDialogListener.onAccepted(this,
                        newPassword, repeatPassword);
            }
        } else {
            validationErrorTextView.setVisibility(View.VISIBLE);
        }

    }

    /**
     * Get Layout Resource
     * @return
     */
    @Override
    protected int getLayoutRes() {
        return R.layout.change_user_password_dialog;
    }

    /**
     * initialize Injector
     */
    @Override
    protected void initializeInjector() {
        AndroidApplication.getInstance().getApplicationComponent()
                .inject(this);
    }

    /**
     * On Accept
     */
    @OnClick(R.id.accept)
    protected void onAccept(){
        validate();
    }

    /**
     * On Cancel
     */
    @OnClick(R.id.cancel)
    protected void onCancel(){
        if (onChangeUserPasswordDialogListener != null)
            onChangeUserPasswordDialogListener.onCancel(this);
        dismiss();
    }


    /**
     * On Change User Password Dialog Listener
     */
    public interface OnChangeUserPasswordDialogListener {

        /**
         * On Accepted
         * @param dialog
         * @param newPassword
         * @param repeatNewPassword
         */
        void onAccepted(final DialogFragment dialog,
                        final String newPassword,
                        final String repeatNewPassword);


        /**
         * On Cancel
         * @param dialog
         */
        void onCancel(final DialogFragment dialog);
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

}
