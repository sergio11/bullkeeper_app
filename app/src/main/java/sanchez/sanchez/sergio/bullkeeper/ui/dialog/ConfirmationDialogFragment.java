package sanchez.sanchez.sergio.bullkeeper.ui.dialog;


import android.app.Dialog;
import android.content.DialogInterface;
import android.os.Bundle;
import android.support.v4.app.DialogFragment;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;

public final class ConfirmationDialogFragment extends SupportDialog {

    public static final String TAG = "CONFIRMATION_DIALOG_FRAGMENT";

    private ConfirmationDialogListener confirmationDialogListener;

    public void setConfirmationDialogListener(ConfirmationDialogListener confirmationDialogListener) {
        this.confirmationDialogListener = confirmationDialogListener;
    }

    /**
     * on Create Dialog
     * @param savedInstanceState
     * @return
     */
    @Override
    public Dialog onCreateDialog(Bundle savedInstanceState) {

        // Create Dialog
        final AlertDialog alertDialog =  new AlertDialog.Builder(getActivity())
                .setMessage(title)
                .setPositiveButton(android.R.string.ok, new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialogInterface, int i) {
                        confirmationDialogListener.onAccepted(ConfirmationDialogFragment.this);
                    }
                })
                .setNegativeButton(android.R.string.cancel, new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialogInterface, int i) {
                        confirmationDialogListener.onRejected(ConfirmationDialogFragment.this);
                    }
                })
                .create();

        alertDialog.setCanceledOnTouchOutside(Boolean.FALSE);
        alertDialog.setCancelable(Boolean.FALSE);

        return alertDialog;
    }


    /**
     * Show Dialog
     * @param activity
     * @return
     */
    public static ConfirmationDialogFragment showDialog(final AppCompatActivity activity,
                                                        final String title, final ConfirmationDialogListener confirmationDialogListener){
        final ConfirmationDialogFragment confirmationDialog = new ConfirmationDialogFragment();
        // Config Arguments
        final Bundle args = new Bundle();
        args.putString(TITLE_ARG, title);
        confirmationDialog.setArguments(args);

        // Config Listener
        if(confirmationDialogListener != null)
            confirmationDialog.setConfirmationDialogListener(confirmationDialogListener);

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
