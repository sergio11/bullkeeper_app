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
import com.squareup.picasso.Picasso;
import java.util.ArrayList;
import java.util.Map;

import de.hdodenhof.circleimageview.CircleImageView;
import sanchez.sanchez.sergio.bullkeeper.R;
import sanchez.sanchez.sergio.bullkeeper.ui.adapter.SupportRecyclerViewAdapter;
import sanchez.sanchez.sergio.domain.models.AppInstalledEntity;
import sanchez.sanchez.sergio.domain.models.AppRuleEnum;

/**
 * App Rules Adapter
 */
public final class AppRulesAdapter extends SupportRecyclerViewAdapter<AppInstalledEntity> {

    private OnAppRulesListener listener;
    private final Picasso picasso;

    /**
     *
     * @param context
     * @param data
     * @param picasso
     */
    public AppRulesAdapter(final Context context, final ArrayList<AppInstalledEntity> data, final Picasso picasso) {
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
        View view = inflater.inflate(R.layout.app_rules_item_layout, viewGroup, false);
        return new AppRulesViewHolder(view);
    }

    /**
     *
     * @param listener
     */
    public void setOnAppRulesListener(final OnAppRulesListener listener) {
        this.listener = listener;
    }


    /**
     *
     * @param appRules
     */
    public void applyRules(Map<String, AppRuleEnum> appRules) {

        boolean matchAny = false;
        for(final AppInstalledEntity appInstalledEntity: data) {
            if (appRules.containsKey(appInstalledEntity.getIdentity())) {
                final AppRuleEnum appRuleEnum = appRules.get(appInstalledEntity.getIdentity());
                appInstalledEntity.setAppRuleEnum(appRuleEnum);
                matchAny = true;
            }
        }

        if(matchAny) notifyDataSetChanged();
    }

    /**
     * On App Rules Listener
     */
    public interface OnAppRulesListener {

        /**
         * On App Rule Changed
         * @param appInstalledIdentity
         * @param oldAppRule
         * @param newAppRule
         */
        void onAppRuleChanged(final String appInstalledIdentity, final AppRuleEnum oldAppRule,
                              final AppRuleEnum newAppRule);

    }


    /**
     * App Rules View Holder
     */
    public class AppRulesViewHolder
            extends SupportItemSwipedViewHolder<AppInstalledEntity>{


        private ImageView appNotAllowed, appPerScheduled, appAllowed, appDisabledImageView;
        private TextView appInstalledName;
        private CircleImageView appInstalledImage;

        /**
         *
         * @param itemView
         */
        AppRulesViewHolder(View itemView) {
            super(itemView);
            appNotAllowed = itemView.findViewById(R.id.appNotAllowed);
            appAllowed = itemView.findViewById(R.id.appAllowed);
            appPerScheduled = itemView.findViewById(R.id.appPerScheduled);
            appDisabledImageView = itemView.findViewById(R.id.appDisabled);
            appInstalledName = itemView.findViewById(R.id.appInstalledName);
            appInstalledImage = itemView.findViewById(R.id.appInstalledImage);
        }

        /**
         * On Bind
         * @param appInstalledEntity
         */
        @Override
        public void bind(final AppInstalledEntity appInstalledEntity) {
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


            if(appInstalledEntity.getDisabled()) {

                appNotAllowed.setImageResource(R.drawable.app_not_allowed_disabled);
                appNotAllowed.setEnabled(false);
                appAllowed.setImageResource(R.drawable.app_allowed_disabled);
                appAllowed.setEnabled(false);
                appPerScheduled.setImageResource(R.drawable.app_per_scheduled_disabled);
                appPerScheduled.setEnabled(false);
                appDisabledImageView.setVisibility(View.VISIBLE);

            } else {

                switch (appInstalledEntity.getAppRuleEnum()) {

                    // Never Allowed
                    case NEVER_ALLOWED:

                        appNotAllowed.setImageResource(R.drawable.app_not_allowed_enabled);
                        appAllowed.setImageResource(R.drawable.app_allowed_disabled);
                        appPerScheduled.setImageResource(R.drawable.app_per_scheduled_disabled);

                        break;
                    // Always Allowed
                    case ALWAYS_ALLOWED:

                        appNotAllowed.setImageResource(R.drawable.app_not_allowed_disabled);
                        appAllowed.setImageResource(R.drawable.app_allowed_enabled);
                        appPerScheduled.setImageResource(R.drawable.app_per_scheduled_disabled);

                        break;
                    // Per Scheduler
                    case PER_SCHEDULER:

                        appNotAllowed.setImageResource(R.drawable.app_not_allowed_disabled);
                        appAllowed.setImageResource(R.drawable.app_allowed_disabled);
                        appPerScheduled.setImageResource(R.drawable.app_per_scheduled_enabled);

                        break;
                }


                // App Not Allowed
                appNotAllowed.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        appNotAllowed.setImageResource(R.drawable.app_not_allowed_enabled);
                        appAllowed.setImageResource(R.drawable.app_allowed_disabled);
                        appPerScheduled.setImageResource(R.drawable.app_per_scheduled_disabled);

                        final AppRuleEnum oldAppRule = appInstalledEntity.getAppRuleEnum();
                        final AppRuleEnum newAppRule = AppRuleEnum.NEVER_ALLOWED;

                        appInstalledEntity.setAppRuleEnum(newAppRule);

                        if(listener != null)
                            listener.onAppRuleChanged(appInstalledEntity.getIdentity(), oldAppRule,
                                    newAppRule);
                    }
                });

                // App Allowed
                appAllowed.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        appNotAllowed.setImageResource(R.drawable.app_not_allowed_disabled);
                        appAllowed.setImageResource(R.drawable.app_allowed_enabled);
                        appPerScheduled.setImageResource(R.drawable.app_per_scheduled_disabled);

                        final AppRuleEnum oldAppRule = appInstalledEntity.getAppRuleEnum();
                        final AppRuleEnum newAppRule = AppRuleEnum.ALWAYS_ALLOWED;

                        appInstalledEntity.setAppRuleEnum(newAppRule);

                        if(listener != null)
                            listener.onAppRuleChanged(appInstalledEntity.getIdentity(), oldAppRule,
                                    newAppRule);

                    }
                });


                // App Per Scheduled
                appPerScheduled.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        appNotAllowed.setImageResource(R.drawable.app_not_allowed_disabled);
                        appAllowed.setImageResource(R.drawable.app_allowed_disabled);
                        appPerScheduled.setImageResource(R.drawable.app_per_scheduled_enabled);

                        final AppRuleEnum oldAppRule = appInstalledEntity.getAppRuleEnum();
                        final AppRuleEnum newAppRule = AppRuleEnum.PER_SCHEDULER;

                        appInstalledEntity.setAppRuleEnum(newAppRule);

                        if(listener != null)
                            listener.onAppRuleChanged(appInstalledEntity.getIdentity(), oldAppRule,
                                    newAppRule);
                    }
                });

                appDisabledImageView.setVisibility(View.INVISIBLE);
            }
        }

    }
}
