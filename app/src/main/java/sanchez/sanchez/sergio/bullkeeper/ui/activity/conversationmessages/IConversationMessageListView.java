package sanchez.sanchez.sergio.bullkeeper.ui.activity.conversationmessages;

import java.util.List;

import sanchez.sanchez.sergio.bullkeeper.core.ui.ISupportView;
import sanchez.sanchez.sergio.domain.models.ConversationEntity;
import sanchez.sanchez.sergio.domain.models.MessageEntity;

/**
 * Conversation Message List View
 */
public interface IConversationMessageListView extends ISupportView {

    /**
     * On Conversation Messages Deleted
     */
    void onConversationMessagesDeleted();

    /**
     * On Conversation Messages Selected Deleted
     */
    void onConversationMessagesSelectedDeleted(final List<String> messageIds);

    /**
     * On Conversation Mesages Loaded
     * @param messageEntities
     */
    void onConversationMessagesLoaded(final List<MessageEntity> messageEntities);

    /**
     * On No Conversation Messages Found
     */
    void onNoConversationMessagesFound();

    /**
     * On Message Added
     * @param messageEntity
     */
    void onMessageAdded(final MessageEntity messageEntity);

    /**
     * On Message Add Errror
     */
    void onMessageAddError();

    /**
     * On Conversation Loaded
     * @param conversationEntity
     */
    void onConversationLoaded(final ConversationEntity conversationEntity);
}
