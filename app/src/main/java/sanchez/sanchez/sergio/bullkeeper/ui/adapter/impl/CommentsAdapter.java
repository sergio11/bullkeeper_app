package sanchez.sanchez.sergio.bullkeeper.ui.adapter.impl;

import android.content.Context;
import android.support.v4.content.ContextCompat;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;
import com.squareup.picasso.Picasso;
import java.util.ArrayList;
import de.hdodenhof.circleimageview.CircleImageView;
import sanchez.sanchez.sergio.domain.models.CommentEntity;
import sanchez.sanchez.sergio.bullkeeper.R;
import sanchez.sanchez.sergio.bullkeeper.ui.adapter.SupportRecyclerViewAdapter;

/**
 * Comments Adapter
 */
public final class CommentsAdapter extends SupportRecyclerViewAdapter<CommentEntity>{

    /**
     * Picasso
     */
    private final Picasso picasso;

    /**
     *
     * @param context
     * @param data
     */
    public CommentsAdapter(final Context context, final ArrayList<CommentEntity> data, final Picasso picasso) {
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
        View view = inflater.inflate(R.layout.comment_item_layout, viewGroup, false);
        return new CommentsViewHolder(context, view);
    }


    /**
     * Comments View Holder
     */
    public final class CommentsViewHolder extends
            SupportItemSwipedViewHolder<CommentEntity> {

        private Context context;

        private CircleImageView authorImage;
        private TextView authorName, commentMessage, commentSince;
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
            this.commentSince = itemView.findViewById(R.id.commentSince);
        }

        /**
         * On Bind
         * @param commentEntity
         */
        @Override
        public void bind(CommentEntity commentEntity) {
            super.bind(commentEntity);


            // Author Photo
            if(commentEntity.getAuthorPhoto() != null &&
                    !commentEntity.getAuthorPhoto().isEmpty())

                // Set Author Image
                picasso.load(commentEntity.getAuthorPhoto())
                        .placeholder(R.drawable.user_default)
                        .error(R.drawable.user_default)
                        .noFade()
                        .into(authorImage);
            else
                authorImage.setImageResource(R.drawable.user_default);


            // Author Name
            authorName.setText(commentEntity.getAuthorName());

            // Comment Message
            commentMessage.setText(commentEntity.getMessage());

            // Comment Since
            commentSince.setText(commentEntity.getExtractedAtSince());

            // Comment Social Media
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

}
