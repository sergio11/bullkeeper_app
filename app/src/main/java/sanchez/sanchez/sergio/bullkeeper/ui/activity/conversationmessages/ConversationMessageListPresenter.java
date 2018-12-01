package sanchez.sanchez.sergio.bullkeeper.ui.activity.conversationmessages;

import android.support.annotation.NonNull;

import net.grandcentrix.thirtyinch.TiPresenter;

import javax.inject.Inject;

/**
 * Conversation Message List Presenter
 */
public final class ConversationMessageListPresenter extends TiPresenter<IConversationMessageListView> {


    @Inject
    public ConversationMessageListPresenter() {
        super();
    }

    /**
     * On Attach View
     * @param view
     */
    @Override
    protected void onAttachView(@NonNull final IConversationMessageListView view) {
        super.onAttachView(view);
    }
}
