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

import sanchez.sanchez.sergio.bullkeeper.R;
import sanchez.sanchez.sergio.bullkeeper.ui.adapter.SupportRecyclerViewAdapter;
import sanchez.sanchez.sergio.domain.models.GuardianEntity;

/**
 * Guardians Adapter
 */
public final class GuardiansAdapter extends SupportRecyclerViewAdapter<GuardianEntity>{

    private final Picasso picasso;

    /**
     *
     * @param context
     * @param data
     */
    public GuardiansAdapter(Context context, ArrayList<GuardianEntity> data, final Picasso picasso) {
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
        View view = inflater.inflate(R.layout.guardian_item_layout, viewGroup, false);
        return new GuardianViewHolder(context, view);
    }

    /**
     * Guardian View Holder
     */
    public final class GuardianViewHolder extends
            SupportItemSwipedViewHolder<GuardianEntity> {

        private Context context;
        private TextView guardianName, guardianAge;
        private ImageView guardianImage;

        /**
         * School View Holder
         * @param context
         * @param itemView
         */
        public GuardianViewHolder(final Context context, final View itemView) {
            super(itemView);
            this.context = context;
            this.guardianName = itemView.findViewById(R.id.guardianName);
            this.guardianAge = itemView.findViewById(R.id.guardianAge);
            this.guardianImage = itemView.findViewById(R.id.guardianImage);
        }

        /**
         * On Bind
         * @param guardianEntity
         */
        @Override
        public void bind(final GuardianEntity guardianEntity) {
            super.bind(guardianEntity);

            if(guardianEntity.getProfileImage() != null &&
                    !guardianEntity.getProfileImage().isEmpty())
                picasso.load(guardianEntity.getProfileImage())
                    .error(R.drawable.parent_default)
                    .placeholder(R.drawable.parent_default)
                    .into(guardianImage);
            else
                guardianImage.setImageResource(R.drawable.parent_default);


            if(hasHighlightText())
                guardianName.setText(getSpannableString(guardianEntity.getFullName()));
            else
                guardianName.setText(guardianEntity.getFullName());


            guardianAge.setText(
                    String.format(Locale.getDefault(),
                            context.getString(R.string.search_guardian_age),
                            String.valueOf(guardianEntity.getAge()))
            );
        }

    }

}
