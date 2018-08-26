package sanchez.sanchez.sergio.bullkeeper.notification.local;

import sanchez.sanchez.sergio.bullkeeper.notification.model.INotification;

/**
 * Local System Notification
 */
public interface ILocalSystemNotification {


    /**
     * Send Notification
     * @param notification
     */
    void sendNotification(final INotification notification);

    /**
     * Register Visitor
     * @param localSystemNotificationVisitor
     */
    void registerVisitor(final ILocalSystemNotificationVisitor localSystemNotificationVisitor,
                         final String action);

    /**
     * Register Visitor
     * @param localSystemNotificationVisitor
     */
    void registerVisitor(final ILocalSystemNotificationVisitor localSystemNotificationVisitor);

    /**
     * Unregister visitor
     */
    void unregisterVisitor();
}