package sanchez.sanchez.sergio.bullkeeper.events.impl;

import android.content.Intent;
import android.os.Bundle;
import com.fernandocejas.arrow.checks.Preconditions;
import sanchez.sanchez.sergio.bullkeeper.core.events.model.SupportEvent;
import sanchez.sanchez.sergio.bullkeeper.events.handler.IMessageEventVisitor;

/**
 * All Messages Deleted Event
 */
public final class AllMessagesDeletedEvent extends SupportEvent<IMessageEventVisitor> {

    /**
     * Args
     */
    private static final String CONVERSATION_ARG = "CONVERSATION_ARG";

    private String conversation;

    /**
     * @param conversation
     */
    public AllMessagesDeletedEvent(final String conversation) {
        this.conversation = conversation;
    }

    public String getConversation() {
        return conversation;
    }

    public void setConversation(String conversation) {
        this.conversation = conversation;
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
    public static AllMessagesDeletedEvent fromBundle(final Bundle bundle) {
        Preconditions.checkNotNull(bundle, "Bundle can not be null");
        final String conversation = bundle.getString(CONVERSATION_ARG, "");
        return new AllMessagesDeletedEvent(conversation);
    }
}
