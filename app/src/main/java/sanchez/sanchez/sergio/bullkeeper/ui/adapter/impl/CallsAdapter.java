package sanchez.sanchez.sergio.bullkeeper.ui.adapter.impl;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Locale;
import sanchez.sanchez.sergio.bullkeeper.R;
import sanchez.sanchez.sergio.bullkeeper.ui.adapter.SupportRecyclerViewAdapter;
import sanchez.sanchez.sergio.domain.models.CallDetailEntity;

/**
 * Calls Adapter
 */
public final class CallsAdapter extends SupportRecyclerViewAdapter<CallDetailEntity> {

    /**
     * @param context
     * @param data
     */
    public CallsAdapter(final Context context, final ArrayList<CallDetailEntity> data) {
        super(context, data);
    }

    /**
     * On Create Item View Holder
     * @param viewGroup
     * @return
     */
    @Override
    protected RecyclerView.ViewHolder onCreateItemViewHolder(ViewGroup viewGroup) {
        View view = inflater.inflate(R.layout.call_item_layout, viewGroup, false);
        return new CallDetailViewHolder(view);
    }

    /**
     * Call Detail View Holder
     */
    public class CallDetailViewHolder
            extends SupportItemSwipedViewHolder<CallDetailEntity>{

        // Call Detail
        private ImageView callDetailTypeImageView, phoneNumberBlockedImageView;
        private TextView phoneNumberTextView, callDurationTextView, callDateTextView;


        /**
         * @param itemView
         */
        CallDetailViewHolder(final View itemView) {
            super(itemView);
            callDetailTypeImageView = itemView.findViewById(R.id.callDetailType);
            phoneNumberBlockedImageView = itemView.findViewById(R.id.phoneNumberBlocked);
            phoneNumberTextView = itemView.findViewById(R.id.phoneNumberText);
            callDurationTextView = itemView.findViewById(R.id.callDuration);
            callDateTextView = itemView.findViewById(R.id.callDate);
        }

        /**
         * On Bind
         * @param callDetailEntity
         */
        @Override
        public void bind(final CallDetailEntity callDetailEntity) {
            super.bind(callDetailEntity);

            // Set Call Type
            switch (callDetailEntity.getCallType()) {

                /**
                 * Incoming
                 */
                case INCOMING:
                    callDetailTypeImageView
                            .setImageResource(R.drawable.incoming_calls);
                    break;
                /**
                 * Missed
                 */
                case MISSED:
                    callDetailTypeImageView
                            .setImageResource(R.drawable.missed_call_phone_icon);
                    break;
                /**
                 * Outgoing
                  */
                case OUTGOING:
                    callDetailTypeImageView
                            .setImageResource(R.drawable.outgoing_call);
                    break;

                    default:
                        callDetailTypeImageView
                                .setImageResource(R.drawable.unknown_call);
            }

            // Set Phone Number
            phoneNumberTextView.setText(
                    String.format(Locale.getDefault(), context.getString(R.string.call_phone_number),
                            callDetailEntity.getPhoneNumber()));


            // Call Duration
            final long totalSecs = Long.valueOf(callDetailEntity.getCallDuration());
            final long minutes = (totalSecs % 3600) / 60;
            final long seconds = totalSecs % 60;
            callDurationTextView.setText(
                    String.format(Locale.getDefault(),
                            context.getString(R.string.call_duration), minutes, seconds));

            // Set Call Day Time
            final SimpleDateFormat simpleDateFormat = new SimpleDateFormat(
                    context.getString(R.string.date_time_format),
                    Locale.getDefault());
            // Call Day Time
            callDateTextView.setText(String.format(Locale.getDefault(),
                    context.getString(R.string.call_date_time),
                    simpleDateFormat.format(callDetailEntity.getCallDayTime())));

            phoneNumberBlockedImageView.setImageResource(
                    !callDetailEntity.isBlocked() ? R.drawable.success_icon_solid :
                            R.drawable.close_circle_solid
            );
        }

    }
}
