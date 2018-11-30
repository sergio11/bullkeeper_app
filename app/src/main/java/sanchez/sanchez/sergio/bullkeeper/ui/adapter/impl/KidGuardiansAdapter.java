package sanchez.sanchez.sergio.bullkeeper.ui.adapter.impl;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;
import com.squareup.picasso.Picasso;
import java.util.ArrayList;
import de.hdodenhof.circleimageview.CircleImageView;
import sanchez.sanchez.sergio.bullkeeper.R;
import sanchez.sanchez.sergio.bullkeeper.ui.adapter.SupportRecyclerViewAdapter;
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
     *
     * @param context
     * @param data
     * @param picasso
     */
    public KidGuardiansAdapter(final Context context, final ArrayList<KidGuardianEntity> data,
                               final Picasso picasso) {
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
        return new KidGuardianViewHolder(view);
    }


    /**
     * Kid Guardian View Holder
     */
    public class KidGuardianViewHolder
            extends SupportItemSwipedViewHolder<KidGuardianEntity>{


        private ImageView appNotAllowed, appPerScheduled, appAllowed;
        private TextView appInstalledName;
        private CircleImageView appInstalledImage;

        /**
         *
         * @param itemView
         */
        KidGuardianViewHolder(View itemView) {
            super(itemView);
            appNotAllowed = itemView.findViewById(R.id.appNotAllowed);
            appAllowed = itemView.findViewById(R.id.appAllowed);
            appPerScheduled = itemView.findViewById(R.id.appPerScheduled);
            appInstalledName = itemView.findViewById(R.id.appInstalledName);
            appInstalledImage = itemView.findViewById(R.id.appInstalledImage);
        }

        /**
         * On Bind
         * @param kidGuardianEntity
         */
        @Override
        public void bind(final KidGuardianEntity kidGuardianEntity) {
            super.bind(kidGuardianEntity);



        }

    }
}
