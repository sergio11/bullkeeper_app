package sanchez.sanchez.sergio.masom_app.ui.adapter.impl;

import android.content.Context;
import android.graphics.drawable.Drawable;
import android.support.v4.content.ContextCompat;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.TextView;
import com.squareup.picasso.Picasso;
import java.util.ArrayList;
import sanchez.sanchez.sergio.domain.models.AlertEntity;
import sanchez.sanchez.sergio.masom_app.R;
import sanchez.sanchez.sergio.masom_app.ui.adapter.SupportRecyclerViewAdapter;
import sanchez.sanchez.sergio.masom_app.ui.images.CircleTransform;

/**
 * Last Alerts Adapter
 */
public final class AlertsAdapter extends SupportRecyclerViewAdapter<AlertEntity>{

    private OnAlertsViewListener onAlertsViewListener;

    /**
     *
     * @param context
     * @param data
     */
    public AlertsAdapter(Context context, ArrayList<AlertEntity> data) {
        super(context, data);
        // enable header
        hasHeader = true;
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
     * On Create Header View Holder
     * @param viewGroup
     * @return
     */
    @Override
    protected RecyclerView.ViewHolder onCreateHeaderViewHolder(ViewGroup viewGroup) {
        final View view = inflater.inflate(R.layout.alert_header_layout, viewGroup, false);
        return new AlertsHeaderViewHolder(view);
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
     * Set On Alerts View Listener
     * @param onAlertsViewListener
     */
    public void setOnAlertsViewListener(OnAlertsViewListener onAlertsViewListener){
        this.onAlertsViewListener = onAlertsViewListener;
    }

    /**
     * On Alerts View Listener
     */
    public interface OnAlertsViewListener {

        /**
         * On Clear All Alerts
         */
        void onClearAllAlerts();

        /**
         * On Filter Alerts
         */
        void onFilterAlerts();
    }

    /**
     * Alerts View Holder
     */
    public final class AlertsViewHolder extends
            SupportRecyclerViewAdapter<AlertEntity>.
                    SupportItemSwipedViewHolder<AlertEntity> {

        private Context context;

        private ImageView alertIcon;
        private ImageView childImage;
        private TextView alertMessage;

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
            // Set Child Image
            Picasso.with(context).load("https://avatars3.githubusercontent.com/u/831538?s=460&v=4")
                    .placeholder(R.drawable.user_default)
                    .error(R.drawable.user_default)
                    .transform(new CircleTransform(alertColor))
                    .into(childImage);
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


    /**
     * Alerts Header View Holder
     */
    public class AlertsHeaderViewHolder extends SupportHeaderViewHolder {

        private ImageButton filterAlerts, clearAlerts;

        public AlertsHeaderViewHolder(View itemView) {
            super(itemView);

            this.filterAlerts = itemView.findViewById(R.id.filterAlerts);
            this.clearAlerts = itemView.findViewById(R.id.clearAlerts);

            clearAlerts.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    if(onAlertsViewListener != null)
                        onAlertsViewListener.onClearAllAlerts();
                }
            });

            filterAlerts.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    if(onAlertsViewListener != null)
                        onAlertsViewListener.onFilterAlerts();
                }
            });

        }

        public ImageButton getFilterAlerts() {
            return filterAlerts;
        }

        public ImageButton getClearAlerts() {
            return clearAlerts;
        }
    }


}
