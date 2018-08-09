package sanchez.sanchez.sergio.masom_app.ui.adapter.impl;

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
import java.util.List;
import sanchez.sanchez.sergio.domain.models.AlertEntity;
import sanchez.sanchez.sergio.masom_app.R;
import sanchez.sanchez.sergio.masom_app.ui.adapter.SupportRecyclerViewAdapter;
import sanchez.sanchez.sergio.masom_app.ui.images.CircleTransform;

/**
 * Last Alerts Adapter
 */
public final class LastAlertsAdapter extends SupportRecyclerViewAdapter<AlertEntity>{

    /**
     *
     * @param context
     * @param data
     */
    public LastAlertsAdapter(Context context, ArrayList<AlertEntity> data) {
        super(context, data);
        // enable header
        hasHeader = true;

    }

    /**
     * On Create Item View Holder
     * @param viewGroup
     * @return
     */
    @Override
    protected RecyclerView.ViewHolder onCreateItemViewHolder(ViewGroup viewGroup) {
        View view = inflater.inflate(R.layout.last_alert_item_layout, viewGroup, false);
        return new LastAlertsViewHolder(view);
    }

    /**
     * On Create Header View Holder
     * @param viewGroup
     * @return
     */
    @Override
    protected RecyclerView.ViewHolder onCreateHeaderViewHolder(ViewGroup viewGroup) {
        final View view = inflater.inflate(R.layout.last_alert_header_layout, viewGroup, false);
        return new LastAlertsHeaderViewHolder(view);
    }


    /**
     * Last Alerts Header VIew Holder
     */
    public class LastAlertsHeaderViewHolder extends SupportHeaderViewHolder {

        public LastAlertsHeaderViewHolder(View itemView) {
            super(itemView);
        }
    }


    /**
     * Last Alerts View Holder
     */
    public class LastAlertsViewHolder
            extends SupportRecyclerViewAdapter<AlertEntity>.SupportItemViewHolder<AlertEntity>{

        private ImageView alertIcon;
        private ImageView childImage;
        private TextView alertMessage;
        private ViewGroup viewBackground, viewForeground;

        LastAlertsViewHolder(View itemView) {
            super(itemView);
            alertIcon = itemView.findViewById(R.id.alertIcon);
            childImage = itemView.findViewById(R.id.childImage);
            alertMessage = itemView.findViewById(R.id.alertMessage);
            viewBackground = itemView.findViewById(R.id.item_background);
            viewForeground = itemView.findViewById(R.id.item_foreground);
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

        public ViewGroup getViewBackground() {
            return viewBackground;
        }

        public void setViewBackground(ViewGroup viewBackground) {
            this.viewBackground = viewBackground;
        }

        public ViewGroup getViewForeground() {
            return viewForeground;
        }

        public void setViewForeground(ViewGroup viewForeground) {
            this.viewForeground = viewForeground;
        }
    }
}
