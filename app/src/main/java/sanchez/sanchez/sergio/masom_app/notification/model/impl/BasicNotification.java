package sanchez.sanchez.sergio.masom_app.notification.model.impl;

import android.content.Intent;
import android.os.Bundle;

import sanchez.sanchez.sergio.masom_app.notification.local.ILocalSystemNotificationVisitor;
import sanchez.sanchez.sergio.masom_app.notification.model.SupportNotification;
import sanchez.sanchez.sergio.masom_app.notification.remote.IRemoteSystemNotificationVisitor;

/**
 * Basic Notification
 */
public final class BasicNotification extends SupportNotification {

    public static String ACTION_NAME = "MASOM_APP.BASIC_NOTIFICATION";
    public static String NOTIFICATION_TITLE = "TITLE";
    public static String NOTIFICATION_CONTENT = "CONTENT";

    // Notification Title
    private final String title;
    // Notification Content
    private final String content;

    /**
     * @param title
     * @param content
     */
    public BasicNotification(final String title, final String content) {
        this.title = title;
        this.content = content;
    }

    /**
     * Visit Basic Notification
     * @param visitor
     */
    @Override
    public void accept(final ILocalSystemNotificationVisitor visitor) {
        visitor.visit(this);
    }

    /**
     * To Bundle
     * @return
     */
    @Override
    public Intent toIntent() {
        final Intent notificationIntent = new Intent(ACTION_NAME);
        final Bundle notificationBundle = new Bundle();
        notificationBundle.putString(NOTIFICATION_TITLE, title);
        notificationBundle.putString(NOTIFICATION_CONTENT, content);
        notificationIntent.putExtras(notificationBundle);
        return notificationIntent;
    }

    /**
     * From Bundle
     * @param notificationBundle
     * @return
     */
    public static BasicNotification fromBundle(final Bundle notificationBundle) {
        final String notificationTitle = notificationBundle.getString(NOTIFICATION_TITLE);
        final String notificationContent = notificationBundle.getString(NOTIFICATION_CONTENT);
        return new BasicNotification(notificationTitle, notificationContent);
    }
}
