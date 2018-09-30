package sanchez.sanchez.sergio.bullkeeper.ui.adapter.impl;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import java.util.ArrayList;
import sanchez.sanchez.sergio.bullkeeper.R;
import sanchez.sanchez.sergio.bullkeeper.ui.adapter.SupportRecyclerViewAdapter;
import sanchez.sanchez.sergio.domain.models.SchoolEntity;

/**
 * School Adapter
 */
public final class SchoolAdapter extends SupportRecyclerViewAdapter<SchoolEntity>{

    private OnSchoolListener schoolListener;

    /**
     *
     * @param context
     * @param data
     */
    public SchoolAdapter(Context context, ArrayList<SchoolEntity> data) {
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
        View view = inflater.inflate(R.layout.school_item_layout, viewGroup, false);
        return new SchoolViewHolder(context, view);
    }

    /**
     * Set School Listener
     * @param schoolListener
     */
    public void setSchoolListener(OnSchoolListener schoolListener) {
        this.schoolListener = schoolListener;
    }

    public interface OnSchoolListener {

        /**
         * On Show School Detail
         * @param schoolEntity
         */
        void onShowSchoolDetail(final SchoolEntity schoolEntity);
    }

    /**
     * School View Holder
     */
    public final class SchoolViewHolder extends
            SupportItemSwipedViewHolder<SchoolEntity> {

        private Context context;
        private TextView schoolName, schoolResidence;
        private ImageView showSchoolDetail;

        /**
         * School View Holder
         * @param context
         * @param itemView
         */
        public SchoolViewHolder(final Context context, final View itemView) {
            super(itemView);
            this.context = context;
            this.schoolName = itemView.findViewById(R.id.schoolName);
            this.schoolResidence = itemView.findViewById(R.id.schoolResidence);
            this.showSchoolDetail = itemView.findViewById(R.id.showSchoolDetail);
        }

        /**
         * On Bind
         * @param schoolEntity
         */
        @Override
        public void bind(final SchoolEntity schoolEntity) {
            super.bind(schoolEntity);
            if(hasHighlightText())
                schoolName.setText(getSpannableString(schoolEntity.getName()));
            else
                schoolName.setText(schoolEntity.getName());

            schoolResidence.setText(
                    String.format(context.getString(R.string.search_school_location),
                            schoolEntity.getResidence(), schoolEntity.getProvince()));

            showSchoolDetail.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    if(schoolListener != null)
                        schoolListener.onShowSchoolDetail(schoolEntity);
                }
            });
        }

    }

}
