package sanchez.sanchez.sergio.bullkeeper.ui.activity.conversationslist;

import android.support.annotation.NonNull;
import net.grandcentrix.thirtyinch.TiPresenter;
import javax.inject.Inject;

/**
 * Conversation Presenter
 */
public final class ConversationListPresenter extends TiPresenter<IConversationListView> {


    @Inject
    public ConversationListPresenter() {
        super();
    }

    /**
     * On Attach View
     * @param view
     */
    @Override
    protected void onAttachView(@NonNull final IConversationListView view) {
        super.onAttachView(view);
    }
}
