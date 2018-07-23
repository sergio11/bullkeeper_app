package sanchez.sanchez.sergio.masom_app.notification.model;

import android.content.Intent;

import sanchez.sanchez.sergio.masom_app.notification.model.impl.BasicNotification;
/**
 * Support Notification
 */
public abstract class SupportNotification implements INotification {

    /**
     * From Intent
     * @param intent
     * @return
     */
    public static INotification fromIntent(final Intent intent) {

        final String notificationAction = intent.getAction();

        INotification notificationFromIntent = null;

        if(notificationAction.equals(BasicNotification.ACTION_NAME)) {
            notificationFromIntent = BasicNotification.fromIntent(intent);
        }

        return notificationFromIntent;

    }

}
