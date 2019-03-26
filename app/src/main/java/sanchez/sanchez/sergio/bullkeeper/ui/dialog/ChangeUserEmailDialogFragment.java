package sanchez.sanchez.sergio.bullkeeper.ui.dialog;

import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.design.widget.TextInputLayout;
import android.support.v4.app.DialogFragment;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.AppCompatEditText;
import android.view.View;
import android.widget.TextView;
import com.mobsandgeeks.saripaar.annotation.ConfirmEmail;
import com.mobsandgeeks.saripaar.annotation.Email;
import com.mobsandgeeks.saripaar.annotation.NotEmpty;
import javax.inject.Inject;
import butterknife.BindView;
import butterknife.OnClick;
import sanchez.sanchez.sergio.bullkeeper.AndroidApplication;
import sanchez.sanchez.sergio.bullkeeper.R;
import sanchez.sanchez.sergio.bullkeeper.core.sounds.ISoundManager;
import sanchez.sanchez.sergio.bullkeeper.core.ui.SupportValidationDialogFragment;

/**
 * Change User Email
 */
public final class ChangeUserEmailDialogFragment extends SupportValidationDialogFragment {

    public static final String TAG = "CHANGE_USER_EMAIL_DIALOG_FRAGMENT";


    /**
     * On Change User Email Dialog Listener
     */
    private OnChangeUserEmailDialogListener onChangeUserEmailDialogListener;

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
     * Validation Error Text View
     */
    @BindView(R.id.validationErrorTextView)
    protected TextView validationErrorTextView;

    /**
     * Dialog Title
     */
    @BindView(R.id.dialogTitle)
    protected TextView dialogTitleTextView;

    /**
     * New Email Input Layout
     */
    @BindView(R.id.newEmailInputLayout)
    protected TextInputLayout newEmailInputLayout;

    /**
     * New Email Input
     */
    @BindView(R.id.newEmailInput)
    @NotEmpty(messageResId = R.string.email_not_empty_error)
    @Email(messageResId = R.string.email_invalid_error)
    protected AppCompatEditText newEmailInput;

    /**
     * Repeat New Email Input Layout
     */
    @BindView(R.id.repeatNewEmailInputLayout)
    protected TextInputLayout repeatNewEmailInputLayout;

    /**
     * Repeat New Email Input
     */
    @BindView(R.id.repeatNewEmailInput)
    @NotEmpty(messageResId = R.string.email_not_empty_error)
    @ConfirmEmail(messageResId = R.string.repeat_email_invalid_error)
    protected AppCompatEditText repeatNewEmailInput;


    /**
     * Show Dialog
     * @param activity
     * @param onChangeUserEmailDialogListener
     * @return
     */
    public static ChangeUserEmailDialogFragment showDialog(final AppCompatActivity activity,
                                                           final OnChangeUserEmailDialogListener onChangeUserEmailDialogListener){

        final ChangeUserEmailDialogFragment changeUserEmailDialogFragment = new ChangeUserEmailDialogFragment();
        changeUserEmailDialogFragment.setStyle(DialogFragment.STYLE_NO_TITLE, R.style.CommonDialogFragmentTheme);
        // Config Arguments
        final Bundle args = new Bundle();
        args.putString(TITLE_ARG, activity.getString(R.string.user_profile_change_email_dialog_title));
        changeUserEmailDialogFragment.setArguments(args);

        // Config Listener
        if(onChangeUserEmailDialogListener != null)
            changeUserEmailDialogFragment.setOnChangeUserEmailDialogListener(onChangeUserEmailDialogListener);

        changeUserEmailDialogFragment.setCancelable(false);
        changeUserEmailDialogFragment.show(activity.getSupportFragmentManager(), TAG);
        return changeUserEmailDialogFragment;
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
    }

    /**
     * On Reset Errors
     */
    @Override
    protected void onResetErrors() {
        newEmailInput.setError(null);
        repeatNewEmailInput.setError(null);
    }

    /**
     * on Reset Fields
     */
    @Override
    protected void onResetFields() {
        super.onResetFields();
        newEmailInput.setText("");
        repeatNewEmailInput.setText("");
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
        if(viewId.equals(R.id.newEmailInput)) {
            newEmailInputLayout.setError(message);
        } else if(viewId.equals(R.id.repeatNewEmailInput)) {
            repeatNewEmailInputLayout.setError(message);
        }
    }

    /**
     * on Validation Succeeded
     */
    @Override
    public void onValidationSucceeded() {
        super.onValidationSucceeded();
        validationErrorTextView.setVisibility(View.GONE);
        dismiss();
        if(onChangeUserEmailDialogListener != null) {
            final String newEmail = newEmailInput.getText().toString();
            onChangeUserEmailDialogListener.onAccepted(this, newEmail);
        }
    }

    /**
     * Get Layout Resource
     * @return
     */
    @Override
    protected int getLayoutRes() {
        return R.layout.change_user_email_dialog;
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
        if (onChangeUserEmailDialogListener != null)
            onChangeUserEmailDialogListener.onCancel(this);
        dismiss();
    }

    /**
     *
     * @param onChangeUserEmailDialogListener
     */
    public void setOnChangeUserEmailDialogListener(final OnChangeUserEmailDialogListener onChangeUserEmailDialogListener) {
        this.onChangeUserEmailDialogListener = onChangeUserEmailDialogListener;
    }

    /**
     * On Change User Email Dialog Listener
     */
    public interface OnChangeUserEmailDialogListener {

        /**
         * On Accepted
         * @param dialog
         * @param newEmail
         */
        void onAccepted(final DialogFragment dialog, final String newEmail);


        /**
         * On Cancel
         * @param dialog
         */
        void onCancel(final DialogFragment dialog);
    }

}
