package sanchez.sanchez.sergio.bullkeeper.ui.adapter.impl;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.view.ViewGroup;
import android.widget.CompoundButton;
import android.widget.TextView;

import com.fernandocejas.arrow.checks.Preconditions;

import java.util.ArrayList;
import sanchez.sanchez.sergio.bullkeeper.R;
import sanchez.sanchez.sergio.bullkeeper.core.ui.components.SupportSwitchCompat;
import sanchez.sanchez.sergio.bullkeeper.ui.adapter.SupportRecyclerViewAdapter;
import sanchez.sanchez.sergio.domain.models.GeofenceEntity;

/**
 * Geofences Adapter
 */
public final class GeofencesAdapter extends SupportRecyclerViewAdapter<GeofenceEntity> {


    private IGeofenceStatusListener listener;

    /**
     * @param context
     * @param data
     */
    public GeofencesAdapter(final Context context, final ArrayList<GeofenceEntity> data) {
        super(context, data);
    }


    public void setListener(IGeofenceStatusListener listener) {
        this.listener = listener;
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
     * Update Status
     * @param geofence
     * @param status
     */
    public void updateStatus(final String geofence, final boolean status) {
        Preconditions.checkNotNull(geofence, "Geofence can not be null");
        Preconditions.checkState(!geofence.isEmpty(), "Geofence can not be empty");
        for(int i = 0; i < getData().size(); i++) {
            final GeofenceEntity geofenceEntity =
                    getData().get(i);
            if (geofenceEntity.getIdentity().equals(geofence)){
                geofenceEntity.setEnabled(status);
                notifyItemChanged(i);
                break;
            }
        }

    }

    /**
     *
     */
    public interface IGeofenceStatusListener {

        /**
         * @param identity
         * @param newStatus
         */
        void onGeofenceStatusChanged(final String identity, final boolean newStatus);

    }

    /**
     * Geofence View Holder
     */
    public class GeofenceViewHolder
            extends SupportItemSwipedViewHolder<GeofenceEntity>{


        /**
         * Views
         */
        private TextView geofenceNameTextView, geofenceAddressTextView;
        private SupportSwitchCompat geofenceStatusSwitchWidget;

        /**
         * @param itemView
         */
        GeofenceViewHolder(final View itemView) {
            super(itemView);
            geofenceNameTextView = itemView.findViewById(R.id.geofenceName);
            geofenceAddressTextView = itemView.findViewById(R.id.geofenceAddress);
            geofenceStatusSwitchWidget = itemView.findViewById(R.id.geofenceStatusSwitchWidget);
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
            geofenceStatusSwitchWidget.setChecked(geofenceEntity.isEnabled(), false);

            geofenceStatusSwitchWidget.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
                @Override
                public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {
                    geofenceEntity.setEnabled(isChecked);
                    if(listener != null)
                        listener.onGeofenceStatusChanged(geofenceEntity.getIdentity(), isChecked);
                }
            });
        }

    }
}
