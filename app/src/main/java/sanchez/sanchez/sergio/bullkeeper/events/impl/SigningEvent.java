package sanchez.sanchez.sergio.bullkeeper.events.impl;

import android.content.Intent;
import android.os.Bundle;

import com.fernandocejas.arrow.checks.Preconditions;

import sanchez.sanchez.sergio.bullkeeper.core.events.model.SupportEvent;
import sanchez.sanchez.sergio.bullkeeper.events.handler.ISigningEventVisitor;

/**
 * Signing Event
 */
public final class SigningEvent extends SupportEvent<ISigningEventVisitor> {

    private static String USER_ID = "USER_ID";

    /**
     * User Id
     */
    private String userId;

    public SigningEvent(){}

    public SigningEvent(String userId) {
        this.userId = userId;
    }

    public void setUserId(String userId) {
        this.userId = userId;
    }

    public String getUserId() {
        return userId;
    }

    /**
     * To intent
     * @return
     */
    @Override
    public Intent toIntent() {
        final Intent notificationIntent = new Intent(getClass().getCanonicalName());
        final Bundle notificationBundle = new Bundle();
        notificationBundle.putString(USER_ID, userId);
        notificationIntent.putExtras(notificationBundle);
        return notificationIntent;
    }

    /**
     * From Bundle
     * @param notificationBundle
     * @return
     */
    public static SigningEvent fromBundle(final Bundle notificationBundle) {
        Preconditions.checkNotNull(notificationBundle, "Notification Bundle can not be null");
        final String userId = notificationBundle.getString(USER_ID, "");
        return new SigningEvent(userId);
    }

    /**
     * Accept
     * @param visitor
     */
    @Override
    public void accept(ISigningEventVisitor visitor) {
        visitor.visit(this);
    }
}
