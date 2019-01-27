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
import sanchez.sanchez.sergio.domain.models.GeofenceAlertEntity;
import sanchez.sanchez.sergio.domain.models.GeofenceTransitionTypeEnum;

/**
 * Geofences Alerts Adapter
 */
public final class GeofencesAlertsAdapter extends SupportRecyclerViewAdapter<GeofenceAlertEntity> {

    /**
     * @param context
     * @param data
     */
    public GeofencesAlertsAdapter(final Context context,
                                  final ArrayList<GeofenceAlertEntity> data) {
        super(context, data);
    }

    /**
     * On Create Item View Holder
     * @param viewGroup
     * @return
     */
    @Override
    protected RecyclerView.ViewHolder onCreateItemViewHolder(ViewGroup viewGroup) {
        View view = inflater.inflate(R.layout.geofence_alert_item_layout, viewGroup, false);
        return new GeofenceAlertViewHolder(view);
    }

    /**
     * Geofence Alert View Holder
     */
    public class GeofenceAlertViewHolder
            extends SupportItemSwipedViewHolder<GeofenceAlertEntity>{


        private ImageView geofenceAlertTypeImageView;
        private TextView geofenceAlertTitleTextView, geofenceAlertDescriptionTextView,
                geofenceAlertDateTextView;

        /**
         * @param itemView
         */
        GeofenceAlertViewHolder(final View itemView) {
            super(itemView);
            geofenceAlertTypeImageView = itemView.findViewById(R.id.geofenceAlertType);
            geofenceAlertTitleTextView = itemView.findViewById(R.id.geofenceAlertTitle);
            geofenceAlertDescriptionTextView = itemView.findViewById(R.id.geofenceAlertDescription);
            geofenceAlertDateTextView = itemView.findViewById(R.id.geofenceAlertDate);
        }

        /**
         * On Bind
         * @param geofenceAlertEntity
         */
        @Override
        public void bind(final GeofenceAlertEntity geofenceAlertEntity) {
            super.bind(geofenceAlertEntity);

            // Set Geofence Alert Title
            geofenceAlertTitleTextView.setText(geofenceAlertEntity.getTitle());

            // Set Geofence Alert Message
            geofenceAlertDescriptionTextView.setText(geofenceAlertEntity.getMessage());

            // Set Geofence Alert Type
            if(geofenceAlertEntity.getType().equals(GeofenceTransitionTypeEnum.TRANSITION_ENTER)) {
                geofenceAlertTypeImageView.setImageResource(R.drawable.geofence_transition_enter);
            } else {
                geofenceAlertTypeImageView.setImageResource(R.drawable.geofence_transition_exit);
            }

            final SimpleDateFormat sdf = new SimpleDateFormat(
                     context.getString(R.string.date_time_format), Locale.getDefault());

            // Set Geofence Date
            geofenceAlertDateTextView.setText(
                    sdf.format(geofenceAlertEntity.getDate()));

        }

    }
}
