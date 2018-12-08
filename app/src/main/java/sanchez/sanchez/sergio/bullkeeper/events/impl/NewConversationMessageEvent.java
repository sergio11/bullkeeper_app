package sanchez.sanchez.sergio.bullkeeper.events.impl;

import android.content.Intent;
import android.os.Bundle;

import sanchez.sanchez.sergio.bullkeeper.core.events.model.SupportEvent;
import sanchez.sanchez.sergio.bullkeeper.events.handler.INewMessageEventVisitor;

/**
 * New Conversation Message Event
 */
public final class NewConversationMessageEvent extends SupportEvent<INewMessageEventVisitor> {

    @Override
    public Intent toIntent() {
        final Intent notificationIntent = new Intent(getClass().getCanonicalName());
        final Bundle notificationBundle = new Bundle();
        notificationIntent.putExtras(notificationBundle);
        return notificationIntent;
    }

    @Override
    public void accept(INewMessageEventVisitor visitor) {

    }
}
