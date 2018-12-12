package sanchez.sanchez.sergio.bullkeeper.ui.adapter.impl;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.view.ViewGroup;
import java.util.ArrayList;
import sanchez.sanchez.sergio.bullkeeper.R;
import sanchez.sanchez.sergio.bullkeeper.ui.adapter.SupportRecyclerViewAdapter;
import sanchez.sanchez.sergio.domain.models.SmsEntity;

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


        /**
         *
         * @param itemView
         */
        SmsListViewHolder(View itemView) {
            super(itemView);
        }

        /**
         * On Bind
         * @param smsEntity
         */
        @Override
        public void bind(final SmsEntity smsEntity) {
            super.bind(smsEntity);

        }

    }
}
