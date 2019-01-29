package sanchez.sanchez.sergio.bullkeeper.ui.adapter.impl;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Locale;
import sanchez.sanchez.sergio.bullkeeper.R;
import sanchez.sanchez.sergio.bullkeeper.ui.adapter.SupportRecyclerViewAdapter;
import sanchez.sanchez.sergio.domain.models.PhoneNumberBlockedEntity;

/**
 * Phone Numbers Blocked Adapter
 */
public final class PhoneNumbersBlockedAdapter
        extends SupportRecyclerViewAdapter<PhoneNumberBlockedEntity> {


    /**
     * Invitations Adapter
     * @param context
     * @param data
     */
    public PhoneNumbersBlockedAdapter(final Context context,
                                      final ArrayList<PhoneNumberBlockedEntity> data) {
        super(context, data);
        // enable header
        hasHeader = false;
        hasFooter = false;
    }

    /**
     * On Create Item View Holder
     * @param viewGroup
     * @return
     */
    @Override
    protected RecyclerView.ViewHolder onCreateItemViewHolder(ViewGroup viewGroup) {
        View view = inflater.inflate(R.layout.phone_number_blocked_item_layout, viewGroup, false);
        return new PhoneNumberBlockedViewHolder(context, view);
    }


    /**
     * Phone Number Blocked View Holder
     */
    public final class PhoneNumberBlockedViewHolder extends
            SupportItemSwipedViewHolder<PhoneNumberBlockedEntity> {

        private Context context;
        private TextView phoneNumberTextView, blockedAtTextView;

        /**
         * Alerts View Holder
         * @param context
         * @param itemView
         */
        public PhoneNumberBlockedViewHolder(final Context context, final View itemView) {
            super(itemView);
            this.context = context;
            this.phoneNumberTextView = itemView.findViewById(R.id.phoneNumberTextView);
            this.blockedAtTextView = itemView.findViewById(R.id.blockedAtTextView);
        }

        /**
         * On Bind
         * @param phoneNumberBlockedEntity
         */
        @Override
        public void bind(PhoneNumberBlockedEntity phoneNumberBlockedEntity) {
            super.bind(phoneNumberBlockedEntity);


            // Set Phone Number
            phoneNumberTextView.setText(phoneNumberBlockedEntity.getPhoneNumber());

            final SimpleDateFormat sdf = new SimpleDateFormat(context.getString(R.string.date_format),
                    Locale.getDefault());

            //set Blocked at
            blockedAtTextView.setText(String.format(Locale.getDefault(),
                    context.getString(R.string.phone_numbers_blocked_at),
                    sdf.format(phoneNumberBlockedEntity.getBlockedAt())));

        }

    }
}
