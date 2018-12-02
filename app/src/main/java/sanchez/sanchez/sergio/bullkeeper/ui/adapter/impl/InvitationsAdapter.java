package sanchez.sanchez.sergio.bullkeeper.ui.adapter.impl;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;
import com.squareup.picasso.Picasso;
import java.util.ArrayList;
import sanchez.sanchez.sergio.bullkeeper.R;
import sanchez.sanchez.sergio.bullkeeper.ui.adapter.SupportRecyclerViewAdapter;
import sanchez.sanchez.sergio.domain.models.KidEntity;
import sanchez.sanchez.sergio.domain.models.SupervisedChildrenEntity;

/**
 * Invitations Adapter
 */
public final class InvitationsAdapter
        extends SupportRecyclerViewAdapter<SupervisedChildrenEntity> {

    /**
     * Picasso
     */
    private final Picasso picasso;

    /**
     * Invitations Adapter
     * @param context
     * @param data
     * @param picasso
     */
    public InvitationsAdapter(final Context context, final ArrayList<SupervisedChildrenEntity> data,
                              final Picasso picasso) {
        super(context, data);
        this.picasso = picasso;
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
        View view = inflater.inflate(R.layout.invitation_item_layout, viewGroup, false);
        return new SupervisedChildrenViewHolder(context, view);
    }


    /**
     * Supervised Children View Holder
     */
    public final class SupervisedChildrenViewHolder extends
            SupportRecyclerViewAdapter<SupervisedChildrenEntity>.
                    SupportItemSwipedViewHolder<SupervisedChildrenEntity> {

        private Context context;

        private ImageView roleImageView, childImage;
        private TextView childName, schoolName;

        /**
         * Alerts View Holder
         * @param context
         * @param itemView
         */
        public SupervisedChildrenViewHolder(final Context context, final View itemView) {
            super(itemView);
            this.childImage = itemView.findViewById(R.id.childImage);
            this.childName = itemView.findViewById(R.id.childName);
            this.schoolName = itemView.findViewById(R.id.schoolName);
            this.roleImageView = itemView.findViewById(R.id.roleImageView);
        }

        /**
         * On Bind
         * @param supervisedChildrenEntity
         */
        @Override
        public void bind(SupervisedChildrenEntity supervisedChildrenEntity) {
            super.bind(supervisedChildrenEntity);

            final KidEntity kidEntity = supervisedChildrenEntity.getKid();

            if (kidEntity.getProfileImage() != null &&
                    !kidEntity.getProfileImage().isEmpty())
                picasso.load(kidEntity.getProfileImage())
                    .error(R.drawable.kid_default_image)
                    .placeholder(R.drawable.kid_default_image)
                    .into(childImage);
            else
                childImage.setImageResource(R.drawable.kid_default_image);

            // Set Kid name
            childName.setText(kidEntity.getFullName());

            // Set School Name
            schoolName.setText(kidEntity.getSchool().getName());

            switch (supervisedChildrenEntity.getGuardianRolesEnum()) {
                case ADMIN:
                    roleImageView.setImageResource(R.drawable.crown_solid_white);
                    break;
                case PARENTAL_CONTROL_RULE_EDITOR:
                    roleImageView.setImageResource(R.drawable.child_white);
                    break;
                case DATA_VIEWER:
                    roleImageView.setImageResource(R.drawable.eye_solid_white);
                    break;
            }


        }

    }
}
