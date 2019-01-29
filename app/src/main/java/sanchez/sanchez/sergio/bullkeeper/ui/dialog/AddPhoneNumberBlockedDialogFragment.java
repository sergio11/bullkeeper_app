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

import javax.inject.Inject;

import butterknife.BindView;
import butterknife.OnClick;
import sanchez.sanchez.sergio.bullkeeper.AndroidApplication;
import sanchez.sanchez.sergio.bullkeeper.R;
import sanchez.sanchez.sergio.bullkeeper.core.sounds.ISoundManager;
import sanchez.sanchez.sergio.bullkeeper.core.ui.SupportDialogFragment;

/**
 * Add Phone Number Blocked Fragment
 */
public final class AddPhoneNumberBlockedDialogFragment extends SupportDialogFragment {

    public static final String TAG = "ADD_PHONE_NUMBER_BLOCKED_DIALOG_FRAGMENT";

    /**
     * On Phone Number Selected
     */
    private OnPhoneNumberSelectedDialogListener phoneNumberSelectedDialogListener;

    /**
     * Dependencies
     * ==============
     */

    @Inject
    protected ISoundManager soundManager;


    /**
     * Tfno Input Layout
     */
    @BindView(R.id.tfnoInputLayout)
    protected TextInputLayout tfnoInputLayout;

    /**
     * Tfno Input
     */
    @BindView(R.id.tfnoInput)
    protected AppCompatEditText tfnoInput;


    /**
     * Views
     * ==============
     */

    /**
     * Dialog Title
     */
    @BindView(R.id.dialogTitle)
    protected TextView dialogTitleTextView;


    public void setPhoneNumberSelectedDialogListener(OnPhoneNumberSelectedDialogListener phoneNumberSelectedDialogListener) {
        this.phoneNumberSelectedDialogListener = phoneNumberSelectedDialogListener;
    }

    /**
     * Show Dialog
     * @param activity
     * @return
     */
    public static AddPhoneNumberBlockedDialogFragment showDialog(final AppCompatActivity activity,
                                                                 final String title,
                                                                 final OnPhoneNumberSelectedDialogListener phoneNumberSelectedDialogListener){
        final AddPhoneNumberBlockedDialogFragment addPhoneNumberBlockedDialog = new AddPhoneNumberBlockedDialogFragment();
        addPhoneNumberBlockedDialog.setStyle(DialogFragment.STYLE_NO_TITLE, R.style.CommonDialogFragmentTheme);
        // Config Arguments
        final Bundle args = new Bundle();
        args.putString(TITLE_ARG, title);
        addPhoneNumberBlockedDialog.setArguments(args);

        // Config Listener
        if(phoneNumberSelectedDialogListener != null)
            addPhoneNumberBlockedDialog.setPhoneNumberSelectedDialogListener(phoneNumberSelectedDialogListener);

        addPhoneNumberBlockedDialog.setCancelable(false);
        addPhoneNumberBlockedDialog.show(activity.getSupportFragmentManager(), TAG);
        return addPhoneNumberBlockedDialog;
    }

    /**
     * Show Dialog
     * @param activity
     * @param title
     * @return
     */
    public static AddPhoneNumberBlockedDialogFragment showDialog(final AppCompatActivity activity, final String title) {
        return showDialog(activity, title, null);
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
     * Get Layout Resource
     * @return
     */
    @Override
    protected int getLayoutRes() {
        return R.layout.add_phone_number_blocked_dialog;
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
        if(phoneNumberSelectedDialogListener != null) {

            final String prefix = getString(R.string.tfno_prefix);
            final String number = tfnoInput.getText().toString();
            final String phoneNumber = prefix.concat(number);
            phoneNumberSelectedDialogListener.onAccepted(this, prefix, number, phoneNumber);
        }
        dismiss();

    }

    /**
     * On Cancel
     */
    @OnClick(R.id.cancel)
    protected void onCancel(){
        if (phoneNumberSelectedDialogListener != null)
            phoneNumberSelectedDialogListener.onRejected(this);
        dismiss();
    }

    /**
     * On Phone Number Selected Dialog Dialog Listener
     */
    public interface OnPhoneNumberSelectedDialogListener {

        /**
         * On Accepted
         * @param dialog
         * @param prefix
         * @param number
         * @param phoneNumber
         */
        void onAccepted(final DialogFragment dialog, final String prefix, final String number, final String phoneNumber);


        /**
         * On Rejected
         * @param dialog
         */
        void onRejected(final DialogFragment dialog);
    }

}
