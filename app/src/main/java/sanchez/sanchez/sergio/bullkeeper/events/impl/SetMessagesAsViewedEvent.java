package sanchez.sanchez.sergio.bullkeeper.events.impl;

import android.content.Intent;
import android.os.Bundle;
import com.fernandocejas.arrow.checks.Preconditions;
import java.util.ArrayList;

import sanchez.sanchez.sergio.bullkeeper.core.events.model.SupportEvent;
import sanchez.sanchez.sergio.bullkeeper.events.handler.IMessageEventVisitor;

/**
 * Set Message As Viewed Event
 */
public final class SetMessagesAsViewedEvent extends SupportEvent<IMessageEventVisitor> {

    /**
     * Args
     */
    private static final String CONVERSATION_ARG = "CONVERSATION_ARG";
    private static final String MESSAGE_IDS_ARG = "MESSAGE_IDS_ARG";

    private final String conversation;
    private final ArrayList<String> messageIds;

    /**
     *
     * @param conversation
     * @param messageIds
     */
    public SetMessagesAsViewedEvent(final String conversation, final ArrayList<String> messageIds) {
        this.conversation = conversation;
        this.messageIds = messageIds;
    }

    public String getConversation() {
        return conversation;
    }

    public ArrayList<String> getMessageIds() {
        return messageIds;
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
        bundle.putSerializable(MESSAGE_IDS_ARG, messageIds);
        intent.putExtras(bundle);
        return intent;
    }

    /**
     *
     * @param visitor
     */
    @Override
    public void accept(IMessageEventVisitor visitor) {
        visitor.visit(this);
    }


    /**
     *
     * @param bundle
     * @return
     */
    public static SetMessagesAsViewedEvent fromBundle(final Bundle bundle) {
        Preconditions.checkNotNull(bundle, "Bundle can not be null");
        final String conversation = bundle.getString(CONVERSATION_ARG, "");
        final ArrayList<String> messageIdsList = (ArrayList<String>) bundle.getSerializable(MESSAGE_IDS_ARG);
        return new SetMessagesAsViewedEvent(conversation, messageIdsList);
    }
}
