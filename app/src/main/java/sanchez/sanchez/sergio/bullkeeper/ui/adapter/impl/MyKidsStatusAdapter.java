package sanchez.sanchez.sergio.bullkeeper.ui.adapter.impl;

import android.annotation.SuppressLint;
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
import sanchez.sanchez.sergio.domain.models.SonEntity;

/**
 * My Kids Home Adapter
 */
public final class MyKidsStatusAdapter extends SupportRecyclerViewAdapter<SonEntity>{

    private OnMyKidsListener listener;

    /**
     *
     * @param context
     * @param data
     */
    public MyKidsStatusAdapter(Context context, ArrayList<SonEntity> data) {
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
        View view = inflater.inflate(R.layout.my_kids_status_item_layout, viewGroup, false);
        return new MyKidsViewHolder(view);
    }



    /**
     * My Kids View Holder
     */
    public class MyKidsViewHolder
            extends SupportItemViewHolder<SonEntity> {

        private ImageView childImage;
        private TextView childName;


        MyKidsViewHolder(View itemView) {
            super(itemView);

            childImage = itemView.findViewById(R.id.childImage);
            childName = itemView.findViewById(R.id.childName);

        }

        /**
         * On Bind
         * @param sonEntity
         */
        @SuppressLint("ClickableViewAccessibility")
        @Override
        public void bind(SonEntity sonEntity){
            super.bind(sonEntity);


            // Set Child Image
            Picasso.with(context).load(sonEntity.getProfileImage())
                    .placeholder(R.drawable.user_default)
                    .error(R.drawable.user_default)
                    .into(childImage);


            itemView.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    if(listener != null)
                        listener.onDetailActionClicked(getItemByAdapterPosition(getAdapterPosition()));
                }
            });

            childName.setText(sonEntity.getFullName());
        }
    }

    /**
     * On My Kids Listener
     */
    public interface OnMyKidsListener {

        /**
         * On Detail Action Clicked
         * @param sonEntity
         */
        void onDetailActionClicked(final SonEntity sonEntity);
    }
}
