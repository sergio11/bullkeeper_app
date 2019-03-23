package sanchez.sanchez.sergio.bullkeeper.ui.dialog;

import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v4.app.DialogFragment;
import android.support.v4.app.FragmentManager;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.TextView;

import javax.inject.Inject;

import butterknife.BindView;
import butterknife.OnClick;
import sanchez.sanchez.sergio.bullkeeper.AndroidApplication;
import sanchez.sanchez.sergio.bullkeeper.R;
import sanchez.sanchez.sergio.bullkeeper.core.sounds.ISoundManager;
import sanchez.sanchez.sergio.bullkeeper.core.ui.SupportDialogFragment;
import timber.log.Timber;

/**
 * Notice Dialog Fragment
 */
public final class NoticeDialogFragment extends SupportDialogFragment {

    public static final String TAG = "NOTICE_DIALOG_FRAGMENT";

    private final static String IS_SUCCESS_ARG = "IS_SUCCESS_ARG";

    private NoticeDialogListener noticeDialogListener;

    /**
     * Dependencies
     * ============
     */

    @Inject
    protected ISoundManager soundManager;

    /**
     * Dialog Title
     */
    @BindView(R.id.dialogTitle)
    protected TextView dialogTitleTextView;

    public void setNoticeDialogListener(NoticeDialogListener noticeDialogListener) {
        this.noticeDialogListener = noticeDialogListener;
    }

    /**
     * Get Layout Resource
     * @return
     */
    @Override
    protected int getLayoutRes() {
        return R.layout.notice_dialog_layout;
    }

    /**
     * Initialize Injector
     */
    @Override
    protected void initializeInjector() {
        AndroidApplication.getInstance().getApplicationComponent()
                .inject(this);
    }

    /**
     * On View Created
     * @param view
     * @param savedInstanceState
     */
    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);

        // Set Dialog Title
        dialogTitleTextView.setText(title);

        if(getArguments() != null && getArguments().getBoolean(IS_SUCCESS_ARG))
            soundManager.playSound(ISoundManager.DIALOG_SUCCESS_SOUND);
        else
            soundManager.playSound(ISoundManager.DIALOG_ERROR_SOUND);
    }



    /**
     * on Pause
     */
    @Override
    public void onPause() {
        super.onPause();
        Timber.d("MAA-176: Dialog Fragment on Pause");
        dismissAllowingStateLoss();
    }

    @Override
    public void onStop() {
        super.onStop();
        Timber.d("MAA-176: Dialog Fragment on Stop");
    }



    /**
     * Show Dialog
     * @param activity
     * @return
     */
    public static NoticeDialogFragment showDialog(final AppCompatActivity activity,
                                                  final String title, final boolean isSuccess, final NoticeDialogListener noticeDialogListener){
        final NoticeDialogFragment noticeDialog = new NoticeDialogFragment();
        noticeDialog.setStyle(DialogFragment.STYLE_NO_TITLE, R.style.CommonDialogFragmentTheme);
        // Config Arguments
        final Bundle args = new Bundle();
        args.putString(TITLE_ARG, title);
        args.putBoolean(IS_SUCCESS_ARG, isSuccess);
        noticeDialog.setArguments(args);
        noticeDialog.setCancelable(false);
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
        return showDialog(activity, title, true);
    }

    /**
     * Show Dialog
     * @param activity
     * @param title
     * @return
     */
    public static NoticeDialogFragment showDialog(final AppCompatActivity activity, final String title, final boolean isSuccess) {
        return showDialog(activity, title, isSuccess, null);
    }


    /**
     * On Dismiss
     */
    @OnClick(R.id.dismiss)
    protected void onClick(){
        if(noticeDialogListener != null)
            noticeDialogListener.onAccepted(this);
        dismiss();
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
