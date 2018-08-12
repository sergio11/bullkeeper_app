package sanchez.sanchez.sergio.masom_app.ui.adapter.impl;

import android.annotation.SuppressLint;
import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.MotionEvent;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageButton;
import android.widget.ImageView;
import com.squareup.picasso.Picasso;
import java.util.ArrayList;
import sanchez.sanchez.sergio.domain.models.SonEntity;
import sanchez.sanchez.sergio.masom_app.R;
import sanchez.sanchez.sergio.masom_app.ui.adapter.SupportRecyclerViewAdapter;
import sanchez.sanchez.sergio.masom_app.ui.images.CircleTransform;

/**
 * My Kids Adapter
 */
public final class MyKidsAdapter extends SupportRecyclerViewAdapter<SonEntity>{

    private OnMyKidsListener listener;

    /**
     *
     * @param context
     * @param data
     */
    public MyKidsAdapter(Context context, ArrayList<SonEntity> data) {
        super(context, data);
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
            extends SupportItemViewHolder<SonEntity> {

        private ImageView childImage;
        private ImageButton resultsAction, alertsAction, relationsAction, profileAction;


        MyKidsViewHolder(View itemView) {
            super(itemView);

            childImage = itemView.findViewById(R.id.childImage);
            resultsAction = itemView.findViewById(R.id.resultsAction);
            alertsAction = itemView.findViewById(R.id.alertsAction);
            relationsAction = itemView.findViewById(R.id.relationsAction);
            profileAction = itemView.findViewById(R.id.profileAction);

        }

        /**
         * On Bind
         * @param sonEntity
         */
        @SuppressLint("ClickableViewAccessibility")
        @Override
        public void bind(SonEntity sonEntity){
            super.bind(sonEntity);

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
                    if(listener != null)
                        listener.onResultsActionClicked(getItemByAdapterPosition(getAdapterPosition()));
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
                    if(listener != null)
                        listener.onAlertsActionClicked(getItemByAdapterPosition(getAdapterPosition()));
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
                    if(listener != null)
                        listener.onRelationsActionClicked(getItemByAdapterPosition(getAdapterPosition()));
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
                    if(listener != null)
                        listener.onProfileActionClicked(getItemByAdapterPosition(getAdapterPosition()));
                }
            });


            // Set Child Image
            Picasso.with(context).load("https://avatars3.githubusercontent.com/u/831538?s=460&v=4")
                    .placeholder(R.drawable.user_default)
                    .error(R.drawable.user_default)
                    .transform(new CircleTransform())
                    .into(childImage);
        }
    }


    public interface OnMyKidsListener {

        /**
         * On Results Action Clicked
         * @param sonEntity
         */
        void onResultsActionClicked(final SonEntity sonEntity);

        /**
         * On Alerts Action Clicked
         * @param sonEntity
         */
        void onAlertsActionClicked(final SonEntity sonEntity);

        /**
         * On Relations Action Clicked
         * @param sonEntity
         */
        void onRelationsActionClicked(final SonEntity sonEntity);

        /**
         * On Profile Action Clicked
         * @param sonEntity
         */
        void onProfileActionClicked(final SonEntity sonEntity);
    }
}