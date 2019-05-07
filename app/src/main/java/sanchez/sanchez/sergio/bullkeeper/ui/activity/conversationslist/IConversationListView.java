package sanchez.sanchez.sergio.bullkeeper.ui.activity.conversationslist;

import sanchez.sanchez.sergio.bullkeeper.core.ui.ISupportLCEView;
import sanchez.sanchez.sergio.domain.models.ConversationEntity;

/**
 * Conversation View
 */
interface IConversationListView extends ISupportLCEView<ConversationEntity> {

    /**
     * on Conversation Deleted Successfully
     */
    void onConversationDeletedSuccessfully();
}
