package sanchez.sanchez.sergio.bullkeeper.ui.adapter.impl;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;
import com.squareup.picasso.Picasso;
import java.util.ArrayList;
import java.util.Locale;

import de.hdodenhof.circleimageview.CircleImageView;
import sanchez.sanchez.sergio.bullkeeper.R;
import sanchez.sanchez.sergio.bullkeeper.ui.adapter.SupportRecyclerViewAdapter;
import sanchez.sanchez.sergio.domain.models.GuardianEntity;
import sanchez.sanchez.sergio.domain.models.GuardianRolesEnum;
import sanchez.sanchez.sergio.domain.models.KidGuardianEntity;

/**
 * Kid Guardians Adapter
 */
public final class KidGuardiansAdapter extends SupportRecyclerViewAdapter<KidGuardianEntity> {


    /**
     * Picasso
     */
    private final Picasso picasso;

    /**
     * Active User Identity
     */
    private final String activeUserIdentity;

    /**
     *
     * @param context
     * @param data
     * @param picasso
     */
    public KidGuardiansAdapter(final Context context, final ArrayList<KidGuardianEntity> data,
                               final Picasso picasso, final String activeUserIdentity) {
        super(context, data);
        this.picasso = picasso;
        this.activeUserIdentity = activeUserIdentity;
    }

    /**
     * On Create Item View Holder
     * @param viewGroup
     * @return
     */
    @Override
    protected RecyclerView.ViewHolder onCreateItemViewHolder(ViewGroup viewGroup) {
        View view = inflater.inflate(R.layout.kid_guardian_item_layout, viewGroup, false);
        return new KidGuardianViewHolder(view);
    }


    /**
     * Kid Guardian View Holder
     */
    public class KidGuardianViewHolder
            extends SupportItemSwipedViewHolder<KidGuardianEntity>{


        private ImageView adminRole, editRulesRole, viewerRole;
        private TextView guardianName;
        private CircleImageView guardianImageView;

        /**
         *
         * @param itemView
         */
        KidGuardianViewHolder(View itemView) {
            super(itemView);
            adminRole = itemView.findViewById(R.id.adminRole);
            editRulesRole = itemView.findViewById(R.id.editRulesRole);
            viewerRole = itemView.findViewById(R.id.viewerRole);
            guardianName = itemView.findViewById(R.id.guardianName);
            guardianImageView = itemView.findViewById(R.id.guardianImageView);
        }

        /**
         * On Bind
         * @param kidGuardianEntity
         */
        @Override
        public void bind(final KidGuardianEntity kidGuardianEntity) {
            super.bind(kidGuardianEntity);

            // Set Guardian Full name
            final GuardianEntity guardianEntity = kidGuardianEntity.getGuardian();

            if (guardianEntity.getIdentity().equals(activeUserIdentity)) {
                guardianName.setText(String.format(Locale.getDefault(),
                        context.getString(R.string.kid_guardian_active_user),
                        guardianEntity.getFullName()));

            } else {

                if (kidGuardianEntity.isConfirmed())
                    guardianName.setText(guardianEntity.getFullName());
                else
                    guardianName.setText(String.format(Locale.getDefault(),
                            context.getString(R.string.kid_guardian_user_no_confirmed),
                            guardianEntity.getFullName()));
            }

            // Set Guardian Profile Image
            if(guardianEntity.getProfileImage() != null &&
                    !guardianEntity.getProfileImage().isEmpty()){

                picasso.load(guardianEntity.getProfileImage())
                        .error(R.drawable.parent_default)
                        .placeholder(R.drawable.parent_default)
                        .into(guardianImageView);

            }


            // Guardian Role
            switch (kidGuardianEntity.getRole()) {

                case ADMIN:

                    adminRole.setImageResource(R.drawable.crown_solid_white);
                    viewerRole.setImageResource(R.drawable.eye_solid_cyan);
                    editRulesRole.setImageResource(R.drawable.child_solid_cyan);

                    break;

                case PARENTAL_CONTROL_RULE_EDITOR:

                    adminRole.setImageResource(R.drawable.crown_solid_cyan);
                    viewerRole.setImageResource(R.drawable.eye_solid_cyan);
                    editRulesRole.setImageResource(R.drawable.child_white);

                    break;

                case DATA_VIEWER:

                    adminRole.setImageResource(R.drawable.crown_solid_cyan);
                    viewerRole.setImageResource(R.drawable.eye_solid_white);
                    editRulesRole.setImageResource(R.drawable.child_solid_cyan);

                    break;
            }

            if(!guardianEntity.getIdentity().equals(activeUserIdentity)) {

                // Admin Role Listener
                adminRole.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        kidGuardianEntity.setRole(GuardianRolesEnum.ADMIN);
                        adminRole.setImageResource(R.drawable.crown_solid_white);
                        viewerRole.setImageResource(R.drawable.eye_solid_cyan);
                        editRulesRole.setImageResource(R.drawable.child_solid_cyan);
                    }
                });

                // Viewer Role Listener
                viewerRole.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        kidGuardianEntity.setRole(GuardianRolesEnum.DATA_VIEWER);
                        adminRole.setImageResource(R.drawable.crown_solid_cyan);
                        viewerRole.setImageResource(R.drawable.eye_solid_white);
                        editRulesRole.setImageResource(R.drawable.child_solid_cyan);
                    }
                });

                // Edit Rules Role Listener
                editRulesRole.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        kidGuardianEntity.setRole(GuardianRolesEnum.PARENTAL_CONTROL_RULE_EDITOR);
                        adminRole.setImageResource(R.drawable.crown_solid_cyan);
                        viewerRole.setImageResource(R.drawable.eye_solid_cyan);
                        editRulesRole.setImageResource(R.drawable.child_white);
                    }
                });
            }

        }

    }
}
