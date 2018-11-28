package sanchez.sanchez.sergio.bullkeeper.ui.adapter.impl;

import android.annotation.SuppressLint;
import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.MotionEvent;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.TextView;

import com.squareup.picasso.Picasso;
import java.util.ArrayList;
import java.util.Locale;

import sanchez.sanchez.sergio.domain.models.GuardianRolesEnum;
import sanchez.sanchez.sergio.domain.models.KidEntity;
import sanchez.sanchez.sergio.bullkeeper.R;
import sanchez.sanchez.sergio.bullkeeper.ui.adapter.SupportRecyclerViewAdapter;
import sanchez.sanchez.sergio.domain.models.SupervisedChildrenEntity;

/**
 * My Kids Adapter
 */
public final class MyKidsAdapter extends SupportRecyclerViewAdapter<SupervisedChildrenEntity>{

    /**
     * Listener
     */
    private OnMyKidsListener listener;

    /**
     * Picasso
     */
    private final Picasso picasso;

    /**
     *
     * @param context
     * @param data
     * @param picasso
     */
    public MyKidsAdapter(Context context, ArrayList<SupervisedChildrenEntity> data, final Picasso picasso) {
        super(context, data);
        this.picasso = picasso;
    }


    /**
     * Set Listener
     * @param listener
     */
    public void setOnMyKidsListenerListener(OnMyKidsListener listener) {
        this.listener = listener;
    }

    /**
     * On Create Item View Holder
     * @param viewGroup
     * @return
     */
    @Override
    protected RecyclerView.ViewHolder onCreateItemViewHolder(ViewGroup viewGroup) {
        View view = inflater.inflate(R.layout.my_kids_item_layout, viewGroup, false);
        return new MyKidsViewHolder(view);
    }



    /**
     * My Kids View Holder
     */
    public class MyKidsViewHolder
            extends SupportItemViewHolder<SupervisedChildrenEntity> {

        private ImageView childImage;
        private ImageButton resultsAction, alertsAction, relationsAction, profileAction;
        private TextView childName, schoolName, terminalsTextView;


        MyKidsViewHolder(View itemView) {
            super(itemView);

            childImage = itemView.findViewById(R.id.childImage);
            resultsAction = itemView.findViewById(R.id.resultsAction);
            alertsAction = itemView.findViewById(R.id.alertsAction);
            relationsAction = itemView.findViewById(R.id.relationsAction);
            profileAction = itemView.findViewById(R.id.profileAction);
            childName = itemView.findViewById(R.id.childName);
            schoolName = itemView.findViewById(R.id.schoolName);
            terminalsTextView = itemView.findViewById(R.id.terminalsTextView);

        }

        /**
         * On Bind
         * @param supervisedChildrenEntity
         */
        @SuppressLint("ClickableViewAccessibility")
        @Override
        public void bind(final SupervisedChildrenEntity supervisedChildrenEntity){
            super.bind(supervisedChildrenEntity);

            final KidEntity kidEntity = supervisedChildrenEntity.getKid();

            // Set Child Name
            childName.setText(kidEntity.getFullName());
            // Set School Name
            schoolName.setText(kidEntity.getSchool().getName());

            // Check Terminals linked
            if(!kidEntity.getTerminalEntities().isEmpty()) {
                terminalsTextView.setText(String.format(Locale.getDefault(),
                        context.getString(R.string.has_terminals_linked),
                        kidEntity.getTerminalEntities().size()));
            } else {
                terminalsTextView.setText(R.string.not_have_any_linked_devices);
            }

            // Results Action
            resultsAction.setOnTouchListener(new View.OnTouchListener() {
                @Override
                public boolean onTouch(View view, MotionEvent motionEvent) {
                    switch(motionEvent.getAction()) {
                        case MotionEvent.ACTION_DOWN:
                            resultsAction.setImageResource(R.drawable.chart_bar_white);
                            break;
                        default:
                            resultsAction.setImageResource(R.drawable.chart_bar_cyan);
                    }
                    return false;
                }
            });

            resultsAction.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    if(listener != null) {
                        final SupervisedChildrenEntity supervisedChildren = getItemByAdapterPosition(getAdapterPosition());
                        listener.onResultsActionClicked(supervisedChildren.getKid(), supervisedChildren.getGuardianRolesEnum());
                    }
                }
            });

            // Alerts Action
            alertsAction.setOnTouchListener(new View.OnTouchListener() {
                @Override
                public boolean onTouch(View view, MotionEvent motionEvent) {
                    switch(motionEvent.getAction()) {
                        case MotionEvent.ACTION_DOWN:
                            alertsAction.setImageResource(R.drawable.alert_white);
                            break;
                        default:
                            alertsAction.setImageResource(R.drawable.alert_cyan);
                    }
                    return false;
                }
            });

            alertsAction.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    if(listener != null) {
                        final SupervisedChildrenEntity supervisedChildren = getItemByAdapterPosition(getAdapterPosition());
                        listener.onAlertsActionClicked(supervisedChildren.getKid(), supervisedChildren.getGuardianRolesEnum());
                    }
                }
            });

            // Relations Action
            relationsAction.setOnTouchListener(new View.OnTouchListener() {
                @Override
                public boolean onTouch(View view, MotionEvent motionEvent) {
                    switch(motionEvent.getAction()) {
                        case MotionEvent.ACTION_DOWN:
                            relationsAction.setImageResource(R.drawable.link_solid_white);
                            break;
                        default:
                            relationsAction.setImageResource(R.drawable.link_solid_cyan);
                    }
                    return false;
                }
            });

            relationsAction.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    if(listener != null) {
                        final SupervisedChildrenEntity supervisedChildren = getItemByAdapterPosition(getAdapterPosition());
                        listener.onRelationsActionClicked(supervisedChildren.getKid(), supervisedChildren.getGuardianRolesEnum());
                    }
                }
            });

            // Profile Action
            profileAction.setOnTouchListener(new View.OnTouchListener() {
                @Override
                public boolean onTouch(View view, MotionEvent motionEvent) {
                    switch(motionEvent.getAction()) {
                        case MotionEvent.ACTION_DOWN:
                            profileAction.setImageResource(R.drawable.user_edit_white);
                            break;
                        default:
                            profileAction.setImageResource(R.drawable.user_edit_cyan);
                    }
                    return false;
                }
            });

            profileAction.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    if(listener != null) {
                        final SupervisedChildrenEntity supervisedChildren = getItemByAdapterPosition(getAdapterPosition());
                        listener.onProfileActionClicked(supervisedChildren.getKid(), supervisedChildren.getGuardianRolesEnum());
                    }
                }
            });

            if(kidEntity.getProfileImage() != null &&
                    !kidEntity.getProfileImage().isEmpty())
                // Set Child Image
                picasso.load(kidEntity.getProfileImage())
                        .placeholder(R.drawable.kid_default_image)
                        .error(R.drawable.kid_default_image)
                        .into(childImage);
            else
                childImage.setImageResource(R.drawable.kid_default_image);


            childImage.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    if(listener != null) {
                        final SupervisedChildrenEntity supervisedChildren = getItemByAdapterPosition(getAdapterPosition());
                        listener.onDetailActionClicked(supervisedChildren.getKid(), supervisedChildren.getGuardianRolesEnum());
                    }
                }
            });
        }
    }

    /**
     * On My Kids Listener
     */
    public interface OnMyKidsListener {

        /**
         * On Detail Action Clicked
         * @param kidEntity
         * @param role
         */
        void onDetailActionClicked(final KidEntity kidEntity, final GuardianRolesEnum role);

        /**
         * On Results Action Clicked
         * @param kidEntity
         * @param role
         */
        void onResultsActionClicked(final KidEntity kidEntity, final GuardianRolesEnum role);

        /**
         * On Alerts Action Clicked
         * @param kidEntity
         * @param role
         */
        void onAlertsActionClicked(final KidEntity kidEntity, final GuardianRolesEnum role);

        /**
         * On Relations Action Clicked
         * @param kidEntity
         * @param role
         */
        void onRelationsActionClicked(final KidEntity kidEntity, final GuardianRolesEnum role);

        /**
         * On Profile Action Clicked
         * @param kidEntity
         * @param role
         */
        void onProfileActionClicked(final KidEntity kidEntity, final GuardianRolesEnum role);
    }
}
