package sanchez.sanchez.sergio.bullkeeper.ui.adapter.impl;

import android.content.Context;
import android.graphics.drawable.Drawable;
import android.support.v4.content.ContextCompat;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;
import com.squareup.picasso.Picasso;
import java.util.ArrayList;
import sanchez.sanchez.sergio.domain.models.AlertEntity;
import sanchez.sanchez.sergio.bullkeeper.R;
import sanchez.sanchez.sergio.bullkeeper.ui.adapter.SupportRecyclerViewAdapter;

/**
 * Alerts Adapter
 */
public final class AlertsAdapter extends SupportRecyclerViewAdapter<AlertEntity>{

    /**
     *
     * @param context
     * @param data
     */
    public AlertsAdapter(Context context, ArrayList<AlertEntity> data) {
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
        return new AlertsViewHolder(context, view);
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
     * Alerts View Holder
     */
    public final class AlertsViewHolder extends
            SupportRecyclerViewAdapter<AlertEntity>.
                    SupportItemSwipedViewHolder<AlertEntity> {

        private Context context;

        private ImageView alertIcon, childImage;
        private TextView alertMessage, alertSince, alertSonName;

        /**
         * Alerts View Holder
         * @param context
         * @param itemView
         */
        public AlertsViewHolder(final Context context, final View itemView) {
            super(itemView);
            this.context = context;
            this.alertIcon = itemView.findViewById(R.id.alertIcon);
            this.childImage = itemView.findViewById(R.id.childImage);
            this.alertMessage = itemView.findViewById(R.id.alertMessage);
            this.alertSince = itemView.findViewById(R.id.alertSince);
            this.alertSonName = itemView.findViewById(R.id.alertSonName);
        }

        /**
         * On Bind
         * @param alertEntity
         */
        @Override
        public void bind(AlertEntity alertEntity) {
            super.bind(alertEntity);

            int alertColor;
            Drawable alertIconDrawable;

            switch (alertEntity.getLevel()){

                case INFO:
                    alertIconDrawable = ContextCompat.getDrawable(context, R.drawable.info_icon_solid);
                    alertColor = ContextCompat.getColor(context, R.color.cyanBrilliant);
                    break;
                case DANGER:
                    alertIconDrawable = ContextCompat.getDrawable(context, R.drawable.danger_icon_solid);
                    alertColor = ContextCompat.getColor(context, R.color.redDanger);
                    break;
                case SUCCESS:
                    alertIconDrawable = ContextCompat.getDrawable(context, R.drawable.success_icon_solid);
                    alertColor = ContextCompat.getColor(context, R.color.greenSuccess);
                    break;
                case WARNING:
                    alertIconDrawable = ContextCompat.getDrawable(context, R.drawable.warning_icon_solid);
                    alertColor = ContextCompat.getColor(context, R.color.yellowWarning);
                    break;
                default:
                    alertIconDrawable = ContextCompat.getDrawable(context, R.drawable.info_icon_solid);
                    alertColor = ContextCompat.getColor(context, R.color.cyanBrilliant);

            }

            // Set Alert Icon
            alertIcon.setImageDrawable(alertIconDrawable);
            // Text Color
            alertMessage.setTextColor(alertColor);
            // Set Alert Since
            alertSince.setText(alertEntity.getSince());
            // Set Alert Payload
            alertMessage.setText(alertEntity.getPayload());
            // Set Son Full name
            alertSonName.setText(alertEntity.getSon().getFullName());

            if(alertEntity.getSon() != null) {

                // Set Child Image
                Picasso.with(context).load(alertEntity.getSon().getProfileImage())
                        .placeholder(R.drawable.kid_default_image)
                        .error(R.drawable.kid_default_image)
                        .into(childImage);
            }

        }

        public ImageView getAlertIcon() {
            return alertIcon;
        }

        public void setAlertIcon(ImageView alertIcon) {
            this.alertIcon = alertIcon;
        }

        public ImageView getChildImage() {
            return childImage;
        }

        public void setChildImage(ImageView childImage) {
            this.childImage = childImage;
        }

        public TextView getAlertMessage() {
            return alertMessage;
        }

        public void setAlertMessage(TextView alertMessage) {
            this.alertMessage = alertMessage;
        }

    }

}
