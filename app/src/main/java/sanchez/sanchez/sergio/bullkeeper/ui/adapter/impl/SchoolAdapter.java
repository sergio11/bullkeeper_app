package sanchez.sanchez.sergio.bullkeeper.ui.adapter.impl;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.view.ViewGroup;
import java.util.ArrayList;
import sanchez.sanchez.sergio.bullkeeper.R;
import sanchez.sanchez.sergio.bullkeeper.ui.adapter.SupportRecyclerViewAdapter;
import sanchez.sanchez.sergio.domain.models.SchoolEntity;

/**
 * School Adapter
 */
public final class SchoolAdapter extends SupportRecyclerViewAdapter<SchoolEntity>{

    /**
     *
     * @param context
     * @param data
     */
    public SchoolAdapter(Context context, ArrayList<SchoolEntity> data) {
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
        View view = inflater.inflate(R.layout.alert_item_layout, viewGroup, false);
        return new SchoolViewHolder(context, view);
    }

    /**
     * On Create Footer View Holder
     * @param viewGroup
     * @return
     */
    @Override
    protected RecyclerView.ViewHolder onCreateFooterViewHolder(ViewGroup viewGroup) {
        final View view = inflater.inflate(R.layout.progress_item, viewGroup, false);
        return new SupportFooterViewHolder(view);
    }


    /**
     * School View Holder
     */
    public final class SchoolViewHolder extends
            SupportItemSwipedViewHolder<SchoolEntity> {

        private Context context;


        /**
         * Alerts View Holder
         * @param context
         * @param itemView
         */
        public SchoolViewHolder(final Context context, final View itemView) {
            super(itemView);
            this.context = context;
        }

        /**
         * On Bind
         * @param schoolEntity
         */
        @Override
        public void bind(final SchoolEntity schoolEntity) {
            super.bind(schoolEntity);


        }

    }

}
