package sanchez.sanchez.sergio.bullkeeper.ui.adapter.impl;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;
import com.squareup.picasso.Picasso;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Locale;

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
        private TextView userNameTextView, lastMessageTextView, unreadMessagesCountTextView,
                lastUpdateTextView;


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
            this.lastUpdateTextView = itemView.findViewById(R.id.lastUpdate);
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
                        .placeholder(R.drawable.user_default_inverse)
                        .error(R.drawable.user_default_inverse)
                        .into(userImageImageView);
            } else {
                userImageImageView.setImageResource(R.drawable.user_default_inverse);
            }

            userNameTextView.setText(targetUser.getFullName());

            final SimpleDateFormat sdf = new SimpleDateFormat(context.getString(R.string.date_format),
                    Locale.getDefault());

            lastUpdateTextView.setText(sdf.format(conversationEntity.getUpdateAt()));

            if(selfUserId.equals(conversationEntity
                    .getMemberOne().getIdentity()) ) {

                if(conversationEntity.getLastMessageForMemberOne() != null &&
                        !conversationEntity.getLastMessageForMemberOne().isEmpty())
                    lastMessageTextView.setText(conversationEntity.getLastMessageForMemberOne());
                else if(conversationEntity.getLastMessage() != null &&
                    !conversationEntity.getLastMessage().isEmpty())
                    lastMessageTextView.setText(conversationEntity.getLastMessage());
                else
                    lastMessageTextView.setText(context.getString(R.string.no_messages_found_for_conversation));

                if(conversationEntity.getPendingMessagesForMemberOne() > 0) {
                    unreadMessagesCountTextView.setVisibility(View.VISIBLE);
                    unreadMessagesCountTextView.setText(String.valueOf(
                            conversationEntity.getPendingMessagesForMemberOne()));
                } else {
                    unreadMessagesCountTextView.setVisibility(View.GONE);
                    unreadMessagesCountTextView.setText("-");
                }

            } else {

                if(conversationEntity.getLastMessageForMemberTwo() != null &&
                        !conversationEntity.getLastMessageForMemberTwo().isEmpty())
                    lastMessageTextView.setText(conversationEntity.getLastMessageForMemberTwo());
                else if(conversationEntity.getLastMessage() != null &&
                    !conversationEntity.getLastMessage().isEmpty())
                    lastMessageTextView.setText(conversationEntity.getLastMessage());
                else
                    lastMessageTextView.setText(context.getString(R.string.no_messages_found_for_conversation));


                if(conversationEntity.getPendingMessagesForMemberTwo() > 0) {
                    unreadMessagesCountTextView.setVisibility(View.VISIBLE);
                    unreadMessagesCountTextView.setText(String.valueOf(
                            conversationEntity.getPendingMessagesForMemberTwo()));
                } else {
                    unreadMessagesCountTextView.setVisibility(View.GONE);
                    unreadMessagesCountTextView.setText("-");
                }
            }
        }

    }

}
