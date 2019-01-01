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
     * Terminal View Holder
     */
    public class TerminalViewHolder
            extends SupportItemSwipedViewHolder<TerminalEntity>{

        /**
         *
         */
        private TextView deviceFullNameTextView, deviceManufacturerTextView,
                systemVersionTextView, appVersionTextView;
        private ImageView cameraNotAllowedImageView, mobileScreenNotAllowedImageView;

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


            if(terminalEntity.isLockCameraEnabled())
                cameraNotAllowedImageView.setVisibility(View.VISIBLE);
            else
                cameraNotAllowedImageView.setVisibility(View.INVISIBLE);


            if(terminalEntity.isLockScreenEnabled())
                mobileScreenNotAllowedImageView.setVisibility(View.VISIBLE);
            else
                mobileScreenNotAllowedImageView.setVisibility(View.INVISIBLE);



        }

    }
}
