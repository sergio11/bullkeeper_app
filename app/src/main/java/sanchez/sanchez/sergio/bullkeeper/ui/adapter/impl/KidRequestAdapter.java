package sanchez.sanchez.sergio.bullkeeper.ui.adapter.impl;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;
import java.util.ArrayList;
import sanchez.sanchez.sergio.bullkeeper.R;
import sanchez.sanchez.sergio.bullkeeper.ui.adapter.SupportRecyclerViewAdapter;
import sanchez.sanchez.sergio.domain.models.KidRequestEntity;

/**
 * Kid Request Adapter
 */
public final class KidRequestAdapter
        extends SupportRecyclerViewAdapter<KidRequestEntity> {


    /**
     * Invitations Adapter
     * @param context
     * @param data
     */
    public KidRequestAdapter(final Context context,
                             final ArrayList<KidRequestEntity> data) {
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
        View view = inflater.inflate(R.layout.kid_request_item_layout, viewGroup, false);
        return new KidRequestViewHolder(context, view);
    }


    /**
     * Kid Request View Holder
     */
    public final class KidRequestViewHolder extends
            SupportItemSwipedViewHolder<KidRequestEntity> {

        private Context context;
        private TextView phoneNumberTextView, blockedAtTextView;

        /**
         * Kid Request View Holder
         * @param context
         * @param itemView
         */
        public KidRequestViewHolder(final Context context, final View itemView) {
            super(itemView);
            this.context = context;
        }

        /**
         * On Bind
         * @param kidRequestEntity
         */
        @Override
        public void bind(KidRequestEntity kidRequestEntity) {
            super.bind(kidRequestEntity);


        }

    }
}
