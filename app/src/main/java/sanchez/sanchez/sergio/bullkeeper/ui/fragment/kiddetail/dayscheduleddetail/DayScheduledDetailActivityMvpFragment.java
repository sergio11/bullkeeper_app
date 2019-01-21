package sanchez.sanchez.sergio.bullkeeper.ui.fragment.kiddetail.dayscheduleddetail;

import android.content.Context;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v4.app.DialogFragment;
import android.view.View;
import android.widget.CompoundButton;
import android.widget.TextView;
import com.fernandocejas.arrow.checks.Preconditions;
import java.util.Locale;
import javax.inject.Inject;
import butterknife.BindView;
import butterknife.OnClick;
import icepick.State;
import nl.dionsegijn.steppertouch.OnStepCallback;
import nl.dionsegijn.steppertouch.StepperTouch;
import sanchez.sanchez.sergio.bullkeeper.R;
import sanchez.sanchez.sergio.bullkeeper.core.ui.SupportMvpFragment;
import sanchez.sanchez.sergio.bullkeeper.core.ui.components.SupportSwitchCompat;
import sanchez.sanchez.sergio.bullkeeper.di.components.FunTimeComponent;
import sanchez.sanchez.sergio.bullkeeper.ui.activity.dayscheduleddetail.IDayScheduledActivityHandler;
import sanchez.sanchez.sergio.bullkeeper.ui.dialog.NoticeDialogFragment;
import sanchez.sanchez.sergio.domain.models.DayScheduledEntity;

import static sanchez.sanchez.sergio.bullkeeper.core.ui.SupportToolbarApp.RETURN_TOOLBAR;

/**
 * Day Scheduled Activity Fragment
 */
public class DayScheduledDetailActivityMvpFragment extends SupportMvpFragment<DayScheduledDetailFragmentPresenter,
        IDayScheduledDetailView, IDayScheduledActivityHandler, FunTimeComponent>
        implements IDayScheduledDetailView {

    /**
     * Args
     */
    public static String TERMINAL_ID_ARG = "TERMINAL_ID_ARG";
    public static String KID_ID_ARG = "KID_ID_ARG";
    public static String DAY_SCHEDULED_ARG = "DAY_SCHEDULED_ARG";
    public static String FUN_TIME_ENABLED_ARG = "FUN_TIME_ENABLED_ARG";

    /**
     * Views
     * =============
     */

    /**
     * Day Of Week Name Text View
     */
    @BindView(R.id.dayOfWeekName)
    protected TextView dayOfWeekNameTextView;

    /**
     * Day Enabled Switch
     */
    @BindView(R.id.dayEnabledSwitch)
    protected SupportSwitchCompat dayEnabledSwitch;

    /**
     * Total Hours Stepper Touch
     */
    @BindView(R.id.totalHoursStepperTouch)
    protected StepperTouch totalHoursStepperTouch;

    /**
     * Total Hours Configured Text View
     */
    @BindView(R.id.totalHoursConfigured)
    protected TextView totalHoursConfiguredTextView;

    /**
     * Day Scheduled Disabled Text View
     */
    @BindView(R.id.dayScheduledDisabled)
    protected TextView dayScheduledDisabledTextView;

    /**
     * Dependencies
     * ===============
     */


    /**
     * App Context
     */
    @Inject
    protected Context appContext;


    /**
     * State
     * =============
     */


    /**
     * Kid
     */
    @State
    protected String kid;

    /**
     * Terminal Id
     */
    @State
    protected String terminal;

    /**
     * Day
     */
    @State
    protected String day;

    /**
     * Fun Time Enabled
     */
    @State
    protected Boolean funTimeEnabled;

    /**
     * Day Scheduled Step CallBack
     */
    private DayScheduledStepCallback dayScheduledStepCallback = new DayScheduledStepCallback();

    public DayScheduledDetailActivityMvpFragment() { }

    /**
     * New Instance
     * @param terminal
     * @param kid
     * @param day
     * @return
     */
    public static DayScheduledDetailActivityMvpFragment newInstance(
            final String terminal, final String kid, final String day, final Boolean isFunTimeEnabled) {
        final DayScheduledDetailActivityMvpFragment fragment =
                new DayScheduledDetailActivityMvpFragment();
        final Bundle args = new Bundle();
        args.putString(TERMINAL_ID_ARG, terminal);
        args.putString(KID_ID_ARG, kid);
        args.putString(DAY_SCHEDULED_ARG, day);
        args.putBoolean(FUN_TIME_ENABLED_ARG, isFunTimeEnabled);
        fragment.setArguments(args);
        return fragment;
    }

    /**
     * Get Args
     * @return
     */
    @Override
    public Bundle getArgs() {
        return getArguments();
    }

    /**
     * On View Created
     * @param view
     * @param savedInstanceState
     */
    @Override
    public void onViewCreated(View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);

        // Get Child Id
        if(!getArgs().containsKey(KID_ID_ARG) ||
                !appUtils.isValidString(getArgs().getString(KID_ID_ARG)))
            throw new IllegalArgumentException("You must provide a child id");

        kid = getArgs().getString(KID_ID_ARG);

        // Get Terminal Id
        if(!getArgs().containsKey(TERMINAL_ID_ARG) ||
                !appUtils.isValidString(getArgs().getString(TERMINAL_ID_ARG)))
            throw new IllegalStateException("You must provide a terminal id");

        terminal = getArgs().getString(TERMINAL_ID_ARG);

        // Get Day
        if(!getArgs().containsKey(DAY_SCHEDULED_ARG) ||
                !appUtils.isValidString(getArgs().getString(DAY_SCHEDULED_ARG)))
            throw new IllegalStateException("You must provide a day");

        day = getArgs().getString(DAY_SCHEDULED_ARG);

        // Is Fun Time Enabled
        if(!getArgs().containsKey(FUN_TIME_ENABLED_ARG))
            throw new IllegalStateException("You must provide a Fun Time Status");

        funTimeEnabled = getArgs().getBoolean(FUN_TIME_ENABLED_ARG);

    }

    /**
     * Get Layout Resource
     * @return
     */
    @Override
    protected int getLayoutRes() {
        return R.layout.fragment_day_scheduled_detail;
    }

    /**
     * Initialize Injector
     */
    @Override
    protected void initializeInjector(final FunTimeComponent component) {
        component.inject(this);
    }


    /**
     * Provide Presenter
     * @return
     */
    @NonNull
    @Override
    public DayScheduledDetailFragmentPresenter providePresenter() {
        return component.dayScheduledDetailFragmentPresenter();
    }


    /**
     * Get Toolbar Type
     * @return
     */
    @Override
    protected int getToolbarType() {
        return RETURN_TOOLBAR;
    }

    /**
     * On Network Error
     */
    @Override
    public void onNetworkError() {
        activityHandler.showNoticeDialog(R.string.network_error_ocurred, new NoticeDialogFragment.NoticeDialogListener() {
            @Override
            public void onAccepted(DialogFragment dialog) {
                activityHandler.closeActivity();
            }
        });
    }

    /**
     * On Other Exception
     */
    @Override
    public void onOtherException() {
        activityHandler.showNoticeDialog(R.string.unexpected_error_ocurred, new NoticeDialogFragment.NoticeDialogListener() {
            @Override
            public void onAccepted(DialogFragment dialog) {
                activityHandler.closeActivity();
            }
        });
    }

    /**
     * Switch Total Hours
     *
     * @param dayScheduledEntity
     */
    private void switchTotalHours(final DayScheduledEntity dayScheduledEntity) {
        if (dayScheduledEntity.getEnabled() && dayScheduledEntity.isEditable()) {
            totalHoursStepperTouch.setVisibility(View.VISIBLE);
            totalHoursConfiguredTextView.setVisibility(View.VISIBLE);
            dayScheduledDisabledTextView.setVisibility(View.GONE);
            totalHoursStepperTouch.enableSideTap(true);
            totalHoursStepperTouch.stepper.setValue(dayScheduledEntity.getTotalHours());
            totalHoursStepperTouch.stepper.addStepCallback(dayScheduledStepCallback);
        } else {
            totalHoursStepperTouch.setVisibility(View.GONE);
            totalHoursConfiguredTextView.setVisibility(View.GONE);
            dayScheduledDisabledTextView.setVisibility(View.VISIBLE);
            totalHoursStepperTouch.stepper.removeStepCallback(dayScheduledStepCallback);
            totalHoursStepperTouch.stepper.setValue(0);
            totalHoursConfiguredTextView.setText(String.format(
                    Locale.getDefault(),
                    getString(R.string.fun_time_total_hours_configured),
                    0
            ));
        }
    }

    /**
     * On Day Scheduled Loaded
     * @param dayScheduledEntity
     */
    @Override
    public void onDayScheduledLoaded(final DayScheduledEntity dayScheduledEntity) {
        Preconditions.checkNotNull(dayScheduledEntity, "Day Scheduled can not be null");

        dayScheduledEntity.setEditable(funTimeEnabled);
        // Set Day Name
        dayOfWeekNameTextView.setText(dayScheduledEntity.getDay());
        // Day Scheduled Status
        dayEnabledSwitch.setChecked(dayScheduledEntity.getEnabled(), false);
        dayEnabledSwitch.setEnabled(dayScheduledEntity.isEditable());

        if (dayScheduledEntity.getEnabled() && dayScheduledEntity.isEditable()) {

            totalHoursConfiguredTextView.setText(String.format(
                    Locale.getDefault(),
                    getString(R.string.fun_time_total_hours_configured),
                    dayScheduledEntity.getTotalHours()
            ));

            dayEnabledSwitch.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
                @Override
                public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {
                    dayScheduledEntity.setEnabled(isChecked);
                    dayScheduledEntity.setTotalHours(0);
                    switchTotalHours(dayScheduledEntity);
                }
            });

        }

        switchTotalHours(dayScheduledEntity);

    }


    /**
     * On Save Changes Clicked
     */
    @OnClick(R.id.saveChanges)
    protected void onSaveChangesClicked(){

        if(funTimeEnabled)
            // Save Day Scheduled
            getPresenter().saveDayScheduled(kid, terminal, day,
                    dayEnabledSwitch.isChecked(),
                    totalHoursStepperTouch.stepper.getValue());

    }

    /**
     * Day Scheduled Step CallBack
     */
    private class DayScheduledStepCallback implements OnStepCallback {

        /**
         * Min Fun Time
         */
        private final int MIN_FUN_TIME_VALUE = 0;

        /**
         * Max Fun Time Value
         */
        private final int MAX_FUN_TIME_VALUE = 8;


        @Override
        public void onStep(int value, boolean positive) {

            if (value < MIN_FUN_TIME_VALUE || value > MAX_FUN_TIME_VALUE) {
                value = value < MIN_FUN_TIME_VALUE ? MIN_FUN_TIME_VALUE : MAX_FUN_TIME_VALUE;
                totalHoursStepperTouch.stepper.setValue(value);
            } else {
                totalHoursConfiguredTextView.setText(String.format(
                        Locale.getDefault(),
                        getString(R.string.fun_time_total_hours_configured),
                        value
                ));
            }
        }
    }
}
