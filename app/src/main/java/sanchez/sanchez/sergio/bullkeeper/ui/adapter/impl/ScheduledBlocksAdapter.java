package sanchez.sanchez.sergio.bullkeeper.ui.adapter.impl;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.SwitchCompat;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;
import com.squareup.picasso.Picasso;
import org.joda.time.format.DateTimeFormat;
import org.joda.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.Locale;
import de.hdodenhof.circleimageview.CircleImageView;
import sanchez.sanchez.sergio.bullkeeper.R;
import sanchez.sanchez.sergio.bullkeeper.core.ui.components.SupportWeeklyFrequencyTextView;
import sanchez.sanchez.sergio.bullkeeper.ui.adapter.SupportRecyclerViewAdapter;
import sanchez.sanchez.sergio.domain.models.ScheduledBlockEntity;

/**
 * Scheduled Blocks Adapter
 */
public final class ScheduledBlocksAdapter extends SupportRecyclerViewAdapter<ScheduledBlockEntity> {

    private final Picasso picasso;

    /**
     *
     * @param context
     * @param data
     * @param picasso
     */
    public ScheduledBlocksAdapter(final Context context, final ArrayList<ScheduledBlockEntity> data, final Picasso picasso) {
        super(context, data);
        this.picasso = picasso;
    }

    /**
     * On Create Item View Holder
     * @param viewGroup
     * @return
     */
    @Override
    protected RecyclerView.ViewHolder onCreateItemViewHolder(ViewGroup viewGroup) {
        View view = inflater.inflate(R.layout.scheduled_blocks_item_layout, viewGroup, false);
        return new ScheduledBlocksViewHolder(view);
    }


    /**
     * Schedule Blocks View Holder
     */
    public class ScheduledBlocksViewHolder
            extends SupportRecyclerViewAdapter<ScheduledBlockEntity>.SupportItemSwipedViewHolder<ScheduledBlockEntity>{

        private final CircleImageView scheduledBlockImage;
        private final TextView scheduledBlockNameTextView, scheduledBlockTimeTextView;
        private final SupportWeeklyFrequencyTextView
                scheduledBlockWeeklyFrequencyTextView;
        private final SwitchCompat switchCompat;

        ScheduledBlocksViewHolder(View itemView) {
            super(itemView);
            this.scheduledBlockImage = itemView.findViewById(R.id.scheduledBlockImage);
            this.scheduledBlockNameTextView = itemView.findViewById(R.id.scheduledBlockName);
            this.scheduledBlockTimeTextView = itemView.findViewById(R.id.scheduledBlockTime);
            this.scheduledBlockWeeklyFrequencyTextView =
                    itemView.findViewById(R.id.scheduledBlockWeeklyFrequency);
            this.switchCompat = itemView.findViewById(R.id.switchWidget);
        }

        /**
         * On Bind
         * @param scheduledBlockEntity
         */
        @Override
        public void bind(ScheduledBlockEntity scheduledBlockEntity) {
            super.bind(scheduledBlockEntity);

            if(scheduledBlockEntity.getImage() != null &&
                    !scheduledBlockEntity.getImage().isEmpty()) {

                picasso.load(scheduledBlockEntity.getImage())
                        .placeholder(R.drawable.scheduled_block_default)
                        .error(R.drawable.scheduled_block_default)
                        .into(scheduledBlockImage);
            } else {
                scheduledBlockImage.setImageResource(R.drawable.scheduled_block_default);
            }

            // set Scheduled Block Title
            scheduledBlockNameTextView.setText(scheduledBlockEntity.getName());

            DateTimeFormatter fmt = DateTimeFormat.forPattern("hh:mm a");

            final String startAtFormatted = scheduledBlockEntity
                   .getStartAt().toString(fmt);
            final String endAtFormatted = scheduledBlockEntity.getEndAt()
                   .toString(fmt);

            // Set Scheduled Block Time
            scheduledBlockTimeTextView.setText(
                    String.format(Locale.getDefault(), "%s - %s",
                            startAtFormatted, endAtFormatted));

            // Weekly Frequency
            scheduledBlockWeeklyFrequencyTextView
                    .setDaysOfWeekSelected(scheduledBlockEntity.getWeeklyFrequency());

            // Is Enable
            switchCompat.setChecked(scheduledBlockEntity.isEnable());

        }

    }
}
