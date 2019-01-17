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
            extends SupportItemSwipedViewHolder<DayScheduledEntity>{

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
        private DayScheduledStepCallback dayScheduledStepCallback;


        /**
         *
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
         * @param status
         */
        private void switchTotalHours(final boolean status) {
            if(status) {
                totalHoursStepperTouch.setVisibility(View.VISIBLE);
                totalHoursConfiguredTextView.setVisibility(View.VISIBLE);
                dayScheduledDisabledTextView.setVisibility(View.GONE);
                totalHoursStepperTouch.enableSideTap(true);
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
         * @param dayScheduledEntity
         */
        @Override
        public void bind(final DayScheduledEntity dayScheduledEntity) {
            super.bind(dayScheduledEntity);

            dayScheduledStepCallback = new DayScheduledStepCallback(dayScheduledEntity);

            // Set Day Name
            dayOfWeekNameTextView.setText(dayScheduledEntity.getDay());
            // Day Scheduled Status
            dayEnabledSwitch.setChecked(dayScheduledEntity.getEnabled(), false);
            dayEnabledSwitch.setEnabled(dayScheduledEntity.isEditable());

            if(dayScheduledEntity.isEditable()) {

                totalHoursStepperTouch.stepper.setValue(dayScheduledEntity.getTotalHours());
                totalHoursConfiguredTextView.setText(String.format(
                        Locale.getDefault(),
                        context.getString(R.string.family_locator_total_hours_configured),
                        dayScheduledEntity.getTotalHours()
                ));

                dayEnabledSwitch.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
                    @Override
                    public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {
                        dayScheduledEntity.setEnabled(isChecked);
                        switchTotalHours(isChecked);
                        if (listener != null)
                            listener.onDayScheduledStatusChanged(
                                    dayScheduledEntity.getDay(),
                                    isChecked, !isChecked);
                    }
                });

                switchTotalHours(dayScheduledEntity.getEnabled());

            } else {

                switchTotalHours(false);
            }

        }


        /**
         * Day Scheduled Step CallBack
         */
        private class DayScheduledStepCallback implements OnStepCallback {

            /**
             *
             */
            private final DayScheduledEntity dayScheduledEntity;

            /**
             *
             * @param dayScheduledEntity
             */
            public DayScheduledStepCallback(final DayScheduledEntity dayScheduledEntity) {
                this.dayScheduledEntity = dayScheduledEntity;
            }

            @Override
            public void onStep(int value, boolean positive) {
                Timber.d("Value -> $d", value);

                if (value < MIN_FUN_TIME_VALUE) {
                    value = MIN_FUN_TIME_VALUE;
                    totalHoursStepperTouch.stepper.setValue(value);
                }

                if (value > 8) {
                    value = 8;
                    totalHoursStepperTouch.stepper.setValue(value);
                }

                totalHoursConfiguredTextView.setText(String.format(
                        Locale.getDefault(),
                        context.getString(R.string.family_locator_total_hours_configured),
                        value
                ));

                if(listener != null)
                    listener.onDayScheduledTotalHoursChanged(
                            dayScheduledEntity.getDay(),
                            value, dayScheduledEntity.getTotalHours()
                    );

                dayScheduledEntity.setTotalHours(value);
            }
        }

    }
}
