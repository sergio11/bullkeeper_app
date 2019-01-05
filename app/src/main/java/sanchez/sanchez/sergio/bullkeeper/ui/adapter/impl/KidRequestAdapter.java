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
import sanchez.sanchez.sergio.domain.models.KidRequestEntity;

/**
 * Kid Request Adapter
 */
public final class KidRequestAdapter
        extends SupportRecyclerViewAdapter<KidRequestEntity> {


    /**
     * Invitations Adapter
     * @param context
     * @param data
     */
    public KidRequestAdapter(final Context context,
                             final ArrayList<KidRequestEntity> data) {
        super(context, data);
        // enable header
        hasHeader = false;
        hasFooter = false;
    }

    /**
     * On Create Item View Holder
     * @param viewGroup
     * @return
     */
    @Override
    protected RecyclerView.ViewHolder onCreateItemViewHolder(ViewGroup viewGroup) {
        View view = inflater.inflate(R.layout.kid_request_item_layout, viewGroup, false);
        return new KidRequestViewHolder(context, view);
    }


    /**
     * Kid Request View Holder
     */
    public final class KidRequestViewHolder extends
            SupportItemSwipedViewHolder<KidRequestEntity> {

        private ImageView kidRequestTypeImageView;
        private TextView kidRequestTypeNameTextView, kidRequestSinceTextView;

        /**
         * Kid Request View Holder
         * @param context
         * @param itemView
         */
        public KidRequestViewHolder(final Context context, final View itemView) {
            super(itemView);
            kidRequestTypeImageView = itemView.findViewById(R.id.kidRequestType);
            kidRequestTypeNameTextView = itemView.findViewById(R.id.kidRequestTypeName);
            kidRequestSinceTextView = itemView.findViewById(R.id.kidRequestSince);
        }

        /**
         * On Bind
         * @param kidRequestEntity
         */
        @Override
        public void bind(KidRequestEntity kidRequestEntity) {
            super.bind(kidRequestEntity);

            switch (kidRequestEntity.getType()) {
                case SOS:
                    kidRequestTypeImageView.setImageResource(R.drawable.sos_icon_white);
                    kidRequestTypeNameTextView.setText(
                            context.getString(R.string.kid_request_sos_event_type_name));
                    break;
                case PICKMEUP:
                    kidRequestTypeImageView.setImageResource(R.drawable.pickme_up_solid_icon);
                    kidRequestTypeNameTextView.setText(
                            context.getString(R.string.kid_request_pickmeup_event_type_name));
                    break;
                default:
                    kidRequestTypeImageView.setImageResource(R.drawable.unknown_icon_white);
                    kidRequestTypeNameTextView.setText(
                            context.getString(R.string.kid_request_unknown_type_name));
            }

            kidRequestSinceTextView.setText(
                    String.format(Locale.getDefault(),
                            context.getString(R.string.kid_request_registered_since),
                                kidRequestEntity.getSince()));
        }

    }
}
