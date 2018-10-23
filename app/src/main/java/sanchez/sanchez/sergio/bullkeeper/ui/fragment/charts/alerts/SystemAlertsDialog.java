package sanchez.sanchez.sergio.bullkeeper.ui.fragment.charts.alerts;

import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v4.app.DialogFragment;
import android.support.v4.content.ContextCompat;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;

import javax.inject.Inject;

import butterknife.BindView;
import butterknife.OnClick;
import sanchez.sanchez.sergio.bullkeeper.R;
import sanchez.sanchez.sergio.bullkeeper.core.ui.SupportDialogFragment;
import sanchez.sanchez.sergio.bullkeeper.navigation.INavigator;
import sanchez.sanchez.sergio.domain.models.AlertLevelEnum;

/**
 * System Alerts Dialog
 */
public final class SystemAlertsDialog extends SupportDialogFragment {

    public static final String TAG = "SYSTEM_ALERTS_DIALOG";

    private static final String ALERT_LEVEL_ARG = "ALERT_LEVEL_ARG";
    private static final String ALERT_LEVEL_VALUE_ARG = "ALERT_LEVEL_VALUE_ARG";
    private static final String KID_IDENTITY_VALUE_ARG = "KID_IDENTITY_VALUE_ARG";

    /**
     * State
     * ==========
     */

    /**
     * Alert Level Enum
     */
    private AlertLevelEnum alertLevelEnum;

    /**
     * Alert Level Value
     */
    private String alertLevelValue;

    /**
     * Kid Identity Value
     */
    private String kidIdentityValue;

    /**
     * Views
     * ==============
     */

    /**
     * Alert Level Title Text View
     */
    @BindView(R.id.alertLevelTitle)
    protected TextView alertLevelTitleTextView;

    /**
     * Alert Level Image View
     */
    @BindView(R.id.alertLevelImage)
    protected ImageView alertLevelImageView;

    /**
     * Separator
     */
    @BindView(R.id.separator)
    protected View separator;

    /**
     * Close Dialog
     */
    @BindView(R.id.closeDialog)
    protected Button closeDialog;

    /**
     * Content Detail Text View
     */
    @BindView(R.id.contentDetailText)
    protected TextView contentDetailTextView;

    /**
     * Show Alerts Text View
     */
    @BindView(R.id.showAlertsTextView)
    protected TextView showAlertsTextView;

    /**
     * Show Alerts Image View
     */
    @BindView(R.id.showAlertsImageView)
    protected ImageView showAlertsImageView;

    /**
     * Navigator
     */
    @Inject
    protected INavigator navigator;

    /**
     * Show Dialog
     * @param appCompatActivity
     */
    public static void show(final AppCompatActivity appCompatActivity, final AlertLevelEnum alertLevelEnum, final String alertLevelValue,
        final String kidIdentity) {
        final SystemAlertsDialog fourDimensionsDialogFragment = new SystemAlertsDialog();
        final Bundle args = new Bundle();
        args.putSerializable(ALERT_LEVEL_ARG, alertLevelEnum);
        args.putString(ALERT_LEVEL_VALUE_ARG, alertLevelValue);
        args.putString(KID_IDENTITY_VALUE_ARG, kidIdentity);
        fourDimensionsDialogFragment.setArguments(args);
        fourDimensionsDialogFragment.setStyle(DialogFragment.STYLE_NO_TITLE, R.style.CommonDialogFragmentTheme);
        fourDimensionsDialogFragment.show(appCompatActivity.getSupportFragmentManager(), TAG);
    }

    /**
     * Get Layout Resource
     * @return
     */
    @Override
    protected int getLayoutRes() {
        return R.layout.system_alerts_dialog_layout;
    }

    /**
     * Initialize Injector
     */
    @Override
    protected void initializeInjector() {
        getApplicationComponent().inject(this);
    }

    /**
     * On Close Dialog
     */
    @OnClick(R.id.closeDialog)
    protected void onCloseDialog(){
        this.dismiss();
    }

    /**
     * On View Created
     * @param view
     * @param savedInstanceState
     */
    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);

        final Bundle args = getArguments();

        if(args == null || !args.containsKey(ALERT_LEVEL_ARG) ||
                !args.containsKey(ALERT_LEVEL_VALUE_ARG) || !args.containsKey(KID_IDENTITY_VALUE_ARG))
            throw new IllegalArgumentException("You must provide the required arguments");

        // Alert Level Enum
        alertLevelEnum = (AlertLevelEnum) args.getSerializable(ALERT_LEVEL_ARG);
        // Alert Level Value
        alertLevelValue = args.getString(ALERT_LEVEL_VALUE_ARG);
        // Kid Identity Value
        kidIdentityValue = args.getString(KID_IDENTITY_VALUE_ARG);

        switch (alertLevelEnum) {

            case INFO:

                // Alerts Level Image
                alertLevelImageView.setImageResource(R.drawable.info_icon_solid);
                // Alert Level Title
                alertLevelTitleTextView.setText(R.string.information_alerts_title);
                alertLevelTitleTextView.setTextColor(ContextCompat.getColor(getContext() ,
                        R.color.cyanBrilliant));

                // Separator
                separator.setBackgroundColor(ContextCompat.getColor(getContext(),
                        R.color.cyanBrilliant));

                // Close Dialog Button
                closeDialog.setBackground(ContextCompat.getDrawable(getContext(),
                        R.drawable.info_alerts_button_state));

                contentDetailTextView.setText(String.format(getString(R.string.kid_results_info_alerts),
                        alertLevelValue));

                showAlertsTextView.setTextColor(ContextCompat.getColor(getContext() ,
                        R.color.cyanBrilliant));
                showAlertsImageView.setImageResource(R.drawable.info_alerts_right_solid);

                break;

            case SUCCESS:

                // Alerts Level Image
                alertLevelImageView.setImageResource(R.drawable.success_icon_solid);
                // Alert Level Title
                alertLevelTitleTextView.setText(R.string.success_alerts_title);
                alertLevelTitleTextView.setTextColor(ContextCompat.getColor(getContext() ,
                        R.color.greenSuccess));

                // Separator
                separator.setBackgroundColor(ContextCompat.getColor(getContext(),
                        R.color.greenSuccess));

                // Close Dialog Button
                closeDialog.setBackground(ContextCompat.getDrawable(getContext(),
                        R.drawable.success_alerts_button_state));

                contentDetailTextView.setText(String.format(getString(R.string.kid_results_success_alerts),
                        alertLevelValue));

                showAlertsTextView.setTextColor(ContextCompat.getColor(getContext() ,
                        R.color.greenSuccess));
                showAlertsImageView.setImageResource(R.drawable.success_alerts_right_solid);
                break;

            case DANGER:

                // Alerts Level Image
                alertLevelImageView.setImageResource(R.drawable.danger_icon_solid);
                // Alert Level Title
                alertLevelTitleTextView.setText(R.string.danger_alerts_title);
                alertLevelTitleTextView.setTextColor(ContextCompat.getColor(getContext() ,
                        R.color.redDanger));

                // Separator
                separator.setBackgroundColor(ContextCompat.getColor(getContext(),
                        R.color.redDanger));

                // Close Dialog Button
                closeDialog.setBackground(ContextCompat.getDrawable(getContext(),
                        R.drawable.danger_alerts_button_state));

                contentDetailTextView.setText(String.format(getString(R.string.kid_results_danger_alerts),
                        alertLevelValue));

                showAlertsTextView.setTextColor(ContextCompat.getColor(getContext() ,
                        R.color.redDanger));
                showAlertsImageView.setImageResource(R.drawable.danger_alerts_right_solid);

                break;

            case WARNING:

                // Alerts Level Image
                alertLevelImageView.setImageResource(R.drawable.warning_icon_solid);
                // Alert Level Title
                alertLevelTitleTextView.setText(R.string.warning_alerts_title);
                alertLevelTitleTextView.setTextColor(ContextCompat.getColor(getContext() ,
                        R.color.yellowWarning));

                // Separator
                separator.setBackgroundColor(ContextCompat.getColor(getContext(),
                        R.color.yellowWarning));

                // Close Dialog Button
                closeDialog.setBackground(ContextCompat.getDrawable(getContext(),
                        R.drawable.warning_alerts_button_state));

                contentDetailTextView.setText(String.format(getString(R.string.kid_results_warning_alerts),
                        alertLevelValue));

                showAlertsTextView.setTextColor(ContextCompat.getColor(getContext() ,
                        R.color.yellowWarning));
                showAlertsImageView.setImageResource(R.drawable.warning_alerts_right_solid);

                break;
        }
    }

    /**
     * On Show ALerts
     */
    @OnClick(R.id.showAlerts)
    protected void onShowAlerts(){
        navigator.navigateToAlertList(alertLevelEnum, kidIdentityValue);
    }
}
