package sanchez.sanchez.sergio.masom_app.ui.adapter.impl;

import android.content.Context;
import android.graphics.drawable.Drawable;
import android.support.v4.content.ContextCompat;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;
import com.squareup.picasso.Picasso;
import java.util.List;
import sanchez.sanchez.sergio.domain.models.AlertEntity;
import sanchez.sanchez.sergio.masom_app.R;
import sanchez.sanchez.sergio.masom_app.ui.adapter.SupportRecyclerViewAdapter;
import sanchez.sanchez.sergio.masom_app.ui.images.CircleTransform;

/**
 * Last Alerts Adapter
 */
public final class LastAlertsAdapter extends SupportRecyclerViewAdapter<AlertEntity,
        LastAlertsAdapter.LastAlertsViewHolder>{

    public LastAlertsAdapter(Context context, List<AlertEntity> data) {
        super(context, data);
    }

    /**
     * On Create View Holder
     * @param parent
     * @param viewType
     * @return
     */
    @Override
    public LastAlertsViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view = inflater.inflate(R.layout.last_alert_layout, parent, false);
        return new LastAlertsViewHolder(view);
    }


    /**
     * Last Alerts View Holder
     */
    public class LastAlertsViewHolder
            extends SupportRecyclerViewAdapter<AlertEntity, LastAlertsAdapter.LastAlertsViewHolder>.SupportViewHolder<AlertEntity>{

        private ImageView alertIcon;
        private ImageView childImage;
        private TextView alertMessage;

        LastAlertsViewHolder(View itemView) {
            super(itemView);
            alertIcon = itemView.findViewById(R.id.alertIcon);
            childImage = itemView.findViewById(R.id.childImage);
            alertMessage = itemView.findViewById(R.id.alertMessage);
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

    }
}
