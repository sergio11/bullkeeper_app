package sanchez.sanchez.sergio.bullkeeper.ui.adapter.impl;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;
import java.util.ArrayList;
import sanchez.sanchez.sergio.bullkeeper.R;
import sanchez.sanchez.sergio.bullkeeper.ui.adapter.SupportRecyclerViewAdapter;
import sanchez.sanchez.sergio.domain.models.ScreenTimeAllowanceEntity;

/**
 * Time Allowance Per Day Adapter
 */
public final class TimeAllowancePerDayAdapter
        extends SupportRecyclerViewAdapter<ScreenTimeAllowanceEntity.TimeAllowancePerDayEntity> {

    /**
     * @param context
     * @param data
     */
    public TimeAllowancePerDayAdapter(final Context context, final ArrayList<ScreenTimeAllowanceEntity.TimeAllowancePerDayEntity> data) {
        super(context, data);
    }

    /**
     * On Create Item View Holder
     * @param viewGroup
     * @return
     */
    @Override
    protected RecyclerView.ViewHolder onCreateItemViewHolder(ViewGroup viewGroup) {
        View view = inflater.inflate(R.layout.time_allowance_per_day_item_layout, viewGroup, false);
        return new TimeAllowanceViewHolder(view);
    }


    /**
     * Time Allowance View Holder
     */
    public class TimeAllowanceViewHolder
            extends SupportItemSwipedViewHolder<ScreenTimeAllowanceEntity.TimeAllowancePerDayEntity>{

        private TextView dayOfWeekNameTextView;

        /**
         *
         * @param itemView
         */
        TimeAllowanceViewHolder(View itemView) {
            super(itemView);
            this.dayOfWeekNameTextView = itemView.findViewById(R.id.dayOfWeekName);
        }

        /**
         * On Bind
         * @param timeAllowancePerDayEntity
         */
        @Override
        public void bind(final ScreenTimeAllowanceEntity.TimeAllowancePerDayEntity timeAllowancePerDayEntity) {
            super.bind(timeAllowancePerDayEntity);

            dayOfWeekNameTextView.setText(timeAllowancePerDayEntity.getDayName());
        }

    }
}
