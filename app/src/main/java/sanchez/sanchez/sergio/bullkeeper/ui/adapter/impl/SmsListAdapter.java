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
import sanchez.sanchez.sergio.domain.models.SmsEntity;
import sanchez.sanchez.sergio.domain.models.SmsReadStateEnum;

/**
 * Sms List Adapter
 */
public final class SmsListAdapter extends SupportRecyclerViewAdapter<SmsEntity> {

    /**
     * @param context
     * @param data
     */
    public SmsListAdapter(final Context context, final ArrayList<SmsEntity> data) {
        super(context, data);
    }

    /**
     * On Create Item View Holder
     * @param viewGroup
     * @return
     */
    @Override
    protected RecyclerView.ViewHolder onCreateItemViewHolder(ViewGroup viewGroup) {
        View view = inflater.inflate(R.layout.sms_item_layout, viewGroup, false);
        return new SmsListViewHolder(view);
    }


    /**
     * Sms List View Holder
     */
    public class SmsListViewHolder
            extends SupportItemSwipedViewHolder<SmsEntity>{

        // Sms Folder Icon Image View
        private ImageView smsFolderIconImageView;
        private TextView addressTextView, smsDateTextView, smsReadStateTextView;

        /**
         *
         * @param itemView
         */
        SmsListViewHolder(View itemView) {
            super(itemView);
            smsFolderIconImageView = itemView.findViewById(R.id.smsFolderIcon);
            addressTextView = itemView.findViewById(R.id.addressText);
            smsDateTextView = itemView.findViewById(R.id.smsDate);
            smsReadStateTextView = itemView.findViewById(R.id.smsReadState);
        }

        /**
         * On Bind
         * @param smsEntity
         */
        @Override
        public void bind(final SmsEntity smsEntity) {
            super.bind(smsEntity);

            // Folder Name
            switch (smsEntity.getFolderName()) {
                case SENT:
                    smsFolderIconImageView.setImageResource(R.drawable.sms_sent_icon);
                    break;
                case INBOX:
                    smsFolderIconImageView.setImageResource(R.drawable.sms_inbox_icon);
                    break;
            }

            // Set Address
            addressTextView.setText(smsEntity.getAddress());

            // Set Sms Date
            final SimpleDateFormat  simpleDateFormat = new SimpleDateFormat(context.getString(R.string.date_time_format),
                    Locale.getDefault());

            smsDateTextView.setText(String.format(Locale.getDefault(),
                    context.getString(R.string.sms_date_time),
                    simpleDateFormat.format(smsEntity.getDate())));

            // Set Read State
            if(smsEntity.getReadState().equals(SmsReadStateEnum.VIEWED)) {
                smsReadStateTextView
                        .setText(context.getString(R.string.message_read));
            } else {
                smsReadStateTextView
                        .setText(context.getString(R.string.unread_message));
            }

        }

    }
}
