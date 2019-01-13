package sanchez.sanchez.sergio.bullkeeper.ui.adapter.impl;

import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.support.v7.widget.RecyclerView;
import android.util.Base64;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;
import java.util.ArrayList;
import de.hdodenhof.circleimageview.CircleImageView;
import sanchez.sanchez.sergio.bullkeeper.R;
import sanchez.sanchez.sergio.bullkeeper.ui.adapter.SupportRecyclerViewAdapter;
import sanchez.sanchez.sergio.domain.models.AppAllowedByScheduledEntity;
import sanchez.sanchez.sergio.domain.models.AppRuleEnum;

/**
 * App Allowed By Scheduled Adapter
 */
public final class AppAllowedByScheduledAdapter extends SupportRecyclerViewAdapter<AppAllowedByScheduledEntity> {


    /**
     * @param context
     * @param data
     */
    public AppAllowedByScheduledAdapter(final Context context, final ArrayList<AppAllowedByScheduledEntity> data) {
        super(context, data);
    }

    /**
     * On Create Item View Holder
     * @param viewGroup
     * @return
     */
    @Override
    protected RecyclerView.ViewHolder onCreateItemViewHolder(ViewGroup viewGroup) {
        View view = inflater.inflate(R.layout.app_allowed_by_scheduled_item_layout, viewGroup, false);
        return new AppAllowedByScheduledViewHolder(view);
    }


    /**
     * App Allowed By Scheduled View Holder
     */
    public class AppAllowedByScheduledViewHolder
            extends SupportItemSwipedViewHolder<AppAllowedByScheduledEntity>{


        private CircleImageView appImageView;
        private TextView appNameTextView, deviceNameTextView, invalidConfigurationTextView;

        /**
         *
         * @param itemView
         */
        AppAllowedByScheduledViewHolder(View itemView) {
            super(itemView);
            appImageView = itemView.findViewById(R.id.appImageView);
            appNameTextView = itemView.findViewById(R.id.appName);
            deviceNameTextView = itemView.findViewById(R.id.deviceNameTextView);
            invalidConfigurationTextView = itemView.findViewById(R.id.invalidConfiguration);
        }


        /**
         * On Bind
         * @param appAllowedByScheduledEntity
         */
        @Override
        public void bind(final AppAllowedByScheduledEntity appAllowedByScheduledEntity) {
            super.bind(appAllowedByScheduledEntity);

            appNameTextView.setText(appAllowedByScheduledEntity.getApp().getAppName());

            if(appAllowedByScheduledEntity.getApp().getIconEncodedString() != null &&
                    !appAllowedByScheduledEntity.getApp().getIconEncodedString().isEmpty()) {
                byte[] decodedString = Base64.decode(appAllowedByScheduledEntity.getApp().getIconEncodedString(),
                        Base64.DEFAULT);
                final Bitmap decodedByte =
                        BitmapFactory.decodeByteArray(decodedString, 0, decodedString.length);
                appImageView.setImageBitmap(decodedByte);
            }

            deviceNameTextView.setText(appAllowedByScheduledEntity
                    .getTerminal().getDeviceName());


            if(appAllowedByScheduledEntity.getApp().getDisabled() ||
                    !appAllowedByScheduledEntity.getApp()
                            .getAppRuleEnum().equals(AppRuleEnum.PER_SCHEDULER))
                invalidConfigurationTextView.setVisibility(View.VISIBLE);

        }

    }
}
