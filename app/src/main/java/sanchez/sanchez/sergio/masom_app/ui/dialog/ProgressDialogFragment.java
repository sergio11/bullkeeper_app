package sanchez.sanchez.sergio.masom_app.ui.dialog;

import android.app.Dialog;
import android.os.Bundle;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.TextView;

import java.lang.ref.WeakReference;

import sanchez.sanchez.sergio.masom_app.R;

/**
 * Progress Dialog Fragment
 */
public final class ProgressDialogFragment extends SupportDialog {

    private static WeakReference<ProgressDialogFragment> currentProgressDialog = null;


    public static final String TAG = "PROGRESS_DIALOG_FRAGMENT";


    /**
     * on Create Dialog
     * @param savedInstanceState
     * @return
     */
    @Override
    public Dialog onCreateDialog(Bundle savedInstanceState) {

        final LayoutInflater layoutInflater = getActivity().getLayoutInflater();

        final View dialogView = layoutInflater.inflate(R.layout.progress_dialog_fragment, null);

        final TextView progressText = dialogView.findViewById(R.id.progressText);

        progressText.setText(title);

        // Create Dialog
        final AlertDialog alertDialog =  new AlertDialog.Builder(getActivity())
                .setView(dialogView)
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
    public static ProgressDialogFragment showDialog(final AppCompatActivity activity, final String title){
        final ProgressDialogFragment progressDialog = new ProgressDialogFragment();
        // Config Arguments
        final Bundle args = new Bundle();
        args.putString(TITLE_ARG, title);
        progressDialog.setArguments(args);
        progressDialog.show(activity.getSupportFragmentManager(), TAG);
        currentProgressDialog = new WeakReference<>(progressDialog);
        return progressDialog;
    }

    /**
     * Cancel Current Progress Dialog
     */
    public static void cancelCurrent(){

        if(currentProgressDialog != null && currentProgressDialog.get() != null) {
            currentProgressDialog.get().dismiss();
            currentProgressDialog.clear();
            currentProgressDialog = null;

        }
    }

}
