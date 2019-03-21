package sanchez.sanchez.sergio.bullkeeper.ui.dialog;

import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v4.app.DialogFragment;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.CompoundButton;
import android.widget.TextView;
import java.util.Locale;
import javax.inject.Inject;
import butterknife.BindView;
import butterknife.OnClick;
import nl.dionsegijn.steppertouch.OnStepCallback;
import nl.dionsegijn.steppertouch.StepperTouch;
import sanchez.sanchez.sergio.bullkeeper.AndroidApplication;
import sanchez.sanchez.sergio.bullkeeper.R;
import sanchez.sanchez.sergio.bullkeeper.core.sounds.ISoundManager;
import sanchez.sanchez.sergio.bullkeeper.core.ui.SupportDialogFragment;
import sanchez.sanchez.sergio.bullkeeper.core.ui.components.SupportSwitchCompat;

/**
 * Configure Terminal Heartbeat Threshold Dialog Fragment
 */
public final class ConfigureTerminalHeartbeatThresholdDialogFragment extends SupportDialogFragment {

    public static final String TAG = "CONFIGURE_TERMINAL_HEARTBEAT_THRESHOLD_DIALOG_FRAGMENT";

    /**
     * Args
     */
    public static String ALERT_THRESHOLD_IN_MINUTES = "ALERT_THRESHOLD_IN_MINUTES";
    public static String ALERT_MODE_ENABLED = "ALERT_MODE_ENABLED";

    /**
     * Min Heart Beat
     */
    private final int MIN_HEART_BEAT_VALUE = 10;

    /**
     * Max Heart Beat
     */
    private final int MAX_HEART_BEAT_VALUE = 120;

    private OnConfigureTerminalHeartbeatThresholdDialogListener configureTerminalHeartbeatThresholdDialogListener;

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

    @BindView(R.id.dialogTitle)
    protected TextView dialogTitleTextView;

    /**
     * Alert Mode Enabled Switch
     */
    @BindView(R.id.alertModeEnabledSwitch)
    protected SupportSwitchCompat alertModeEnabledSwitch;

    /**
     * Alert Threshold In Minutes Stepper Touch
     */
    @BindView(R.id.alertThresholdInMinutesStepperTouch)
    protected StepperTouch alertThresholdInMinutesStepperTouch;

    /**
     * Alert Threshold In Minutes Text View
     */
    @BindView(R.id.alertThresholdInMinutes)
    protected TextView alertThresholdInMinutesTextView;

    /**
     * Alert Mode Disabled
     */
    @BindView(R.id.alertModeDisabled)
    protected TextView alertModeDisabledTextView;




    /**
     * Show Dialog
     * @param activity
     * @param configureTerminalHeartbeatThresholdDialogListener
     * @param alertThresholdInMinutes
     * @param alertModeEnabled
     * @return
     */
    public static ConfigureTerminalHeartbeatThresholdDialogFragment showDialog(final AppCompatActivity activity,
                                                                               final OnConfigureTerminalHeartbeatThresholdDialogListener configureTerminalHeartbeatThresholdDialogListener,
                                                                               final int alertThresholdInMinutes,
                                                                               final boolean alertModeEnabled){
        final ConfigureTerminalHeartbeatThresholdDialogFragment dialogFragment = new ConfigureTerminalHeartbeatThresholdDialogFragment();
        dialogFragment.setStyle(DialogFragment.STYLE_NO_TITLE, R.style.CommonDialogFragmentTheme);

        final Bundle args = new Bundle();
        args.putInt(ALERT_THRESHOLD_IN_MINUTES, alertThresholdInMinutes);
        args.putBoolean(ALERT_MODE_ENABLED, alertModeEnabled);
        dialogFragment.setArguments(args);

        // Config Listener
        if(configureTerminalHeartbeatThresholdDialogListener != null)
            dialogFragment.setConfigureTerminalHeartbeatThresholdListener(configureTerminalHeartbeatThresholdDialogListener);
        dialogFragment.setCancelable(false);
        dialogFragment.show(activity.getSupportFragmentManager(), TAG);
        return dialogFragment;
    }

    public void setConfigureTerminalHeartbeatThresholdListener(OnConfigureTerminalHeartbeatThresholdDialogListener configureTerminalHeartbeatThresholdDialogListener) {
        this.configureTerminalHeartbeatThresholdDialogListener = configureTerminalHeartbeatThresholdDialogListener;
    }

    /**
     * On View Created
     * @param view
     * @param savedInstanceState
     */
    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);

        soundManager.playSound(ISoundManager.DIALOG_CONFIRM_SOUND);

        alertThresholdInMinutesStepperTouch.stepper.addStepCallback(new HeartbeatStepCallback());

        if(getArguments() != null) {

            int alertThresholdInMinutes = getArguments().getInt(ALERT_THRESHOLD_IN_MINUTES);
            alertThresholdInMinutesStepperTouch.stepper.setValue(alertThresholdInMinutes);

            boolean alertModeEnabled = getArguments().getBoolean(ALERT_MODE_ENABLED, false);
            alertModeEnabledSwitch.setChecked(alertModeEnabled, true);
        }

        alertModeEnabledSwitch.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {
                    if(isChecked) {
                        alertThresholdInMinutesStepperTouch.setVisibility(View.VISIBLE);
                        alertThresholdInMinutesTextView.setVisibility(View.VISIBLE);
                        alertThresholdInMinutesTextView.setText(String.format(
                                Locale.getDefault(),
                                getString(R.string.terminal_heartbeat_alert_threshold_in_minutes),
                                alertThresholdInMinutesStepperTouch.stepper.getValue()
                        ));
                        alertModeDisabledTextView.setVisibility(View.GONE);
                    } else {
                        alertModeDisabledTextView.setVisibility(View.VISIBLE);
                        alertThresholdInMinutesTextView.setVisibility(View.GONE);
                        alertThresholdInMinutesStepperTouch.setVisibility(View.INVISIBLE);
                        alertThresholdInMinutesStepperTouch.getStepper().setValue(MIN_HEART_BEAT_VALUE);
                    }
            }
        });

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
        if (configureTerminalHeartbeatThresholdDialogListener != null) {
            int alertThresholdInMinutes = alertThresholdInMinutesStepperTouch.stepper.getValue();
            boolean isAlertModeEnabled = alertModeEnabledSwitch.isChecked();
            configureTerminalHeartbeatThresholdDialogListener.onSave(this,
                    alertThresholdInMinutes, isAlertModeEnabled);
        }
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
        void onSave(final DialogFragment dialog, final int alertThresholdInMinutes, final boolean isAlertModeEnabled);


        /**
         * On Cancel
         */
        void onCancel(final DialogFragment dialog);
    }


    /**
     * Heartbeat Step CallBack
     */
    private class HeartbeatStepCallback implements OnStepCallback {


        /**
         * on Step
         * @param value
         * @param positive
         */
        @Override
        public void onStep(int value, boolean positive) {

            if (value < MIN_HEART_BEAT_VALUE || value > MAX_HEART_BEAT_VALUE) {
                value = value < MIN_HEART_BEAT_VALUE ? MIN_HEART_BEAT_VALUE : MAX_HEART_BEAT_VALUE;
                alertThresholdInMinutesStepperTouch.stepper.setValue(value);
            } else {

                alertThresholdInMinutesTextView.setText(String.format(
                        Locale.getDefault(),
                        getString(R.string.terminal_heartbeat_alert_threshold_in_minutes),
                        alertThresholdInMinutesStepperTouch.stepper.getValue()
                ));
            }
        }
    }
}
