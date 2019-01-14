package sanchez.sanchez.sergio.bullkeeper.ui.adapter.impl;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.view.ViewGroup;
import android.widget.CompoundButton;
import android.widget.TextView;
import java.util.ArrayList;
import sanchez.sanchez.sergio.bullkeeper.R;
import sanchez.sanchez.sergio.bullkeeper.core.ui.components.SupportSwitchCompat;
import sanchez.sanchez.sergio.bullkeeper.ui.adapter.SupportRecyclerViewAdapter;
import sanchez.sanchez.sergio.domain.models.DayScheduledEntity;

/**
 * Fun Time Day Scheduled Adapter
 */
public final class FunTimeDayScheduledAdapter
        extends SupportRecyclerViewAdapter<DayScheduledEntity> {

    private onFunTimeDayScheduledChangedListener listener;

    /**
     * @param context
     * @param data
     */
    public FunTimeDayScheduledAdapter(final Context context, final ArrayList<DayScheduledEntity> data) {
        super(context, data);
    }

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
         * On Day Scheduled Changed
         * @param dayScheduledEntity
         */
        void onDayScheduledChanged(final DayScheduledEntity dayScheduledEntity);

    }

    /**
     * Fun Time Day Scheduled View Holder
     */
    public class FunTimeDayScheduledViewHolder
            extends SupportItemSwipedViewHolder<DayScheduledEntity>{

        /**
         * Day Of Week Name
         */
        private TextView dayOfWeekNameTextView;

        /**
         * Day Enabled Switch
         */
        private SupportSwitchCompat dayEnabledSwitch;

        /**
         *
         * @param itemView
         */
        FunTimeDayScheduledViewHolder(View itemView) {
            super(itemView);
            this.dayOfWeekNameTextView = itemView.findViewById(R.id.dayOfWeekName);
            this.dayEnabledSwitch = itemView.findViewById(R.id.dayEnabledSwitch);
        }

        /**
         * On Bind
         * @param dayScheduledEntity
         */
        @Override
        public void bind(final DayScheduledEntity dayScheduledEntity) {
            super.bind(dayScheduledEntity);
            // Set Day Name
            dayOfWeekNameTextView.setText(dayScheduledEntity.getDay());
            // Day Scheduled Status
            dayEnabledSwitch.setChecked(dayScheduledEntity.getEnabled(), false);
            dayEnabledSwitch.setEnabled(dayScheduledEntity.isEditable());
            if(dayScheduledEntity.isEditable())
                dayEnabledSwitch.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
                    @Override
                    public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {
                        dayScheduledEntity.setEnabled(isChecked);
                        if(listener != null)
                            listener.onDayScheduledChanged(dayScheduledEntity);
                    }
                });

        }

    }
}
