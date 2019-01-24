package sanchez.sanchez.sergio.bullkeeper.ui.adapter.impl;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;
import java.util.ArrayList;
import sanchez.sanchez.sergio.bullkeeper.R;
import sanchez.sanchez.sergio.bullkeeper.core.ui.components.SupportSwitchCompat;
import sanchez.sanchez.sergio.bullkeeper.ui.adapter.SupportRecyclerViewAdapter;
import sanchez.sanchez.sergio.domain.models.GeofenceEntity;

/**
 * Geofences Adapter
 */
public final class GeofencesAdapter extends SupportRecyclerViewAdapter<GeofenceEntity> {

    /**
     * @param context
     * @param data
     */
    public GeofencesAdapter(final Context context, final ArrayList<GeofenceEntity> data) {
        super(context, data);
    }

    /**
     * On Create Item View Holder
     * @param viewGroup
     * @return
     */
    @Override
    protected RecyclerView.ViewHolder onCreateItemViewHolder(ViewGroup viewGroup) {
        View view = inflater.inflate(R.layout.geofence_item_layout, viewGroup, false);
        return new GeofenceViewHolder(view);
    }

    /**
     * Geofence View Holder
     */
    public class GeofenceViewHolder
            extends SupportItemSwipedViewHolder<GeofenceEntity>{


        /**
         * Geofence Name
         */
        private TextView geofenceNameTextView, geofenceAddressTextView;
        private SupportSwitchCompat switchWidget;

        /**
         * @param itemView
         */
        GeofenceViewHolder(final View itemView) {
            super(itemView);
            geofenceNameTextView = itemView.findViewById(R.id.geofenceName);
            geofenceAddressTextView = itemView.findViewById(R.id.geofenceAddress);
            switchWidget = itemView.findViewById(R.id.switchWidget);
        }

        /**
         * On Bind
         * @param geofenceEntity
         */
        @Override
        public void bind(final GeofenceEntity geofenceEntity) {
            super.bind(geofenceEntity);

            // Geofence Name
            geofenceNameTextView.setText(geofenceEntity.getName());
            // Geofence Address
            geofenceAddressTextView.setText(geofenceEntity.getAddress());
            // Geofence Status
            switchWidget.setChecked(geofenceEntity.isEnabled());
        }

    }
}
