package sanchez.sanchez.sergio.masom_app.ui.dialog;

import android.app.Dialog;
import android.content.DialogInterface;
import android.os.Bundle;
import android.support.v4.app.DialogFragment;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;

/**
 * Notice Dialog Fragment
 */
public final class NoticeDialogFragment extends SupportDialog
    implements DialogInterface.OnClickListener{

    public static final String TAG = "NOTICE_DIALOG_FRAGMENT";

    private NoticeDialogListener noticeDialogListener;

    public void setNoticeDialogListener(NoticeDialogListener noticeDialogListener) {
        this.noticeDialogListener = noticeDialogListener;
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
                .setPositiveButton(android.R.string.ok, this)
                .create();

        alertDialog.setCanceledOnTouchOutside(Boolean.FALSE);
        alertDialog.setCancelable(Boolean.FALSE);

        return alertDialog;
    }

    /**
     * On Positive Click
     * @param dialogInterface
     * @param i
     */
    @Override
    public void onClick(DialogInterface dialogInterface, int i) {
        if(noticeDialogListener != null)
            noticeDialogListener.onAccepted(this);
    }


    /**
     * Show Dialog
     * @param activity
     * @return
     */
    public static NoticeDialogFragment showDialog(final AppCompatActivity activity,
                                                  final String title, final NoticeDialogListener noticeDialogListener){
        final NoticeDialogFragment noticeDialog = new NoticeDialogFragment();
        // Config Arguments
        final Bundle args = new Bundle();
        args.putString(TITLE_ARG, title);
        noticeDialog.setArguments(args);

        // Config Listener
        if(noticeDialogListener != null)
            noticeDialog.setNoticeDialogListener(noticeDialogListener);

        noticeDialog.show(activity.getSupportFragmentManager(), TAG);
        return noticeDialog;
    }

    /**
     * Show Dialog
     * @param activity
     * @param title
     * @return
     */
    public static NoticeDialogFragment showDialog(final AppCompatActivity activity, final String title) {
        return showDialog(activity, title, null);
    }


    /**
     * Notice Dialog Listener
     */
    public interface NoticeDialogListener {

        /**
         * On Accepted
         * @param dialog
         */
        void onAccepted(final DialogFragment dialog);
    }



}
