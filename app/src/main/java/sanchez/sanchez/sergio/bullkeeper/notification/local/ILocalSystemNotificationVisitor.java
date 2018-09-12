package sanchez.sanchez.sergio.bullkeeper.notification.local;

import sanchez.sanchez.sergio.bullkeeper.notification.model.impl.BasicNotification;
import sanchez.sanchez.sergio.utils.IVisitor;

/**
 *
 */
public interface ILocalSystemNotificationVisitor extends IVisitor {

    /**
     * Visit Basic Notification
     * @param basicNotification
     */
    void visit(final BasicNotification basicNotification);

}
