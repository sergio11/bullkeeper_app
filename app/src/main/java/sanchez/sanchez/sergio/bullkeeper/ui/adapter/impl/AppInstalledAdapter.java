package sanchez.sanchez.sergio.bullkeeper.ui.adapter.impl;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.view.ViewGroup;
import java.util.ArrayList;
import sanchez.sanchez.sergio.bullkeeper.R;
import sanchez.sanchez.sergio.bullkeeper.ui.adapter.SupportRecyclerViewAdapter;
import sanchez.sanchez.sergio.domain.models.AppInstalledEntity;

/**
 * App Installed Adapter
 */
public final class AppInstalledAdapter
        extends SupportRecyclerViewAdapter<AppInstalledEntity> {

    /**
     * @param context
     * @param data
     */
    public AppInstalledAdapter(final Context context, final ArrayList<AppInstalledEntity> data) {
        super(context, data);
    }

    /**
     * On Create Item View Holder
     * @param viewGroup
     * @return
     */
    @Override
    protected RecyclerView.ViewHolder onCreateItemViewHolder(ViewGroup viewGroup) {
        View view = inflater.inflate(R.layout.app_installed_item_layout, viewGroup, false);
        return new AppInstalledViewHolder(view);
    }


    /**
     * App Installed View Holder
     */
    public class AppInstalledViewHolder
            extends SupportItemSwipedViewHolder<AppInstalledEntity>{

        /**
         *
         * @param itemView
         */
        AppInstalledViewHolder(View itemView) {
            super(itemView);

        }


        /**
         * On Bind
         * @param appInstalledEntity
         */
        @Override
        public void bind(final AppInstalledEntity appInstalledEntity) {
            super.bind(appInstalledEntity);

        }

    }
}
