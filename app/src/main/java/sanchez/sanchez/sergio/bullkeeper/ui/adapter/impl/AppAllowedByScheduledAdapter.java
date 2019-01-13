package sanchez.sanchez.sergio.bullkeeper.ui.adapter.impl;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.view.ViewGroup;
import java.util.ArrayList;
import sanchez.sanchez.sergio.bullkeeper.R;
import sanchez.sanchez.sergio.bullkeeper.ui.adapter.SupportRecyclerViewAdapter;
import sanchez.sanchez.sergio.domain.models.AppAllowedByScheduledEntity;

/**
 * App Allowed By Scheduled Adapter
 */
public final class AppAllowedByScheduledAdapter extends SupportRecyclerViewAdapter<AppAllowedByScheduledEntity> {


    /**
     * @param context
     * @param data
     */
    public AppAllowedByScheduledAdapter(final Context context, final ArrayList<AppAllowedByScheduledEntity> data) {
        super(context, data);
    }

    /**
     * On Create Item View Holder
     * @param viewGroup
     * @return
     */
    @Override
    protected RecyclerView.ViewHolder onCreateItemViewHolder(ViewGroup viewGroup) {
        View view = inflater.inflate(R.layout.app_allowed_by_scheduled_item_layout, viewGroup, false);
        return new AppAllowedByScheduledViewHolder(view);
    }


    /**
     * App Allowed By Scheduled View Holder
     */
    public class AppAllowedByScheduledViewHolder
            extends SupportItemSwipedViewHolder<AppAllowedByScheduledEntity>{

        /**
         *
         * @param itemView
         */
        AppAllowedByScheduledViewHolder(View itemView) {
            super(itemView);
        }


        /**
         * On Bind
         * @param appAllowedByScheduledEntity
         */
        @Override
        public void bind(final AppAllowedByScheduledEntity appAllowedByScheduledEntity) {
            super.bind(appAllowedByScheduledEntity);

        }

    }
}
