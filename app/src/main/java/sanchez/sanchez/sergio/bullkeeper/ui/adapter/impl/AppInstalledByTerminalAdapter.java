package sanchez.sanchez.sergio.bullkeeper.ui.adapter.impl;

import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.support.v7.widget.RecyclerView;
import android.util.Base64;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import java.util.ArrayList;

import de.hdodenhof.circleimageview.CircleImageView;
import sanchez.sanchez.sergio.bullkeeper.R;
import sanchez.sanchez.sergio.bullkeeper.ui.adapter.SupportRecyclerViewAdapter;
import sanchez.sanchez.sergio.domain.models.AppInstalledByTerminalEntity;
import sanchez.sanchez.sergio.domain.models.AppInstalledEntity;

/**
 * App Installed By Terminal Adapter
 */
public final class AppInstalledByTerminalAdapter
        extends SupportRecyclerViewAdapter<AppInstalledByTerminalEntity> {

    /**
     * @param context
     * @param data
     */
    public AppInstalledByTerminalAdapter(final Context context, final ArrayList<AppInstalledByTerminalEntity> data) {
        super(context, data);
    }

    /**
     * On Create Item View Holder
     * @param viewGroup
     * @return
     */
    @Override
    protected RecyclerView.ViewHolder onCreateItemViewHolder(ViewGroup viewGroup) {
        View view = inflater.inflate(R.layout.app_installed_by_terminal_item_layout, viewGroup, false);
        return new AppInstalledViewHolder(view);
    }


    /**
     * App Installed View Holder
     */
    public class AppInstalledViewHolder
            extends SupportItemSwipedViewHolder<AppInstalledByTerminalEntity>{

        private ImageView appNotAllowed, appPerScheduled, appFunTime, appAllowed, appDisabledImageView;
        private TextView appInstalledName, deviceNameTextView;
        private CircleImageView appInstalledImage;

        /**
         *
         * @param itemView
         */
        AppInstalledViewHolder(View itemView) {
            super(itemView);
            appNotAllowed = itemView.findViewById(R.id.appNotAllowed);
            appAllowed = itemView.findViewById(R.id.appAllowed);
            appFunTime = itemView.findViewById(R.id.appFunTime);
            appPerScheduled = itemView.findViewById(R.id.appPerScheduled);
            appDisabledImageView = itemView.findViewById(R.id.appDisabled);
            appInstalledName = itemView.findViewById(R.id.appInstalledName);
            deviceNameTextView = itemView.findViewById(R.id.deviceNameTextView);
            appInstalledImage = itemView.findViewById(R.id.appInstalledImage);
        }


        /**
         * On Bind
         * @param appInstalledEntity
         */
        @Override
        public void bind(final AppInstalledByTerminalEntity appInstalledEntity) {
            super.bind(appInstalledEntity);

            // Set App Name
            if(hasHighlightText())
                appInstalledName.setText(getSpannableString(appInstalledEntity.getAppName()));
            else
                appInstalledName.setText(appInstalledEntity.getAppName());

            if(appInstalledEntity.getIconEncodedString() != null &&
                    !appInstalledEntity.getIconEncodedString().isEmpty()) {
                byte[] decodedString = Base64.decode(appInstalledEntity.getIconEncodedString(),
                        Base64.DEFAULT);
                final Bitmap decodedByte =
                        BitmapFactory.decodeByteArray(decodedString, 0, decodedString.length);
                appInstalledImage.setImageBitmap(decodedByte);
            }

            deviceNameTextView.setText(appInstalledEntity.getTerminal().getDeviceName());

            if(appInstalledEntity.getDisabled()) {

                appNotAllowed.setImageResource(R.drawable.app_not_allowed_disabled);
                appAllowed.setImageResource(R.drawable.app_allowed_disabled);
                appPerScheduled.setImageResource(R.drawable.app_per_scheduled_disabled);
                appFunTime.setImageResource(R.drawable.app_fun_time_disabled);
                appDisabledImageView.setVisibility(View.VISIBLE);

            } else {

                switch (appInstalledEntity.getAppRuleEnum()) {

                    // Never Allowed
                    case NEVER_ALLOWED:

                        appNotAllowed.setImageResource(R.drawable.app_not_allowed_enabled);
                        appAllowed.setImageResource(R.drawable.app_allowed_disabled);
                        appPerScheduled.setImageResource(R.drawable.app_per_scheduled_disabled);
                        appFunTime.setImageResource(R.drawable.app_fun_time_disabled);
                        break;
                    // Always Allowed
                    case ALWAYS_ALLOWED:

                        appNotAllowed.setImageResource(R.drawable.app_not_allowed_disabled);
                        appAllowed.setImageResource(R.drawable.app_allowed_enabled);
                        appPerScheduled.setImageResource(R.drawable.app_per_scheduled_disabled);
                        appFunTime.setImageResource(R.drawable.app_fun_time_disabled);
                        break;

                    // Fun Time
                    case FUN_TIME:

                        appNotAllowed.setImageResource(R.drawable.app_not_allowed_disabled);
                        appAllowed.setImageResource(R.drawable.app_allowed_disabled);
                        appPerScheduled.setImageResource(R.drawable.app_per_scheduled_disabled);
                        appFunTime.setImageResource(R.drawable.app_fun_time_enabled);
                        break;
                    // Per Scheduler
                    case PER_SCHEDULER:

                        appNotAllowed.setImageResource(R.drawable.app_not_allowed_disabled);
                        appAllowed.setImageResource(R.drawable.app_allowed_disabled);
                        appPerScheduled.setImageResource(R.drawable.app_per_scheduled_enabled);
                        appFunTime.setImageResource(R.drawable.app_fun_time_disabled);

                        break;
                }


                appDisabledImageView.setVisibility(View.INVISIBLE);
            }

        }

    }
}
