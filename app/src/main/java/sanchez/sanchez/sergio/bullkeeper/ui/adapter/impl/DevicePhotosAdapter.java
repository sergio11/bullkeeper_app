package sanchez.sanchez.sergio.bullkeeper.ui.adapter.impl;

import android.content.Context;
import android.support.v7.widget.AppCompatImageView;
import android.support.v7.widget.AppCompatTextView;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.view.ViewGroup;
import com.squareup.picasso.Picasso;
import java.util.ArrayList;
import sanchez.sanchez.sergio.bullkeeper.R;
import sanchez.sanchez.sergio.bullkeeper.ui.adapter.SupportRecyclerViewAdapter;
import sanchez.sanchez.sergio.domain.models.DevicePhotoEntity;

/**
 * Device Photos Adapter
 */
public final class DevicePhotosAdapter extends SupportRecyclerViewAdapter<DevicePhotoEntity> {

    private final Picasso picasso;

    /**
     * @param context
     * @param data
     */
    public DevicePhotosAdapter(final Context context, final ArrayList<DevicePhotoEntity> data,  final Picasso picasso) {
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
        View view = inflater.inflate(R.layout.device_photo_item_layout, viewGroup, false);
        return new DevicePhotoViewHolder(view);
    }

    /**
     * Device Photo View Holder
     */
    public class DevicePhotoViewHolder
            extends SupportItemViewHolder<DevicePhotoEntity>{

        private AppCompatImageView imageView;
        private AppCompatTextView displayNameTextView;


        /**
         * @param itemView
         */
        DevicePhotoViewHolder(final View itemView) {
            super(itemView);
            imageView = itemView.findViewById(R.id.image);
            displayNameTextView = itemView.findViewById(R.id.displayName);
        }

        /**
         * On Bind
         * @param devicePhotoEntity
         */
        @Override
        public void bind(final DevicePhotoEntity devicePhotoEntity) {
            super.bind(devicePhotoEntity);

            displayNameTextView.setText(devicePhotoEntity.getDisplayName());

            final ViewGroup.LayoutParams params = imageView.getLayoutParams();
            params.width = devicePhotoEntity.getWidth();
            params.height = devicePhotoEntity.getHeight();
            imageView.setLayoutParams(params);

            picasso.load(devicePhotoEntity.getImageUrl())
                    .rotate(devicePhotoEntity.getOrientation())
                    .into(imageView);

        }

    }
}
