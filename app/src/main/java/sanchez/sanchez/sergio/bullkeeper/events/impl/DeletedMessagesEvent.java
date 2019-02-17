package sanchez.sanchez.sergio.bullkeeper.events.impl;

import android.content.Intent;
import android.os.Bundle;
import com.fernandocejas.arrow.checks.Preconditions;
import java.util.ArrayList;
import sanchez.sanchez.sergio.bullkeeper.core.events.model.SupportEvent;
import sanchez.sanchez.sergio.bullkeeper.events.handler.IMessageEventVisitor;

/**
 * Deleted Messages Event
 */
public final class DeletedMessagesEvent extends SupportEvent<IMessageEventVisitor> {

    /**
     * Args
     */
    private static final String CONVERSATION_ARG = "CONVERSATION_ARG";
    private static final String MESSAGE_IDS_ARG = "MESSAGE_IDS_ARG";


    private String conversation;
    private ArrayList<String> ids;

    /**
     *
     * @param conversation
     * @param ids
     */
    public DeletedMessagesEvent(final String conversation, final ArrayList<String> ids) {
        this.conversation = conversation;
        this.ids = ids;
    }

    public String getConversation() {
        return conversation;
    }

    public void setConversation(String conversation) {
        this.conversation = conversation;
    }

    public ArrayList<String> getIds() {
        return ids;
    }

    public void setIds(ArrayList<String> ids) {
        this.ids = ids;
    }

    /**
     * To Intent
     * @return
     */
    @Override
    public Intent toIntent() {
        final Intent intent = new Intent(getClass().getCanonicalName());
        final Bundle bundle = new Bundle();
        bundle.putString(CONVERSATION_ARG, conversation);
        bundle.putSerializable(MESSAGE_IDS_ARG, ids);
        intent.putExtras(bundle);
        return intent;
    }

    /**
     * Accept
     * @param visitor
     */
    @Override
    public void accept(IMessageEventVisitor visitor) {
        visitor.visit(this);
    }

    /**
     * From Bundle
     * @param bundle
     * @return
     */
    public static DeletedMessagesEvent fromBundle(final Bundle bundle) {
        Preconditions.checkNotNull(bundle, "Bundle can not be null");
        final String conversation = bundle.getString(CONVERSATION_ARG, "");
        final ArrayList<String> messageList = (ArrayList<String>)bundle.getSerializable(MESSAGE_IDS_ARG);
        return new DeletedMessagesEvent(conversation, messageList);
    }
}
