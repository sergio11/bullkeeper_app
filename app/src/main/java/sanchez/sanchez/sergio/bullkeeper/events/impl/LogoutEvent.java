package sanchez.sanchez.sergio.bullkeeper.events.impl;

import android.content.Intent;
import android.os.Bundle;
import com.fernandocejas.arrow.checks.Preconditions;
import sanchez.sanchez.sergio.bullkeeper.core.events.model.SupportEvent;
import sanchez.sanchez.sergio.bullkeeper.events.handler.ILogoutEventVisitor;

/**
 * Logout Event
 */
public final class LogoutEvent extends SupportEvent<ILogoutEventVisitor> {

    private static String USER_ID = "USER_ID";

    /**
     * User Id
     */
    private final String userId;

    /**
     *
     * @param userId
     */
    public LogoutEvent(String userId) {
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
    public static LogoutEvent fromBundle(final Bundle notificationBundle) {
        Preconditions.checkNotNull(notificationBundle, "Notification Bundle can not be null");
        final String userId = notificationBundle.getString(USER_ID, "");
        return new LogoutEvent(userId);
    }

    /**
     * Accept
     * @param visitor
     */
    @Override
    public void accept(final ILogoutEventVisitor visitor) {
        visitor.visit(this);
    }
}
