package sanchez.sanchez.sergio.bullkeeper.ui.adapter.impl;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;
import java.util.ArrayList;
import java.util.Locale;
import sanchez.sanchez.sergio.bullkeeper.R;
import sanchez.sanchez.sergio.bullkeeper.ui.adapter.SupportRecyclerViewAdapter;
import sanchez.sanchez.sergio.domain.models.TerminalEntity;
import sanchez.sanchez.sergio.domain.models.TerminalStatusEnum;

/**
 * Terminals Adapter
 */
public final class TerminalsAdapter extends SupportRecyclerViewAdapter<TerminalEntity> {

    /**
     *
     * @param context
     * @param data
     */
    public TerminalsAdapter(Context context, ArrayList<TerminalEntity> data) {
        super(context, data);
    }

    /**
     * On Create Item View Holder
     * @param viewGroup
     * @return
     */
    @Override
    protected RecyclerView.ViewHolder onCreateItemViewHolder(ViewGroup viewGroup) {
        View view = inflater.inflate(R.layout.terminal_item_layout, viewGroup, false);
        return new TerminalViewHolder(view);
    }

    /**
     * Change Screen Status
     * @param status
     */
    public void changeScreenStatus(boolean status) {
        for(final TerminalEntity terminalEntity: data) {
            terminalEntity.setScreenEnabled(status);
        }
        notifyDataSetChanged();
    }


    /**
     * Terminal View Holder
     */
    public class TerminalViewHolder
            extends SupportItemSwipedViewHolder<TerminalEntity>{

        /**
         *
         */
        private TextView deviceFullNameTextView, deviceManufacturerTextView,
                systemVersionTextView, appVersionTextView;
        private ImageView cameraNotAllowedImageView, mobileScreenNotAllowedImageView,
                batteryStatusImageView, terminalStatusImageView,
                terminalExceededThresholdImageView, terminalAppNotInstalledImageView;

        /**
         * @param itemView
         */
        TerminalViewHolder(View itemView) {
            super(itemView);
            deviceFullNameTextView = itemView.findViewById(R.id.deviceFullName);
            deviceManufacturerTextView = itemView.findViewById(R.id.deviceManufacturer);
            systemVersionTextView = itemView.findViewById(R.id.systemVersion);
            appVersionTextView = itemView.findViewById(R.id.appVersion);
            cameraNotAllowedImageView = itemView.findViewById(R.id.cameraNotAllowed);
            mobileScreenNotAllowedImageView = itemView.findViewById(R.id.mobileScreenNotAllowed);
            batteryStatusImageView = itemView.findViewById(R.id.batteryStatus);
            terminalStatusImageView = itemView.findViewById(R.id.terminalStatus);
            terminalExceededThresholdImageView = itemView.findViewById(R.id.terminalExceededThreshold);
            terminalAppNotInstalledImageView = itemView.findViewById(R.id.terminalAppNotInstalled);
        }

        /**
         * On Bind
         * @param terminalEntity
         */
        @Override
        public void bind(final TerminalEntity terminalEntity) {
            super.bind(terminalEntity);

            // Device Full Name
            deviceFullNameTextView.setText(String.format(Locale.getDefault(),
                    "%s - %s", terminalEntity.getDeviceName(), terminalEntity.getModel()));

            // Set Manufacturer
            deviceManufacturerTextView.setText(terminalEntity.getManufacturer());

            // System Version
            systemVersionTextView.setText(String.format(Locale.getDefault(),
                    context.getString(R.string.terminal_os_sdk), terminalEntity.getOsVersion(),
                    terminalEntity.getSdkVersion()));

            // Set App Version
            appVersionTextView.setText(String.format(Locale.getDefault(),
                    context.getString(R.string.terminal_app_version),
                    terminalEntity.getAppVersionName(), terminalEntity.getAppVersionCode()));


            if(!terminalEntity.isCameraEnabled())
                cameraNotAllowedImageView.setVisibility(View.VISIBLE);
            else
                cameraNotAllowedImageView.setVisibility(View.INVISIBLE);


            if(!terminalEntity.isScreenEnabled())
                mobileScreenNotAllowedImageView.setVisibility(View.VISIBLE);
            else
                mobileScreenNotAllowedImageView.setVisibility(View.INVISIBLE);

            if(!terminalEntity.isDetached()) {
                terminalAppNotInstalledImageView.setVisibility(View.VISIBLE);
                terminalExceededThresholdImageView.setVisibility(View.GONE);
                batteryStatusImageView.setVisibility(View.GONE);
                terminalStatusImageView.setVisibility(View.GONE);
            } else {

                terminalAppNotInstalledImageView.setVisibility(View.GONE);

                if(terminalEntity.getTerminalHeartbeatEntity().hasExceededThreshold()) {
                    terminalExceededThresholdImageView.setVisibility(View.VISIBLE);
                    batteryStatusImageView.setVisibility(View.GONE);
                    terminalStatusImageView.setVisibility(View.GONE);
                } else {

                    terminalExceededThresholdImageView.setVisibility(View.GONE);

                    if(terminalEntity.isBatteryCharging()) {
                        batteryStatusImageView.setImageResource(R.drawable.battery_is_charging);
                    } else {

                        if(terminalEntity.getBatteryLevel() <= 100 && terminalEntity.getBatteryLevel() >= 80 ) {
                            batteryStatusImageView.setImageResource(R.drawable.battery_fully_charged);
                        } else if(terminalEntity.getBatteryLevel() < 80 && terminalEntity.getBatteryLevel() >= 30) {
                            batteryStatusImageView.setImageResource(R.drawable.normal_charge_battery);
                        } else {
                            batteryStatusImageView.setImageResource(R.drawable.battery_about_to_run_out);
                        }
                    }

                    if(terminalEntity.getStatus().equals(TerminalStatusEnum.STATE_OFF))
                        terminalStatusImageView.setVisibility(View.VISIBLE);
                    else
                        terminalStatusImageView.setVisibility(View.INVISIBLE);
                }
            }

        }

    }
}
