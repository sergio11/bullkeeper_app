package sanchez.sanchez.sergio.bullkeeper.ui.dialog;


import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v4.app.DialogFragment;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.TextView;

import java.lang.ref.WeakReference;

import butterknife.BindView;
import sanchez.sanchez.sergio.bullkeeper.R;
import sanchez.sanchez.sergio.bullkeeper.ui.support.SupportDialogFragment;

/**
 * Progress Dialog Fragment
 */
public final class ProgressDialogFragment extends SupportDialogFragment {

    private static WeakReference<ProgressDialogFragment> currentProgressDialog = null;


    public static final String TAG = "PROGRESS_DIALOG_FRAGMENT";

    /**
     * Dialog Title Text View
     */
    @BindView(R.id.dialogTitle)
    protected TextView dialogTitleTextView;

    /**
     * Show Dialog
     * @param activity
     * @return
     */
    public static ProgressDialogFragment showDialog(final AppCompatActivity activity, final String title){
        final ProgressDialogFragment progressDialog = new ProgressDialogFragment();
        progressDialog.setStyle(DialogFragment.STYLE_NO_TITLE, R.style.CommonDialogFragmentTheme);
        // Config Arguments
        final Bundle args = new Bundle();
        args.putString(TITLE_ARG, title);
        progressDialog.setArguments(args);
        progressDialog.show(activity.getSupportFragmentManager(), TAG);
        currentProgressDialog = new WeakReference<>(progressDialog);
        progressDialog.setCancelable(false);
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

    /**
     * Get Layout Res
     * @return
     */
    @Override
    protected int getLayoutRes() {
        return R.layout.progress_dialog_layout;
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

    @Override
    protected void initializeInjector() {

    }
}
