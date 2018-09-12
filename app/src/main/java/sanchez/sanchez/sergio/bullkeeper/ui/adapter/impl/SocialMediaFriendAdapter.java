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
import sanchez.sanchez.sergio.domain.models.SocialMediaFriendEntity;
import sanchez.sanchez.sergio.bullkeeper.R;
import sanchez.sanchez.sergio.bullkeeper.ui.adapter.SupportRecyclerViewAdapter;

/**
 * Social Media Friends Adapter
 */
public final class SocialMediaFriendAdapter extends SupportRecyclerViewAdapter<SocialMediaFriendEntity> {


    private OnSocialMediaFriendsViewListener listener;

    /**
     * @param context
     * @param data
     */
    public SocialMediaFriendAdapter(Context context, ArrayList<SocialMediaFriendEntity> data) {
        super(context, data);
        hasHeader = true;
    }

    /**
     * Set Listener
     * @param listener
     */
    public void setListener(OnSocialMediaFriendsViewListener listener) {
        this.listener = listener;
    }

    /**
     * On Create Item View Holder
     * @param viewGroup
     * @return
     */
    @Override
    protected RecyclerView.ViewHolder onCreateItemViewHolder(ViewGroup viewGroup) {
        View view = inflater.inflate(R.layout.social_media_friend_item_layout, viewGroup, false);
        return new SocialMediaFriendsViewHolder(context, view);
    }

    /**
     * On Create Header View Holder
     * @param viewGroup
     * @return
     */
    @Override
    protected RecyclerView.ViewHolder onCreateHeaderViewHolder(ViewGroup viewGroup) {
        final View view = inflater.inflate(R.layout.social_media_friend_layout_header, viewGroup, false);
        return new SocialMediaFriendHeaderViewHolder(view);
    }


    /**
     * On Social Media Friends Listener
     */
    public interface OnSocialMediaFriendsViewListener {

        /**
         * On Show Info
         */
        void onShowInfo();
    }

    /**
     * Social Media Friends View Holder
     */
    public final class SocialMediaFriendsViewHolder extends
            SupportRecyclerViewAdapter<SocialMediaFriendEntity>
                    .SupportItemViewHolder<SocialMediaFriendEntity> {

        private Context context;

        private ImageView socialMediaIcon, friendProfileImage;
        private TextView friendMessage, friendValue;

        /**
         * Alerts View Holder
         * @param context
         * @param itemView
         */
        public SocialMediaFriendsViewHolder(final Context context, final View itemView) {
            super(itemView);

            this.context = context;
            this.socialMediaIcon = itemView.findViewById(R.id.socialMediaIcon);
            this.friendProfileImage = itemView.findViewById(R.id.friendProfileImage);
            this.friendMessage = itemView.findViewById(R.id.friendName);
            this.friendValue = itemView.findViewById(R.id.friendValue);
        }

        /**
         * On Bind
         * @param socialMediaFriendEntity
         */
        @Override
        public void bind(SocialMediaFriendEntity socialMediaFriendEntity) {
            super.bind(socialMediaFriendEntity);

            switch (socialMediaFriendEntity.getSocialMediaEnum()) {

                case INSTAGRAM:

                    socialMediaIcon.setImageDrawable(ContextCompat.getDrawable(context,
                            R.drawable.instagram_brands_solid_cyan));

                    break;

                case YOUTUBE:

                    socialMediaIcon.setImageDrawable(ContextCompat.getDrawable(context,
                            R.drawable.youtube_brands_solid_cyan));

                    break;

                case FACEBOOK:

                    socialMediaIcon.setImageDrawable(ContextCompat.getDrawable(context,
                            R.drawable.facebook_brand_solid_cyan));

                    break;


            }


            // Set Child Image
            Picasso.with(context).load("https://avatars3.githubusercontent.com/u/831538?s=460&v=4")
                    .placeholder(R.drawable.user_default)
                    .error(R.drawable.user_default)
                    .noFade()
                    .into(friendProfileImage);
        }

        public ImageView getSocialMediaIcon() {
            return socialMediaIcon;
        }

        public ImageView getFriendProfileImage() {
            return friendProfileImage;
        }

        public TextView getFriendMessage() {
            return friendMessage;
        }
    }


    /**
     * Alerts Header View Holder
     */
    public class SocialMediaFriendHeaderViewHolder extends SupportHeaderViewHolder {

        private ImageView showInfo;

        public SocialMediaFriendHeaderViewHolder(View itemView) {
            super(itemView);

            this.showInfo = itemView.findViewById(R.id.infoBtn);

            showInfo.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    if (listener != null)
                        listener.onShowInfo();
                }
            });

        }
    }
}
