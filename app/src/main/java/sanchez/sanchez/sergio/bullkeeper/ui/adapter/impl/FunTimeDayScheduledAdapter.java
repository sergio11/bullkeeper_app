package sanchez.sanchez.sergio.bullkeeper.ui.adapter.impl;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.view.ViewGroup;
import android.widget.CompoundButton;
import android.widget.TextView;
import java.util.ArrayList;
import java.util.Locale;
import nl.dionsegijn.steppertouch.OnStepCallback;
import nl.dionsegijn.steppertouch.StepperTouch;
import sanchez.sanchez.sergio.bullkeeper.R;
import sanchez.sanchez.sergio.bullkeeper.core.ui.components.SupportSwitchCompat;
import sanchez.sanchez.sergio.bullkeeper.ui.adapter.SupportRecyclerViewAdapter;
import sanchez.sanchez.sergio.domain.models.DayScheduledEntity;
import timber.log.Timber;

/**
 * Fun Time Day Scheduled Adapter
 */
public final class FunTimeDayScheduledAdapter
        extends SupportRecyclerViewAdapter<DayScheduledEntity> {

    /**
     * Min Fun Time
     */
    private final int MIN_FUN_TIME_VALUE = 0;

    /**
     * Max Fun Time Value
     */
    private final int MAX_FUN_TIME_VALUE = 8;

    /**
     * Listener
     */
    private onFunTimeDayScheduledChangedListener listener;

    /**
     * @param context
     * @param data
     */
    public FunTimeDayScheduledAdapter(final Context context, final ArrayList<DayScheduledEntity> data) {
        super(context, data);
    }

    /**
     *
     * @param listener
     */
    public void setListener(onFunTimeDayScheduledChangedListener listener) {
        this.listener = listener;
    }

    /**
     * On Create Item View Holder
     * @param viewGroup
     * @return
     */
    @Override
    protected RecyclerView.ViewHolder onCreateItemViewHolder(ViewGroup viewGroup) {
        View view = inflater.inflate(R.layout.fun_time_day_scheduled_item_layout, viewGroup, false);
        return new FunTimeDayScheduledViewHolder(view);
    }

    /**
     * Switch Editable Status For All Days Of Week
     * @param isEditable
     */
    public void switchEditableStatusForAllDaysOfWeek(boolean isEditable){
        for (final DayScheduledEntity dayScheduledEntity: data) {
            dayScheduledEntity.setEditable(isEditable);
        }
        notifyDataSetChanged();

    }


    /**
     * On Fun Time Day Scheduled Changed Listener
     */
    public interface onFunTimeDayScheduledChangedListener {

        /**
         * On Day Scheduled Status Changed
         * @param day
         * @param newEnabledValue
         * @param oldEnabledValue
         */
        void onDayScheduledStatusChanged(final String day, final boolean newEnabledValue,
                                         final boolean oldEnabledValue);

        /**
         * On Day Scheduled Status Changed
         * @param day
         * @param newTotalHoursValue
         * @param oldTotalHoursValue
         */
        void onDayScheduledTotalHoursChanged(final String day, final int newTotalHoursValue,
                                         final int oldTotalHoursValue);

    }

    /**
     * Fun Time Day Scheduled View Holder
     */
    public class FunTimeDayScheduledViewHolder
            extends SupportItemSwipedViewHolder<DayScheduledEntity> {

        /**
         * Day Of Week Name
         */
        private TextView dayOfWeekNameTextView, totalHoursConfiguredTextView,
                dayScheduledDisabledTextView;

        /**
         * Day Enabled Switch
         */
        private SupportSwitchCompat dayEnabledSwitch;

        /**
         * Total Hours Stepper
         */
        private StepperTouch totalHoursStepperTouch;

        /**
         * Day Scheduled Step Callback
         */
        private DayScheduledStepCallback dayScheduledStepCallback = new DayScheduledStepCallback();


        /**
         * @param itemView
         */
        FunTimeDayScheduledViewHolder(View itemView) {
            super(itemView);
            this.dayOfWeekNameTextView = itemView.findViewById(R.id.dayOfWeekName);
            this.dayEnabledSwitch = itemView.findViewById(R.id.dayEnabledSwitch);
            this.totalHoursStepperTouch = itemView.findViewById(R.id.totalHoursStepperTouch);
            this.totalHoursConfiguredTextView = itemView.findViewById(R.id.totalHoursConfigured);
            this.dayScheduledDisabledTextView = itemView.findViewById(R.id.dayScheduledDisabled);
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
            }
        }

        /**
         * On Bind
         *
         * @param dayScheduledEntity
         */
        @Override
        public void bind(final DayScheduledEntity dayScheduledEntity) {
            super.bind(dayScheduledEntity);

            Timber.d("FunTimeAdapter: Set Total Hours -> %d for Day -> %s",
                    dayScheduledEntity.getTotalHours(), dayScheduledEntity.getDay());


            // Set Day Name
            dayOfWeekNameTextView.setText(dayScheduledEntity.getDay());
            // Day Scheduled Status
            dayEnabledSwitch.setChecked(dayScheduledEntity.getEnabled(), false);
            dayEnabledSwitch.setEnabled(dayScheduledEntity.isEditable());

            if (dayScheduledEntity.isEditable()) {

                dayScheduledStepCallback.setDayScheduledEntity(dayScheduledEntity);

                totalHoursConfiguredTextView.setText(String.format(
                        Locale.getDefault(),
                        context.getString(R.string.fun_time_total_hours_configured),
                        dayScheduledEntity.getTotalHours()
                ));

                dayEnabledSwitch.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
                    @Override
                    public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {
                        dayScheduledEntity.setEnabled(isChecked);
                        switchTotalHours(dayScheduledEntity);
                        if (listener != null) {
                            listener.onDayScheduledStatusChanged(
                                    dayScheduledEntity.getDay(),
                                    isChecked, !isChecked);
                            listener.onDayScheduledTotalHoursChanged(
                                    dayScheduledEntity.getDay(),
                                    0, dayScheduledEntity.getTotalHours()
                            );
                        }
                        dayScheduledEntity.setTotalHours(0);
                    }
                });

            } else {
                dayScheduledStepCallback.setDayScheduledEntity(null);
            }

            switchTotalHours(dayScheduledEntity);


        }


        /**
         * Day Scheduled Step CallBack
         */
        private class DayScheduledStepCallback implements OnStepCallback {

            /**
             *
             */
            private DayScheduledEntity dayScheduledEntity;

            public void setDayScheduledEntity(DayScheduledEntity dayScheduledEntity) {
                this.dayScheduledEntity = dayScheduledEntity;
            }

            @Override
            public void onStep(int value, boolean positive) {


                Timber.d("Value -> $d", value);

                if (dayScheduledEntity != null) {

                    if (value < MIN_FUN_TIME_VALUE || value > MAX_FUN_TIME_VALUE) {
                        value = value < MIN_FUN_TIME_VALUE ? MIN_FUN_TIME_VALUE : MAX_FUN_TIME_VALUE;
                        totalHoursStepperTouch.stepper.setValue(value);
                    } else {

                        totalHoursConfiguredTextView.setText(String.format(
                                Locale.getDefault(),
                                context.getString(R.string.fun_time_total_hours_configured),
                                value
                        ));


                        if (value != dayScheduledEntity.getTotalHours()) {

                            if (listener != null)
                                listener.onDayScheduledTotalHoursChanged(
                                        dayScheduledEntity.getDay(),
                                        value, dayScheduledEntity.getTotalHours()
                                );
                        }

                        dayScheduledEntity.setTotalHours(value);

                    }
                }
            }
        }
    }
}
