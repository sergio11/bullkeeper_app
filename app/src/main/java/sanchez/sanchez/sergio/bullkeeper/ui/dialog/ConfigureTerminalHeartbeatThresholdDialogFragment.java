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
 * Configure Terminal Heartbeat Threshold Dialog Fragment
 */
public final class ConfigureTerminalHeartbeatThresholdDialogFragment extends SupportDialogFragment {

    public static final String TAG = "CONFIGURE_TERMINAL_HEARTBEAT_THRESHOLD_DIALOG_FRAGMENT";

    /**
     * On Configure Terminal Heartbeat Threshold Dialog Listener
     */
    private OnConfigureTerminalHeartbeatThresholdDialogListener configureTerminalHeartbeatThresholdDialogListener;

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


    public void setConfigureTerminalHeartbeatThresholdListener(OnConfigureTerminalHeartbeatThresholdDialogListener configureTerminalHeartbeatThresholdDialogListener) {
        this.configureTerminalHeartbeatThresholdDialogListener = configureTerminalHeartbeatThresholdDialogListener;
    }

    /**
     * Show Dialog
     * @param activity
     * @return
     */
    public static ConfigureTerminalHeartbeatThresholdDialogFragment showDialog(final AppCompatActivity activity,
                                                                               final String title,
                                                                               final OnConfigureTerminalHeartbeatThresholdDialogListener configureTerminalHeartbeatThresholdDialogListener){
        final ConfigureTerminalHeartbeatThresholdDialogFragment dialogFragment = new ConfigureTerminalHeartbeatThresholdDialogFragment();
        dialogFragment.setStyle(DialogFragment.STYLE_NO_TITLE, R.style.CommonDialogFragmentTheme);
        // Config Arguments
        final Bundle args = new Bundle();
        args.putString(TITLE_ARG, title);
        dialogFragment.setArguments(args);

        // Config Listener
        if(configureTerminalHeartbeatThresholdDialogListener != null)
            dialogFragment.setConfigureTerminalHeartbeatThresholdListener(configureTerminalHeartbeatThresholdDialogListener);

        dialogFragment.setCancelable(false);
        dialogFragment.show(activity.getSupportFragmentManager(), TAG);
        return dialogFragment;
    }

    /**
     * Show Dialog
     * @param activity
     * @param title
     * @return
     */
    public static ConfigureTerminalHeartbeatThresholdDialogFragment showDialog(final AppCompatActivity activity, final String title) {
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
        return R.layout.configure_terminal_heartbeat_threshold_dialog;
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
        if (configureTerminalHeartbeatThresholdDialogListener != null)
            configureTerminalHeartbeatThresholdDialogListener.onSave(this);
        dismiss();

    }

    /**
     * On Cancel
     */
    @OnClick(R.id.cancel)
    protected void onCancel(){
        if (configureTerminalHeartbeatThresholdDialogListener != null)
            configureTerminalHeartbeatThresholdDialogListener.onCancel(this);
        dismiss();
    }

    /**
     * On Configure Terminal Heartbeat Threshold Dialog
     */
    public interface OnConfigureTerminalHeartbeatThresholdDialogListener {

        /**
         * On Save
         * @param dialog
         */
        void onSave(final DialogFragment dialog);


        /**
         * On Cancel
         */
        void onCancel(final DialogFragment dialog);
    }

}
