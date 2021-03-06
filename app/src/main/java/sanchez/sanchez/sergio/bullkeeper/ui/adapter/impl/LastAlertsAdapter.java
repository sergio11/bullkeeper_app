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

import de.hdodenhof.circleimageview.CircleImageView;
import sanchez.sanchez.sergio.domain.models.AlertEntity;
import sanchez.sanchez.sergio.bullkeeper.R;
import sanchez.sanchez.sergio.bullkeeper.ui.adapter.SupportRecyclerViewAdapter;

/**
 * Last Alerts Adapter
 */
public final class LastAlertsAdapter extends SupportRecyclerViewAdapter<AlertEntity>{

    private final Picasso picasso;

    /**
     *
     * @param context
     * @param data
     */
    public LastAlertsAdapter(final Context context, final ArrayList<AlertEntity> data, final Picasso picasso) {
        super(context, data);
        this.picasso = picasso;

    }

    /**
     * On Create Item View Holder
     * @param viewGroup
     * @return
     */
    @Override
    protected RecyclerView.ViewHolder onCreateItemViewHolder(ViewGroup viewGroup) {
        View view = inflater.inflate(R.layout.alert_item_layout, viewGroup, false);
        return new LastAlertsViewHolder(view);
    }


    /**
     * Last Alerts View Holder
     */
    public class LastAlertsViewHolder
            extends SupportRecyclerViewAdapter<AlertEntity>.SupportItemSwipedViewHolder<AlertEntity>{


        private CircleImageView childImage;
        private ImageView alertIcon;
        private TextView alertMessage, alertSince, alertKidName;

        LastAlertsViewHolder(View itemView) {
            super(itemView);
            this.alertIcon = itemView.findViewById(R.id.alertIcon);
            this.childImage = itemView.findViewById(R.id.childImage);
            this.alertMessage = itemView.findViewById(R.id.alertMessage);
            this.alertSince = itemView.findViewById(R.id.alertSince);
            this.alertKidName = itemView.findViewById(R.id.alertKidName);
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
            // Set Kid Full name
            alertKidName.setText(alertEntity.getSon().getFullName());

            if(alertEntity.getSon() != null) {

                if(alertEntity.getSon().getProfileImage() != null &&
                        !alertEntity.getSon().getProfileImage().isEmpty())
                    // Set Child Image
                    picasso.load(alertEntity.getSon().getProfileImage())
                        .placeholder(R.drawable.kid_default_image)
                        .error(R.drawable.kid_default_image)
                        .into(childImage);
                else
                    childImage.setImageResource(R.drawable.kid_default_image);
            }

            childImage.setBorderColor(alertColor);

        }

        public ImageView getAlertIcon() {
            return alertIcon;
        }

        public void setAlertIcon(ImageView alertIcon) {
            this.alertIcon = alertIcon;
        }

        public CircleImageView getChildImage() {
            return childImage;
        }

        public void setChildImage(CircleImageView childImage) {
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
