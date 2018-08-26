package sanchez.sanchez.sergio.bullkeeper.ui.adapter.impl;

import android.content.Context;
import android.support.v4.content.ContextCompat;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.TextView;

import com.squareup.picasso.Picasso;

import java.util.ArrayList;

import de.hdodenhof.circleimageview.CircleImageView;
import sanchez.sanchez.sergio.domain.models.CommentEntity;
import sanchez.sanchez.sergio.bullkeeper.R;
import sanchez.sanchez.sergio.bullkeeper.ui.adapter.SupportRecyclerViewAdapter;

/**
 * Last Alerts Adapter
 */
public final class CommentsAdapter extends SupportRecyclerViewAdapter<CommentEntity>{

    /**
     * On Comments View Listener
     */
    private OnCommentsViewListener onCommentsViewListener;

    /**
     *
     * @param context
     * @param data
     */
    public CommentsAdapter(Context context, ArrayList<CommentEntity> data) {
        super(context, data);
        // enable header
        hasHeader = true;
        hasFooter = false;

    }

    /**
     * On Create Item View Holder
     * @param viewGroup
     * @return
     */
    @Override
    protected RecyclerView.ViewHolder onCreateItemViewHolder(ViewGroup viewGroup) {
        View view = inflater.inflate(R.layout.comment_item_layout, viewGroup, false);
        return new CommentsViewHolder(context, view);
    }

    /**
     * On Create Header View Holder
     * @param viewGroup
     * @return
     */
    @Override
    protected RecyclerView.ViewHolder onCreateHeaderViewHolder(ViewGroup viewGroup) {
        final View view = inflater.inflate(R.layout.comments_header_layout, viewGroup, false);
        return new CommentsHeaderViewHolder(view);
    }

    /**
     * On Create Footer View Holder
     * @param viewGroup
     * @return
     */
    @Override
    protected RecyclerView.ViewHolder onCreateFooterViewHolder(ViewGroup viewGroup) {
        final View view = inflater.inflate(R.layout.progress_item, viewGroup, false);
        return new SupportFooterViewHolder(view);
    }

    /**
     * Set On Alerts View Listener
     * @param onCommentsViewListener
     */
    public void setOnCommentsViewListener(OnCommentsViewListener onCommentsViewListener){
        this.onCommentsViewListener = onCommentsViewListener;
    }

    /**
     * On Comments View Listener
     */
    public interface OnCommentsViewListener {

        /**
         * On Filter Alerts
         */
        void onFilterAlerts();
    }

    /**
     * Comments View Holder
     */
    public final class CommentsViewHolder extends
            SupportItemSwipedViewHolder<CommentEntity> {

        private Context context;

        private CircleImageView authorImage;
        private TextView authorName, commentMessage;
        private ImageView commentSocialMedia;

        /**
         * Alerts View Holder
         * @param context
         * @param itemView
         */
        public CommentsViewHolder(final Context context, final View itemView) {
            super(itemView);
            this.context = context;
            this.authorImage = itemView.findViewById(R.id.authorImage);
            this.authorName = itemView.findViewById(R.id.authorName);
            this.commentMessage = itemView.findViewById(R.id.commentMessage);
            this.commentSocialMedia = itemView.findViewById(R.id.commentSocialMedia);
        }

        /**
         * On Bind
         * @param commentEntity
         */
        @Override
        public void bind(CommentEntity commentEntity) {
            super.bind(commentEntity);

            // Set Author Image
            Picasso.with(context).load("https://avatars3.githubusercontent.com/u/831538?s=460&v=4")
                    .placeholder(R.drawable.user_default)
                    .error(R.drawable.user_default)
                    .noFade()
                    .into(authorImage);


            switch (commentEntity.getSocialMedia()) {

                case FACEBOOK:

                    commentSocialMedia.setImageDrawable(ContextCompat.getDrawable(context,
                            R.drawable.facebook_brand_solid_cyan));

                    break;

                case YOUTUBE:

                    commentSocialMedia.setImageDrawable(ContextCompat.getDrawable(context,
                            R.drawable.youtube_brands_solid_cyan));

                    break;

                case INSTAGRAM:

                    commentSocialMedia.setImageDrawable(ContextCompat.getDrawable(context,
                            R.drawable.instagram_brands_solid_cyan));

                    break;

            }

        }



    }


    /**
     * Comments Header View Holder
     */
    public class CommentsHeaderViewHolder extends SupportHeaderViewHolder {

        private ImageButton filterAlerts;

        public CommentsHeaderViewHolder(View itemView) {
            super(itemView);

            this.filterAlerts = itemView.findViewById(R.id.filterAlerts);


            filterAlerts.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    if (onCommentsViewListener != null)
                        onCommentsViewListener.onFilterAlerts();
                }
            });

        }

        public ImageButton getFilterAlerts() {
            return filterAlerts;
        }
    }


}
