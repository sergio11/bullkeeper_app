package sanchez.sanchez.sergio.bullkeeper.ui.dialog;

import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v4.app.DialogFragment;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.TextView;
import butterknife.BindView;
import butterknife.OnClick;
import sanchez.sanchez.sergio.bullkeeper.R;
import sanchez.sanchez.sergio.bullkeeper.ui.support.SupportDialogFragment;

/**
 * Confirmation Dialog Fragment
 */
public final class ConfirmationDialogFragment extends SupportDialogFragment {

    public static final String TAG = "CONFIRMATION_DIALOG_FRAGMENT";

    private ConfirmationDialogListener confirmationDialogListener;

    /**
     * Dialog Title
     */
    @BindView(R.id.dialogTitle)
    protected TextView dialogTitleTextView;

    public void setConfirmationDialogListener(ConfirmationDialogListener confirmationDialogListener) {
        this.confirmationDialogListener = confirmationDialogListener;
    }

    /**
     * Show Dialog
     * @param activity
     * @return
     */
    public static ConfirmationDialogFragment showDialog(final AppCompatActivity activity,
                                                        final String title, final ConfirmationDialogListener confirmationDialogListener){
        final ConfirmationDialogFragment confirmationDialog = new ConfirmationDialogFragment();
        confirmationDialog.setStyle(DialogFragment.STYLE_NO_TITLE, R.style.CommonDialogFragmentTheme);
        // Config Arguments
        final Bundle args = new Bundle();
        args.putString(TITLE_ARG, title);
        confirmationDialog.setArguments(args);

        // Config Listener
        if(confirmationDialogListener != null)
            confirmationDialog.setConfirmationDialogListener(confirmationDialogListener);

        confirmationDialog.setCancelable(false);
        confirmationDialog.show(activity.getSupportFragmentManager(), TAG);
        return confirmationDialog;
    }

    /**
     * Show Dialog
     * @param activity
     * @param title
     * @return
     */
    public static ConfirmationDialogFragment showDialog(final AppCompatActivity activity, final String title) {
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


    }

    /**
     * Get Layout Resource
     * @return
     */
    @Override
    protected int getLayoutRes() {
        return R.layout.confirmation_dialog_layout;
    }

    /**
     *
     */
    @Override
    protected void initializeInjector() { }

    /**
     * On Accept
     */
    @OnClick(R.id.accept)
    protected void onAccept(){
        if(confirmationDialogListener != null)
            confirmationDialogListener.onAccepted(this);
        dismiss();

    }

    /**
     * On Cancel
     */
    @OnClick(R.id.cancel)
    protected void onCancel(){
        if (confirmationDialogListener != null)
            confirmationDialogListener.onRejected(this);
        dismiss();
    }

    /**
     * Confirmation Dialog Listener
     */
    public interface ConfirmationDialogListener {

        /**
         * On Accepted
         * @param dialog
         */
        void onAccepted(final DialogFragment dialog);


        /**
         * On Rejected
         * @param dialog
         */
        void onRejected(final DialogFragment dialog);
    }

}
