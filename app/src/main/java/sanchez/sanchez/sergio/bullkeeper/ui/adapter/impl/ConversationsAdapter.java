package sanchez.sanchez.sergio.bullkeeper.ui.adapter.impl;

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
import sanchez.sanchez.sergio.domain.models.ConversationEntity;
import sanchez.sanchez.sergio.domain.models.PersonEntity;

/**
 * Conversation Adapter
 */
public final class ConversationsAdapter extends SupportRecyclerViewAdapter<ConversationEntity>{

    /**
     * Picasso
     */
    private final Picasso picasso;

    /**
     * Self User Id
     */
    private final String selfUserId;

    /**
     * Conversation Adapter
     * @param context
     * @param data
     * @param picasso
     * @param selfUserId
     */
    public ConversationsAdapter(final Context context, final ArrayList<ConversationEntity> data,
                                final Picasso picasso, final String selfUserId) {
        super(context, data);
        this.picasso = picasso;
        this.selfUserId = selfUserId;
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
        View view = inflater.inflate(R.layout.conversation_item_layout, viewGroup, false);
        return new ConversationViewHolder(view);
    }

    /**
     * Conversation View Holder
     */
    public final class ConversationViewHolder extends
            SupportItemSwipedViewHolder<ConversationEntity> {

        private ImageView userImageImageView;
        private TextView userNameTextView, lastMessageTextView, unreadMessagesCountTextView;


        /**
         * School View Holder
         * @param itemView
         */
        public ConversationViewHolder(final View itemView) {
            super(itemView);
            this.userImageImageView = itemView.findViewById(R.id.userImage);
            this.lastMessageTextView = itemView.findViewById(R.id.lastMessage);
            this.userNameTextView = itemView.findViewById(R.id.userName);
            this.unreadMessagesCountTextView = itemView.findViewById(R.id.unreadMessagesCount);
        }

        /**
         * On Bind
         * @param conversationEntity
         */
        @Override
        public void bind(final ConversationEntity conversationEntity) {
            super.bind(conversationEntity);

            final PersonEntity targetUser = selfUserId.equals(conversationEntity
                    .getMemberOne().getIdentity()) ? conversationEntity.getMemberTwo()
                        : conversationEntity.getMemberOne();

            if(targetUser.getProfileImage() != null &&
                    !targetUser.getProfileImage().isEmpty()) {

                picasso.load(targetUser.getProfileImage())
                        .placeholder(R.drawable.user_default)
                        .error(R.drawable.user_default)
                        .into(userImageImageView);
            } else {
                userImageImageView.setImageResource(R.drawable.user_default);
            }

            userNameTextView.setText(targetUser.getFullName());
            lastMessageTextView.setText(conversationEntity.getLastMessage());

            if(conversationEntity.getUnreadMessages() > 0) {
                unreadMessagesCountTextView.setVisibility(View.VISIBLE);
                unreadMessagesCountTextView.setText(String.valueOf(
                        conversationEntity.getUnreadMessages()));
            }

        }

    }

}
